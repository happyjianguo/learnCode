package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.dao.hibernate.hql.PosReceiptInfoDaoHibernateHQL;
import cn.yufu.posp.merchant.domain.model.PosReceiptInfoModel;


public class PosReceiptInfoLogic extends BaseLogic implements PosReceiptInfoLogicInterface {

	private static final Log log = LogFactory.getLog("merchant");

	public PosReceiptInfoLogic() {

	}

	/**
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(PosReceiptInfoModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("PosReceiptInfoLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			PosReceiptInfoDaoHibernateHQL dao = (PosReceiptInfoDaoHibernateHQL) getBean("posRecInfoDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);
		
			
			if (list != null && list.size() > 0) {
		
				
				for (int i = 0; i < list.size(); i++) {
					
				}
			}

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("PosReceiptInfoLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PosReceiptInfoLogic.queryAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return page;
	}

	

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		PosReceiptInfoModel newModel = new PosReceiptInfoModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("PosReceiptInfoLogic.findItem(long key, UserData ud)开始调用：显示一条记录。" + ud.getUserId());

			PosReceiptInfoDaoHibernateHQL dao = (PosReceiptInfoDaoHibernateHQL) getBean("posRecInfoDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			}
		
			
			parameteMap.put("Infolist", newModel);
		

			log.info("PosReceiptInfoLogic.findItem(long key, UserData ud)结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PosReceiptInfoLogic.findItem(long key, UserData ud)显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(PosReceiptInfoModel newModel, UserData ud) throws OAException {
		try {
			log.info("PosReceiptInfoLogic.saveItem(PosReceiptInfoModel newModel, UserData ud)开始调用：修改一条记录。" + ud.getUserId());
			
			PosReceiptInfoDaoHibernateHQL dao = (PosReceiptInfoDaoHibernateHQL) getBean("posRecInfoDao");
			dao.saveItem(newModel, ud);
		
			log.info("PosReceiptInfoLogic.saveItem(PosReceiptInfoModel newModel, UserData ud)结束调用：修改一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PosReceiptInfoLogic.saveItem(PosReceiptInfoModel newModel, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}


	
}
