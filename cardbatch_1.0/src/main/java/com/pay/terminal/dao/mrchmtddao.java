package com.pay.terminal.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.pay.merchant.bean.Mrchmtdbean;
import com.pay.util.DbExec;

public class mrchmtddao {
	private static final Logger logger = Logger.getLogger(mrchmtddao.class);
	private String sql="";
	private int result = 0;
	
	public int mrchmtddao(Mrchmtdbean merchantdao) throws SQLException, Exception {
		sql = "insert into Mrchmtd ( BTCHCNTMTD, BTCHCNTPM, BTCHCNTYTD, BTCHDRMTD, BTCHDRPM, BTCHDRYTD, BTCHCRMTD, BTCHCRPM, BTCHCRYTD, BTCHCOMMTD, BTCHCOMPM, BTCHCOMYTD) values(?,?,?,?,?,?,?,?,?,?)";

		ArrayList params = new ArrayList();
		Mrchmtdbean mrchmtdbean=new Mrchmtdbean();
		params.add( (mrchmtdbean.getBtchntmtd()) );
		params.add( (mrchmtdbean.getBtchntpm()) );
		params.add( (mrchmtdbean.getBtchcntytd()) );
		params.add( (mrchmtdbean.getBtchdrmtd()) );
		params.add( (mrchmtdbean.getBtchdrpm()) );
		params.add( (mrchmtdbean.getBtchdrytd()) );
		params.add( (mrchmtdbean.getBtchcommtd()) );
		params.add( (mrchmtdbean.getBtchcompm()) );
		params.add( (mrchmtdbean.getBtchcomytd()) );
		
		result = DbExec.execUpdate(sql, params);
		try {
			result = DbExec.execUpdate(sql, params);
		} catch (SQLException e) {
			logger.error(mrchmtdbean.toString(), e);
			result = -1;
		}catch (Exception e) {
			logger.error(mrchmtdbean.toString(), e);
			result = -1;
		}
		return result;
	}


	public int Modfymrchmtd(Mrchmtdbean mrchmtdBean) {
		sql = "update mrchmtd set VERNO_CTX=?, MERCHANT_ID=?, CURRCODE=?, BTCHCNTMTD=?, BTCHCNTPM=?, BTCHCNTYTD=?, BTCHDRMTD=?, BTCHDRPM=?, BTCHDRYTD=?, BTCHCRMTD=?, BTCHCRPM=?, BTCHCRYTD=?, BTCHCOMMTD=?, BTCHCOMPM=?, where BTCHCOMYTD=?  ";
	
		ArrayList params = new ArrayList();
		Mrchmtdbean mrchmtdbean=new Mrchmtdbean();
	
		params.add( (mrchmtdbean.getVerno_ctx()) );
		params.add( (mrchmtdbean.getMerchant_id()) );
		params.add( (mrchmtdbean.getCurrcode()) );
		params.add( (mrchmtdbean.getBtchntmtd()) );
		params.add( (mrchmtdbean.getBtchntpm()) );
		params.add( (mrchmtdbean.getBtchcntytd()) );
		params.add( (mrchmtdbean.getBtchdrmtd()) );
		params.add( (mrchmtdbean.getBtchdrpm()) );
		params.add( (mrchmtdbean.getBtchcrytd()) );
		params.add( (mrchmtdbean.getBtchcommtd()) );
		params.add( (mrchmtdbean.getBtchcompm()) );
		params.add( (mrchmtdbean.getBtchcomytd()) );
		
		try{
			result = DbExec.execUpdate(sql, params);
		} catch (SQLException e) {
			result = -1;
			logger.error(mrchmtdbean.toString(), e);
		}catch (Exception e) {
			result = -1;
			logger.error(mrchmtdbean.toString(), e);
		}
		return result;
	}
}
