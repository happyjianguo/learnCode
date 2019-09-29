/**
 * FTPUtil.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:端木英男 2016年6月21日
 */
package cn.com.jansh.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * FTP上传文件、下载文件、删除文件
 * 
 * @author 端木英男
 * @version 1.0
 */
public class SftpUtil {
    
    private static final Logger logger = LogManager.getLogger(SftpUtil.class);
	/**
	 * 连接sftp服务器
	 * 
	 * @param host
	 *            主机
	 * @param port
	 *            端口
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	public static ChannelSftp connect(String host, int port, String username, String password) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			Session sshSession = jsch.getSession(username, host, port);
			logger.info("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			logger.info("Session connected.");
			logger.info("Opening Channel.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			logger.info("Connected to " + host + ".");
		} catch (Exception e) {
		    logger.error(e);
		}
		return sftp;
	}

	/**
	 * 上传文件
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * @param sftp
	 */
	public static void upload(String directory, String uploadFile, ChannelSftp sftp) {

		try {
			sftp.cd(directory);
			File file = new File(uploadFile);
			InputStream is = new FileInputStream(file);
			sftp.put(is, file.getName());
			is.close();
		} catch (Exception e) {
		    logger.error(e);
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveFile
	 *            存在本地的路径
	 * @param sftp
	 */
	public void download(String directory, String downloadFile, String saveFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file = new File(saveFile);
			FileOutputStream is = new FileOutputStream(file);
			sftp.get(downloadFile, is);
			is.close();
		} catch (Exception e) {
		    logger.error(e);
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 * @param sftp
	 */
	public void delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
		    logger.error(e);
		}
	}

	public static void main(String[] args) {
		ChannelSftp flag = connect("192.168.23.36", 22, "root", "**********");
		upload("/root", "D:/logs/wx_server_jeewx.debug.log", flag);
		System.out.println(flag);
	}
}
