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
			log.info("RuleManagerLogic.queryTempAll()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			RuleManagerDaoHibernateHQL ruleManagerDao = (RuleManagerDaoHibernateHQL) getBean("ruleManagerDao");

			// 得到记录的总条数
			totalCount = ruleManagerDao.queryTempCount(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);

			startIndex = (currentPage - 1) * pageSize;

			List list = ruleManagerDao.queryTemp(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);

			log.info("RuleManagerLogic.queryTempAll()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("RuleManagerLogic.queryTempAll()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	@Override
	public List queryTemplateByNo(RuleTemplate queryModel, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("RuleManagerLogic.queryTempAll()开始调用：查询模板配置规则。");
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
			log.info("RuleManagerLogic.queryTempAll()结束调用：查询模板配置规则。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("RuleManagerLogic.queryTempParamByNo()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}

	@Override
	public void createItem(RuleConfig ruleConfig, List confParamlist, UserData ud) throws OAException {
		try {
			log.info("RuleManagerLogic.createItem()开始调用：新建一条记录。" + ud.getUserId());
			RuleManagerDaoHibernateHQL ruleManagerDao = (RuleManagerDaoHibernateHQL) getBean("ruleManagerDao");
			ruleManagerDao.createItem(ruleConfig, confParamlist, ud);
			log.info("RuleManagerLogic.createItem()结束调用：新建一条记录。" + ud.getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("RuleManagerLogic.createItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
	}

	@Override
	public PageInfoModel queryRuleList(RuleConfig queryModel, PageInfoModel pageInfo, UserData ud) throws OAException {
		PageInfoModel page = pageInfo;

		try {
			log.info("RuleManagerLogic.queryRuleList()开始调用：分页查询。");

			int startIndex = 0;
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();
			int totalCount = 0;

			RuleManagerDaoHibernateHQL ruleManagerDao = (RuleManagerDaoHibernateHQL) getBean("ruleManagerDao");

			// 得到记录的总条数
			totalCount = ruleManagerDao.queryRuleCount(queryModel, ud);
			// 防止最后一页没有东西
			currentPage =pageInfo.currentPage(pageInfo, currentPage, pageSize,
					totalCount);
			
			startIndex = (currentPage - 1) * pageSize;
			List list = ruleManagerDao.queryRule(queryModel, startIndex, pageSize, page.getOrderField(), page.getOrderType(), ud);

			page.getResultItems().addAll(list);
			page.setTotalCount(totalCount);
			log.info("RuleManagerLogic.queryRuleList()结束调用：分页查询。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("RuleManagerLogic.queryRuleList()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return page;
	}

	/**
	 * 删除一条记录
	 */
	public void deleteRule(List newKeys, UserData ud) throws OAException {
		try {
			log.info("RuleManagerLogic.deleteRule()开始调用：删除一条记录。" + ud.getUserId());

			RuleManagerDaoHibernateHQL ruleManagerDao = (RuleManagerDaoHibernateHQL) getBean("ruleManagerDao");

			ruleManagerDao.deleteRule(newKeys, ud);

			log.info("RuleManagerLogic.deleteRule()结束调用：删除一条记录。" + ud.getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("RuleManagerLogic.deleteRule()删除一条记录,调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}

	@Override
	public List queryRuleOne(String configId, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("RuleManagerLogic.queryRuleOne()开始调用：查询规则。");
			RuleManagerDaoHibernateHQL ruleManagerDao = (RuleManagerDaoHibernateHQL) getBean("ruleManagerDao");
			list = ruleManagerDao.queryRuleOne(configId, ud);
			log.info("RuleManagerLogic.queryRuleOne()结束调用：查询规则。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("RuleManagerLogic.queryTempParamByNo()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}
		return list;
	}
}
