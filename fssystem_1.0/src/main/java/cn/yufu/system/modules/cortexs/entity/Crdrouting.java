package cn.yufu.system.modules.cortexs.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import cn.yufu.system.common.persistence.DataEntity;

/**
 * 卡BIN信息Entity
 * @author LLG
 * @version 2017-04-25
 */
public class Crdrouting extends DataEntity<Crdrouting> {
	
	private static final long serialVersionUID = 1L;
	private Integer vernoCtx;		// verno_ctx
	private String iid;		// iid
	private String descr;		// descr
	private String exclude;		// exclude
	private String authsvc;		// authsvc
	private String finsvc;		// finsvc
	private String ichgsvc;		// ichgsvc
	private String fecode;		// fecode
	private String scheme;		// scheme
	private String allowsvc;		// allowsvc
	private String localUse;		// local_use
	private String settletype;		// settletype
	private String panlens;		// panlens
	private String svccodes;		// svccodes
	private Integer luhnlen;		// luhnlen
	private String startfmt;		// startfmt
	private String endfmt;		// endfmt
	private Integer isslen;		// isslen
	private Integer posniss;		// posniss
	private Integer posnexp;		// posnexp
	private Integer posnstart;		// posnstart
	private Integer posnsvc;		// posnsvc
	private Integer posncvv;		// posncvv
	private Integer posnpvv;		// posnpvv
	private Integer outbtchtype;		// outbtchtype
	private Integer outatmbtchtype;		// outatmbtchtype
	private String subaddress;		// subaddress
	private String curbill;		// curbill
	private String curset;		// curset
	private Integer rateinstId;		// rateinst_id
	private Integer rateprodId;		// rateprod_id
	private String emvadvsvc;		// emvadvsvc
	private String options;		// options
	
	private String cardBin;
	
	public Crdrouting() {
		super();
	}

	public Crdrouting(String id){
		super(id);
	}

	@NotNull(message="verno_ctx不能为空")
	public Integer getVernoCtx() {
		return vernoCtx;
	}

	public void setVernoCtx(Integer vernoCtx) {
		this.vernoCtx = vernoCtx;
	}
	
