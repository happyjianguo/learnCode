/**
 *包名:cn.yufu.posp.terminalmanager.web.form
 *描述:package cn.yufu.posp.terminalmanager.web.form;
 */
package cn.yufu.posp.terminalmanager.web.form;
/**
 * EdcZskterminalOrmUploadForm.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年3月2日
 * 描述:专属卡终端upload
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
