package cn.yufu.posp.terminalmanager.domain.logic;

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
import cn.yufu.posp.keyManager.dao.hibernate.hql.TMKMasterKEYDaoHibernateHQL;
import cn.yufu.posp.keyManager.domain.model.BtsKey;
import cn.yufu.posp.merchant.domain.logic.MerchantService;
import cn.yufu.posp.merchant.domain.logic.MerchantUtil;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCardDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcSwitchDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcTerminalDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcCard;
import cn.yufu.posp.terminalmanager.domain.model.EdcCardId;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitch;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitchId;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;

public class EdcTerminalLogic extends BaseLogic implements EdcTerminalLogicInterface {

	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcTerminalLogic() {

	}

	public void createItem(EdcTerminal edcTerminal, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalLogic.createItem()开始调用：新建终端资料信息。");

			EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");
			dao.createItem(edcTerminal, ud);

			// 新建终端时同步终端密钥表 用于生成终端主密钥用
			TMKMasterKEYDaoHibernateHQL tmkDao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");
			// 新建一个Model
			BtsKey btsKey = new BtsKey();
			btsKey.setSettleFlag("0");// 0--新终端 1--生成终端主密钥 2--已签到
			btsKey.setBatchNo("1");
			btsKey.setOperNo("001");
			btsKey.setOperPasswd("00000000");
			btsKey.setMerchantId(edcTerminal.getId().getMerchantId());
			btsKey.setTerminalId(edcTerminal.getId().getTerminalId());
			tmkDao.createItem(btsKey, ud);
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				MerchantService service = (MerchantService) getBean("syncMerchant");
				List<EdcTerminal> list = new ArrayList<EdcTerminal>();
				list.add(edcTerminal);
				String result = service.syncEdcTerminal(MerchantUtil.edcTerminalToJson(list, 1));
				JSONObject ret = JSONObject.fromObject(result);
				if ("0".equals(ret.get("code"))) {
					// 同步成功
					log.info("MerchantLogic.saveItem()同步新增终端资料数据成功。" + ud.getUserId());
				} else {
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("EdcTerminalLogic.createItem()结束调用：新建终端资料信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalLogic.createItem()新建终端资料信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalLogic.deleteItem()开始调用：删除终端资料信息。");

			List<EdcCard> edcCardKeys = new ArrayList<EdcCard>();
			List<EdcSwitch> edcSwitchKeys = new ArrayList<EdcSwitch>();

			for (int i = 0; i < keys.size(); i++) {
				EdcTerminal model = (EdcTerminal) keys.get(i);

				String merchantId = model.getId().getMerchantId();
				String terminalId = model.getId().getTerminalId();

				EdcCard edcCard = new EdcCard();
				EdcCardId edcCardId = new EdcCardId();
				edcCardId.setMerchantId(merchantId);
				edcCardId.setTerminalId(terminalId);
				edcCard.setId(edcCardId);

				EdcSwitch edcSwitch = new EdcSwitch();
				EdcSwitchId edcSwitchId = new EdcSwitchId();
				edcSwitchId.setMerchantId(merchantId);
				edcSwitchId.setTerminalId(terminalId);
				edcSwitch.setId(edcSwitchId);

				EdcCommonDaoHibernateHQL commonDao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");

				// 删除卡类转换
				EdcSwitchDaoHibernateHQL dao1 = (EdcSwitchDaoHibernateHQL) getBean("EdcSwitchDao");
				edcSwitchKeys = commonDao.findEdcSwitchItemByKey(edcSwitch, ud);
				dao1.deleteItem(edcSwitchKeys, ud);

				// 删除受理卡类
				EdcCardDaoHibernateHQL dao2 = (EdcCardDaoHibernateHQL) getBean("EdcCardDao");
				edcCardKeys = commonDao.findEdcCardItemByKey(edcCard, ud);
				dao2.deleteItem(edcCardKeys, ud);
				
				// 删除密钥表
				TMKMasterKEYDaoHibernateHQL tmkDao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");
				BtsKey key = new BtsKey();
				key.setMerchantId(merchantId);
				key.setTerminalId(terminalId);
				key = tmkDao.findItemByKey(key , ud);
				tmkDao.deleteItem(key, ud);

			}
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				MerchantService service = (MerchantService) getBean("syncMerchant");
				String result = service.syncEdcTerminal(MerchantUtil.edcTerminalToJson(keys, 2));
				JSONObject ret = JSONObject.fromObject(result);
				if ("0".equals(ret.get("code"))) {
					// 同步成功
					log.info("MerchantLogic.saveItem()同步新增终端资料数据成功。" + ud.getUserId());
				} else {
					throw new OAException(ret.getString("msg"));
				}
			}
			// 删除终端资料设定
			EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");
			dao.deleteItem(keys, ud);

			log.info("EdcTerminalLogic.deleteItem()结束调用：删除终端资料信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalLogic.deleteItem()删除终端资料信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(EdcTerminal key, UserData ud) throws OAException {
		EdcTerminal newModel = new EdcTerminal();
		HashMap map = new HashMap();
		try {
			log.info("EdcTerminalLogic.findItemByKey()开始调用：查找一条信息。");

			EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");
			newModel = dao.findItemByKey(key, ud);

			if (newModel != null) {
				EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				newModel.getId().setMerchanName(commondao.findMerchantNameByKey(newModel.getId().getMerchantId(), ud));
			}
			map.put("Infolist", newModel);

			log.info("EdcTerminalLogic.findItemByKey()结束调用：查找一条信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalLogic.findItemByKey()查找一条信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(EdcTerminal edcTerminal, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("EdcTerminalLogic.queryAllItem()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");

			// 得到记录的总条数
			totalCount = dao.queryCount(edcTerminal, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(edcTerminal, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("返回结果集长度==" + list.size() + "起始位长度==" + startIndex + "页码数==" + (startIndex + pageSize) / pageSize + "总记录数==" + totalCount);

			log.info("EdcTerminalLogic.queryAllItem()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalLogic.ueryAllItem()分页查询，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(EdcTerminal edcTerminal, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalLogic.saveItem()开始调用：保存 、修改 信息。");

			EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");

			dao.saveItem(edcTerminal, ud);
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				MerchantService service = (MerchantService) getBean("syncMerchant");
				List<EdcTerminal> list = new ArrayList<EdcTerminal>();
				list.add(edcTerminal);
				String result = service.syncEdcTerminal(MerchantUtil.edcTerminalToJson(list, 3));
				JSONObject ret = JSONObject.fromObject(result);
				if ("0".equals(ret.get("code"))) {
					// 同步成功
					log.info("MerchantLogic.saveItem()同步修改终端资料数据成功。" + ud.getUserId());
				} else {
					throw new OAException(ret.getString("msg"));
				}
			}
			log.info("EdcTerminalLogic.EdcTerminalLogic.saveItem()结束调用：保存 、修改 信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalLogic.saveItem()保存 、修改 调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	public void saveUploadItem(List<EdcTerminal> edcTerminalList, UserData ud) throws OAException {
		try {
			TMKMasterKEYDaoHibernateHQL tmkDao = (TMKMasterKEYDaoHibernateHQL) getBean("TMKMasterKEYDao");
			EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");
			for (int i = 0; i < edcTerminalList.size(); i++) {
				// 创建新的信息
				dao.createItem(edcTerminalList.get(i), ud);
				// 新建终端时同步终端密钥表 用于生成终端主密钥用
				// 新建一个Model
				BtsKey btsKey = new BtsKey();
				btsKey.setSettleFlag("0");// 0--新终端 1--生成终端主密钥 2--已签到
				btsKey.setBatchNo("1");
				btsKey.setOperNo("001");
				btsKey.setOperPasswd("00000000");
				btsKey.setMerchantId(edcTerminalList.get(i).getId().getMerchantId());
				btsKey.setTerminalId(edcTerminalList.get(i).getId().getTerminalId());
				tmkDao.createItem(btsKey, ud);
			}
			if ("YES".equals(CommonDomain.CONST_CHECK_SYNCDATA)) {
				MerchantService service = (MerchantService) getBean("syncMerchant");
				String result = service.syncEdcTerminal(MerchantUtil.edcTerminalToJson(edcTerminalList, 1));
				JSONObject ret = JSONObject.fromObject(result);
				if ("0".equals(ret.get("code"))) {
					// 同步成功
					log.info("MerchantLogic.saveItem()同步新增终端资料数据成功。" + ud.getUserId());
				} else {
					throw new OAException(ret.getString("msg"));
				}
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcTerminalLogic.saveUploadItem()保存终端信息及同步终端主密钥表。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}
	public List<EdcTerminal> queryTerminalListByMerchantId(String merchantId,String cardBin,UserData ud) throws OAException{
		EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");
		return dao.queryTerminalListByMerchantId(merchantId,cardBin, ud);
	}
	
	/**
	 * 判断所有的终端是否都是该商户的
	 * @param merchantId
	 * @param terminals
	 * @param ud
	 * @return	true：有不是该商户的终端；false：都是该商户的终端
	 * @throws OAException
	 */
	public boolean checkTerminalIsNotMechants(String merchantId, String terminals,
			UserData ud) throws OAException{
		EdcTerminalDaoHibernateHQL dao = (EdcTerminalDaoHibernateHQL) getBean("EdcTerminalDao");
		return dao.checkTerminalIsNotMechants(merchantId, terminals, ud);
	}
}
