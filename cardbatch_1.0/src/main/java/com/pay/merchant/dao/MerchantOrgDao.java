package com.pay.merchant.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pay.merchant.bean.MerchantOrgBean;
import com.pay.merchant.struts.form.MerchantOrgForm;
import com.pay.util.DbExec;
import com.pay.util.PageBean;
import com.pay.util.StringUtils;

public class MerchantOrgDao {
	private static final Logger logger = Logger.getLogger(MerchantOrgDao.class);
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
	public int getCount(MerchantOrgForm form, HttpSession session) {
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql = " select count(*) numbers  from merchant_org";
		try {
			Vector vector = DbExec.execQuery(sql, param);
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

	/**
	 * 根据搜索条件返回指定的list
	 * 
	 * @param pageBean
	 * @param form
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<MerchantOrgBean> getMerchantOrgList(PageBean pageBean,
			MerchantOrgForm form) {
		List<String> param = new ArrayList<String>();
		List<MerchantOrgBean> beans = new ArrayList<MerchantOrgBean>();
		String sql = " SELECT m.org_id AS orgId, m.org_name AS orgName,m.org_ratio AS orgRatio,"
				+"m.org_single_amt AS orgSingleAmt,m.org_stat AS orgStat,"
				+"m.create_by AS createBy,m.create_date AS createDate,"
				+"m.update_by AS updateBy,m.update_date AS updateDate"
				+ " FROM merchant_org m WHERE 1=1 ";
		if (StringUtils.isNotEmptyStr(form.getOrgName())) {
			sql += (" AND m.org_name LIKE  '%'||'"+ form.getOrgName() +"'||'%' ORDER BY m.org_id DESC  ");
		}
		if (StringUtils.isNotEmptyStr(form.getOrgStat())) {
			sql += (" AND m.org_stat = '"+ form.getOrgStat() +"' ");
		}
		sql += "  ORDER BY m.org_id DESC ";
		Vector vector = null;
		try {
			if (pageBean != null) {
				vector = DbExec.execQuery(sql, param, pageBean.getStart(),
						pageBean.getPageSize());
			} else {
				vector = DbExec.execQuery(sql, param);
			}
			MerchantOrgBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = getMerchantOrgBean(map);
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
	
	/**
	 * 获取待修改的商户机构信息
	 * 
	 * @param orgId
	 * @return
	 */
	public MerchantOrgBean getMerchantOrgById(String orgId) {
		String sql = " SELECT m.org_id AS orgId, m.org_name AS orgName,m.org_ratio AS orgRatio,"
				+"m.org_single_amt AS orgSingleAmt,m.org_stat AS orgStat,"
				+"m.create_by AS createBy,m.create_date AS createDate,"
				+"m.update_by AS updateBy,m.update_date AS updateDate"
				+ " FROM merchant_org m WHERE  m.org_id = '" + orgId +"'";
		MerchantOrgBean merchantOrgBean = null;
		try {
			Vector vector = DbExec.execQuery(sql, null);
			if (vector != null && !vector.isEmpty()) {
				merchantOrgBean = getMerchantOrgBean((HashMap) vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception", e);
		}
		return merchantOrgBean;
	}
	
	/**
	 * 修改商户机构信息
	 * 
	 * @param merchantOrgBean
	 * @return
	 */
	public int updateMerchantOrgInfo(MerchantOrgBean merchantOrgBean){
		String sql = " update merchant_org set ";
		String updateSql = getUpdateSql(merchantOrgBean, sql);
		try {
			result = DbExec.execUpdate(updateSql);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updateMerchantOrgInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updateMerchantOrgInfo", e);
			result = -1;
		}
		return result;
	}
	
	/**
	 * 商户机构信息添加
	 * 
	 * @param merchantOrgBean
	 * @return
	 */
	public int addMerchantOrgInfo(MerchantOrgBean merchantOrgBean) {
		String sql = " INSERT INTO merchant_org (org_id,org_name,org_ratio,"
				+ "org_single_amt,org_stat,create_by,create_date,update_by,"
				+ "update_date) values(SEQ_MERCHANT_ORG.Nextval,'"
				+ merchantOrgBean.getOrgName() + "','" + merchantOrgBean.getOrgRatio() + "','"
				+ merchantOrgBean.getOrgSingleAmt() + "','" + merchantOrgBean.getOrgStat() + "','" 
				+ merchantOrgBean.getCreateBy() + "','" + merchantOrgBean.getCreateDate() + "','" 
				+ merchantOrgBean.getUpdateBy() + "','" + merchantOrgBean.getUpdateDate() + "') ";
		try {
			result = DbExec.execUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: addMerchantOrgInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: addMerchantOrgInfo", e);
			result = -1;
		}
		return result;
	}	
	
	/**
	 * 根据更新条件返回 相应的更新语句
	 * 
	 * @param form
	 * @param sql
	 * @param param
	 * @return
	 */
	private String getUpdateSql(MerchantOrgBean merchantOrgBean, String sql) {
		if (StringUtils.isNotEmptyStr(merchantOrgBean.getOrgName())) {
			sql += " org_name = '" + merchantOrgBean.getOrgName() + "',"; 
		}
		if (StringUtils.isNotEmptyStr(merchantOrgBean.getOrgRatio())) {
			sql += " org_ratio = '" + merchantOrgBean.getOrgRatio() + "',"; 
		}
		if (StringUtils.isNotEmptyStr(merchantOrgBean.getOrgSingleAmt())) {
			sql += " org_single_amt = '" + merchantOrgBean.getOrgSingleAmt() + "',"; 
		}
		if (StringUtils.isNotEmptyStr(merchantOrgBean.getOrgStat())) {
			sql += " org_stat = '" + merchantOrgBean.getOrgStat() + "',"; 
		}
		sql += " update_by = '" + merchantOrgBean.getUpdateBy() + "',"; 
		sql += " update_date = '" + merchantOrgBean.getUpdateDate() + "'"; 
		
		if (StringUtils.isNotEmptyStr(merchantOrgBean.getOrgId())) {
			sql += " WHERE org_id = '" + merchantOrgBean.getOrgId() + "'";  
		}
		return sql;
	}
	
	private MerchantOrgBean getMerchantOrgBean(HashMap record) throws Exception{
		MerchantOrgBean merchantOrgBean = new MerchantOrgBean();
		for (Object val : record.entrySet()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>)val;
			if ("ORGID".equals(entry.getKey().toUpperCase())) {
				merchantOrgBean.setOrgId(entry.getValue());
				continue;
			}
			if ("ORGNAME".equals(entry.getKey().toUpperCase())) {
				merchantOrgBean.setOrgName(entry.getValue());
				continue;
			}
			if ("ORGRATIO".equals(entry.getKey().toUpperCase())) {
				String value = entry.getValue();
				double parseDouble = Double.parseDouble(value);
				merchantOrgBean.setOrgRatio((int)(parseDouble*100)+"%");
				continue;
			}
			if ("ORGSINGLEAMT".equals(entry.getKey().toUpperCase())) {
				merchantOrgBean.setOrgSingleAmt(entry.getValue());
				continue;
			}
			if ("ORGSTAT".equals(entry.getKey().toUpperCase())) {
				merchantOrgBean.setOrgStat(entry.getValue());
				continue;
			}
			if ("CREATEBY".equals(entry.getKey().toUpperCase())) {
				merchantOrgBean.setCreateBy(entry.getValue());
				continue;
			}
			if ("CREATEDATE".equals(entry.getKey().toUpperCase())) {
				merchantOrgBean.setCreateDate(entry.getValue());
				continue;
			}
			if ("UPDATEBY".equals(entry.getKey().toUpperCase())) {
				merchantOrgBean.setUpdateBy(entry.getValue());
				continue;
			}
			if ("UPDATEDATE".equals(entry.getKey().toUpperCase())) {
				merchantOrgBean.setUpdateDate(entry.getValue());
				continue;
			}
		}
		return merchantOrgBean;
	}
	
}
