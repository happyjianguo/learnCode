package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Order;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcAlipayWeChat;
import cn.yufu.posp.terminalmanager.domain.model.EdcNewfkterminalOrm;

public class EdcAlipayWeChatDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements EdcAlipayWeChatDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public EdcAlipayWeChatDaoHibernateHQLImpl() {
	}
	
	@SuppressWarnings("unchecked")
	public int queryCount(EdcAlipayWeChat edcAlipayWeChat, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.queryCount()开始调用：查询符合条件的终端资料数量。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from EdcAlipayWeChat  t where 1=1");

			if (edcAlipayWeChat != null) {
				// 按商户编号查询
				if (edcAlipayWeChat.getMerchantId() != null && !edcAlipayWeChat.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcAlipayWeChat.getMerchantId() + "'");

				// 按终端编号查询
				if (edcAlipayWeChat.getTerminalId() != null && !edcAlipayWeChat.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcAlipayWeChat.getTerminalId() + "'");
				
				if (edcAlipayWeChat.getLogonStatus() != null && !edcAlipayWeChat.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcAlipayWeChat.getLogonStatus() + "'");
			}
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("查询语句==" + hql);
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.queryCount()出现异常：查询符合条件的终端资料数量。");
		} catch (Exception e) {

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.queryCount()查询符合条件的终端资料数量，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}
	
	@SuppressWarnings("unchecked")
	public List queryAllItem(EdcAlipayWeChat edcAlipayWeChat, int startIndex, int maxresults, 
			String sortfield, String sortType, UserData ud) throws OAException {
		
		List<EdcAlipayWeChat> list = new ArrayList<EdcAlipayWeChat>();
		
		try {
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.queryAllItem()开始调用：查找符合条件的记录。");

			StringBuffer bufferHql = new StringBuffer(" from EdcAlipayWeChat  t where 1=1");

			if (edcAlipayWeChat != null) {
				// 按商户编号查询
				if (edcAlipayWeChat.getMerchantId() != null && !edcAlipayWeChat.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcAlipayWeChat.getMerchantId() + "'");

				// 按终端编号查询
				if (edcAlipayWeChat.getTerminalId() != null && !edcAlipayWeChat.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcAlipayWeChat.getTerminalId() + "'");

				if (edcAlipayWeChat.getLogonStatus() != null && !edcAlipayWeChat.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcAlipayWeChat.getLogonStatus() + "'");
			}

			// 添加排序信息
			if (sortfield != null && sortType != null) {
				bufferHql.append(" order by t." + sortfield + " " + sortType + " ");
			} else {
				bufferHql.append(Order.desc(" order by t.id.merchantId desc "));
			}

			String hql = bufferHql.toString();

			List<EdcAlipayWeChat> edcAlipayWeChatList = new ArrayList<EdcAlipayWeChat>();
			edcAlipayWeChatList = findByHQL(hql, ud);
			int endIndex = startIndex + maxresults;
			if (endIndex > edcAlipayWeChatList.size())
				endIndex = edcAlipayWeChatList.size();

			list = edcAlipayWeChatList.subList(startIndex, endIndex);

			log.info("查询语句==" + hql);

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.queryAllItem()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.queryAllItem()查找符合条件的记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}
	
	public void createItem(EdcAlipayWeChat edcAlipayWeChat, UserData ud) throws OAException {
		try {
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.createItem()开始调用：添加一条终端资料记录。");

			save(edcAlipayWeChat, ud);

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.createItem()结束调用：添加一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.createItem()添加一条终端资料记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.deleteItem()开始调用：删除终端资料记录。");

			for (int i = 0; i < keys.size(); i++) {
				EdcAlipayWeChat model = findItemByKey((EdcAlipayWeChat) keys.get(i), ud);
				System.out.println(model);
				delete(model, ud);
			}

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.deleteItem()结束调用：删除终端资料记录。");
		} catch (Exception e) {

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.deleteItem()删除终端资料记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public EdcAlipayWeChat findItemByKey(EdcAlipayWeChat edcAlipayWeChat, UserData ud) throws OAException {
		List<EdcAlipayWeChat> edcAlipayWeChatList = new ArrayList<EdcAlipayWeChat>();
		EdcAlipayWeChat model = new EdcAlipayWeChat();
		try {
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.findItemByKey()开始调用：查找一条终端资料记录。");

			StringBuffer bufferHql = new StringBuffer(" from EdcAlipayWeChat  t where 1=1");

			if (edcAlipayWeChat != null) {

				if (edcAlipayWeChat.getMerchantId() != null && !edcAlipayWeChat.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcAlipayWeChat.getMerchantId() + "'");

				if (edcAlipayWeChat.getTerminalId() != null && !edcAlipayWeChat.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcAlipayWeChat.getTerminalId() + "'");
				
				if (edcAlipayWeChat.getModuleId() != null && !edcAlipayWeChat.getModuleId().equals(""))
					bufferHql.append(" and t.moduleId = '" + edcAlipayWeChat.getModuleId() + "'");

			}

			String hql = bufferHql.toString();
			edcAlipayWeChatList = findByHQL(hql, ud);

			if (edcAlipayWeChatList.size() > 0)
				model = (EdcAlipayWeChat) edcAlipayWeChatList.get(0);

			log.info(" 查询语句: hql==" + hql);
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.findItemByKey()结束调用：查找一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.findItemByKey()查找一条终端资料记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	public void saveItem(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.saveItem()开始调用：保存一条终端资料记录。");

			update(edcNewfkterminalOrm, ud);

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.saveItem()结束调用：保存一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.saveItem()保存一条终端资料记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
