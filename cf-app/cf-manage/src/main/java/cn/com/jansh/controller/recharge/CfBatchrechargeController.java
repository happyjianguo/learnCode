package cn.com.jansh.controller.recharge;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.core.annotation.ExceptionHandle;
import cn.com.jansh.core.annotation.OperationResult;
import cn.com.jansh.core.annotation.SecurityRequest;
import cn.com.jansh.core.constant.Operation;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.util.IDUtils;
import cn.com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.core.web.util.AppUtil;
import cn.com.jansh.entity.wsfdn.CfAccessclientEntity;
import cn.com.jansh.entity.wsfdn.CfBatchinfoEntity;
import cn.com.jansh.entity.wsfdn.CfBatchrechargeEntity;
import cn.com.jansh.model.BatchinfoModel;
import cn.com.jansh.service.audit.impl.AuditBatchrechargeServiceImpl;
import cn.com.jansh.service.wsfdn.CfAccessclientService;
import cn.com.jansh.service.wsfdn.CfAuditService;
import cn.com.jansh.service.wsfdn.CfBatchinfoService;
import cn.com.jansh.service.wsfdn.CfBatchrechargeService;
import cn.com.jansh.service.wsfdn.CfCustomerService;

/**
 * 充值管理
 * @author duanmuyn
 *
 */
@Controller
@RequestMapping(value="/batchrecharge")
public class CfBatchrechargeController {
	
	private static final Logger logger = LogManager.getLogger(CfBatchrechargeController.class);
	
	@Autowired
	private CfCustomerService cfCustomerService;
	
	@Autowired
	private CfBatchinfoService cfBatchinfoService;
	
	@Autowired
	private CfBatchrechargeService cfBatchrechargeService;
	
	@Autowired
	private CfAccessclientService cfAccessclientService;
	
	@Autowired
	private AuditBatchrechargeServiceImpl auditBatchrechargeServiceImpl;
	
	@Autowired
	private CfAuditService cfAuditService;
	/**
	 * 充值页面初始化
	 * @return
	 */
	@RequestMapping(value="/init")
	public String init(BatchinfoModel batchinfoModel,Model model){
		logger.info("批量充值初始化");
		/*初始化客户*/
		batchinfoModel.setCfCustomerList(cfCustomerService.queryCustomer());
		return"/recharge/batchrecharge/batchrecharge";
	}
	
