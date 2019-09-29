package cn.yufu.system.modules.cortexs.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import cn.yufu.system.common.persistence.DataEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 交易流水Entity
 * @author LLG
 * @version 2016-08-24
 */
public class Tlog extends DataEntity<Tlog> {
	
	private static final long serialVersionUID = 1L;
	private Integer vernoCtx;		// verno_ctx
	private String msghdr;		// msghdr
	private String bitmap;		// bitmap
	private Integer stan;		// 交易流水号
	private Integer stanorg;		// 原始交易流水号
	private String rrn;		// 主机流水号
	private String termtype;		// 终端类型
	private Integer termseq;		// pos流水号
	private Integer privdata1;		// privdata1
	private String routeIid;		// 路由国家代码
	private Integer crdseqno;		// crdseqno
	private String pan;		// 卡号
	private String panext;		// panext
	private String dateexp;		// 卡有效期
	private Integer svccode;		// 服务代码
	private String t1;		// 一磁信息
	private String t2;		// 二磁信息
	private String t3;		// 三磁信息
	private String crdacptloc;		// 终端受理地址
	private Integer crdacptbus;		// crdacptbus
	private String crdacptid;		// 商户号
	private String termcode;		// 终端号
	private String poschic;		// poschic
	private String poschac;		// poschac
	private String poscrc;		// PIN输入方式
	private String posoe;		// 操作环境
	private String poschp;		// 持卡人是否在现场
	private String poscp;		// 卡是否在现场
	private String poscdim;		// 卡数据输入方式
	private String poscham;		// 持卡人授权方式
	private String poscha;		// 持卡人授权途径
	private String poschad;		// 卡数据输出能力
	private String possd;		// 终端输出能力
	private String pospcc;		// 密码捕获能力
	private Integer poscc89;		// poscc_89
	private String aiid;		// 收单机构代码
	private String acqcountry;		// 收单机构国家
	private Date datexmit;		// 时间（UTC）
	private Integer timexmit;		// 日期（UTC）
	private Date datelocal;		// 交易日期
	private Integer timelocal;		// 交易时间
	private Date datexmitorg;		// datexmitorg
	private Integer timexmitorg;		// timexmitorg
	private String msgcls;		// msgcls
	private String msgclsorg;		// msgclsorg
	private String msgfn;		// msgfn
	private String msgfnorg;		// msgfnorg
	private String txnsrc;		// 交易来源码
	private String txnsrcorg;		// txnsrcorg
	private Integer fncode;		// fncode
	private Integer txncode;		// 交易码
	private Integer txnstatus;		// 交易状态
	private String rspsrc;		// rspsrc
	private Integer stipreason;		// stipreason
	private Integer rejreason;		// rejreason
	private String curtxn;		// 交易币种
	private String amttxn;		// 交易金额
	private String amttxnfee;		// 交易手续费
	private String amtprocfee;		// 处理手续费
	private String amttxnorg;		// 原始交易金额
	private String amttxncb;		// amttxncb
	private Integer actype1;		// actype1
	private String acnum1;		// acnum1
	private Integer actype2;		// actype2
	private String acnum2;		// acnum2
	private String actioncode;		// actioncode
	private String rspcode;		// 应答码
	private Integer reasoncode;		// 原因代码
	private String aprvlcode;		// aprvlcode
	private String vercodes;		// vercodes
	private Integer authlife;		// authlife
	private String fiid;		// 机构机构码
	private String fiidcountry;		// 机构标识国家
	private String riid;		// 接收机构ID
	private String riidcountry;		// 接收机构国家
	private String siid;		// 清算机构ID
	private String siidcountry;		// 清算机构国家
	private Date dateset;		// 清算日期
	private String amtset;		// 清算金额
	private String rateset;		// 币种转换汇率
	private String curset;		// 清算币种
	private Integer inbtchid;		// inbtchid
	private Integer outbtchid;		// outbtchid
	private Date ctxdate;		// ctxdate
	private Date indaterec;		// indaterec
	private Integer inrecbtch;		// inrecbtch
	private Date outdaterec;		// outdaterec
	private Integer outrecbtch;		// outrecbtch
	private Integer inbtchseq;		// 批次索引号
	private String afe;		// 收单FE
	private String ife;		// 发卡FE
	private Integer outbtchtype;		// 批次类型
	private String curbill;		// 客户清算币种
	private String amtbill;		// 客户清算金额
	private String amtbillcb;		// 部分现金交易
	private String ratebill;		// 转换汇率
	private String adddata;		// 附加数据
	private String scheme;		// 卡组织
	private String amttax;		// 交易手续费（POS）
	private String ratetax;		// 交易税百分比
	private String amtcom;		// 商户委托交易
	private String vcps;		// vcps
	private String vmscflds;		// vmscflds
	private String cleared;		// 结算标志
	private String pmcid;		// pmcid
	private String curTraded;		// cur_traded
	private String amtTraded;		// amt_traded
	private String amtorgTraded;		// amtorg_traded
	private String amtcbTraded;		// amtcb_traded
	private String panDisplay;		// pan_display
	private String crdproduct;		// 卡产品
	private Integer crddetId;		// crddet_id
	private Integer crddetRealId;		// crddet_real_id
	private Integer accdet1Id;		// accdet1_id
	private Integer accdet2Id;		// accdet2_id
	private String catParams;		// cat_params
	private Long groupId;		// group_id
	private String trn;		// trn
	private String arn;		// arn
	private String amtIssuerCom;		// amt_issuer_com
	private String curIssuerCom;		// cur_issuer_com
	private String amtAcqCom;		// amt_acq_com
	private String curAcqCom;		// cur_acq_com
	private String amtReimbursement;		// 退还金额
	private String curReimbursement;		// 退还币种
	private String curtxnfee;		// 交易手续费
	private String curprocfee;		// 交易处理费
	private String curtxnorg;		// 原始交易币种
	private String curtax;		// 当前税
	private String curcom;		// curcom
	private String amttxnAuth;		// 授权交易金额
	private String amttxncbAuth;		// 授权交易
	private Integer atc;		// 现金授权交易
	private String ucaf;		// ucaf
	private String aavars;		// aavars
	private String mcPrivate;		// mc_private
	private String srcIid;		// src_iid
	private String srcIidCountry;		// src_iid_country
	private String destIid;		// dest_iid
	private String destIidCountry;		// dest_iid_country
	private String whenCreated;		// when_created
	private Long acqInstId;		// acq_inst_id
	private Long issInstId;		// iss_inst_id
	private String netMrchRegion;		// net_mrch_region
	private String netIssRegion;		// net_iss_region
	private String netMccGroup;		// net_mcc_group
	private String netCrdproduct;		// net_crdproduct
	private String netMrchId;		// net_mrch_id
	private String netRrn;		// net_rrn
	private String poscap;		// poscap
	private String poscdic;		// Pos地址
	private String crdacptlocName;		// crdacptloc_name
	private String crdacptlocStreet;		// crdacptloc_street
	private String crdacptlocCity;		// crdacptloc_city
	private String crdacptlocPostcode;		// crdacptloc_postcode
	private String crdacptlocRegion;		// crdacptloc_region
	private String crdacptlocCountry;		// crdacptloc_country
	private Date authExpdate;		// auth_expdate
	private String verresults;		// verresults
	private String subTxncode;		// 子处理码
	private String beginDatelocal;		// 开始 交易日期
	private String endDatelocal;		// 结束 交易日期
	
