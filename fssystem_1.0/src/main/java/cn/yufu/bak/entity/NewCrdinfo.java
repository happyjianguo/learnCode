package cn.yufu.bak.entity;

import java.util.Date;

public class NewCrdinfo {
    private Long id;

    private Long vernoCtx;

    private String pan;

    private String custName;

    private String idNum;

    private String mailBox;

    private String cellPhone;

    private String isCheckCellphone;

    private String isOnlineRedeem;

    private String bankName;

    private String branchName;

    private String bankPan;

    private String cardAcceptorName;

    private String cardAcceptorId;

    private String reserved1;

    private String reserved2;

    private String reserved3;  //实名日期
    
    private String begainTrueName;  //开始实名日期
    
    private String endTrueName;  //结束实名日期

    private String userName;

    private Date dateBirth;

    private String idType;

    private String sex;

    private String address;

    private String postcode;

    private String married;

    private String province;

    private String city;

    private String fax;

    private String phone;

    private String degree;

    private String work;
    
    //民生订单、会员卡BIN补充字段
    private String operator; //操作员
    
    private String indentNumber;  //订单号
    
    private String memberName;  //所属会员名称
    
    private String memberCardBin;  //所属会员对应卡BIN
    
    private String panStart;
    
    private String panEnd;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVernoCtx() {
        return vernoCtx;
    }

    public void setVernoCtx(Long vernoCtx) {
        this.vernoCtx = vernoCtx;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan == null ? null : pan.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum == null ? null : idNum.trim();
    }

    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox == null ? null : mailBox.trim();
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone == null ? null : cellPhone.trim();
    }

    public String getIsCheckCellphone() {
        return isCheckCellphone;
    }

    public void setIsCheckCellphone(String isCheckCellphone) {
        this.isCheckCellphone = isCheckCellphone == null ? null : isCheckCellphone.trim();
    }

    public String getIsOnlineRedeem() {
        return isOnlineRedeem;
    }

    public void setIsOnlineRedeem(String isOnlineRedeem) {
        this.isOnlineRedeem = isOnlineRedeem == null ? null : isOnlineRedeem.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
    }

    public String getBankPan() {
        return bankPan;
    }

    public void setBankPan(String bankPan) {
        this.bankPan = bankPan == null ? null : bankPan.trim();
    }

    public String getCardAcceptorName() {
        return cardAcceptorName;
    }

    public void setCardAcceptorName(String cardAcceptorName) {
        this.cardAcceptorName = cardAcceptorName == null ? null : cardAcceptorName.trim();
    }

    public String getCardAcceptorId() {
        return cardAcceptorId;
    }

    public void setCardAcceptorId(String cardAcceptorId) {
        this.cardAcceptorId = cardAcceptorId == null ? null : cardAcceptorId.trim();
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2 == null ? null : reserved2.trim();
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3 == null ? null : reserved3.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType == null ? null : idType.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married == null ? null : married.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work == null ? null : work.trim();
    }

	public String getBegainTrueName() {
		return begainTrueName;
	}

	public void setBegainTrueName(String begainTrueName) {
		this.begainTrueName = begainTrueName;
	}

	public String getEndTrueName() {
		return endTrueName;
	}

	public void setEndTrueName(String endTrueName) {
		this.endTrueName = endTrueName;
	}

	public String getPanStart() {
		return panStart;
	}

	public void setPanStart(String panStart) {
		this.panStart = panStart;
	}

	public String getPanEnd() {
		return panEnd;
	}

	public void setPanEnd(String panEnd) {
		this.panEnd = panEnd;
	}

	public String getIndentNumber() {
		return indentNumber;
	}

	public void setIndentNumber(String indentNumber) {
		this.indentNumber = (indentNumber == null || indentNumber.trim() == "") ? null : indentNumber.trim();
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = (memberName == null || memberName.trim() == "") ? null : memberName.trim();
	}

	public String getMemberCardBin() {
		return memberCardBin;
	}

	public void setMemberCardBin(String memberCardBin) {
		this.memberCardBin = (memberCardBin == null || memberCardBin.trim() == "") ? null : memberCardBin.trim();
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = (operator == null || operator.trim() == "") ? null : operator.trim();
	}
    
}