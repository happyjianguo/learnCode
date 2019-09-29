package cn.com.jansh.service.system.impl;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.IDUtils;
import com.jansh.comm.util.StringUtil;
import com.jansh.core.entity.sys.IMUser;
import com.jansh.core.entity.sys.PubsSysBase;
import com.jansh.core.exception.AppException;
import com.jansh.core.exception.SysErrorCode;
import com.jansh.core.security.authentication.dao.LoginAuthenticationProvider;
import com.jansh.core.service.sys.EmailService;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.entity.system.IMRole;
import cn.com.jansh.entity.system.IMUserN;
import cn.com.jansh.entity.system.IMUserRole;
import cn.com.jansh.mapper.system.IMUserMapper;
import cn.com.jansh.mapper.system.IMUserRoleMapper;
import cn.com.jansh.model.component.PassWordModel;
import cn.com.jansh.model.system.RegisterAccountModel;
import cn.com.jansh.model.system.UserManageModel;
import cn.com.jansh.service.system.IMUserService;
import cn.com.jansh.service.system.PubsSysBaseService;
import cn.com.jansh.vo.AjaxObj;

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
	@Autowired
	private GlobalProperties globalProperties;
	@Autowired
	private PubsSysBaseService baseService;

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
			emailService.sendmail(userManageModel.getEmail(), "话费流量分发平台新增用户",
					"【话费流量分发平台】新增用户成功，用户名：" + userManageModel.getUsername() + ",密码:" + pwd_y);
		} catch (EmailException e) {
			logger.error("发送邮件错误：{}", e);
		}
	}
	/**
	 * 
	 * 查询同一机构下的用户
	 * 
	 */
	@Override
	public List<IMUserN> queryUserInOrg(){
		String userid = AppUtil.getUserDetail().getUserid();
		IMUserN user = imusermapper.selectNewByUserid(userid);
		Map<String, Object> map = new HashMap<>();
		map.put("orgid", user.getOrgid());
		return imusermapper.selectInOrg(map);
	}
	
	/**
	 * 校验验证码
	 * 
	 * @throws AppException
	 * 
	 */
	@Override
	public void checkValidCode(HttpServletRequest request) throws AppException {
		logger.info("开始校验验证码");
		HttpSession session = request.getSession();
//		String username = request.getParameter("username");
		String phoneno = request.getParameter("phoneno");
		String validecode = request.getParameter("validecode");
		@SuppressWarnings("unchecked")
		Map<String, String> attribute = (Map<String, String>) session.getAttribute("_app_msg_code") == null
				? new HashMap<String, String>() : (Map<String, String>) session.getAttribute("_app_msg_code");
		Date nowtime = new Date();
		if (!attribute.isEmpty()) {
			if (nowtime.getTime()
					- Long.parseLong(attribute.get("time")) > (Integer.parseInt(globalProperties.getSendInterval()) * 60 * 1000)) {
				logger.info("验证码超时！！");
				session.removeAttribute("_app_msg_code");
				throw new AppException(AppErrorCode.E200020);
			} else if (attribute.get("msgcode") == null || !attribute.get("msgcode").equals(validecode)) {
				logger.info("验证码不正确！！");
				throw new AppException(AppErrorCode.E200010);
			}
		} else {
			logger.info("未获取验证码！！");
			throw new AppException(AppErrorCode.E200030);
		}

		logger.info("校验通过，开始获取用户信息");

		IMUser user = imusermapper.selectOneByPhoneno(phoneno);

		if (user==null) {
			logger.info("手机号填写不正确！");
			throw new AppException(AppErrorCode.E280002);
		}
		
		session.removeAttribute("_app_msg_code");
		request.setAttribute("username", user.getUsername());
	}
	
	/**
	 * 修改账户密码
	 * @param request
	 * @throws AppException 
	 */
	@Override
	public void editpassword(PassWordModel passWordModel) throws AppException {
		String opwd = passWordModel.getOpwd();
		String pwd1 = passWordModel.getPwd1();
		String pwd2 = passWordModel.getPwd2();
		// 校验数据是否为空

		// 获取当前用户的id
		String userid = AppUtil.getUserDetail().getUserid();

		if (StringUtils.isBlank(opwd) || StringUtils.isBlank(pwd1) || StringUtils.isBlank(pwd2)) {
			logger.error("密码不能为空");
			throw new AppException(AppErrorCode.E200006);
		}

		// 校验新密码密码是否一致
		if (!pwd1.equals(pwd2)) {
			logger.error("两次密码输入不一致!");
			throw new AppException(AppErrorCode.E200003);
		}

		String pwdString = enCoder.proccessPasswd(userid, opwd);
		IMUser user = selectByUserid(userid);
		// 旧密码校验失败更新校验次数
		if (!enCoder.getPasswordEncoder().matches(pwdString, user.getPasswd())) {
			logger.error("原密码校验失败");
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userid);
			map.put("updatetime", DateUtil.getDateTimestamp());
			int pwserrnum = Integer.parseInt(user.getPwderrnum()) + 1;
			map.put("pwserrnum", String.valueOf(pwserrnum));
			PubsSysBase pubsSysBase = baseService.querySysBaseAceBaseId("PWDERRCOUNT");
			String errNumValue = pubsSysBase.getSysBaseValue();
			int errnum = Integer.parseInt(errNumValue);
			if (pwserrnum >= errnum) {
				map.put("status", "0");
				logger.error("密码错误次数已超限，请联系管理员进行密码重置！");
			}
			updatePwdErrNum(map);

			if (pwserrnum >= errnum) {
				logger.error("密码错误次数已超限，请联系管理员进行密码重置！");
				throw new AppException(AppErrorCode.E200005);
			} else {
				// 原密码输入错误
				throw new AppException(AppErrorCode.E200004);
			}
		}
		String pwd1String = enCoder.proccessPasswd(userid, pwd1);
		if (pwd1String.equals(pwdString)) {
			logger.error("新密码与旧密码不能相同，请重新输入！");	
			throw new AppException(AppErrorCode.E200007);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("passwd", enCoder.getPasswordEncoder().encode(enCoder.proccessPasswd(userid, pwd1)));		
		map.put("updatetime", DateUtil.getDateTimestamp());
		map.put("pwserrnum", "0");
		// 更新密码
		updatePwd(map);
	}
	/**
	 * 获取登录用户信息
	 * @return IMUser
	 */
	@Override
	public IMUser getCurrentUser(){
		logger.info("获取当前用户信息");
		// 获取当前用户的id
		String userid = AppUtil.getUserDetail().getUserid();
		IMUser user = imusermapper.selectByUserid(userid);
		return user;
	}

	/**
	 * 根据验证码修改账户密码
	 * 
	 * @param imuser
	 * @return
	 * @throws AppException
	 */
	@Override
	public void modifypassword(HttpServletRequest request) throws AppException {
		logger.info("开始修改用户密码");
		String username = request.getParameter("username");
		String newsecret = request.getParameter("newsecret");
		String newsecretagain = request.getParameter("newsecretagain");

		if (StringUtils.isNotBlank(newsecret) && newsecret.equals(newsecretagain)) {
			logger.info("密码校验通过！");
			IMUser user = selectByUsername(username);
			String encrysecret = enCoder.getPasswordEncoder()
					.encode(enCoder.proccessPasswd(user.getUserid(), newsecret));
			user.setPasswd(encrysecret);
			user.setUpdatetime(DateUtil.getDateTimestamp());
			user.setPwderrnum("0");//错误次数清零
			user.setStatus(AppCommonsCode.UserRightStatus.value());
			imusermapper.updateImUser(user);
		} else {
			logger.error("两次密码输入不一致！");
			throw new AppException(AppErrorCode.E200003);
		}
	}

	/**
	 * 校验验证码 保存新用户信息
	 * 
	 * @throws AppException
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int insertImuserNew(RegisterAccountModel model, HttpSession session) throws AppException {
		logger.info("开始校验验证码！");
		Map<String, String> attribute = new HashMap<String, String>();
		if (session.getAttribute("_app_msg_code") != null) {
			attribute = (Map<String, String>) session.getAttribute("_app_msg_code");
		}
		Date nowtime = new Date();
		int interval = Integer.parseInt(globalProperties.getSendInterval());
		String validecode = model.getValidcode();
		if (!attribute.isEmpty()) {
			if (nowtime.getTime() - Long.parseLong(attribute.get("time")) > (interval*60 * 1000)) {
				logger.info("验证码超时！！");
				session.removeAttribute("_app_msg_code");
				throw new AppException(AppErrorCode.E200020);
			} else if (attribute.get("msgcode") == null || !attribute.get("msgcode").equals(validecode)) {
				logger.info("验证码不正确！！");
				throw new AppException(AppErrorCode.E200010);
			}
		} else {
			logger.info("未获取验证码！！");
			throw new AppException(AppErrorCode.E200030);
		}
		
		if(!model.getPasswd().equals(model.getPasswdagain())){
			logger.info("两次密码输入不一致！！");
			throw new AppException(AppErrorCode.E200003);
		}

		logger.info("开始保存用户信息！");
		IMUserN exist = imusermapper.selectNewByUserNameOrPhoneOrEmail(model.getUsername());
		if (exist != null && StringUtils.isNotBlank(exist.getUserid())) {
			throw new AppException(AppErrorCode.E280001);//用户名已存在
		}
		IMUserN imu = imusermapper.selectNewByUserNameOrPhoneOrEmail(model.getPhoneno());
		if (imu != null && StringUtils.isNotBlank(imu.getUserid())) {
			throw new AppException(AppErrorCode.E280003);//手机号已存在
		}
		
		
		IMUserN imUser = new IMUserN();
		String userid = IDUtils.getTimeRandon();
		String createtime = DateUtil.getDateTimestamp();
		String updatetime = createtime;
		String orgid = IDUtils.getTimeRandon();
		imUser.setUserid(userid);
		imUser.setCreatetime(createtime);
		imUser.setUpdatetime(updatetime);
		imUser.setOusername(imUser.getUsername());
		imUser.setPwderrnum("0");
		imUser.setStatus(AppCommonsCode.UserDefaultStatus.value());
		imUser.setOrgid(orgid);
		try {
			PropertyUtils.copyProperties(imUser, model);
		} catch (Exception e) {
			logger.error("拷贝对象属性异常{}", e);
		}
		imUser.setCname(imUser.getPhoneno());
		try {
			imUser.setPasswd(enCoder.getPasswordEncoder().encode(enCoder.proccessPasswd(userid, model.getPasswd())));
			int insertSize = imusermapper.insertNew(imUser);
			IMUserRole iMUserRole = new IMUserRole();
			iMUserRole.setRoleid(globalProperties.getDefaultRoleid());
			iMUserRole.setUserid(userid);
			iMUserRole.setCreatetime(createtime);
			iMUserRole.setUpdatetime(updatetime);
			imuserrolemapper.insert(iMUserRole);
			session.removeAttribute("_app_msg_code");
			return insertSize;
		} catch (Exception e) {
			logger.error("插入用户异常{}", e);
			throw new AppException(AppErrorCode.E120001);
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
		imusermapper.delImUser(userids);
		imuserrolemapper.delImUserRole(userids);
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
			emailService.sendmail(email, "话费流量分发平台密码重置", "【话费流量分发平台】密码重置成功，新密码:" + pwd_y);
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
	public IMUserN selectNewByUserid(String userid) {
		return imusermapper.selectNewByUserid(userid);
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
	public void updateUserInfo(HttpServletRequest request , UserManageModel userManageModel) throws AppException {

		logger.info("开始校验验证码");
		HttpSession session = request.getSession();
		String validecode = userManageModel.getValidecode();
		@SuppressWarnings("unchecked")
		Map<String, String> attribute = (Map<String, String>) session.getAttribute("_app_msg_code") == null
				? new HashMap<String, String>() : (Map<String, String>) session.getAttribute("_app_msg_code");
		Date nowtime = new Date();
		if (!attribute.isEmpty()) {
			if (nowtime.getTime()
					- Long.parseLong(attribute.get("time")) > (Integer.parseInt(globalProperties.getSendInterval()) * 60 * 1000)) {
				logger.info("验证码超时！！");
				session.removeAttribute("_app_msg_code");
				throw new AppException(AppErrorCode.E200020);
			} else if (attribute.get("msgcode") == null || !attribute.get("msgcode").equals(validecode)) {
				logger.info("验证码不正确！！");
				throw new AppException(AppErrorCode.E200010);
			}
		} else {
			logger.info("未获取验证码！！");
			throw new AppException(AppErrorCode.E200030);
		}

		String userId = AppUtil.getUserDetail().getUserid();
		/** 如果用户上传了头像跟营业执照图片则先上传图片 **/
		uploadImageToLocal(userManageModel.getMyfiles(), userId, globalProperties.getUploadIconPath());
		uploadImageToLocal(userManageModel.getMylicense(), userId, globalProperties.getUploadLicensePath());
		
		Map<String,Object> map = new HashMap<>();
		map.put("email", userManageModel.getEmail());
		map.put("userid", userId);
		IMUserN exist = imusermapper.selectNewByUserNameOrPhoneOrEmail(userManageModel.getEmail());
		if(exist != null && !exist.getUserid().equals(userId)){
			logger.info("邮箱已经存在！");
			throw new AppException(AppErrorCode.E280004);
		}
		List<IMUser> users = imusermapper.checkInfoExist(map);
		if(CollectionUtils.isNotEmpty(users)){
			logger.info("邮箱已经存在！");
			throw new AppException(AppErrorCode.E280004);
		}
		map.remove("email");
		map.put("phoneno", userManageModel.getPhoneno());
		exist = imusermapper.selectNewByUserNameOrPhoneOrEmail(userManageModel.getPhoneno());
		if(exist != null && !exist.getUserid().equals(userId)){
			logger.info("手机号已经存在！");
			throw new AppException(AppErrorCode.E280004);
		}
		users = imusermapper.checkInfoExist(map);
		if(CollectionUtils.isNotEmpty(users)){
			logger.info("手机号已经存在！");
			throw new AppException(AppErrorCode.E280003);
		}
		
		/** 更新用户信息 **/
		IMUserN imuser = new IMUserN();
		imuser.setUserid(userId);
		imuser.setPhoneno(userManageModel.getPhoneno());
		imuser.setUsername(userManageModel.getUsername());
		imuser.setMf(userManageModel.getMf());
		imuser.setEmail(userManageModel.getEmail());
		imuser.setUpdatetime(DateUtil.getDateTimestamp());
		imusermapper.updateImUserN(imuser);
		session.removeAttribute("_app_msg_code");
	}

	@Override
	public void showUserHead(HttpServletRequest request , HttpServletResponse response) {
		// 通过get方式获取图片的地址 然后跟硬盘路径拼接成绝对路径
		// 通过二进制流 将图片显示到页面上
		String name = AppUtil.getUserDetail().getUserid();
		logger.info("图片name是：" + name);
		OutputStream output = null;
		InputStream fis = null;
		StringBuilder builder = new StringBuilder();
		builder.append(globalProperties.getIconServer());
		builder.append(name);
		builder.append(".png");
			try {
				response.reset();
				response.setCharacterEncoding("utf-8");
				response.setContentType("image/png; charset=utf-8");
				response.setHeader("Content-Disposition",
						"attachment; filename=" + new String((name+".png").getBytes("utf-8"), "utf-8"));
			} catch (UnsupportedEncodingException e1) {
				logger.error("字符编码异常！",e1);
			}
			URL url;
			try {
				url = new URL(builder.toString());
				URLConnection connection = url.openConnection();
				fis = connection.getInputStream();
				logger.info("图片地址是：" + builder.toString());
			}catch (IOException e) {
				String realPath = request.getSession().getServletContext().getRealPath("");
				File file = new File(realPath+"resources/img/default.png");
				try {
					fis = new FileInputStream(file);
				} catch (FileNotFoundException e1) {
					logger.error("头像图片未找到！",e1);
					return;
				}
			}
			try {
				output = response.getOutputStream();
				byte[] b = new byte[1024];
				int i = 0;

				while ((i = fis.read(b)) != -1) {

					output.write(b, 0, i);
				}

				output.flush();
				response.flushBuffer();
			} catch (Exception e) {
				logger.error("图片显示异常{}", e);
			} finally {
				try {
					if (fis != null) {
						fis.close();
						fis = null;
					}
					if (output != null) {
						output.close();
						output = null;
					}
				} catch (Exception e2) {
				}
			}
	}

	private void uploadImageToLocal(MultipartFile myfiles, String userid, String imagePath) throws AppException {

		if (myfiles.isEmpty()) {
			return;
		}
		String imageType = myfiles.getContentType();
		
		if (imageType.equals("image/png")) {
			imageType = "png";
		} else if (imageType.equals("image/jpg")) {
			imageType = "jpg";
		} else if (imageType.equals("image/jpeg")) {
			imageType = "jpeg";
		} else {
			logger.error("图片类型不符合要求！");
			throw new AppException(AppErrorCode.E200100);
		}
		long imageSize = myfiles.getSize();
		if (imageSize > Integer.parseInt(globalProperties.getImageMaxSize()) * 1024 * 1024) {
			logger.error("图片过大！");
			throw new AppException(AppErrorCode.E200200);
		}
		StringBuilder builder = new StringBuilder();
		builder.append(imagePath);
		builder.append(userid);
		builder.append(".");
		// 再添加上图片类型
		builder.append("png");
		String iconPathAndName = builder.toString();
		File toPath = new File(imagePath);
		File toFile = new File(iconPathAndName);

		if (!toPath.exists()) {
			toPath.mkdirs();
		}
		try {
			if (!toFile.exists()) {
				toFile.createNewFile();
			}
			OutputStream output = null;
			InputStream fis = null;
			try {
				output = new FileOutputStream(toFile);
				fis = compressPic(myfiles.getInputStream());
				if(fis == null){
					fis = myfiles.getInputStream();
				}
				byte[] b = new byte[1024];
				int i = 0;

				while ((i = fis.read(b)) != -1) {

					output.write(b, 0, i);
				}
				output.flush();
			} catch (Exception e) {
				logger.error("图片上传异常{}", e);
				throw new AppException(AppErrorCode.E200300);
			} finally {
				if (fis != null) {
					fis.close();
					fis = null;
				}
				if (output != null) {
					output.close();
					output = null;
				}
			}
		} catch (Exception e) {
			logger.error("图片上传异常{}", e);
			throw new AppException(AppErrorCode.E200300);
		}
	}
	/**
	 * 图片压缩处理
	 * @param input
	 * @return
	 */
	private  InputStream compressPic(InputStream input) {
		int legth = 300;
		BufferedImage img;
		BufferedImage tag = null;
		ImageOutputStream imOut = null;
		InputStream is = null;
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		try {
			imOut = ImageIO.createImageOutputStream(bs);
			img = ImageIO.read(input);
			if (img.getWidth(null) > legth || img.getHeight(null) > legth) {
				logger.info("img.getWidth(null):" + img.getWidth(null) + ",img.getHeight(null):" + img.getHeight(null));
				int newWidth;
				int newHeight;
				double rate = img.getWidth(null) > img.getHeight(null) ? ((double) img.getHeight(null)) / (double) legth
						: ((double) img.getWidth(null)) / (double) legth;
				newWidth = (int) (((double) img.getWidth(null)) / rate);
				newHeight = (int) (((double) img.getHeight(null)) / rate);
				tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
//				if (tag.getHeight() < tag.getWidth()) {
//					tag = rotateImage(tag, 90);
//				}
				ImageIO.write(tag, "jpg", imOut);
				is = new ByteArrayInputStream(bs.toByteArray());
			} else {
				return null;
			}
		} catch (IOException e) {
			logger.error("压缩图片异常{}", e);
		}
		return is;
	}
	
	/**
	 * 图片旋转
	 * @param bufferedimage
	 * @param degree
	 * @return
	 */
	public static BufferedImage rotateImage(final BufferedImage bufferedimage, final int degree) {
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		int type = bufferedimage.getColorModel().getTransparency();

		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(h, w, type)).createGraphics())
				.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		logger.info(h / 2 + "," + w / 2);

		graphics2d.rotate(Math.toRadians(degree), h / 2, h / 2);
		graphics2d.drawImage(bufferedimage, 0, 0, null);
		graphics2d.dispose();
		return img;
	}

	/**
	 * 查询手机号是否存在
	 * @return AjaxObj
	 */
	@Override
	public AjaxObj checkPhoneNo(String phoneno) {
		logger.info("开始查询手机号"+phoneno);
		AjaxObj ajaxObj = new AjaxObj();
		IMUser user = imusermapper.selectOneByPhoneno(phoneno);
		if(user != null){
			ajaxObj.setResult(1);
		}else{
			ajaxObj.setResult(0);
		}
		/*CloudSecurityLimitEntity phoneResult = limitMapper.selectByPhone(phoneno);
		if(phoneResult != null){
			String current = DateUtil.getDateTimestamp();
			String updatetime = phoneResult.getUpdatetime();
			int interval = Integer.parseInt(globalProperties.getSendInterval());
				Date update;
				try {
					update = new SimpleDateFormat("yyyyMMddHHmmss").parse(updatetime);
					Date currentdate = new SimpleDateFormat("yyyyMMddHHmmss").parse(current);
					if(currentdate.getTime() - update.getTime() < interval*60*1000 ){
						ajaxObj.setResult(0);
					}
				} catch (ParseException e) {
					logger.error("解析异常",e);
					ajaxObj.setResult(0);
				}
				
		}*/
		
		return ajaxObj;
	}
	/**
	 * 查询手机号是否存在
	 * @return AjaxObj
	 */
	@Override
	public AjaxObj checkPhoneExist(String phoneno ,String email,String username) {
		logger.info("开始查询手机号"+phoneno);
		AjaxObj ajaxObj = new AjaxObj();
		String userId = AppUtil.getUserDetail().getUserid();
		Map<String, Object> map = new HashMap<>();
		map.put("phoneno", phoneno);
		map.put("email", email);
		map.put("username", username);
		map.put("userid", userId);
		List<IMUser> users = imusermapper.checkInfoExist(map);
		if(CollectionUtils.isNotEmpty(users)){
			ajaxObj.setResult(0);
		}else{
			ajaxObj.setResult(1);
		}
		return ajaxObj;
	}
	/**
	 * 查看验证码是否有效
	 * @return AjaxObj
	 */
	@Override
	public AjaxObj checkValidate(HttpServletRequest request) {
		logger.info("开始校验验证码");
		AjaxObj ajaxObj = new AjaxObj();
		HttpSession session = request.getSession();
		String validecode = request.getParameter("validecode");
		@SuppressWarnings("unchecked")
		Map<String, String> attribute = (Map<String, String>) session.getAttribute("_app_msg_code") == null
				? new HashMap<String, String>() : (Map<String, String>) session.getAttribute("_app_msg_code");
		Date nowtime = new Date();
		if (!attribute.isEmpty()) {
			if (nowtime.getTime()
					- Long.parseLong(attribute.get("time")) > (Integer.parseInt(globalProperties.getSendInterval()) * 60 * 1000)) {
				logger.info("验证码超时！！");
				session.removeAttribute("_app_msg_code");
				ajaxObj.setResult(0);
				return ajaxObj;
			}
			if (attribute.get("msgcode") == null || !attribute.get("msgcode").equals(validecode)) {
				logger.info("验证码不正确！！");
				ajaxObj.setResult(0);
				return ajaxObj;
			}
		} else {
			logger.info("未获取验证码！！");
			ajaxObj.setResult(0);
			return ajaxObj;
		}
		ajaxObj.setResult(1);
		return ajaxObj;
	}

}
