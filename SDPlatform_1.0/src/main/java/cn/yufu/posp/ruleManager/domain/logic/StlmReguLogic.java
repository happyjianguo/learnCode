package cn.yufu.posp.ruleManager.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.MccParamDaoHibernateHQL;
import cn.yufu.posp.merchant.dao.hibernate.hql.MerchantCardDaoHibernateHQL;
import cn.yufu.posp.merchant.dao.hibernate.hql.MerchantDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.MccParamModel;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.ruleManager.dao.hibernate.hql.StlmReguDaoHibernateHQL;
import cn.yufu.posp.ruleManager.domain.model.TblStlmRegu;

public class StlmReguLogic extends BaseLogic implements StlmReguLogicInterface {

	private static final Log log = LogFactory.getLog("rule");

	public StlmReguLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(TblStlmRegu queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("StlmReguLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			StlmReguDaoHibernateHQL dao = (StlmReguDaoHibernateHQL) getBean("stlmReguDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			MerchantDaoHibernateHQL mercDao = (MerchantDaoHibernateHQL) getBean("merchantDao");
			TblStlmRegu stlmRegu = new TblStlmRegu();
			for (int i = 0; i < list.size(); i++) {
				// ��Ʒ��������
				stlmRegu = (TblStlmRegu) list.get(i);
				if (!"ALL".equals(stlmRegu.getMcc().trim().toUpperCase())){
					stlmRegu.setMccName(mercDao.findMccName(stlmRegu.getMcc(), ud));
				}else{
					stlmRegu.setMccName("ȫ������");
				}
			}

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("StlmReguLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguLogic.queryAll()����ʱ�����쳣��");
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

			log.info("StlmReguLogic.deleteItem()��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			StlmReguDaoHibernateHQL dao = (StlmReguDaoHibernateHQL) getBean("stlmReguDao");

			dao.deleteItem(newKeys, ud);

			log.info("StlmReguLogic.deleteItem()�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguLogic.deleteItem()ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		TblStlmRegu newModel = new TblStlmRegu();

		HashMap parameteMap = new HashMap();
		try {
			log.info("StlmReguLogic.findItem()��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			StlmReguDaoHibernateHQL dao = (StlmReguDaoHibernateHQL) getBean("stlmReguDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			}

			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("StlmReguLogic.findItem()�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguLogic.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(TblStlmRegu newModel, UserData ud) throws OAException {
		try {
			// ��֤�̻������Ƿ����
			if (newModel.getMcc() != null && !"".equals(newModel.getMcc()) && !"ALL".equals(newModel.getMcc().trim().toUpperCase())) {
				MccParamDaoHibernateHQL mdao = (MccParamDaoHibernateHQL) getBean("mccDao");
				MccParamModel mpm = mdao.findItem(newModel.getMcc().toUpperCase(), ud);
				if (mpm == null) {
					throw new OAException("��������̻����������ݿ��в����ڣ����������룡");
				}
			}
			if (!"ALL".equals(newModel.getMchtNo().trim().toUpperCase())) {
				MerchantCardDaoHibernateHQL mercDao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");
				// ��֤�̻����Ƿ��Ѿ����������ݿ���
				MerchantBaseModel msm = mercDao.findItemById(newModel.getMchtNo().toUpperCase(), ud);
				if (msm == null) {
					throw new OAException("�̻����:" + newModel.getMchtNo() + " �ļ�¼�����ݿ��в����ڣ�");
				}
			}
//			if (!"ALL".equals(newModel.getTermNo().trim().toUpperCase())) {
//				EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
//				EdcTerminal edcTerminal = new EdcTerminal();
//				EdcTerminalId id = new EdcTerminalId();
//				id.setTerminalId(newModel.getTermNo().toUpperCase());
//				edcTerminal.setId(id);
//				List<EdcTerminal> list = dao.findEdcTerminalItemByKey(edcTerminal, ud);
//				if (list.size() < 1) {
//					throw new OAException("�ն˺�:" + newModel.getTermNo() + " �ļ�¼�����ݿ��в����ڣ�");
//				}
//			}
			log.info("StlmReguLogic.saveItem()��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			StlmReguDaoHibernateHQL dao = (StlmReguDaoHibernateHQL) getBean("stlmReguDao");
			dao.saveItem(newModel, ud);
			log.info("StlmReguLogic.saveItem()�������ã��޸�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguLogic.saveItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(TblStlmRegu newModel, UserData ud) throws OAException {
		try {
			log.info("StlmReguLogic.createItem()��ʼ���ã��½�һ����¼��" + ud.getUserId());
			// ��֤�̻������Ƿ����
			if (newModel.getMcc() != null && !"".equals(newModel.getMcc()) && !"ALL".equals(newModel.getMcc().trim().toUpperCase())) {
				MccParamDaoHibernateHQL mdao = (MccParamDaoHibernateHQL) getBean("mccDao");
				MccParamModel mpm = mdao.findItem(newModel.getMcc().toUpperCase(), ud);
				if (mpm == null) {
					throw new OAException("��������̻����������ݿ��в����ڣ����������룡");
				}
			}
			if (!"ALL".equals(newModel.getMchtNo().trim().toUpperCase())) {
				MerchantCardDaoHibernateHQL mercDao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");
				// ��֤�̻����Ƿ��Ѿ����������ݿ���
				MerchantBaseModel msm = mercDao.findItemById(newModel.getMchtNo().toUpperCase(), ud);
				if (msm == null) {
					throw new OAException("�̻����:" + newModel.getMchtNo() + " �ļ�¼�����ݿ��в����ڣ�");
				}
			}
			// if (!"ALL".equals(newModel.getTermNo().trim().toUpperCase())) {
			// EdcCommonDaoHibernateHQL dao = (EdcCommonDaoHibernateHQL)
			// getBean("EdcCommonDao");
			// EdcTerminal edcTerminal = new EdcTerminal();
			// EdcTerminalId id = new EdcTerminalId();
			// id.setTerminalId(newModel.getTermNo().toUpperCase());
			// edcTerminal.setId(id);
			// List<EdcTerminal> list =
			// dao.findEdcTerminalItemByKey(edcTerminal, ud);
			// if (list.size() < 1) {
			// throw new OAException("�ն˺�:" + newModel.getTermNo() +
			// " �ļ�¼�����ݿ��в����ڣ�");
			// }
			// }
			StlmReguDaoHibernateHQL dao = (StlmReguDaoHibernateHQL) getBean("stlmReguDao");
			dao.createItem(newModel, ud);

			log.info("StlmReguLogic.createItem()�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

}
