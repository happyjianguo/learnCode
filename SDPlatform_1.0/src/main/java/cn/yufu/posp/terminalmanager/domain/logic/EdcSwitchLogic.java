package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcSwitchDaoHibernateHQL;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitch;

public class EdcSwitchLogic extends BaseLogic implements EdcSwitchLogicInterface {

	private static final Log log = LogFactory.getLog("edcSwitch");

	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcSwitchLogic() {

	}

	public void createItem(EdcSwitch edcSwitch, UserData ud) throws OAException {
		try {
			log.info("edcSwitchLogic.createItem()��ʼ���ã��½��ն���������Ϣ��");

			EdcSwitchDaoHibernateHQL dao = (EdcSwitchDaoHibernateHQL) getBean("EdcSwitchDao");
			dao.createItem(edcSwitch, ud);

			log.info("edcSwitchLogic.createItem()�������ã��½��ն���������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("edcSwitchLogic.createItem()�½��ն���������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("edcSwitchLogic.deleteItem()��ʼ���ã�ɾ���ն���������Ϣ��");

			EdcSwitchDaoHibernateHQL dao = (EdcSwitchDaoHibernateHQL) getBean("EdcSwitchDao");
			dao.deleteItem(keys, ud);

			log.info("EdcSwitchLogic.deleteItem()�������ã�ɾ���ն���������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcSwitchLogic.deleteItem()ɾ���ն���������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(EdcSwitch key, UserData ud) throws OAException {
		EdcSwitch newModel = new EdcSwitch();
		HashMap map = new HashMap();
		try {
			log.info("EdcSwitchLogic.findItemByKey()��ʼ���ã�����һ���ն���������Ϣ��");

			EdcSwitchDaoHibernateHQL dao = (EdcSwitchDaoHibernateHQL) getBean("EdcSwitchDao");
			newModel = dao.findItemByKey(key, ud);
			if (newModel != null) {
				EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				List<Banktype> list = commondao.queryAllBanktypeItem(null, ud);
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					Banktype banktype = (Banktype) iterator.next();
					if (banktype.getBankType().trim().equals(newModel.getId().getBankType().trim()))
						newModel.getId().setBankTypeName(banktype.getTypeName().trim());
				}

				newModel.getId().setMerchantName(commondao.findMerchantNameByKey(newModel.getId().getMerchantId(), ud));
			}
			map.put("Infolist", newModel);

			log.info("EdcSwitchLogic.findItemByKey()�������ã�����һ���ն���������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcSwitchLogic.findItemByKey()����һ���ն���������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(EdcSwitch EdcSwitch, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("==EdcSwitchLogic.queryAllItem()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcSwitchDaoHibernateHQL dao = (EdcSwitchDaoHibernateHQL) getBean("EdcSwitchDao");

			// �õ���¼��������
			totalCount = dao.queryCount(EdcSwitch, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(EdcSwitch, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			List<EdcSwitch> edcSwitchList = new ArrayList<EdcSwitch>();
			EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			List<Banktype> banktypeList = commondao.queryAllBanktypeItem(null, ud);
			for (int i = 0; i < list.size(); i++) {
				EdcSwitch newModel = (EdcSwitch) list.get(i);
				for (int k = 0; k < banktypeList.size(); k++) {
					Banktype banktype = (Banktype) banktypeList.get(k);
					if (banktype.getBankType().trim().equals(newModel.getId().getBankType().trim()))
						newModel.getId().setBankTypeName(banktype.getTypeName().trim());
				}
				edcSwitchList.add(newModel);
			}

			page.getResultItems().addAll(edcSwitchList);
			page.setTotalCount(totalCount);

			log.info("�ն�ת�� ���ؽ��������==" + list.size() + " ��ʼλ����==" + startIndex + " ҳ����==" + (startIndex + pageSize) / pageSize + " �ܼ�¼��==" + totalCount);
			log.info("EdcSwitchLogic.queryAllItem()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("=EdcSwitchLogic.queryAllItem()��ҳ��ѯ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(EdcSwitch EdcSwitch, UserData ud) throws OAException {
		try {
			log.info("EdcSwitchLogic.saveItem()��ʼ���ã����� ���޸� �ն���������Ϣ��");

			EdcSwitchDaoHibernateHQL dao = (EdcSwitchDaoHibernateHQL) getBean("EdcSwitchDao");
			dao.saveItem(EdcSwitch, ud);

			log.info("EdcSwitchLogic.saveItem()�������ã����� ���޸� �ն���������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcSwitchLogic.saveItem()���� ���޸� ����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

}
