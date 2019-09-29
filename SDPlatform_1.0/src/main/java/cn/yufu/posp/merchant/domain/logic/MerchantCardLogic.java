package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.MerchantCardDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.MerchantCardModel;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogic;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;

public class MerchantCardLogic extends BaseLogic implements MerchantCardLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantCardLogic() {

	}

	/**
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(MerchantCardModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("MerchantCardLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			MerchantCardDaoHibernateHQL dao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			// for(int i=0;i<list.size();i++){
			// MerchantCardModel mc = (MerchantCardModel)list.get(i);
			// mc.setBankType(mc.getBankType().replaceAll(" ", ""));
			// }

			// 封装银行类型名称和卡类型名称
			if (list != null && list.size() > 0) {
				EdcCommonLogic elogic = new EdcCommonLogic();
				List<Banktype> banktypeList = elogic.queryAllBanktypeItem(null, ud);
				List<Cardtype> cardtypeList = elogic.queryAllCardtypeItem(null, ud);
				for (int i = 0; i < list.size(); i++) {
					MerchantCardModel mcm = (MerchantCardModel) list.get(i);
					for (Banktype bvo : banktypeList) {
						if (mcm.getBankType().equals(bvo.getBankType())) {
							mcm.setBankTypeName(bvo.getTypeName());
						}
					}
					for (Cardtype cvo : cardtypeList) {
						if (mcm.getCardType().equals((cvo.getId().getCardType()).replace(" ", ""))) {
							mcm.setCardTypeName(cvo.getId().getTypeName());
						}
					}

				}
			}

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("MerchantCardLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCardLogic.queryAll()调用时出现异常。");
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
			log.info("MerchantCardLogic.deleteItem(List newKeys, UserData ud)开始调用：删除一条记录。" + ud.getUserId());

			MerchantCardDaoHibernateHQL dao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");

			dao.deleteItem(newKeys, ud);

			log.info("MerchantCardLogic.deleteItem(List newKeys, UserData ud)结束调用：删除一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCardLogic.deleteItem(List newKeys, UserData ud)删除一条记录,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		MerchantCardModel newModel = new MerchantCardModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantCardLogic.findItem(long key, UserData ud)开始调用：显示一条记录。" + ud.getUserId());

			MerchantCardDaoHibernateHQL dao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			}
			// 封装商户名称
			newModel.setMerchantName(dao.findMerchantName(newModel.getMerchantId(), ud));
			// 封装银行类型名和卡类型名
			EdcCommonLogic elogic = new EdcCommonLogic();
			List<Banktype> banktypeList = elogic.queryAllBanktypeItem(null, ud);
			List<Cardtype> cardtypeList = elogic.queryAllCardtypeItem(null, ud);
			for (Banktype bt : banktypeList) {
				if (newModel.getBankType().equals(bt.getBankType())) {
					newModel.setBankTypeName(bt.getTypeName());
				}
			}
			for (Cardtype ct : cardtypeList) {
				if (newModel.getCardType().equals((ct.getId().getCardType()).replace(" ", ""))) {
					newModel.setCardTypeName(ct.getId().getTypeName());
				}
			}
			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("MerchantCardLogic.findItem(long key, UserData ud)结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCardLogic.findItem(long key, UserData ud)显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(MerchantCardModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantCardLogic.saveItem(MerchantCardModel newModel, UserData ud)开始调用：修改一条记录。" + ud.getUserId());
			MerchantCardDaoHibernateHQL dao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");
			dao.saveItem(newModel, ud);
			log.info("MerchantCardLogic.saveItem(MerchantCardModel newModel, UserData ud)结束调用：修改一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCardLogic.saveItem(MerchantCardModel newModel, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 新建一条记录
	 */
	public void createItem(MerchantCardModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantCardLogic.createItem(MerchantCardModel newModel, UserData ud)开始调用：新建一条记录。" + ud.getUserId());

			MerchantCardDaoHibernateHQL dao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");
			dao.createItem(newModel, ud);

			log.info("MerchantCardLogic.createItem(MerchantCardModel newModel, UserData ud)结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCardLogic.createItem(MerchantCardModel newModel, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * Ajax显示商户名称
	 */
	public String findMerchantName(String merchantId, UserData ud) {
		MerchantCardDaoHibernateHQL dao = (MerchantCardDaoHibernateHQL) getBean("merCardDao");
		return dao.findMerchantName(merchantId, ud);

	}

}
