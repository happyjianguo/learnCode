package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcSwitchDaoHibernateHQL;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitch;

public class EdcSwitchLogic extends BaseLogic implements EdcSwitchLogicInterface {

	private static final Log log = LogFactory.getLog("edcSwitch");

	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcSwitchLogic() {

	}

	public void createItem(EdcSwitch edcSwitch, UserData ud) throws OAException {
		try {
			log.info("edcSwitchLogic.createItem()开始调用：新建终端受理卡类信息。");

			EdcSwitchDaoHibernateHQL dao = (EdcSwitchDaoHibernateHQL) getBean("EdcSwitchDao");
			dao.createItem(edcSwitch, ud);

			log.info("edcSwitchLogic.createItem()结束调用：新建终端受理卡类信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("edcSwitchLogic.createItem()新建终端受理卡类信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("edcSwitchLogic.deleteItem()开始调用：删除终端受理卡类信息。");

			EdcSwitchDaoHibernateHQL dao = (EdcSwitchDaoHibernateHQL) getBean("EdcSwitchDao");
			dao.deleteItem(keys, ud);

			log.info("EdcSwitchLogic.deleteItem()结束调用：删除终端受理卡类信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcSwitchLogic.deleteItem()删除终端受理卡类信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(EdcSwitch key, UserData ud) throws OAException {
		EdcSwitch newModel = new EdcSwitch();
		HashMap map = new HashMap();
		try {
			log.info("EdcSwitchLogic.findItemByKey()开始调用：查找一条终端受理卡类信息。");

			EdcSwitchDaoHibernateHQL dao = (EdcSwitchDaoHibernateHQL) getBean("EdcSwitchDao");
			newModel = dao.findItemByKey(key, ud);
			if (newModel != null) {
				EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				List<Banktype> list = commondao.queryAllBanktypeItem(null, ud);
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					Banktype banktype = (Banktype) iterator.next();
					if (banktype.getBankType().trim().equals(newModel.getId().getBankType().trim()))
						newModel.getId().setBankTypeName(banktype.getTypeName().trim());
				}

				newModel.getId().setMerchantName(commondao.findMerchantNameByKey(newModel.getId().getMerchantId(), ud));
			}
			map.put("Infolist", newModel);

			log.info("EdcSwitchLogic.findItemByKey()结束调用：查找一条终端受理卡类信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcSwitchLogic.findItemByKey()查找一条终端受理卡类信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(EdcSwitch EdcSwitch, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("==EdcSwitchLogic.queryAllItem()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcSwitchDaoHibernateHQL dao = (EdcSwitchDaoHibernateHQL) getBean("EdcSwitchDao");

			// 得到记录的总条数
			totalCount = dao.queryCount(EdcSwitch, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(EdcSwitch, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			List<EdcSwitch> edcSwitchList = new ArrayList<EdcSwitch>();
			EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
			List<Banktype> banktypeList = commondao.queryAllBanktypeItem(null, ud);
			for (int i = 0; i < list.size(); i++) {
				EdcSwitch newModel = (EdcSwitch) list.get(i);
				for (int k = 0; k < banktypeList.size(); k++) {
					Banktype banktype = (Banktype) banktypeList.get(k);
					if (banktype.getBankType().trim().equals(newModel.getId().getBankType().trim()))
						newModel.getId().setBankTypeName(banktype.getTypeName().trim());
				}
				edcSwitchList.add(newModel);
			}

			page.getResultItems().addAll(edcSwitchList);
			page.setTotalCount(totalCount);

			log.info("终端转换 返回结果集长度==" + list.size() + " 起始位长度==" + startIndex + " 页码数==" + (startIndex + pageSize) / pageSize + " 总记录数==" + totalCount);
			log.info("EdcSwitchLogic.queryAllItem()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("=EdcSwitchLogic.queryAllItem()分页查询，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(EdcSwitch EdcSwitch, UserData ud) throws OAException {
		try {
			log.info("EdcSwitchLogic.saveItem()开始调用：保存 、修改 终端受理卡类信息。");

			EdcSwitchDaoHibernateHQL dao = (EdcSwitchDaoHibernateHQL) getBean("EdcSwitchDao");
			dao.saveItem(EdcSwitch, ud);

			log.info("EdcSwitchLogic.saveItem()结束调用：保存 、修改 终端受理卡类信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcSwitchLogic.saveItem()保存 、修改 调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

}
