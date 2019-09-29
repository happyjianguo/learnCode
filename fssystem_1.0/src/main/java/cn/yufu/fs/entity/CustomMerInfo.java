package cn.yufu.fs.entity;

public class CustomMerInfo {
    private String batchId;	//批次号ID

    private String merNo;	//商户号

    private String companyName;	//公司名称

    private String tranStartDate;	//消费开始日期

    private String tranEndDate;	//消费结束日期

    private String orderDate;	//下单日期

    private String dealDate;	//处理时间

    private String flag;	//处理状态
    
    private String choiceFlag;  //选择标志 1 商户号 2  公司名称

    private String freeField2;

    private String freeField3;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo == null ? null : merNo.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getTranStartDate() {
        return tranStartDate;
    }

    public void setTranStartDate(String tranStartDate) {
        this.tranStartDate = tranStartDate == null ? null : tranStartDate.trim();
    }

    public String getTranEndDate() {
        return tranEndDate;
    }

    public void setTranEndDate(String tranEndDate) {
        this.tranEndDate = tranEndDate == null ? null : tranEndDate.trim();
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate == null ? null : orderDate.trim();
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate == null ? null : dealDate.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
    
    public String getChoiceFlag() {
        return choiceFlag;
    }

    public void setChoiceFlag(String choiceFlag) {
        this.choiceFlag = choiceFlag == null ? null : choiceFlag.trim();
    }

    public String getFreeField2() {
        return freeField2;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2 == null ? null : freeField2.trim();
    }

    public String getFreeField3() {
        return freeField3;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3 == null ? null : freeField3.trim();
    }
}