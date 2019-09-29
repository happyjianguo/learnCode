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
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(MerchantBlackModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("MerchantCardLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			MerchantBlackDaoHibernateHQL dao = (MerchantBlackDaoHibernateHQL) getBean("merBlackDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			// for(int i=0;i<list.size();i++){
			// MerchantBlackModel mc = (MerchantBlackModel)list.get(i);
			// mc.setBankType(mc.getBankType().replaceAll(" ", ""));
			// }

			// 封装银行类型名称和卡类型名称
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

			log.info("MerchantBlackLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackLogic.queryAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return page;
	}

	/**
	 * 删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("MerchantBlackLogic.deleteItem(List newKeys, UserData ud)开始调用：删除一条记录。" + ud.getUserId());

			MerchantBlackDaoHibernateHQL dao = (MerchantBlackDaoHibernateHQL) getBean("merBlackDao");

			dao.deleteItem(newKeys, ud);

			log.info("MerchantBlackLogic.deleteItem(List newKeys, UserData ud)结束调用：删除一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackLogic.deleteItem(List newKeys, UserData ud)删除一条记录,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		MerchantBlackModel newModel = new MerchantBlackModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantBlackLogic.findItem(long key, UserData ud)开始调用：显示一条记录。" + ud.getUserId());

			MerchantBlackDaoHibernateHQL dao = (MerchantBlackDaoHibernateHQL) getBean("merBlackDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			}
			// 封装商户名称
			newModel.setMerchantName(dao.findMerchantName(newModel.getMerchantId(), ud));
			// System.out.println("商户名称："+newModel.getMerchantName());
			// 封装银行类型名和卡类型名
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
			// System.out.println("===卡类型id==="+(ct.getId().getCardType()).replaceAll(" ",
			// "")+"AAAA");
			// newModel.setCardTypeName(ct.getId().getTypeName());
			// }
			// }
			// System.out.println("银行类型名称=="+newModel.getBankTypeName());
			// System.out.println("卡类型名称=="+newModel.getCardTypeName());
			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("MerchantBlackLogic.findItem(long key, UserData ud)结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackLogic.findItem(long key, UserData ud)显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(MerchantBlackModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantBlackLogic.saveItem(MerchantBlackModel newModel, UserData ud)开始调用：修改一条记录。" + ud.getUserId());
			MerchantBlackDaoHibernateHQL dao = (MerchantBlackDaoHibernateHQL) getBean("merBlackDao");
			dao.saveItem(newModel, ud);
			log.info("MerchantBlackLogic.saveItem(MerchantBlackModel newModel, UserData ud)结束调用：修改一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackLogic.saveItem(MerchantBlackModel newModel, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 新建一条记录
	 */
	public void createItem(MerchantBlackModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantBlackLogic.createItem(MerchantBlackModel newModel, UserData ud)开始调用：新建一条记录。" + ud.getUserId());

			MerchantBlackDaoHibernateHQL dao = (MerchantBlackDaoHibernateHQL) getBean("merBlackDao");
			dao.createItem(newModel, ud);

			log.info("MerchantBlackLogic.createItem(MerchantBlackModel newModel, UserData ud)结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackLogic.createItem(MerchantBlackModel newModel, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * Ajax显示商户名称
	 */
	public String findMerchantName(String merchantId, UserData ud) {
		MerchantBlackDaoHibernateHQL dao = (MerchantBlackDaoHibernateHQL) getBean("merBlackDao");
		return dao.findMerchantName(merchantId, ud);

	}

}
