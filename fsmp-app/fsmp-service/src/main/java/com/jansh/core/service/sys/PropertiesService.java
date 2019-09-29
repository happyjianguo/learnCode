/**
 * PropertiesService.java
 * 2016年1月7日 下午3:57:07
 */
package com.jansh.core.service.sys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * properties组件
 * 
 * @author nie
 *
 */
@Service
public class PropertiesService {

	@Value("${mailHostname}")
	private String mailHostname;

	@Value("${mailUsername}")
	private String mailUsername;

	@Value("${mailPassword}")
	private String mailPassword;

	@Value("${mailSslonconnect}")
	private String mailSslonconnect;
	
	@Value("${login.otp.enable}")
	private String optenable;

	public String getMailHostname() {
		return mailHostname;
	}

	public String getMailUsername() {
		return mailUsername;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public String getMailSslonconnect() {
		return mailSslonconnect;
	}

	public String getOptenable() {
		return optenable;
	}

}
