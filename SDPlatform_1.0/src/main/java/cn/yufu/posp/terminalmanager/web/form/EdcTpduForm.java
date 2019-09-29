package cn.yufu.posp.terminalmanager.web.form;

import cn.yufu.core.web.form.BaseForm;

public class EdcTpduForm extends BaseForm {
	
	/**pos上传的tpdu值**/
	private String tpdu;
	/**交易连接类型（0-直连1-间连2-代理）**/
	private String posLinkType;
	/**交易的渠道号，目前没有用到，默认为100**/
	private String chnlno;
	/**根据交易连接类型发往的模块号<p>0-直连 84-招行代理 85-中信代理</p>**/
	private int moduleId;
	/**POS上送交易的拆解报文类型名称<p>STD-直连 CMB-招行代理 CITIC-中信代理</p>**/
	private String packType;
	/**POS上送交易的拆解报文类型值<p>1-直连 2-招行代理 3-中信代理</p>*/
	private int packTypeno;
	
	
	private String ch_posLinkType;
	private String ch_moduleId;
	private String ch_packType;
	private String ch_packTypeno;
	
	
	
	public String getTpdu() {
		return tpdu;
	}
	public void setTpdu(String tpdu) {
		this.tpdu = tpdu;
	}
	public String getPosLinkType() {
		return posLinkType;
	}
	public void setPosLinkType(String posLinkType) {
		this.posLinkType = posLinkType;
	}
	public String getChnlno() {
		return chnlno;
	}
	public void setChnlno(String chnlno) {
		this.chnlno = chnlno;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getPackType() {
		return packType;
	}
	public void setPackType(String packType) {
		this.packType = packType;
	}
	public int getPackTypeno() {
		return packTypeno;
	}
	public void setPackTypeno(int packTypeno) {
		this.packTypeno = packTypeno;
	}
	public String getCh_posLinkType() {
		return ch_posLinkType;
	}
	public void setCh_posLinkType(String chPosLinkType) {
		ch_posLinkType = chPosLinkType;
	}
	public String getCh_moduleId() {
		return ch_moduleId;
	}
	public void setCh_moduleId(String chModuleId) {
		ch_moduleId = chModuleId;
	}
	public String getCh_packType() {
		return ch_packType;
	}
	public void setCh_packType(String chPackType) {
		ch_packType = chPackType;
	}
	public String getCh_packTypeno() {
		return ch_packTypeno;
	}
	public void setCh_packTypeno(String chPackTypeno) {
		ch_packTypeno = chPackTypeno;
	}
	
	
}
