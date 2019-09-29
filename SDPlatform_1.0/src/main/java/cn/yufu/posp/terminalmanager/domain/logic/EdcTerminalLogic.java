package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.core.common.util.CommonDomain;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.keyManager.dao.hibernate.hql.TMKMasterKEYDaoHibernateHQL;
import cn.yufu.posp.keyManager.domain.model.BtsKey;
import cn.yufu.posp.merchant.domain.logic.MerchantService;
import cn.yufu.posp.merchant.domain.logic.MerchantUtil;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCardDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcSwitchDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcTerminalDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcCard;
import cn.yufu.posp.terminalmanager.domain.model.EdcCardId;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitch;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitchId;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;

public class EdcTerminalLogic extends BaseLogic implements EdcTerminalLogicInterface {

	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcTerminalLogic() {

	}

	public void createItem(EdcTerminal edcTerminal, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalLogic.createItem()��ʼ���ã��½��ն�������Ϣ��");

			EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");
			dao.createItem(edcTerminal, ud);

			// �½��ն�ʱͬ���ն���Կ�� ���������ն�����Կ��
			TMKMasterKEYDaoHibernateHQL tmkDao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");
			// �½�һ��Model
			BtsKey btsKey = new BtsKey();
			btsKey.setSettleFlag("0");// 0--���ն� 1--�����ն�����Կ 2--��ǩ��
			btsKey.setBatchNo("1");
			btsKey.setOperNo("001");
			btsKey.setOperPasswd("00000000");
			btsKey.setMerchantId(edcTerminal.getId().getMerchantId());
			btsKey.setTerminalId(edcTerminal.getId().getTerminalId());
			tmkDao.createItem(btsKey, ud);
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				MerchantService service = (MerchantService) getBean("syncMerchant");
				List<EdcTerminal> list = new ArrayList<EdcTerminal>();
				list.add(edcTerminal);
				String result = service.syncEdcTerminal(MerchantUtil.edcTerminalToJson(list, 1));
				JSONObject ret = JSONObject.fromObject(result);
				if ("0".equals(ret.get("code"))) {
					// ͬ���ɹ�
					log.info("MerchantLogic.saveItem()ͬ�������ն��������ݳɹ���" + ud.getUserId());
				} else {
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("EdcTerminalLogic.createItem()�������ã��½��ն�������Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalLogic.createItem()�½��ն�������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalLogic.deleteItem()��ʼ���ã�ɾ���ն�������Ϣ��");

			List<EdcCard> edcCardKeys = new ArrayList<EdcCard>();
			List<EdcSwitch> edcSwitchKeys = new ArrayList<EdcSwitch>();

			for (int i = 0; i < keys.size(); i++) {
				EdcTerminal model = (EdcTerminal) keys.get(i);

				String merchantId = model.getId().getMerchantId();
				String terminalId = model.getId().getTerminalId();

				EdcCard edcCard = new EdcCard();
				EdcCardId edcCardId = new EdcCardId();
				edcCardId.setMerchantId(merchantId);
				edcCardId.setTerminalId(terminalId);
				edcCard.setId(edcCardId);

				EdcSwitch edcSwitch = new EdcSwitch();
				EdcSwitchId edcSwitchId = new EdcSwitchId();
				edcSwitchId.setMerchantId(merchantId);
				edcSwitchId.setTerminalId(terminalId);
				edcSwitch.setId(edcSwitchId);

				EdcCommonDaoHibernateHQL commonDao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");

				// ɾ������ת��
				EdcSwitchDaoHibernateHQL dao1 = (EdcSwitchDaoHibernateHQL) getBean("EdcSwitchDao");
				edcSwitchKeys = commonDao.findEdcSwitchItemByKey(edcSwitch, ud);
				dao1.deleteItem(edcSwitchKeys, ud);

				// ɾ��������
				EdcCardDaoHibernateHQL dao2 = (EdcCardDaoHibernateHQL) getBean("EdcCardDao");
				edcCardKeys = commonDao.findEdcCardItemByKey(edcCard, ud);
				dao2.deleteItem(edcCardKeys, ud);
				
				// ɾ����Կ��
				TMKMasterKEYDaoHibernateHQL tmkDao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");
				BtsKey key = new BtsKey();
				key.setMerchantId(merchantId);
				key.setTerminalId(terminalId);
				key = tmkDao.findItemByKey(key , ud);
				tmkDao.deleteItem(key, ud);

			}
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				MerchantService service = (MerchantService) getBean("syncMerchant");
				String result = service.syncEdcTerminal(MerchantUtil.edcTerminalToJson(keys, 2));
				JSONObject ret = JSONObject.fromObject(result);
				if ("0".equals(ret.get("code"))) {
					// ͬ���ɹ�
					log.info("MerchantLogic.saveItem()ͬ�������ն��������ݳɹ���" + ud.getUserId());
				} else {
					throw new OAException(ret.getString("msg"));
				}
			}
			// ɾ���ն������趨
			EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");
			dao.deleteItem(keys, ud);

			log.info("EdcTerminalLogic.deleteItem()�������ã�ɾ���ն�������Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalLogic.deleteItem()ɾ���ն�������Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(EdcTerminal key, UserData ud) throws OAException {
		EdcTerminal newModel = new EdcTerminal();
		HashMap map = new HashMap();
		try {
			log.info("EdcTerminalLogic.findItemByKey()��ʼ���ã�����һ����Ϣ��");

			EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");
			newModel = dao.findItemByKey(key, ud);

			if (newModel != null) {
				EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				newModel.getId().setMerchanName(commondao.findMerchantNameByKey(newModel.getId().getMerchantId(), ud));
			}
			map.put("Infolist", newModel);

			log.info("EdcTerminalLogic.findItemByKey()�������ã�����һ����Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalLogic.findItemByKey()����һ����Ϣ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(EdcTerminal edcTerminal, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("EdcTerminalLogic.queryAllItem()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");

			// �õ���¼��������
			totalCount = dao.queryCount(edcTerminal, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(edcTerminal, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("���ؽ��������==" + list.size() + "��ʼλ����==" + startIndex + "ҳ����==" + (startIndex + pageSize) / pageSize + "�ܼ�¼��==" + totalCount);

			log.info("EdcTerminalLogic.queryAllItem()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalLogic.ueryAllItem()��ҳ��ѯ������ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(EdcTerminal edcTerminal, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalLogic.saveItem()��ʼ���ã����� ���޸� ��Ϣ��");

			EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");

			dao.saveItem(edcTerminal, ud);
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				MerchantService service = (MerchantService) getBean("syncMerchant");
				List<EdcTerminal> list = new ArrayList<EdcTerminal>();
				list.add(edcTerminal);
				String result = service.syncEdcTerminal(MerchantUtil.edcTerminalToJson(list, 3));
				JSONObject ret = JSONObject.fromObject(result);
				if ("0".equals(ret.get("code"))) {
					// ͬ���ɹ�
					log.info("MerchantLogic.saveItem()ͬ���޸��ն��������ݳɹ���" + ud.getUserId());
				} else {
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("EdcTerminalLogic.EdcTerminalLogic.saveItem()�������ã����� ���޸� ��Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalLogic.saveItem()���� ���޸� ����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	public void saveUploadItem(List<EdcTerminal> edcTerminalList, UserData ud) throws OAException {
		try {
			TMKMasterKEYDaoHibernateHQL tmkDao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");
			EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");
			for (int i = 0; i < edcTerminalList.size(); i++) {
				// �����µ���Ϣ
				dao.createItem(edcTerminalList.get(i), ud);
				// �½��ն�ʱͬ���ն���Կ�� ���������ն�����Կ��
				// �½�һ��Model
				BtsKey btsKey = new BtsKey();
				btsKey.setSettleFlag("0");// 0--���ն� 1--�����ն�����Կ 2--��ǩ��
				btsKey.setBatchNo("1");
				btsKey.setOperNo("001");
				btsKey.setOperPasswd("00000000");
				btsKey.setMerchantId(edcTerminalList.get(i).getId().getMerchantId());
				btsKey.setTerminalId(edcTerminalList.get(i).getId().getTerminalId());
				tmkDao.createItem(btsKey, ud);
			}
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				MerchantService service = (MerchantService) getBean("syncMerchant");
				String result = service.syncEdcTerminal(MerchantUtil.edcTerminalToJson(edcTerminalList, 1));
				JSONObject ret = JSONObject.fromObject(result);
				if ("0".equals(ret.get("code"))) {
					// ͬ���ɹ�
					log.info("MerchantLogic.saveItem()ͬ�������ն��������ݳɹ���" + ud.getUserId());
				} else {
					throw new OAException(ret.getString("msg"));
				}
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcTerminalLogic.saveUploadItem()�����ն���Ϣ��ͬ���ն�����Կ��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}
	public List<EdcTerminal> queryTerminalListByMerchantId(String merchantId,String cardBin,UserData ud) throws OAException{
		EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");
		return dao.queryTerminalListByMerchantId(merchantId,cardBin, ud);
	}
	
	/**
	 * �ж����е��ն��Ƿ��Ǹ��̻���
	 * @param merchantId
	 * @param terminals
	 * @param ud
	 * @return	true���в��Ǹ��̻����նˣ�false�����Ǹ��̻����ն�
	 * @throws OAException
	 */
	public boolean checkTerminalIsNotMechants(String merchantId, String terminals,
			UserData ud) throws OAException{
		EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");
		return dao.checkTerminalIsNotMechants(merchantId, terminals, ud);
	}
}
