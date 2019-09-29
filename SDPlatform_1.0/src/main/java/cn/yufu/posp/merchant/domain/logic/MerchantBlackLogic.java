package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.MerchantBlackDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.MerchantBlackModel;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogic;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;

public class MerchantBlackLogic extends BaseLogic implements MerchantBlackLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantBlackLogic() {

	}

	/**
	 * ��ѯ���м�¼
	 */
	public PageInfoModel queryAll(MerchantBlackModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("MerchantCardLogic.queryAll()��ʼ���ã���ҳ��ѯ��" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			MerchantBlackDaoHibernateHQL dao = (MerchantBlackDaoHibernateHQL) getBean("merBlackDao");

			// �õ���¼��������
			totalCount = dao.querySum(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			// for(int i=0;i<list.size();i++){
			// MerchantBlackModel mc = (MerchantBlackModel)list.get(i);
			// mc.setBankType(mc.getBankType().replaceAll(" ", ""));
			// }

			// ��װ�����������ƺͿ���������
			if (list != null && list.size() > 0) {
				EdcCommonLogic elogic = new EdcCommonLogic();
				List<Banktype> banktypeList = elogic.queryAllBanktypeItem(null, ud);
				List<Cardtype> cardtypeList = elogic.queryAllCardtypeItem(null, ud);
				for (int i = 0; i < list.size(); i++) {
					// MerchantBlackModel mcm = (MerchantBlackModel)list.get(i);
					// for(Banktype bvo : banktypeList){
					// if(mcm.getBankType().equals(bvo.getId().getBankType())){
					// mcm.setBankTypeName(bvo.getId().getTypeName());
					// }
					// }
					// for(Cardtype cvo:cardtypeList){
					// if(mcm.getCardType().equals((cvo.getId().getCardType()).replace(" ",
					// ""))){
					// mcm.setCardTypeName(cvo.getId().getTypeName());
					// }
					// }
				}
			}

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("MerchantBlackLogic.queryAll()�������ã���ҳ��ѯ��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackLogic.queryAll()����ʱ�����쳣��");
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
			log.info("MerchantBlackLogic.deleteItem(List newKeys, UserData ud)��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			MerchantBlackDaoHibernateHQL dao = (MerchantBlackDaoHibernateHQL) getBean("merBlackDao");

			dao.deleteItem(newKeys, ud);

			log.info("MerchantBlackLogic.deleteItem(List newKeys, UserData ud)�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackLogic.deleteItem(List newKeys, UserData ud)ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * ��ʾһ����¼
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		MerchantBlackModel newModel = new MerchantBlackModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantBlackLogic.findItem(long key, UserData ud)��ʼ���ã���ʾһ����¼��" + ud.getUserId());

			MerchantBlackDaoHibernateHQL dao = (MerchantBlackDaoHibernateHQL) getBean("merBlackDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("��Ҫ���ҵ���Ϣ�����ڣ�");
			}
			// ��װ�̻�����
			newModel.setMerchantName(dao.findMerchantName(newModel.getMerchantId(), ud));
			// System.out.println("�̻����ƣ�"+newModel.getMerchantName());
			// ��װ�����������Ϳ�������
			// EdcCommonLogic elogic = new EdcCommonLogic();
			// List<Banktype> banktypeList = elogic.queryAllBanktypeItem(null,
			// ud);
			// List<Cardtype> cardtypeList = elogic.queryAllCardtypeItem(null,
			// ud);
			// for(Banktype bt:banktypeList){
			// if(newModel.getBankType().equals(bt.getId().getBankType())){
			// newModel.setBankTypeName(bt.getId().getTypeName());
			// }
			// }
			// for(Cardtype ct:cardtypeList){
			// if(newModel.getCardType().equals((ct.getId().getCardType()).replace(" ",
			// ""))){
			// System.out.println("===������id==="+(ct.getId().getCardType()).replaceAll(" ",
			// "")+"AAAA");
			// newModel.setCardTypeName(ct.getId().getTypeName());
			// }
			// }
			// System.out.println("������������=="+newModel.getBankTypeName());
			// System.out.println("����������=="+newModel.getCardTypeName());
			// ��model
			parameteMap.put("Infolist", newModel);

			log.info("MerchantBlackLogic.findItem(long key, UserData ud)�������ã���ʾһ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackLogic.findItem(long key, UserData ud)��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * �޸�һ����¼
	 */
	public void saveItem(MerchantBlackModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantBlackLogic.saveItem(MerchantBlackModel newModel, UserData ud)��ʼ���ã��޸�һ����¼��" + ud.getUserId());
			MerchantBlackDaoHibernateHQL dao = (MerchantBlackDaoHibernateHQL) getBean("merBlackDao");
			dao.saveItem(newModel, ud);
			log.info("MerchantBlackLogic.saveItem(MerchantBlackModel newModel, UserData ud)�������ã��޸�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackLogic.saveItem(MerchantBlackModel newModel, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * �½�һ����¼
	 */
	public void createItem(MerchantBlackModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantBlackLogic.createItem(MerchantBlackModel newModel, UserData ud)��ʼ���ã��½�һ����¼��" + ud.getUserId());

			MerchantBlackDaoHibernateHQL dao = (MerchantBlackDaoHibernateHQL) getBean("merBlackDao");
			dao.createItem(newModel, ud);

			log.info("MerchantBlackLogic.createItem(MerchantBlackModel newModel, UserData ud)�������ã��½�һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackLogic.createItem(MerchantBlackModel newModel, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * Ajax��ʾ�̻�����
	 */
	public String findMerchantName(String merchantId, UserData ud) {
		MerchantBlackDaoHibernateHQL dao = (MerchantBlackDaoHibernateHQL) getBean("merBlackDao");
		return dao.findMerchantName(merchantId, ud);

	}

}
