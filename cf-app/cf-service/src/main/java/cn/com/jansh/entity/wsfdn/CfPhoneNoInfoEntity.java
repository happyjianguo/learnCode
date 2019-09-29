package cn.com.jansh.entity.wsfdn;

/**
 * 手机归属地Entity
 * 
 * @author gll
 *
 */
public class CfPhoneNoInfoEntity {

	/** 手机号 */
	private String phone;
	/** 运营商 */
	private String supplier;
	/** 省份 */
	private String province;
	/** 城市 */
	private String city;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
