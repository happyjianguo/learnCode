package cn.com.jansh.service.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.AppErrorCode;
import com.jansh.core.entity.sys.IMUser;
import com.jansh.core.exception.AppException;
import com.jansh.core.exception.SysErrorCode;
import com.jansh.core.security.authentication.dao.LoginAuthenticationProvider;
import com.jansh.core.service.sys.EmailService;
import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.IDUtils;
import com.jansh.comm.util.StringUtil;

import cn.com.jansh.entity.system.IMRole;
import cn.com.jansh.entity.system.IMUserRole;
import cn.com.jansh.mapper.system.IMUserMapper;
import cn.com.jansh.mapper.system.IMUserRoleMapper;
import cn.com.jansh.model.system.UserManageModel;
import cn.com.jansh.service.system.IMUserService;
import cn.com.jansh.service.system.PubsSysBaseService;

@Service
public class IMUserServiceImpl implements IMUserService {

	private static final Logger logger = LogManager.getLogger(IMUserServiceImpl.class);
	@Autowired
	private IMUserMapper imusermapper;
	@Autowired
	private IMUserRoleMapper imUserRoleMapper;
	@Autowired
	private IMUserRoleMapper imuserrolemapper;
	@Autowired
	private PubsSysBaseService pubsSysBaseService;
	@Autowired
	private LoginAuthenticationProvider enCoder;
	@Autowired
	private EmailService emailService;

	/**
	 * 
	 */
	@Override
	public List<IMUser> selectImuser(UserManageModel userManageModel) {
		IMUser imuser = new IMUser();
		imuser.setUsername(userManageModel.getQusername());
		imuser.setCname(userManageModel.getQcname());
		// imuser.setUsername(userManageModel.getCname());
		List<IMUser> li = imusermapper.queryImuser(imuser);
		// 如果查询条件中角色id不为空
		// 查询角色表中条件为userid 和 roleid的数据
		// 如果没有查询到，将此IMUser从list中删除
		if (!StringUtils.isEmpty(userManageModel.getQroleid())) {

			int listSize = li.size();
			for (int i = listSize - 1; i >= 0; i--) {
				IMUserRole userRole = new IMUserRole();
				userRole.setUserid(li.get(i).getUserid());
				userRole.setRoleid(userManageModel.getQroleid());
				IMUserRole checkExist = imuserrolemapper.selectOne(userRole);
				if (checkExist == null) {
					li.remove(i);
				}
			}
		}

		for (IMUser imu : li) {
			List<IMRole> roleids = imusermapper.queryRole(imu);
			StringBuffer rolename = new StringBuffer();
			String[] roleid = null;
			if (roleids != null && roleids.size() > 0) {
				roleid = new String[roleids.size()];
				for (int i = 0; i < roleids.size(); i++) {
					if (StringUtils.isNotBlank(roleids.get(i).getRoleName())) {
						rolename.append(roleids.get(i).getRoleName() + ",");
					}
					if (StringUtils.isNotBlank(roleids.get(i).getRoleId())) {
						roleid[i] = roleids.get(i).getRoleId();
					}
				}
			}

			imu.setRoleid(roleid);
			if (StringUtils.isNotBlank(rolename)) {
				imu.setRolename(rolename.toString().substring(0, rolename.toString().length() - 1));
			}
		}
		return li;
	}