	private String txnTypeDesc; 	//交易类型描述
	private String txnType;			//交易类型
	
	private String fatherOrder;
	private String time;
	private String salesPoint;
	private String payerName;
	private String amtEachCrd;
	
	
	public Tlog() {
		super();
	}

	public Tlog(String id){
		super(id);
	}

	@NotNull(message="verno_ctx不能为空")
	public Integer getVernoCtx() {
		return vernoCtx;
	}

	public void setVernoCtx(Integer vernoCtx) {
		this.vernoCtx = vernoCtx;
	}
	
	@Length(min=1, max=64, message="msghdr长度必须介于 1 和 64 之间")
	public String getMsghdr() {
		return msghdr;
	}

	public void setMsghdr(String msghdr) {
		this.msghdr = msghdr;
	}
	
	@Length(min=1, max=32, message="bitmap长度必须介于 1 和 32 之间")
	public String getBitmap() {
		return bitmap;
	}

	public void setBitmap(String bitmap) {
		this.bitmap = bitmap;
	}
	
	@NotNull(message="交易流水号不能为空")
	public Integer getStan() {
		return stan;
	}

	public void setStan(Integer stan) {
		this.stan = stan;
	}
	
	@NotNull(message="原始交易流水号不能为空")
	public Integer getStanorg() {
		return stanorg;
	}

	public void setStanorg(Integer stanorg) {
		this.stanorg = stanorg;
	}
	
	@Length(min=1, max=12, message="主机流水号长度必须介于 1 和 12 之间")
	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	
	@Length(min=1, max=1, message="终端类型长度必须介于 1 和 1 之间")
	public String getTermtype() {
		return termtype;
	}

	public void setTermtype(String termtype) {
		this.termtype = termtype;
	}
	
	@NotNull(message="pos流水号不能为空")
	public Integer getTermseq() {
		return termseq;
	}

	public void setTermseq(Integer termseq) {
		this.termseq = termseq;
	}
	
	@NotNull(message="privdata1不能为空")
	public Integer getPrivdata1() {
		return privdata1;
	}

	public void setPrivdata1(Integer privdata1) {
		this.privdata1 = privdata1;
	}
	
	@Length(min=1, max=11, message="路由国家代码长度必须介于 1 和 11 之间")
	public String getRouteIid() {
		return routeIid;
	}

	public void setRouteIid(String routeIid) {
		this.routeIid = routeIid;
	}
	
	@NotNull(message="crdseqno不能为空")
	public Integer getCrdseqno() {
		return crdseqno;
	}

	public void setCrdseqno(Integer crdseqno) {
		this.crdseqno = crdseqno;
	}
	
