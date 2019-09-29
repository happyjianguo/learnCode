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
import cn.yufu.posp.merchant.domain.model.MerchantCardModel;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogic;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;

public class MerchantCardLogic extends BaseLogic implements MerchantCardLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantCardLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(MerchantCardModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("MerchantCardLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			MerchantCardDaoHibernateHQL dao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			// for(int i=0;i<list.size();i++){
			// MerchantCardModel mc = (MerchantCardModel)list.get(i);
			// mc.setBankType(mc.getBankType().replaceAll(" ", ""));
			// }

			// ��װ�����������ƺͿ���������
			if (list != null && list.size() > 0) {
				EdcCommonLogic elogic = new EdcCommonLogic();
				List<Banktype> banktypeList = elogic.queryAllBanktypeItem(null, ud);
				List<Cardtype> cardtypeList = elogic.queryAllCardtypeItem(null, ud);
				for (int i = 0; i < list.size(); i++) {
					MerchantCardModel mcm = (MerchantCardModel) list.get(i);
					for (Banktype bvo : banktypeList) {
						if (mcm.getBankType().equals(bvo.getBankType())) {
							mcm.setBankTypeName(bvo.getTypeName());
						}
					}
					for (Cardtype cvo : cardtypeList) {
						if (mcm.getCardType().equals((cvo.getId().getCardType()).replace(" ", ""))) {
							mcm.setCardTypeName(cvo.getId().getTypeName());
						}
					}

				}
			}

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("MerchantCardLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCardLogic.queryAll()����ʱ�����쳣��");
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
			log.info("MerchantCardLogic.deleteItem(List newKeys, UserData ud)��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			MerchantCardDaoHibernateHQL dao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");

			dao.deleteItem(newKeys, ud);

			log.info("MerchantCardLogic.deleteItem(List newKeys, UserData ud)�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCardLogic.deleteItem(List newKeys, UserData ud)ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		MerchantCardModel newModel = new MerchantCardModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantCardLogic.findItem(long key, UserData ud)��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			MerchantCardDaoHibernateHQL dao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			}
			// ��װ�̻�����
			newModel.setMerchantName(dao.findMerchantName(newModel.getMerchantId(), ud));
			// ��װ�����������Ϳ�������
			EdcCommonLogic elogic = new EdcCommonLogic();
			List<Banktype> banktypeList = elogic.queryAllBanktypeItem(null, ud);
			List<Cardtype> cardtypeList = elogic.queryAllCardtypeItem(null, ud);
			for (Banktype bt : banktypeList) {
				if (newModel.getBankType().equals(bt.getBankType())) {
					newModel.setBankTypeName(bt.getTypeName());
				}
			}
			for (Cardtype ct : cardtypeList) {
				if (newModel.getCardType().equals((ct.getId().getCardType()).replace(" ", ""))) {
					newModel.setCardTypeName(ct.getId().getTypeName());
				}
			}
			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("MerchantCardLogic.findItem(long key, UserData ud)�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCardLogic.findItem(long key, UserData ud)��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(MerchantCardModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantCardLogic.saveItem(MerchantCardModel newModel, UserData ud)��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			MerchantCardDaoHibernateHQL dao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");
			dao.saveItem(newModel, ud);
			log.info("MerchantCardLogic.saveItem(MerchantCardModel newModel, UserData ud)�������ã��޸�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCardLogic.saveItem(MerchantCardModel newModel, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(MerchantCardModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantCardLogic.createItem(MerchantCardModel newModel, UserData ud)��ʼ���ã��½�һ����¼��" + ud.getUserId());

			MerchantCardDaoHibernateHQL dao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");
			dao.createItem(newModel, ud);

			log.info("MerchantCardLogic.createItem(MerchantCardModel newModel, UserData ud)�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCardLogic.createItem(MerchantCardModel newModel, UserData ud)����ʱ�����쳣��");
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
