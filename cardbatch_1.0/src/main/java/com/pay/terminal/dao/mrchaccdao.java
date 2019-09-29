package com.pay.terminal.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.pay.merchant.bean.Mrchaccbean;
import com.pay.util.DbExec;

public class mrchaccdao {
	private static final Logger logger = Logger.getLogger(mrchaccdao.class);
	private String sql="";
	private int result = 0;
	public int mrchaccdao(Mrchaccbean mrchaccbean) {
		sql = "insert into Mrchacc ((VERNO_CTX, MERCHANT_ID, CURRCODE, DATE_LAST_STMT, CLOSING_BAL, CURRENT_BAL, LAST_POST_BAL, LAST_POST_COM, LAST_POST_TAX) values(?,?,?,?,?,?,?)";

		ArrayList params = new ArrayList();
		Mrchaccbean mrchaccBean=new Mrchaccbean();
		params.add(mrchaccbean.getDate_last_stmt());
		params.add(mrchaccbean.getClosing_bal());
		params.add(mrchaccbean.getCurrent_bal());
		params.add(mrchaccbean.getLast_post_bal());
		params.add(mrchaccbean.getLast_post_com());
		params.add(mrchaccbean.getLast_post_tax());
		try {
			result = DbExec.execUpdate(sql, params);
		} catch (SQLException e) {
			logger.error(mrchaccbean.toString(), e);
			result = -1;
		}catch (Exception e) {
			logger.error(mrchaccbean.toString(), e);
			result = -1;
		}
		return result;
	}


	public int Modfymrchacc(Mrchaccbean mrchaccBean) {
		sql = "update mrchacc set VERNO_CTX=?, MERCHANT_ID=?, CURRCODE=?, DATE_LAST_STMT=?, CLOSING_BAL=?, CURRENT_BAL=?, LAST_POST_BAL=?, LAST_POST_COM=?,where LAST_POST_TAX=?  ";
	
		ArrayList params = new ArrayList();
		Mrchaccbean mrchaccbean=new Mrchaccbean();
	
		params.add(mrchaccbean.getVerno_ctx());
		params.add(mrchaccbean. getMerchant_id());
		params.add(mrchaccbean.getCurrcode());
		params.add(mrchaccbean.getDate_last_stmt());
		params.add(mrchaccbean.getClosing_bal());
		params.add(mrchaccbean.getCurrent_bal());
		params.add(mrchaccbean.getLast_post_bal());
		params.add(mrchaccbean.getLast_post_com());
		params.add(mrchaccbean.getLast_post_tax());
		
		try{
			result = DbExec.execUpdate(sql, params);
		} catch (SQLException e) {
			result = -1;
			logger.error(mrchaccbean.toString(), e);
		}catch (Exception e) {
			result = -1;
			logger.error(mrchaccbean.toString(), e);
		}
		return result;
	}
}
