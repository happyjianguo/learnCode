/**
 *包名:cn.yufu.posp.queryManager.domain.logic
 *描述:package cn.yufu.posp.queryManager.domain.logic;
 */
package cn.yufu.posp.queryManager.domain.logic;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.queryManager.domain.model.TblExpCurTranLog;

/**
 * CurTranLsWechatAlipayLogicInterface.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年7月21日
 * 描述:支付宝微信Logic接口
 */
public interface CurTranLsWechatAlipayLogicInterface {

	public PageInfoModel queryAll(TblExpCurTranLog queryModel, PageInfoModel pageInfo, UserData sessionUserData, String startDate, String endDate) throws OAException;

	@SuppressWarnings("rawtypes")
	public List queryExport(TblExpCurTranLog queryModel, UserData sessionUserData, String startDate, String endDate) throws OAException;
	
}
