package cn.yufu.posp.cardtype.web.form;

import org.apache.struts.upload.FormFile;

import cn.yufu.core.web.form.BaseForm;

public class CardTypeUploadForm extends BaseForm {
	private FormFile upload ;

	public FormFile getUpload() {
		return upload;
	}

	public void setUpload(FormFile upload) {
		this.upload = upload;
	}
}
