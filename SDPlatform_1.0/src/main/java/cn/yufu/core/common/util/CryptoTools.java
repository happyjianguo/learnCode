package cn.yufu.core.common.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CryptoTools {
	public static String encryptMode(String skey, String sdata) throws Exception {
		byte[] key = new BASE64Decoder().decodeBuffer(skey);
		byte[] data = sdata.getBytes("UTF-8");
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] bOut = cipher.doFinal(data);
		return new BASE64Encoder().encode(bOut);
	}

	/**
	 * ECB解密,不要IV
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            Base64编码的密文
	 * @return 明文
	 * @throws Exception
	 */
	public static String decryptMode(String skey, String sdata) throws Exception {
		byte[] key = new BASE64Decoder().decodeBuffer(skey);
		byte[] data = new BASE64Decoder().decodeBuffer(sdata);

		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, deskey);
		byte[] bOut = cipher.doFinal(data);
		return new String(bOut, "UTF-8");
	}

	public static void main(String[] args) throws Exception {
		String a = CryptoTools.encryptMode("112233Fdefw0iqRoPUyG4Mv3UmcgYrWo", "mengfp");
		System.out.println(a);
		System.out.println(CryptoTools.decryptMode("112233Fdefw0iqRoPUyG4Mv3UmcgYrWo", a));
	}
}
