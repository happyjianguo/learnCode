package cn.yufu.posp.terminalmanager.domain.model;

/**
 * TPreTpdu entity. @author MyEclipse Persistence Tools
 */

public class TPreTpdu implements java.io.Serializable {

	// Fields
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
				ch_packType = "直连";
			if(packType.trim().toString().equals("CMB"))
				ch_packType = "招行代理";
			if(packType.trim().toString().equals("CITIC"))
				ch_packType = "中信代理";
		}else
			ch_packType=packType;
	}

	public void setCh_posLinkType(String posLinkType) {
		if(posLinkType!=null&&posLinkType!=""){
			if(posLinkType.endsWith("0"))
				  ch_posLinkType = "直连";
			if(posLinkType.endsWith("1"))
				  ch_posLinkType = "间连";
			if(posLinkType.endsWith("2"))
				  ch_posLinkType = "代理";
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
		      ch_packTypeno = "直连";
		if(packTypeno==2)
			  ch_packTypeno = "招行代理";
		if(packTypeno==3)
			  ch_packTypeno = "中信代理";
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