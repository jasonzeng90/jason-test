package com.rockfintech.reas.xabank.util.security;



import org.apache.commons.lang3.ArrayUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


public class RSAUtils {
    public static final String KEY_ALGORTHM="RSA";//
    public static final String SIGNATURE_ALGORITHM="MD5withRSA";

    public static final String PUBLIC_KEY = "RSAPublicKey";//公钥
    public static final String PRIVATE_KEY = "RSAPrivateKey";//私钥

    /**
     * BASE64解密
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception{
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key)throws Exception{
        return (new BASE64Encoder()).encodeBuffer(key);
    }


    /**
     * 初始化密钥
     * @return
     * @throws Exception
     */
    public static Map<String,Object> initKey()throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORTHM);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //私钥
        RSAPrivateKey privateKey =  (RSAPrivateKey) keyPair.getPrivate();

        Map<String,Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);

        return keyMap;
    }

    /**
     * 取得公钥，并转化为String类型
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)throws Exception{
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 取得私钥，并转化为String类型
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception{
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }


    /**
     * 用公钥加密
     * @param data  加密数据
     * @param key   密钥
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data,String key)throws Exception{
        //对公钥解密
        byte[] keyBytes = decryptBASE64(key);
        byte[] returnBytes = new byte[0];
        //取公钥
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        //对数据加密密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 加密时超过117字节就报错。为此采用分段加密的办法来加密
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i += 100) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i,
                    i + 100));
            sb.append(new String(doFinal));
            returnBytes = ArrayUtils.addAll(returnBytes, doFinal);
        }
        return returnBytes;
    }


    /**
     * 用私钥解密
     * @param data  加密数据
     * @param key   密钥
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data,String key)throws Exception{
        //对私钥解密
        byte[] keyBytes = decryptBASE64(key);
        byte[] returnBytes = new byte[0];
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        //对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        // 解密时超过128字节就报错。为此采用分段解密的办法来解密
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i += 128) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i,
                    i + 128));
            sb.append(new String(doFinal));
        }
        returnBytes = sb.toString().getBytes();

        return returnBytes;
    }



    /**
     *  用私钥对信息生成数字签名
     * @param data  //加密数据
     * @param privateKey    //私钥
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data,String privateKey)throws Exception{
        //解密私钥
        byte[] keyBytes = decryptBASE64(privateKey);
        //构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        //指定加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        //取私钥匙对象
        PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        //用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey2);
        signature.update(data);

        return encryptBASE64(signature.sign());
    }



    /**
     * 校验数字签名
     * @param data  加密数据
     * @param publicKey 公钥
     * @param sign  数字签名
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data,String publicKey,String sign)throws Exception{
        //解密公钥
        byte[] keyBytes = decryptBASE64(publicKey);
        //构造X509EncodedKeySpec对象
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        //指定加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        //取公钥匙对象
        PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey2);
        signature.update(data);
        //验证签名是否正常
        return signature.verify(decryptBASE64(sign));

    }

        public static void main(String[] args) throws UnsupportedEncodingException {
        String content = "{name:tansen,age:25,sex:man,address:湖南省常德市澧县}";

//        content = URLEncoder.encode(content,"UTF-8"); 传输过程乱码进行转码加密
        //1.初始化公钥私钥
        String rsaPublicKey = null;
        String rsaPrivateKey = null;
        try {
            Map<String, Object> map = initKey();
            rsaPublicKey = getPublicKey(map);
            rsaPrivateKey = getPrivateKey(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //2.使用公钥加密
        try {
            System.out.println("加密前=="+content);
            byte[] result_m = encryptByPublicKey(content.getBytes(), rsaPublicKey);
            content = encryptBASE64(result_m);
            System.out.println("加密后=="+content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3.私钥解密
        try {
            byte[] b1 = decryptBASE64(content);
            byte[] b2 = decryptByPrivateKey(b1, rsaPrivateKey);
            content = new String(b2);
//            content = URLDecoder.decode(content,"UTF-8");  如果乱码
            System.out.println("解密后=="+content);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //4.私钥加签
        String sign = null;
        try {
            sign = sign(content.getBytes(), rsaPrivateKey);
            System.out.println("签名=="+sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //5.公钥验签
        try {
            boolean flag = verify(content.getBytes(), rsaPublicKey, sign);
            System.out.println("延签结果=="+flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    public static void main(String[] args) {
//        String content = "{name:tansen,age:25,sex:man,address:湖南省常德市澧县}";
//
//        //1.初始化公钥私钥
//        String rsaPublicKey = null;
//        String rsaPrivateKey = null;
//        try {
//            Map<String, Object> map = initKey();
//            rsaPublicKey = getPublicKey(map);
//            rsaPrivateKey = getPrivateKey(map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //2.使用公钥加密
//        try {
//            System.out.println("加密前=="+content);
//            byte[] result_m = encryptByPublicKey(content.getBytes(), rsaPublicKey);
//            content = encryptBASE64(result_m);
//            System.out.println("加密后=="+content);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //3.私钥解密
//        try {
//            byte[] b1 = decryptBASE64(content);
//            byte[] b2 = decryptByPrivateKey(b1, rsaPrivateKey);
//            content = new String(b2);
//            System.out.println("解密后=="+content);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        //4.私钥加签
//        String sign = null;
//        try {
//            sign = sign(content.getBytes(), rsaPrivateKey);
//            System.out.println("签名=="+sign);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //5.公钥验签
//        try {
//            boolean flag = verify(content.getBytes(), rsaPublicKey, sign);
//            System.out.println("延签结果=="+flag);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
