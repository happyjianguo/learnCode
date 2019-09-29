package com.pay.merInfoStatistics.struts.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;


public class MerchantInfoUploadForm extends ValidatorForm {
	private FormFile upload;

	public FormFile getUpload() {
		return upload;
	}

	public void setUpload(FormFile upload) {
		this.upload = upload;
	}
}
