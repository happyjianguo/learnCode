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
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcTerminalOrmDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalOrm;

public class EdcTerminalOrmLogic extends BaseLogic implements EdcTerminalOrmLogicInterface {

	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcTerminalOrmLogic() {

	}

	public void createItem(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalOrmLogic.createItem()��ʼ���ã��½��ն�������Ϣ��");

			EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");
			dao.createItem(edcTerminalOrm, ud);

			log.info("EdcTerminalOrmLogic.createItem()�������ã��½��ն�������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalOrmLogic.createItem()�½��ն�������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalOrmLogic.deleteItem()��ʼ���ã�ɾ���ն�������Ϣ��");

			// ɾ���ն������趨
			EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");
			dao.deleteItem(keys, ud);

			log.info("EdcTerminalOrmLogic.deleteItem()�������ã�ɾ���ն�������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalOrmLogic.deleteItem()ɾ���ն�������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(EdcTerminalOrm key, UserData ud) throws OAException {
		EdcTerminalOrm newModel = new EdcTerminalOrm();
		HashMap map = new HashMap();
		try {
			log.info("EdcTerminalOrmLogic.findItemByKey()��ʼ���ã�����һ����Ϣ��");

			EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");
			newModel = dao.findItemByKey(key, ud);

			if (newModel != null) {
				EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				newModel.setMerchanName(commondao.findMerchantNameByKey(newModel.getMerchantId(), ud));
			}
			map.put("Infolist", newModel);

			log.info("EdcTerminalOrmLogic.findItemByKey()�������ã�����һ����Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalOrmLogic.findItemByKey()����һ����Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(EdcTerminalOrm edcTerminalOrm, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("EdcTerminalOrmLogic.queryAllItem()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");

			// �õ���¼��������
			totalCount = dao.queryCount(edcTerminalOrm, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(edcTerminalOrm, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("���ؽ��������==" + list.size() + "��ʼλ����==" + startIndex + "ҳ����==" + (startIndex + pageSize) / pageSize + "�ܼ�¼��==" + totalCount);

			log.info("EdcTerminalOrmLogic.queryAllItem()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalOrmLogic.ueryAllItem()��ҳ��ѯ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalOrmLogic.saveItem()��ʼ���ã����� ���޸� ��Ϣ��");

			EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");

			dao.saveItem(edcTerminalOrm, ud);

			log.info("EdcTerminalOrmLogic.EdcTerminalOrmLogic.saveItem()�������ã����� ���޸� ��Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalOrmLogic.saveItem()���� ���޸� ����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}
	
	

	public void saveUploadItem(List<EdcTerminalOrm> edcTerminalOrmList, UserData ud) throws OAException {
		try {
			EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");
			for (int i = 0; i < edcTerminalOrmList.size(); i++) {
				// �����µ���Ϣ
				dao.createItem(edcTerminalOrmList.get(i), ud);
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcTerminalOrmLogic.saveUploadItem()�����ն���Ϣ��ͬ���ն�����Կ��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

	public EdcTerminalOrm queryModualBy(String merchantId, String terminalId) throws OAException {
		log.info("EdcTerminalOrmLogic.createItem()��ʼ���ã��½��ն�������Ϣ��");

		EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");
		EdcTerminalOrm model = dao.queryModualBy(merchantId, terminalId);

		log.info("EdcTerminalOrmLogic.createItem()�������ã��½��ն�������Ϣ��");
		return model;
	}

	//У����������Ψһ��PKEY
	public String checkEdcTerminalOrmPKEY(String merchantId,String terminalId,String moduleId) throws OAException {
		String checkFlag="0";
		try {
			EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");
			checkFlag=dao.checkEdcTerminalOrmPKEY(merchantId,terminalId,moduleId);
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcTerminalOrmLogic.checkEdcTerminalOrmPKEY()У����������Ψһ��PKEY��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
		return checkFlag;
	}
	
	
	//У������Ψһ��ORM
		public String checkEdcTerminalOrmORM(String bankMerchantId,String bankTerminalId,String moduleId) throws OAException {
			String checkFlag="0";
			try {
				EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");
				checkFlag=dao.checkEdcTerminalOrmORM(bankMerchantId,bankTerminalId,moduleId);
			} catch (Exception e) {
				if (log.isDebugEnabled())
					e.printStackTrace();
				log.error("EdcTerminalOrmLogic.checkEdcTerminalOrmORM()У������Ψһ��ORM��");
				log.error(e, e.fillInStackTrace());

				throw new OAException(e.getMessage());
			}
			return checkFlag;
		}
}
