package cn.yufu.SDMTPlatform.commons.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * DES对称加密算法
 * 
 * 加密:encrypt(String srcStr, String key) 解密:descrypt(String encryptStr,String
 * key ) 加密解密过程中采用的key是一样的
 * 
 * @author mengfp
 *
 */
public class DESEncryptUtil {
	private static Log log = Log.getLog(DESEncryptUtil.class);

	private final static String charset = "UTF-8";
	private final static String algorithm = "DES";

	/**
	 * 加密
	 * 
	 * @param srcStr
	 *            明文
	 * @param key
	 *            密钥
	 * @return 密文
	 */
	public static String encrypt(String srcStr, String key) {
		String strEncrypt = null;
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			strEncrypt = base64en.encode(encryptByte(srcStr.getBytes(charset),
					key));
		} catch (Exception e) {
			log.error("加密异常:", e);
			// throw new BssRuntimeException(Result.BSS_SYS_ERROR,"加密异常",e);
		}
		return strEncrypt;
	}

	/**
	 * 解密
	 * 
	 * @param encryptStr
	 *            密文
	 * @param key
	 *            密钥
	 * @return 明文
	 */
	public static String decrypt(String encryptStr, String key) {
		BASE64Decoder base64De = new BASE64Decoder();
		String strDecrypt = null;
		try {
			strDecrypt = new String(decryptByte(
					base64De.decodeBuffer(encryptStr), key), charset);
		} catch (Exception e) {
			log.error("解密异常:", e);
			// throw new BssRuntimeException(Result.BSS_SYS_ERROR,"解密异常",e);
		} finally {
			base64De = null;
		}
		return strDecrypt;
	}

	/**
	 * 字节加密
	 * 
	 * @param srcByte
	 *            明文
	 * @param key
	 *            密钥
	 * @return 密文
	 */
	public static byte[] encryptByte(byte[] srcByte, String key) {
		byte[] byteFina = null;
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, generateKey(key));
			byteFina = cipher.doFinal(srcByte);
		} catch (Exception e) {
			log.error("字节加密异常:", e);
			// throw new BssRuntimeException(Result.BSS_SYS_ERROR,"字节加密异常",e);
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 字节解密
	 * 
	 * @param encryptByte
	 *            密文
	 * @param key
	 *            密钥
	 * @return 明文
	 */
	public static byte[] decryptByte(byte[] encryptByte, String key) {
		Cipher cipher;
		byte[] decryptByte = null;
		try {
			cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.DECRYPT_MODE, generateKey(key));
			decryptByte = cipher.doFinal(encryptByte);
		} catch (Exception e) {
			log.error("字节解密异常:", e);
			// throw new BssRuntimeException(Result.BSS_SYS_ERROR,"字节解密异常",e);
		} finally {
			cipher = null;
		}
		return decryptByte;

	}

	/**
	 * 根据传入的字符串的key生成key对象
	 * 
	 * @param strKey
	 */
	public static Key generateKey(String strKey) {
		try {
			DESKeySpec desKeySpec = new DESKeySpec(strKey.getBytes(charset));
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance(algorithm);
			return keyFactory.generateSecret(desKeySpec);
		} catch (Exception e) {
			// throw new BssRuntimeException(Result.BSS_SYS_ERROR,"生成密钥异常",e);
		}
		return null;
	}

	/**
	 * 测试函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String key = "i am key,let me encrypt you! yfsd!!!";
		String src = "posp";
 
		log.debug("密钥:" + key);
		log.debug("明文:" + src);

		String strEnc = DESEncryptUtil.encrypt(src, key);
		log.debug("加密后,密文:" + strEnc);

		String strDes = DESEncryptUtil.decrypt(strEnc, key);
		log.debug("解密后,明文:" + strDes);

	}

}
