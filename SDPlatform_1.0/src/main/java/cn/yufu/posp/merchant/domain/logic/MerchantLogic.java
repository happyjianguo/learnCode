package cn.yufu.posp.merchant.domain.logic;

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
import cn.yufu.posp.merchant.dao.hibernate.hql.MccParamDaoHibernateHQL;
import cn.yufu.posp.merchant.dao.hibernate.hql.MerchantDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.MccParamModel;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.sysparam.domain.model.AreaCodeInfo;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalId;

public class MerchantLogic extends BaseLogic implements MerchantLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(MerchantBaseModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("MerchantLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			MerchantBaseModel merchantM = new MerchantBaseModel();
			for (int i = 0; i < list.size(); i++) {
				// ��Ʒ��������
				merchantM = (MerchantBaseModel) list.get(i);
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

			log.info("MerchantLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantLogic.queryAll()����ʱ�����쳣��");
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

			log.info("MerchantLogic.deleteItem()��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");

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
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				MerchantService service = (MerchantService) getBean("syncMerchant");
				List<MerchantBaseModel> list = new ArrayList<MerchantBaseModel>();
				for (int i = 0; i < newKeys.size(); i++) {
					MerchantBaseModel newModel = new MerchantBaseModel();
					newModel.setMerchantId(new String(newKeys.get(i) + ""));
					list.add(newModel);
				}
				String result = service.syncMerchant(MerchantUtil.merchantToJson(list, 2));
				JSONObject ret = JSONObject.fromObject(result);
				if("0".equals(ret.get("code"))){
					//ͬ���ɹ�
					log.info("MerchantLogic.saveItem()ͬ��ɾ���̻���Ϣ���ݳɹ���" + ud.getUserId());
				}else{
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("MerchantLogic.deleteItem()�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantLogic.deleteItem()ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		MerchantBaseModel newModel = new MerchantBaseModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantLogic.findItem()��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			}

			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("MerchantLogic.findItem()�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantLogic.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(MerchantBaseModel newModel, UserData ud) throws OAException {
		try {
			// ��֤�̻������Ƿ����
			if (newModel.getMcc() != null && !"".equals(newModel.getMcc())) {
				MccParamDaoHibernateHQL mdao = (MccParamDaoHibernateHQL) getBean("mccDao");
				MccParamModel mpm = mdao.findItem(newModel.getMcc(), ud);
				if (mpm == null) {
					throw new OAException("��������̻����������ݿ��в����ڣ����������룡");
				}
			}

			log.info("MerchantLogic.saveItem()��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");
			dao.saveItem(newModel, ud);
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				MerchantService service = (MerchantService) getBean("syncMerchant");
				List<MerchantBaseModel> list = new ArrayList<MerchantBaseModel>();
				list.add(newModel);
				String result = service.syncMerchant(MerchantUtil.merchantToJson(list, 3));
				JSONObject ret = JSONObject.fromObject(result);
				if("0".equals(ret.get("code"))){
					//ͬ���ɹ�
					log.info("MerchantLogic.saveItem()ͬ���޸��̻���Ϣ���ݳɹ���" + ud.getUserId());
				}else{
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("MerchantLogic.saveItem()�������ã��޸�һ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantLogic.saveItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(MerchantBaseModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantLogic.createItem()��ʼ���ã��½�һ����¼��" + ud.getUserId());
			MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");
			// ��֤�̻������Ƿ����
			if (newModel.getMcc() != null && !"".equals(newModel.getMcc())) {
				MccParamDaoHibernateHQL mdao = (MccParamDaoHibernateHQL) getBean("mccDao");
				MccParamModel mpm = mdao.findItem(newModel.getMcc(), ud);
				if (mpm == null) {
					throw new OAException("��������̻����� " + newModel.getMcc() + " �����ݿ��в����ڣ����������룡");
				}
			}
			// ��֤����Ƿ��Ѿ�����-������Ϣ
			try {
				log.info("MerchantDaoHibernateHQLImpl.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
				MerchantBaseModel sm = dao.findItemById(newModel.getMerchantId(), ud);
				log.info("MerchantDaoHibernateHQLImpl.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
				if (sm != null) {
					throw new OAException("�̻����  " + newModel.getMerchantId() + " �Ѿ������ݿ��д��ڣ�");
				}
			} catch (Exception e) {
				throw new OAException(e.getMessage());
			}
			dao.createItem(newModel, ud);
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				MerchantService service = (MerchantService) getBean("syncMerchant");
				List<MerchantBaseModel> list = new ArrayList<MerchantBaseModel>();
				list.add(newModel);
				String result = service.syncMerchant(MerchantUtil.merchantToJson(list, 1));
				JSONObject ret = JSONObject.fromObject(result);
				if("0".equals(ret.get("code"))){
					//ͬ���ɹ�
					log.info("MerchantLogic.createItem()ͬ�������̻���Ϣ���ݳɹ���" + ud.getUserId());
				}else{
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("MerchantLogic.createItem()�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

	/**
	 * Ajax ��ѯǩԼ�кź�������
	 */
	public List<MerchantBaseModel> findSignBankInfo(String bankId, UserData ud) {
		MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");
		List<MerchantBaseModel> list = dao.findSignBankInfo(bankId,ud);
		return list;
	}

	/**
	 * Ajax ��ѯ�̻�����
	 */
	public List<MerchantBaseModel> findMccInfo(String mcc, UserData ud) {
		MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");
		List<MerchantBaseModel> list = dao.findMccInfo(mcc,ud);
		return list;
	}

	/**
	 * ��ѯ����
	 * 
	 * public List findAllJGs(UserData ud){ MerchantDaoHibernateHQL dao =
	 * (MerchantDaoHibernateHQL) getBean("merchantDao"); return
	 * dao.findAllJG(ud); }
	 */

	@Override
	public void saveBaseInfo(MerchantBaseBo model, UserData ud) throws OAException {
		try {
			log.info("MerchantLogic.saveBaseInfo()��ʼ���ã��½�һ����¼��" + ud.getUserId());
			MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");
			// ��֤�̻������Ƿ����
			if (model.getMcc() != null && !"".equals(model.getMcc())) {
				MccParamDaoHibernateHQL mdao = (MccParamDaoHibernateHQL) getBean("mccDao");
				MccParamModel mpm = mdao.findItem(model.getMcc(), ud);
				if (mpm == null) {
					throw new OAException("��������̻����� " + model.getMcc() + " �����ݿ��в����ڣ����������룡");
				}
			}

			dao.saveBaseInfo(model, ud);

			log.info("MerchantLogic.saveBaseInfo()�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

	public MerchantBaseBo findBaseInfoById(String merchantId, UserData ud) throws OAException {

		MerchantBaseBo newModel = new MerchantBaseBo();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantLogic.findBaseInfoById()��ʼ���ã���ʾһ��������Ϣ��¼��" + ud.getUserId());

			MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");
			newModel = dao.findBaseInfoById(merchantId, ud);

			log.info("MerchantLogic.findBaseInfoById()�������ã���ʾһ��������Ϣ��¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantLogic.findBaseInfoById()��ʾһ��������Ϣ��¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
		return newModel;

	}

	@Override
	public List<AreaCodeInfo> findArea(AreaCodeInfo area, UserData ud) {
		MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");
		List<AreaCodeInfo> list = dao.findArea(area,ud);
		return list;
	}
}
