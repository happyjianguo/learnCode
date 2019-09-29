package cn.yufu.system.modules.sys.utils;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 */
public abstract class Tools {
	
	public static double fenToYuan(String fen){
		if(null == fen || "".equals(fen)) return 0;
		Integer f = Integer.valueOf(fen);
		return f/100;
	}

	public static byte[] shortToByteArray(short s) {
		byte[] shortBuf = new byte[2];
		for (int i = 0; i < 2; i++) {
			int offset = (shortBuf.length - 1 - i) * 8;
			shortBuf[i] = (byte) ((s >>> offset) & 0xff);
		}
		return shortBuf;
	}

	public static String byte2hex(byte[] b) {
		if (b == null)
			return null;
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}
//	public static int byte2Int(byte[] byteArray) {
//		StringBuilder sBuilder = new StringBuilder();
//		for (byte b : byteArray) {
//			sBuilder.append(b);
//		}
//		int intValue = Integer.valueOf(sBuilder.toString());
//		System.out.println(intValue);
//	}

	public static String byte2hex(byte[] b, int off, int len) {
		byte[] data = Arrays.copyOfRange(b, off, off + len);
		return byte2hex(data);
	}

	public static byte[] hexStringToByte(String hex) {
		 if (hex.length() % 2 != 0) {
			 hex = "0"+hex;
	     }
		hex = hex.toUpperCase();
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}
	 
	 /**
	     * 二进制字符串转十进制
	     * 
	     * @param binary
	     */
	    public static int binaryToAlgorism(String binary) {
	        int max = binary.length();
	        int result = 0;
	        for (int i = max; i > 0; i--) {
	            char c = binary.charAt(i - 1);
	            int algorism = c - '0';
	            result += Math.pow(2, max - i) * algorism;
	        }
	        return result;
	    }
	 
	 /**
	     * 十六转二进制
	     * 
	     * @param hex
	     *            十六进制字符�?
	     * @return 二进制字符串
	     */
	    public static String hexStringToBinary(String hex) {
	        hex = hex.toUpperCase();
	        String result = "";
	        int max = hex.length();
	        for (int i = 0; i < max; i++) {
	            char c = hex.charAt(i);
	            switch (c) {
	            case '0':
	                result += "0000";
	                break;
	            case '1':
	                result += "0001";
	                break;
	            case '2':
	                result += "0010";
	                break;
	            case '3':
	                result += "0011";
	                break;
	            case '4':
	                result += "0100";
	                break;
	            case '5':
	                result += "0101";
	                break;
	            case '6':
	                result += "0110";
	                break;
	            case '7':
	                result += "0111";
	                break;
	            case '8':
	                result += "1000";
	                break;
	            case '9':
	                result += "1001";
	                break;
	            case 'A':
	                result += "1010";
	                break;
	            case 'B':
	                result += "1011";
	                break;
	            case 'C':
	                result += "1100";
	                break;
	            case 'D':
	                result += "1101";
	                break;
	            case 'E':
	                result += "1110";
	                break;
	            case 'F':
	                result += "1111";
	                break;
	            }
	        }
	        return result;
	    }

	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	private static byte abcd_to_asc(byte abyte) {
		if (abyte <= 9)
			abyte = (byte) (abyte + '0');
		else
			abyte = (byte) (abyte + 'A' - 10);
		return (abyte);
	}

