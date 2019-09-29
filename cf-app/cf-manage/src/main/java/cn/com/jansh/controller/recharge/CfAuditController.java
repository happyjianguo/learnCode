package cn.com.jansh.controller.recharge;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.core.annotation.OperationResult;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.core.web.util.AppUtil;
import cn.com.jansh.entity.wsfdn.CfAccessclientEntity;
import cn.com.jansh.entity.wsfdn.CfAuditEntity;
import cn.com.jansh.entity.wsfdn.CfBatchrechargeEntity;
import cn.com.jansh.entity.wsfdn.CfCustomerEntity;
import cn.com.jansh.model.AccesspriceModel;
import cn.com.jansh.model.AuditModel;
import cn.com.jansh.model.BatchinfoModel;
import cn.com.jansh.model.SupplierpriceModel;
import cn.com.jansh.service.wsfdn.CfAccessclientService;
import cn.com.jansh.service.wsfdn.CfAuditService;
import cn.com.jansh.service.wsfdn.CfBatchrechargeService;
import cn.com.jansh.service.wsfdn.CfCustomerService;
import cn.com.jansh.service.wsfdn.CfProvinceService;
import cn.com.jansh.service.wsfdn.CfSupplierService;

/**
 * 审批管理
 * @author duanmuyn
 */
@Controller
@RequestMapping(value="/audit")
public class CfAuditController {

	private static final Logger logger = LogManager.getLogger(CfAuditController.class);
	
	@Autowired
	private CfAuditService cfAuditService; 	
	
	@Autowired
	private CfAccessclientService cfAccessclientService;
	
	@Autowired
	private CfProvinceService cfProvinceService;
	
	@Autowired
	private CfSupplierService cfSupplierService;
	
	@Autowired
	private CfCustomerService cfCustomerService;
	
	@Autowired
	private CfBatchrechargeService cfBatchrechargeService;
	/**
	 * 跳转初始化页面
	 * @return
	 */
	@RequestMapping(value="/init")
	public String init(AuditModel auditModel){
		logger.info("审批管理初始化");
		auditModel.setStatus(AppCommonsCode.RECHARGE_30.value());
		return "/recharge/audit/auditmanage";
	}
	
	/**
	 * 初始待审批列表列表（查询）
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "ajax/auditdata")
	@ResponseBody
	public ViewObject findByMessage() {
		return new ViewObject(cfAuditService.initshow(AppCommonsCode.AUDIT_WAIT.value(),AppUtil.getUserDetail().getUserid()));
	}
	
	/**
	 * 审批页面初始化
	 * @param auditModel
	 * @return
	 */
	@RequestMapping(value="/audit")
	public String audit(AuditModel auditModel,Model model){
		logger.info("待审批数据{}",auditModel.getId());
		
		Map<String,Object> map = cfAuditService.audit(auditModel.getId());
		CfAuditEntity audit = (CfAuditEntity)map.get("audit");
		model.addAttribute("auditid", auditModel.getId());
		model.addAttribute("opinion", audit.getOpinion());
		model.addAttribute("auditstatus", audit.getStatus());
		model.addAttribute("draftman",audit.getDraftman());
		//接入者报价
		if(map.get("data") instanceof AccesspriceModel){
			AccesspriceModel accesspriceModel = new AccesspriceModel();
			accesspriceModel = (AccesspriceModel)map.get("data");
			/*初始化接入者*/
			accesspriceModel.setPriceList(cfAccessclientService.queryAccessclient());
			/*初始化省份*/
			accesspriceModel.setProvinceList(cfProvinceService.query());
			model.addAttribute(accesspriceModel);
			return "/recharge/audit/accessprice/apmanage";
		//供应商报价
		}else if(map.get("data") instanceof SupplierpriceModel){
			SupplierpriceModel supplierpriceModel = new SupplierpriceModel();
			supplierpriceModel = (SupplierpriceModel)map.get("data");
			supplierpriceModel.setBegintime(DateUtil.getDate(DateUtil.parseDate(supplierpriceModel.getBegintime())));
			supplierpriceModel.setEndtime(DateUtil.getDate(DateUtil.parseDate(supplierpriceModel.getEndtime())));
			logger.info("修改供应商报价初始化" + supplierpriceModel.getId());
			//supplierpriceModel = entityToModel(cfSupplierpriceService.querySupplierpriceByid(supplierpriceModel.getId()));
			/*初始化供应商*/
			supplierpriceModel.setSupplierList(cfSupplierService.querySupplier());
			/*初始化省份*/
			supplierpriceModel.setProvinceList(cfProvinceService.query());
			model.addAttribute(supplierpriceModel);
			return "/recharge/audit/supplierprice/spmanage";
		//批量充值
		}else if(map.get("data") instanceof BatchinfoModel){
			BatchinfoModel batchinfoModel = new BatchinfoModel();
			batchinfoModel = (BatchinfoModel)map.get("data");
			CfAccessclientEntity cfAccessclientEntity = cfAccessclientService.queryAccessclientById(batchinfoModel.getApno());
			CfCustomerEntity cfCustomerEntity = cfCustomerService.selectCustomerById((cfAccessclientEntity.getCid()));
			/*初始化客户*/
			batchinfoModel.setCfCustomerList(cfCustomerService.queryCustomer());
			model.addAttribute(batchinfoModel);
			model.addAttribute("cname", cfCustomerEntity.getCname());
			model.addAttribute("acname", cfAccessclientEntity.getAcname());
			return "/recharge/audit/batchrecharge/batchrechargemanage";
		}
		auditModel.setStatus(AppCommonsCode.RECHARGE_30.value());
		return"/recharge/audit/auditmanage";
	}
	
