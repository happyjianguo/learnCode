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

import cn.yufu.system.common.utils.StringUtils;

public class UploadFileSFTP {
	
	private static UploadFileSFTP instance = null;
	
	private ChannelSftp sftp;
	
	private UploadFileSFTP (){};
	
	public static UploadFileSFTP getInstance(){
		if (null == instance) {
			instance = new UploadFileSFTP();
		}
		return instance;
	}
	
	/**
	 * 通过SFTP上传文件至LINUX服务器
	 * @param format 指定上传文件的格式，不指定上传该目录下所有
	 * @param batch  是否批量上传， true -- 批量
	 * @return int 0 --> 上传PDF成功； 1 --> 上传PDF失败
	 * */
	public boolean uploadFile(Session session, String sourcePath, String targetPath, 
			String format, boolean batch) {
		boolean result = false;
		if (null == session || StringUtils.isEmpty(sourcePath) 
				|| StringUtils.isEmpty(targetPath) ) {
			System.out.println("参数[session，sourcePath，targetPath]: 均不可为空");
			return result;
		}
		Channel channel = null;
		try {
			channel = (Channel) session.openChannel("sftp");
			channel.connect(10000000);
			sftp = (ChannelSftp) channel;
			try {
				sftp.cd(targetPath);
			} catch (SftpException e) {
				sftp.mkdir(targetPath);
				sftp.cd(targetPath);
			}
			if (batch) {
				result = uploadDirectory(sourcePath, targetPath, format);
			} else {
				result = uploadFile(sourcePath, targetPath, format);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect(sftp, session);
		}
		return result;
	}
	
	/***
	 * 上传单个Ftp文件， 当前文件目录下的所有文件[非目录]，也视为单个文件，进行批量上传
	 * 
	 * @param localFile 
	 * @param targetPath 
	 * @param format 	指定上传文件的格式[文件后缀]，不指定上传该目录下所有
	 * 
	 */
	public boolean uploadFile(String sourcePath, String targetPath, String format) {
		boolean success = false;
		if (!createDir(targetPath)) {
			System.out.println("targetPath : " + targetPath + "  路径有误 ");
			return success;
		}
		File sourceFile = new File(sourcePath);
		File[] files = sourceFile.listFiles();
		if (null == files || files.length == 0) {
			System.out.println("目录： sourceFile，下无文件 ");
			return success;
		}
		//遍历两遍，避免不必要的上传
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.println("sourcePath : " + sourcePath + "  路径有误，不可含有子目录 ");
				return success;
			}
		}
		String fileName = "";
		for (File file : files) {
			fileName = file.getName();
			if (StringUtils.isNotEmpty(format)) {
				if (fileName.endsWith(format)) {
					success = uploadFiles(sourcePath + "/" + fileName, fileName);
				}
			} else {
				success = uploadFiles(sourcePath + "/" + fileName, fileName);
			}
		}
		return success;
	}
	
	/***
	 * @批量上传文件夹
	 * @param sourcePath  当地文件夹
	 * @param targetPath  Ftp 服务器路径 以目录"/"结束
	 */
	public boolean uploadDirectory(String sourcePath, String targetPath, String format) {
		File src = new File(sourcePath);
		//targetPath = targetPath + src.getName() + "/";
		if (!createDir(targetPath)) {
			System.out.println("sourcePath : " + sourcePath + "  路径有误");
		}
        
		File[] allFile = src.listFiles();
		for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
			if (!allFile[currentFile].isDirectory()) {
				String srcName = allFile[currentFile].getPath().toString();
				String fileName = allFile[currentFile].getName();
				if (StringUtils.isNotEmpty(format)) {
					if (fileName.endsWith(format)) {
						uploadFiles(srcName, fileName);
					}
				} else {
					uploadFiles(srcName, fileName);
				}
			}
		}
		for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
			if (allFile[currentFile].isDirectory()) {
				// 递归
				uploadDirectory(allFile[currentFile].getPath().toString(), targetPath + "/" + allFile[currentFile].getName(), format);
			}
		}
		return true;
	}
	
	/***
	 * 上传Ftp文件
	 * 
	 * @param localFile
	 *            当地文件
	 * @param romotUpLoadePath 上传服务器文件名
	 * 
	 */
	public boolean uploadFiles(String sourceName, String remoteFileName) {
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
			System.out.println(sourceName + "文件未找到.....");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SFTP: upload file failure ------------- \n");
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
			System.out.println(remoteDirectoryPath + " 目录创建成功");
			return true;
		} catch (SftpException e) {
			e.printStackTrace();
			System.out.println(remoteDirectoryPath + " 目录创建失败");
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
