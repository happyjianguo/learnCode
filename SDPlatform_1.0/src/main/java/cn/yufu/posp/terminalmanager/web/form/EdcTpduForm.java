package cn.yufu.posp.terminalmanager.web.form;

import cn.yufu.core.web.form.BaseForm;

public class EdcTpduForm extends BaseForm {
	
	/**pos�ϴ���tpduֵ**/
	private String tpdu;
	/**�����������ͣ�0-ֱ��1-����2-����**/
	private String posLinkType;
	/**���׵������ţ�Ŀǰû���õ���Ĭ��Ϊ100**/
	private String chnlno;
	/**���ݽ����������ͷ�����ģ���<p>0-ֱ�� 84-���д��� 85-���Ŵ���</p>**/
	private int moduleId;
	/**POS���ͽ��׵Ĳ�ⱨ����������<p>STD-ֱ�� CMB-���д��� CITIC-���Ŵ���</p>**/
	private String packType;
	/**POS���ͽ��׵Ĳ�ⱨ������ֵ<p>1-ֱ�� 2-���д��� 3-���Ŵ���</p>*/
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
