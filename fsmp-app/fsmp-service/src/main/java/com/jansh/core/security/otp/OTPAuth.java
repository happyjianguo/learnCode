package com.jansh.core.security.otp;
//package cn.com.jansh.core.security.otp;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//
//import javax.annotation.PostConstruct;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.ResourceUtils;
//
//import cn.com.jansh.core.service.sys.PropertiesService;
//import ft.otp.agent.OTPAgent;
//
//public class OTPAuth {
//	
//	private static final Logger logger = LogManager.getLogger(OTPAuth.class);
//
//	@Autowired
//	private PropertiesService properties;
//	
//	private String config;
//	
//	private OTPAgent otpAgent = null;
//
//	@PostConstruct
//	public void init() throws Exception {
//		if(!"1".equals(properties.getOptenable())){
//			logger.info("关闭otp认证");
//			return;
//		}
//		if (StringUtils.isBlank(config)) {
//			logger.error("OTP config file is blank");
//			throw new Exception("OTP config file is blank");
//		}
//		this.otpAgent = new OTPAgent();
//		File cfgFile = null;
//		try {
//			cfgFile = ResourceUtils.getFile(config);
//		} catch (FileNotFoundException e) {
//			logger.error("OTP config file notfount,{}", config);
//			throw e;
//		}
//		otpAgent.setConfig(cfgFile.getPath());
//	}
//
//	/**
//	 * 根据用户名验证otppwd
//	 * 
//	 * @param username
//	 * @param otppwd
//	 * @return
//	 */
//	public int auth(String username, String otppwd) {
//		if("1".equals(properties.getOptenable())){
//			logger.info("验证otp");
//			return otpAgent.auth(username, otppwd);
//		}else{
//			logger.info("已关闭otp认证");
//			return 0;
//		}
//		
//	}
//
//	public void setConfig(String config) {
//		this.config = config;
//	}
//
//}
