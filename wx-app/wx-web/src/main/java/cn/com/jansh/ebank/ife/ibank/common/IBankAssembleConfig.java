/**
 * IBankAssembleConfig.java
 * 版权所有(C) 2011 深圳市银之杰科技股份有限公司
 * 创建:YangFan 2011-3-8
 */
package cn.com.jansh.ebank.ife.ibank.common;

/**
 * 上传下传常量定义
 * @author YangFan
 * @version 1.0.0
 */
public class IBankAssembleConfig {

	/**
	 * 
	 * 报文头 九个组成部分
	 * 
	 * 报文体长度：是整个报文体的长度，不包括报文头
	 * 交易代码：用于标识一个具体的交易
	 * 报文类型：RQ表示请求报文，RS表示回应报文
	 * 文件传送标志：’0’：无文件，‘1’：请求报文有文件传送；’2’：应答报文有文件传送
	 * 加密卡ID
	 * 报文效验码：用于记录报文校验码
	 * 保留:预留8位
	 * 服务名称：前台上传时指定调用后台的服务程序名称
	 * 服务协议：‘1’:CICS;’3’:TCP/IP, ‘4’:TUXEDO、‘0’：本地
	 */
	public class MessageHead{
		//报文类型
		public static final String REQUEST = "RQ";
		public static final String RECEIVER = "RS";
		//文件传送标志
		public static final String NOFILE = "0";
		public static final String REQUESTFILE = "1";
		public static final String RECEIVERFILE= "2";
		//加密卡ID ,报文效验码 ,保留
		public static final String COMMONSTRING = "VOST0001################        ";
		//服务名称 = tp + 交易码
		public static final String PARTSTRING = "tp";
		//服务协议
		public static final String SERVERCICS = "1";
		public static final String SERVERTCP = "3";
		public static final String SERVERLOCAL = "0";
	}
	
}
