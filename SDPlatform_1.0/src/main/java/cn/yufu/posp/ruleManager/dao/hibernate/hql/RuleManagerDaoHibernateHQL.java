package cn.yufu.posp.ruleManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.ruleManager.domain.model.RuleConfig;
import cn.yufu.posp.ruleManager.domain.model.RuleTemplate;

public interface RuleManagerDaoHibernateHQL {

	int queryTempCount(RuleTemplate queryModel, UserData ud) throws OAException;

	List queryTemp(RuleTemplate queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException;

	List queryTempParamByNo(RuleTemplate queryModel, UserData ud) throws OAException;

	List queryTempByNo(RuleTemplate queryModel, UserData ud) throws OAException;

	List queryTempParamListByParamId(String paramId, UserData ud) throws OAException;

	void createItem(RuleConfig ruleConfig, List confParamlist, UserData ud) throws OAException;

	int queryRuleCount(RuleConfig queryModel, UserData ud) throws OAException;

	List queryRule(RuleConfig queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException;

	void deleteRule(List newKeys, UserData ud) throws OAException;

	List queryRuleOne(String configId, UserData ud) throws OAException;

}
