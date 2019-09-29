package cn.yufu.posp.jgmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.jgmanager.dao.hibernate.hql.JgDaoHibernateHQL;
import cn.yufu.posp.jgmanager.domain.model.JgModel;

public class JgLogic extends BaseLogic implements JgLogicInterface {

	private static final Log log = LogFactory.getLog("jg");

	/**
	 * @roseuid 44BAF7190128
	 */
	public JgLogic() {

	}

	/**
	 * ���һ�����Ϣ
	 */
	public PageInfoModel queryJg(JgModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("JgLogic.queryJG()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			JgDaoHibernateHQL jgDao = (JgDaoHibernateHQL) getBean("JgDao");

			// �õ���¼��������
			totalCount = jgDao.queryJgCount(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = jgDao.queryJg(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			// System.out.println(list.size() +
			// "================rtertwetert=============" + totalCount);
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("JgLogic.queryJg()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgLogic.queryJg()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	/**
	 * ɾ��������Ϣ
	 */
	public void deleteJg(List newKeys, UserData ud) throws OAException {
		try {
			log.info("deleteJg(List newKeys, UserData ud)��ʼ���ã�ɾ��������Ϣ��");

			JgDaoHibernateHQL jgDao = (JgDaoHibernateHQL) getBean("JgDao");

			jgDao.deleteJg(newKeys, ud);
			jgDao.deleteUser(newKeys, ud);
			log.info("deleteJg(List newKeys, UserData ud)�������ã�ɾ��������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("deleteJgs(List newKeys, UserData ud)ɾ��������Ϣ,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	/**
	 * �½�������Ϣ
	 */
	public void createJg(JgModel newModel, UserData ud) throws OAException {
		try {
			log.info("createJg(JgModel newJg, UserData ud)��ʼ���ã��½�������Ϣ��");

			JgDaoHibernateHQL jgDao = (JgDaoHibernateHQL) getBean("JgDao");
			jgDao.createJg(newModel, ud);

			log.info("createJg(JgModel newJg, UserData ud)�������ã��½�������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("createJg(JgModel newJg, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	/**
	 * ��key�������Ϣ
	 */
	public HashMap queryJgByKey(String newKey, UserData ud) throws OAException {
		JgModel newModel = new JgModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("JgLogic.queryJgByKey(long key, UserData ud)��ʼ���ã��������Ϣ��");

			JgDaoHibernateHQL jgDao = (JgDaoHibernateHQL) getBean("JgDao");
			newModel = jgDao.queryJgByKey(newKey, ud);

			// ��model
			parameteMap.put("Infolist", newModel);
			log.info("JgLogic.queryJgByKey(long key, UserData ud)�������ã��������Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgLogic.queryJgByKey(long key, UserData ud)�������Ϣ�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return parameteMap;
	}

	/**
	 * ���������Ϣ
	 */
	public void saveJg(JgModel newModel, UserData ud) throws OAException {
		try {
			log.info("saveJg(JgModel newJg, UserData ud)��ʼ���ã����������Ϣ��");
			JgDaoHibernateHQL jgDao = (JgDaoHibernateHQL) getBean("JgDao");
			jgDao.saveJg(newModel, ud);
			log.info("saveJg(JgModel newJg, UserData ud)�������ã����������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("saveJg(JgModel newJg, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

}
