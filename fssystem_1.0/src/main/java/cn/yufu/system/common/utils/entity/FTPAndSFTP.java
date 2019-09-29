package cn.yufu.system.common.utils.entity;

import java.io.Serializable;

import cn.yufu.system.modules.sys.utils.DictUtils;

public class FTPAndSFTP implements Serializable{
	private static final long serialVersionUID = 990275590990498664L;
	
	private String uploadIp;		//上传FTP服务器IP
	private int uploadPort;			//上传FTP服务器Port
	private String uploadUsername;	//上传FTP服务器用户名
	private String uploadPassword;	//上传FTP服务器密码
	private String sourcePath;		//上传FTP服务器来源目录
	private String targetPath;		//上传FTP服务器目标目录
	private String privateKeyPath;	//上传FTP服务器登录方式
	private String passphrase;
	
	private final static FTPAndSFTP instance = new FTPAndSFTP();
	
	private FTPAndSFTP() {
		init();
	}
	
	public static FTPAndSFTP getInstance(){
		return instance;
	}
	
	private void init() {
		targetPath = DictUtils.getDictValue("上传FTP服务器目录路径", "FTP_UPLOAD_PATH", "/home/testftp/fssystem_pdf/");
		uploadIp = DictUtils.getDictValue("上传FTP服务器IP", "FTP_UPLOAD_IP", "192.168.10.75");
		String uploadPortStr = DictUtils.getDictValue("上传FTP服务器PORT", "FTP_UPLOAD_PORT", "22");
		uploadPort = Integer.parseInt(uploadPortStr);
		uploadUsername = DictUtils.getDictValue("上传FTP服务器用户名", "FTP_UPLOAD_USERNAME", "testftp");
		uploadPassword = DictUtils.getDictValue("上传FTP服务器密码", "FTP_UPLOAD_PASSWORD", "testftp");
		//测试数据
		/*targetPath = "/home/testftp/fssystem_pdf/";
		uploadIp = "192.168.10.75";
		uploadPort = 22;
		uploadUsername = "testftp";
		uploadPassword = "testftp";*/
	}

	public String getUploadIp() {
		return uploadIp;
	}
	public void setUploadIp(String uploadIp) {
		this.uploadIp = uploadIp;
	}
	public int getUploadPort() {
		return uploadPort;
	}
	public void setUploadPort(int uploadPort) {
		this.uploadPort = uploadPort;
	}
	public String getUploadUsername() {
		return uploadUsername;
	}
	public void setUploadUsername(String uploadUsername) {
		this.uploadUsername = uploadUsername;
	}
	public String getUploadPassword() {
		return uploadPassword;
	}
	public void setUploadPassword(String uploadPassword) {
		this.uploadPassword = uploadPassword;
	}
	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	public String getTargetPath() {
		return targetPath;
	}
	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}
	public String getPrivateKeyPath() {
		return privateKeyPath;
	}
	public void setPrivateKeyPath(String privateKeyPath) {
		this.privateKeyPath = privateKeyPath;
	}
	public String getPassphrase() {
		return passphrase;
	}
	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

	@Override
	public String toString() {
		return "FTPAndSFTP [uploadIp=" + uploadIp + ", uploadPort=" + uploadPort
				+ ", uploadUsername=" + uploadUsername + ", uploadPassword=" + uploadPassword + ", sourcePath="
				+ sourcePath + ", targetPath=" + targetPath + ", privateKeyPath=" + privateKeyPath + ", passphrase="
				+ passphrase + "]";
	}

}
