package cn.com.jansh.service.component;

import java.nio.charset.Charset;

import cn.com.jansh.core.util.HttpClientUtil;

public class QCellCoreComponent {

	private String qcellcoreurl;

	/**
	 * 查询手机号归属地
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public String qCellCore(String phone) throws Exception {
		return HttpClientUtil.httpGet(qcellcoreurl + phone, Charset.forName("GBK"));
	}

	public String getQcellcoreurl() {
		return qcellcoreurl;
	}

	public void setQcellcoreurl(String qcellcoreurl) {
		this.qcellcoreurl = qcellcoreurl;
	}
}
