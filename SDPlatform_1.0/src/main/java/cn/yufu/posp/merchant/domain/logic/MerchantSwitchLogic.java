package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.MerchantCardDaoHibernateHQL;
import cn.yufu.posp.merchant.dao.hibernate.hql.MerchantSwitchDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.MerchantSwitchModel;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogic;
import cn.yufu.posp.bank.domain.model.Banktype;

public class MerchantSwitchLogic extends BaseLogic implements MerchantSwitchLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantSwitchLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(MerchantSwitchModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("MerchantSwitchLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			MerchantSwitchDaoHibernateHQL dao = (MerchantSwitchDaoHibernateHQL) getBean("merSwitchDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			if (list != null && list.size() > 0) {

				EdcCommonLogic elogic = new EdcCommonLogic();
				List<Banktype> banktypeList = elogic.queryAllBanktypeItem(null, null);
				for (int j = 0; j < list.size(); j++) {
					MerchantSwitchModel msm = (MerchantSwitchModel) list.get(j);
					for (Banktype bt : banktypeList) {
						if (msm.getBankType().equals(bt.getBankType())) {
							msm.setBankTypeName(bt.getTypeName());
						}
					}
				}

			}
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("MerchantSwitchLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantSwitchLogic.queryAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return page;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("MerchantSwitchLogic.deleteItem(List newKeys, UserData ud)��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			MerchantSwitchDaoHibernateHQL dao = (MerchantSwitchDaoHibernateHQL) getBean("merSwitchDao");

			dao.deleteItem(newKeys, ud);

			log.info("MerchantSwitchLogic.deleteItem(List newKeys, UserData ud)�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantSwitchLogic.deleteItem(List newKeys, UserData ud)ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		MerchantSwitchModel newModel = new MerchantSwitchModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantSwitchLogic.findItem(long key, UserData ud)��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			MerchantSwitchDaoHibernateHQL dao = (MerchantSwitchDaoHibernateHQL) getBean("merSwitchDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			} else {
				newModel.setMerchantName(dao.findMerchantName(newModel.getMerchantId(), ud));// ��װ�̻�����
				// ��װ������������
				EdcCommonLogic elogic = new EdcCommonLogic();
				List<Banktype> banktypeList = elogic.queryAllBanktypeItem(null, null);
				for (Banktype bt : banktypeList) {
					if (newModel.getBankType().equals(bt.getBankType())) {
						newModel.setBankTypeName(bt.getTypeName());
					}
				}
			}

			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("MerchantSwitchLogic.findItem(long key, UserData ud)�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantSwitchLogic.findItem(long key, UserData ud)��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(MerchantSwitchModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantSwitchLogic.saveItem(MerchantSwitchModel newModel, UserData ud)��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			MerchantSwitchDaoHibernateHQL dao = (MerchantSwitchDaoHibernateHQL) getBean("merSwitchDao");
			dao.saveItem(newModel, ud);
			log.info("MerchantSwitchLogic.saveItem(MerchantSwitchModel newModel, UserData ud)�������ã��޸�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantSwitchLogic.saveItem(MerchantSwitchModel newModel, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(MerchantSwitchModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantSwitchLogic.createItem(MerchantSwitchModel newModel, UserData ud)��ʼ���ã��½�һ����¼��" + ud.getUserId());

			MerchantSwitchDaoHibernateHQL dao = (MerchantSwitchDaoHibernateHQL) getBean("merSwitchDao");
			dao.createItem(newModel, ud);

			log.info("MerchantSwitchLogic.createItem(MerchantSwitchModel newModel, UserData ud)�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantSwitchLogic.createItem(MerchantSwitchModel newModel, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * Ajax��ʾ�̻�����
	 */
	public String findMerchantName(String merchantId, UserData ud) {
		MerchantCardDaoHibernateHQL dao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");
		return dao.findMerchantName(merchantId, ud);

	}
}
