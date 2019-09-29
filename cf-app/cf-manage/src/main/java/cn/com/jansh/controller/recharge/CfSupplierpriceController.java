package cn.com.jansh.controller.recharge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.core.annotation.ExceptionHandle;
import cn.com.jansh.core.annotation.OperationResult;
import cn.com.jansh.core.annotation.SecurityRequest;
import cn.com.jansh.core.constant.Operation;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.IDUtils;
import cn.com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.entity.wsfdn.CfSupplierpriceEntity;
import cn.com.jansh.model.SupplierpriceModel;
import cn.com.jansh.service.audit.impl.AuditSupplierpriceServiceImpl;
import cn.com.jansh.service.wsfdn.CfAuditService;
import cn.com.jansh.service.wsfdn.CfProvinceService;
import cn.com.jansh.service.wsfdn.CfSupplierService;
import cn.com.jansh.service.wsfdn.CfSupplierpriceService;

/**
 * 供应商报价管理
 * @author duanmuyn
 *
 */
@Controller
@RequestMapping(value="/supplierprice")
public class CfSupplierpriceController {

	private static final Logger logger = LogManager.getLogger(CfSupplierpriceController.class);
	
	@Autowired
	private CfSupplierpriceService cfSupplierpriceService;
	
	@Autowired
	private CfSupplierService cfSupplierService;
	
	@Autowired
	private CfProvinceService cfProvinceService;
	
	@Autowired
	private CfAuditService cfAuditService;
	
	@Autowired
	private AuditSupplierpriceServiceImpl auditSupplierpriceServiceImpl;
	/**
	 * 跳转初始化页面
	 * @return
	 */
	@RequestMapping(value="/init")
	public String init(SupplierpriceModel supplierpriceModel){
		logger.info("供应商报价列表初始化");
		/*初始化供应商*/
		supplierpriceModel.setSupplierList(cfSupplierService.querySupplier());
		/*初始化省份*/
		supplierpriceModel.setProvinceList(cfProvinceService.query());
		return"/recharge/supplierprice/spmanage";
	}
	
