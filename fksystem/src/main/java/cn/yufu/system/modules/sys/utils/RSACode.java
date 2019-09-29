package cn.yufu.system.modules.sys.utils;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSACode {
	

	public static final String KEY_ALGORITHM = "RSA";
	/*public static final String ACCESS_PUBLIC_KEY_PATH = PathUtils.getResoucesPath(RSACode.class) + "rsa_public_key.pem";	//"D:/sslrsa/rsa_public_key.pem";
    public static final String ACCESS_PRIVATE_KEY_PATH =PathUtils.getResoucesPath(RSACode.class) + "pkcs8_rsa_private_key.pem";	//"D:/sslrsa/pkcs8_rsa_private_key.pem";
    */
   /**
    * 路径私钥加密
    * @param data
    * @return
    * @throws Exception
    */
    public static byte[] encryptByPrivateKeyPath(String data, String path) throws Exception {
    	String key = readFileByLines(path);
		return encryptByPrivateKey( data,  key);
	}
    
    /**
     * 路径公钥解密
     * @param data
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKeyPath(String data, String path) throws Exception {
    	String key = readFileByLines(path);
		return decryptByPublicKey( data,  key);
	}
    
    /**
     * 路径私钥解密
     * @param data
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKeyPath(String data, String path) throws Exception {
    	String key = readFileByLines(path);
    	return decryptByPrivateKey( data,  key);
    }
    
    /**
     * 路径公钥加密
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKeyPath(String data, String path) throws Exception {
    	String key = readFileByLines(path);
    	return encryptByPublicKey( data,  key) ;
    }
	
	/**
	 * 私钥加密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 * @author yangxing
	 * 2014-7-22
	 */
	public static byte[] encryptByPrivateKey(String data, String key) throws Exception {
		return encryptByPrivateKey(data.getBytes(),Base64.decodeBase64(key));
	}
	
	/**
	 * 公钥解密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 * @author yangxing
	 * 2014-7-22
	 */
	public static String decryptByPublicKey(String data, String key) throws Exception {
		byte[] dataByte = decryptByPublicKey(Base64.decodeBase64(data),Base64.decodeBase64(key));
		return new String(dataByte);
	}
	
	
	/**
	 * 公钥加密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 * @author yangxing
	 * 2014-7-22
	 */
	public static byte[] encryptByPublicKey(String data, String key) throws Exception {
		return encryptByPublicKey(data.getBytes(),Base64.decodeBase64(key));
	}
	
	/**
	 * 私钥解密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 * @author yangxing
	 * 2014-7-22
	 */
	public static String decryptByPrivateKey(String data, String key) throws Exception {
		byte[] dataByte = decryptByPrivateKey(Base64.decodeBase64(data),Base64.decodeBase64(key));
		return new String(dataByte);
	}

	/**
	 * 私钥加密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 * @author yangxing
	 * 2014-7-22
	 */
	public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 公钥解密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 * @author yangxing
	 * 2014-7-22
	 */
	public static byte[] decryptByPublicKey(byte[] data, byte[] key)throws Exception {
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 公钥加密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 * @author yangxing
	 * 2014-7-22
	 */
	public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 私钥解密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 * @author yangxing
	 * 2014-7-22
	 */
	public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 私钥验证公钥密文
	 * 
	 * @param data
	 * @param sign
	 * @param pvKey
	 * @return
	 * @throws Exception
	 * @author yangxing 2014-7-21
	 */
	public static boolean checkPublicEncrypt(String data, String sign, String pvKey) throws Exception {
		return data.equals(decryptByPrivateKey(sign, pvKey));
	}
	
	/**
	 * 公钥验证私钥密文
	 * @param data
	 * @param sign
	 * @param pbKey
	 * @return
	 * @throws Exception
	 * @author yangxing
	 * 2014-7-22
	 */
	public static boolean checkPrivateEncrypt(String data, String sign,String pbKey) throws Exception {
		return data.equals(decryptByPublicKey(sign, pbKey));
	}

	/**
	 * 取得私钥
	 * @param keyMap
	 * @return
	 * @throws Exception
	 * @author yangxing 2014-7-21
	 */
//	public static byte[] getPrivateKey(Map<String, Object> keyMap) throws Exception {
//		Key key = (Key) keyMap.get(PRIVATE_KEY);
//		return key.getEncoded();
//	}
	
	/**
	 * 取得公钥
	 * @param keyMap
	 * @return
	 * @throws Exception
	 * @author yangxing 2014-7-21
	 */
//	public static byte[] getPublicKey(Map<String, Object> keyMap)throws Exception {
//		Key key = (Key) keyMap.get(PUBLIC_KEY);
//		return key.getEncoded();
//	}

	/**
	 * 初始化密钥
	 * 
	 * @return
	 * @throws Exception
	 * @author yangxing 2014-7-21
	 */
//	public static Map<String, Object> initKey() throws Exception {
//		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
//		keyPairGen.initialize(KEY_SIZE);
//		KeyPair keyPair = keyPairGen.generateKeyPair();
//		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//		Map<String, Object> keyMap = new HashMap<String, Object>(2);
//		keyMap.put(PUBLIC_KEY, publicKey);
//		keyMap.put(PRIVATE_KEY, privateKey);
//		return keyMap;
//	}
	
	/**
	 * getDecryptbankandfkXml:密码、福卡密码、cvn解密 <br/>
	 * @author yangxing
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	/*public static String getDecryptbankandfkXml(String securityMsg) throws Exception{
		String certPath = SystemConfig.getString("cer.path");
		String certKey = EncDecUtil.getCertKey("EJROMGFUKAPAYMENT0606",certPath);
		BASE64Decoder dec=new BASE64Decoder();
		byte[] xmlBody = RSACoderUtil.decryptByPrivateKey(dec.decodeBuffer(securityMsg),dec.decodeBuffer(certKey));
		return  new String(xmlBody);
	}*/
	
	public static String toStringWithBase64(byte[] data){
		return new String(Base64.encodeBase64(data));
	}
	
	 public static String readFileByLines(String fileName) throws Exception{
	        File file = new File(fileName);
	        BufferedReader reader = null;
	        StringBuilder sb= new StringBuilder();  
	        try {
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            int line = 1;
	            while ((tempString = reader.readLine()) != null) {
	                // 显示行号
	                //System.out.println("line " + line + ": " + tempString);
	                //line++;
	                if(tempString.charAt(0)=='-'){  
	                    continue;  
	                }else{  
	                    sb.append(tempString);  
	                }  
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw e;
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                	e1.printStackTrace();
	                }
	            }
	        }
	        return sb.toString();
	    }
	
	public static void main(String[] args) throws Exception {
		/*
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("DESede");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 密钥位数
		keyPairGen.initialize(1024);
		// 密钥对
		KeyPair keyPair = keyPairGen.generateKeyPair();
		// 公钥
		PublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		System.out.println(publicKey.toString());
		*/
		/*
		 try {
		 BASE64Encoder enc = new BASE64Encoder();
		
		 Map<String, Object> mp = initKey();
		 byte[] publicKey = getPublicKey(mp);
		 byte[] privateKey = getPrivateKey(mp);
		 String pbkey = enc.encode(publicKey);
		 String prkey = enc.encode(privateKey);
		
		 System.out.println("公钥:" + pbkey);
		 System.out.println("私钥:" + prkey);
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		 */
		
		String password = "3131313131313131";
		System.out.println("原文："+password);
		try {
////			String encryptStr = RSACoderUtil.toStringWithBase64(RSACoderUtil.encryptByPublicKey(password, publicKeyStr));
//			String encryptStr = RSACode.toStringWithBase64(RSACode.encryptByPublicKeyPath(password, ACCESS_PUBLIC_KEY_PATH));
//			System.out.println("密文："+encryptStr);
////			String decryptStr = RSACode.decryptByPrivateKey(encryptStr, privateKeyStr);
//			String decryptStr = RSACode.decryptByPrivateKeyPath(encryptStr, ACCESS_PRIVATE_KEY_PATH);
//			System.out.println("解文："+decryptStr);

			String pin = "3131313131313131";
			//String ACCESS_PRIVATE_KEY_PATH =PathUtils.getResoucesPath(RSACode.class) + "pkcs8_rsa_private_key.pem";
			//String ACCESS_PUBLIC_KEY_PATH = PathUtils.getResoucesPath(RSACode.class) + "rsa_public_key.pem";
			//String encryptStr = RSACode.toStringWithBase64(RSACode.encryptByPublicKeyPath(password, ACCESS_PUBLIC_KEY_PATH));
			//String decryptStr = RSACode.decryptByPrivateKeyPath(encryptStr, ACCESS_PRIVATE_KEY_PATH);
			System.out.println(pin);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	


}
