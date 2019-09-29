package cn.com.jansh.core.exception;

public enum SysErrorCode implements ErrorCode {
	
	/**
	 * 网页已过期
	 */
	E110006("110006"),
	/**
	 * 上传文件异常
	 */
	E110005("110005"),
	
	/**
	 * 新增数据异常
	 */
	E110001("110001"),
	/**
	 * 删除数据异常
	 */
	E110002("110002"),
	/**
	 * 修改数据异常
	 */
	E110003("110003"),
	/**
	 * 查询数据异常
	 */
	E110004("110004"),
	/**
	 * 该数据正在审核中
	 */
	E110007("110007"),
	/**
	 * 系统忙，请稍后再试
	 */
	E100001("100001");
	

	/* ======================================= */
	private final String value;

	private SysErrorCode(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

}
