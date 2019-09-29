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
import cn.yufu.posp.merchant.dao.hibernate.hql.MerchantIdentityDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantIdentity;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalId;

public class MerchantIdentityLogic extends BaseLogic implements MerchantIdentityLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantIdentityLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(MerchantIdentity queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("MerchantIdentityLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			MerchantIdentityDaoHibernateHQL dao = (MerchantIdentityDaoHibernateHQL) getBean("merchantIdentityDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("MerchantIdentityLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityLogic.queryAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {

			log.info("MerchantIdentityLogic.deleteItem()��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			MerchantIdentityDaoHibernateHQL dao = (MerchantIdentityDaoHibernateHQL) getBean("merchantIdentityDao");

			for (int i = 0; i < newKeys.size(); i++) {
				String tmp = newKeys.get(i) + "";
				// �жϸ��̻�����Ƿ����ն������趨��Ϣ�������ڣ�����ɾ��
				EdcTerminal et = new EdcTerminal();
				EdcTerminalId etd = new EdcTerminalId();
				EdcCommonDaoHibernateHQL edao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				if (tmp != null && !"".equals(tmp)) {
					etd.setMerchantId(tmp);
					et.setId(etd);
					List<EdcTerminal> list = edao.findEdcTerminalItemByKey(et, ud);
					if (list.size() > 0) {
						throw new OAException("��Ҫɾ�����̻� " + tmp + " �������ն������趨���У�����ɾ������ִ����ز�������ɾ����");
					}
				}

			}
			dao.deleteItem(newKeys, ud);

			log.info("MerchantIdentityLogic.deleteItem()�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityLogic.deleteItem()ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		MerchantIdentity newModel = new MerchantIdentity();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantIdentityLogic.findItem()��ʼ���ã���ʾһ����¼��" + ud.getUserId());
			MerchantIdentityDaoHibernateHQL dao = (MerchantIdentityDaoHibernateHQL) getBean("merchantIdentityDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			}

			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("MerchantIdentityLogic.findItem()�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityLogic.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(MerchantIdentity newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantIdentityLogic.saveItem()��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			MerchantIdentityDaoHibernateHQL dao = (MerchantIdentityDaoHibernateHQL) getBean("merchantIdentityDao");
			dao.saveItem(newModel, ud);
//			MerchantService service = (MerchantService) getBean("syncdata");
//			List<MerchantIdentity> list = new ArrayList<MerchantIdentity>();
//			list.add(newModel);
//			String result = service.syncMerchant(MerchantUtil.merchantToJson(list, 3));
//			JSONObject ret = JSONObject.fromString(result);
//			if("0".equals(ret.get("code"))){
//				//ͬ���ɹ�
//				log.info("MerchantIdentityLogic.saveItem()ͬ���޸��̻���Ϣ���ݳɹ���" + ud.getUserId());
//			}else{
//				throw new OAException(ret.getString("msg"));
//			}
			log.info("MerchantIdentityLogic.saveItem()�������ã��޸�һ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityLogic.saveItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}


	public MerchantBaseBo findBaseInfoById(String merchantId, UserData ud) throws OAException {

		MerchantBaseBo newModel = new MerchantBaseBo();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantIdentityLogic.findBaseInfoById()��ʼ���ã���ʾһ��������Ϣ��¼��" + ud.getUserId());

			MerchantIdentityDaoHibernateHQL dao = (MerchantIdentityDaoHibernateHQL) getBean("merchantIdentityDao");
			newModel = dao.findBaseInfoById(merchantId, ud);

			log.info("MerchantIdentityLogic.findBaseInfoById()�������ã���ʾһ��������Ϣ��¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityLogic.findBaseInfoById()��ʾһ��������Ϣ��¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
		return newModel;

	}
}
