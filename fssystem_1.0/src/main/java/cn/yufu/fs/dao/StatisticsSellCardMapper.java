/**
 *包名:cn.yufu.fs.dao
 *描述:package cn.yufu.fs.dao;
 */
package cn.yufu.fs.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.FukaCompanys;
import cn.yufu.fs.entity.FukaSalepoint;
import cn.yufu.fs.entity.StatisticsSellCard;

/**
 * StatisticsSellCardMapper.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年5月16日
 * 描述:售卡网点查询接口
 */
@Repository("fs.StatisticsSellCardDao")
public interface StatisticsSellCardMapper {

	/**
	 * 得到售卡网点信息
	 * @return
	 */
	public List<FukaSalepoint> getFukaSalePoint();

	/**
	 * 根据父id查询公司信息
	 * @param cid
	 * @return
	 */
	public FukaCompanys getFukaCompany(@Param("cid") BigDecimal cid);

	/**
	 * 查询总数
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
	public List<StatisticsSellCard> queryList(@Param("queryModel") StatisticsSellCard queryModel, @Param("startResult") int startResult, @Param("endResult") int endResult);

	/**
	 * 导出EXCEL
	 * @param info
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
