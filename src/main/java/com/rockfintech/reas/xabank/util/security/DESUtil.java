package com.rockfintech.reas.xabank.util.security;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 * @author zengsheng
 * @ClassName: DESUtil
 * @Description:
 * @date 2018/3/22
 * @Copyright：上海钜石信息科技有限公司
 */
public class DESUtil {
    static String src = "Hello,sahadev!";

    public static void main(String[] args) {
        DES();
    }

    public static void DES() {

        try {
            // 以DES的方式初始化Key生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);// 设置密钥的长度为56位
            // 生成一个Key
            SecretKey generateKey = keyGenerator.generateKey();
            // 转变为字节数组
            byte[] encoded = generateKey.getEncoded();
            // 生成密钥字符串
            String encodeHexString = Hex.encodeHexString(encoded);
            System.out.println("Key ： " + encodeHexString);
            // 再把我们的字符串转变为字节数组，可以用于另一方使用，验证
            byte[] decodeHex = Hex.decodeHex(encodeHexString.toCharArray());
            // 生成密钥对象
            SecretKeySpec secretKeySpec = new SecretKeySpec(decodeHex, "DES");

            // 获取加解密实例
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            // 初始化加密模式
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            // 加密
            byte[] doFinal = cipher.doFinal(src.getBytes());
            System.out.println("加密结果 : " + new HexBinaryAdapter().marshal(doFinal));

            // 初始化解密模式
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            // 解密
            byte[] doFinal2 = cipher.doFinal(doFinal);
            // 输出解密结果
            System.out.println("解密结果 : " + new String(doFinal2));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
