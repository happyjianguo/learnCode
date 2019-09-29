package com.pay.batch.opencard.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.pay.util.RecordMethod;

public class OpenCardBean {
	
	private String id;
	private String txncode;
	private String time;
	private String stan;
	private String pan_start;
	private String pan_end;
	private String pan_count;
	private String amt_each_crd;
	private String father_order;
	private String children_order;
	private String pay_type;
	private String pay_desc;
	private String payer_name;
	private String sales_point;	//售卡点
	private String area;
	private String operator;
	private String summary;
	private String mrcht_id;
	private String isopen_flag;
	private String batch_stat;
	private String acct_period;
	private String descr_u;
	private String descr_t;
	private String descr;
	
	private String org_f_order;
	private String org_c_order;
	private String curtxn;		
	private String amttxn;		
	private String rateset;		
	private String currbill;	
	private String crdproduct;	
	private String is_enablement;		//选择销售点后可对其编辑是否开卡，默认是“是:1,否:0”
	
	private String pay_type_desc;
	
	
	private String verno_ctx;
	private String reserved1;
	private String reserved2;
	private String reserved3;
	
	private String startdate;
	private String enddate;
	private String start_period;
	private String end_period;
	
	private String city_no;
	private String province;
	private String zone;
	
	private String descrStartDate;	//审批日期开始
	private String descrEndDate;	//审批日期结束
	
	public String getPay_type_desc() {
		return pay_type_desc;
	}

	public void setPay_type_desc(String payTypeDesc) {
		pay_type_desc = payTypeDesc;
	}

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

	public String getRateset() {
		return rateset;
	}

	public void setRateset(String rateset) {
		this.rateset = rateset;
	}

	public String getCurrbill() {
		return currbill;
	}

	public void setCurrbill(String currbill) {
		this.currbill = currbill;
	}

	public String getCrdproduct() {
		return crdproduct;
	}

	public void setCrdproduct(String crdproduct) {
		this.crdproduct = crdproduct;
	}

	public String getCity_no() {
		return city_no;
	}

