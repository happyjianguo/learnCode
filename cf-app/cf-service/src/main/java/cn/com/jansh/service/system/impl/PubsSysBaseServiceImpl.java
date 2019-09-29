package cn.com.jansh.service.system.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.core.entity.sys.PubsSysBase;
import cn.com.jansh.mapper.system.PubsSysBaseMapper;
import cn.com.jansh.service.system.PubsSysBaseService;

/**
 * 数据字典业务服务实现
 *
 * @author Mr.wong
 */
@Service
public class PubsSysBaseServiceImpl implements PubsSysBaseService {

	@Autowired
	private PubsSysBaseMapper baseMapper;

	private static final Logger logger = LogManager.getLogger(PubsSysBaseServiceImpl.class);

	/**
	 * 通过系统参数名获取参数实体
	 */
	@Override
	public PubsSysBase querySysBaseAceBaseId(String baseId) {
		PubsSysBase base = baseMapper.selectOneByBaseId(baseId);
		return base;
	}

	/**
	 * 通过系统参数名获取参数实体
	 */
	@Override
	public String querySysBaseValue(String baseId) {
		PubsSysBase base = baseMapper.selectOneByBaseId(baseId);
		String val = base.getSysBaseValue();
		return val == null ? "" : val;
	}

	/**
	 * 获取重置密码
	 */
	@Override
	public String querySysBaseInitPwd() {
		PubsSysBase base = baseMapper.selectOneByBaseId("INITPASSWD");

		String val = base.getSysBaseValue();
		logger.info("获取重置密码{}", val);
		return val == null ? "" : val;
	}

	/**
	 * 微信请求url
	 */
	@Override
	public String querySysBaseWxMsgRequrl() {
		PubsSysBase base = baseMapper.selectOneByBaseId("WXMSGREQURL");
		String val = base.getSysBaseValue();
		logger.info("微信请求url:{}", val);
		return val == null ? "" : val;
	}

	/**
	 * 获取全部的系统参数
	 */
	@Override
	public List<PubsSysBase> queryAllSysBase(String sysBaseId) {
		List<PubsSysBase> bases;
		if (StringUtils.isBlank(sysBaseId)) {
			
			 	bases = baseMapper.selectAllBase();
		}else {
				bases = baseMapper.selectBasesByBaseId(sysBaseId);
		}
		return bases;
	}

	/**
	 * 更新系统参数
	 */
	@Override
	public void updateSysBase(PubsSysBase pubsSysBase) {
		baseMapper.update(pubsSysBase);
	}

}
