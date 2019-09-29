package cn.yufu.posp.cardBinArea.domain.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.cardBinArea.dao.hibernate.hql.CardBinAreaDaoHibernateHQL;
import cn.yufu.posp.cardBinArea.domain.model.CardBinArea;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;

public class CardBinAreaLogic extends BaseLogic implements CardBinAreaLogicInterface {

	private static final Log log = LogFactory.getLog("CardBinArea");

	public CardBinAreaLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(CardBinArea queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("CardBinAreaLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());
			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;
			CardBinAreaDaoHibernateHQL dao = (CardBinAreaDaoHibernateHQL) getBean("CardBinAreaDao");
			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);
			
			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			log.info("totalCount=" + totalCount + ";list.size()=" + list.size());
			if (list != null && list.size() > 0) {
				Map areaCodeMap=dao.getAreaCodeMap();
				for (int i = 0; i < list.size(); i++) {
					CardBinArea CardBinArea = (CardBinArea) list.get(i);
					if(areaCodeMap!=null&&areaCodeMap.size()>0&&CardBinArea.getAreaCode()!=null&&!"".equals(CardBinArea.getAreaCode())){						
						CardBinArea.setAreaCodeName(areaCodeMap.get(CardBinArea.getAreaCode()).toString());
					}
				}
			}
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("CardBinAreaLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("CardBinAreaLogic.queryAll()����ʱ�����쳣��");
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
			log.info("CardBinAreaLogic.deleteItem()��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			CardBinAreaDaoHibernateHQL dao = (CardBinAreaDaoHibernateHQL) getBean("CardBinAreaDao");

			dao.deleteItem(newKeys, ud);

			log.info("CardBinAreaLogic.deleteItem()�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaLogic.deleteItem()ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		CardBinArea newModel = new CardBinArea();

		HashMap parameteMap = new HashMap();
		try {
			log.info("CardBinAreaLogic.findItem()��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			CardBinAreaDaoHibernateHQL dao = (CardBinAreaDaoHibernateHQL) getBean("CardBinAreaDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			} else {
				Map areaCodeMap=dao.getAreaCodeMap();
				if(newModel.getAreaCode()!=null&&!"".equals(newModel.getAreaCode())&&areaCodeMap!=null&&areaCodeMap.size()>0){					
					newModel.setAreaCodeName(areaCodeMap.get(newModel.getAreaCode()).toString());
				}
			}
			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("CardBinAreaLogic.findItem()�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaLogic.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(CardBinArea newModel, UserData ud) throws OAException {
		try {
			log.info("CardBinAreaLogic.saveItem()��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			CardBinAreaDaoHibernateHQL dao = (CardBinAreaDaoHibernateHQL) getBean("CardBinAreaDao");
			dao.saveItem(newModel, ud);
			log.info("CardBinAreaLogic.saveItem()�������ã��޸�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaLogic.saveItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(CardBinArea newModel, UserData ud) throws OAException {
		try {
			log.info("CardBinAreaLogic.createItem()��ʼ���ã��½�һ����¼��" + ud.getUserId());
			CardBinAreaDaoHibernateHQL dao = (CardBinAreaDaoHibernateHQL) getBean("CardBinAreaDao");
			// ��֤����Ƿ��Ѿ�����-������Ϣ
			try {
				log.info("CardBinAreaLogic.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
				CardBinArea sm = dao.findItemById(newModel.getCardBin(), ud);
				log.info("CardBinAreaLogic.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
				if (sm != null) {
					throw new OAException("��BIN " + newModel.getCardBin() + " �Ѿ������ݿ��д��ڣ�");
				}
			} catch (Exception e) {
				throw new OAException(e.getMessage());
			}
			dao.createItem(newModel, ud);
			log.info("CardBinAreaLogic.createItem()�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
