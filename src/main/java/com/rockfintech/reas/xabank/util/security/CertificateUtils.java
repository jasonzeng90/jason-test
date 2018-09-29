	/*
 * /*********************************************************************
 * ====================================================================== CHANGE
 * HISTORY LOG
 * ---------------------------------------------------------------------- MOD.
 * NO.| DATE | NAME | REASON | CHANGE REQ.
 * ----------------------------------------------------------------------
 * |2016年10月12日 | liubing | Created | DESCRIPTION:
 * *********************************************************************
 */
/**
 * @Title: CertificateUtils.java
 * @Package com.bny.common.cert
 * @date 2016年10月12日 下午2:39:35
 * @version V1.0
 */
package com.rockfintech.reas.xabank.util.security;

import com.alibaba.fastjson.JSON;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;



/**
 * @Description 数字证书工具类
 * @Date 2016年10月12日 下午2:39:35
 * @Copyright：上海钜石信息科技有限公司
 */
public class CertificateUtils {

	/**
	 * Java密钥库(Java 密钥库，JKS)KEY_STORE
	 */
	public static final String KEY_STORE = "JKS";
	public static final String X509 = "X.509";
	public static final String TYPE_PFX = "PKCS12";

	public static final String ALGORITHM = "SHA256withRSA";
	public static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";

	/**
	 * 
	 * @Description: 私钥签名
	 * @param dataByte 待签数据
	 * @param privateKey 私钥
	 * @return
	 * @throws Exception
	 * @CreateDate：2016年10月12日 下午2:59:17
	 * @Update:
	 * @UpdateDate:
	 * @UpdateDescription:
	 */
	public static byte[] singWithPrivatekey(byte[] dataByte, RSAPrivateKey privateKey) throws Exception {
		Signature signature = Signature.getInstance(ALGORITHM);
		signature.initSign(privateKey);
		signature.update(dataByte);
		return signature.sign();
	}

	/**
	 * 
	 * @Description: 公钥验签
	 * @param dataByte 待签数据
	 * @param signByte 签名数据
	 * @param publicKey 公钥
	 * @return
	 * @throws Exception
	 * @CreateDate：2016年10月11日 下午12:22:56
	 * @Update:
	 * @UpdateDate:
	 * @UpdateDescription:
	 */
	public static boolean verifySignWithPublicKey(byte[] dataByte, byte[] signByte, RSAPublicKey publicKey) throws Exception {

		Signature signature = Signature.getInstance(ALGORITHM);
		signature.initVerify(publicKey);
		signature.update(dataByte);
		return signature.verify(signByte);
	}

	/**
	 * 
	 * @Description: 私钥加密
	 * @param dataByte 原始字节数组
	 * @param privateKey 私钥
	 * @return
	 * @throws Exception
	 * @CreateDate：2016年10月12日 下午3:02:28
	 * @Update:
	 * @UpdateDate:
	 * @UpdateDescription:
	 */
	public static byte[] privateEncrypt(byte[] dataByte, RSAPrivateKey privateKey) throws Exception {

		byte[] result = null;

		// 秘钥模长，需要小于：据说加密模长-11 解密最大模长
		int modulus = privateKey.getModulus().bitLength() / 8 - 11;
		byte[][] arrays = splitArray(dataByte, modulus);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		for (int i = 0; i < arrays.length; i++) {
			bos.write(cipher.doFinal(arrays[i]));
		}
		bos.flush();
		result = bos.toByteArray();
		bos.close();

		return result;

	}

	/**
	 * 
	 * @Description: 私钥解密
	 * @param srcbyte 加密字节数组
	 * @param privateKey 私钥
	 * @return
	 * @throws Exception
	 * @CreateDate：2016年10月12日 下午3:03:37
	 * @Update:
	 * @UpdateDate:
	 * @UpdateDescription:
	 */
	public static byte[] privateDecrypt(byte[] srcbyte, RSAPrivateKey privateKey) throws Exception {

		// 秘钥模长，需要小于：据说加密模长-11 解密最大模长
		int modulus = privateKey.getModulus().bitLength() / 8;

		byte[][] arrays = splitArray(srcbyte, modulus);

		Cipher cipher = Cipher.getInstance(TRANSFORMATION);//
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		for (int i = 0; i < arrays.length; i++) {
			bos.write(cipher.doFinal(arrays[i]));
		}

		byte[] result = bos.toByteArray();

		bos.close();

		return result;
	}

