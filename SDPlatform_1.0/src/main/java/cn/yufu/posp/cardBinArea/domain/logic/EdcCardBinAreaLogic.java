package cn.yufu.posp.cardBinArea.domain.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.cardBinArea.dao.hibernate.hql.EdcCardBinAreaDaoHibernateHQL;
import cn.yufu.posp.cardBinArea.domain.model.EdcCardBinArea;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public class EdcCardBinAreaLogic extends BaseLogic implements EdcCardBinAreaLogicInterface {

	private static final Log log = LogFactory.getLog("EdcCardBinArea");

	public EdcCardBinAreaLogic() {

	}

	/**
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(EdcCardBinArea queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("EdcCardBinAreaLogic.queryAll()开始调用：分页查询。" + ud.getUserId());
			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;
			EdcCardBinAreaDaoHibernateHQL dao = (EdcCardBinAreaDaoHibernateHQL) getBean("EdcCardBinAreaDao");
			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);
			
			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
			log.info("totalCount=" + totalCount + ";list.size()=" + list.size());
//			if (list != null && list.size() > 0) {
//				Map areaCodeMap=dao.getAreaCodeMap();
//				for (int i = 0; i < list.size(); i++) {
//					EdcCardBinArea EdcCardBinArea = (EdcCardBinArea) list.get(i);
//					if(areaCodeMap!=null&&areaCodeMap.size()>0&&EdcCardBinArea.getAreaCode()!=null&&!"".equals(EdcCardBinArea.getAreaCode())){						
//						EdcCardBinArea.setAreaCodeName(areaCodeMap.get(EdcCardBinArea.getAreaCode()).toString());
//					}
//				}
//			}
			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("EdcCardBinAreaLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcCardBinAreaLogic.queryAll()调用时出现异常。");
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
			log.info("EdcCardBinAreaLogic.deleteItem()开始调用：删除一条记录。" + ud.getUserId());

			EdcCardBinAreaDaoHibernateHQL dao = (EdcCardBinAreaDaoHibernateHQL) getBean("EdcCardBinAreaDao");

			dao.deleteItem(newKeys, ud);

			log.info("EdcCardBinAreaLogic.deleteItem()结束调用：删除一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaLogic.deleteItem()删除一条记录,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		EdcCardBinArea newModel = new EdcCardBinArea();

		HashMap parameteMap = new HashMap();
		try {
			log.info("EdcCardBinAreaLogic.findItem()开始调用：显示一条记录。" + ud.getUserId());

			EdcCardBinAreaDaoHibernateHQL dao = (EdcCardBinAreaDaoHibernateHQL) getBean("EdcCardBinAreaDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			} else {
//				Map areaCodeMap=dao.getAreaCodeMap();
//				if(newModel.getAreaCode()!=null&&!"".equals(newModel.getAreaCode())&&areaCodeMap!=null&&areaCodeMap.size()>0){					
//					newModel.setAreaCodeName(areaCodeMap.get(newModel.getAreaCode()).toString());
//				}
			}
			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("EdcCardBinAreaLogic.findItem()结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaLogic.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(EdcCardBinArea newModel, UserData ud) throws OAException {
		try {
			log.info("EdcCardBinAreaLogic.saveItem()开始调用：修改一条记录。" + ud.getUserId());
			EdcCardBinAreaDaoHibernateHQL dao = (EdcCardBinAreaDaoHibernateHQL) getBean("EdcCardBinAreaDao");
			dao.saveItem(newModel, ud);
			log.info("EdcCardBinAreaLogic.saveItem()结束调用：修改一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaLogic.saveItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 新建一条记录
	 */
	public void createItem(EdcCardBinArea newModel, UserData ud) throws OAException {
		try {
			log.info("EdcCardBinAreaLogic.createItem()开始调用。" + ud.getUserId());
			EdcCardBinAreaDaoHibernateHQL dao = (EdcCardBinAreaDaoHibernateHQL) getBean("EdcCardBinAreaDao");
//			// 验证编号是否已经存在-基本信息
//			try {
//				log.info("EdcCardBinAreaLogic.createItem()开始调用：验证信息是否已经存在。");
//				EdcCardBinArea sm = dao.findItemById(newModel.getCardBin(),newModel.getMerchantId(),newModel.getTerminalId(), ud);
//				log.info("EdcCardBinAreaLogic.createItem()结束调用：验证信息是否已经存在。");
//				if (sm != null) {
//					throw new OAException("卡BIN " + newModel.getCardBin() + " 已经在数据库中存在！");
//				}
//			} catch (Exception e) {
//				throw new OAException(e.getMessage());
//			}
			String terminalIds=newModel.getTerminalId();
			String[] terminalIdStr=terminalIds.split(",");
			for(int i=0;i<terminalIdStr.length;i++){
				newModel.setTerminalId(terminalIdStr[i]);
				dao.createItem(newModel, ud);
			}
			log.info("EdcCardBinAreaLogic.createItem()结束调用：新建"+terminalIdStr.length+"条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaLogic.createItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

}