	// @Override
	// public List<IMUser> selectImuserByUserId(UserManageModel userManageModel)
	// {
	// IMUser imuser = new IMUser();
	// imuser.setUserid(userManageModel.getUserid());
	// // imuser.setUsername(userManageModel.getCname());
	// List<IMUser> li = imusermapper.queryImuserByUserId(imuser);
	// // 如果查询条件中角色id不为空
	// // 查询角色表中条件为userid 和 roleid的数据
	// // 如果没有查询到，将此IMUser从list中删除
	// if (!StringUtils.isEmpty(userManageModel.getQroleid())) {
	//
	// int listSize = li.size();
	// for (int i = listSize - 1; i >= 0; i--) {
	// IMUserRole userRole = new IMUserRole();
	// userRole.setUserid(li.get(i).getUserid());
	// userRole.setRoleid(userManageModel.getQroleid());
	// IMUserRole checkExist = imuserrolemapper.selectOne(userRole);
	// if (checkExist == null) {
	// li.remove(i);
	// }
	// }
	// }
	//
	// for (IMUser imu : li) {
	// List<IMRole> roleids = imusermapper.queryRole(imu);
	// StringBuffer rolename = new StringBuffer();
	// String[] roleid = new String[roleids.size()];
	// if (roleids.size() > 0 && roleids != null) {
	// for (int i = 0; i < roleids.size(); i++) {
	// if (StringUtils.isNotBlank(roleids.get(i).getRoleName())) {
	// rolename.append(roleids.get(i).getRoleName() + ",");
	// }
	// if (StringUtils.isNotBlank(roleids.get(i).getRoleId())) {
	// roleid[i] = roleids.get(i).getRoleId();
	// }
	// }
	// }
	//
	// imu.setRoleid(roleid);
	// if (StringUtils.isNotBlank(rolename)) {
	// imu.setRolename(rolename.toString().substring(0,
	// rolename.toString().length() - 1));
	// }
	// }
	// return li;
	// }
	@Override
	public void updateImuser(IMUser imuser) throws AppException {
		if (!imuser.getUsername().equals(imuser.getOusername())) {
			if (null != selectByUsername(imuser.getUsername())) {
				throw new AppException(AppErrorCode.E120000);
			}
		}
		imuser.setUpdatetime(DateUtil.getDateTimestamp());
		imusermapper.updateImUser(imuser);

		List<String> li = new ArrayList<String>();
		li.add(imuser.getUserid());
		imuserrolemapper.delImUserRole(li);

		IMUserRole imuserrole = new IMUserRole();
		if (imuser.getRoleid() != null) {
			String[] roles = imuser.getRoleid();
			for (int i = 0; i < roles.length; i++) {
				imuserrole.setUserid(imuser.getUserid());
				imuserrole.setRoleid(roles[i]);
				imuserrole.setUpdatetime(DateUtil.getDateTimestamp());
				imuserrole.setCreatetime(DateUtil.getDateTimestamp());
				imuserrolemapper.insert(imuserrole);
			}
		}

	}