	/**
	 * 
	 * @Description: 公钥加密
	 * @param srcbyte 原始字节数组
	 * @param publicKey 公钥
	 * @return
	 * @throws Exception
	 * @CreateDate：2016年10月12日 下午3:03:37
	 * @Update:
	 * @UpdateDate:
	 * @UpdateDescription:
	 */
	public static byte[] publicEncrypt(byte[] dataByte, RSAPublicKey publicKey) throws Exception {

		byte[] result = null;

		// 秘钥模长，需要小于：据说加密模长-11 解密最大模长
		int modulus = publicKey.getModulus().bitLength() / 8 - 11;
		byte[][] arrays = splitArray(dataByte, modulus);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		for (int i = 0; i < arrays.length; i++) {
			bos.write(cipher.doFinal(arrays[i]));
		}
		bos.flush();
		result = bos.toByteArray();
		bos.close();

		return result;
	}

	/**
	 * 
	 * @Description: 公钥解密
	 * @param srcbyte 加密字节数组
	 * @param publicKey 公钥
	 * @return
	 * @throws Exception
	 * @CreateDate：2016年10月12日 下午3:03:37
	 * @Update:
	 * @UpdateDate:
	 * @UpdateDescription:
	 */
	public static byte[] publicDecrypt(byte[] srcbyte, RSAPublicKey publicKey) throws Exception {

		// 秘钥模长，需要小于：据说加密模长-11 解密最大模长
		int modulus = publicKey.getModulus().bitLength() / 8;

		byte[][] arrays = splitArray(srcbyte, modulus);

		Cipher cipher = Cipher.getInstance(TRANSFORMATION);//
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		for (int i = 0; i < arrays.length; i++) {
			bos.write(cipher.doFinal(arrays[i]));
		}

		byte[] result = bos.toByteArray();

		bos.close();

		return result;
	}

	public static byte[][] splitArray(byte[] data, int len) {

		// 小于拆分目标长度直接封装返回
		if (data.length <= len) {
			byte[][] arrays = new byte[1][];
			arrays[0] = data;
			return arrays;
		}

		// 读取拆分后总长度
		int x = data.length / len;
		int y = data.length % len;

		// 余数不等于0，返回数组总长度加一
		if (y != 0) {
			x += 1;
		}

		byte[][] arrays = new byte[x][];
		byte[] arr;
		for (int i = 0; i < x; i++) {
			if (i == x - 1 && y != 0) {
				arr = new byte[y];// 处理余数数组
				System.arraycopy(data, i * len, arr, 0, y);
			} else {
				arr = new byte[len];
				System.arraycopy(data, i * len, arr, 0, len);
			}
			arrays[i] = arr;
		}
		return arrays;
	}

	/**
	 * 
	 * @Description:字节数组转Base64字符串
	 * @param signByte
	 * @return
	 * @CreateDate：2016年10月12日 下午4:09:02
	 * @Update:
	 * @UpdateDate:
	 * @UpdateDescription:
	 */
	public static String byteToBase64(byte[] signByte) {
		return new String(Base64.encodeBase64(signByte));
	}

	/**
	 * 
	 * @Description:Base64字符串转字节数组
	 * @param base64
	 * @return
	 * @CreateDate：2016年10月12日 下午4:09:26
	 * @Update:
	 * @UpdateDate:
	 * @UpdateDescription:
	 */
	public static byte[] base64ToByte(String base64) {
		return Base64.decodeBase64(base64.getBytes());
	}

	/**
	 * 
	 * @Description: 根据密钥库获得私钥
	 * @param keyStorePath 密钥库存储路径
	 * @param password 密钥库密码
	 * @return
	 * @throws Exception
	 * @CreateDate：2016年10月17日 下午3:57:04
	 * @Update:
	 * @UpdateDate:
	 * @UpdateDescription:
	 */
	public static RSAPrivateKey getPrivateKey(String keyStorePath, String password) throws Exception {

		RSAPrivateKey privateKey = null;

		KeyStore p12KeyStore = KeyStore.getInstance(TYPE_PFX);
		InputStream clientInput = new BufferedInputStream(new FileInputStream(keyStorePath));
		try {
			p12KeyStore.load(clientInput, password.toCharArray());// p12的密码
		} finally {
			clientInput.close();
		}

		Enumeration<String> alist = p12KeyStore.aliases();
		while (alist.hasMoreElements()) {
			String eName = alist.nextElement();
			privateKey = (RSAPrivateKey) p12KeyStore.getKey(eName, password.toCharArray());
		}

		return privateKey;

	}

