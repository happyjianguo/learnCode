package cn.yufu.system.common.utils.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

/**
 * FTP工具类
 * 
 * */
public class FTPUtils {
	private static FTPUtils instance = null;
	private static FTPClient ftpClient = null;
	private String server = "192.168.10.75";
	private int port = 21;
	private String userName = "ftpweb";
	private String userPassword = "ftpweb";

	public static FTPUtils getInstance() {
		if (instance == null) {
			instance = new FTPUtils();
		}

		ftpClient = new FTPClient();
		return instance;
	}

	/**
	 * 连接FTP服务器
	 * 
	 * @return
	 */
	private boolean connect() {
		boolean stat = false;
		try {
			if (ftpClient.isConnected())
				return true;
			ftpClient.connect(server, port);
			stat = true;
		} catch (SocketException e) {
			stat = false;
			e.printStackTrace();
		} catch (IOException e) {
			stat = false;
			e.printStackTrace();
		}
		return stat;
	}

	/**
	 * 打开FTP服务器
	 * 
	 * @return
	 */
	public boolean open() {
		if (!connect()) {
			return false;
		}

		boolean stat = false;
		try {
			stat = ftpClient.login(userName, userPassword);
			// 检测连接是否成功
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				close();
				stat = false;
			}
		} catch (IOException e) {
			stat = false;
		}
		return stat;
	}

	/**
	 * 关闭FTP服务器
	 */
	public void close() {
		try {
			if (ftpClient != null) {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}

				ftpClient = null;
			}
		} catch (IOException e) {
		}
	}

	/**
	 * 上传文件到FTP服务器
	 * 
	 * @param filename
	 * @param path
	 * @param input
	 * @return
	 */
	public boolean upload(String filename, String path, InputStream input) {
		boolean stat = false;
		try {
			cd(path);
			ftpClient.setBufferSize(1024);
			ftpClient.setControlEncoding("GBK");
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			stat = ftpClient.storeFile(filename, input);
			input.close(); // 关闭输入流
		} catch (IOException e) {

		}
		return stat;
	}

	/**
	 * 上传文件到FTP服务器
	 * 
	 * @param filename
	 * @param path
	 * @param input
	 * @return
	 */
	public boolean upload(String filename, String path, String filepath) {
		boolean stat = false;
		try {
			cd(path);
			ftpClient.setBufferSize(1024);
			ftpClient.setControlEncoding("GBK");
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			FileInputStream input = new FileInputStream(new File(filepath));
			stat = ftpClient.storeFile(filename, input);
			input.close(); // 关闭输入流
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return stat;
	}

	/**
	 * 上传文件
	 * 
	 * @param filename
	 * @param path
	 * @param file
	 * @return
	 */
	public boolean upload(String filename, String path, File file) {
		boolean stat = false;
		try {
			cd(path);
			ftpClient.setBufferSize(1024);
			ftpClient.setControlEncoding("GBK");
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			FileInputStream input = new FileInputStream(file);
			stat = ftpClient.storeFile(filename, input);
			input.close(); // 关闭输入流
		} catch (IOException e) {

		}
		return stat;
	}

	/**
	 * 循环切换目录
	 * 
	 * @param dir
	 * @return
	 */
	public boolean cd(String dir) {
		boolean stat = true;
		try {
			String[] dirs = dir.split("/");
			if (dirs.length == 0) {
				return ftpClient.changeWorkingDirectory(dir);
			}

			stat = ftpClient.changeToParentDirectory();
			for (String dirss : dirs) {
				stat = stat && ftpClient.changeWorkingDirectory(dirss);
			}

			stat = true;
		} catch (IOException e) {
			stat = false;
		}
		return stat;
	}

	/***
	 * 创建目录
	 * 
	 * @param dir
	 * @return
	 */
	public boolean mkdir(String dir) {
		boolean stat = false;
		try {
			ftpClient.changeToParentDirectory();
			ftpClient.makeDirectory(dir);
			stat = true;
		} catch (IOException e) {
			stat = false;
		}
		return stat;
	}

	/***
	 * 创建多个层级目录
	 * 
	 * @param dir
	 *            dong/zzz/ddd/ewv
	 * @return
	 */
	public boolean mkdirs(String dir) {
		String[] dirs = dir.split("/");
		if (dirs.length == 0) {
			return false;
		}
		boolean stat = false;
		try {
			ftpClient.changeToParentDirectory();
			for (String dirss : dirs) {
				ftpClient.makeDirectory(dirss);
				ftpClient.changeWorkingDirectory(dirss);
			}

			ftpClient.changeToParentDirectory();
			stat = true;
		} catch (IOException e) {
			stat = false;
		}
		return stat;
	}

	/**
	 * 删除文件夹
	 * 
	 * @param pathname
	 * @return
	 */
	public boolean rmdir(String pathname) {
		try {
			return ftpClient.removeDirectory(pathname);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param pathname
	 * @return
	 */
	public boolean remove(String pathname) {
		boolean stat = false;
		try {
			stat = ftpClient.deleteFile(pathname);
		} catch (Exception e) {
			stat = false;
		}
		return stat;
	}

	/**
	 * 移动文件或文件夹
	 * 
	 * @param pathname1
	 * @param pathname2
	 * @return
	 */
	public boolean rename(String pathname1, String pathname2) {
		try {
			return ftpClient.rename(pathname1, pathname2);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		// FTPUtils ftp=new FTPUtils();
		// ftp.getInstance();
		// if(ftp.connect()){
		// ftp.open();
		// ftp.upload("1.txt", "/home/ftpweb/fssystem_pdf", "D:/1.txt");
		// ftp.close();
		// }
		try {
			FtpClient ftp = connectFTP("192.168.10.75", 21, "ftpweb", "ftpweb");
			//ftp = ftp.changeToParentDirectory();
			//ftp.makeDirectory("66710001440000120170101");
			 upload("d://667100014400001#20170101#llghaier@163.com#01.pdf",
			 "/home/ftpweb/fssystem_pdf/667100014400001#20170101#llghaier@163.com#01.pdf",
			 ftp);
			 FTPUtils.download("e://1.txt", "/home/ftpweb/fssystem_pdf/1.txt",
			 ftp);
			ftp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}/* catch (FtpProtocolException e) {
			e.printStackTrace();
		}*/
	}

	/**
	 * 获取FTP
	 * 
	 * @param url
	 * @param port
	 * @param username
	 * @param password
	 * @return
	 */
	public static FtpClient connectFTP(String url, int port, String username,
			String password) {
		// 创建ftp
		FtpClient ftp = null;
		try {
			// 创建地址
			SocketAddress addr = new InetSocketAddress(url, port);
			// 连接
			ftp = FtpClient.create();
			ftp.connect(addr);
			// 登陆
			ftp.login(username, password.toCharArray());
			ftp.setBinaryType();
		} catch (FtpProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ftp;
	}

	/**
	 * 上传本地文件到ftp服务器
	 * 
	 * @param localFile
	 *            本地文件目录,如：d://1.txt
	 * @param ftpFile
	 *            ftp服务器文件目录,如：/home/ftpweb/fssystem_pdf/1.txt
	 * @param ftp
	 */
	public static void upload(String localFile, String ftpFile, FtpClient ftp) {
		OutputStream os = null;
		FileInputStream fis = null;
		try {
			// 将ftp文件加入输出流中。输出到ftp上
			os = ftp.putFileStream(ftpFile);
			File file = new File(localFile);

			// 创建一个缓冲区
			fis = new FileInputStream(file);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = fis.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
			System.out.println("upload success!!");
		} catch (FtpProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 从ftp服务器下载文件到本地
	 * 
	 * @param localFile
	 * @param ftpFile
	 * @param ftp
	 */
	public static void download(String localFile, String ftpFile, FtpClient ftp) {
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			// 获取ftp上的文件
			is = ftp.getFileStream(ftpFile);
			File file = new File(localFile);
			byte[] bytes = new byte[1024];
			int i;
			fos = new FileOutputStream(file);
			while ((i = is.read(bytes)) != -1) {
				fos.write(bytes, 0, i);
			}
			System.out.println("download success!!");

		} catch (FtpProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}