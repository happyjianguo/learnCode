package cn.yufu.system.common.shiro;

import java.io.Serializable;

import cn.yufu.system.modules.sys.entity.User;

public class PrincipalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id; // 编号
	private String loginName; // 登录名
	private String name; // 姓名
	private boolean mobileLogin; // 是否手机登录


	public PrincipalBean(User user, boolean mobileLogin) {
		this.id = user.getId();
		this.loginName = user.getLoginName();
		this.name = user.getName();
		this.mobileLogin = mobileLogin;
	}

	public String getId() {
		return id;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getName() {
		return name;
	}

	public boolean isMobileLogin() {
		return mobileLogin;
	}

	@Override
	public String toString() {
		return id;
	}

}
