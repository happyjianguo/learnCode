/**
 *包名:com.pay.merchant.dao
 *描述:package com.pay.merchant.dao;
 */
package com.pay.merchant.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pay.merchant.bean.TCardMerInfoBean;
import com.pay.merchant.struts.action.TCardMerInfoAction;
import com.pay.merchant.struts.form.TCardMerInfoForm;
import com.pay.util.DbExec;
import com.pay.util.PageBean;
import com.pay.util.StringUtils;

/**
 * TCardMerInfoDao.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年6月25日
 */
public class TCardMerInfoDao {
	private static final Logger logger = Logger.getLogger(TCardMerInfoDao.class);
	private int result = 0;

	/**
	 * 查询分页总条数
	 * 
	 * @param form
	 * @param session
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public int getCount(TCardMerInfoForm form, HttpSession session) {
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql = " select count(*) numbers  from t_card_mer_info where 1=1";
		if (StringUtils.isNotEmptyStr(form.getCardNo())) {
			sql += (" AND card_no  = '"+ form.getCardNo() +"' ");
		}
		if (StringUtils.isNotEmptyStr(form.getMerNo())) {
			sql += (" AND mer_no = '"+ form.getMerNo() +"' ");
		}
		try {
			Vector vector = DbExec.execQueryFkqz(sql, param);
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = Integer.parseInt(((String) map.get("numbers")).trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCount", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SQLException--getCount", e);
		}
		return count;
	}

	public int delTCardMerInfo(TCardMerInfoForm tCardMerInfoForm, HttpSession session) {
		String sql = " DELETE FROM t_card_mer_info WHERE card_no = "+tCardMerInfoForm.getCardNo()+"  AND mer_no = "+tCardMerInfoForm.getMerNo();
		try {
			result = DbExec.execUpdateFkqz(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updSysParameter", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updSysParameter", e);
			result = -1;
		}
		return result;
	}

	public int addTCardMerInfo(TCardMerInfoBean tCardMerInfoBean) {
		String sql = " insert into t_card_mer_info VALUES (?,?,?,?) ";
		List<Object> param = new ArrayList<Object>();
		String startno = tCardMerInfoBean.getStartcardNo();
		String endno = tCardMerInfoBean.getEndcardNo();
		String merno = tCardMerInfoBean.getMerNo();
		String adduser = tCardMerInfoBean.getAddUser();
		String time = tCardMerInfoBean.getTimeStamp();
		//判断起始卡号是否合法
		if(TCardMerInfoAction.isInteger(startno) && TCardMerInfoAction.isInteger(endno)){
			BigDecimal sno = new BigDecimal(startno); 
			BigDecimal eno = new BigDecimal(endno); 
			//判断结束卡号是否大于开始卡号
			if(eno.compareTo(sno) >= 0){
				try {
					int smatch = eno.subtract(sno).intValue()+1;
					if(smatch == 0){
						Object[] obj = {sno.toString(),merno,adduser,time,}; 
						param.add(obj);
					}else{
						for(int i=0; i<smatch;i++){
							Object[] obj = {sno.add(new BigDecimal(i)).toString(),merno,adduser,time,}; 
							param.add(obj);
						}
					}
					result = DbExec.execBatchFkqz(sql,param);
				} catch (SQLException e) {
					e.printStackTrace();
					logger.error("SQLException: insert", e);
					result = -1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Exception: insert", e);
					result = -1;
				}
			}else{
				logger.error("Exception: insert--开始卡号小于结束卡号");
				result = -1;
			}
		}else{
			logger.error("Exception: insert--起始卡号格式有误");
			result = -1;
		}
		return result;
	}
	/**
	 * 根据搜索条件返回指定的list
	 * 
	 * @param pageBean
	 * @param form
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<TCardMerInfoBean> getTCardMerInfoList(PageBean pageBean,
			TCardMerInfoForm form) {
		List<String> param = new ArrayList<String>();
		List<TCardMerInfoBean> beans = new ArrayList<TCardMerInfoBean>();
		String sql = " select t.card_no cardNo,t.mer_no merNo,t.add_user addUser,t.time_stamp timeStamp from t_card_mer_info t where 1=1";
		if (StringUtils.isNotEmptyStr(form.getCardNo())) {
			sql += (" AND t.card_no  = '"+ form.getCardNo() +"' ");
		}
		if (StringUtils.isNotEmptyStr(form.getMerNo())) {
			sql += (" AND t.mer_no = '"+ form.getMerNo() +"' ");
		}
		if (StringUtils.isNotEmptyStr(form.getStartcardNo()) && StringUtils.isNotEmptyStr(form.getEndcardNo())) {
			sql += (" AND t.card_no BETWEEN '"+ form.getStartcardNo() +"' AND '"+ form.getEndcardNo() +"' ");
		}
		sql += "  ORDER BY t.time_stamp DESC ";
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execQueryFkqz(sql, param, pageBean.getStart(),
						pageBean.getPageSize());
			} else {
				vector = DbExec.execQueryFkqz(sql, param);
			}
			TCardMerInfoBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = getTCardMerInfoBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getMerchantOrgList", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getMerchantOrgList", e);
		}
		return beans;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private TCardMerInfoBean getTCardMerInfoBean(HashMap record) throws Exception{
		TCardMerInfoBean merchantOrgBean = new TCardMerInfoBean();
		for (Object val : record.entrySet()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>)val;
			if ("CARDNO".equals(entry.getKey().toUpperCase())) {
				merchantOrgBean.setCardNo(entry.getValue());
				continue;
			}
			if ("MERNO".equals(entry.getKey().toUpperCase())) {
				merchantOrgBean.setMerNo(entry.getValue());
				continue;
			}
			if ("ADDUSER".equals(entry.getKey().toUpperCase())) {
				merchantOrgBean.setAddUser(entry.getValue());
				continue;
			}
			if ("TIMESTAMP".equals(entry.getKey().toUpperCase())) {
				merchantOrgBean.setTimeStamp(entry.getValue());
				continue;
			}
		}
		return merchantOrgBean;
	}
	/**
	 * 判断此卡段在卡系统中的卡数量
	 * @param startno
	 * @param endno
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int getTCardCount(String startno, String endno) {
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql = " select count(t.pan) numbers from crddet t where t.pan between '"+ startno +"' || '0' and '"+ endno +"' || '9'";
		try {
			Vector vector = DbExec.execQueryListBak(sql, param);
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = Integer.parseInt(((String) map.get("numbers")).trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCount", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SQLException--getCount", e);
		}
		return count;
	}
}
