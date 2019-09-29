/**
 * UserManageController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月25日
 */
package cn.com.jansh.controller.usermanage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jansh.comm.util.DateUtil;
import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.OperationResult;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.core.exception.AppException;

import cn.com.jansh.entity.review.FsmUserEntity;
import cn.com.jansh.entity.system.CloudplatformorginationEntity;
import cn.com.jansh.model.review.RegisterReviewModel;
import cn.com.jansh.service.review.RegisterReviewService;
import cn.com.jansh.service.system.CloudplatformorginationService;

/**
 * 会员管理控制类
 * @author xieliangliang
 * @version 1.0
 */
@Controller
@RequestMapping(value="/membermanage")
public class UserManageController {
	
	private static final Logger logger = LogManager.getLogger(UserManageController.class);

	@Autowired
	private RegisterReviewService registerReviewService;
	
	@Autowired
	private CloudplatformorginationService cloudpfgService; //机构接口
	
	/**
	 * 会员管理页面初始化界面
	 */
	@RequestMapping("init")
	public String init(RegisterReviewModel registerReviewModel){
		logger.info("会员管理页面初始化");
		
		List<CloudplatformorginationEntity> orgList = cloudpfgService.queryAllDate(); //获取机构的下拉列表值
		registerReviewModel.setCloudpforgList(orgList);
		List<FsmUserEntity> providerauthList= registerReviewService.query(modelToEntity(registerReviewModel));
		registerReviewModel.setFsmUserList(providerauthList);
		
		return "/usermanege/membermanage";
	}
	
	/**
	 * 新增会员页面初始化界面
	 */
	@RequestMapping("addinit")
	public String addinit(RegisterReviewModel registerReviewModel,Model model) {
		logger.info("新增会员初始化页面");
		List<CloudplatformorginationEntity> orgList = cloudpfgService.queryAllDate(); //获取机构的下拉列表值
		registerReviewModel.setCloudpforgList(orgList);
		model.addAttribute(registerReviewModel);
		return "/usermanege/membermanageadd";
	}
	
	/**
	 * 新增会员
	 */
	@RequestMapping(value = "/adduser")
	@ExceptionHandle("/membermanage/addinit")
	@OperationResult(value = Operation.CREATE)
	@OperationLog(value = Operation.CREATE)
	@SecurityRequest(repeatRequest = true)
	public String adddata(RegisterReviewModel registerReviewModel) throws AppException{
		logger.info("新增会员信息");
		registerReviewService.adddata(registerReviewModel);
		registerReviewModel.setUsername("");
		registerReviewModel.setStatus("");
		registerReviewModel.setOrgid("");
		return init(registerReviewModel);
	}
	
	
	/**
	 * 编辑会员
	 */
	@RequestMapping(value="/editinfo")
	public String editinfo(RegisterReviewModel registerReviewModel,Model model) {
		
		logger.info("修改用户信息页面初始化数据: {}",registerReviewModel.getUserid());
		registerReviewModel = entutyToEntity(registerReviewService.queryByUserId(registerReviewModel.getUserid()));
		model.addAttribute(registerReviewModel);
		
		//获取机构下拉列表
		List<CloudplatformorginationEntity> orgList = cloudpfgService.queryAllDate(); //获取机构的下拉列表值
		registerReviewModel.setCloudpforgList(orgList);
		
		return "/usermanege/membermanageedit";
	}
	
	/**
	 * 更新会员信息
	 */
	@RequestMapping(value = "/editdata")
	@OperationResult(value = Operation.UPDATE)
	@OperationLog(value = Operation.UPDATE)
	@ExceptionHandle("/membermanage/editinit")
	public String editdata(RegisterReviewModel registerReviewModel) throws AppException {
		logger.info("修改数据:{}", registerReviewModel);
		FsmUserEntity entity = new FsmUserEntity();
		entity.setUserid(registerReviewModel.getUserid());
		entity.setUsername(registerReviewModel.getUsername());
		entity.setCname(registerReviewModel.getCname());
		entity.setPhoneno(registerReviewModel.getPhoneno());
		entity.setEmail(registerReviewModel.getEmail());
		entity.setOrgid(registerReviewModel.getOrgid());
		entity.setUpdatetime(DateUtil.getDateTimestamp());
		entity.setStatus(registerReviewModel.getStatus());
		entity.setMf(registerReviewModel.getMf());
		//0说明不修改机构
		if("0".equals(registerReviewModel.getMflg())){
			registerReviewService.updateInfoNoOrgId(entity);
		}else if("1".equals(registerReviewModel.getMflg())) {
			//修改当前所属机构
			registerReviewService.updateInfo(entity);
		}
		registerReviewModel.setUsername("");
		registerReviewModel.setStatus("");
		registerReviewModel.setOrgid("");
		return init(registerReviewModel);
	}
	
