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
import cn.yufu.posp.merchant.dao.hibernate.hql.TblNoPasswdCardBinDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.TblNoPasswdCardBinModel;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogic;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;

public class TblNoPasswdCardBinLogic extends BaseLogic implements TblNoPasswdCardBinLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public TblNoPasswdCardBinLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(TblNoPasswdCardBinModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("TblNoPasswdCardBinLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			TblNoPasswdCardBinDaoHibernateHQL dao = (TblNoPasswdCardBinDaoHibernateHQL) getBean("tblNoPasswdCardBinDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("TblNoPasswdCardBinLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinLogic.queryAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return page;
	}



	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		TblNoPasswdCardBinModel newModel = new TblNoPasswdCardBinModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("TblNoPasswdCardBinLogic.findItem(long key, UserData ud)��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			TblNoPasswdCardBinDaoHibernateHQL dao = (TblNoPasswdCardBinDaoHibernateHQL) getBean("tblNoPasswdCardBinDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			}
			
			parameteMap.put("Infolist", newModel);

			log.info("TblNoPasswdCardBinLogic.findItem(long key, UserData ud)�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinLogic.findItem(long key, UserData ud)��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(TblNoPasswdCardBinModel newModel, UserData ud) throws OAException {
		try {
			log.info("TblNoPasswdCardBinLogic.saveItem(TblNoPasswdCardBinModel newModel, UserData ud)��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			TblNoPasswdCardBinDaoHibernateHQL dao = (TblNoPasswdCardBinDaoHibernateHQL) getBean("tblNoPasswdCardBinDao");
			dao.saveItem(newModel, ud);
			log.info("TblNoPasswdCardBinLogic.saveItem(TblNoPasswdCardBinModel newModel, UserData ud)�������ã��޸�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinLogic.saveItem(TblNoPasswdCardBinModel newModel, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(TblNoPasswdCardBinModel newModel, UserData ud) throws OAException {
		try {
			log.info("TblNoPasswdCardBinLogic.createItem(TblNoPasswdCardBinModel newModel, UserData ud)��ʼ���ã��½�һ����¼��" + ud.getUserId());

			TblNoPasswdCardBinDaoHibernateHQL dao = (TblNoPasswdCardBinDaoHibernateHQL) getBean("tblNoPasswdCardBinDao");
			dao.createItem(newModel, ud);

			log.info("TblNoPasswdCardBinLogic.createItem(TblNoPasswdCardBinModel newModel, UserData ud)�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinLogic.createItem(TblNoPasswdCardBinModel newModel, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}
	
	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("TblNoPasswdCardBinLogic.deleteItem(List newKeys, UserData ud)��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			TblNoPasswdCardBinDaoHibernateHQL dao = (TblNoPasswdCardBinDaoHibernateHQL) getBean("tblNoPasswdCardBinDao");
			
			dao.deleteItem(newKeys, ud);

			log.info("TblNoPasswdCardBinLogic.deleteItem(List newKeys, UserData ud)�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinLogic.deleteItem(List newKeys, UserData ud)ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}


	/**
	 * // ��֤��BIN�Ƿ��Ѿ�����
	 */
	public String findfirstCardBinByKey(TblNoPasswdCardBinModel model, UserData ud) throws OAException {
		try {
			
			TblNoPasswdCardBinDaoHibernateHQL dao = (TblNoPasswdCardBinDaoHibernateHQL) getBean("tblNoPasswdCardBinDao");
			
			return dao.findfirstCardBinByKey(model, ud);

			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinLogic.findfirstCardBinByKey(String newKeys, UserData ud)��֤����Ϣ�Ƿ��Ѿ�����,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}
	
	
}
