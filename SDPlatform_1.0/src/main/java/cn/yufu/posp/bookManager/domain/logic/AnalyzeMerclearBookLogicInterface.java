package cn.yufu.posp.bookManager.domain.logic;

import cn.yufu.posp.bookManager.domain.model.AnalyzeMerclearBook;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

/**
 * �̻���ֵ�
 * @author King
 *
 */
public interface AnalyzeMerclearBookLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(AnalyzeMerclearBook queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	// ��ѯ��ϸ
	public AnalyzeMerclearBook queryDetail(String id, UserData ud) throws OAException;

}
