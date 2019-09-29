package cn.yufu.posp.bookManager.domain.logic;

import cn.yufu.posp.bookManager.domain.model.AnalyzeMerfeeBook;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

/**
 * �̻�������Ӧ�յ�
 * @author King
 *
 */
public interface AnalyzeMerfeeBookLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(AnalyzeMerfeeBook queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	// ��ѯ��ϸ
	public AnalyzeMerfeeBook queryDetail(String id, UserData ud) throws OAException;

}
