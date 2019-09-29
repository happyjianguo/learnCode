/**
 *包名:com.pay.batch.tlog.dao
 *描述:package com.pay.batch.tlog.dao;
 */
package com.pay.batch.tlog.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.batch.tlog.bean.QuerySinopecSweepCardBean;
import com.pay.batch.tlog.struts.form.SinopecSweepCardUploadForm;
import com.pay.util.DbExec;

/**
 * QuerySinopecSweepCardDao.java 版权所有(C) 2018 裕福控股有限公司 创建:gll 时间:2018年5月17日
 * 描述:中石化扫码刷卡统计
 */
public class QuerySinopecSweepCardDao {
	private static final Logger logger = Logger.getLogger(QuerySinopecSweepCardDao.class);
	private String sql = "";

	public List<QuerySinopecSweepCardBean> getSinopecSweepCard(SinopecSweepCardUploadForm querycondition) throws SQLException, Exception {
		List<QuerySinopecSweepCardBean> list = new ArrayList<QuerySinopecSweepCardBean>();
		sql = " SELECT (CASE T.TXNSRC WHEN '9' THEN 'SM' ELSE 'SK' END) tradingType, COUNT(1) tradingNumber, SUM(T.AMTTXN) tradingAmt FROM TLOG T WHERE 1=1 AND T.FNCODE = 200 AND T.TXNCODE = 0 AND T.SUB_TXNCODE = 00 AND T.TXNSTATUS = 7 ";
		Vector vector = null;
		sql += " AND T.CRDACPTID = '"+querycondition.getMerchant_no().trim()+"'";
		sql += " AND T.TERMCODE IN (" + querycondition.getTermial_no().trim()+") ";
		sql += " AND T.DATELOCAL >= TO_DATE("+querycondition.getStarttime().trim()+", 'yyyy/mm/dd')";
		sql += " AND T.DATELOCAL <= TO_DATE("+querycondition.getEndtime().trim()+", 'yyyy/mm/dd')";
		sql += " GROUP BY CASE T.TXNSRC WHEN '9' THEN 'SM' ELSE 'SK' END";
		System.out.println(sql);
		vector = DbExec.execQueryListBak(sql);
		if (vector != null && !vector.isEmpty()) {
			for (int i = 0; i < vector.size(); i++) {
				QuerySinopecSweepCardBean bean = new QuerySinopecSweepCardBean((HashMap) vector.get(i));
				list.add(bean);
			}
		}
		return list;
	}
}
