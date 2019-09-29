package cn.com.jansh.utils;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * 提供接收和推送给公众平台消息的加解密接口方法(UTF8编码的字符串).
 * @author gll
 *
 */
public class AESUtils {
	//encodingAesKey 公众平台上，开发者设置的EncodingAESKey
	private static final String ENCODINGAESKEY = "ki2x2iKWuUyD0NWQr59pcB6w1jNV85qm89r3qj2i3p3";
	//token 公众平台上，开发者设置的token
	private static final String TOKEN = "5nvCiJwOH8E88KD2";
	//appId 公众平台appid
	private static final String APPID = "wx82e127bfcb74609a";
	
	/**
	 * 提供接收和推送给公众平台消息的解密接口方法(UTF8编码的字符串).
	 * @param msgSignature
	 * @param timestamp
	 * @param nonce
	 * @param fromXML
	 * @return
	 * @throws AesException
	 */
	public static String wxJieMi(String msgSignature,String timestamp,String nonce,String fromXML) throws AesException {
		WXBizMsgCrypt pc = new WXBizMsgCrypt(TOKEN, ENCODINGAESKEY, APPID);
		String result2 = "";
		if(null != pc){
			result2 = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
			System.out.println("解密后明文: " + result2);
		}
		return result2;
	}
	/**
	 * 提供接收和推送给公众平台消息的加密接口方法(UTF8编码的字符串).
	 * @param msgSignature
	 * @param timestamp
	 * @param nonce
	 * @param fromXML
	 * @return
	 * @throws AesException
	 */
	public static String wxJiaMi(String timestamp,String nonce,String replyMsg) throws AesException {
		WXBizMsgCrypt pc = new WXBizMsgCrypt(TOKEN, ENCODINGAESKEY, APPID);
		String mingwen = "";
		if(null != pc){
			mingwen = pc.encryptMsg(replyMsg, timestamp, nonce);
			System.out.println("加密后: " + mingwen);
		}
		return mingwen;
	}
}
