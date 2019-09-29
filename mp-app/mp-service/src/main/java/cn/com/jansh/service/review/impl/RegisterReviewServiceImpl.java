/**
 * RegisterReviewServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月18日
 */
package cn.com.jansh.service.review.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.constant.ContextCode;

import com.jansh.core.entity.sys.PubsSysBase;
import com.jansh.core.exception.AppException;
import com.jansh.core.security.authentication.dao.LoginAuthenticationProvider;
import com.jansh.core.service.sys.EmailService;
import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.IDUtils;

import cn.com.jansh.entity.review.FsmUserEntity;
import cn.com.jansh.entity.review.FsmUserRoleEntity;
import cn.com.jansh.mapper.review.IFsMUserRoleMapper;
import cn.com.jansh.mapper.review.IRegisterReviewMapper;
import cn.com.jansh.mapper.system.PubsSysBaseMapper;
import cn.com.jansh.model.review.RegisterReviewModel;
import cn.com.jansh.service.review.RegisterReviewService;

/**
 * 用户注册审核
 * @author xieliangliang
 * @version 1.0
 */
@Service
public class RegisterReviewServiceImpl implements RegisterReviewService{
	
	private static final Logger logger = LogManager.getLogger(RegisterReviewServiceImpl.class);

	@Autowired
	public IRegisterReviewMapper registerReviewMapper;
	
	@Autowired
	private LoginAuthenticationProvider enCoder;
	
	@Autowired
	private PubsSysBaseMapper baseMapper;
	
	@Autowired
	private IFsMUserRoleMapper imuserrolemapper;
	
	@Autowired
	private EmailService emailService;
	
	/**
	 * 查询所有未审核状态的注册信息
	 */
	@Override
	public List<FsmUserEntity> query(FsmUserEntity entity) {
		List<FsmUserEntity> list = registerReviewMapper.query(entity);
			
		for(int i=0; i<list.size(); i++){
			
			//将数据库中的时间格式转换成 yyyy-MM-dd HH:mm:ss用于显示
			list.get(i).setCreatetime(DateUtil.formatDateTimestamp(list.get(i).getCreatetime()));
			
			if(StringUtils.isNotBlank(list.get(i).getUpdatetime())) {
				list.get(i).setUpdatetime(DateUtil.formatDateTimestamp(list.get(i).getUpdatetime()));
			}
		}
				
		return list;
	}

	/**
	 * 根据用户ID查询用户信息
	 */
	@Override
	public FsmUserEntity queryByUserId(String userid) {
		return registerReviewMapper.queryByUserId(userid);
	}
	
	/**
	 * 根据用户账号查询用户信息
	 */
	@Override
	public FsmUserEntity queryByUserName(String userName) {
		return registerReviewMapper.queryByUserName(userName);
	}

	/**
	 * 更新用户审核状态
	 */
	@Override
	public void updateStatus(FsmUserEntity modelToEntity) {
		registerReviewMapper.updateStatus(modelToEntity);
	}

	/**
	 * 新增会员用户信息
	 */
	@Override
	public void adddata(RegisterReviewModel registerReviewModel)  throws AppException{
		logger.info("新增会员信息,{}",registerReviewModel);
		FsmUserEntity user = queryByUserName(registerReviewModel.getUsername());
		if (null != user) {
			// 用户已存在
			logger.error("用户已存在,{}",registerReviewModel.getUsername());
			throw new AppException(AppErrorCode.E280001);
		}
		
		FsmUserEntity datap = new FsmUserEntity();
		String id = IDUtils.getTimeRandon(); //ID唯一，除用户表需要后续权限也需要此ID
		datap.setUserid(id);
		datap.setUsername(registerReviewModel.getUsername());
		datap.setOrgid(registerReviewModel.getOrgid());
		String pwd_y = registerReviewModel.getPasswd();
		String pwd = enCoder.getPasswordEncoder().encode(enCoder.proccessPasswd(id, pwd_y));
		datap.setPasswd(pwd);
		datap.setCname(registerReviewModel.getCname());
		datap.setPwderrnum("0"); //密码错误次数
		datap.setStatus(registerReviewModel.getStatus()); //用户状态暂由前台录入   正常-0ContextCode.USER_STATUS_OK.value()
		datap.setCreatetime(DateUtil.getDateTimestamp());
		datap.setUpdatetime(DateUtil.getDateTimestamp());
		datap.setEmail(registerReviewModel.getEmail());
		datap.setPhoneno(registerReviewModel.getPhoneno());
		datap.setMf(registerReviewModel.getMf());
		
		//保存用户信息 
		registerReviewMapper.saveData(datap);
		
		//到系统参数表获取默认的机构ID
		List<PubsSysBase> syslist = baseMapper.selectBasesByBaseId(ContextCode.USER_ROLE_ID.value());
		String sysValue = syslist.get(0).getSysBaseValue(); //获取系统参数值
		logger.info("会员表部分处理成功！插入机构权限表,{}",sysValue);
		//将用户权限存储数据库
		FsmUserRoleEntity iMUserRole = new FsmUserRoleEntity();
		iMUserRole.setRoleid(sysValue); //角色的ID
		iMUserRole.setUserid(id); //用户的ID
		iMUserRole.setCreatetime(DateUtil.getDateTimestamp());
		iMUserRole.setUpdatetime(DateUtil.getDateTimestamp());
		imuserrolemapper.insert(iMUserRole); //保存到权限表
	}

	/**
	 * 编辑更新用户信息
	 */
	@Override
	public void updateInfo(FsmUserEntity entity) {
		logger.info("更新会员全部信息,{}",entity);
		registerReviewMapper.updateInfo(entity);	
	}
	
	/**
	 * 编辑更新用户信息-不更新机构
	 */
	@Override
	public void updateInfoNoOrgId(FsmUserEntity entity) {
		logger.info("更新会员信息，不修改机构,{}",entity);
		registerReviewMapper.updateInfoNoOrgId(entity);	
	}

	/**
	 * 删除会员用户
	 */
	@Override
	public void delMemberInfo(String userid) {
		logger.info("删除会员ID为:{}的信息",userid);
		registerReviewMapper.delMemberInfo(userid);
	}

	/**
	 * 重置密码
	 */
	@Override
	public void initPwd(Map<String, String> map) {
		logger.info("会员重置密码,{}",map.get("userid"));
		FsmUserEntity user = registerReviewMapper.queryByUserId(map.get("userid"));
		FsmUserEntity imuser = new FsmUserEntity();
		imuser.setUserid(map.get("userid"));
		imuser.setUpdatetime(DateUtil.getDateTimestamp());
		String pwd_y = IDUtils.getTimeRandon();
		String pwd = enCoder.getPasswordEncoder().encode(enCoder.proccessPasswd(map.get("userid"), pwd_y));
		imuser.setPasswd(pwd);
		imuser.setPwderrnum("0");
		imuser.setStatus(ContextCode.USER_STATUS_OK.value());
		registerReviewMapper.updateInfo(imuser);
		String email = user.getEmail();
		try {
			emailService.sendmail(email, "场景化金融营销平台", "欢迎使用坚石营销平台，您账户密码为:" + pwd_y);
		} catch (EmailException e) {
			logger.error("发送邮件错误：{}", e);
		}
	}
}