	/**
	 * 删除会员初始化页面
	 */
	@RequestMapping(value="/delinfo")
	public String delinfo(RegisterReviewModel registerReviewModel,Model model) {
		
		logger.info("删除用户信息页面初始化ID: {}",registerReviewModel.getUserid());
		registerReviewModel = entutyToEntity(registerReviewService.queryByUserId(registerReviewModel.getUserid()));
		model.addAttribute(registerReviewModel);
		
		//获取机构下拉列表
		List<CloudplatformorginationEntity> orgList = cloudpfgService.queryAllDate(); //获取机构的下拉列表值
		registerReviewModel.setCloudpforgList(orgList);
		
		return "/usermanege/membermanagedel";
	}

	
	/**
	 * 删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deldata")
	@OperationResult(value = Operation.DELETE)
	@OperationLog(value = Operation.DELETE)
	public String deldata(RegisterReviewModel registerReviewModel) throws Exception {
		logger.info("删除用户" + registerReviewModel.getUserid());
		try {
			registerReviewService.delMemberInfo(registerReviewModel.getUserid());
		} catch (Exception e) {
			throw e;
		}
		registerReviewModel.setUsername("");
		registerReviewModel.setStatus("");
		return init(registerReviewModel);
	}
	
	/**
	 * 重置密码
	 */
	@RequestMapping(value = "/initPwd")
	@OperationResult(value = Operation.DEFULT)
	@OperationLog(value = Operation.DEFULT)
	public String initPwd(RegisterReviewModel registerReviewModel) {
		logger.info("密码重置{}", registerReviewModel.getUserid());
		try {
			if (StringUtils.isBlank(registerReviewModel.getUserid())) {
				/*
				 * ajaxObj.setMsg("密码重置异常"); ajaxObj.setResult(0); return
				 * ajaxObj;
				 */
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", registerReviewModel.getUserid());
			registerReviewService.initPwd(map);

		} catch (Exception e) {
			/*
			 * logger.error("密码重置异常{}", e); ajaxObj.setMsg("密码重置异常");
			 * ajaxObj.setResult(0); return ajaxObj;
			 */
		}
		registerReviewModel.setUsername("");
		registerReviewModel.setStatus("");
		return init(registerReviewModel);
	}
	
	
	/**
	 * entity转model
	 */
	private RegisterReviewModel entutyToEntity(FsmUserEntity entity){
		RegisterReviewModel reModel = new RegisterReviewModel();
		reModel.setUserid(entity.getUserid());
		reModel.setUsername(entity.getUsername());
		reModel.setCname(entity.getCname());
		reModel.setEmail(entity.getEmail());
		reModel.setPhoneno(entity.getPhoneno());
		reModel.setMf(entity.getMf());
		reModel.setOrgid(entity.getOrgid());
		reModel.setStatus(entity.getStatus());
		
		return reModel;
		
	}
	
	/**
	 * model转Entity
	 */
	private FsmUserEntity modelToEntity(RegisterReviewModel registerReviewModel){
		FsmUserEntity fsmUserEntity = new FsmUserEntity();
		fsmUserEntity.setUserid(registerReviewModel.getUserid());
		fsmUserEntity.setUsername(registerReviewModel.getUsername());
		fsmUserEntity.setStatus(registerReviewModel.getStatus());
		fsmUserEntity.setReviewper(registerReviewModel.getReviewper());
		fsmUserEntity.setUpdatetime(registerReviewModel.getUpdatetime());
		fsmUserEntity.setReviewdes(registerReviewModel.getReviewdes());
		fsmUserEntity.setOrgid(registerReviewModel.getOrgid());
		return fsmUserEntity;
	}
}
