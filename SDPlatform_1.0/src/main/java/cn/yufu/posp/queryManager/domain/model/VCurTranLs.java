package cn.yufu.posp.queryManager.domain.model;

import java.math.BigDecimal;

/**
 * VCurTranLs entity. @author MyEclipse Persistence Tools
 */

public class VCurTranLs implements java.io.Serializable {

	// Fields

	private String merchantId;
	private String merchantName;
	private BigDecimal amtTotal;
	private BigDecimal purchase;
	private BigDecimal purchaseTotal;
	private BigDecimal refund;
	private BigDecimal refundTotal;
	private BigDecimal posVoid;
	private BigDecimal posVoidTotal;
	private BigDecimal sumls;

	// Constructors

	/** default constructor */
	public VCurTranLs() {
	}

	/** full constructor */
	public VCurTranLs(String merchantId, String merchantName,
			BigDecimal amtTotal, BigDecimal purchase, BigDecimal purchaseTotal,
			BigDecimal refund, BigDecimal refundTotal, BigDecimal posVoid,
			BigDecimal posVoidTotal, BigDecimal sumls) {
		this.merchantId = merchantId;
		this.merchantName = merchantName;
		this.amtTotal = amtTotal;
		this.purchase = purchase;
		this.purchaseTotal = purchaseTotal;
		this.refund = refund;
		this.refundTotal = refundTotal;
		this.posVoid = posVoid;
		this.posVoidTotal = posVoidTotal;
		this.sumls = sumls;
	}

	// Property accessors

	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return this.merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public BigDecimal getAmtTotal() {
		return this.amtTotal;
	}

	public void setAmtTotal(BigDecimal amtTotal) {
		this.amtTotal = amtTotal;
	}

	public BigDecimal getPurchase() {
		return this.purchase;
	}

	public void setPurchase(BigDecimal purchase) {
		this.purchase = purchase;
	}

	public BigDecimal getPurchaseTotal() {
		return this.purchaseTotal;
	}

	public void setPurchaseTotal(BigDecimal purchaseTotal) {
		this.purchaseTotal = purchaseTotal;
	}

	public BigDecimal getRefund() {
		return this.refund;
	}

	public void setRefund(BigDecimal refund) {
		this.refund = refund;
	}

	public BigDecimal getRefundTotal() {
		return this.refundTotal;
	}

	public void setRefundTotal(BigDecimal refundTotal) {
		this.refundTotal = refundTotal;
	}

	public BigDecimal getPosVoid() {
		return this.posVoid;
	}

	public void setPosVoid(BigDecimal posVoid) {
		this.posVoid = posVoid;
	}

	public BigDecimal getPosVoidTotal() {
		return this.posVoidTotal;
	}

	public void setPosVoidTotal(BigDecimal posVoidTotal) {
		this.posVoidTotal = posVoidTotal;
	}

	public BigDecimal getSumls() {
		return this.sumls;
	}

	public void setSumls(BigDecimal sumls) {
		this.sumls = sumls;
	}

}