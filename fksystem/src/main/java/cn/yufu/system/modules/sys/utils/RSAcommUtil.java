package cn.yufu.system.modules.sys.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.net.Socket;


public class RSAcommUtil {
	private static Logger logger = LoggerFactory.getLogger(RSAcommUtil.class);
	/** 指定key的大小 */
	private static int KEYSIZE = 1024;
    public static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";

	public static final String KEY_ALGORITHM = "RSA";

	private static final  String ACCESS_PRIVATE_KEY_PATH  = PathUtils.getResoucesPath(RSACode.class) + "pkcs8_rsa_private_key.pem";

	/**
	 * 调用加密机对RSA密文解密
	 * @param pinBlock
	 * @throws Exception
	 */
	public static String decryptRsaPinBlock(String pinBlock,String ip,int port,String pin) throws Exception{
				
		Socket socket = null;
		OutputStream os = null;
		DataInputStream dis = null;
		try {
			socket = new Socket(ip, port);
			dis = new DataInputStream(socket.getInputStream());
			os = socket.getOutputStream();
			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			byte[] commandType = new byte[1];
			commandType[0] = Tools.hexStringToByte("C0")[0];
			byteArray.write(commandType);
			byte[] command = new byte[1];
			command[0] = 0x16;
			byteArray.write(command);
			byte[] retainKey = new byte[8];
			byteArray.write(retainKey);
			byte[] keyIndex = new byte[2];
			keyIndex[1] = 0x04;
			byteArray.write(keyIndex);

			byte[] accessPassword = new byte[8];
			//pin = RSACode.decryptByPrivateKeyPath(pin, ACCESS_PRIVATE_KEY_PATH);
			accessPassword[0] = Byte.valueOf(pin.substring(0, 2), 16);
			accessPassword[1] = Byte.valueOf(pin.substring(2, 4), 16);
			accessPassword[2] = Byte.valueOf(pin.substring(4, 6), 16);
			accessPassword[3] = Byte.valueOf(pin.substring(6, 8), 16);
			accessPassword[4] = Byte.valueOf(pin.substring(8, 10), 16);
			accessPassword[5] = Byte.valueOf(pin.substring(10, 12), 16);
			accessPassword[6] = Byte.valueOf(pin.substring(12, 14), 16);
			accessPassword[7] = Byte.valueOf(pin.substring(14, 16), 16);

			byteArray.write(accessPassword);
			byte[] dataBlockByte = Tools.hexStringToByte(pinBlock);
			byte[] dataLength = new byte[2];
			String hexLen = Integer.toHexString(dataBlockByte.length);
			byte[] b_Len = Tools.hexStringToByte(hexLen);
			if(b_Len.length>1){
				dataLength[0] = b_Len[0];
				dataLength[1] = b_Len[1];
			}else{
				dataLength[1] = b_Len[0];
			}
			byteArray.write(dataLength);
			byteArray.write(dataBlockByte);
			byte[]  b_result = byteArray.toByteArray();

			os.write(b_result);
			os.flush();
			dis = new DataInputStream(socket.getInputStream());
			byte[] bufData = new byte[50];
			dis.read(bufData);
			byte[] resCode = new byte[1];
			System.arraycopy(bufData, 0, resCode, 0, resCode.length);
			if("45".equals(Tools.byte2hex(resCode))){
				byte[] errorCode = new byte[1];//解密错误码
				System.arraycopy(bufData, 9, errorCode, 0, errorCode.length);
				logger.error("加密机解密失败，响应码 " + Tools.byte2hex(errorCode));
				throw new Exception("加密机解密失败，响应码 "+ Tools.byte2hex(errorCode));
			}else if("41".equals(Tools.byte2hex(resCode))){
				logger.error("加密机解密成功，开始组装返回数据域");
				byte[] len = new byte[2];//数据长度
				System.arraycopy(bufData, 9, len, 0, len.length);
				byte[] dataByte = new byte[Integer.parseInt(Tools.byte2hex(len), 16)];
				System.arraycopy(bufData, 11, dataByte, 0, dataByte.length);
				return new String (dataByte);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw  e;
		} finally {
			try {
				if(os !=null){
					os.close();
				}
				if(dis !=null ){
					dis.close();
				}
				if(socket !=null){
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw  e;
			}
		}
	}



	public static void main(String args[]) throws Exception{/*
		String pin = PropertiesUtil.getValRelPath(RSAcommUtil.class, SecurityConfig.PROP_FILE,SecurityConfig.ENCRYPTOR_PIN_KEY);
		String ip = PropertiesUtil.getValRelPath(RSAcommUtil.class, SecurityConfig.PROP_FILE, SecurityConfig.ENCRYPTOR_IP_KEY);
		String port = PropertiesUtil.getValRelPath(RSAcommUtil.class, SecurityConfig.PROP_FILE, SecurityConfig.ENCRYPTOR_PORT_KEY);

		String rsaHex = "1D489813CBBA6DA73BA3F4D5564761E95C861B6E20E0275137D222CF3C3595AC51318512C6C9D08191A5BC44DDBFDA80E3B5765A0886936320AF4F1FDD6B06D212839FC53654F17B1D037B6F28FDD4D239C32136FE9ECF8AA61F78DFEF58D82B89FE9371EA8317B2901637DACF712F7FE46175FB24F84DD5425590B48A8A681D";
		RSAcommUtil.decryptRsaPinBlock(rsaHex,ip,Integer.valueOf(port),pin);




	*/}

}