	public static int BCD2INT(byte[] bcd_buf, int len) {
		try {
			return Integer.parseInt(new String(BCD2ASCII(bcd_buf, len),
					"US-ASCII"));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static byte[] BCD2ASCII(byte[] bcd_buf, int len) {
		int i, n;
		n = len;
		ByteBuffer asc_buf = ByteBuffer.allocate(n);
		byte tmp;
		for (i = 0; i < n / 2; i++) {
			tmp = (byte) ((bcd_buf[i] & 0xf0) >> 4);
			tmp = abcd_to_asc(tmp);
			asc_buf.put(tmp);
			tmp = (byte) (bcd_buf[i] & 0x0f);
			tmp = abcd_to_asc(tmp);
			asc_buf.put(tmp);
		}
		if (n % 2 != 0) {
			tmp = (byte) ((bcd_buf[i] & 0xf0) >> 4);
			tmp = abcd_to_asc(tmp);
			asc_buf.put(tmp);
		}
		asc_buf.flip();
		byte[] res = null;
		res = new byte[asc_buf.remaining()];
		asc_buf.get(res, 0, res.length);
		return res;
	}

	private static byte aasc_to_bcd(byte asc) {
		byte bcd;
		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else if ((asc > 0x39) && (asc <= 0x3f))
			bcd = (byte) (asc - '0');
		else {
			bcd = 0x0f;
		}
		return bcd;
	}

	public static byte[] ASCII2BCD(byte[] asc_buf) {
		int j = 0;
		int n = asc_buf.length;
		byte[] bcd_buf = new byte[(n + 1) / 2];
		for (int i = 0; i < (n + 1) / 2; i++) {
			bcd_buf[i] = aasc_to_bcd(asc_buf[j++]);
			bcd_buf[i] = (byte) (((j >= n) ? 0x00 : aasc_to_bcd(asc_buf[j++])) + (bcd_buf[i] << 4));
		}
		return bcd_buf;
	}

	public static byte[] ASCII2BCD(byte[] asc_buf, int off, int len) {
		byte[] data = Arrays.copyOfRange(asc_buf, off, off + len);

		return ASCII2BCD(data);
	}

	/**
	 *
	 * @param i
	 * @param format
	 * @return
	 * @throws Exception
	 */
	/*public static byte[] INT2BCD(int i, FieldFormat format) throws Exception {
		switch (format) {
		case LLVAR:
			return String.format("%1$02d", i).getBytes();
		case LLLVAR:
			return String.format("%1$03d", i).getBytes();
		case LVAR:
			return String.format("%1$01d", i).getBytes();
		default:
			throw new RuntimeException();
		}
	}*/

	public static byte[] str2Bcd(String asc) {
		if (asc.length() % 2 != 0) {
			asc = "0" + asc;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		char[] ch = asc.toCharArray();
		for (int i = 0; i < ch.length; i += 2) {
			int height = ch[i] - 48;
			int low = ch[i + 1] - 48;
			out.write(height << 4 | low);
		}
		return out.toByteArray();
	}

	public static String bcd2Str(byte[] bcd) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < bcd.length; i++) {
			int height = ((bcd[i] & 0xff) >> 4) + 48;
			builder.append((char) height);
			int low = (bcd[i] & 0x0f) + 48;
			builder.append((char) low);
		}
		return builder.toString().replaceFirst("0", "");
	}

	public static byte[] hexStringToByteArray(String text) {
		if (text == null)
			return null;
		byte[] result = new byte[text.length() / 2];
		for (int i = 0; i < result.length; ++i) {
			int x = Integer.parseInt(text.substring(i * 2, i * 2 + 2), 16);
			result[i] = x <= 127 ? (byte) x : (byte) (x - 256);
		}
		return result;
	}

	public static byte xorCheck(byte[] data) {
		byte result = 0;
		for (int i = 0; i < data.length; i++) {
			result ^= data[i];
		}
		return result;
	}

//	public static byte[] getMac(String macStr, byte[] content) throws Exception {
//		byte[] tmp = content;
//		byte[] src = null;
//		if (tmp.length % 8 != 0) {
//			src = new byte[(tmp.length / 8 + 1) * 8];
//		} else {
//			src = new byte[tmp.length];
//		}
//		System.arraycopy(tmp, 0, src, 0, tmp.length);
//		if (tmp.length != src.length) {
//			for (int i = tmp.length; i < src.length; i++) {
//				src[i] = 0x00;
//			}
//		}
//		byte[] xorInit = new byte[8];
//		System.arraycopy(src, 0, xorInit, 0, 8);
//		for (int i = 1; i < src.length / 8; i++) {
//			byte[] xorTmp = new byte[8];
//			System.arraycopy(src, i * 8, xorTmp, 0, xorTmp.length);
//			for (int j = 0; j < 8; j++) {
//				xorInit[j] ^= xorTmp[j];
//			}
//		}
//
//		String xorHexStrTmp = Tools.byte2hex(xorInit);
//		byte[] xorHex = xorHexStrTmp.getBytes();
//
//		byte[] desSrc = new byte[8];
//		System.arraycopy(xorHex, 0, desSrc, 0, 8);
//		byte[] desSrc2 = new byte[8];
//		System.arraycopy(xorHex, 8, desSrc2, 0, 8);
//		DesUtil des1 = new DesUtil(macStr);
//		byte[] desEnTmp = des1.encrypt(desSrc);
//		for (int j = 0; j < 8; j++) {
//			desEnTmp[j] ^= desSrc2[j];
//		}
//		byte[] desEn = des1.encrypt(desEnTmp);
//		String aa = Tools.byte2hex(desEn);
//		byte[] bb = aa.getBytes();
//		byte[] mac = new byte[8];
//		System.arraycopy(bb, 0, mac, 0, 8);
//		return mac;
//	}

	/**
	 * 
	 * @param macStr
	 * @param content
	 * @return
	 * @throws Exception
	 */
//	public static byte[] getMac2(String macStr, byte[] content)
//			throws Exception {
//		byte[] src = null;
//
//		if (content.length % 8 != 0) {
//			src = new byte[(content.length / 8 + 1) * 8];
//			System.arraycopy(content, 0, src, 0, content.length);
//			for (int i = content.length; i < src.length; i++) {
//				src[i] = 0x00;
//			}
//		} else {
//			src = content;
//		}
//		byte[] xorInit = new byte[8];
//		System.arraycopy(src, 0, xorInit, 0, 8);
//		for (int i = 1; i < src.length / 8; i++) {
//			byte[] xorTmp = new byte[8];
//			System.arraycopy(src, i * 8, xorTmp, 0, xorTmp.length);
//			for (int j = 0; j < 8; j++) {
//				xorInit[j] ^= xorTmp[j];
//			}
//		}
//		String xorHexStrTmp = Tools.byte2hex(xorInit);
//		byte[] xorHex = xorHexStrTmp.getBytes();
//		byte[] desSrc = new byte[8];
//		System.arraycopy(xorHex, 0, desSrc, 0, 8);
//		byte[] desSrc2 = new byte[8];
//		System.arraycopy(xorHex, 8, desSrc2, 0, 8);
//		DesUtil des1 = new DesUtil(macStr);
//		byte[] desEnTmp = des1.encrypt(desSrc);
//
//		for (int j = 0; j < 8; j++) {
//			desEnTmp[j] ^= desSrc2[j];
//		}
//		byte[] desEn = des1.encrypt(desEnTmp);
//		String aa = Tools.byte2hex(desEn);
//		byte[] bb = aa.getBytes();
//		byte[] mac = new byte[8];
//		System.arraycopy(bb, 0, mac, 0, 8);
//		return mac;
//	}

	/**
	 * 
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 */
//	public static byte[] calcMac(String key, byte[] input) throws Exception {
//		// 对MAB，按�?个字节做异或（不管信息中的字符格式），如果最后不�?个字节，则添�?0X00"
//		int length = input.length;
//		int x = length % 8;
//		int addLength = 0;
//		byte[] src = null;
//		if (x != 0) {
//			addLength = 8 - x;
//			src = new byte[length + addLength];
//			System.arraycopy(input, 0, src, 0, length);
//		} else {
//			src = input;
//		}
//		byte[] xorInit = new byte[8];
//		System.arraycopy(src, 0, xorInit, 0, 8);
//		for (int i = 1; i < src.length / 8; i++) {
//			byte[] xorTmp = new byte[8];
//			System.arraycopy(src, i * 8, xorTmp, 0, xorTmp.length);
//			byte[] t = bytesXOR(xorInit, xorTmp);
//			xorInit = t;
//		}
//		// 将异或运算后的最�?个字节（RESULT BLOCK）转换成16 个HEXDECIMAL
//		String xorHexStrTmp = Tools.byte2hex(xorInit);
//		byte[] xorHex = xorHexStrTmp.getBytes();
//		byte[] desSrc = new byte[8];
//		// 取前8 个字节用MAK加密
//		System.arraycopy(xorHex, 0, desSrc, 0, 8);
//		byte[] desSrc2 = new byte[8];
//		System.arraycopy(xorHex, 8, desSrc2, 0, 8);
//		DesUtil des1 = new DesUtil(key);
//		byte[] desEnTmp = des1.encrypt(desSrc);
//		// 将加密后的结果与�? 个字节异�?
//		desEnTmp = bytesXOR(desEnTmp, desSrc2);
//		// 用异或的结果TEMP BLOCK 再进行一次单倍长密钥算法运算�?
//		byte[] desEn = des1.encrypt(desEnTmp);
//		// 将运算后的结果（ENC BLOCK2）转换成16 个HEXDECIMAL�?
//		String aa = Tools.byte2hex(desEn);
//		byte[] bb = aa.getBytes();
//		byte[] mac = new byte[8];
//		// 取前8个字节作为MAC值�?
//		System.arraycopy(bb, 0, mac, 0, 8);
//		return mac;
//	}

	/**
	 * 计算两个数组的异或�?
	 * 
	 * @param src1
	 * @param src2
	 * @return
	 */

	public static byte[] bytesXOR(byte[] src1, byte[] src2) {
		int length = src1.length;
		if (length != src2.length) {
			return null;
		}
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++) {
			result[i] = (byte) ((src1[i] & 0xFF) ^ (src2[i] & 0xFF));
		}
		return result;
	}

