package cn.com.jansh.controller.recharge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.core.annotation.ExceptionHandle;
import cn.com.jansh.core.annotation.OperationResult;
import cn.com.jansh.core.annotation.SecurityRequest;
import cn.com.jansh.core.constant.Operation;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.util.IDUtils;
import cn.com.jansh.entity.wsfdn.CfSupplierEntity;
import cn.com.jansh.model.SupplierModel;
import cn.com.jansh.service.wsfdn.CfSupplierService;

/**
 * 
 * 供应商管理
 * @author duanmuyn
 */
@Controller
@RequestMapping(value="/supplier")
public class CfSupplierController {

	private static final Logger logger = LogManager.getLogger(CfSupplierController.class);
	
	@Autowired
	private CfSupplierService cfSupplierService;
	
	/**
	 * 跳转初始化页面
	 * @return
	 */
	@RequestMapping(value="/init")
	public String init(SupplierModel supplierModel){
		logger.info("供应商列表初始化");
		supplierModel.setSupplierList(cfSupplierService.querySupplier());
		return"/recharge/supplier/smanage";
	}
	/**
	 * 新增页面初始化
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping(value = "/addinit")
	@ExceptionHandle("/supplier/init")
	public String addinit(SupplierModel supplierModel) {
		logger.info("新增供应商初始化");
		return "/recharge/supplier/smanageadd";
	}

	/**
	 * 增加数据
	 * @return
	 * @throws AppException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/add")
	@ExceptionHandle("/supplier/addinit")
	@OperationResult(value=Operation.CREATE)
	@SecurityRequest(repeatRequest=true)
	public String useradd(SupplierModel supplierModel) throws AppException{
		logger.info("添加供应商:{}", supplierModel);
		cfSupplierService.insertSupplier(modelToEntity(supplierModel,AppCommonsCode.CREATE.value()));
		return init(supplierModel);
	}

	/**
	 * Model转Entity(新增)
	 * @param supplierModel
	 * @param type("add"时id自动生成，"edit"时用原有id)
	 * @return
	 */
	private CfSupplierEntity modelToEntity(SupplierModel supplierModel,String type){
		CfSupplierEntity cfSupplierEntity = new CfSupplierEntity();
		if(type.equals(AppCommonsCode.CREATE.value())){
			cfSupplierEntity.setId(IDUtils.getTimeRandon());
		}else if(type.equals(AppCommonsCode.UPDATE.value())){
			cfSupplierEntity.setId(supplierModel.getId());
		}
		cfSupplierEntity.setSname(supplierModel.getSname());
		cfSupplierEntity.setMname(supplierModel.getMname());
		cfSupplierEntity.setPhone(supplierModel.getPhone());
		cfSupplierEntity.setEmail(supplierModel.getEmail());
		return cfSupplierEntity;
	}

	/**
	 * 修改页面初始化
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping(value = "/editinit")
	@ExceptionHandle("/supplier/init")
	public String editinit(SupplierModel supplierModel,Model model) {
		logger.info("修改供应商初始化" + supplierModel.getId());
		model.addAttribute(entityToModel(cfSupplierService.querySupplierByid(supplierModel.getId())));
		return "/recharge/supplier/smanageedit";
	}
	
	/**
	 * Entity转Model(修改)
	 * @param supplierModel
	 * @return
	 */
	private SupplierModel entityToModel(CfSupplierEntity cfSupplierEntity){
		logger.info("Entity转Model(修改):{}", cfSupplierEntity.getId());
		SupplierModel supplierModel = new SupplierModel();
		supplierModel.setMname(cfSupplierEntity.getMname());
		supplierModel.setSname(cfSupplierEntity.getSname());
		supplierModel.setPhone(cfSupplierEntity.getPhone());
		supplierModel.setEmail(cfSupplierEntity.getEmail());
		supplierModel.setId(cfSupplierEntity.getId());
		return supplierModel;
	}
	
	/**
	 * 修改数据
	 * @param imuser
	 * @return
	 */
	@RequestMapping(value = "/editdata")
	@ExceptionHandle("/supplier/editinit")
	@OperationResult(value=Operation.UPDATE)
	@SecurityRequest(repeatRequest=true)
	public String useredit(SupplierModel supplierModel){
		logger.info("修改供应商数据:{}", supplierModel);
		cfSupplierService.updateSupplier(modelToEntity(supplierModel,AppCommonsCode.UPDATE.value()));
		return init(supplierModel);
	}
	
	/**
	 * 删除页面初始化
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping(value = "/delinit")
	@ExceptionHandle("/supplier/init")
	public String delinit(SupplierModel supplierModel,Model model) {
		logger.info("删除供应商" + supplierModel.getId());
		model.addAttribute(entityToModel(cfSupplierService.querySupplierByid(supplierModel.getId())));		
		return "/recharge/supplier/smanageedel";
	}
	
	/**
	 * 删除数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deldata")
	@ExceptionHandle("/supplier/delinit")
	@OperationResult(value=Operation.DELETE)
	@SecurityRequest(repeatRequest=true)
	public String deldata(SupplierModel supplierModel) throws AppException {
		logger.info("删除供应商" + supplierModel.getId());
		cfSupplierService.deleteSupplier(supplierModel.getId());
		return init(supplierModel);
	}
	
	
}
