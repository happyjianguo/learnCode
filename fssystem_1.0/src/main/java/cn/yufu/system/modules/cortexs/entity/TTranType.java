package cn.yufu.system.modules.cortexs.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.yufu.system.common.persistence.DataEntity;

/**
 * TLOG交易类型Entity
 * @author LLG
 * @version 2016-08-24
 */
public class TTranType extends DataEntity<TTranType> {
	
	private static final long serialVersionUID = 1L;
	private String tranTypeDesc;		// 交易类型描述
	private Integer txncode;		// 交易码
	private String subTxncode;		// 子处理码
	private String amtFlag;		// 金额符号
	private String showFlag;		// 是否需要显示
	private String comments;		// 备注
	private Integer fncode;			//功能码
	
	
	public TTranType() {
		super();
	}

	public TTranType(String id){
		super(id);
	}

	@Length(min=1, max=200, message="交易类型描述长度必须介于 1 和 200 之间")
	public String getTranTypeDesc() {
		return tranTypeDesc;
	}

	public void setTranTypeDesc(String tranTypeDesc) {
		this.tranTypeDesc = tranTypeDesc;
	}
	
	@NotNull(message="交易码不能为空")
	public Integer getTxncode() {
		return txncode;
	}

	public void setTxncode(Integer txncode) {
		this.txncode = txncode;
	}
	
	@Length(min=1, max=2, message="子处理码长度必须介于 1 和 2 之间")
	public String getSubTxncode() {
		return subTxncode;
	}

	public void setSubTxncode(String subTxncode) {
		this.subTxncode = subTxncode;
	}
	
	@Length(min=1, max=2, message="金额符号长度必须介于 1 和 2 之间")
	public String getAmtFlag() {
		return amtFlag;
	}

	public void setAmtFlag(String amtFlag) {
		this.amtFlag = amtFlag;
	}
	
	@Length(min=1, max=1, message="是否需要显示长度必须介于 1 和 1 之间")
	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	
	@Length(min=0, max=200, message="备注长度必须介于 0 和 200 之间")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getFncode() {
		return fncode;
	}

	public void setFncode(Integer fncode) {
		this.fncode = fncode;
	}
	
}