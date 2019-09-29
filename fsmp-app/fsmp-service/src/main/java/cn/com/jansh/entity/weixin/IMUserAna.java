package cn.com.jansh.entity.weixin;

/**
 * 用户分析实体
 * @author gll
 * @version 1.0
 */
public class IMUserAna {
	
	private String name;
	private String value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "IMUserAna [name=" + name + ", value=" + value + "]";
	}
}
