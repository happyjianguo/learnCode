package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.MerchantUserDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.MerchantUser;
import cn.yufu.posp.merchant.domain.model.MerchantUserRela;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalId;

public class MerchantUserLogic extends BaseLogic implements MerchantUserLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantUserLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(MerchantUser queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("MerchantUserLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			MerchantUserDaoHibernateHQL dao = (MerchantUserDaoHibernateHQL) getBean("merchantUserDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("MerchantUserLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantUserLogic.queryAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {

			log.info("MerchantUserLogic.deleteItem()��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			MerchantUserDaoHibernateHQL dao = (MerchantUserDaoHibernateHQL) getBean("merchantUserDao");

			for (int i = 0; i < newKeys.size(); i++) {
				String tmp = newKeys.get(i) + "";
				// �жϸ��̻�����Ƿ����ն������趨��Ϣ�������ڣ�����ɾ��
				EdcTerminal et = new EdcTerminal();
				EdcTerminalId etd = new EdcTerminalId();
				EdcCommonDaoHibernateHQL edao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				if (tmp != null && !"".equals(tmp)) {
					etd.setMerchantId(tmp);
					et.setId(etd);
					List<EdcTerminal> list = edao.findEdcTerminalItemByKey(et, ud);
					if (list.size() > 0) {
						throw new OAException("��Ҫɾ�����̻� " + tmp + " �������ն������趨���У�����ɾ������ִ����ز�������ɾ����");
					}
				}

			}
			dao.deleteItem(newKeys, ud);
		
			log.info("MerchantUserLogic.deleteItem()�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantUserLogic.deleteItem()ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		MerchantUser newModel = new MerchantUser();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantUserLogic.findItem()��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			MerchantUserDaoHibernateHQL dao = (MerchantUserDaoHibernateHQL) getBean("merchantUserDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			}

			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("MerchantUserLogic.findItem()�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantUserLogic.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(MerchantUser newModel, UserData ud) throws OAException {
		try {

			log.info("MerchantUserLogic.saveItem()��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			MerchantUserDaoHibernateHQL dao = (MerchantUserDaoHibernateHQL) getBean("merchantUserDao");
			dao.saveItem(newModel, ud);
			log.info("MerchantUserLogic.saveItem()�������ã��޸�һ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantUserLogic.saveItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(MerchantUser newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantUserLogic.createItem()��ʼ���ã��½�һ����¼��" + ud.getUserId());
			MerchantUserDaoHibernateHQL dao = (MerchantUserDaoHibernateHQL) getBean("merchantUserDao");
			
			dao.createItem(newModel, ud);

			log.info("MerchantUserLogic.createItem()�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantUserLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}


}
