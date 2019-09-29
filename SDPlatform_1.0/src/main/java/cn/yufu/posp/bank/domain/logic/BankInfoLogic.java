package cn.yufu.posp.bank.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.bank.dao.hibernate.hql.BankInfoDaoHibernateHQL;
import cn.yufu.posp.bank.domain.model.BankInfoId;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public class BankInfoLogic extends BaseLogic implements BankInfoLogicInterface {

	private static final Log log = LogFactory.getLog("bankInfo");

	public BankInfoLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(BankInfoId queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("BankInfoLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());
			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;
			BankInfoDaoHibernateHQL dao = (BankInfoDaoHibernateHQL) getBean("bankinfoDao");
			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);
			
			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			log.info("totalCount=" + totalCount + ";list.size()=" + list.size());
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					BankInfoId bankInfo = (BankInfoId) list.get(i);
					bankInfo.setBankTypeName(dao.findTypeNameById(bankInfo.getBankType().trim(), ud));
				}
			}
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("BankInfoLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("BankInfoLogic.queryAll()����ʱ�����쳣��");
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
			log.info("BankInfoLogic.deleteItem()��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			BankInfoDaoHibernateHQL dao = (BankInfoDaoHibernateHQL) getBean("bankinfoDao");

			dao.deleteItem(newKeys, ud);

			log.info("BankInfoLogic.deleteItem()�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoLogic.deleteItem()ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		BankInfoId newModel = new BankInfoId();

		HashMap parameteMap = new HashMap();
		try {
			log.info("BankInfoLogic.findItem()��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			BankInfoDaoHibernateHQL dao = (BankInfoDaoHibernateHQL) getBean("bankinfoDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			} else {
				newModel.setBankTypeName(dao.findTypeNameById(newModel.getBankType(), ud));
			}

			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("BankInfoLogic.findItem()�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoLogic.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(BankInfoId newModel, UserData ud) throws OAException {
		try {
			log.info("BankInfoLogic.saveItem()��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			BankInfoDaoHibernateHQL dao = (BankInfoDaoHibernateHQL) getBean("bankinfoDao");
			dao.saveItem(newModel, ud);
			log.info("BankInfoLogic.saveItem()�������ã��޸�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoLogic.saveItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(BankInfoId newModel, UserData ud) throws OAException {
		try {
			log.info("BankInfoLogic.createItem()��ʼ���ã��½�һ����¼��" + ud.getUserId());

			BankInfoDaoHibernateHQL dao = (BankInfoDaoHibernateHQL) getBean("bankinfoDao");
			dao.createItem(newModel, ud);

			log.info("BankInfoLogic.createItem()�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
