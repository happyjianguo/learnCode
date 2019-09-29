package cn.com.jansh.core.service.sys.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.core.entity.sys.IMUser;
import cn.com.jansh.core.mapper.SysCoreMapper;
import cn.com.jansh.core.service.sys.DefSysBaseService;
import cn.com.jansh.core.service.sys.DefUserService;
import cn.com.jansh.core.util.StringUtil;

@Service
public class DefUserServiceImpl implements DefUserService {

	private static final Logger logger = LogManager.getLogger(DefUserServiceImpl.class);
	@Autowired
	private SysCoreMapper sysCoreMapper;
	@Autowired
	private DefSysBaseService pubsSysBaseService;

	@Override
	public int updatePwdErr(IMUser imuser) {
		return sysCoreMapper.updateUserErr(imuser);
	}

	/**
	 * 更新校验密码错误次数
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public void updateErrNumSafe(String userId, String operadate) {
		IMUser imuser = new IMUser();
		imuser.setUserid(userId);
		imuser.setUpdatetime(operadate);
		sysCoreMapper.updateUserErrNum(imuser);
		IMUser newUser = sysCoreMapper.selectUserByUserid(userId);
		if (newUser == null) {
			logger.info("查询不到用户: {}",userId);
			return;
		}
		int errNum = StringUtil.parseInt(newUser.getPwderrnum());
		logger.info("用户名：{}，密码错误次数：{}", newUser.getUsername(), newUser.getPwderrnum());
		String pwderrcount = pubsSysBaseService.querySysBaseValue("PWDERRCOUNT");
		int count = StringUtil.parseInt(pwderrcount);
		if (errNum >= count) {
			logger.info("用户状态更新为冻结: {}",userId);
			newUser.setStatus("0");// 冻结
			sysCoreMapper.updateUser(newUser);
		}
	}

}