	/**
	 * 上传数据
	 * @param joinmodel
	 * @return
	 * @throws AppException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/upload")
	@ExceptionHandle("/batchrecharge/init")
	@OperationResult(value=Operation.CREATE)
	@SecurityRequest(repeatRequest=true)
	public String upload(BatchinfoModel batchinfoModel,@RequestParam MultipartFile myfile) throws JsonProcessingException, AppException {
		logger.info("myfile.getSize()：" + myfile.getSize());
		if(CollectionUtils.isNotEmpty(cfBatchinfoService.queryBatchinfoByBatchname(batchinfoModel.getBatchname())))
		{
		 throw new AppException(AppErrorCode.E290001);	      
	    }else{
		/*初始化批次编号*/
		batchinfoModel.setBatchid(IDUtils.getTimeRandon());
		cfBatchrechargeService.uploadExcel(myfile,modelToEntity(batchinfoModel));
	    }
		return review(batchinfoModel);
	}
	
	/**
	 * Model转Entity
	 * @param batchinfoModel
	 * @return
	 */
	private CfBatchinfoEntity modelToEntity(BatchinfoModel batchinfoModel){
		CfBatchinfoEntity cfBatchinfoEntity = new CfBatchinfoEntity();
		cfBatchinfoEntity.setBatchid(batchinfoModel.getBatchid());
		cfBatchinfoEntity.setBatchname(batchinfoModel.getBatchname());
		cfBatchinfoEntity.setApno(batchinfoModel.getApno());
		cfBatchinfoEntity.setStatus(AppCommonsCode.RECHARGE_20.value());
		cfBatchinfoEntity.setUserid(AppUtil.getUserDetail().getUserid());
		return cfBatchinfoEntity;
	}

	/**
	 * 充值信息初始化
	 * @return
	 */
	@RequestMapping(value="/reviewinit")
	@ExceptionHandle("/batchrecharge/init")
	@OperationResult(value=Operation.RETRIEVE)
	public String review(BatchinfoModel batchinfoModel) throws  JsonProcessingException, AppException{
		logger.info("充值信息初始化");
		batchinfoModel.setCfBatchinfoList(cfBatchinfoService.queryCfBatchinfo(AppCommonsCode.RECHARGE_20.value()));
		return"/recharge/batchrecharge/querybatchrecharge";
	}
	/**
	 * 查询批量充值信息
	 * @param batchinfoModel
	 * @return
	 */
	@RequestMapping(value="/ajax/batchrecharge")
	@ResponseBody
	public ViewObject ajaxbatchrecharge(String batchid,String status){
		logger.info("查询批量充值信息{}",batchid);
		CfBatchrechargeEntity cfBatchrechargeEntity = new CfBatchrechargeEntity();
		cfBatchrechargeEntity.setBatchid(batchid);
//		cfBatchrechargeEntity.setStatus(status);
		return new ViewObject(cfBatchrechargeService.queryBatchrecharge(cfBatchrechargeEntity));
	}
	
	/**
	 * 确认上传
	 * @throws JsonProcessingException 
	 * @throws AppException 
	 */
	@RequestMapping(value="/uploadOk")
	@ExceptionHandle("/batchrecharge/init")
	@SecurityRequest(repeatRequest=true)
	public String uploadOk(BatchinfoModel batchinfoModel,Model model) throws JsonProcessingException, AppException{
		batchinfoModel =entityToModel(cfBatchrechargeService.queryBatchinfo(batchinfoModel.getBatchid()));
		//授权
		cfAuditService.saveAudit("批量充值管理", Operation.CREATE, batchinfoModel.getBatchid(), batchinfoModel, auditBatchrechargeServiceImpl);
		model.addAttribute(batchinfoModel);
		return init(batchinfoModel,model);
	}
	private CfBatchinfoEntity modelToEntity2(BatchinfoModel batchinfoModel){
		CfBatchinfoEntity cfBatchinfoEntity = new CfBatchinfoEntity();
		cfBatchinfoEntity.setBatchid(batchinfoModel.getBatchid());
		cfBatchinfoEntity.setStatus(AppCommonsCode.RECHARGE_21.value());
		return cfBatchinfoEntity;
	}
	
	private BatchinfoModel entityToModel(CfBatchinfoEntity cfBatchinfoEntity){
		BatchinfoModel batchinfoModel = new BatchinfoModel();
		batchinfoModel.setApno(cfBatchinfoEntity.getApno());
		batchinfoModel.setBatchid(cfBatchinfoEntity.getBatchid());
		batchinfoModel.setBatchname(cfBatchinfoEntity.getBatchname());
		batchinfoModel.setStatus(cfBatchinfoEntity.getStatus());
		return batchinfoModel;
	}
	/**
	 * 取消上传
	 * @param batchinfoModel
	 * @return
	 */
	@RequestMapping(value="/uploadRollBack")
	@ExceptionHandle("/batchrecharge/init")
	@SecurityRequest(repeatRequest=true)
	public String uploadRollBack(BatchinfoModel batchinfoModel,Model model){
		cfBatchrechargeService.rollback(modelToEntity2(batchinfoModel));
		model.addAttribute(batchinfoModel);
		return init(batchinfoModel,model);
	}
	
	/**
	 * 根据客户查询接入者
	 * @return
	 */
	@RequestMapping(value="/ajax/initClient")
	@ResponseBody
	public ViewObject initClient(String cid){
		logger.info("接入者初始化数据{}",cid);
		List<CfAccessclientEntity> cfAccessclientList = cfAccessclientService.queryAccessclientByCustormerId(cid);

		List<CfAccessclientEntity> cfAccessclientlist = new ArrayList<CfAccessclientEntity>();
		for(CfAccessclientEntity cf : cfAccessclientList){
			if(cf.getStatus().equals("gb")){
				continue;
			}
				CfAccessclientEntity cfAccessclient1 = cf;
				cfAccessclientlist.add(cfAccessclient1);
		}
		return new ViewObject(cfAccessclientlist);		
	} 
	
}
