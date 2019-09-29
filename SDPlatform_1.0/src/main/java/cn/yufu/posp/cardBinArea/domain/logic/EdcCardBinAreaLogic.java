package cn.yufu.posp.cardBinArea.domain.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.cardBinArea.dao.hibernate.hql.EdcCardBinAreaDaoHibernateHQL;
import cn.yufu.posp.cardBinArea.domain.model.EdcCardBinArea;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public class EdcCardBinAreaLogic extends BaseLogic implements EdcCardBinAreaLogicInterface {

	private static final Log log = LogFactory.getLog("EdcCardBinArea");

	public EdcCardBinAreaLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(EdcCardBinArea queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("EdcCardBinAreaLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());
			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;
			EdcCardBinAreaDaoHibernateHQL dao = (EdcCardBinAreaDaoHibernateHQL) getBean("EdcCardBinAreaDao");
			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);
			
			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			log.info("totalCount=" + totalCount + ";list.size()=" + list.size());
//			if (list != null && list.size() > 0) {
//				Map areaCodeMap=dao.getAreaCodeMap();
//				for (int i = 0; i < list.size(); i++) {
//					EdcCardBinArea EdcCardBinArea = (EdcCardBinArea) list.get(i);
//					if(areaCodeMap!=null&&areaCodeMap.size()>0&&EdcCardBinArea.getAreaCode()!=null&&!"".equals(EdcCardBinArea.getAreaCode())){						
//						EdcCardBinArea.setAreaCodeName(areaCodeMap.get(EdcCardBinArea.getAreaCode()).toString());
//					}
//				}
//			}
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("EdcCardBinAreaLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcCardBinAreaLogic.queryAll()����ʱ�����쳣��");
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
			log.info("EdcCardBinAreaLogic.deleteItem()��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			EdcCardBinAreaDaoHibernateHQL dao = (EdcCardBinAreaDaoHibernateHQL) getBean("EdcCardBinAreaDao");

			dao.deleteItem(newKeys, ud);

			log.info("EdcCardBinAreaLogic.deleteItem()�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaLogic.deleteItem()ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		EdcCardBinArea newModel = new EdcCardBinArea();

		HashMap parameteMap = new HashMap();
		try {
			log.info("EdcCardBinAreaLogic.findItem()��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			EdcCardBinAreaDaoHibernateHQL dao = (EdcCardBinAreaDaoHibernateHQL) getBean("EdcCardBinAreaDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			} else {
//				Map areaCodeMap=dao.getAreaCodeMap();
//				if(newModel.getAreaCode()!=null&&!"".equals(newModel.getAreaCode())&&areaCodeMap!=null&&areaCodeMap.size()>0){					
//					newModel.setAreaCodeName(areaCodeMap.get(newModel.getAreaCode()).toString());
//				}
			}
			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("EdcCardBinAreaLogic.findItem()�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaLogic.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(EdcCardBinArea newModel, UserData ud) throws OAException {
		try {
			log.info("EdcCardBinAreaLogic.saveItem()��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			EdcCardBinAreaDaoHibernateHQL dao = (EdcCardBinAreaDaoHibernateHQL) getBean("EdcCardBinAreaDao");
			dao.saveItem(newModel, ud);
			log.info("EdcCardBinAreaLogic.saveItem()�������ã��޸�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaLogic.saveItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(EdcCardBinArea newModel, UserData ud) throws OAException {
		try {
			log.info("EdcCardBinAreaLogic.createItem()��ʼ���á�" + ud.getUserId());
			EdcCardBinAreaDaoHibernateHQL dao = (EdcCardBinAreaDaoHibernateHQL) getBean("EdcCardBinAreaDao");
//			// ��֤����Ƿ��Ѿ�����-������Ϣ
//			try {
//				log.info("EdcCardBinAreaLogic.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
//				EdcCardBinArea sm = dao.findItemById(newModel.getCardBin(),newModel.getMerchantId(),newModel.getTerminalId(), ud);
//				log.info("EdcCardBinAreaLogic.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
//				if (sm != null) {
//					throw new OAException("��BIN " + newModel.getCardBin() + " �Ѿ������ݿ��д��ڣ�");
//				}
//			} catch (Exception e) {
//				throw new OAException(e.getMessage());
//			}
			String terminalIds=newModel.getTerminalId();
			String[] terminalIdStr=terminalIds.split(",");
			for(int i=0;i<terminalIdStr.length;i++){
				newModel.setTerminalId(terminalIdStr[i]);
				dao.createItem(newModel, ud);
			}
			log.info("EdcCardBinAreaLogic.createItem()�������ã��½�"+terminalIdStr.length+"����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
