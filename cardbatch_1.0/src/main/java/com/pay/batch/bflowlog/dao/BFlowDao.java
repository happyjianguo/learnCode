package com.pay.batch.bflowlog.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.batch.bflowlog.bean.BFlowBean;
import com.pay.util.DbExec;

public class BFlowDao {
	private static final Logger logger = Logger.getLogger(BFlowDao.class);

	public BFlowBean getBFlowBean(String panflagno) {
		if (panflagno != null && !"".equals(panflagno)) {
			String sql = "select panflagno, batchflag, sendbuf from monbatchflw "
					+ " where  panflagno='" + panflagno.trim() + "' ";
			BFlowBean BFlowBean = null;
			try {
				Vector vector = DbExec.execQueryKeep(sql, null);
				if (vector != null && !vector.isEmpty()) {
					BFlowBean = new BFlowBean((HashMap) vector.get(0));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("SQLException", e);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("SQLException", e);
			}
			return BFlowBean;
		} else {
			return null;
		}
	}
	
	public int addBFlowBean(BFlowBean BFlowBean) {
		int result=0;
		String sql = "insert into monbatchflw(panflagno, batchflag, sendbuf) values('"
				+ BFlowBean.getPanflagno()
				+ "','"
				+ BFlowBean.getBatchflag()
				+ "','"
				+ BFlowBean.getSendbuf() + "')";
		try {
			result = DbExec.execUpdateKeep(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: addCrdformatMap", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: addCrdformatMap", e);
			result = -1;
		}
		return result;
	}
}
