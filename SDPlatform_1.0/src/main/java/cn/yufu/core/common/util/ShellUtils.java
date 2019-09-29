package cn.yufu.core.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class ShellUtils {
	private static final Log log = LogFactory.getLog("edcTerminal");

	private String host;// sftp������ip
	private String username;// �û���
	private String password;// ����
	private int port = 21;// Ĭ�ϵ�sftp�˿ں���22
	private Session session;

	public ShellUtils(String host, String username, String password, int port) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.port = port;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return channel
	 */
	public boolean connect() {
		JSch jsch = new JSch();
		try {
			session = jsch.getSession(username, host, port);
			if (password != null && !"".equals(password)) {
				session.setPassword(password);
			}
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");// do not verify host
			// key
			session.setConfig(sshConfig);
			// session.setTimeout(timeout);
			session.setServerAliveInterval(92000);
			session.connect();
			return true;
		} catch (JSchException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ִ����ص�����
	 * 
	 * @throws JSchException
	 */
	public void execCmd(String command) throws JSchException {
		BufferedReader reader = null;
		Channel channel = null;
		try {
			if (command != null) {
				channel = session.openChannel("exec");
				((ChannelExec) channel).setCommand("source .bash_profile;" + command);
				channel.setInputStream(null);
				((ChannelExec) channel).setErrStream(System.err);
				channel.connect();
				InputStream in = channel.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				String buf = null;
				while ((buf = reader.readLine()) != null) {
					// System.out.println(buf);
					log.info(buf);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSchException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			channel.disconnect();
			session.disconnect();
		}
	}

	/**
	 * ִ����ص�����
	 * 
	 * @throws JSchException
	 */
	public String execCommand(String command) throws JSchException {
		BufferedReader reader = null;
		Channel channel = null;
		StringBuffer sb = new StringBuffer();
		try {
			if (command != null) {
				channel = session.openChannel("exec");
				((ChannelExec) channel).setCommand("source .bash_profile;" + command);
				channel.setInputStream(null);
				((ChannelExec) channel).setErrStream(System.err);
				channel.connect();
				InputStream in = channel.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				String buf = null;
				while ((buf = reader.readLine()) != null) {
					// System.out.println(buf);
					log.info(buf);
					sb.append(buf);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSchException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			channel.disconnect();
			session.disconnect();
		}
		return sb.toString();
	}
}
