package cn.yufu.posp.merchant.web.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class FileActionForm extends ActionForm {
	private String fileName;// �ϴ��ļ�������
	private String fileSize;// �ϴ��ļ��Ĵ�С
	private String filePath;// �ϴ��ļ�����������·��
	private String fileDate;// �ϴ��ļ�������
	private FormFile file;// �ϴ��ļ�

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