	public void setCity_no(String city_no) {
		this.city_no = city_no;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	
	
	
	public String getDescrStartDate() {
		return descrStartDate;
	}

	public void setDescrStartDate(String descrStartDate) {
		this.descrStartDate = descrStartDate;
	}

	public String getDescrEndDate() {
		return descrEndDate;
	}

	public void setDescrEndDate(String descrEndDate) {
		this.descrEndDate = descrEndDate;
	}

	public OpenCardBean() {

	}

	public OpenCardBean(HashMap record) {
		// 利用java反射机制为javabean赋值
		RecordMethod recordMethod = new RecordMethod(record,
				OpenCardBean.class, this);
		recordMethod.recordset();

	}

	public String getStart_period() {
		return start_period;
	}

	public void setStart_period(String start_period) {
		this.start_period = start_period;
	}

	public String getEnd_period() {
		return end_period;
	}

	public void setEnd_period(String end_period) {
		this.end_period = end_period;
	}

	public String getAcct_period() {
		return acct_period;
	}

	public void setAcct_period(String acct_period) {
		this.acct_period = acct_period;
	}

	public String getVerno_ctx() {
		return verno_ctx;
	}

	public void setVerno_ctx(String verno_ctx) {
		this.verno_ctx = verno_ctx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTxncode() {
		return txncode;
	}

	public void setTxncode(String txncode) {
		this.txncode = txncode;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStan() {
		return stan;
	}

	public void setStan(String stan) {
		this.stan = stan;
	}

	public String getPan_start() {
		return pan_start;
	}

	public void setPan_start(String pan_start) {
		this.pan_start = pan_start;
	}

	public String getPan_end() {
		return pan_end;
	}

	public void setPan_end(String pan_end) {
		this.pan_end = pan_end;
	}

	public String getPan_count() {
		return pan_count;
	}

	public void setPan_count(String pan_count) {
		this.pan_count = pan_count;
	}

	public String getAmt_each_crd() {
		return amt_each_crd;
	}

	public void setAmt_each_crd(String amt_each_crd) {
		this.amt_each_crd = amt_each_crd;
	}

	public String getFather_order() {
		return father_order;
	}

	public void setFather_order(String father_order) {
		this.father_order = father_order;
	}

	public String getChildren_order() {
		return children_order;
	}

	public void setChildren_order(String children_order) {
		this.children_order = children_order;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getPay_desc() {
		return pay_desc;
	}

	public void setPay_desc(String pay_desc) {
		this.pay_desc = pay_desc;
	}

	public String getPayer_name() {
		return payer_name;
	}

	public void setPayer_name(String payer_name) {
		this.payer_name = payer_name;
	}

	public String getSales_point() {
		return sales_point;
	}

	public void setSales_point(String sales_point) {
//		if(sales_point.contains(",")){
//			this.sales_point = sales_point.split(",", -1)[1];
//			if (sales_point.split(",", -1).length > 1)
//				this.sales_point = sales_point.split(",", -1)[1];
//			else
//				this.sales_point = sales_point;
//		}else{
//			this.sales_point = sales_point;
//		}
		this.sales_point = sales_point;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
//		if (operator.contains(",") && operator.split(",", -1).length > 1)
//			this.operator = operator.split(",", -1)[1];
//		else
			this.operator = operator;

	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getMrcht_id() {
		return mrcht_id;
	}

	public void setMrcht_id(String mrcht_id) {
		this.mrcht_id = mrcht_id;
	}

	public String getIsopen_flag() {
		return isopen_flag;
	}

	public void setIsopen_flag(String isopen_flag) {
		this.isopen_flag = isopen_flag;
	}

	public String getBatch_stat() {
		return batch_stat;
	}

	public void setBatch_stat(String batch_stat) {
		this.batch_stat = batch_stat;
	}

	public String getOrg_f_order() {
		return org_f_order;
	}

	public void setOrg_f_order(String org_f_order) {
		this.org_f_order = org_f_order;
	}

	public String getOrg_c_order() {
		return org_c_order;
	}

	public void setOrg_c_order(String org_c_order) {
		this.org_c_order = org_c_order;
	}

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getReserved3() {
		return reserved3;
	}

	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
		if (descr.trim().length()>0&&descr.contains(",")) {
			
			this.descr_u = descr.split(",", -1)[0];
			this.descr_t = descr.split(",", -1)[1];
			
		}
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	

	public String getIs_enablement() {
		return is_enablement;
	}

	public void setIs_enablement(String is_enablement) {
		this.is_enablement = is_enablement;
	}

	public String getDescr_u() {
		return descr_u;
	}

	public void setDescr_u(String descr_u) {
		//this.descr_u = descr_u;
	}

	public String getDescr_t() {
		return descr_t;
	}

	public void setDescr_t(String descr_t) {
		//this.descr_t = descr_t;
	}

	public static void main(String argsv[]) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {

		/*
		 * HashMap record = new HashMap(); record.put("id", "1234"); Class[]
		 * argsClass = new Class[1]; argsClass[0] = "id".getClass(); Method
		 * method = HashMap.class.getMethod("get",new Class[] { Object.class });
		 * String id = (String) method.invoke(record, "id");
		 * System.out.println(id); Field[]
		 * field=OpenCardBean.class.getDeclaredFields(); OpenCardBean bean=new
		 * OpenCardBean(); String str2=""; for(Field f:field){ String str =
		 * (String) method.invoke(record, f.getName());
		 * setGetMethod(OpenCardBean.class,f.getName()).invoke(bean, str);
		 * 
		 * Method m=getGetMethod(OpenCardBean.class,f.getName());
		 * str2=(String)m.invoke(bean);
		 * System.out.println("获取全部公共域的方法:"+f.toString
		 * ()+"--"+f.getName()+"--"+str+"==="+str2+"---"+bean.getId()); }
		 */
		// OpenCardBean bean = new OpenCardBean();

		// Field[] field2=HashMap.class.getDeclaredFields();
		// for(Field f:field2){
		// System.out.println("获取全部域的方法(含私有域):"+f.toString());
		// }
		// Method[] method=HashMap.class.getMethods();
		// for(Method m:method){
		// System.out.println("获取全部公共方法的方法:"+m.toString());
		// }
		HashMap record = new HashMap();
		record.put("id", "id123");
		record.put("descr", "descr123");
		record.put("reserved3", "reserved3123");
		OpenCardBean bean = new OpenCardBean(record);
		System.out.println("获取全部公共方法的方法:" + bean.getId());
		System.out.println("获取全部公共方法的方法:" + bean.getDescr());
		System.out.println("获取全部公共方法的方法:" + bean.getReserved3());

	}
}
