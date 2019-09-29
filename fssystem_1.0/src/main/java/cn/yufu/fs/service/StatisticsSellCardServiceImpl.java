/**
 *包名:cn.yufu.fs.service
 *描述:package cn.yufu.fs.service;
 */
package cn.yufu.fs.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.StatisticsSellCardMapper;
import cn.yufu.fs.entity.FukaCompanys;
import cn.yufu.fs.entity.FukaSalepoint;
import cn.yufu.fs.entity.StatisticsSellCard;
import cn.yufu.system.common.utils.DateUtils;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;

/**
 * StatisticsSellCardServiceImpl.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年5月16日
 * 描述:售卡网点统计Service Impl
 */
@Service("fs.StatisticsSellCardService")
public class StatisticsSellCardServiceImpl implements StatisticsSellCardService {

	Log log = Log.getLog(StatisticsSellCardServiceImpl.class);
	
	@Autowired
	@Qualifier("fs.StatisticsSellCardDao")
	private StatisticsSellCardMapper StatisticsSellCardDao;
	
	@Override
	public List<FukaSalepoint> getFukaSalePoint() {
		log.debug("得到售卡网点");
		List<FukaSalepoint> list = StatisticsSellCardDao.getFukaSalePoint();
		if(!(null == list || list.size() == 0)){
			for(int i=0;i<list.size();i++){
				//使用cid查询公司信息
				FukaCompanys fukaCompanys = StatisticsSellCardDao.getFukaCompany(list.get(i).getCid());
				if(null != fukaCompanys){
					//showname; //显示名称，格式=分公司名-售卡网点编码：售卡网点名
					String showname = fukaCompanys.getCompanyName()+"—"+list.get(i).getPointcode()+"："+list.get(i).getPointname();
					list.get(i).setShowname(showname);
				}
			}
		}
		return list;
	}
	@Override
	public int queryCnt(StatisticsSellCard queryModel) {
		/*时间格式yyyyMMdd(20170507)转yyyy-MM-dd HH:mm(2017-05-19 14:08)*/
		if(StringUtil.isEmpty(queryModel.getEndStlDate()) || StringUtil.isEmpty(queryModel.getStartStlDate())){
			queryModel.setStartStlDate("");
			queryModel.setEndStlDate("");
		}else{
			try {
				queryModel.setStartStlDate(DateUtils.strToDateFormat(queryModel.getStartStlDate()));
				queryModel.setEndStlDate(DateUtils.strToDateFormat(queryModel.getEndStlDate()).substring(0,11)+"23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		log.info("查询总数");
		return StatisticsSellCardDao.queryCnt(queryModel);
	}

	@Override
	public List<StatisticsSellCard> queryList(StatisticsSellCard queryModel, int startResult, int endResult) {
		/*时间格式yyyyMMdd(20170507)转yyyy-MM-dd HH:mm(2017-05-19 14:08)*/
//		try {
//			queryModel.setStartStlDate(DateUtils.strToDateFormat(queryModel.getStartStlDate()));
//			queryModel.setEndStlDate(DateUtils.strToDateFormat(queryModel.getEndStlDate()).substring(0,11)+"23:59");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		log.info("查询数据");
		List<StatisticsSellCard> list = StatisticsSellCardDao.queryList(queryModel, startResult, endResult);
		return list;
	}

	@Override
	public List<StatisticsSellCard> queryExcelList(StatisticsSellCard info) {
		/*时间格式yyyyMMdd(20170507)转yyyy-MM-dd HH:mm(2017-05-19 14:08)*/
		if(StringUtil.isEmpty(info.getEndStlDate()) || StringUtil.isEmpty(info.getStartStlDate())){
			info.setStartStlDate("");
			info.setEndStlDate("");
		}else{
			try {
				info.setStartStlDate(DateUtils.strToDateFormat(info.getStartStlDate()));
				info.setEndStlDate(DateUtils.strToDateFormat(info.getEndStlDate()).substring(0,11)+"23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		log.info("导出Excel查询数据");
		List<StatisticsSellCard> list = StatisticsSellCardDao.queryExcelList(info);
		return list;
	}
	/**
	 * 合计备付金总金额和售卡总金额
	 */
	@Override
	public StatisticsSellCard getSumAmt(StatisticsSellCard info) {
		log.debug("合计备付金总金额和售卡总金额", info);
		return StatisticsSellCardDao.getSumAmt(info);
	}

}