	/**
	 * 新增用户
	 * 
	 * @param userManageModel
	 * @throws AppException
	 */
	@Override
	public void useradd(UserManageModel userManageModel) throws AppException {
		IMUser user = selectByUsername(userManageModel.getUsername());
		if (null != user) {
			// 用户已存在
			throw new AppException(AppErrorCode.E280001);
		}
		
		IMUser imuser = new IMUser();
		imuser.setUsername(userManageModel.getUsername());
		imuser.setCname(userManageModel.getCname());
		imuser.setUserid(IDUtils.getTimeRandon());
		imuser.setPhoneno(userManageModel.getPhoneno());
		imuser.setEmail(userManageModel.getEmail());
		imuser.setCreatetime(DateUtil.getDateTimestamp());
		imuser.setUpdatetime(DateUtil.getDateTimestamp());
		String pwd_y = RandomStringUtils.randomAlphanumeric(6);
		String pwd = enCoder.getPasswordEncoder().encode(enCoder.proccessPasswd(imuser.getUserid(), pwd_y));
		imuser.setPasswd(pwd);
		try {
			imusermapper.insert(imuser);
			if (userManageModel.getRoleids() != null) {
				String[] roleid = userManageModel.getRoleids();
				String date = DateUtil.getDateTimestamp();

				IMUserRole iMUserRole = new IMUserRole();
				for (int i = 0; i < roleid.length; i++) {
					iMUserRole.setRoleid(roleid[i]);
					iMUserRole.setUserid(imuser.getUserid());
					iMUserRole.setCreatetime(date);
					iMUserRole.setUpdatetime(date);
					imuserrolemapper.insert(iMUserRole);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			throw new AppException(SysErrorCode.E100001);
		}
		try {
			emailService.sendmail(userManageModel.getEmail(), "场景化金融营销平台内管系统新增用户",
					"【场景化金融营销平台内管系统】新增用户成功，用户名：" + userManageModel.getUsername() + ",密码:" + pwd_y);
		} catch (EmailException e) {
			logger.error("发送邮件错误：{}", e);
		}
	}

	@Override
	public int insertImuser(IMUser imuser) {
		if (null != selectByUsername(imuser.getUsername())) {
			return 0;
		}
		imuser.setCreatetime(DateUtil.getDateTimestamp());
		imuser.setUpdatetime(DateUtil.getDateTimestamp());
		String pwd = enCoder.getPasswordEncoder()
				.encode(enCoder.proccessPasswd(imuser.getUserid(), pubsSysBaseService.querySysBaseInitPwd()));
		imuser.setPasswd(pwd);
		try {
			imusermapper.insert(imuser);
			if (imuser.getRoleid().length > 0) {
				String[] roleid = imuser.getRoleid();
				String date = DateUtil.getDateTimestamp();

				IMUserRole iMUserRole = new IMUserRole();
				for (int i = 0; i < roleid.length; i++) {
					iMUserRole.setRoleid(roleid[i]);
					iMUserRole.setUserid(imuser.getUserid());
					iMUserRole.setCreatetime(date);
					iMUserRole.setUpdatetime(date);
					imuserrolemapper.insert(iMUserRole);
				}
			}
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public int delImuser(List<String> userids) {
		/*删除用户角色关联信息*/
		imuserrolemapper.delImUserRole(userids);
		/*删除用户*/
		imusermapper.delImUser(userids);
		return 1;
	}

	@Override
	public IMUser selectByUsername(String username) {
		return imusermapper.selectOne(username);
	}

	@Override
	public int initPwd(Map<String, String> map) throws EmailException {

		IMUser user = imusermapper.selectByUserid(map.get("userid"));
		IMUser imuser = new IMUser();
		imuser.setUserid(map.get("userid"));
		imuser.setUpdatetime(DateUtil.getDateTimestamp());
		String pwd_y = RandomStringUtils.randomAlphanumeric(6);
		String pwd = enCoder.getPasswordEncoder().encode(enCoder.proccessPasswd(map.get("userid"), pwd_y));
		imuser.setPasswd(pwd);
		imuser.setPwderrnum("0");
		imuser.setStatus("1");
		int flag = imusermapper.updateImUser(imuser);
		String email = user.getEmail();
		try {
			emailService.sendmail(email, "场景化金融营销平台内管系统密码重置", "【场景化金融营销平台内管系统】密码重置成功，新密码:" + pwd_y);
		} catch (EmailException e) {
			logger.error("发送邮件错误：{}", e);
		}
		return flag;
	}

	@Override
	public IMUser selectByUserid(String userid) {
		return imusermapper.selectByUserid(userid);
	}

	@Override
	public UserManageModel selectModelByUserid(String userid) {
		UserManageModel userManageModel = new UserManageModel();
		IMUser imuser = imusermapper.selectByUserid(userid);
		List<String> roleids = imUserRoleMapper.selectRoleidByUserid(userid);
		userManageModel.setUserid(userid);
		userManageModel.setUsername(imuser.getUsername());
		userManageModel.setCname(imuser.getCname());
		userManageModel.setPhoneno(imuser.getPhoneno());
		userManageModel.setEmail(imuser.getEmail());
		userManageModel.setRoleids(roleids.toArray(new String[roleids.size()]));
		return userManageModel;
	}

	@Override
	public int updatePwdErrNum(Map<String, String> map) {
		IMUser imuser = new IMUser();
		imuser.setUserid(map.get("userid"));
		imuser.setPwderrnum(map.get("pwserrnum"));
		imuser.setUpdatetime(DateUtil.getDateTimestamp());
		if (!StringUtils.isBlank(map.get("status"))) {
			imuser.setStatus(map.get("status"));
		}
		return imusermapper.updateImUser(imuser);
	}

	@Override
	public int updatePwdErr(IMUser imuser) {

		return imusermapper.updateImUserErr(imuser);
	}

	/**
	 * 更新校验密码错误次数
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public int updateErrNumSafe(Map<String, String> map) {
		IMUser imuser = new IMUser();
		imuser.setUsername(map.get("username"));
		imuser.setUpdatetime(map.get("updatetime"));
		imusermapper.updateImUserErrNum(imuser);
		IMUser newUser = imusermapper.selectOne(map.get("username"));
		if (newUser == null) {
			return 0;
		}
		int errNum = StringUtil.parseInt(newUser.getPwderrnum());
		String pwderrcount = pubsSysBaseService.querySysBaseValue("PWDERRCOUNT");
		int count = StringUtil.parseInt(pwderrcount);
		if (errNum >= count) {
			newUser.setStatus("0");// 冻结
			return imusermapper.updateImUser(newUser);
		}
		return 0;
	}

	@Override
	public int updatePwd(Map<String, String> map) {
		IMUser imuser = new IMUser();
		imuser.setUserid(map.get("userid"));
		imuser.setPasswd(map.get("passwd"));
		return imusermapper.updateImUser(imuser);
	}

	/**
	 * 更新用户信息
	 */
	@Override
	public void updateUserInfo(IMUser imuser) throws AppException {
		imuser.setUpdatetime(DateUtil.getDateTimestamp());
		imusermapper.updateImUser(imuser);
	}
}
