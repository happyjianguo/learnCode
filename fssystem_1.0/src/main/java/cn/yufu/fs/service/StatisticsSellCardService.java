/**
 *包名:cn.yufu.fs.service
 *描述:package cn.yufu.fs.service;
 */
package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.FukaSalepoint;
import cn.yufu.fs.entity.StatisticsSellCard;

/**
 * StatisticsSellCardService.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年5月16日
 * 描述:售卡网点统计service
 */
public interface StatisticsSellCardService {
	/**
	 * 得到售卡网点信息
	 * @return
	 */
	public List<FukaSalepoint> getFukaSalePoint();
	/**
	 * 
	 * 计算分页总数
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(StatisticsSellCard queryModel);
	/**
	 * 查询数据
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<StatisticsSellCard> queryList(StatisticsSellCard queryModel, int startResult, int endResult);
	/**
	 * 导出Excel查询数据
	 * @param queryModel
	 * @return
	 */
	public List<StatisticsSellCard> queryExcelList(StatisticsSellCard info);
	/**
	 * 
	 * 合计备付金总金额和售卡总金额
	 * @param info
	 * @return
	 */
	public StatisticsSellCard getSumAmt(StatisticsSellCard info);

}
