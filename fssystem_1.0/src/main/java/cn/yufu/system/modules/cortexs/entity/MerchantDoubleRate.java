package cn.yufu.system.modules.cortexs.entity;

import org.hibernate.validator.constraints.Length;

import cn.yufu.system.common.persistence.DataEntity;

/**
 * 商户双费率Entity
 * @author LLG
 * @version 2017-04-25
 */
public class MerchantDoubleRate extends DataEntity<MerchantDoubleRate> {
	
	private static final long serialVersionUID = 1L;
	private String merchantId;		// 商户编号
	private String cardBin;		// 卡BIN
	private String rate;		// 费率
	
	private String merchantIdLike;//商户号模糊查询
	private String mrchtName;//商户名称
	
	
	public MerchantDoubleRate() {
		super();
	}

	public MerchantDoubleRate(String id){
		super(id);
	}

	@Length(min=1, max=15, message="商户编号长度必须介于 1 和 15 之间")
	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
	@Length(min=0, max=11, message="卡BIN长度必须介于 0 和 11 之间")
	public String getCardBin() {
		return cardBin;
	}

	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}
	
	@Length(min=0, max=64, message="费率长度必须介于 0 和 64 之间")
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getMerchantIdLike() {
		return merchantIdLike;
	}

	public void setMerchantIdLike(String merchantIdLike) {
		this.merchantIdLike = merchantIdLike;
	}

	public String getMrchtName() {
		return mrchtName;
	}

	public void setMrchtName(String mrchtName) {
		this.mrchtName = mrchtName;
	}
	
}