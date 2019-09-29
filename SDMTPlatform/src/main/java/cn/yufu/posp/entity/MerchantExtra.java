package cn.yufu.posp.entity;

public class MerchantExtra {
    private String merchantId;

    private String sndName;

    private String sndAcct;

    private String sndBank;

    private String rcvName;

    private String rcvAcct1;

    private String rcvAcct2;

    private String rcvBank;

    private String mchtStlmFlg;

    private String mchtStlmN;

    private String mchtFeecycle;

    private String mchtFeedayN;

    private String mchtFeemode;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    public String getSndName() {
        return sndName;
    }

    public void setSndName(String sndName) {
        this.sndName = sndName == null ? null : sndName.trim();
    }

    public String getSndAcct() {
        return sndAcct;
    }

    public void setSndAcct(String sndAcct) {
        this.sndAcct = sndAcct == null ? null : sndAcct.trim();
    }

    public String getSndBank() {
        return sndBank;
    }

    public void setSndBank(String sndBank) {
        this.sndBank = sndBank == null ? null : sndBank.trim();
    }

    public String getRcvName() {
        return rcvName;
    }

    public void setRcvName(String rcvName) {
        this.rcvName = rcvName == null ? null : rcvName.trim();
    }

    public String getRcvAcct1() {
        return rcvAcct1;
    }

    public void setRcvAcct1(String rcvAcct1) {
        this.rcvAcct1 = rcvAcct1 == null ? null : rcvAcct1.trim();
    }

    public String getRcvAcct2() {
        return rcvAcct2;
    }

    public void setRcvAcct2(String rcvAcct2) {
        this.rcvAcct2 = rcvAcct2 == null ? null : rcvAcct2.trim();
    }

    public String getRcvBank() {
        return rcvBank;
    }

    public void setRcvBank(String rcvBank) {
        this.rcvBank = rcvBank == null ? null : rcvBank.trim();
    }

    public String getMchtStlmFlg() {
        return mchtStlmFlg;
    }

    public void setMchtStlmFlg(String mchtStlmFlg) {
        this.mchtStlmFlg = mchtStlmFlg == null ? null : mchtStlmFlg.trim();
    }

    public String getMchtStlmN() {
        return mchtStlmN;
    }

    public void setMchtStlmN(String mchtStlmN) {
        this.mchtStlmN = mchtStlmN == null ? null : mchtStlmN.trim();
    }

    public String getMchtFeecycle() {
        return mchtFeecycle;
    }

    public void setMchtFeecycle(String mchtFeecycle) {
        this.mchtFeecycle = mchtFeecycle == null ? null : mchtFeecycle.trim();
    }

    public String getMchtFeedayN() {
        return mchtFeedayN;
    }

    public void setMchtFeedayN(String mchtFeedayN) {
        this.mchtFeedayN = mchtFeedayN == null ? null : mchtFeedayN.trim();
    }

    public String getMchtFeemode() {
        return mchtFeemode;
    }

    public void setMchtFeemode(String mchtFeemode) {
        this.mchtFeemode = mchtFeemode == null ? null : mchtFeemode.trim();
    }
}