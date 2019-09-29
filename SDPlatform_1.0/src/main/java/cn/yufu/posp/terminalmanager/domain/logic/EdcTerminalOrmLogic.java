package cn.yufu.posp.terminalmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcCommonDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcTerminalOrmDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalOrm;

public class EdcTerminalOrmLogic extends BaseLogic implements EdcTerminalOrmLogicInterface {

	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF7190128
	 */
	public EdcTerminalOrmLogic() {

	}

	public void createItem(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalOrmLogic.createItem()开始调用：新建终端资料信息。");

			EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");
			dao.createItem(edcTerminalOrm, ud);

			log.info("EdcTerminalOrmLogic.createItem()结束调用：新建终端资料信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalOrmLogic.createItem()新建终端资料信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalOrmLogic.deleteItem()开始调用：删除终端资料信息。");

			// 删除终端资料设定
			EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");
			dao.deleteItem(keys, ud);

			log.info("EdcTerminalOrmLogic.deleteItem()结束调用：删除终端资料信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalOrmLogic.deleteItem()删除终端资料信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

	@SuppressWarnings("unchecked")
	public HashMap findItemByKey(EdcTerminalOrm key, UserData ud) throws OAException {
		EdcTerminalOrm newModel = new EdcTerminalOrm();
		HashMap map = new HashMap();
		try {
			log.info("EdcTerminalOrmLogic.findItemByKey()开始调用：查找一条信息。");

			EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");
			newModel = dao.findItemByKey(key, ud);

			if (newModel != null) {
				EdcCommonDaoHibernateHQL commondao = (EdcCommonDaoHibernateHQL) getBean("EdcCommonDao");
				newModel.setMerchanName(commondao.findMerchantNameByKey(newModel.getMerchantId(), ud));
			}
			map.put("Infolist", newModel);

			log.info("EdcTerminalOrmLogic.findItemByKey()结束调用：查找一条信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalOrmLogic.findItemByKey()查找一条信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public PageInfoModel queryAllItem(EdcTerminalOrm edcTerminalOrm, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("EdcTerminalOrmLogic.queryAllItem()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");

			// 得到记录的总条数
			totalCount = dao.queryCount(edcTerminalOrm, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = dao.queryAllItem(edcTerminalOrm, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("返回结果集长度==" + list.size() + "起始位长度==" + startIndex + "页码数==" + (startIndex + pageSize) / pageSize + "总记录数==" + totalCount);

			log.info("EdcTerminalOrmLogic.queryAllItem()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalOrmLogic.ueryAllItem()分页查询，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	public void saveItem(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcTerminalOrmLogic.saveItem()开始调用：保存 、修改 信息。");

			EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");

			dao.saveItem(edcTerminalOrm, ud);

			log.info("EdcTerminalOrmLogic.EdcTerminalOrmLogic.saveItem()结束调用：保存 、修改 信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTerminalOrmLogic.saveItem()保存 、修改 调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}
	
	

	public void saveUploadItem(List<EdcTerminalOrm> edcTerminalOrmList, UserData ud) throws OAException {
		try {
			EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");
			for (int i = 0; i < edcTerminalOrmList.size(); i++) {
				// 创建新的信息
				dao.createItem(edcTerminalOrmList.get(i), ud);
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcTerminalOrmLogic.saveUploadItem()保存终端信息及同步终端主密钥表。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}

	}

	public EdcTerminalOrm queryModualBy(String merchantId, String terminalId) throws OAException {
		log.info("EdcTerminalOrmLogic.createItem()开始调用：新建终端资料信息。");

		EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");
		EdcTerminalOrm model = dao.queryModualBy(merchantId, terminalId);

		log.info("EdcTerminalOrmLogic.createItem()结束调用：新建终端资料信息。");
		return model;
	}

	//校验联合主键唯一性PKEY
	public String checkEdcTerminalOrmPKEY(String merchantId,String terminalId,String moduleId) throws OAException {
		String checkFlag="0";
		try {
			EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");
			checkFlag=dao.checkEdcTerminalOrmPKEY(merchantId,terminalId,moduleId);
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcTerminalOrmLogic.checkEdcTerminalOrmPKEY()校验联合主键唯一性PKEY。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
		return checkFlag;
	}
	
	
	//校验索引唯一性ORM
		public String checkEdcTerminalOrmORM(String bankMerchantId,String bankTerminalId,String moduleId) throws OAException {
			String checkFlag="0";
			try {
				EdcTerminalOrmDaoHibernateHQL dao = (EdcTerminalOrmDaoHibernateHQL) getBean("EdcTerminalOrmDao");
				checkFlag=dao.checkEdcTerminalOrmORM(bankMerchantId,bankTerminalId,moduleId);
			} catch (Exception e) {
				if (log.isDebugEnabled())
					e.printStackTrace();
				log.error("EdcTerminalOrmLogic.checkEdcTerminalOrmORM()校验索引唯一性ORM。");
				log.error(e, e.fillInStackTrace());

				throw new OAException(e.getMessage());
			}
			return checkFlag;
		}
}