	/**
	 * 
	 * @Description: 通过字节流获取公钥
	 * @param is 证书字节流
	 * @return
	 * @throws Exception
	 * @CreateDate：2016年10月19日 下午6:12:34
	 * @Update:
	 * @UpdateDate:
	 * @UpdateDescription:
	 */
	public static RSAPublicKey getPublicKey(InputStream is) throws Exception {

		RSAPublicKey publicKey = null;

		CertificateFactory certFactory = CertificateFactory.getInstance(X509);
		X509Certificate cert = (X509Certificate) certFactory.generateCertificate(is);
		publicKey = (RSAPublicKey) cert.getPublicKey();

		return publicKey;
	}

	/**
	 * 
	 * @Description: 通过证书获取公钥
	 * @param certificatePath 证书存储路径
	 * @return
	 * @CreateDate：2016年10月17日 下午4:02:26
	 * @Update:
	 * @UpdateDate:
	 * @UpdateDescription:
	 */
	public static RSAPublicKey getPublicKey(String certificatePath) throws Exception {

		RSAPublicKey publicKey = null;

		CertificateFactory certFactory = CertificateFactory.getInstance(X509);
		X509Certificate cert = (X509Certificate) certFactory.generateCertificate(new FileInputStream(new File(certificatePath)));
		publicKey = (RSAPublicKey) cert.getPublicKey();

		return publicKey;

	}

	public static void main(String[] args) throws Exception {

		/********* 应用示例 begin **********/
		// 定义变量
		// String data = "{'custId':3867939}";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custId", "3867939");
		map.put("approvalStatus", "-1");
		map.put("loanStatus", "1");
		map.put("offset", 0);
		map.put("queryCount", "10");
		String data = JSON.toJSONString(map);
		String KEY_STORE_NAME = "client1@testbusi.p12";
		String CERTIFICATE_NAME = "client1.crt";
		String password = "p12_password";

		String certificatePath = "C:/Users/Administrator/Desktop/cert/" + CERTIFICATE_NAME;
		String keyStorePath = "C:/Users/Administrator/Desktop/cert/" + KEY_STORE_NAME;

		// 获取私钥
		RSAPrivateKey privateKey = getPrivateKey(keyStorePath, password);
		// 获取公钥
		RSAPublicKey publicKey = getPublicKey(certificatePath);

		System.out.println("===================================");
		// 私钥加密 -- 公钥解密
		byte[] encryptBytes = privateEncrypt(data.getBytes(), privateKey);
		System.out.println("私钥加密Base64字符串：" + byteToBase64(encryptBytes));
		byte[] decrypteBytes = publicDecrypt(encryptBytes, publicKey);
		System.out.println("公钥解密字符串：" + new String(decrypteBytes));

		System.out.println("===================================");
		// 公钥加密 -- 私钥解密
		encryptBytes = publicEncrypt(data.getBytes(), publicKey);
		System.out.println("公钥钥加密Base64字符串：" + byteToBase64(encryptBytes));
		decrypteBytes = privateDecrypt(encryptBytes, privateKey);
		System.out.println("私钥解密字符串：" + new String(decrypteBytes));

		System.out.println("===================================");
		// 签名数据
		byte[] sign = singWithPrivatekey(data.getBytes(), privateKey);
		String signBase64 = byteToBase64(sign);
		System.out.println("私钥签名Base64:" + signBase64);
		sign = base64ToByte(signBase64);
		System.out.println("解密Base64：" + sign);
		boolean verifyresult = verifySignWithPublicKey(data.getBytes(), sign, publicKey);
		System.out.println("公钥验证结果:" + verifyresult);
		/********* 应用示例 end **********/

		// String str =
		// "Yh7e2sUJZ47QgXvRpVmZo+p5MqlmfdwYGx4rvICoo65K/tjLfJEe/UNjqCM5+FCdCla+1AeIkRx3yO9KJ+k5VHNmBzgnAkzzAWCJiIiQqiR8KxOleq7iSU4YCjco9EcmSRJAedJs/UNt8+5o6PZ3IBbSGa5+P8JiFNFlUuzyqENJWmKEqTBAk49sYPOeiVmYryPVOGEtNzxwlApcTnztLwz1iqERLm0VghZcDy/+Kg8hX03matJPWTfKNgazO4Mroef6durBY7Jd3ffrVxjV5RfQrZ1cpGc17/YvUdqUVV8hfra/Y6BHn0ihCPSqjVAq2yvpN0xZ3iym+GWBFUctQQ==";
		String str = byteToBase64(encryptBytes);
		String s = URLEncoder.encode(str);
		System.out.println("s=" + s);

	}

}
