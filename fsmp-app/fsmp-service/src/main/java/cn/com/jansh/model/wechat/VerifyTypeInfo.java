package cn.com.jansh.model.wechat;

/**
 * 验证类型实体
 * @author Mr.wong
 * @version 1.0
 */
public class VerifyTypeInfo {

	private int id ;
	
	public VerifyTypeInfo() {
		
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
