package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.SysParameterDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;

public class SysParameterLogic extends BaseLogic implements SysParameterLogicInterface {

	private static final Log log = LogFactory.getLog("sysParameter");

	/**
	 * @roseuid 44BAF7190128
	 */
	public SysParameterLogic() {

	}

	public void createItem(SysParameter sysParameter, UserData ud) throws OAException {
		try {
			log.info("SysParameterLogic.createItem()��ʼ���ã��½�һ����Ϣ��");

			SysParameterDaoHibernateHQL dao = (SysParameterDaoHibernateHQL) getBean("SysParameterDao");
			dao.createItem(sysParameter, ud);

			log.info("SysParameterLogic.createItem()�������ã��½�һ����Ϣ��");

		} catch (Exception e) {

			log.info("SysParameterLogic.createItem()�½�һ����Ϣ�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("SysParameterLogic.deleteItem()��ʼ���ã�ɾ����Ϣ��");

			SysParameterDaoHibernateHQL dao = (SysParameterDaoHibernateHQL) getBean("SysParameterDao");
			dao.deleteItem(keys, ud);

			log.info("SysParameterLogic.deleteItem()�������ã�ɾ����Ϣ��");

		} catch (Exception e) {

			log.info("SysParameterLogic.deleteItem()ɾ����Ϣ�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(SysParameter key, UserData ud) throws OAException {
		SysParameter newModel = new SysParameter();
		HashMap map = new HashMap();
		try {
			log.info("SysParameterLogic.findItemByKey()��ʼ���ã�����һ����Ϣ��");

			SysParameterDaoHibernateHQL dao = (SysParameterDaoHibernateHQL) getBean("SysParameterDao");
			newModel = dao.findItemByKey(key, ud);

			map.put("Infolist", newModel);

			log.info("SysParameterLogic.findItemByKey()�������ã�����һ����Ϣ��");

		} catch (Exception e) {

			log.info("SysParameterLogic.findItemByKey()����һ����Ϣ�������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(SysParameter sysParameter, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("SysParameterLogic.queryAllItem()��ʼ���ã���ҳ��ѯ");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			SysParameterDaoHibernateHQL dao = (SysParameterDaoHibernateHQL) getBean("SysParameterDao");

			// �õ���¼��������
			totalCount = dao.queryCount(sysParameter, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(sysParameter, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("����==" + list.size() + "��ʼλ����==" + startIndex + "ҳ����==" + (startIndex + pageSize) / pageSize + "�ܼ�¼��==" + totalCount + "");
			log.info("SysParameterLogic.queryAllItem()�������ã���ҳ��ѯ");

		} catch (Exception e) {

			log.info("SysParameterLogic.queryAllItem()��ҳ��ѯ�������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(SysParameter sysParameter, UserData ud) throws OAException {
		try {
			log.info("SysParameterLogic.saveItem()��ʼ���ã��޸�һ����Ϣ");

			SysParameterDaoHibernateHQL dao = (SysParameterDaoHibernateHQL) getBean("SysParameterDao");
			dao.saveItem(sysParameter, ud);

			log.info("SysParameterLogic.saveItem()�������ã��޸�һ����Ϣ");

		} catch (Exception e) {

			log.info("SysParameterLogic.saveItem()�޸�һ����Ϣ�������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	@Override
	public List queryAllItem(SysParameter sysParameter, UserData ud) throws OAException {
		List list = new ArrayList();

		try {
			log.info("SysParameterLogic.queryAllItem()��ʼ���ã���ҳ��ѯ");
			SysParameterDaoHibernateHQL dao = (SysParameterDaoHibernateHQL) getBean("SysParameterDao");

			list = dao.queryAllItem(sysParameter, ud);

			log.info("SysParameterLogic.queryAllItem()�������ã���ҳ��ѯ");

		} catch (Exception e) {

			log.info("SysParameterLogic.queryAllItem()��ҳ��ѯ�������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return list;
	}

	@Override
	public SysParameter findItemByKeyValue(SysParameter key, UserData ud) throws OAException {
		SysParameter newModel = new SysParameter();
		try {
			log.info("SysParameterLogic.findItemByKeyValue()��ʼ���ã�����һ����Ϣ��");

			SysParameterDaoHibernateHQL dao = (SysParameterDaoHibernateHQL) getBean("SysParameterDao");
			newModel = dao.findItemByKeyValue(key, ud);

			log.info("SysParameterLogic.findItemByKeyValue()�������ã�����һ����Ϣ��");

		} catch (Exception e) {

			log.info("SysParameterLogic.findItemByKeyValue()����һ����Ϣ�������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return newModel;
	}

}
