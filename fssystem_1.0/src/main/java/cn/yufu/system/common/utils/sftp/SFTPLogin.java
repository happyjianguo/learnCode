package cn.yufu.system.common.utils.sftp;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.utils.entity.FTPAndSFTP;

public class SFTPLogin {
	
	public static void main(String[] args) {
		FTPAndSFTP instance = FTPAndSFTP.getInstance();
		instance.setSourcePath("D:/asds");
		login(instance);
	}
	
	/**
	 * 登录成功后直接上传文件
	 * @param FTPAndSFTP 连接参数
	 * @return boolean true --> 上传成功； false --> 上传失败
	 * */
	public static boolean login(FTPAndSFTP ftpAndSFTP) {
		StringBuilder sb = new StringBuilder();
		System.out.println("上传文件参数：FTPAndSFTP ---> " + ftpAndSFTP.toString());
		String ip = ftpAndSFTP.getUploadIp();
		String user = ftpAndSFTP.getUploadUsername();
		String pwd = ftpAndSFTP.getUploadPassword();
		int port = ftpAndSFTP.getUploadPort();
		String sourcePath = ftpAndSFTP.getSourcePath();
		String targetPath = ftpAndSFTP.getTargetPath();
		String privateKeyPath = ftpAndSFTP.getPrivateKeyPath();
		String passphrase = ftpAndSFTP.getPassphrase();
		boolean result = false;
		
		if (StringUtils.isNotEmpty(ip) && StringUtils.isNotEmpty(user)
				&& port > 0 && StringUtils.isNotEmpty(sourcePath) 
				&& StringUtils.isNotEmpty(targetPath)) {
			if (privateKeyPath != null && !privateKeyPath.equals("")) {
				result = sshSftp2(ip, user, port, privateKeyPath, passphrase, 
						sourcePath, targetPath, sb);
			} else if (pwd != null && !pwd.equals("")) {
				result = sshSftp(ip, user, pwd, port, sourcePath, targetPath, sb);
			} /*else {
				Console console = System.console();
				System.out.print("Enter password:");
				char[] readPassword = console.readPassword();
				sshSftp(ip, user, new String(readPassword), port, sourcePath, destinationPath);
			}*/
		} else {
			sb.append("FTPAndSFTP: 上传文件参数错误  ---------- \n");
		}
		System.out.println(sb.toString());
		return result;
	}

	/**
	 * 密码方式登录
	 * 
	 * @param ip
	 * @param user
	 * @param psw
	 * @param port
	 * @param sPath
	 * @param dPath
	 * @return boolean true --> 上传成功； false --> 上传失败
	 */
	public static boolean sshSftp(String ip, String user, String psw, 
			int port, String sPath, String dPath, StringBuilder sb) {
		System.out.println("SFTP: password login ------------- \n");
		Session session = null;
		JSch jsch = new JSch();
		try {
			if (port <= 0) {
				// 连接服务器，采用默认端口
				session = jsch.getSession(user, ip);
			} else {
				// 采用指定的端口连接服务器
				session = jsch.getSession(user, ip, port);
			}
			// 如果服务器连接不上，则抛出异常
			if (session == null) {
				sb.append("SFTP: session is null ------------- \n");
				throw new Exception("");
			}
			// 设置登陆主机的密码
			session.setPassword(psw);// 设置密码
			// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
			session.setConfig("StrictHostKeyChecking", "no");
			// 设置登陆超时时间
			session.connect(300000);
			
			SFTPUploadFile sftpUploadFile = new SFTPUploadFile();
			sftpUploadFile.upLoadFile(session, sPath, dPath);
			sb.append("SFTP: upload file success ------------- \n");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			sb.append("SFTP: upload file failure ------------- \n");
		} finally {
			if (session != null) {
				if (session.isConnected()) {
					session.disconnect();
					sb.append("Session is closed already ------------- \n");
				}
			}
		}
		return false;
	}

	/**
	 * 密匙方式登录
	 * 
	 * @param ip
	 * @param user
	 * @param port
	 * @param privateKey
	 * @param passphrase
	 * @param sPath
	 * @param dPath
	 * @return boolean true --> 上传成功； false --> 上传失败
	 */
	public static boolean sshSftp2(String ip, String user, int port, 
			String privateKey, String passphrase, String sPath,
			String dPath, StringBuilder sb) {
		System.out.println("SFTP: privateKey login ------------- \n");
		Session session = null;
		JSch jsch = new JSch();
		try {
			// 设置密钥和密码
			// 支持密钥的方式登陆，只需在jsch.getSession之前设置一下密钥的相关信息就可以了
			if (privateKey != null && !"".equals(privateKey)) {
				if (passphrase != null && "".equals(passphrase)) {
					// 设置带口令的密钥
					jsch.addIdentity(privateKey, passphrase);
				} else {
					// 设置不带口令的密钥
					jsch.addIdentity(privateKey);
				}
			}
			if (port <= 0) {
				// 连接服务器，采用默认端口
				session = jsch.getSession(user, ip);
			} else {
				// 采用指定的端口连接服务器
				session = jsch.getSession(user, ip, port);
			}
			// 如果服务器连接不上，则抛出异常
			if (session == null) {
				sb.append("SFTP: session is null ------------- \n");
			}
			// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
			session.setConfig("StrictHostKeyChecking", "no");
			// 设置登陆超时时间
			session.connect(300000);
			sb.append("SFTP: success ------------- \n");
			
			SFTPUploadFile sftpUploadFile = new SFTPUploadFile();
			sftpUploadFile.upLoadFile(session, sPath, dPath);
			
			sb.append("SFTP: upload file success ------------- \n");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			sb.append("SFTP: upload file failure ------------- \n");
		} finally {
			if (session != null) {
				if (session.isConnected()) {
					session.disconnect();
					sb.append("Session is closed already ------------- \n");
				}
			}
		}
		return false;
	}

}
