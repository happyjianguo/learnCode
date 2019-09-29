
package cn.com.jansh.constant;

public enum AppCommonsCode {
	//**********************操作*******************
	/**
	 * 新增
	 */
	CREATE("create"),
	/**
	 * 修改
	 */
	UPDATE("update"),
	//********************黑名单接口******************
	/**
	 * 黑名单接口请求失败
	 */
	BLACKINTER_ERROR("000099"),
	/**
	 * 黑名单接口请求成功
	 */
	BLACKINTER_OK("000000"),
	/**
	 * 黑名单接口请求错误
	 */
	BLACKINTER_ERROR_REQUEST("999998"),
	/**
	 * 黑名单接口请求参数为空或者验签串为空的错误
	 */
	BLACKINTER_ERROR_PARSE("900001");
	
	
	private final String value;


    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
	AppCommonsCode(String value) {
        this.value = value;
    }
    
    public String value() {
        return value;
    }
}
