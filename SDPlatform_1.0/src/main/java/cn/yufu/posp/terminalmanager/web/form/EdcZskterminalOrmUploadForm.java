/**
 *����:cn.yufu.posp.terminalmanager.web.form
 *����:package cn.yufu.posp.terminalmanager.web.form;
 */
package cn.yufu.posp.terminalmanager.web.form;
/**
 * EdcZskterminalOrmUploadForm.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��3��2��
 * ����:ר�����ն�upload
 */
import org.apache.struts.upload.FormFile;

import cn.yufu.core.web.form.BaseForm;

public class EdcZskterminalOrmUploadForm extends BaseForm {
	private FormFile upload;

	public FormFile getUpload() {
		return upload;
	}

	public void setUpload(FormFile upload) {
		this.upload = upload;
	}
}
