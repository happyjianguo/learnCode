package cn.yufu.posp.bank.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.bank.dao.hibernate.hql.BanktypeDaoHibernateHQL;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public class BanktypeLogic extends BaseLogic implements BanktypeLogicInterface {

	private static final Log log = LogFactory.getLog("bankInfo");

	public BanktypeLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(Banktype queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("BanktypeLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());
			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;
			BanktypeDaoHibernateHQL dao = (BanktypeDaoHibernateHQL) getBean("banktypeDao");
			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);
			
			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			log.info("totalCount=" + totalCount + ";list.size()=" + list.size());
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("BanktypeLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("BanktypeLogic.queryAll()����ʱ�����쳣��");
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
			log.info("BanktypeLogic.deleteItem()��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			BanktypeDaoHibernateHQL dao = (BanktypeDaoHibernateHQL) getBean("banktypeDao");

			dao.deleteItem(newKeys, ud);

			log.info("BanktypeLogic.deleteItem()�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeLogic.deleteItem()ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		Banktype newModel = new Banktype();

		HashMap parameteMap = new HashMap();
		try {
			log.info("BanktypeLogic.findItem()��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			BanktypeDaoHibernateHQL dao = (BanktypeDaoHibernateHQL) getBean("banktypeDao");
			newModel = dao.findItem(newKey, ud);
			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("BanktypeLogic.findItem()�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeLogic.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(Banktype newModel, UserData ud) throws OAException {
		try {
			log.info("BanktypeLogic.saveItem()��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			BanktypeDaoHibernateHQL dao = (BanktypeDaoHibernateHQL) getBean("banktypeDao");
			dao.saveItem(newModel, ud);
			log.info("BanktypeLogic.saveItem()�������ã��޸�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeLogic.saveItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(Banktype newModel, UserData ud) throws OAException {
		try {
			log.info("BanktypeLogic.createItem()��ʼ���ã��½�һ����¼��" + ud.getUserId());

			BanktypeDaoHibernateHQL dao = (BanktypeDaoHibernateHQL) getBean("banktypeDao");
			dao.createItem(newModel, ud);

			log.info("BanktypeLogic.createItem()�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
