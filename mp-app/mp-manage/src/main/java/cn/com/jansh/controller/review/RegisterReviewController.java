/**
 * RegisterReviewController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月18日
 */
package cn.com.jansh.controller.review;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.OperationResult;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.comm.util.DateUtil;
import com.jansh.core.web.util.AppUtil;
import cn.com.jansh.entity.review.FsmUserEntity;
import cn.com.jansh.model.review.RegisterReviewModel;
import cn.com.jansh.service.review.RegisterReviewService;

/**
 * 用户注册审核
 * @author xieliangliang
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/registerreview")
public class RegisterReviewController {
	
	private static final Logger logger = LogManager.getLogger(RegisterReviewController.class);

	@Autowired
	private RegisterReviewService registerReviewService;
	
	/**
	 * 初始化用户注册需要审核的数据
	 */
	@RequestMapping(value = "/init")
	public String init(RegisterReviewModel registerReviewModel) {
		logger.info("获取注册用户未审核列表");
		List<FsmUserEntity> providerauthList= registerReviewService.query(modelToEntity(registerReviewModel));
		registerReviewModel.setFsmUserList(providerauthList);
		return "/review/registerreview";
	}
	
	/**
	 * 审核初始化页面
	 */
	@RequestMapping(value = "/reviewinit")
	@ExceptionHandle("/registerreview/init")
	public String reviewinit(RegisterReviewModel registerReviewModel,Model model) {
		logger.info("审核页面初始化");
		registerReviewModel = entityToModel(registerReviewService.queryByUserId(registerReviewModel.getUserid()));
		model.addAttribute(registerReviewModel);
		return "/review/reviewop";
	}
	
	
	/**
	 * 审核注册信息
	 */
	@RequestMapping(value = "/reviewsb")
	@ExceptionHandle("/registerreview/init")
	@OperationResult(value=Operation.UPDATE)
	@OperationLog(key = "button.reviewed")
	@SecurityRequest(repeatRequest=true)
	public String reviewsb(RegisterReviewModel registerReviewModel) {
		logger.info("审核注册用户信息：{}",registerReviewModel);
		registerReviewModel.setReviewper(AppUtil.getUserDetail().getUsername()); //审核人
		registerReviewModel.setUpdatetime(DateUtil.getDateTimestamp()); //审核日期

		registerReviewService.updateStatus(modelToEntity(registerReviewModel));
		
		registerReviewModel.setUsername("");
		registerReviewModel.setStatus("");
		return init(registerReviewModel);
	}
	
	/**
	 * Entity转model
	 */
	public RegisterReviewModel entityToModel(FsmUserEntity fsmUserEntity) {
		RegisterReviewModel registerReviewModel = new RegisterReviewModel();
		registerReviewModel.setUserid(fsmUserEntity.getUserid());
		registerReviewModel.setUsername(fsmUserEntity.getUsername());
		registerReviewModel.setPhoneno(fsmUserEntity.getPhoneno());
		registerReviewModel.setEmail(fsmUserEntity.getEmail());
		registerReviewModel.setReviewdes(fsmUserEntity.getReviewdes());
		return registerReviewModel;
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
		return fsmUserEntity;
	}
}
