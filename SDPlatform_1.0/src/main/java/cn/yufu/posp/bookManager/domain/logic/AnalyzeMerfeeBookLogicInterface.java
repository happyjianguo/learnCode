package cn.yufu.posp.bookManager.domain.logic;

import cn.yufu.posp.bookManager.domain.model.AnalyzeMerfeeBook;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

/**
 * 商户手续费应收单
 * @author King
 *
 */
public interface AnalyzeMerfeeBookLogicInterface {

	// 查找所有记录
	public PageInfoModel queryAll(AnalyzeMerfeeBook queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	// 查询明细
	public AnalyzeMerfeeBook queryDetail(String id, UserData ud) throws OAException;

}
