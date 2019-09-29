package cn.yufu.posp.ruleManager.domain.logic;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.ruleManager.dao.hibernate.hql.RuleManagerDaoHibernateHQL;
import cn.yufu.posp.ruleManager.domain.model.RuleConfig;
import cn.yufu.posp.ruleManager.domain.model.RuleTemplate;
import cn.yufu.posp.ruleManager.web.form.RuleTempParamForm;
import cn.yufu.posp.ruleManager.web.form.RuleTemplateForm;

public class RuleManagerLogic extends BaseLogic implements RuleManagerLogicInterface {

	private static final Log log = LogFactory.getLog("rule");

	@Override
	public PageInfoModel queryTempAll(RuleTemplate queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("RuleManagerLogic.queryTempAll()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			RuleManagerDaoHibernateHQL ruleManagerDao = (RuleManagerDaoHibernateHQL) getBean("ruleManagerDao");

			// �õ���¼��������
			totalCount = ruleManagerDao.queryTempCount(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = ruleManagerDao.queryTemp(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("RuleManagerLogic.queryTempAll()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("RuleManagerLogic.queryTempAll()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	@Override
	public List queryTemplateByNo(RuleTemplate queryModel, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("RuleManagerLogic.queryTempAll()��ʼ���ã���ѯģ�����ù���");
			RuleManagerDaoHibernateHQL ruleManagerDao = (RuleManagerDaoHibernateHQL) getBean("ruleManagerDao");
			list = ruleManagerDao.queryTempByNo(queryModel, ud);
			for (int i = 0; i < list.size(); i++) {
				RuleTemplateForm tempModel = (RuleTemplateForm) list.get(i);
				List paramList = ruleManagerDao.queryTempParamByNo(queryModel, ud);
				tempModel.setRuleTempParam(paramList);
				for (int j = 0; j < paramList.size(); j++) {
					RuleTempParamForm tempParam = (RuleTempParamForm) paramList.get(j);
					if ("Y".equals(tempParam.getRuleTempRegList())) {
						List paramListList = ruleManagerDao.queryTempParamListByParamId(tempParam.getRuleTempParamId(), ud);
						tempParam.setRuleTempParamList(paramListList);
					}
				}
			}
			log.info("RuleManagerLogic.queryTempAll()�������ã���ѯģ�����ù���");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("RuleManagerLogic.queryTempParamByNo()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}

	@Override
	public void createItem(RuleConfig ruleConfig, List confParamlist, UserData ud) throws OAException {
		try {
			log.info("RuleManagerLogic.createItem()��ʼ���ã��½�һ����¼��" + ud.getUserId());
			RuleManagerDaoHibernateHQL ruleManagerDao = (RuleManagerDaoHibernateHQL) getBean("ruleManagerDao");
			ruleManagerDao.createItem(ruleConfig, confParamlist, ud);
			log.info("RuleManagerLogic.createItem()�������ã��½�һ����¼��" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("RuleManagerLogic.createItem()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	@Override
	public PageInfoModel queryRuleList(RuleConfig queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("RuleManagerLogic.queryRuleList()��ʼ���ã���ҳ��ѯ��");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			RuleManagerDaoHibernateHQL ruleManagerDao = (RuleManagerDaoHibernateHQL) getBean("ruleManagerDao");

			// �õ���¼��������
			totalCount = ruleManagerDao.queryRuleCount(queryModel, ud);
			// ��ֹ���һҳû�ж���
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);
			
			startIndex = (currentPage - 1) * pageSize;
			List list = ruleManagerDao.queryRule(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);
			log.info("RuleManagerLogic.queryRuleList()�������ã���ҳ��ѯ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("RuleManagerLogic.queryRuleList()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteRule(List newKeys, UserData ud) throws OAException {
		try {
			log.info("RuleManagerLogic.deleteRule()��ʼ���ã�ɾ��һ����¼��" + ud.getUserId());

			RuleManagerDaoHibernateHQL ruleManagerDao = (RuleManagerDaoHibernateHQL) getBean("ruleManagerDao");

			ruleManagerDao.deleteRule(newKeys, ud);

			log.info("RuleManagerLogic.deleteRule()�������ã�ɾ��һ����¼��" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("RuleManagerLogic.deleteRule()ɾ��һ����¼,����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	@Override
	public List queryRuleOne(String configId, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("RuleManagerLogic.queryRuleOne()��ʼ���ã���ѯ����");
			RuleManagerDaoHibernateHQL ruleManagerDao = (RuleManagerDaoHibernateHQL) getBean("ruleManagerDao");
			list = ruleManagerDao.queryRuleOne(configId, ud);
			log.info("RuleManagerLogic.queryRuleOne()�������ã���ѯ����");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("RuleManagerLogic.queryTempParamByNo()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}
}
