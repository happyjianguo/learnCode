/**
 *����:cn.yufu.posp.terminalmanager.domain.logic
 *����:package cn.yufu.posp.terminalmanager.domain.logic;
 */
package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcZskterminalOrm;

/**
 * EdcZskterminalOrmLogicInterface.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��3��2��
 * ����:ר�����ն�
 */
public interface EdcZskterminalOrmLogicInterface {

	/** �������м�¼ */
	public PageInfoModel queryAllItem(EdcZskterminalOrm edcZskterminalOrm, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	/** ����һ����¼ */
	public HashMap findItemByKey(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException;
	
	public void saveUploadItem(List<EdcZskterminalOrm> edcZskterminalOrmList, UserData ud) throws OAException;
	
}
