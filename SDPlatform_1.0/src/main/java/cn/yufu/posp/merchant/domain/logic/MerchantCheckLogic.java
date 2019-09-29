package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.MerchantCheckDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;

public class MerchantCheckLogic extends BaseLogic implements MerchantCheckLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantCheckLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(MerchantBaseBo queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("MerchantCheckLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			MerchantCheckDaoHibernateHQL dao = (MerchantCheckDaoHibernateHQL) getBean("merchantCheckDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			MerchantBaseBo merchantM = new MerchantBaseBo();
			for (int i = 0; i < list.size(); i++) {
				// ��Ʒ��������
				merchantM = (MerchantBaseBo) list.get(i);
				merchantM.setMccName(dao.findMccName(merchantM.getMcc(), ud));
				// ��װ��������
				/*
				 * merchantM.setJgName(dao.findJGById(merchantM.getMerchantId(),
				 * ud));
				 * System.out.println("�̻���ţ�"+merchantM.getMerchantId()+"\t�������ƣ�"
				 * +merchantM.getJgName());
				 */
			}

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("MerchantCheckLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckLogic.queryAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		MerchantBaseBo newModel = new MerchantBaseBo();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantCheckLogic.findItem()��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			MerchantCheckDaoHibernateHQL dao = (MerchantCheckDaoHibernateHQL) getBean("merchantCheckDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			}

			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("MerchantCheckLogic.findItem()�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckLogic.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(MerchantBaseBo newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantCheckLogic.saveItem()��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			MerchantCheckDaoHibernateHQL dao = (MerchantCheckDaoHibernateHQL) getBean("merchantCheckDao");
			dao.saveItem(newModel, ud);
			log.info("MerchantCheckLogic.saveItem()�������ã��޸�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("MerchantCheckLogic.saveItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}


}