	// 左补0
	public static String padLeft(String s, int length) {
		byte[] bs = new byte[length];
		byte[] ss = s.getBytes();
		Arrays.fill(bs, (byte) (48 & 0xff));
		System.arraycopy(ss, 0, bs, length - ss.length, ss.length);
		return new String(bs);
	}
	
	public static byte[] hex2Byte(String hex) {                                            
		   String digital = "0123456789ABCDEF";                                                
		   char[] hex2char = hex.toCharArray();                                                
		   byte[] bytes = new byte[hex.length() / 2];                                          
		   int temp;                                                                           
		   for (int i = 0; i < bytes.length; i++) {                                            
		   // 其实和上面的函数是一样的 multiple 16 就是右移4�?这样就成了高4位了               
		   // 然后和低四位相加�?相当�?位操�?|"                                              
		   //相加后的数字 进行 �?"&" 操作 防止负数的自动扩�? {0xff byte�?��表示数}           
		       temp = digital.indexOf(hex2char[2 * i]) * 16;                                   
		       temp += digital.indexOf(hex2char[2 * i + 1]);                                   
		       bytes[i] = (byte) (temp & 0xff);                                                
		   }                                                                                   
		   return bytes;                                                                       
	}      
	
	public static byte[] string2ASCII(String s) {// 字符串转换为ASCII�?         
	       if (s == null || "".equals(s)) {                                     
	           return null;                                                     
	       }                                                                    
	                                                                            
	       char[] chars = s.toCharArray();                                      
	       byte[] asciiArray = new byte[chars.length];                            
	                                                                            
	       for (int i = 0; i < chars.length; i++) {                             
	           asciiArray[i] = char2ASCII(chars[i]);                            
	       }                                                                    
	       return asciiArray;                                                   
	   }  
	
	public static byte char2ASCII(char c) {  
		        return (byte) c;  
    }  

	/**                                                              
	    * 字符串转换成十六进制字符�?                                  
	    * @param str String 待转换的ASCII字符�?                       
	    * @return String 每个Byte之间空格分隔，如: [61 6C 6B]          
	    */                                                             
	   public static String str2HexStr(String str){                    
	       StringBuilder sb = new StringBuilder();      
	       char[] mChars = "0123456789ABCDEF".toCharArray();  
	       byte[] bs = str.getBytes();                                 
	                                                                   
	       for (int i = 0; i < bs.length; i++){                        
	           sb.append(mChars[(bs[i] & 0xFF) >> 4]);                 
	           sb.append(mChars[bs[i] & 0x0F]);                        
	       }                                                           
	       return sb.toString().trim();                                
	   }      



}