	/**
	 * 初始化供应商报价列表（查询）
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "ajax/supplierprice")
	@ResponseBody
	private ViewObject findByMessage(CfSupplierpriceEntity cfSupplierpriceEntity) {
		return new ViewObject(cfSupplierpriceService.query(cfSupplierpriceEntity));
	}
	
	/**
	 * 新增页面初始化
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping(value = "/addinit")
	@ExceptionHandle("/supplierprice/init")
	public String addinit(SupplierpriceModel supplierpriceModel) {
		logger.info("新增供应商报价初始化");
		/*初始化起止日期*/
		supplierpriceModel.setBegintime(DateUtil.getDateDay());
		supplierpriceModel.setEndtime(DateUtil.getLastDay());
		/*初始化供应商*/
		supplierpriceModel.setSupplierList(cfSupplierService.querySupplier());
		/*初始化省份*/
		supplierpriceModel.setProvinceList(cfProvinceService.query());
		return "/recharge/supplierprice/spmanageadd";
	}

	/**
	 * 增加数据
	 * @return
	 * @throws AppException 
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/add")
	@ExceptionHandle("/supplierprice/addinit")
	@OperationResult(value=Operation.CREATE)
	@SecurityRequest(repeatRequest=true)
	public String useradd(SupplierpriceModel supplierpriceModel) throws  JsonProcessingException, AppException {
		logger.info("添加供应商报价:{}", supplierpriceModel);
		supplierpriceModel.setSize(supplierpriceModel.getSize()+supplierpriceModel.getCompany());
		supplierpriceModel.setId(IDUtils.getTimeRandon());
		supplierpriceModel.setBegintime(DateUtil.getDateTime(DateUtil.parseDate(supplierpriceModel.getBegintime())));
		supplierpriceModel.setEndtime(DateUtil.getDateTime(DateUtil.addOSecond(DateUtil.addDate(DateUtil.parseDate(supplierpriceModel.getEndtime()),1),-1)));
		//校验是否存在
		if(cfSupplierpriceService.queryByName(supplierpriceModel.getPname())!=null){
			throw new AppException(AppErrorCode.E250001);
		}
		//授权
		cfAuditService.saveAudit("供应商价格管理", Operation.CREATE, supplierpriceModel.getId(), supplierpriceModel, auditSupplierpriceServiceImpl);
		//cfSupplierpriceService.addCFSupplierprice(modelToEntity(supplierpriceModel,"add"));
		return init(supplierpriceModel);
	}

	/**
	 * 修改页面初始化
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping(value = "/editinit")
	@ExceptionHandle("/supplierprice/init")
	public String editinit(SupplierpriceModel supplierpriceModel,Model model) {
		logger.info("修改供应商报价初始化" + supplierpriceModel.getId());
		supplierpriceModel = entityToModel(cfSupplierpriceService.querySupplierpriceByid(supplierpriceModel.getId()));
		supplierpriceModel.setBegintime(DateUtil.getDate(DateUtil.parseDateTime(supplierpriceModel.getBegintime())));
		supplierpriceModel.setEndtime(DateUtil.getDate(DateUtil.parseDateTime(supplierpriceModel.getEndtime())));
		/*初始化供应商*/
		supplierpriceModel.setSupplierList(cfSupplierService.querySupplier());
		/*初始化省份*/
		supplierpriceModel.setProvinceList(cfProvinceService.query());
		model.addAttribute(supplierpriceModel);
		return "/recharge/supplierprice/spmanageedit";
	}
	
	/**
	 * Entity转Model(修改)
	 * @param supplierModel
	 * @return
	 */
	private SupplierpriceModel entityToModel(CfSupplierpriceEntity cfSupplierpriceEntity){
		logger.info("Entity转Model(修改):{}", cfSupplierpriceEntity.getId());
		SupplierpriceModel supplierpriceModel = new SupplierpriceModel();
		supplierpriceModel.setId(cfSupplierpriceEntity.getId());
		supplierpriceModel.setPname(cfSupplierpriceEntity.getPname());
		supplierpriceModel.setIspno(cfSupplierpriceEntity.getIspno());
		supplierpriceModel.setIpstype(cfSupplierpriceEntity.getIpstype());
		supplierpriceModel.setPno(cfSupplierpriceEntity.getPno());
		supplierpriceModel.setSize(cfSupplierpriceEntity.getSize());
		supplierpriceModel.setSid(cfSupplierpriceEntity.getSid());
		supplierpriceModel.setPrice(cfSupplierpriceEntity.getPrice());
		supplierpriceModel.setBegintime(cfSupplierpriceEntity.getBegintime());
		supplierpriceModel.setEndtime(cfSupplierpriceEntity.getEndtime());
		supplierpriceModel.setStatus(cfSupplierpriceEntity.getStatus());
		supplierpriceModel.setCpordernos(cfSupplierpriceEntity.getCpordernos());
		return supplierpriceModel;
	}
	
	/**
	 * 修改数据
	 * @param imuser
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/editdata")
	@ExceptionHandle("/supplierprice/editinit")
	@OperationResult(value=Operation.UPDATE)
	@SecurityRequest(repeatRequest=true)
	public String useredit(SupplierpriceModel supplierpriceModel) throws AppException, JsonProcessingException {
		logger.info("修改供应商报价数据:{}", supplierpriceModel);
		supplierpriceModel.setBegintime(DateUtil.getDateTime(DateUtil.parseDate(supplierpriceModel.getBegintime())));
		supplierpriceModel.setEndtime(DateUtil.getDateTime(DateUtil.addOSecond(DateUtil.addDate(DateUtil.parseDate(supplierpriceModel.getEndtime()),1),-1)));
		//授权
		cfAuditService.saveAudit("供应商价格管理", Operation.UPDATE, supplierpriceModel.getId(), supplierpriceModel, auditSupplierpriceServiceImpl);
		//cfSupplierpriceService.updateSupplierprice(modelToEntity(supplierpriceModel,"edit"));
		return init(supplierpriceModel);
	}
	
	/**
	 * 删除页面初始化
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping(value = "/delinit")
	@ExceptionHandle("/supplierprice/init")
	public String delinit(SupplierpriceModel supplierpriceModel,Model model) {
		logger.info("删除供应商报价初始化" + supplierpriceModel.getId());
		supplierpriceModel = entityToModel(cfSupplierpriceService.querySupplierpriceByid(supplierpriceModel.getId()));
		supplierpriceModel.setBegintime(DateUtil.getDate(DateUtil.parseDateTime(supplierpriceModel.getBegintime())));
		supplierpriceModel.setEndtime(DateUtil.getDate(DateUtil.parseDateTime(supplierpriceModel.getEndtime())));
		/*初始化供应商*/
		supplierpriceModel.setSupplierList(cfSupplierService.querySupplier());
		/*初始化省份*/
		supplierpriceModel.setProvinceList(cfProvinceService.query());
		model.addAttribute(supplierpriceModel);		
		return "/recharge/supplierprice/spmanageedel";
	}
	
	/**
	 * 删除数据(改状态)
	 * @return
	 * @throws JsonProcessingException 
	 * @throws Exception
	 */
	@RequestMapping(value = "/deldata")
	@ExceptionHandle("/supplierprice/delinit")
	@OperationResult(value=Operation.DELETE)
	@SecurityRequest(repeatRequest=true)
	public String deldata(SupplierpriceModel supplierpriceModel) throws AppException, JsonProcessingException {
		logger.info("删除供应商报价" + supplierpriceModel.getId());
		supplierpriceModel = entityToModel(cfSupplierpriceService.querySupplierpriceByid(supplierpriceModel.getId()));
		//授权
		cfAuditService.saveAudit("供应商价格管理", Operation.DELETE, supplierpriceModel.getId(), supplierpriceModel, auditSupplierpriceServiceImpl);
		//cfSupplierpriceService.delSupplierprice(cfSupplierpriceEntity);
		return init(supplierpriceModel);
	}
}
