/**
 * HostMode3111.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:YangFan 2015-7-17
 */
package cn.com.jansh.ebank.ife.ibank;

/**
 * 信用卡消费查询接口
 * @author YangFan
 * @version 1.0.0
 */
public class HostMode3111 {

	protected String tagId[];
	protected String tagFormat[];
	protected String tagName[];
	
	public HostMode3111() {
		setTagId();
		setTagFormat();
		setTagName();
	}

	/**
	 * 报文接口字段名
	 */
	protected void setTagId() {
		this.tagId = new String[] {"TradeCode", "CltSeqNo", "SRFlg", "AccTyp", "AccNo", "BeginDate", "EndDate"};
	}
	/**
	 * 报文接口对应长度及类型。长度：不确定用"0"；类型：d-日期，n-数字，x-字符串。
	 */
	protected void setTagFormat() {
		this.tagFormat = new String[] {"4x", "14x", "1x", "4x", "32x", "8x", "8x"};
	}
	/**
	 * 交易请求数据
	 */
	protected void setTagName() {
		this.tagName = new String[] {"TRADECODE", "CLTSEQNO", "SRFLG", "ACCTYP", "ACCNO", "BEGINDATE", "ENDDATE"};
	}
	
	
	/**
	 * @return the tagId
	 */
	public String[] getTagId() {
		return tagId;
	}
	/**
	 * @return the tagFormat
	 */
	public String[] getTagFormat() {
		return tagFormat;
	}
	/**
	 * @return the tagName
	 */
	public String[] getTagName() {
		return tagName;
	}
}
