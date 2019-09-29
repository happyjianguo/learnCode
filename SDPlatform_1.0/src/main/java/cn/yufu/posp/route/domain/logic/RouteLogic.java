package cn.yufu.posp.route.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.route.dao.hibernate.hql.RouteDaoHibernateHQL;

public class RouteLogic extends BaseLogic implements RouteLogicInterface {

	private static final Log log = LogFactory.getLog("route");

	/**
	 * @roseuid 44BAF7190128
	 */
	public RouteLogic() {

	}

	/**
	 * ���һ�����Ϣ
	 */
	public PageInfoModel queryRoute(Object queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("JgLogic.queryJG()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			RouteDaoHibernateHQL jgDao = (RouteDaoHibernateHQL) getBean("routeDao");

			// �õ���¼��������
			totalCount = jgDao.queryCount(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = jgDao.query(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

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
	public void deleteRoute(Object newKeys, UserData ud) throws OAException {
		try {
			log.info("��ʼ���ã�ɾ��·����Ϣ��");

			RouteDaoHibernateHQL jgDao = (RouteDaoHibernateHQL) getBean("routeDao");

			jgDao.deleteRoute(newKeys, ud);

			log.info("�������ã�ɾ��·����Ϣ��");

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
	public void createRoute(Object newModel, UserData ud) throws OAException {
		try {
			log.info("��ʼ���ã��½�·����Ϣ��");

			RouteDaoHibernateHQL jgDao = (RouteDaoHibernateHQL) getBean("routeDao");
			jgDao.create(newModel, ud);

			log.info("�������ã��½�·����Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("createRoute����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	/**
	 * ��key�������Ϣ
	 */
	public HashMap queryRouteByKey(String newKey, UserData ud) throws OAException {
		Object newModel = null;

		HashMap parameteMap = new HashMap();
		try {
			log.info("JgLogic.queryJgByKey(long key, UserData ud)��ʼ���ã��������Ϣ��");

			RouteDaoHibernateHQL jgDao = (RouteDaoHibernateHQL) getBean("routeDao");
			newModel = jgDao.queryByKey(newKey, ud);

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
	public void saveRoute(Object newModel, UserData ud) throws OAException {
		try {
			log.info("saveJg(JgModel newJg, UserData ud)��ʼ���ã����������Ϣ��");
			RouteDaoHibernateHQL jgDao = (RouteDaoHibernateHQL) getBean("routeDao");
			jgDao.save(newModel, ud);
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
