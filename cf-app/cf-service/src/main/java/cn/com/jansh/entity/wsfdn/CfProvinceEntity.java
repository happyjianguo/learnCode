package cn.com.jansh.entity.wsfdn;
/**
 * 省份
 * @author duanmuyn
 *
 */
public class CfProvinceEntity {

	/**  */
    private String pno;
    
    /**  */
    private String pname;
    
    /**  */
    private String porder;
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getPno() {
        return this.pno;
    }
     
    /**
     * 设置
     * 
     * @param pno
     *          
     */
    public void setPno(String pno) {
        this.pno = pno;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getPname() {
        return this.pname;
    }
     
    /**
     * 设置
     * 
     * @param pname
     *          
     */
    public void setPname(String pname) {
        this.pname = pname;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getPorder() {
        return this.porder;
    }
     
    /**
     * 设置
     * 
     * @param porder
     *          
     */
    public void setPorder(String porder) {
        this.porder = porder;
    }
    
    @Override
	public String toString() {
		return "CfProvinceEntity [pno=" + pno + ", pname=" + pname + ", porder=" + porder + "]";
	}
}