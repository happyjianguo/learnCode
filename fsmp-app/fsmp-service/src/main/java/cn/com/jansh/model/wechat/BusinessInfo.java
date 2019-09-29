package cn.com.jansh.model.wechat;

/**
 * 授权公众号开通功能实体
 * @author Mr.wong
 * @version 1.0
 */
public class BusinessInfo {

	private int open_store;
	private int open_scan;
	private int open_pay;
	private int open_card;
	private int open_shake;
	
	public BusinessInfo() {
		
	}

	public int getOpen_store() {
		return open_store;
	}

	public void setOpen_store(int open_store) {
		this.open_store = open_store;
	}

	public int getOpen_scan() {
		return open_scan;
	}

	public void setOpen_scan(int open_scan) {
		this.open_scan = open_scan;
	}

	public int getOpen_pay() {
		return open_pay;
	}

	public void setOpen_pay(int open_pay) {
		this.open_pay = open_pay;
	}

	public int getOpen_card() {
		return open_card;
	}

	public void setOpen_card(int open_card) {
		this.open_card = open_card;
	}

	public int getOpen_shake() {
		return open_shake;
	}

	public void setOpen_shake(int open_shake) {
		this.open_shake = open_shake;
	}

	@Override
	public String toString() {
		return "[open_store=" + open_store + ", open_scan=" + open_scan + ", open_pay=" + open_pay
				+ ", open_card=" + open_card + ", open_shake=" + open_shake + "]";
	}
	
	
}
