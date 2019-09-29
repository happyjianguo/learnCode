/**
 *包名:cn.yufu.posp.terminalmanager.dao.hibernate.hql
 *描述:package cn.yufu.posp.terminalmanager.dao.hibernate.hql;
 */
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
import cn.yufu.posp.terminalmanager.domain.model.EdcZskterminalOrm;

/**
 * EdcZskterminalOrmDaoHibernateHQLImpl.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年3月2日
 * 描述:专属卡终端
 */
public class EdcZskterminalOrmDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl
		implements EdcZskterminalOrmDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("edcTerminal");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public EdcZskterminalOrmDaoHibernateHQLImpl() {
	}
	
	@Override
	public int queryCount(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.queryCount()开始调用：查询符合条件的终端资料数量。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from EdcZskterminalOrm  t where 1=1");

			if (edcZskterminalOrm != null) {
				// 按商户编号查询
				if (edcZskterminalOrm.getMerchantId() != null && !edcZskterminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcZskterminalOrm.getMerchantId() + "'");

				// 按终端编号查询
				if (edcZskterminalOrm.getTerminalId() != null && !edcZskterminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcZskterminalOrm.getTerminalId() + "'");
				
				if (edcZskterminalOrm.getLogonStatus() != null && !edcZskterminalOrm.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcZskterminalOrm.getLogonStatus() + "'");
			}
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("查询语句==" + hql);
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.queryCount()出现异常：查询符合条件的终端资料数量。");
		} catch (Exception e) {

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.queryCount()查询符合条件的终端资料数量，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	@Override
	public List queryAllItem(EdcZskterminalOrm edcZskterminalOrm, int startIndex, int maxresults, String sortfield,
			String sortType, UserData ud) throws OAException {
		
		List<EdcZskterminalOrm> list = new ArrayList<EdcZskterminalOrm>();
		
		try {
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.queryAllItem()开始调用：查找符合条件的记录。");

			StringBuffer bufferHql = new StringBuffer(" from EdcZskterminalOrm  t where 1=1");

			if (edcZskterminalOrm != null) {
				// 按商户编号查询
				if (edcZskterminalOrm.getMerchantId() != null && !edcZskterminalOrm.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcZskterminalOrm.getMerchantId() + "'");

				// 按终端编号查询
				if (edcZskterminalOrm.getTerminalId() != null && !edcZskterminalOrm.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcZskterminalOrm.getTerminalId() + "'");

				if (edcZskterminalOrm.getLogonStatus() != null && !edcZskterminalOrm.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcZskterminalOrm.getLogonStatus() + "'");
			}

			// 添加排序信息
			if (sortfield != null && sortType != null) {
				bufferHql.append(" order by t." + sortfield + " " + sortType + " ");
			} else {
				bufferHql.append(Order.desc(" order by t.id.merchantId desc "));
			}

			String hql = bufferHql.toString();

			List<EdcZskterminalOrm> edcAlipayWeChatList = new ArrayList<EdcZskterminalOrm>();
			edcAlipayWeChatList = findByHQL(hql, ud);
			int endIndex = startIndex + maxresults;
			if (endIndex > edcAlipayWeChatList.size())
				endIndex = edcAlipayWeChatList.size();

			list = edcAlipayWeChatList.subList(startIndex, endIndex);

			log.info("查询语句==" + hql);

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.queryAllItem()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.queryAllItem()查找符合条件的记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@Override
	public EdcZskterminalOrm findItemByKey(EdcZskterminalOrm key, UserData ud) throws OAException {
		List<EdcZskterminalOrm> edcAlipayWeChatList = new ArrayList<EdcZskterminalOrm>();
		EdcZskterminalOrm model = new EdcZskterminalOrm();
		try {
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.findItemByKey()开始调用：查找一条终端资料记录。");

			StringBuffer bufferHql = new StringBuffer(" from EdcZskterminalOrm  t where 1=1");

			if (key != null) {

				if (key.getMerchantId() != null && !key.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + key.getMerchantId() + "'");

				if (key.getTerminalId() != null && !key.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + key.getTerminalId() + "'");
				
				if (key.getModuleId() != null && !key.getModuleId().equals(""))
					bufferHql.append(" and t.moduleId = '" + key.getModuleId() + "'");

			}

			String hql = bufferHql.toString();
			edcAlipayWeChatList = findByHQL(hql, ud);

			if (edcAlipayWeChatList.size() > 0)
				model = (EdcZskterminalOrm) edcAlipayWeChatList.get(0);

			log.info(" 查询语句: hql==" + hql);
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.findItemByKey()结束调用：查找一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.findItemByKey()查找一条终端资料记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}	

	@Override
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.deleteItem()开始调用：删除终端资料记录。");

			for (int i = 0; i < keys.size(); i++) {
				EdcZskterminalOrm model = findItemByKey((EdcZskterminalOrm) keys.get(i), ud);
				System.out.println(model);
				delete(model, ud);
			}

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.deleteItem()结束调用：删除终端资料记录。");
		} catch (Exception e) {

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.deleteItem()删除终端资料记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@Override
	public void createItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.createItem()开始调用：添加一条终端资料记录。");

			save(edcZskterminalOrm, ud);

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.createItem()结束调用：添加一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.createItem()添加一条终端资料记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@Override
	public void saveItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.saveItem()开始调用：保存一条终端资料记录。");

			update(edcZskterminalOrm, ud);

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.saveItem()结束调用：保存一条终端资料记录。");
		} catch (Exception e) {

			log.info("EdcZskterminalOrmDaoHibernateHQLImpl.saveItem()保存一条终端资料记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
