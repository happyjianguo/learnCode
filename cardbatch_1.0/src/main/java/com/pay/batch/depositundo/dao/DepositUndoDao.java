/**
 *包名:com.pay.batch.depositundo.dao
 *描述:package com.pay.batch.depositundo.dao;
 */
package com.pay.batch.depositundo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.batch.depositundo.bean.DepositUndoBean;
import com.pay.batch.depositundo.struts.form.DepositUndoForm;
import com.pay.userCrdproduct.bean.UserCrdproductBean;
import com.pay.userCrdproduct.dao.UserCrdproductDao;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

/**
 * DepositUndoDao.java 版权所有(C) 2017 裕福控股有限公司 创建:gll 时间:2017年9月4日 描述:TODO
 */
public class DepositUndoDao {

	private static final Logger logger = Logger.getLogger(DepositUndoDao.class);
	private String sql = "";
	private int result = 0;

	public List<DepositUndoBean> getDepositList(PageBean pageBean, DepositUndoForm querybean, String user_code) {
		List<DepositUndoBean> list = new ArrayList<DepositUndoBean>();
		// 获取用户对应卡产品str
		String cpIn = "";
		String sqlStr = "";
		if (user_code != null && !"".equals(user_code)) {
			UserCrdproductDao userCrdproductDao = new UserCrdproductDao();
			UserCrdproductBean ucBean = userCrdproductDao.getUserCrdproductByID(user_code);
			String cpStr = ucBean == null ? "" : ucBean.getCrdproduct();
			if (!"".equals(cpStr)) {
				if (cpStr.contains(",")) {
					cpIn = cpStr.replaceAll(",", "','");
				} else {
					cpIn = cpStr;
				}
				sqlStr = " and crdproduct in ('" + cpIn + "')";
			}
		}
		sql = "select verno_ctx, id, txnsrc, txncode, time, stan, pan_start, pan_end, pan_count, amt_each_crd, father_order, children_order, org_f_order, org_c_order, acct_period, pay_type, pay_desc, payer_name, substr(sales_point,instr(sales_point,',')+1) as sales_point, area, id_type, id_number, cell_phone, phone, address, cashin_type, ispaid, summary, substr(operator,instr(operator,',')+1) as operator, mrcht_id, batch_stat, reserved1, reserved2, reserved3, descr, currbill, amttxn, rateset, crdproduct, curtxn from cashinbatch where 1=1 ";
		sql = sql + sqlStr;

		try {
			Vector vector = null;
			sql += sqlparm(querybean);
			sql += " order by id desc";
			vector = DbExec.execQuery(sql, pageBean.getStart(), pageBean.getPageSize());
			if (vector != null && !vector.isEmpty()) {
				for (int i = 0; i < vector.size(); i++) {
					DepositUndoBean bean = new DepositUndoBean((HashMap) vector.get(i));
					list.add(bean);
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;

	}

	public String getSumAmt(DepositUndoForm querybean, String user_code) {

		// 获取用户对应卡产品str
		String cpIn = "";
		String sqlStr = "";
		if (user_code != null && !"".equals(user_code)) {
			UserCrdproductDao userCrdproductDao = new UserCrdproductDao();
			UserCrdproductBean ucBean = userCrdproductDao.getUserCrdproductByID(user_code);
			String cpStr = ucBean == null ? "" : ucBean.getCrdproduct();
			if (!"".equals(cpStr)) {
				if (cpStr.contains(",")) {
					cpIn = cpStr.replaceAll(",", "','");
				} else {
					cpIn = cpStr;
				}
				sqlStr = " and crdproduct in ('" + cpIn + "')";
			}
		}
		String count = "0";
		sql = "select sum(pan_count*amt_each_crd) as amt_each_crd from cashinbatch where 1=1 ";
		sql = sql + sqlStr;

		try {
			Vector vector = null;
			sql += sqlparm(querybean);
			vector = DbExec.execQuery(sql);

			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = (String) map.get("amt_each_crd");
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return count;

	}

	public String getSumPanCount(DepositUndoForm querybean) {

		String count = "0";
		sql = "select sum(pan_count) as pan_count from cashinbatch where 1=1 ";
		try {
			Vector vector = null;
			sql += sqlparm(querybean);
			vector = DbExec.execQuery(sql);

			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = (String) map.get("pan_count");
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return count;

	}
	public int updCashInBatchDescr(String id, String desc) {
        sql = "update cashinbatch t set descr = ? where  t.reserved1 = ? ";
        
        List<String> paramList = new ArrayList<String>();
        paramList.add(desc);
        paramList.add(id);
        try {
			result = DbExec.execUpdate(sql, paramList);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: update cashinbatch", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: update cashinbatch", e);
			result = -1;
		}
		return result;
    }
	public String sqlparm(DepositUndoForm querybean) {
		querybean.getReserved1();
		StringBuffer sb = new StringBuffer("");
		if (querybean != null) {
			if (null != querybean.getReserved1() && !"".equals(querybean.getReserved1())
					&& !"null".equals(querybean.getReserved1())) {
				sb.append(" and reserved1 ='").append(querybean.getReserved1().trim()).append("' ");
			}
			
			if (null != querybean.getFather_order() && !"".equals(querybean.getFather_order())
					&& !"null".equals(querybean.getFather_order())) {
				sb.append(" and father_order ='").append(querybean.getFather_order().trim()).append("' ");
			}

			if (null != querybean.getTime() && !"".equals(querybean.getTime()) && !"null".equals(querybean.getTime())) {
				sb.append(" and Time ='").append(querybean.getTime().trim()).append("' ");
			}
			if (null != querybean.getStartdate() && !"".equals(querybean.getStartdate())
					&& !"null".equals(querybean.getStartdate())) {
				sb.append(" and Time >='").append(querybean.getStartdate().trim()).append("' ");
			}
			if (null != querybean.getEnddate() && !"".equals(querybean.getEnddate())
					&& !"null".equals(querybean.getEnddate())) {
				sb.append(" and Time <='").append(querybean.getEnddate().trim()).append("' ");
			}

			// 审批日期
			if (null != querybean.getDescrStartDate() && !"".equals(querybean.getDescrStartDate())
					&& !"null".equals(querybean.getDescrStartDate())) {
				sb.append(" and REGEXP_SUBSTR(descr,'[^,]+',1,2,'i') >='").append(querybean.getDescrStartDate().trim())
						.append("' ");
			}
			if (null != querybean.getDescrEndDate() && !"".equals(querybean.getDescrEndDate())
					&& !"null".equals(querybean.getDescrEndDate())) {
				sb.append(" and REGEXP_SUBSTR(descr,'[^,]+',1,2,'i') <='").append(querybean.getDescrEndDate().trim())
						.append("' ");
			}

			if (null != querybean.getPay_type() && !"".equals(querybean.getPay_type())
					&& !"null".equals(querybean.getPay_type())) {
				sb.append(" and Pay_type ='").append(querybean.getPay_type().trim()).append("' ");
			}
			if (null != querybean.getBatch_stat() && !"".equals(querybean.getBatch_stat())
					&& !"null".equals(querybean.getBatch_stat())) {
				sb.append(" and batch_stat ='").append(querybean.getBatch_stat().trim()).append("' ");
			}

			if (null != querybean.getSales_point() && !"".equals(querybean.getSales_point())
					&& !"null".equals(querybean.getSales_point())) {
				sb.append(" and sales_point like '%").append(querybean.getSales_point().trim()).append("%' ");
			}
			if (null != querybean.getPan_start() && !"".equals(querybean.getPan_start())
					&& !"null".equals(querybean.getPan_start())) {
				String panStart = querybean.getPan_start().trim();
				if (panStart.length() > 15) {
					panStart = panStart.substring(0, 15);
				}
				sb.append(" and Pan_start ='").append(panStart).append("' ");
			}
			if (null != querybean.getPan_end() && !"".equals(querybean.getPan_end())
					&& !"null".equals(querybean.getPan_end())) {
				String panEnd = querybean.getPan_end().trim();
				if (panEnd.length() > 15) {
					panEnd = panEnd.substring(0, 15);
				}
				sb.append(" and Pan_end ='").append(panEnd).append("' ");
			}
			if (null != querybean.getAmt_each_crd() && !"".equals(querybean.getAmt_each_crd())
					&& !"null".equals(querybean.getAmt_each_crd())) {
				sb.append(" and Amt_each_crd =").append(querybean.getAmt_each_crd().trim()).append(" ");
			}
			if (null != querybean.getStart_period() && !"".equals(querybean.getStart_period())
					&& !"null".equals(querybean.getStart_period())) {
				sb.append(" and Acct_period >=").append(querybean.getStart_period().trim()).append(" ");
			}
			if (null != querybean.getEnd_period() && !"".equals(querybean.getEnd_period())
					&& !"null".equals(querybean.getEnd_period())) {
				sb.append(" and Acct_period <=").append(querybean.getEnd_period().trim()).append(" ");
			}
			if (null != querybean.getProvince() && !"".equals(querybean.getProvince())
					&& !"null".equals(querybean.getProvince())) {

				if (null != querybean.getCity_no() && !"".equals(querybean.getCity_no())
						&& !"null".equals(querybean.getCity_no())) {
					sb.append(" and area ='").append(querybean.getProvince().trim()).append(",")
							.append(querybean.getCity_no()).append("' ");
				} else {
					sb.append(" and area like '").append(querybean.getProvince().trim()).append(",%'");
				}
			}
			if (null != querybean.getOperator() && !"".equals(querybean.getOperator())
					&& !"null".equals(querybean.getOperator())) {
				sb.append(" and LOWER(Operator) like '%").append(querybean.getOperator().toLowerCase().trim())
						.append("%' ");
			}
			if (null != querybean.getCrdproduct() && !"".equals(querybean.getCrdproduct())
					&& !"null".equals(querybean.getCrdproduct())) {
				sb.append(" and crdproduct ='").append(querybean.getCrdproduct().trim()).append("' ");
			}
		}
		return sb.toString();

	}

	public int getCount(DepositUndoForm querybean, String user_code) {

		// 获取用户对应卡产品str
		String cpIn = "";
		String sqlStr = "";
		if (user_code != null && !"".equals(user_code)) {
			UserCrdproductDao userCrdproductDao = new UserCrdproductDao();
			UserCrdproductBean ucBean = userCrdproductDao.getUserCrdproductByID(user_code);
			String cpStr = ucBean == null ? "" : ucBean.getCrdproduct();
			if (!"".equals(cpStr)) {
				if (cpStr.contains(",")) {
					cpIn = cpStr.replaceAll(",", "','");
				} else {
					cpIn = cpStr;
				}
				sqlStr = " and crdproduct in ('" + cpIn + "')";
			}
		}
		int count = 0;
		sql = "select count(*) numbers from cashinbatch where 1=1 ";
		sql = sql + sqlStr;

		try {
			Vector vector = null;
			sql += sqlparm(querybean);
			vector = DbExec.execQuery(sql);

			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = Integer.parseInt(((String) map.get("numbers")).trim());
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return count;
	}

	public DepositUndoBean getDepositInfo(String id) {
        sql = "select a.verno_ctx, a.id, a.txnsrc, a.txncode, a.time, a.stan, a.pan_start, a.pan_end, a.pan_count, a.amt_each_crd, a.father_order, a.children_order, a.org_f_order, a.org_c_order, a.acct_period, a.pay_type, a.pay_desc, a.payer_name, a.sales_point, a.area, a.id_type, a.id_number, a.cell_phone, a.phone, a.address, a.cashin_type, a.ispaid, a.summary, a.operator, a.mrcht_id, a.batch_stat, a.reserved1, a.reserved2, a.reserved3, a.descr, a.currbill, a.amttxn, a.rateset, c.descr as crdproduct, a.curtxn from cashinbatch a left join crdproduct c on a.crdproduct=c.crdproduct where  a.id = ? ";
        List<String> paramList = new ArrayList<String>();
        paramList.add(id);
        List lst;
        DepositUndoBean bean=new DepositUndoBean();
        try {
            Vector verctor = DbExec.execQuery(sql,paramList);
            if (verctor != null && !verctor.isEmpty()) {
            	bean = new DepositUndoBean((HashMap) verctor.get(0));                    
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("SQLException", e);
        }
        return bean;
    }
}
