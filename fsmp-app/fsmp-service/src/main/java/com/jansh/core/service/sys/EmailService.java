package com.jansh.core.service.sys;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发送邮件服务
 * 
 * @author nie
 *
 */
@Service
public class EmailService {

	private String mailHostname;

	private String mailUsername;

	private String mailPassword;

	private String mailSslonconnect;

	@Autowired
	public EmailService(PropertiesService properties) {
		this.mailHostname = properties.getMailHostname();
		this.mailUsername = properties.getMailUsername();
		this.mailPassword = properties.getMailPassword();
		this.mailSslonconnect = properties.getMailPassword();
	}

	public EmailService() {
	}

	/**
	 * text email
	 * 
	 * @param to
	 * @param subject
	 * @param msg
	 * @throws EmailException
	 * 
	 */
	public void sendmail(String to, String subject, String msg) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName(mailHostname);
		email.setCharset("utf-8");
		email.setAuthenticator(new DefaultAuthenticator(mailUsername, mailPassword));
		if ("true".equals(mailSslonconnect)) {
			email.setSSLOnConnect(true);
		}
		// email.setSmtpPort(22222);
		email.setFrom(mailUsername);
		email.setSubject(subject);
		email.setMsg(msg);
		email.addTo(to);
		email.send();
	}

	public void setMailHostname(String mailHostname) {
		this.mailHostname = mailHostname;
	}

	public void setMailUsername(String mailUsername) {
		this.mailUsername = mailUsername;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public void setMailSslonconnect(String mailSslonconnect) {
		this.mailSslonconnect = mailSslonconnect;
	}
}
