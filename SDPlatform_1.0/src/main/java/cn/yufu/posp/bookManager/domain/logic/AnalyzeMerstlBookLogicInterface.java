package cn.yufu.posp.bookManager.domain.logic;

import cn.yufu.posp.bookManager.domain.model.AnalyzeMerstlBook;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

/**
 * �̻������
 * 
 * @author King
 * 
 */
public interface AnalyzeMerstlBookLogicInterface {

	// �������м�¼
	public PageInfoModel queryAll(AnalyzeMerstlBook queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	// ��ѯ��ϸ
	public AnalyzeMerstlBook queryDetail(String id, UserData ud) throws OAException;

}
