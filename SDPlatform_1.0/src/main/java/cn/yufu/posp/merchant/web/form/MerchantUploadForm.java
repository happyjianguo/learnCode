package cn.yufu.posp.merchant.web.form;

import org.apache.struts.upload.FormFile;

import cn.yufu.core.web.form.BaseForm;

public class MerchantUploadForm extends BaseForm {
	private FormFile upload;

	public FormFile getUpload() {
		return upload;
	}

	public void setUpload(FormFile upload) {
		this.upload = upload;
	}
}