	@Length(min=1, max=19, message="卡号长度必须介于 1 和 19 之间")
	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}
	
	@Length(min=1, max=3, message="panext长度必须介于 1 和 3 之间")
	public String getPanext() {
		return panext;
	}

	public void setPanext(String panext) {
		this.panext = panext;
	}
	
	@Length(min=1, max=4, message="卡有效期长度必须介于 1 和 4 之间")
	public String getDateexp() {
		return dateexp;
	}

	public void setDateexp(String dateexp) {
		this.dateexp = dateexp;
	}
	
	@NotNull(message="服务代码不能为空")
	public Integer getSvccode() {
		return svccode;
	}

	public void setSvccode(Integer svccode) {
		this.svccode = svccode;
	}
	
	@Length(min=1, max=75, message="一磁信息长度必须介于 1 和 75 之间")
	public String getT1() {
		return t1;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}
	
	@Length(min=1, max=37, message="二磁信息长度必须介于 1 和 37 之间")
	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}
	
	@Length(min=1, max=106, message="三磁信息长度必须介于 1 和 106 之间")
	public String getT3() {
		return t3;
	}

	public void setT3(String t3) {
		this.t3 = t3;
	}
	
	@Length(min=1, max=64, message="终端受理地址长度必须介于 1 和 64 之间")
	public String getCrdacptloc() {
		return crdacptloc;
	}

	public void setCrdacptloc(String crdacptloc) {
		this.crdacptloc = crdacptloc;
	}
	
	@NotNull(message="crdacptbus不能为空")
	public Integer getCrdacptbus() {
		return crdacptbus;
	}

	public void setCrdacptbus(Integer crdacptbus) {
		this.crdacptbus = crdacptbus;
	}
	
	@Length(min=1, max=15, message="商户号长度必须介于 1 和 15 之间")
	public String getCrdacptid() {
		return crdacptid;
	}

	public void setCrdacptid(String crdacptid) {
		this.crdacptid = crdacptid;
	}
	
	@Length(min=1, max=16, message="终端号长度必须介于 1 和 16 之间")
	public String getTermcode() {
		return termcode;
	}

	public void setTermcode(String termcode) {
		this.termcode = termcode;
	}
	
	@Length(min=1, max=1, message="poschic长度必须介于 1 和 1 之间")
	public String getPoschic() {
		return poschic;
	}

	public void setPoschic(String poschic) {
		this.poschic = poschic;
	}
	
	@Length(min=1, max=1, message="poschac长度必须介于 1 和 1 之间")
	public String getPoschac() {
		return poschac;
	}

	public void setPoschac(String poschac) {
		this.poschac = poschac;
	}
	
	@Length(min=1, max=1, message="PIN输入方式长度必须介于 1 和 1 之间")
	public String getPoscrc() {
		return poscrc;
	}

	public void setPoscrc(String poscrc) {
		this.poscrc = poscrc;
	}
	
	@Length(min=1, max=1, message="操作环境长度必须介于 1 和 1 之间")
	public String getPosoe() {
		return posoe;
	}

	public void setPosoe(String posoe) {
		this.posoe = posoe;
	}
	
	@Length(min=1, max=1, message="持卡人是否在现场长度必须介于 1 和 1 之间")
	public String getPoschp() {
		return poschp;
	}

	public void setPoschp(String poschp) {
		this.poschp = poschp;
	}
	
	@Length(min=1, max=1, message="卡是否在现场长度必须介于 1 和 1 之间")
	public String getPoscp() {
		return poscp;
	}

	public void setPoscp(String poscp) {
		this.poscp = poscp;
	}
	
	@Length(min=1, max=1, message="卡数据输入方式长度必须介于 1 和 1 之间")
	public String getPoscdim() {
		return poscdim;
	}

	public void setPoscdim(String poscdim) {
		this.poscdim = poscdim;
	}
	
	@Length(min=1, max=1, message="持卡人授权方式长度必须介于 1 和 1 之间")
	public String getPoscham() {
		return poscham;
	}

	public void setPoscham(String poscham) {
		this.poscham = poscham;
	}
	
	@Length(min=1, max=1, message="持卡人授权途径长度必须介于 1 和 1 之间")
	public String getPoscha() {
		return poscha;
	}

	public void setPoscha(String poscha) {
		this.poscha = poscha;
	}
	
	@Length(min=1, max=1, message="卡数据输出能力长度必须介于 1 和 1 之间")
	public String getPoschad() {
		return poschad;
	}

	public void setPoschad(String poschad) {
		this.poschad = poschad;
	}
	
	@Length(min=1, max=1, message="终端输出能力长度必须介于 1 和 1 之间")
	public String getPossd() {
		return possd;
	}

	public void setPossd(String possd) {
		this.possd = possd;
	}
	
	@Length(min=1, max=1, message="密码捕获能力长度必须介于 1 和 1 之间")
	public String getPospcc() {
		return pospcc;
	}

	public void setPospcc(String pospcc) {
		this.pospcc = pospcc;
	}
	
	@NotNull(message="poscc_89不能为空")
	public Integer getPoscc89() {
		return poscc89;
	}

	public void setPoscc89(Integer poscc89) {
		this.poscc89 = poscc89;
	}
	
	@Length(min=1, max=11, message="收单机构代码长度必须介于 1 和 11 之间")
	public String getAiid() {
		return aiid;
	}

	public void setAiid(String aiid) {
		this.aiid = aiid;
	}
	
	@Length(min=1, max=3, message="收单机构国家长度必须介于 1 和 3 之间")
	public String getAcqcountry() {
		return acqcountry;
	}

	public void setAcqcountry(String acqcountry) {
		this.acqcountry = acqcountry;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="时间（UTC）不能为空")
	public Date getDatexmit() {
		return datexmit;
	}

	public void setDatexmit(Date datexmit) {
		this.datexmit = datexmit;
	}
	
	@NotNull(message="日期（UTC）不能为空")
	public Integer getTimexmit() {
		return timexmit;
	}

	public void setTimexmit(Integer timexmit) {
		this.timexmit = timexmit;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="交易日期不能为空")
	public Date getDatelocal() {
		return datelocal;
	}

	public void setDatelocal(Date datelocal) {
		this.datelocal = datelocal;
	}
	
	@NotNull(message="交易时间不能为空")
	public Integer getTimelocal() {
		return timelocal;
	}

	public void setTimelocal(Integer timelocal) {
		this.timelocal = timelocal;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="datexmitorg不能为空")
	public Date getDatexmitorg() {
		return datexmitorg;
	}

	public void setDatexmitorg(Date datexmitorg) {
		this.datexmitorg = datexmitorg;
	}
	
	@NotNull(message="timexmitorg不能为空")
	public Integer getTimexmitorg() {
		return timexmitorg;
	}

	public void setTimexmitorg(Integer timexmitorg) {
		this.timexmitorg = timexmitorg;
	}
	
	@Length(min=1, max=1, message="msgcls长度必须介于 1 和 1 之间")
	public String getMsgcls() {
		return msgcls;
	}

	public void setMsgcls(String msgcls) {
		this.msgcls = msgcls;
	}
	
	@Length(min=1, max=1, message="msgclsorg长度必须介于 1 和 1 之间")
	public String getMsgclsorg() {
		return msgclsorg;
	}

	public void setMsgclsorg(String msgclsorg) {
		this.msgclsorg = msgclsorg;
	}
	
	@Length(min=1, max=1, message="msgfn长度必须介于 1 和 1 之间")
	public String getMsgfn() {
		return msgfn;
	}

	public void setMsgfn(String msgfn) {
		this.msgfn = msgfn;
	}
	
	@Length(min=1, max=1, message="msgfnorg长度必须介于 1 和 1 之间")
	public String getMsgfnorg() {
		return msgfnorg;
	}

	public void setMsgfnorg(String msgfnorg) {
		this.msgfnorg = msgfnorg;
	}
	
	@Length(min=1, max=1, message="交易来源码长度必须介于 1 和 1 之间")
	public String getTxnsrc() {
		return txnsrc;
	}

	public void setTxnsrc(String txnsrc) {
		this.txnsrc = txnsrc;
	}
	
	@Length(min=1, max=1, message="txnsrcorg长度必须介于 1 和 1 之间")
	public String getTxnsrcorg() {
		return txnsrcorg;
	}

	public void setTxnsrcorg(String txnsrcorg) {
		this.txnsrcorg = txnsrcorg;
	}
	
	@NotNull(message="fncode不能为空")
	public Integer getFncode() {
		return fncode;
	}

	public void setFncode(Integer fncode) {
		this.fncode = fncode;
	}
	
	@NotNull(message="交易码不能为空")
	public Integer getTxncode() {
		return txncode;
	}

	public void setTxncode(Integer txncode) {
		this.txncode = txncode;
	}
	
	@NotNull(message="交易状态不能为空")
	public Integer getTxnstatus() {
		return txnstatus;
	}

	public void setTxnstatus(Integer txnstatus) {
		this.txnstatus = txnstatus;
	}
	
	@Length(min=1, max=2, message="rspsrc长度必须介于 1 和 2 之间")
	public String getRspsrc() {
		return rspsrc;
	}

	public void setRspsrc(String rspsrc) {
		this.rspsrc = rspsrc;
	}
	
	@NotNull(message="stipreason不能为空")
	public Integer getStipreason() {
		return stipreason;
	}

	public void setStipreason(Integer stipreason) {
		this.stipreason = stipreason;
	}
	
	@NotNull(message="rejreason不能为空")
	public Integer getRejreason() {
		return rejreason;
	}

	public void setRejreason(Integer rejreason) {
		this.rejreason = rejreason;
	}
	
	@Length(min=1, max=3, message="交易币种长度必须介于 1 和 3 之间")
	public String getCurtxn() {
		return curtxn;
	}

	public void setCurtxn(String curtxn) {
		this.curtxn = curtxn;
	}
	
	public String getAmttxn() {
		return amttxn;
	}

	public void setAmttxn(String amttxn) {
		this.amttxn = amttxn;
	}
	
	public String getAmttxnfee() {
		return amttxnfee;
	}

	public void setAmttxnfee(String amttxnfee) {
		this.amttxnfee = amttxnfee;
	}
	
	public String getAmtprocfee() {
		return amtprocfee;
	}

	public void setAmtprocfee(String amtprocfee) {
		this.amtprocfee = amtprocfee;
	}
	
	public String getAmttxnorg() {
		return amttxnorg;
	}

	public void setAmttxnorg(String amttxnorg) {
		this.amttxnorg = amttxnorg;
	}
	
	public String getAmttxncb() {
		return amttxncb;
	}

	public void setAmttxncb(String amttxncb) {
		this.amttxncb = amttxncb;
	}
	
	@NotNull(message="actype1不能为空")
	public Integer getActype1() {
		return actype1;
	}

	public void setActype1(Integer actype1) {
		this.actype1 = actype1;
	}
	
	@Length(min=1, max=28, message="acnum1长度必须介于 1 和 28 之间")
	public String getAcnum1() {
		return acnum1;
	}

	public void setAcnum1(String acnum1) {
		this.acnum1 = acnum1;
	}
	
	@NotNull(message="actype2不能为空")
	public Integer getActype2() {
		return actype2;
	}

	public void setActype2(Integer actype2) {
		this.actype2 = actype2;
	}
	
	@Length(min=1, max=28, message="acnum2长度必须介于 1 和 28 之间")
	public String getAcnum2() {
		return acnum2;
	}

	public void setAcnum2(String acnum2) {
		this.acnum2 = acnum2;
	}
	
	@Length(min=1, max=1, message="actioncode长度必须介于 1 和 1 之间")
	public String getActioncode() {
		return actioncode;
	}

	public void setActioncode(String actioncode) {
		this.actioncode = actioncode;
	}
	
	@Length(min=1, max=2, message="应答码长度必须介于 1 和 2 之间")
	public String getRspcode() {
		return rspcode;
	}

	public void setRspcode(String rspcode) {
		this.rspcode = rspcode;
	}
	
	@NotNull(message="原因代码不能为空")
	public Integer getReasoncode() {
		return reasoncode;
	}

	public void setReasoncode(Integer reasoncode) {
		this.reasoncode = reasoncode;
	}
	
	@Length(min=1, max=9, message="aprvlcode长度必须介于 1 和 9 之间")
	public String getAprvlcode() {
		return aprvlcode;
	}

	public void setAprvlcode(String aprvlcode) {
		this.aprvlcode = aprvlcode;
	}
	
	@Length(min=1, max=3, message="vercodes长度必须介于 1 和 3 之间")
	public String getVercodes() {
		return vercodes;
	}

	public void setVercodes(String vercodes) {
		this.vercodes = vercodes;
	}
	
	@NotNull(message="authlife不能为空")
	public Integer getAuthlife() {
		return authlife;
	}

	public void setAuthlife(Integer authlife) {
		this.authlife = authlife;
	}
	
	@Length(min=1, max=11, message="机构机构码长度必须介于 1 和 11 之间")
	public String getFiid() {
		return fiid;
	}

	public void setFiid(String fiid) {
		this.fiid = fiid;
	}
	
	@Length(min=1, max=3, message="机构标识国家长度必须介于 1 和 3 之间")
	public String getFiidcountry() {
		return fiidcountry;
	}

	public void setFiidcountry(String fiidcountry) {
		this.fiidcountry = fiidcountry;
	}
	
	@Length(min=1, max=11, message="接收机构ID长度必须介于 1 和 11 之间")
	public String getRiid() {
		return riid;
	}

	public void setRiid(String riid) {
		this.riid = riid;
	}
	
	@Length(min=1, max=3, message="接收机构国家长度必须介于 1 和 3 之间")
	public String getRiidcountry() {
		return riidcountry;
	}

	public void setRiidcountry(String riidcountry) {
		this.riidcountry = riidcountry;
	}
	
	@Length(min=1, max=11, message="清算机构ID长度必须介于 1 和 11 之间")
	public String getSiid() {
		return siid;
	}

	public void setSiid(String siid) {
		this.siid = siid;
	}
	
	@Length(min=1, max=3, message="清算机构国家长度必须介于 1 和 3 之间")
	public String getSiidcountry() {
		return siidcountry;
	}

	public void setSiidcountry(String siidcountry) {
		this.siidcountry = siidcountry;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="清算日期不能为空")
	public Date getDateset() {
		return dateset;
	}

	public void setDateset(Date dateset) {
		this.dateset = dateset;
	}
	
	public String getAmtset() {
		return amtset;
	}

	public void setAmtset(String amtset) {
		this.amtset = amtset;
	}
	
	public String getRateset() {
		return rateset;
	}

	public void setRateset(String rateset) {
		this.rateset = rateset;
	}
	
	@Length(min=1, max=3, message="清算币种长度必须介于 1 和 3 之间")
	public String getCurset() {
		return curset;
	}

	public void setCurset(String curset) {
		this.curset = curset;
	}
	
	@NotNull(message="inbtchid不能为空")
	public Integer getInbtchid() {
		return inbtchid;
	}

	public void setInbtchid(Integer inbtchid) {
		this.inbtchid = inbtchid;
	}
	
	@NotNull(message="outbtchid不能为空")
	public Integer getOutbtchid() {
		return outbtchid;
	}

	public void setOutbtchid(Integer outbtchid) {
		this.outbtchid = outbtchid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="ctxdate不能为空")
	public Date getCtxdate() {
		return ctxdate;
	}

	public void setCtxdate(Date ctxdate) {
		this.ctxdate = ctxdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="indaterec不能为空")
	public Date getIndaterec() {
		return indaterec;
	}

	public void setIndaterec(Date indaterec) {
		this.indaterec = indaterec;
	}
	
	@NotNull(message="inrecbtch不能为空")
	public Integer getInrecbtch() {
		return inrecbtch;
	}

	public void setInrecbtch(Integer inrecbtch) {
		this.inrecbtch = inrecbtch;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="outdaterec不能为空")
	public Date getOutdaterec() {
		return outdaterec;
	}

	public void setOutdaterec(Date outdaterec) {
		this.outdaterec = outdaterec;
	}
	
	@NotNull(message="outrecbtch不能为空")
	public Integer getOutrecbtch() {
		return outrecbtch;
	}

	public void setOutrecbtch(Integer outrecbtch) {
		this.outrecbtch = outrecbtch;
	}
	
	@NotNull(message="批次索引号不能为空")
	public Integer getInbtchseq() {
		return inbtchseq;
	}

	public void setInbtchseq(Integer inbtchseq) {
		this.inbtchseq = inbtchseq;
	}
	
	@Length(min=1, max=8, message="收单FE长度必须介于 1 和 8 之间")
	public String getAfe() {
		return afe;
	}

	public void setAfe(String afe) {
		this.afe = afe;
	}
	
	@Length(min=1, max=8, message="发卡FE长度必须介于 1 和 8 之间")
	public String getIfe() {
		return ife;
	}

	public void setIfe(String ife) {
		this.ife = ife;
	}
	
	@NotNull(message="批次类型不能为空")
	public Integer getOutbtchtype() {
		return outbtchtype;
	}

	public void setOutbtchtype(Integer outbtchtype) {
		this.outbtchtype = outbtchtype;
	}
	
	@Length(min=1, max=3, message="客户清算币种长度必须介于 1 和 3 之间")
	public String getCurbill() {
		return curbill;
	}

	public void setCurbill(String curbill) {
		this.curbill = curbill;
	}
	
	public String getAmtbill() {
		return amtbill;
	}

	public void setAmtbill(String amtbill) {
		this.amtbill = amtbill;
	}
	
	public String getAmtbillcb() {
		return amtbillcb;
	}

	public void setAmtbillcb(String amtbillcb) {
		this.amtbillcb = amtbillcb;
	}
	
	public String getRatebill() {
		return ratebill;
	}

	public void setRatebill(String ratebill) {
		this.ratebill = ratebill;
	}
	
	@Length(min=1, max=255, message="附加数据长度必须介于 1 和 255 之间")
	public String getAdddata() {
		return adddata;
	}

	public void setAdddata(String adddata) {
		this.adddata = adddata;
	}
	
	@Length(min=1, max=12, message="卡组织长度必须介于 1 和 12 之间")
	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	public String getAmttax() {
		return amttax;
	}

	public void setAmttax(String amttax) {
		this.amttax = amttax;
	}
	
	public String getRatetax() {
		return ratetax;
	}

	public void setRatetax(String ratetax) {
		this.ratetax = ratetax;
	}
	
	public String getAmtcom() {
		return amtcom;
	}

	public void setAmtcom(String amtcom) {
		this.amtcom = amtcom;
	}
	
	@Length(min=1, max=20, message="vcps长度必须介于 1 和 20 之间")
	public String getVcps() {
		return vcps;
	}

	public void setVcps(String vcps) {
		this.vcps = vcps;
	}
	
	@Length(min=1, max=64, message="vmscflds长度必须介于 1 和 64 之间")
	public String getVmscflds() {
		return vmscflds;
	}

	public void setVmscflds(String vmscflds) {
		this.vmscflds = vmscflds;
	}
	
	@Length(min=1, max=1, message="结算标志长度必须介于 1 和 1 之间")
	public String getCleared() {
		return cleared;
	}

	public void setCleared(String cleared) {
		this.cleared = cleared;
	}
	
	@Length(min=1, max=6, message="pmcid长度必须介于 1 和 6 之间")
	public String getPmcid() {
		return pmcid;
	}

	public void setPmcid(String pmcid) {
		this.pmcid = pmcid;
	}
	
	@Length(min=1, max=3, message="cur_traded长度必须介于 1 和 3 之间")
	public String getCurTraded() {
		return curTraded;
	}

	public void setCurTraded(String curTraded) {
		this.curTraded = curTraded;
	}
	
	public String getAmtTraded() {
		return amtTraded;
	}

	public void setAmtTraded(String amtTraded) {
		this.amtTraded = amtTraded;
	}
	
	public String getAmtorgTraded() {
		return amtorgTraded;
	}

	public void setAmtorgTraded(String amtorgTraded) {
		this.amtorgTraded = amtorgTraded;
	}
	
	public String getAmtcbTraded() {
		return amtcbTraded;
	}

	public void setAmtcbTraded(String amtcbTraded) {
		this.amtcbTraded = amtcbTraded;
	}
	
	@Length(min=1, max=19, message="pan_display长度必须介于 1 和 19 之间")
	public String getPanDisplay() {
		return panDisplay;
	}

	public void setPanDisplay(String panDisplay) {
		this.panDisplay = panDisplay;
	}
	
	@Length(min=1, max=4, message="卡产品长度必须介于 1 和 4 之间")
	public String getCrdproduct() {
		return crdproduct;
	}

	public void setCrdproduct(String crdproduct) {
		this.crdproduct = crdproduct;
	}
	
	@NotNull(message="crddet_id不能为空")
	public Integer getCrddetId() {
		return crddetId;
	}

	public void setCrddetId(Integer crddetId) {
		this.crddetId = crddetId;
	}
	
	@NotNull(message="crddet_real_id不能为空")
	public Integer getCrddetRealId() {
		return crddetRealId;
	}

	public void setCrddetRealId(Integer crddetRealId) {
		this.crddetRealId = crddetRealId;
	}
	
	@NotNull(message="accdet1_id不能为空")
	public Integer getAccdet1Id() {
		return accdet1Id;
	}

	public void setAccdet1Id(Integer accdet1Id) {
		this.accdet1Id = accdet1Id;
	}
	
	@NotNull(message="accdet2_id不能为空")
	public Integer getAccdet2Id() {
		return accdet2Id;
	}

	public void setAccdet2Id(Integer accdet2Id) {
		this.accdet2Id = accdet2Id;
	}
	
	@Length(min=1, max=127, message="cat_params长度必须介于 1 和 127 之间")
	public String getCatParams() {
		return catParams;
	}

	public void setCatParams(String catParams) {
		this.catParams = catParams;
	}
	
	@NotNull(message="group_id不能为空")
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	@Length(min=1, max=25, message="trn长度必须介于 1 和 25 之间")
	public String getTrn() {
		return trn;
	}

	public void setTrn(String trn) {
		this.trn = trn;
	}
	
	@Length(min=1, max=23, message="arn长度必须介于 1 和 23 之间")
	public String getArn() {
		return arn;
	}

	public void setArn(String arn) {
		this.arn = arn;
	}
	
	public String getAmtIssuerCom() {
		return amtIssuerCom;
	}

	public void setAmtIssuerCom(String amtIssuerCom) {
		this.amtIssuerCom = amtIssuerCom;
	}
	
	@Length(min=1, max=3, message="cur_issuer_com长度必须介于 1 和 3 之间")
	public String getCurIssuerCom() {
		return curIssuerCom;
	}

	public void setCurIssuerCom(String curIssuerCom) {
		this.curIssuerCom = curIssuerCom;
	}
	
	public String getAmtAcqCom() {
		return amtAcqCom;
	}

	public void setAmtAcqCom(String amtAcqCom) {
		this.amtAcqCom = amtAcqCom;
	}
	
	@Length(min=1, max=3, message="cur_acq_com长度必须介于 1 和 3 之间")
	public String getCurAcqCom() {
		return curAcqCom;
	}

	public void setCurAcqCom(String curAcqCom) {
		this.curAcqCom = curAcqCom;
	}
	
	public String getAmtReimbursement() {
		return amtReimbursement;
	}

	public void setAmtReimbursement(String amtReimbursement) {
		this.amtReimbursement = amtReimbursement;
	}
	
	@Length(min=1, max=3, message="退还币种长度必须介于 1 和 3 之间")
	public String getCurReimbursement() {
		return curReimbursement;
	}

	public void setCurReimbursement(String curReimbursement) {
		this.curReimbursement = curReimbursement;
	}
	
	@Length(min=1, max=3, message="交易手续费长度必须介于 1 和 3 之间")
	public String getCurtxnfee() {
		return curtxnfee;
	}

	public void setCurtxnfee(String curtxnfee) {
		this.curtxnfee = curtxnfee;
	}
	
	@Length(min=1, max=3, message="交易处理费长度必须介于 1 和 3 之间")
	public String getCurprocfee() {
		return curprocfee;
	}

	public void setCurprocfee(String curprocfee) {
		this.curprocfee = curprocfee;
	}
	
	@Length(min=1, max=3, message="原始交易币种长度必须介于 1 和 3 之间")
	public String getCurtxnorg() {
		return curtxnorg;
	}

	public void setCurtxnorg(String curtxnorg) {
		this.curtxnorg = curtxnorg;
	}
	
	@Length(min=1, max=3, message="当前税长度必须介于 1 和 3 之间")
	public String getCurtax() {
		return curtax;
	}

	public void setCurtax(String curtax) {
		this.curtax = curtax;
	}
	
	@Length(min=1, max=3, message="curcom长度必须介于 1 和 3 之间")
	public String getCurcom() {
		return curcom;
	}

	public void setCurcom(String curcom) {
		this.curcom = curcom;
	}
	
	public String getAmttxnAuth() {
		return amttxnAuth;
	}

	public void setAmttxnAuth(String amttxnAuth) {
		this.amttxnAuth = amttxnAuth;
	}
	
	public String getAmttxncbAuth() {
		return amttxncbAuth;
	}

	public void setAmttxncbAuth(String amttxncbAuth) {
		this.amttxncbAuth = amttxncbAuth;
	}
	
	@NotNull(message="现金授权交易不能为空")
	public Integer getAtc() {
		return atc;
	}

	public void setAtc(Integer atc) {
		this.atc = atc;
	}
	
	@Length(min=1, max=40, message="ucaf长度必须介于 1 和 40 之间")
	public String getUcaf() {
		return ucaf;
	}

	public void setUcaf(String ucaf) {
		this.ucaf = ucaf;
	}
	
	@Length(min=1, max=1, message="aavars长度必须介于 1 和 1 之间")
	public String getAavars() {
		return aavars;
	}

	public void setAavars(String aavars) {
		this.aavars = aavars;
	}
	
	@Length(min=1, max=100, message="mc_private长度必须介于 1 和 100 之间")
	public String getMcPrivate() {
		return mcPrivate;
	}

	public void setMcPrivate(String mcPrivate) {
		this.mcPrivate = mcPrivate;
	}
	
	@Length(min=1, max=11, message="src_iid长度必须介于 1 和 11 之间")
	public String getSrcIid() {
		return srcIid;
	}

	public void setSrcIid(String srcIid) {
		this.srcIid = srcIid;
	}
	
	@Length(min=1, max=3, message="src_iid_country长度必须介于 1 和 3 之间")
	public String getSrcIidCountry() {
		return srcIidCountry;
	}

	public void setSrcIidCountry(String srcIidCountry) {
		this.srcIidCountry = srcIidCountry;
	}
	
	@Length(min=1, max=11, message="dest_iid长度必须介于 1 和 11 之间")
	public String getDestIid() {
		return destIid;
	}

	public void setDestIid(String destIid) {
		this.destIid = destIid;
	}
	
	@Length(min=1, max=3, message="dest_iid_country长度必须介于 1 和 3 之间")
	public String getDestIidCountry() {
		return destIidCountry;
	}

	public void setDestIidCountry(String destIidCountry) {
		this.destIidCountry = destIidCountry;
	}
	
	@Length(min=1, max=17, message="when_created长度必须介于 1 和 17 之间")
	public String getWhenCreated() {
		return whenCreated;
	}

	public void setWhenCreated(String whenCreated) {
		this.whenCreated = whenCreated;
	}
	
	@NotNull(message="acq_inst_id不能为空")
	public Long getAcqInstId() {
		return acqInstId;
	}

	public void setAcqInstId(Long acqInstId) {
		this.acqInstId = acqInstId;
	}
	
	@NotNull(message="iss_inst_id不能为空")
	public Long getIssInstId() {
		return issInstId;
	}

	public void setIssInstId(Long issInstId) {
		this.issInstId = issInstId;
	}
	
	@Length(min=1, max=4, message="net_mrch_region长度必须介于 1 和 4 之间")
	public String getNetMrchRegion() {
		return netMrchRegion;
	}

	public void setNetMrchRegion(String netMrchRegion) {
		this.netMrchRegion = netMrchRegion;
	}
	
	@Length(min=1, max=4, message="net_iss_region长度必须介于 1 和 4 之间")
	public String getNetIssRegion() {
		return netIssRegion;
	}

	public void setNetIssRegion(String netIssRegion) {
		this.netIssRegion = netIssRegion;
	}
	
	@Length(min=1, max=4, message="net_mcc_group长度必须介于 1 和 4 之间")
	public String getNetMccGroup() {
		return netMccGroup;
	}

	public void setNetMccGroup(String netMccGroup) {
		this.netMccGroup = netMccGroup;
	}
	
	@Length(min=1, max=4, message="net_crdproduct长度必须介于 1 和 4 之间")
	public String getNetCrdproduct() {
		return netCrdproduct;
	}

	public void setNetCrdproduct(String netCrdproduct) {
		this.netCrdproduct = netCrdproduct;
	}
	
	@Length(min=1, max=10, message="net_mrch_id长度必须介于 1 和 10 之间")
	public String getNetMrchId() {
		return netMrchId;
	}

	public void setNetMrchId(String netMrchId) {
		this.netMrchId = netMrchId;
	}
	
	@Length(min=1, max=12, message="net_rrn长度必须介于 1 和 12 之间")
	public String getNetRrn() {
		return netRrn;
	}

	public void setNetRrn(String netRrn) {
		this.netRrn = netRrn;
	}
	
	@Length(min=1, max=32, message="poscap长度必须介于 1 和 32 之间")
	public String getPoscap() {
		return poscap;
	}

	public void setPoscap(String poscap) {
		this.poscap = poscap;
	}
	
	@Length(min=1, max=20, message="Pos地址长度必须介于 1 和 20 之间")
	public String getPoscdic() {
		return poscdic;
	}

	public void setPoscdic(String poscdic) {
		this.poscdic = poscdic;
	}
	
	@Length(min=1, max=40, message="crdacptloc_name长度必须介于 1 和 40 之间")
	public String getCrdacptlocName() {
		return crdacptlocName;
	}

	public void setCrdacptlocName(String crdacptlocName) {
		this.crdacptlocName = crdacptlocName;
	}
	
	@Length(min=1, max=35, message="crdacptloc_street长度必须介于 1 和 35 之间")
	public String getCrdacptlocStreet() {
		return crdacptlocStreet;
	}

	public void setCrdacptlocStreet(String crdacptlocStreet) {
		this.crdacptlocStreet = crdacptlocStreet;
	}
	
	@Length(min=1, max=40, message="crdacptloc_city长度必须介于 1 和 40 之间")
	public String getCrdacptlocCity() {
		return crdacptlocCity;
	}

	public void setCrdacptlocCity(String crdacptlocCity) {
		this.crdacptlocCity = crdacptlocCity;
	}
	
	@Length(min=1, max=10, message="crdacptloc_postcode长度必须介于 1 和 10 之间")
	public String getCrdacptlocPostcode() {
		return crdacptlocPostcode;
	}

	public void setCrdacptlocPostcode(String crdacptlocPostcode) {
		this.crdacptlocPostcode = crdacptlocPostcode;
	}
	
	@Length(min=1, max=3, message="crdacptloc_region长度必须介于 1 和 3 之间")
	public String getCrdacptlocRegion() {
		return crdacptlocRegion;
	}

	public void setCrdacptlocRegion(String crdacptlocRegion) {
		this.crdacptlocRegion = crdacptlocRegion;
	}
	
	@Length(min=1, max=3, message="crdacptloc_country长度必须介于 1 和 3 之间")
	public String getCrdacptlocCountry() {
		return crdacptlocCountry;
	}

	public void setCrdacptlocCountry(String crdacptlocCountry) {
		this.crdacptlocCountry = crdacptlocCountry;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="auth_expdate不能为空")
	public Date getAuthExpdate() {
		return authExpdate;
	}

	public void setAuthExpdate(Date authExpdate) {
		this.authExpdate = authExpdate;
	}
	
	@Length(min=1, max=64, message="verresults长度必须介于 1 和 64 之间")
	public String getVerresults() {
		return verresults;
	}

	public void setVerresults(String verresults) {
		this.verresults = verresults;
	}
	
	@Length(min=1, max=2, message="子处理码长度必须介于 1 和 2 之间")
	public String getSubTxncode() {
		return subTxncode;
	}

	public void setSubTxncode(String subTxncode) {
		this.subTxncode = subTxncode;
	}
	
	public String getBeginDatelocal() {
		return beginDatelocal;
	}

	public void setBeginDatelocal(String beginDatelocal) {
		this.beginDatelocal = beginDatelocal;
	}
	
	public String getEndDatelocal() {
		return endDatelocal;
	}

	public void setEndDatelocal(String endDatelocal) {
		this.endDatelocal = endDatelocal;
	}

	public String getTxnTypeDesc() {
		return txnTypeDesc;
	}

	public void setTxnTypeDesc(String txnTypeDesc) {
		this.txnTypeDesc = txnTypeDesc;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getFatherOrder() {
		return fatherOrder;
	}

	public void setFatherOrder(String fatherOrder) {
		this.fatherOrder = fatherOrder;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSalesPoint() {
		return salesPoint;
	}

	public void setSalesPoint(String salesPoint) {
		this.salesPoint = salesPoint;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getAmtEachCrd() {
		return amtEachCrd;
	}

	public void setAmtEachCrd(String amtEachCrd) {
		this.amtEachCrd = amtEachCrd;
	}

	
		
}