package cn.yufu.system.common.utils.sftp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class SFTPUploadFile {
	
	private ChannelSftp sftp;
	
	/**
	 * 通过SFTP上传PDF至LINUX服务器
	 * @return int 0 --> 上传PDF成功； 1 --> 上传PDF失败
	 * */
	public int upLoadFile(Session session, String sourcePath, String targetPath) {
		Channel channel = null;
		int result = 0;
		try {
			channel = (Channel) session.openChannel("sftp");
			channel.connect(10000000);
			sftp = (ChannelSftp) channel;
			try {
				sftp.cd(targetPath);
				/*Scanner scanner = new Scanner(System.in);
				System.out.println(targetPath + ":此目录已存在,文件可能会被覆盖!是否继续y/n?");
				String next = scanner.next();
				if (!next.toLowerCase().equals("y")) {
					return 1;
				}*/
			} catch (SftpException e) {
				sftp.mkdir(targetPath);
				sftp.cd(targetPath);
			}
			//File file = new File(sourcePath);
			uploadDirectory(sourcePath, targetPath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect(sftp, session);
		}
		return result;
	}
	
	/***
	 * @批量上传文件夹
	 * @param localDirectory
	 *            当地文件夹
	 * @param remoteDirectoryPath
	 *            Ftp 服务器路径 以目录"/"结束
	 */
	public boolean uploadDirectory(String localDirectory, String remoteDirectoryPath) {
		File src = new File(localDirectory);
		remoteDirectoryPath = remoteDirectoryPath + src.getName() + "/";
		if (!createDir(remoteDirectoryPath)) {
			System.out.println("localDirectory : " + localDirectory + "  路径有误 ");
		}
        
		File[] allFile = src.listFiles();
		for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
			if (!allFile[currentFile].isDirectory()) {
				String srcName = allFile[currentFile].getPath().toString();
				uploadFile(srcName, allFile[currentFile].getName());
			}
		}
		for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
			if (allFile[currentFile].isDirectory()) {
				// 递归
				uploadDirectory(allFile[currentFile].getPath().toString(), remoteDirectoryPath);
			}
		}
		return true;
	}
	
	/***
	 * 上传Ftp文件
	 * 
	 * @param localFile
	 *            当地文件
	 * @param romotUpLoadePath上传服务器文件名
	 * 
	 */
	public boolean uploadFile(String sourceName, String remoteFileName) {
		BufferedInputStream inStream = null;
		File localFile = null;
		boolean success = false;
		try {
			localFile = new File(sourceName);
			inStream = new BufferedInputStream(new FileInputStream(localFile));
			
			System.out.println(localFile.getName() + "开始上传.....");
			//此方法无法解决中文乱码问题(或许是个人工作空间的问题)
			//sftp.setFilenameEncoding("UTF-8");
			sftp.put(inStream, remoteFileName);
			success = true;
			System.out.println(localFile.getName() + "上传成功.....");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(sourceName + "文件未找到");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}
	
	/**
	 * 创建目录
	 * 
	 * @param createpath
	 * @return
	 */
	public boolean createDir(String remoteDirectoryPath) {
		try {
			if (isDirExist(remoteDirectoryPath)) {
				sftp.cd(remoteDirectoryPath);
				return true;
			}
			String pathArry[] = remoteDirectoryPath.split("/");
			StringBuffer filePath = new StringBuffer("/");
			for (String path : pathArry) {
				if (path.equals("")) {
					continue;
				}
				filePath.append(path + "/");
				if (isDirExist(filePath.toString())) {
					sftp.cd(filePath.toString());
				} else {
					// 建立目录
					sftp.mkdir(filePath.toString());
					// 进入并设置为当前目录
					sftp.cd(filePath.toString());
				}
			}
			sftp.cd(remoteDirectoryPath);
			System.out.println(remoteDirectoryPath + "目录创建成功");
			return true;
		} catch (SftpException e) {
			e.printStackTrace();
			System.out.println(remoteDirectoryPath + "目录创建失败");
		}
		return false;
	}

	/**
	 * 判断目录是否存在
	 * 
	 * @param directory
	 * @return
	 */
	public boolean isDirExist(String remoteDirectoryPath) {
		boolean isDirExistFlag = false;
		try {
			SftpATTRS sftpATTRS = sftp.lstat(remoteDirectoryPath);
			isDirExistFlag = true;
			return sftpATTRS.isDir();
		} catch (Exception e) {
			if (e.getMessage().toLowerCase().equals("no such file")) {
				isDirExistFlag = false;
			}
		}
		return isDirExistFlag;
	}
	
	/**
	 * 关闭连接
	 */
	public void disconnect(ChannelSftp sftp, Session session) {
		if (sftp != null) {
			if (sftp.isConnected()) {
				sftp.disconnect();
				System.out.println("SFTP is closed already ------------- ");
			}
		}
		if (session != null) {
			if (session.isConnected()) {
				session.disconnect();
				System.out.println("Session is closed already ------------- ");
			}
		}
	}
}
