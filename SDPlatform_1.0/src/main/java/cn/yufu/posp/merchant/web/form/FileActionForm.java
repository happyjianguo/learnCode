package cn.yufu.posp.merchant.web.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class FileActionForm extends ActionForm {
	private String fileName;// 上传文件的名称
	private String fileSize;// 上传文件的大小
	private String filePath;// 上传文件到服务器的路径
	private String fileDate;// 上传文件的日期
	private FormFile file;// 上传文件

	public String getFileName() {
		return fileName;
	}

	public FormFile getFile() {
		return file;
	}

	public String getFileSize() {
		return fileSize;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

}
