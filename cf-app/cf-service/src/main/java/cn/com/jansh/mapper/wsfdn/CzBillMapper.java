package cn.com.jansh.mapper.wsfdn;

import java.util.List;

import cn.com.jansh.entity.wsfdn.CzBill;
import cn.com.jansh.entity.wsfdn.CzCurrBusiLog;
import cn.com.jansh.entity.wsfdn.CzLogBill;

public interface CzBillMapper {

	public int addTrainRecordBatch(List<CzBill> billlist);
	
	public int truncartCzBill();
	/**
	 * 正常订单
	 * @return
	 */
	public List<CzLogBill> query1();
	/**
	 * 状态不一致的订单
	 * @return
	 */
	public List<CzLogBill> query2();
	/**
	 * 平台多出的记录
	 * @return
	 */
	public List<CzCurrBusiLog> query3();
	/**
	 * 网宿提供的输的数据多出的记录
	 * @return
	 */
	public List<CzBill> query4();
}
