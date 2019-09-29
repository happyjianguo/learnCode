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
	 * 查询所有记录
	 */
	public PageInfoModel queryAll(MerchantBaseModel queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("MerchantLogic.queryAll()开始调用：分页查询。" + ud.getUserId());

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");

			// 得到记录的总条数
			totalCount = dao.querySum(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAll(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			MerchantBaseModel merchantM = new MerchantBaseModel();
			for (int i = 0; i < list.size(); i++) {
				// 商品类型名称
				merchantM = (MerchantBaseModel) list.get(i);
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

			log.info("MerchantLogic.queryAll()结束调用：分页查询。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantLogic.queryAll()调用时出现异常。");
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

			log.info("MerchantLogic.deleteItem()开始调用：删除一条记录。" + ud.getUserId());

			MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");

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
					//同步成功
					log.info("MerchantLogic.saveItem()同步删除商户信息数据成功。" + ud.getUserId());
				}else{
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("MerchantLogic.deleteItem()结束调用：删除一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantLogic.deleteItem()删除一条记录,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 显示一条记录
	 */
	public HashMap findItem(String newKey, UserData ud) throws OAException {
		MerchantBaseModel newModel = new MerchantBaseModel();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantLogic.findItem()开始调用：显示一条记录。" + ud.getUserId());

			MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");
			newModel = dao.findItem(newKey, ud);
			if (newModel == null) {
				throw new OAException("您要查找的信息不存在！");
			}

			// 传model
			parameteMap.put("Infolist", newModel);

			log.info("MerchantLogic.findItem()结束调用：显示一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantLogic.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

		return parameteMap;
	}

	/**
	 * 修改一条记录
	 */
	public void saveItem(MerchantBaseModel newModel, UserData ud) throws OAException {
		try {
			// 验证商户类型是否存在
			if (newModel.getMcc() != null && !"".equals(newModel.getMcc())) {
				MccParamDaoHibernateHQL mdao = (MccParamDaoHibernateHQL) getBean("mccDao");
				MccParamModel mpm = mdao.findItem(newModel.getMcc(), ud);
				if (mpm == null) {
					throw new OAException("您输入的商户类型在数据库中不存在，请重新输入！");
				}
			}

			log.info("MerchantLogic.saveItem()开始调用：修改一条记录。" + ud.getUserId());
			MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");
			dao.saveItem(newModel, ud);
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				MerchantService service = (MerchantService) getBean("syncMerchant");
				List<MerchantBaseModel> list = new ArrayList<MerchantBaseModel>();
				list.add(newModel);
				String result = service.syncMerchant(MerchantUtil.merchantToJson(list, 3));
				JSONObject ret = JSONObject.fromObject(result);
				if("0".equals(ret.get("code"))){
					//同步成功
					log.info("MerchantLogic.saveItem()同步修改商户信息数据成功。" + ud.getUserId());
				}else{
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("MerchantLogic.saveItem()结束调用：修改一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantLogic.saveItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

	}

	/**
	 * 新建一条记录
	 */
	public void createItem(MerchantBaseModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantLogic.createItem()开始调用：新建一条记录。" + ud.getUserId());
			MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");
			// 验证商户类型是否存在
			if (newModel.getMcc() != null && !"".equals(newModel.getMcc())) {
				MccParamDaoHibernateHQL mdao = (MccParamDaoHibernateHQL) getBean("mccDao");
				MccParamModel mpm = mdao.findItem(newModel.getMcc(), ud);
				if (mpm == null) {
					throw new OAException("您输入的商户类型 " + newModel.getMcc() + " 在数据库中不存在，请重新输入！");
				}
			}
			// 验证编号是否已经存在-基本信息
			try {
				log.info("MerchantDaoHibernateHQLImpl.createItem()开始调用：验证信息是否已经存在。");
				MerchantBaseModel sm = dao.findItemById(newModel.getMerchantId(), ud);
				log.info("MerchantDaoHibernateHQLImpl.createItem()结束调用：验证信息是否已经存在。");
				if (sm != null) {
					throw new OAException("商户编号  " + newModel.getMerchantId() + " 已经在数据库中存在！");
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
					//同步成功
					log.info("MerchantLogic.createItem()同步新增商户信息数据成功。" + ud.getUserId());
				}else{
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("MerchantLogic.createItem()结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantLogic.createItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

	/**
	 * Ajax 查询签约行号和主机号
	 */
	public List<MerchantBaseModel> findSignBankInfo(String bankId, UserData ud) {
		MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");
		List<MerchantBaseModel> list = dao.findSignBankInfo(bankId,ud);
		return list;
	}

	/**
	 * Ajax 查询商户类型
	 */
	public List<MerchantBaseModel> findMccInfo(String mcc, UserData ud) {
		MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");
		List<MerchantBaseModel> list = dao.findMccInfo(mcc,ud);
		return list;
	}

	/**
	 * 查询机构
	 * 
	 * public List findAllJGs(UserData ud){ MerchantDaoHibernateHQL dao =
	 * (MerchantDaoHibernateHQL) getBean("merchantDao"); return
	 * dao.findAllJG(ud); }
	 */

	@Override
	public void saveBaseInfo(MerchantBaseBo model, UserData ud) throws OAException {
		try {
			log.info("MerchantLogic.saveBaseInfo()开始调用：新建一条记录。" + ud.getUserId());
			MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");
			// 验证商户类型是否存在
			if (model.getMcc() != null && !"".equals(model.getMcc())) {
				MccParamDaoHibernateHQL mdao = (MccParamDaoHibernateHQL) getBean("mccDao");
				MccParamModel mpm = mdao.findItem(model.getMcc(), ud);
				if (mpm == null) {
					throw new OAException("您输入的商户类型 " + model.getMcc() + " 在数据库中不存在，请重新输入！");
				}
			}

			dao.saveBaseInfo(model, ud);

			log.info("MerchantLogic.saveBaseInfo()结束调用：新建一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantLogic.createItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

	public MerchantBaseBo findBaseInfoById(String merchantId, UserData ud) throws OAException {

		MerchantBaseBo newModel = new MerchantBaseBo();

		HashMap parameteMap = new HashMap();
		try {
			log.info("MerchantLogic.findBaseInfoById()开始调用：显示一条基本信息记录。" + ud.getUserId());

			MerchantDaoHibernateHQL dao = (MerchantDaoHibernateHQL) getBean("merchantDao");
			newModel = dao.findBaseInfoById(merchantId, ud);

			log.info("MerchantLogic.findBaseInfoById()结束调用：显示一条基本信息记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantLogic.findBaseInfoById()显示一条基本信息记录，出现异常。");
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
