package cn.yufu.posp.ruleManager.domain.logic;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.ruleManager.domain.model.RuleConfig;
import cn.yufu.posp.ruleManager.domain.model.RuleTemplate;

public interface RuleManagerLogicInterface {

	PageInfoModel queryTempAll(RuleTemplate queryModel, PageInfoModel pageInfo, UserData ud) throws OAException;

	List queryTemplateByNo(RuleTemplate queryModel, UserData ud) throws OAException;

	void createItem(RuleConfig ruleConfig, List confParamlist, UserData ud) throws OAException;

	PageInfoModel queryRuleList(RuleConfig queryModel, PageInfoModel pageInfo, UserData sessionUserData) throws OAException;

	void deleteRule(List keysList, UserData ud) throws OAException;

	List queryRuleOne(String configId, UserData ud) throws OAException;

}
