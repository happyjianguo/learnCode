package cn.com.jansh.dao.entity;

public class PubsDic {
	private String AUTOID;// 序号
	private String NODETAG;// 类型标识
	private String NODETYPE;// 节点定义
	private String NODETYPEDES;// 节点描述
	private String NODEVALUE;// 节点枚举值
	private String NODEREMARK;// 备注

	public String getAUTOID() {
		return AUTOID;
	}

	public void setAUTOID(String aUTOID) {
		AUTOID = aUTOID;
	}

	public String getNODETAG() {
		return NODETAG;
	}

	public void setNODETAG(String nODETAG) {
		NODETAG = nODETAG;
	}

	public String getNODETYPE() {
		return NODETYPE;
	}

	public void setNODETYPE(String nODETYPE) {
		NODETYPE = nODETYPE;
	}

	public String getNODETYPEDES() {
		return NODETYPEDES;
	}

	public void setNODETYPEDES(String nODETYPEDES) {
		NODETYPEDES = nODETYPEDES;
	}

	public String getNODEVALUE() {
		return NODEVALUE;
	}

	public void setNODEVALUE(String nODEVALUE) {
		NODEVALUE = nODEVALUE;
	}

	public String getNODEREMARK() {
		return NODEREMARK;
	}

	public void setNODEREMARK(String nODEREMARK) {
		NODEREMARK = nODEREMARK;
	}
}