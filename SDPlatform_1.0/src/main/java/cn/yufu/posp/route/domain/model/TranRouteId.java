package cn.yufu.posp.route.domain.model;



/**
 * TranRouteId entity. @author MyEclipse Persistence Tools
 */

public class TranRouteId  implements java.io.Serializable {


    // Fields    

     private String tranBit;
     private String bankType;
     private String rcvBankName;
     public String getRcvBankName() {
		return rcvBankName;
	}

	public void setRcvBankName(String rcvBankName) {
		this.rcvBankName = rcvBankName;
	}

	private String bankTypeName;
     public String getBankTypeName() {
		return bankTypeName;
	}

	public void setBankTypeName(String bankTypeName) {
		this.bankTypeName = bankTypeName;
	}

	private String rcvBankId;
     private String rcvHostId;
     private Integer moduleId;
     private String updateOper;
     private String updateDate;
     private String updateTime;


    // Constructors

    /** default constructor */
    public TranRouteId() {
    }

	/** minimal constructor */
    public TranRouteId(String tranBit, String bankType, String rcvBankId, String rcvHostId, Integer moduleId) {
        this.tranBit = tranBit;
        this.bankType = bankType;
        this.rcvBankId = rcvBankId;
        this.rcvHostId = rcvHostId;
        this.moduleId = moduleId;
    }
    
    /** full constructor */
    public TranRouteId(String tranBit, String bankType, String rcvBankId, String rcvHostId, Integer moduleId, String updateOper, String updateDate, String updateTime) {
        this.tranBit = tranBit;
        this.bankType = bankType;
        this.rcvBankId = rcvBankId;
        this.rcvHostId = rcvHostId;
        this.moduleId = moduleId;
        this.updateOper = updateOper;
        this.updateDate = updateDate;
        this.updateTime = updateTime;
    }

   
    // Property accessors

    public String getTranBit() {
        return this.tranBit;
    }
    
    public void setTranBit(String tranBit) {
        this.tranBit = tranBit;
    }

    public String getBankType() {
        return this.bankType;
    }
    
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getRcvBankId() {
        return this.rcvBankId;
    }
    
    public void setRcvBankId(String rcvBankId) {
        this.rcvBankId = rcvBankId;
    }

    public String getRcvHostId() {
        return this.rcvHostId;
    }
    
    public void setRcvHostId(String rcvHostId) {
        this.rcvHostId = rcvHostId;
    }

    public Integer getModuleId() {
        return this.moduleId;
    }
    
    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getUpdateOper() {
        return this.updateOper;
    }
    
    public void setUpdateOper(String updateOper) {
        this.updateOper = updateOper;
    }

    public String getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TranRouteId) ) return false;
		 TranRouteId castOther = ( TranRouteId ) other; 
         
		 return ( (this.getTranBit()==castOther.getTranBit()) || ( this.getTranBit()!=null && castOther.getTranBit()!=null && this.getTranBit().equals(castOther.getTranBit()) ) )
 && ( (this.getBankType()==castOther.getBankType()) || ( this.getBankType()!=null && castOther.getBankType()!=null && this.getBankType().equals(castOther.getBankType()) ) )
 && ( (this.getRcvBankId()==castOther.getRcvBankId()) || ( this.getRcvBankId()!=null && castOther.getRcvBankId()!=null && this.getRcvBankId().equals(castOther.getRcvBankId()) ) )
 && ( (this.getRcvHostId()==castOther.getRcvHostId()) || ( this.getRcvHostId()!=null && castOther.getRcvHostId()!=null && this.getRcvHostId().equals(castOther.getRcvHostId()) ) )
 && ( (this.getModuleId()==castOther.getModuleId()) || ( this.getModuleId()!=null && castOther.getModuleId()!=null && this.getModuleId().equals(castOther.getModuleId()) ) )
 && ( (this.getUpdateOper()==castOther.getUpdateOper()) || ( this.getUpdateOper()!=null && castOther.getUpdateOper()!=null && this.getUpdateOper().equals(castOther.getUpdateOper()) ) )
 && ( (this.getUpdateDate()==castOther.getUpdateDate()) || ( this.getUpdateDate()!=null && castOther.getUpdateDate()!=null && this.getUpdateDate().equals(castOther.getUpdateDate()) ) )
 && ( (this.getUpdateTime()==castOther.getUpdateTime()) || ( this.getUpdateTime()!=null && castOther.getUpdateTime()!=null && this.getUpdateTime().equals(castOther.getUpdateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTranBit() == null ? 0 : this.getTranBit().hashCode() );
         result = 37 * result + ( getBankType() == null ? 0 : this.getBankType().hashCode() );
         result = 37 * result + ( getRcvBankId() == null ? 0 : this.getRcvBankId().hashCode() );
         result = 37 * result + ( getRcvHostId() == null ? 0 : this.getRcvHostId().hashCode() );
         result = 37 * result + ( getModuleId() == null ? 0 : this.getModuleId().hashCode() );
         result = 37 * result + ( getUpdateOper() == null ? 0 : this.getUpdateOper().hashCode() );
         result = 37 * result + ( getUpdateDate() == null ? 0 : this.getUpdateDate().hashCode() );
         result = 37 * result + ( getUpdateTime() == null ? 0 : this.getUpdateTime().hashCode() );
         return result;
   }   





}