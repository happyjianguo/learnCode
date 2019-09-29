package cn.com.jansh.model.wechat;
/**
 * 服务类型实体
 * @author Mr.wong
 * @version 1.0
 */
public class ServiceTypeInfo {

	private int id;

	public ServiceTypeInfo() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "[id=" + id + "]";
	}

	
}
