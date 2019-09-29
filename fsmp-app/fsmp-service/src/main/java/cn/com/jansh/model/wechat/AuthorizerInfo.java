package cn.com.jansh.model.wechat;
/**
 * 授权公众号基本信息实体
 * @author Mr.wong
 * @version 1.0
 */
public class AuthorizerInfo {

	private String nick_name;
	private String head_img;
	private ServiceTypeInfo service_type_info;
	private VerifyTypeInfo verify_type_info;
	private String user_name;
	private BusinessInfo business_info;
	private String alias;
	private String qrcode_url;
	private String idc;
	
	public AuthorizerInfo() {
		
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getHead_img() {
		return head_img;
	}

	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}

	public ServiceTypeInfo getService_type_info() {
		return service_type_info;
	}

	public void setService_type_info(ServiceTypeInfo service_type_info) {
		this.service_type_info = service_type_info;
	}

	public VerifyTypeInfo getVerify_type_info() {
		return verify_type_info;
	}

	public void setVerify_type_info(VerifyTypeInfo verify_type_info) {
		this.verify_type_info = verify_type_info;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public BusinessInfo getBusiness_info() {
		return business_info;
	}

	public void setBusiness_info(BusinessInfo business_info) {
		this.business_info = business_info;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	

	public String getQrcode_url() {
		return qrcode_url;
	}

	public void setQrcode_url(String qrcode_url) {
		this.qrcode_url = qrcode_url;
	}

	public String getIdc() {
		return idc;
	}

	public void setIdc(String idc) {
		this.idc = idc;
	}

	@Override
	public String toString() {
		return "[nick_name=" + nick_name + ", head_img=" + head_img + ", service_type_info="
				+ service_type_info + ", verify_type_info=" + verify_type_info + ", user_name=" + user_name
				+ ", business_info=" + business_info + ", alias=" + alias + ", qrcode_url=" + qrcode_url + ", idc="
				+ idc + "]";
	}

	

	
	
}
