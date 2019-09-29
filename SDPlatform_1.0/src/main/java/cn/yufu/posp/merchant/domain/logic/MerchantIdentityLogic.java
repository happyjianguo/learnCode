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
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(MerchantIdentity queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("MerchantIdentityLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			MerchantIdentityDaoHibernateHQL dao = (MerchantIdentityDaoHibernateHQL) getBean("merchantIdentityDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("MerchantIdentityLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityLogic.queryAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	/**
	 * 删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {

			log.info("MerchantIdentityLogic.deleteItem()开始调用：删除一条记录。" + ud.getUserId());

			MerchantIdentityDaoHibernateHQL dao = (MerchantIdentityDaoHibernateHQL) getBean("merchantIdentityDao");

			for (int i = 0; i < newKeys.size(); i++) {
				String tmp = newKeys.get(i) + "";
				// 判断该商户编号是否在终端资料设定信息表里，如果在，则不能删除
				EdcTerminal et = new EdcTerminal();
				EdcTerminalId etd = new EdcTerminalId();
				EdcCommonDaoHibernateHQL edao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				if (tmp != null && !"".equals(tmp)) {
					etd.setMerchantId(tmp);
					et.setId(etd);
					List<EdcTerminal> list = edao.findEdcTerminalItemByKey(et, ud);
					if (list.size() > 0) {
						throw new OAException("您要删除的商户 " + tmp + " 存在在终端资料设定表中，不能删除，请执行相关操作后再删除！");
					}
				}

			}
			dao.deleteItem(newKeys, ud);

			log.info("MerchantIdentityLogic.deleteItem()结束调用：删除一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityLogic.deleteItem()删除一条记录,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		MerchantIdentity newModel = new MerchantIdentity();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantIdentityLogic.findItem()开始调用：显示一条记录。" + ud.getUserId());
			MerchantIdentityDaoHibernateHQL dao = (MerchantIdentityDaoHibernateHQL) getBean("merchantIdentityDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			}

			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("MerchantIdentityLogic.findItem()结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityLogic.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(MerchantIdentity newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantIdentityLogic.saveItem()开始调用：修改一条记录。" + ud.getUserId());
			MerchantIdentityDaoHibernateHQL dao = (MerchantIdentityDaoHibernateHQL) getBean("merchantIdentityDao");
			dao.saveItem(newModel, ud);
//			MerchantService service = (MerchantService) getBean("syncdata");
//			List<MerchantIdentity> list = new ArrayList<MerchantIdentity>();
//			list.add(newModel);
//			String result = service.syncMerchant(MerchantUtil.merchantToJson(list, 3));
//			JSONObject ret = JSONObject.fromString(result);
//			if("0".equals(ret.get("code"))){
//				//同步成功
//				log.info("MerchantIdentityLogic.saveItem()同步修改商户信息数据成功。" + ud.getUserId());
//			}else{
//				throw new OAException(ret.getString("msg"));
//			}
			log.info("MerchantIdentityLogic.saveItem()结束调用：修改一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityLogic.saveItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}


	public MerchantBaseBo findBaseInfoById(String merchantId, UserData ud) throws OAException {

		MerchantBaseBo newModel = new MerchantBaseBo();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantIdentityLogic.findBaseInfoById()开始调用：显示一条基本信息记录。" + ud.getUserId());

			MerchantIdentityDaoHibernateHQL dao = (MerchantIdentityDaoHibernateHQL) getBean("merchantIdentityDao");
			newModel = dao.findBaseInfoById(merchantId, ud);

			log.info("MerchantIdentityLogic.findBaseInfoById()结束调用：显示一条基本信息记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityLogic.findBaseInfoById()显示一条基本信息记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
		return newModel;

	}
}
