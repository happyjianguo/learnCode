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
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(MerchantBaseBo queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("MerchantCheckLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			MerchantCheckDaoHibernateHQL dao = (MerchantCheckDaoHibernateHQL) getBean("merchantCheckDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			MerchantBaseBo merchantM = new MerchantBaseBo();
			for (int i = 0; i < list.size(); i++) {
				// 商品类型名称
				merchantM = (MerchantBaseBo) list.get(i);
				merchantM.setMccName(dao.findMccName(merchantM.getMcc(), ud));
				// 封装机构名称
				/*
				 * merchantM.setJgName(dao.findJGById(merchantM.getMerchantId(),
				 * ud));
				 * System.out.println("商户编号："+merchantM.getMerchantId()+"\t机构名称："
				 * +merchantM.getJgName());
				 */
			}

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("MerchantCheckLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckLogic.queryAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		MerchantBaseBo newModel = new MerchantBaseBo();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantCheckLogic.findItem()开始调用：显示一条记录。" + ud.getUserId());

			MerchantCheckDaoHibernateHQL dao = (MerchantCheckDaoHibernateHQL) getBean("merchantCheckDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			}

			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("MerchantCheckLogic.findItem()结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckLogic.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(MerchantBaseBo newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantCheckLogic.saveItem()开始调用：修改一条记录。" + ud.getUserId());
			MerchantCheckDaoHibernateHQL dao = (MerchantCheckDaoHibernateHQL) getBean("merchantCheckDao");
			dao.saveItem(newModel, ud);
			log.info("MerchantCheckLogic.saveItem()结束调用：修改一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("MerchantCheckLogic.saveItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}


}
