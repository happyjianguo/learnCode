package cn.yufu.posp.terminalmanager.domain.model;

/**
 * TPreTpdu entity. @author MyEclipse Persistence Tools
 */

public class TPreTpdu implements java.io.Serializable {

	// Fields
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
	
	
	// Constructors

	public String getCh_posLinkType() {
		return ch_posLinkType;
	}

	public String getCh_packType() {
		return ch_packType;
	}

	public void setCh_packType(String packType) {
		if(packType!=null&&packType!=""){			
			if(packType.trim().toString().equals("STD"))
				ch_packType = "ֱ��";
			if(packType.trim().toString().equals("CMB"))
				ch_packType = "���д���";
			if(packType.trim().toString().equals("CITIC"))
				ch_packType = "���Ŵ���";
		}else
			ch_packType=packType;
	}

	public void setCh_posLinkType(String posLinkType) {
		if(posLinkType!=null&&posLinkType!=""){
			if(posLinkType.endsWith("0"))
				  ch_posLinkType = "ֱ��";
			if(posLinkType.endsWith("1"))
				  ch_posLinkType = "����";
			if(posLinkType.endsWith("2"))
				  ch_posLinkType = "����";
		}else
			ch_posLinkType=posLinkType;
		
	}

	public String getCh_moduleId() {
		return ch_moduleId;
	}

	public void setCh_moduleId(String ch_moduleId) {
		this.ch_moduleId = ch_moduleId;
	}

	public String getCh_packTypeno() {
		return ch_packTypeno;
	}

	public void setCh_packTypeno(int packTypeno) {
		if(packTypeno==1)
		      ch_packTypeno = "ֱ��";
		if(packTypeno==2)
			  ch_packTypeno = "���д���";
		if(packTypeno==3)
			  ch_packTypeno = "���Ŵ���";
	}


	public String getTpdu() {
		return this.tpdu;
	}

	public void setTpdu(String tpdu) {
		this.tpdu = tpdu;
	}

	public String getPosLinkType() {
		return this.posLinkType==null?this.posLinkType:this.posLinkType.trim();
	}

	public void setPosLinkType(String posLinkType) {
		this.posLinkType = posLinkType;
		setCh_posLinkType(posLinkType);
	}

	public String getChnlno() {
		return this.chnlno;
	}

	public void setChnlno(String chnlno) {
		this.chnlno = chnlno;
	}

	public int getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
		//setCh_moduleId(moduleId);
	}

	public String getPackType() {
		return this.packType==null?this.packType:this.packType.trim();
	}

	public void setPackType(String packType) {
		this.packType = packType;
		setCh_packType(packType.toString());
	}

	public int getPackTypeno() {
		return this.packTypeno;
	}

	public void setPackTypeno(int packTypeno) {
		this.packTypeno = packTypeno;
		setCh_packTypeno(packTypeno);
	}

}