	@Length(min=1, max=11, message="iid长度必须介于 1 和 11 之间")
	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}
	
	@Length(min=1, max=25, message="descr长度必须介于 1 和 25 之间")
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	@Length(min=1, max=1, message="exclude长度必须介于 1 和 1 之间")
	public String getExclude() {
		return exclude;
	}

	public void setExclude(String exclude) {
		this.exclude = exclude;
	}
	
	@Length(min=1, max=15, message="authsvc长度必须介于 1 和 15 之间")
	public String getAuthsvc() {
		return authsvc;
	}

	public void setAuthsvc(String authsvc) {
		this.authsvc = authsvc;
	}
	
	@Length(min=1, max=15, message="finsvc长度必须介于 1 和 15 之间")
	public String getFinsvc() {
		return finsvc;
	}

	public void setFinsvc(String finsvc) {
		this.finsvc = finsvc;
	}
	
	@Length(min=1, max=15, message="ichgsvc长度必须介于 1 和 15 之间")
	public String getIchgsvc() {
		return ichgsvc;
	}

	public void setIchgsvc(String ichgsvc) {
		this.ichgsvc = ichgsvc;
	}
	
	@Length(min=1, max=8, message="fecode长度必须介于 1 和 8 之间")
	public String getFecode() {
		return fecode;
	}

	public void setFecode(String fecode) {
		this.fecode = fecode;
	}
	
	@Length(min=1, max=12, message="scheme长度必须介于 1 和 12 之间")
	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	@Length(min=1, max=192, message="allowsvc长度必须介于 1 和 192 之间")
	public String getAllowsvc() {
		return allowsvc;
	}

	public void setAllowsvc(String allowsvc) {
		this.allowsvc = allowsvc;
	}
	
	@Length(min=1, max=1, message="local_use长度必须介于 1 和 1 之间")
	public String getLocalUse() {
		return localUse;
	}

	public void setLocalUse(String localUse) {
		this.localUse = localUse;
	}
	
	@Length(min=1, max=1, message="settletype长度必须介于 1 和 1 之间")
	public String getSettletype() {
		return settletype;
	}

	public void setSettletype(String settletype) {
		this.settletype = settletype;
	}
	
	@Length(min=1, max=12, message="panlens长度必须介于 1 和 12 之间")
	public String getPanlens() {
		return panlens;
	}

	public void setPanlens(String panlens) {
		this.panlens = panlens;
	}
	
	@Length(min=1, max=20, message="svccodes长度必须介于 1 和 20 之间")
	public String getSvccodes() {
		return svccodes;
	}

	public void setSvccodes(String svccodes) {
		this.svccodes = svccodes;
	}
	
	@NotNull(message="luhnlen不能为空")
	public Integer getLuhnlen() {
		return luhnlen;
	}

	public void setLuhnlen(Integer luhnlen) {
		this.luhnlen = luhnlen;
	}
	
	@Length(min=1, max=4, message="startfmt长度必须介于 1 和 4 之间")
	public String getStartfmt() {
		return startfmt;
	}

	public void setStartfmt(String startfmt) {
		this.startfmt = startfmt;
	}
	
	@Length(min=1, max=4, message="endfmt长度必须介于 1 和 4 之间")
	public String getEndfmt() {
		return endfmt;
	}

	public void setEndfmt(String endfmt) {
		this.endfmt = endfmt;
	}
	
	@NotNull(message="isslen不能为空")
	public Integer getIsslen() {
		return isslen;
	}

	public void setIsslen(Integer isslen) {
		this.isslen = isslen;
	}
	
	@NotNull(message="posniss不能为空")
	public Integer getPosniss() {
		return posniss;
	}

	public void setPosniss(Integer posniss) {
		this.posniss = posniss;
	}
	
	@NotNull(message="posnexp不能为空")
	public Integer getPosnexp() {
		return posnexp;
	}

	public void setPosnexp(Integer posnexp) {
		this.posnexp = posnexp;
	}
	
	@NotNull(message="posnstart不能为空")
	public Integer getPosnstart() {
		return posnstart;
	}

	public void setPosnstart(Integer posnstart) {
		this.posnstart = posnstart;
	}
	
	@NotNull(message="posnsvc不能为空")
	public Integer getPosnsvc() {
		return posnsvc;
	}

	public void setPosnsvc(Integer posnsvc) {
		this.posnsvc = posnsvc;
	}
	
	@NotNull(message="posncvv不能为空")
	public Integer getPosncvv() {
		return posncvv;
	}

	public void setPosncvv(Integer posncvv) {
		this.posncvv = posncvv;
	}
	
	@NotNull(message="posnpvv不能为空")
	public Integer getPosnpvv() {
		return posnpvv;
	}

	public void setPosnpvv(Integer posnpvv) {
		this.posnpvv = posnpvv;
	}
	
	@NotNull(message="outbtchtype不能为空")
	public Integer getOutbtchtype() {
		return outbtchtype;
	}

	public void setOutbtchtype(Integer outbtchtype) {
		this.outbtchtype = outbtchtype;
	}
	
	@NotNull(message="outatmbtchtype不能为空")
	public Integer getOutatmbtchtype() {
		return outatmbtchtype;
	}

	public void setOutatmbtchtype(Integer outatmbtchtype) {
		this.outatmbtchtype = outatmbtchtype;
	}
	
	@Length(min=1, max=16, message="subaddress长度必须介于 1 和 16 之间")
	public String getSubaddress() {
		return subaddress;
	}

	public void setSubaddress(String subaddress) {
		this.subaddress = subaddress;
	}
	
	@Length(min=1, max=3, message="curbill长度必须介于 1 和 3 之间")
	public String getCurbill() {
		return curbill;
	}

	public void setCurbill(String curbill) {
		this.curbill = curbill;
	}
	
	@Length(min=1, max=3, message="curset长度必须介于 1 和 3 之间")
	public String getCurset() {
		return curset;
	}

	public void setCurset(String curset) {
		this.curset = curset;
	}
	
	@NotNull(message="rateinst_id不能为空")
	public Integer getRateinstId() {
		return rateinstId;
	}

	public void setRateinstId(Integer rateinstId) {
		this.rateinstId = rateinstId;
	}
	
	@NotNull(message="rateprod_id不能为空")
	public Integer getRateprodId() {
		return rateprodId;
	}

	public void setRateprodId(Integer rateprodId) {
		this.rateprodId = rateprodId;
	}
	
	@Length(min=1, max=15, message="emvadvsvc长度必须介于 1 和 15 之间")
	public String getEmvadvsvc() {
		return emvadvsvc;
	}

	public void setEmvadvsvc(String emvadvsvc) {
		this.emvadvsvc = emvadvsvc;
	}
	
	@Length(min=1, max=64, message="options长度必须介于 1 和 64 之间")
	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getCardBin() {
		return this.descr+"("+this.iid+")";
	}

	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}
	
}