	/**
	 * 审批不同意
	 * @param request
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping(value="/no")
	@OperationResult(msgCode="270013")
	public String auditno(HttpServletRequest request) throws AppException{
		String id = request.getParameter("auditid");
		logger.info("审批不同意{}",id);
		String opinion = request.getParameter("opinion");
		CfAuditEntity cfAuditEntity = new CfAuditEntity();
		cfAuditEntity.setId(id);
		cfAuditEntity.setAuditer(AppUtil.getUserDetail().getUserid());
		cfAuditEntity.setStatus(AppCommonsCode.AUDIT_ERROR.value());
		cfAuditEntity.setUpdatetime(DateUtil.getDateTime());
		cfAuditEntity.setOpinion(opinion);
		cfAuditService.audit(cfAuditEntity);
		return "/recharge/audit/auditmanage";
	}
	
	/**
	 * 审批同意
	 * @param request
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping(value="/yes")
	@OperationResult(msgCode="270012")
	public String audityes(HttpServletRequest request) throws AppException{
		String id = request.getParameter("auditid");
		logger.info("审批同意{}",id);
		String opinion = request.getParameter("opinion");
		CfAuditEntity cfAuditEntity = new CfAuditEntity();
		cfAuditEntity.setId(id);
		cfAuditEntity.setAuditer(AppUtil.getUserDetail().getUserid());
		cfAuditEntity.setStatus(AppCommonsCode.AUDIT_OK.value());
		cfAuditEntity.setUpdatetime(DateUtil.getDateTime());
		cfAuditEntity.setOpinion(opinion);
		cfAuditService.audit(cfAuditEntity);
		return "/recharge/audit/auditmanage";
	}
	
	/**
	 * Model转Entity(新增)
	 * @param SupplierpriceModel
	 * @param type("add"时id自动生成，"edit"时用原有id)
	 * @return
	 */
	/*private CfAuditEntity modelToEntity(AuditModel auditModel){
		CfAuditEntity CfAuditEntity = new CfAuditEntity();
		CfAuditEntity.setId(auditModel.getId());
		CfAuditEntity.setAuname(auditModel.getAuname());
		CfAuditEntity.setAutype(auditModel.getAutype());
		CfAuditEntity.setDetailkey(auditModel.getDetailkey());
		CfAuditEntity.setCreatetime(auditModel.getCreatetime());
		CfAuditEntity.setUpdatetime(auditModel.getUpdatetime());
		CfAuditEntity.setStatus(auditModel.getStatus());
		CfAuditEntity.setAuditer(auditModel.getAuditer());
		CfAuditEntity.setDraftman(auditModel.getDraftman());
		CfAuditEntity.setOpinion(auditModel.getOpinion());
		CfAuditEntity.setServicename(auditModel.getServicename());	
		return CfAuditEntity;
	}*/
	
	
	/**
	 * 根据客户查询接入者
	 * @return
	 */
	@RequestMapping(value="/ajax/initClient")
	@ResponseBody
	public ViewObject initClient(String cid){
		logger.info("接入者初始化数据{}",cid);
		return new ViewObject(cfAccessclientService.queryAccessclientByCustormerId(cid));
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
		cfBatchrechargeEntity.setStatus(status);
		return new ViewObject(cfBatchrechargeService.queryBatchrecharge(cfBatchrechargeEntity));
	}
}
