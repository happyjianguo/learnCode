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
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.queryCount()��ʼ���ã���ѯ�����������ն�����������");

			StringBuffer bufferHql = new StringBuffer("select count(*) from EdcAlipayWeChat  t where 1=1");

			if (edcAlipayWeChat != null) {
				// ���̻���Ų�ѯ
				if (edcAlipayWeChat.getMerchantId() != null && !edcAlipayWeChat.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcAlipayWeChat.getMerchantId() + "'");

				// ���ն˱�Ų�ѯ
				if (edcAlipayWeChat.getTerminalId() != null && !edcAlipayWeChat.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcAlipayWeChat.getTerminalId() + "'");
				
				if (edcAlipayWeChat.getLogonStatus() != null && !edcAlipayWeChat.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcAlipayWeChat.getLogonStatus() + "'");
			}
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("��ѯ���==" + hql);
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.queryCount()�����쳣����ѯ�����������ն�����������");
		} catch (Exception e) {

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.queryCount()��ѯ�����������ն����������������쳣��");
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
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.queryAllItem()��ʼ���ã����ҷ��������ļ�¼��");

			StringBuffer bufferHql = new StringBuffer(" from EdcAlipayWeChat  t where 1=1");

			if (edcAlipayWeChat != null) {
				// ���̻���Ų�ѯ
				if (edcAlipayWeChat.getMerchantId() != null && !edcAlipayWeChat.getMerchantId().equals(""))
					bufferHql.append(" and t.merchantId = '" + edcAlipayWeChat.getMerchantId() + "'");

				// ���ն˱�Ų�ѯ
				if (edcAlipayWeChat.getTerminalId() != null && !edcAlipayWeChat.getTerminalId().equals(""))
					bufferHql.append(" and t.terminalId = '" + edcAlipayWeChat.getTerminalId() + "'");

				if (edcAlipayWeChat.getLogonStatus() != null && !edcAlipayWeChat.getLogonStatus().equals(""))
					bufferHql.append(" and t.logonStatus = '" + edcAlipayWeChat.getLogonStatus() + "'");
			}

			// ���������Ϣ
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

			log.info("��ѯ���==" + hql);

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.queryAllItem()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.queryAllItem()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}
	
	public void createItem(EdcAlipayWeChat edcAlipayWeChat, UserData ud) throws OAException {
		try {
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.createItem()��ʼ���ã����һ���ն����ϼ�¼��");

			save(edcAlipayWeChat, ud);

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.createItem()�������ã����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.createItem()���һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.deleteItem()��ʼ���ã�ɾ���ն����ϼ�¼��");

			for (int i = 0; i < keys.size(); i++) {
				EdcAlipayWeChat model = findItemByKey((EdcAlipayWeChat) keys.get(i), ud);
				System.out.println(model);
				delete(model, ud);
			}

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.deleteItem()�������ã�ɾ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.deleteItem()ɾ���ն����ϼ�¼�������쳣��");
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
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.findItemByKey()��ʼ���ã�����һ���ն����ϼ�¼��");

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

			log.info(" ��ѯ���: hql==" + hql);
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.findItemByKey()�������ã�����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.findItemByKey()����һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	public void saveItem(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException {
		try {
			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.saveItem()��ʼ���ã�����һ���ն����ϼ�¼��");

			update(edcNewfkterminalOrm, ud);

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.saveItem()�������ã�����һ���ն����ϼ�¼��");
		} catch (Exception e) {

			log.info("EdcAlipayWeChatDaoHibernateHQLImpl.saveItem()����һ���ն����ϼ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
