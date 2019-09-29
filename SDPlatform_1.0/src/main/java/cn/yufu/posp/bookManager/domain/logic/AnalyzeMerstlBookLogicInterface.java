package cn.yufu.posp.bookManager.domain.logic;

import cn.yufu.posp.bookManager.domain.model.AnalyzeMerstlBook;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

/**
 * 商户结算打款单
 * 
 * @author King
 * 
 */
public interface AnalyzeMerstlBookLogicInterface {

	// 查找所有记录
	public PageInfoModel queryAll(AnalyzeMerstlBook queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	// 查询明细
	public AnalyzeMerstlBook queryDetail(String id, UserData ud) throws OAException;

}
