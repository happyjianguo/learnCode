package cn.yufu.posp.logManager.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.logManager.dao.hibernate.hql.LogDaoHibernateHQL;
import cn.yufu.posp.logManager.domain.model.OperateLog;

public class LogLogic extends BaseLogic implements LogLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public LogLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(OperateLog queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("LogLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			LogDaoHibernateHQL dao = (LogDaoHibernateHQL) getBean("logDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("LogLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogLogic.queryAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		OperateLog newModel = new OperateLog();

		HashMap parameteMap = new HashMap();
		try {
			log.info("LogLogic.findItem()��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			LogDaoHibernateHQL dao = (LogDaoHibernateHQL) getBean("logDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			}

			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("LogLogic.findItem()�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogLogic.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(OperateLog newModel, UserData ud) throws OAException {
		try {
			log.info("LogLogic.createItem()��ʼ���ã��½�һ����¼��" + ud.getUserId());
			LogDaoHibernateHQL dao = (LogDaoHibernateHQL) getBean("logDao");
			
			dao.createItem(newModel, ud);
			
			log.info("LogLogic.createItem()�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

}
