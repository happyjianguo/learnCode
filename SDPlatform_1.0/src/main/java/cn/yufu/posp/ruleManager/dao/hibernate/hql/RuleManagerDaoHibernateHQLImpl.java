package cn.yufu.posp.ruleManager.dao.hibernate.hql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.ruleManager.domain.model.RuleConfig;
import cn.yufu.posp.ruleManager.domain.model.RuleConfigParam;
import cn.yufu.posp.ruleManager.domain.model.RuleTempParam;
import cn.yufu.posp.ruleManager.domain.model.RuleTempParamList;
import cn.yufu.posp.ruleManager.domain.model.RuleTemplate;
import cn.yufu.posp.ruleManager.web.form.RuleConfigForm;
import cn.yufu.posp.ruleManager.web.form.RuleConfigParamForm;
import cn.yufu.posp.ruleManager.web.form.RuleTempParamForm;
import cn.yufu.posp.ruleManager.web.form.RuleTempParamListForm;
import cn.yufu.posp.ruleManager.web.form.RuleTemplateForm;

public class RuleManagerDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements RuleManagerDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("rule");

	@Override
	public List queryTemp(RuleTemplate queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("RuleManagerDaoHibernateHQLImpl.queryTemp()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(RuleTemplate.class);

			// 按名程序模板查询
			if (queryModel.getRuleTempNo() != null && !queryModel.getRuleTempNo().equals(""))
				dcr.add(Restrictions.eq("ruleTempNo", queryModel.getRuleTempNo()));
			if (queryModel.getStatus() != null && !queryModel.getStatus().equals(""))
				dcr.add(Restrictions.like("status", queryModel.getStatus(), MatchMode.ANYWHERE));

			// 添加排序信息
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("ruleTempNo"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("RuleManagerDaoHibernateHQLImpl.queryTemp()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("RuleManagerDaoHibernateHQLImpl.queryTemp()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("查询模板对象个数时抛异常！");
		}
		return list;
	}

	@Override
	public int queryTempCount(RuleTemplate queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("RuleManagerDaoHibernateHQLImpl.queryTempCount()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from RuleTemplate where 1=1");

			// //按条件查询
			// if(queryModel.getId().getCardNo()!=null &&
			// !queryModel.getId().getCardNo().equals("") )
			// bufferHql.append(" and id.cardNo = "+queryModel.getId().getCardNo()+" ");
			// if(queryModel.getId().getTraceNo()!=null &&
			// !queryModel.getId().getTraceNo().equals("") )
			// bufferHql.append(" and id.traceNo = "+queryModel.getId().getTraceNo()+" ");
			// if(queryModel.getId().getTranType()!=null &&
			// !queryModel.getId().getTranType().equals("") )
			// bufferHql.append(" and id.tranType = "+queryModel.getId().getTranType()+" ");
			// if(queryModel.getId().getMerchantId()!=null &&
			// !queryModel.getId().getMerchantId().equals("") )
			// bufferHql.append(" and id.merchantId like '%"+queryModel.getId().getMerchantId()+"%'");
			// if(queryModel.getId().getMerchantName()!=null &&
			// !queryModel.getId().getMerchantName().equals("") )
			// bufferHql.append(" and id.merchantIName like '%"+queryModel.getId().getMerchantName()+"%'");
			//			
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("RuleManagerDaoHibernateHQLImpl.queryTempCount()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("RuleManagerDaoHibernateHQLImpl.queryTempCount()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询模板对象时抛异常！");
		}
		return count;

	}

	@Override
	public List queryTempParamByNo(RuleTemplate queryModel, UserData ud) throws OAException {
		List list = new ArrayList();
		try {
			log.info("RuleManagerDaoHibernateHQLImpl.queryTempParamByNo()开始调用：查询模板配置规则。");
			DetachedCriteria dcr = DetachedCriteria.forClass(RuleTempParam.class);
			dcr.add(Restrictions.eq("ruleTempNo", queryModel.getRuleTempNo()));
			dcr.add(Restrictions.eq("status", "S"));
			dcr.addOrder(Order.desc("ruleTempReg"));
			List paramList = findBYCriteria(dcr, ud);
			for (int j = 0; j < paramList.size(); j++) {
				RuleTempParamForm tempParamForm = new RuleTempParamForm();
				RuleTempParam tempParam = (RuleTempParam) paramList.get(j);
				tempParamForm.setRuleTempReg(tempParam.getRuleTempReg());
				tempParamForm.setRuleTempParamId(tempParam.getRuleTempParamId());
				tempParamForm.setRuleTempNo(tempParam.getRuleTempNo());
				tempParamForm.setRuleTempReg(tempParam.getRuleTempReg());
				tempParamForm.setRuleTempRegDefault(tempParam.getRuleTempRegDefault());
				tempParamForm.setRuleTempRegList(tempParam.getRuleTempRegList());
				tempParamForm.setRuleTempRegType(tempParam.getRuleTempRegType());
				list.add(tempParamForm);
			}
			log.info("RuleManagerDaoHibernateHQLImpl.queryTempParamByNo()结束调用：查询模板配置规则。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("RuleManagerDaoHibernateHQLImpl.queryTempParamByNo()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询模板对象时抛异常！");
		}
		return list;
	}

	@Override
	public List queryTempByNo(RuleTemplate queryModel, UserData ud) throws OAException {
		List list = new ArrayList();
		try {
			log.info("RuleManagerDaoHibernateHQLImpl.queryTempByNo()开始调用：查询模板配置规则。");
			DetachedCriteria dcr = DetachedCriteria.forClass(RuleTemplate.class);
			dcr.add(Restrictions.eq("ruleTempNo", queryModel.getRuleTempNo()));
			dcr.add(Restrictions.eq("status", "S"));
			List tempList = findBYCriteria(dcr, ud);
			for (int i = 0; i < tempList.size(); i++) {
				RuleTemplateForm templateForm = new RuleTemplateForm();
				RuleTemplate tempModel = (RuleTemplate) tempList.get(i);
				templateForm.setRuleTempNo(tempModel.getRuleTempNo());
				templateForm.setRuleTempReg(tempModel.getRuleTempReg());
				templateForm.setRuleTempDesc(tempModel.getRuleTempDesc());
				list.add(templateForm);
			}
			log.info("RuleManagerDaoHibernateHQLImpl.queryTempByNo()结束调用：查询模板配置规则。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("RuleManagerDaoHibernateHQLImpl.queryTempByNo()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询模板对象时抛异常！");
		}
		return list;
	}

	@Override
	public List queryTempParamListByParamId(String paramId, UserData ud) throws OAException {
		List list = new ArrayList();
		try {
			log.info("RuleManagerDaoHibernateHQLImpl.queryTempParamByNo()开始调用：查询模板配置规则。");
			DetachedCriteria dcr = DetachedCriteria.forClass(RuleTempParamList.class);
			dcr.add(Restrictions.eq("ruleTempParamId", paramId));
			dcr.add(Restrictions.eq("status", "S"));
			dcr.addOrder(Order.desc("key"));
			List paramListList = findBYCriteria(dcr, ud);
			for (int k = 0; k < paramListList.size(); k++) {
				RuleTempParamListForm tempParamListForm = new RuleTempParamListForm();
				RuleTempParamList tempParamList = (RuleTempParamList) paramListList.get(k);
				tempParamListForm.setKey(tempParamList.getKey());
				tempParamListForm.setRuleTempParamId(tempParamList.getRuleTempParamId());
				tempParamListForm.setValue(tempParamList.getValue());
				list.add(tempParamListForm);
			}
			log.info("RuleManagerDaoHibernateHQLImpl.queryTempParamByNo()结束调用：查询模板配置规则。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("RuleManagerDaoHibernateHQLImpl.queryTempParamByNo()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询模板对象时抛异常！");
		}
		return list;
	}

	@Override
	public void createItem(RuleConfig ruleConfig, List confParamlist, UserData ud) throws OAException {
		try {
			log.info("RuleManagerDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");
			save(ruleConfig, ud);
			for (int i = 0; i < confParamlist.size(); i++) {
				saveOrUpdate(confParamlist.get(i), ud);
			}
			log.info("RuleManagerDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("RuleManagerDaoHibernateHQLImpl.createItem()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询模板对象时抛异常！");
		}
	}

	@Override
	public List queryRule(RuleConfig queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("RuleManagerDaoHibernateHQLImpl.queryRule()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(RuleConfig.class);

			// 按名程序模板查询
			if (queryModel.getRuleTempNo() != null && !queryModel.getRuleTempNo().equals(""))
				dcr.add(Restrictions.eq("ruleTempNo", queryModel.getRuleTempNo()));
			if (queryModel.getConfigId() != null && !queryModel.getConfigId().equals(""))
				dcr.add(Restrictions.eq("configId", queryModel.getConfigId()));
			if (queryModel.getStatus() != null && !queryModel.getStatus().equals(""))
				dcr.add(Restrictions.like("status", queryModel.getStatus(), MatchMode.ANYWHERE));

			// 添加排序信息
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("ruleTempNo"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("RuleManagerDaoHibernateHQLImpl.queryRule()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("RuleManagerDaoHibernateHQLImpl.queryTemp()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("查询模板对象个数时抛异常！");
		}
		return list;
	}

	@Override
	public int queryRuleCount(RuleConfig queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("RuleManagerDaoHibernateHQLImpl.queryRuleCount()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from RuleConfig where 1=1");

			// //按条件查询
			if (queryModel.getRuleTempNo() != null && !queryModel.getRuleTempNo().equals(""))
				bufferHql.append(" and ruleTempNo = '" + queryModel.getRuleTempNo() + "' ");
			if (queryModel.getConfigId() != null && !queryModel.getConfigId().equals(""))
				bufferHql.append(" and configId = '" + queryModel.getConfigId() + "' ");
			if (queryModel.getStatus() != null && !queryModel.getStatus().equals(""))
				bufferHql.append(" and status = '" + queryModel.getStatus() + "' ");
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("RuleManagerDaoHibernateHQLImpl.queryRuleCount()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("RuleManagerDaoHibernateHQLImpl.queryRuleCount()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询模板对象时抛异常！");
		}
		return count;
	}

	/**
	 * 删除一条记录
	 */
	public void deleteRule(List newKeys, UserData ud) throws OAException {
		try {
			log.info("MerchantCardDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)调用开始：删除一条记录。");

			// 这种删法，会连子表一同删除

			for (int i = 0; i < newKeys.size(); i++) {
				StringBuffer bufferHql = new StringBuffer("delete from Rule_Config  where config_Id='" + newKeys.get(i) + "'");
				String hql = bufferHql.toString();
				System.out.println("删除：：" + hql);
				saveOrUpdateOrDeleteBySQL(hql, ud);
				bufferHql = new StringBuffer("delete from Rule_Config_Param  where config_Id='" + newKeys.get(i) + "'");
				hql = bufferHql.toString();
				System.out.println("删除：：" + hql);
				saveOrUpdateOrDeleteBySQL(hql, ud);
			}

			log.info("MerchantCardDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)结束调用：删除一条记录。");
		} catch (Exception e) {
			log.error("MerchantCardDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("删除受理卡类时抛异常！");
		}
	}

	@Override
	public List queryRuleOne(String configId, UserData ud) throws OAException {
		List list = new ArrayList();
		try {
			log.info("RuleManagerDaoHibernateHQLImpl.queryTempParamByNo()开始调用：查询模板配置规则。");
			DetachedCriteria dcr = DetachedCriteria.forClass(RuleConfig.class);
			dcr.add(Restrictions.eq("configId", configId));
			List ruleConfig = findBYCriteria(dcr, ud);
			for (int k = 0; k < ruleConfig.size(); k++) {
				RuleConfigForm ruleConfigForm = new RuleConfigForm();
				RuleConfig tempParamList = (RuleConfig) ruleConfig.get(k);
				BeanUtils.copyProperties(ruleConfigForm, tempParamList);
				dcr = DetachedCriteria.forClass(RuleConfigParam.class);
				dcr.add(Restrictions.eq("configId", configId));
				List ruleParamConfig = findBYCriteria(dcr, ud);
				List paramList = new ArrayList();
				for (int i = 0; i < ruleParamConfig.size(); i++) {
					RuleConfigParamForm newForm = new RuleConfigParamForm();
					RuleConfigParam old = (RuleConfigParam) ruleParamConfig.get(i);
					BeanUtils.copyProperties(newForm, old);
					paramList.add(newForm);
				}
				ruleConfigForm.setList(paramList);
				list.add(ruleConfigForm);
			}
			log.info("RuleManagerDaoHibernateHQLImpl.queryTempParamByNo()结束调用：查询模板配置规则。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("RuleManagerDaoHibernateHQLImpl.queryTempParamByNo()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询模板对象时抛异常！");
		}
		return list;
	}
}
