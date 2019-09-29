package cn.yufu.system.modules.cortexs.entity;

import cn.yufu.system.common.persistence.DataEntity;
import cn.yufu.system.common.utils.excel.annotation.ExcelField;

public class CrdfeeRuleCkjf extends DataEntity<CrdfeeRuleCkjf>{
	private static final long serialVersionUID = 727659225257968301L;

    private String iid;			//卡BIN

    private String usemonth;	//有效期月数

    private String acctype4;	//04账户（购物返积分）有效标识  1有效 0无效   

    private String acctype9;	//09账户（联名卡送积分）有效标识   1有效  0无效  

    private String flag;		//有效标识 1 有效  0 无效

    private String inserttime;	//时间

    private String updattime;	//时间

    private String oper;		//操作者

    private String reserved1;

    private String reserved3;

    private String reserved2;

    @ExcelField(title="积分卡BIN", align=2, sort=10)	
    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid == null ? null : iid.trim();
    }

    @ExcelField(title="有效期(月数)", align=2, sort=20)	
    public String getUsemonth() {
        return usemonth;
    }

    public void setUsemonth(String usemonth) {
        this.usemonth = usemonth == null ? null : usemonth.trim();
    }

    @ExcelField(title="购物返积分", align=2, sort=50, dictType="IS_EFFECTIVE")	
    public String getAcctype4() {
        return acctype4;
    }

    public void setAcctype4(String acctype4) {
        this.acctype4 = acctype4 == null ? null : acctype4.trim();
    }

    @ExcelField(title="联名卡送积分", align=2, sort=60, dictType="IS_EFFECTIVE")	
    public String getAcctype9() {
        return acctype9;
    }

    public void setAcctype9(String acctype9) {
        this.acctype9 = acctype9 == null ? null : acctype9.trim();
    }

    @ExcelField(title="积分卡BIN是否生效", align=2, sort=100, dictType="IS_EFFECTIVE")	
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime == null ? null : inserttime.trim();
    }

    public String getUpdattime() {
        return updattime;
    }

    public void setUpdattime(String updattime) {
        this.updattime = updattime == null ? null : updattime.trim();
    }

    @ExcelField(title="最后操作者", align=2, sort=300)	
    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper == null ? null : oper.trim();
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3 == null ? null : reserved3.trim();
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2 == null ? null : reserved2.trim();
    }
}