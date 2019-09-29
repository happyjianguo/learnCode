package cn.yufu.posp.cardBinArea.domain.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.cardBinArea.dao.hibernate.hql.CardBinAreaDaoHibernateHQL;
import cn.yufu.posp.cardBinArea.domain.model.CardBinArea;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;

public class CardBinAreaLogic extends BaseLogic implements CardBinAreaLogicInterface {

	private static final Log log = LogFactory.getLog("CardBinArea");

	public CardBinAreaLogic() {

	}

	/**
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(CardBinArea queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("CardBinAreaLogic.queryAll()开始调用：分页查询。" + ud.getUserId());
			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;
			CardBinAreaDaoHibernateHQL dao = (CardBinAreaDaoHibernateHQL) getBean("CardBinAreaDao");
			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);
			
			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			log.info("totalCount=" + totalCount + ";list.size()=" + list.size());
			if (list != null && list.size() > 0) {
				Map areaCodeMap=dao.getAreaCodeMap();
				for (int i = 0; i < list.size(); i++) {
					CardBinArea CardBinArea = (CardBinArea) list.get(i);
					if(areaCodeMap!=null&&areaCodeMap.size()>0&&CardBinArea.getAreaCode()!=null&&!"".equals(CardBinArea.getAreaCode())){						
						CardBinArea.setAreaCodeName(areaCodeMap.get(CardBinArea.getAreaCode()).toString());
					}
				}
			}
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("CardBinAreaLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("CardBinAreaLogic.queryAll()调用时出现异常。");
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
			log.info("CardBinAreaLogic.deleteItem()开始调用：删除一条记录。" + ud.getUserId());

			CardBinAreaDaoHibernateHQL dao = (CardBinAreaDaoHibernateHQL) getBean("CardBinAreaDao");

			dao.deleteItem(newKeys, ud);

			log.info("CardBinAreaLogic.deleteItem()结束调用：删除一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaLogic.deleteItem()删除一条记录,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		CardBinArea newModel = new CardBinArea();

		HashMap parameteMap = new HashMap();
		try {
			log.info("CardBinAreaLogic.findItem()开始调用：显示一条记录。" + ud.getUserId());

			CardBinAreaDaoHibernateHQL dao = (CardBinAreaDaoHibernateHQL) getBean("CardBinAreaDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			} else {
				Map areaCodeMap=dao.getAreaCodeMap();
				if(newModel.getAreaCode()!=null&&!"".equals(newModel.getAreaCode())&&areaCodeMap!=null&&areaCodeMap.size()>0){					
					newModel.setAreaCodeName(areaCodeMap.get(newModel.getAreaCode()).toString());
				}
			}
			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("CardBinAreaLogic.findItem()结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaLogic.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(CardBinArea newModel, UserData ud) throws OAException {
		try {
			log.info("CardBinAreaLogic.saveItem()开始调用：修改一条记录。" + ud.getUserId());
			CardBinAreaDaoHibernateHQL dao = (CardBinAreaDaoHibernateHQL) getBean("CardBinAreaDao");
			dao.saveItem(newModel, ud);
			log.info("CardBinAreaLogic.saveItem()结束调用：修改一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaLogic.saveItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 新建一条记录
	 */
	public void createItem(CardBinArea newModel, UserData ud) throws OAException {
		try {
			log.info("CardBinAreaLogic.createItem()开始调用：新建一条记录。" + ud.getUserId());
			CardBinAreaDaoHibernateHQL dao = (CardBinAreaDaoHibernateHQL) getBean("CardBinAreaDao");
			// 验证编号是否已经存在-基本信息
			try {
				log.info("CardBinAreaLogic.createItem()开始调用：验证信息是否已经存在。");
				CardBinArea sm = dao.findItemById(newModel.getCardBin(), ud);
				log.info("CardBinAreaLogic.createItem()结束调用：验证信息是否已经存在。");
				if (sm != null) {
					throw new OAException("卡BIN " + newModel.getCardBin() + " 已经在数据库中存在！");
				}
			} catch (Exception e) {
				throw new OAException(e.getMessage());
			}
			dao.createItem(newModel, ud);
			log.info("CardBinAreaLogic.createItem()结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaLogic.createItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
