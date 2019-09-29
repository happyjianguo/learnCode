/**
 *����:cn.yufu.posp.queryManager.domain.logic
 *����:package cn.yufu.posp.queryManager.domain.logic;
 */
package cn.yufu.posp.queryManager.domain.logic;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.queryManager.domain.model.TblExpCurTranLog;

/**
 * CurTranLsWechatAlipayLogicInterface.java
 * ��Ȩ����(C) 2017 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2017��7��21��
 * ����:֧����΢��Logic�ӿ�
 */
public interface CurTranLsWechatAlipayLogicInterface {

	public PageInfoModel queryAll(TblExpCurTranLog queryModel, PageInfoModel pageInfo, UserData sessionUserData, String startDate, String endDate) throws OAException;

	@SuppressWarnings("rawtypes")
	public List queryExport(TblExpCurTranLog queryModel, UserData sessionUserData, String startDate, String endDate) throws OAException;
	
}
