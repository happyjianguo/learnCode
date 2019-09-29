package cn.yufu.SDMTPlatform.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpClientUtil {
	public static void main(String[] args) throws Exception {
		FtpClientUtil ftp = new FtpClientUtil("192.168.6.246", 21, "fk_dev", "fk_dev");
		ftp.open();
		// ftp.download("start.sh", "D:\\start.sh");
		System.out.println(ftp.getFileNameList(""));
		ftp.close();
	}

	FTPClient ftpClient;
	private String server;
	private int port;
	private String userName;
	private String userPassword;

	public FtpClientUtil(String server, int port, String userName, String userPassword) {
		this.server = server;
		this.port = port;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	/**
	 * 链接到服务器
	 * 
	 * @return
	 */
	public boolean open() {
		if (ftpClient != null && ftpClient.isConnected())
			return true;
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(server, port);
			ftpClient.login(userName, userPassword);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			ftpClient = null;
			return false;
		}
	}

	public boolean cd(String dir) {
		boolean f = false;
		try {
			ftpClient.cwd(dir);
		} catch (IOException e) {
			// Logs.error(e.toString());
			return f;
		}
		return true;
	}

	/**
	 * 上传文件到FTP服务器
	 * 
	 * @param localPathAndFileName
	 *            本地文件目录和文件名
	 * @param ftpFileName
	 *            上传后的文件名
	 * @param ftpDirectory
	 *            FTP目录如:/path1/pathb2/,如果目录不存在回自动创建目录
	 * @throws Exception
	 */
	/**
	 * 上传文件到FTP服务器
	 * 
	 * @param ftpDirectoryAndFileName
	 * @param localDirectoryAndFileName
	 * @return
	 * @throws Exception
	 */
	public boolean upload(String ftpDirectory, String FileName, String localDirectoryAndFileName) throws Exception {
		if (!open())
			return false;
		FileInputStream is = null;
		OutputStream os = null;
		try {
			// 文件夹不存在则创建
			if (!ftpClient.changeWorkingDirectory(ftpDirectory)) {
				ftpClient.makeDirectory(ftpDirectory);
			}
			os = ftpClient.storeFileStream(ftpDirectory + "/" + FileName);
			File file_in = new File(localDirectoryAndFileName);
			is = new FileInputStream(file_in);
			byte bytes[] = new byte[1024];
			int i;
			while ((i = is.read(bytes)) != -1)
				os.write(bytes, 0, i);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (is != null)
				is.close();
			if (os != null)
				os.close();
		}
	}

	/**
	 * 从FTP服务器上下载文件并返回下载文件长度
	 * 
	 * @param ftpDirectoryAndFileName
	 * @param localDirectoryAndFileName
	 * @return
	 * @throws Exception
	 */
	public long download(String ftpDirectoryAndFileName, String localDirectoryAndFileName) throws Exception {
		long result = 0;
		if (!open())
			return result;
		FileOutputStream os = null;
		InputStream is = null;
		try {
			is = ftpClient.retrieveFileStream(ftpDirectoryAndFileName);
			java.io.File outfile = new java.io.File(localDirectoryAndFileName);
			os = new FileOutputStream(outfile);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
				result = result + c;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (is != null)
				is.close();
			if (os != null)
				os.close();

		}
		return result;
	}

	/**
	 * 返回FTP目录下的文件列表
	 * 
	 * @param ftpDirectory
	 * @return
	 */
	public List<String> getFileNameList(String ftpDirectory) {
		List<String> list = new ArrayList<String>();
		if (!open())
			return list;
		try {
			FTPFile[] dis = ftpClient.listDirectories(ftpDirectory);
			for (int i = 0; i < dis.length; i++) {
				list.add(dis[i].getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// /**
	// * 删除FTP上的文件
	// *
	// * @param ftpDirAndFileName
	// */
	// public boolean deleteFile(String ftpDirAndFileName) {
	// if (!open())
	// return false;
	// ftpClient.sendServer("DELE " + ftpDirAndFileName + "\r\n");
	// return true;
	// }

	// /**
	// * 删除FTP目录
	// *
	// * @param ftpDirectory
	// */
	// public boolean deleteDirectory(String ftpDirectory) {
	// if (!open())
	// return false;
	// ftpClient.sendServer("XRMD " + ftpDirectory + "\r\n");
	// return true;
	// }

	/**
	 * 关闭链接
	 */
	public void close() {
		try {
			if (ftpClient != null && ftpClient.isConnected())
				ftpClient.logout();
		} catch (Exception e) {
			System.out.println("close error");
		}
	}

}
