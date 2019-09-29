package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.TblMerchantTranParamDaoHibernateHQL;
import cn.yufu.posp.merchant.dao.hibernate.hql.TblNoPasswdCardBinDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.TblMerchantTranParamModel;
import cn.yufu.posp.merchant.domain.model.TblNoPasswdCardBinModel;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogic;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;

public class TblMerchantTranParamLogic extends BaseLogic implements TblMerchantTranParamLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public TblMerchantTranParamLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(TblMerchantTranParamModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("TblMerchantTranParamLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			TblMerchantTranParamDaoHibernateHQL dao = (TblMerchantTranParamDaoHibernateHQL) getBean("tblMerchantTranParamDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("TblMerchantTranParamLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamLogic.queryAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return page;
	}



	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		TblMerchantTranParamModel newModel = new TblMerchantTranParamModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("TblMerchantTranParamLogic.findItem(long key, UserData ud)��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			TblMerchantTranParamDaoHibernateHQL dao = (TblMerchantTranParamDaoHibernateHQL) getBean("tblMerchantTranParamDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			}
			
			// ��װ�̻�����
			newModel.setMerchantName(dao.findMerchantName(newModel.getMerchantId(), ud));
						
			parameteMap.put("Infolist", newModel);

			log.info("TblMerchantTranParamLogic.findItem(long key, UserData ud)�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamLogic.findItem(long key, UserData ud)��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(TblMerchantTranParamModel newModel, UserData ud) throws OAException {
		try {
			log.info("TblMerchantTranParamLogic.saveItem(TblMerchantTranParamModel newModel, UserData ud)��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			TblMerchantTranParamDaoHibernateHQL dao = (TblMerchantTranParamDaoHibernateHQL) getBean("tblMerchantTranParamDao");
			dao.saveItem(newModel, ud);
			log.info("TblMerchantTranParamLogic.saveItem(TblMerchantTranParamModel newModel, UserData ud)�������ã��޸�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamLogic.saveItem(TblMerchantTranParamModel newModel, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(TblMerchantTranParamModel newModel, UserData ud) throws OAException {
		try {
			log.info("TblMerchantTranParamLogic.createItem(TblMerchantTranParamModel newModel, UserData ud)��ʼ���ã��½�һ����¼��" + ud.getUserId());

			TblMerchantTranParamDaoHibernateHQL dao = (TblMerchantTranParamDaoHibernateHQL) getBean("tblMerchantTranParamDao");
			dao.createItem(newModel, ud);

			log.info("TblMerchantTranParamLogic.createItem(TblMerchantTranParamModel newModel, UserData ud)�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamLogic.createItem(TblMerchantTranParamModel newModel, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}


	/**
	 * // ��֤�̻�����Ƿ��Ѿ�����
	 */
	public String checkMerchantId(String merchantId, UserData ud) throws OAException {
		try {
			
			TblMerchantTranParamDaoHibernateHQL dao = (TblMerchantTranParamDaoHibernateHQL) getBean("tblMerchantTranParamDao");
			
			return dao.checkMerchantId(merchantId, ud);

			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinLogic.findfirstCardBinByKey(String newKeys, UserData ud)��֤����Ϣ�Ƿ��Ѿ�����,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}
}
