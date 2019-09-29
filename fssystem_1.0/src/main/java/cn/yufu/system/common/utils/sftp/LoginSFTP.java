package cn.yufu.system.common.utils.sftp;


import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import cn.yufu.system.common.utils.StringUtils;

public class LoginSFTP {
	
	public static void main(String[] args) {
		
		Session session = login("192.168.6.135", "rwusr", "rw@admin123", 22, "", "");
		UploadFileSFTP instance = UploadFileSFTP.getInstance();
		boolean result = instance.uploadFile(session, "D:/ttttttttttt/20180813", "/propertyvksd/20180813",
					"", false);
		System.out.println(result);
		
	}
	
	/**
	 * 登录成功
	 * @return Session
	 * */
	public static Session login(String ip, String user, String pwd, int port,
			String privateKeyPath, String passphrase) {
		Session session = null;
		
		if (StringUtils.isNotEmpty(ip) && StringUtils.isNotEmpty(user)
				&& port > 0) {
			
			if (privateKeyPath != null && !privateKeyPath.equals("")) {
				session = sshSftp2(ip, user, port, privateKeyPath, passphrase);
			} else if (pwd != null && !pwd.equals("")) {
				session = sshSftp(ip, user, pwd, port);
			} /*else {
				Console console = System.console();
				System.out.print("Enter password:");
				char[] readPassword = console.readPassword();
				sshSftp(ip, user, new String(readPassword), port, sourcePath, destinationPath);
			}*/
		} else {
			System.out.println("FTPAndSFTP: 上传文件参数错误  ---------- \n");
		}
		return session;
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
	 * @return Session
	 */
	public static Session sshSftp(String ip, String user, String pwd, int port) {
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
				System.out.println("SFTP: session is null ------------- \n");
				throw new Exception("session is null");
			}
			// 设置登陆主机的密码
			session.setPassword(pwd);// 设置密码
			// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
			session.setConfig("StrictHostKeyChecking", "no");
			// 设置登陆超时时间
			session.connect(300000);
			
			System.out.println("SFTP: login SFTP success ------------- \n");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SFTP: login SFTP failure ------------- \n");
		} /*finally {
			if (session != null) {
				if (session.isConnected()) {
					session.disconnect();
					System.out.println("Session is closed already ------------- \n");
				}
			}
		}*/
		return session;
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
	public static Session sshSftp2(String ip, String user, int port, 
			String privateKey, String passphrase) {
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
				System.out.println("SFTP: session is null ------------- \n");
				throw new Exception("session is null");
			}
			// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
			session.setConfig("StrictHostKeyChecking", "no");
			// 设置登陆超时时间
			session.connect(300000);
			System.out.println("SFTP: login SFTP success ------------- \n");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SFTP: login SFTP failure ------------- \n");
		} /*finally {
			if (session != null) {
				if (session.isConnected()) {
					session.disconnect();
					System.out.println("Session is closed already ------------- \n");
				}
			}
		}*/
		return session;
	}

}
