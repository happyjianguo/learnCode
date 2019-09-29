/**
 *����:cn.yufu.posp.terminalmanager.domain.logic
 *����:package cn.yufu.posp.terminalmanager.domain.logic;
 */
package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcAlipayWeChatDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcNewfkterminalOrmDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcZskterminalOrmDaoHibernateHQLImpl;
import cn.yufu.posp.terminalmanager.domain.model.EdcAlipayWeChat;
import cn.yufu.posp.terminalmanager.domain.model.EdcZskterminalOrm;

/**
 * EdcZskterminalOrmLogic.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��3��2��
 * ����:ר�����ն�
 */
public class EdcZskterminalOrmLogic extends BaseLogic implements EdcZskterminalOrmLogicInterface {

	private static final Log log = LogFactory.getLog("edcTerminal");
	
	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcZskterminalOrmLogic() {

	}
	
	@Override
	public PageInfoModel queryAllItem(EdcZskterminalOrm edcZskterminalOrm, PageInfoModel pageInfo, UserData ud)
			throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("EdcZskterminalOrmLogic.queryAllItem()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcZskterminalOrmDaoHibernateHQLImpl dao = (EdcZskterminalOrmDaoHibernateHQLImpl) getBean("EdcZskterminalOrmDao");

			// �õ���¼��������
			totalCount = dao.queryCount(edcZskterminalOrm, ud);
			// ��ֹ���һҳû�ж���
			currentPage = pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(edcZskterminalOrm, startIndex, pageSize,
					page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("���ؽ��������==" + list.size() + "��ʼλ����==" + startIndex
					+ "ҳ����==" + (startIndex + pageSize) / pageSize + "�ܼ�¼��=="
					+ totalCount);

			log.info("EdcZskterminalOrmLogic.queryAllItem()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcZskterminalOrmLogic.queryAllItem()��ҳ��ѯ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	@Override
	public HashMap findItemByKey(EdcZskterminalOrm key, UserData ud) throws OAException {
		EdcZskterminalOrm newModel = new EdcZskterminalOrm();
		HashMap map = new HashMap();
		try {
			log.info("EdcZskterminalOrmLogic.findItemByKey()��ʼ���ã�����һ����Ϣ��");

			EdcZskterminalOrmDaoHibernateHQLImpl dao = (EdcZskterminalOrmDaoHibernateHQLImpl) getBean("EdcZskterminalOrmDao");
			newModel = dao.findItemByKey(key, ud);

			/*
			 * if (newModel != null) { EdcCommonDaoHibernateHQL commondao =
			 * (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			 * newModel.setMerchanName
			 * (commondao.findMerchantNameByKey(newModel.getMerchantId(), ud));
			 * }
			 */
			map.put("Infolist", newModel);

			log.info("EdcZskterminalOrmLogic.findItemByKey()�������ã�����һ����Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcZskterminalOrmLogic.findItemByKey()����һ����Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	 }

	@Override
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcZskterminalOrmLogic.deleteItem()��ʼ���ã�ɾ���ն�������Ϣ��");

			// ɾ���ն������趨
			EdcZskterminalOrmDaoHibernateHQLImpl dao = (EdcZskterminalOrmDaoHibernateHQLImpl) getBean("EdcZskterminalOrmDao");
			dao.deleteItem(keys, ud);

			log.info("EdcZskterminalOrmLogic.deleteItem()�������ã�ɾ���ն�������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcZskterminalOrmLogic.deleteItem()ɾ���ն�������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@Override
	public void createItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcZskterminalOrmLogic.createItem()��ʼ���ã��½��ն�������Ϣ��");

			EdcZskterminalOrmDaoHibernateHQLImpl dao = (EdcZskterminalOrmDaoHibernateHQLImpl) getBean("EdcZskterminalOrmDao");
			dao.createItem(edcZskterminalOrm, ud);

			log.info("EdcZskterminalOrmLogic.createItem()�������ã��½��ն�������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcZskterminalOrmLogic.createItem()�½��ն�������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@Override
	public void saveItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcZskterminalOrmLogic.saveItem()��ʼ���ã����� ���޸� ��Ϣ��");

			EdcZskterminalOrmDaoHibernateHQLImpl dao = (EdcZskterminalOrmDaoHibernateHQLImpl) getBean("EdcZskterminalOrmDao");

			dao.saveItem(edcZskterminalOrm, ud);

			log.info("EdcZskterminalOrmLogic.EdcNewfkterminalOrmLogic.saveItem()�������ã����� ���޸� ��Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcZskterminalOrmLogic.saveItem()���� ���޸� ����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	@Override
	public void saveUploadItem(List<EdcZskterminalOrm> edcZskterminalOrmList, UserData ud) throws OAException {
		try {
			EdcZskterminalOrmDaoHibernateHQLImpl dao = (EdcZskterminalOrmDaoHibernateHQLImpl) getBean("EdcZskterminalOrmDao");
			for (int i = 0; i < edcZskterminalOrmList.size(); i++) {
				// �����µ���Ϣ
				dao.createItem(edcZskterminalOrmList.get(i), ud);
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcZskterminalOrmLogic.saveUploadItem()�����ն���Ϣ��ͬ���ն�����Կ��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

}
