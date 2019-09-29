package cn.yufu.posp.sysparam.domain.logic;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.logic.BaseLogic;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysparam.dao.hibernate.hql.SysParamDaoHibernateHQL;
import cn.yufu.posp.sysparam.domain.model.SysParam;

public class SysParamLogic extends BaseLogic implements SysParamLogicInterface {

	private static final Log log = LogFactory.getLog("sysparam");

	/**
	 * @roseuid 44BAF7190128
	 */
	public SysParamLogic() {

	}

	/**
	 * 按key查机构信息
	 */
	public HashMap querySysParamByKey(String newKey, UserData ud) throws OAException {
		SysParam newModel = new SysParam();

		HashMap parameteMap = new HashMap();
		try {
			log.info("JgLogic.queryJgByKey()开始调用：查机构信息。");

			SysParamDaoHibernateHQL jgDao = (SysParamDaoHibernateHQL) getBean("sysDao");
			newModel = jgDao.querySysParamByKey(newKey, ud);

			// 传model
			parameteMap.put("Infolist", newModel);
			log.info("JgLogic.queryJgByKey()结束调用：查机构信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgLogic.queryJgByKey()查机构信息，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

		return parameteMap;
	}

	/**
	 * 保存机构信息
	 */
	public void saveSysParam(SysParam newModel, UserData ud) throws OAException {
		try {
			log.info("queryJgByKey()开始调用：保存参数信息。");
			SysParamDaoHibernateHQL jgDao = (SysParamDaoHibernateHQL) getBean("sysDao");
			jgDao.saveSysParam(newModel, ud);
			log.info("queryJgByKey()结束调用：保存参数信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("queryJgByKey()保存参数信息，调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_LOGIC);
		}

	}

}
