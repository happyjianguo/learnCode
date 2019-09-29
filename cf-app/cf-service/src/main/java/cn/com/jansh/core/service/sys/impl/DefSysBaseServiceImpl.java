package cn.com.jansh.core.service.sys.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.core.entity.sys.PubsSysBase;
import cn.com.jansh.core.mapper.SysCoreMapper;
import cn.com.jansh.core.service.sys.DefSysBaseService;

/**
 * 数据字典业务服务实现
 *
 * @author Mr.wong
 */
@Service
public class DefSysBaseServiceImpl implements DefSysBaseService {

	@Autowired
	private SysCoreMapper sysCoreMapper;

	private static final Logger logger = LogManager.getLogger(DefSysBaseServiceImpl.class);

	/**
	 * 通过系统参数名获取参数实体
	 */
	// @Override
	// public PubsSysBase querySysBaseAceBaseId(String baseId) {
	// PubsSysBase base = baseMapper.selectOneByBaseId(baseId);
	// return base;
	// }

	/**
	 * 通过系统参数名获取参数实体
	 */
	@Override
	public String querySysBaseValue(String baseId) {
		PubsSysBase base = sysCoreMapper.selectOneByBaseId(baseId);
		String val = base.getSysBaseValue();
		return val == null ? "" : val;
	}

	/**
	 * 获取重置密码
	 */
	@Override
	public String querySysBaseInitPwd() {
		PubsSysBase base = sysCoreMapper.selectOneByBaseId("INITPASSWD");

		String val = base.getSysBaseValue();
		logger.info("获取重置密码{}", val);
		return val == null ? "" : val;
	}

}
