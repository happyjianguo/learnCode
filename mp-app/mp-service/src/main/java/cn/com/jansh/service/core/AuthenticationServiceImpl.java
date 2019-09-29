package cn.com.jansh.service.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jansh.comm.util.StringUtil;
import com.jansh.core.entity.sys.IMUser;
import com.jansh.core.entity.sys.IMUserLog;
import com.jansh.core.mapper.SysCoreMapper;
import com.jansh.core.service.listener.AuthenticationService;
import com.jansh.core.service.sys.DefSysBaseService;

public class AuthenticationServiceImpl implements AuthenticationService {

	private static final Logger logger = LogManager.getLogger(AuthenticationServiceImpl.class);

	@Autowired
	private SysCoreMapper sysCoreMapper;
	@Autowired
	private DefSysBaseService pubsSysBaseService;

	/**
	 * 插入操作日志
	 */
	@Override
	public void insertLog(IMUserLog imUserLog) {
		sysCoreMapper.insertUserLog(imUserLog);
	}

	@Override
	public int updatePwdErr(IMUser imuser) {
		return sysCoreMapper.updateUserErr(imuser);
	}

	@Override
	public void updateErrNumSafe(String userId, String operadate) {
		IMUser imuser = new IMUser();
		imuser.setUserid(userId);
		imuser.setUpdatetime(operadate);
		sysCoreMapper.updateUserErrNum(imuser);
		IMUser newUser = sysCoreMapper.selectUserByUserid(userId);
		if (newUser == null) {
			logger.info("查询不到用户: {}", userId);
			return;
		}
		int errNum = StringUtil.parseInt(newUser.getPwderrnum());
		logger.info("用户名：{}，密码错误次数：{}", newUser.getUsername(), newUser.getPwderrnum());
		String pwderrcount = pubsSysBaseService.querySysBaseValue("PWDERRCOUNT");
		int count = StringUtil.parseInt(pwderrcount);
		if (errNum >= count) {
			logger.info("用户状态更新为冻结: {}", userId);
			newUser.setStatus("0");// 冻结
			sysCoreMapper.updateUser(newUser);
		}

	}

	@Override
	public void queryUser(String username) {
		// TODO Auto-generated method stub

	}

}
