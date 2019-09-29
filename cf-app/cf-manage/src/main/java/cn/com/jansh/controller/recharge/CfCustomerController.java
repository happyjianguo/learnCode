package cn.com.jansh.controller.recharge;

import java.util.List;

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
import cn.com.jansh.entity.wsfdn.CfCustomerEntity;
import cn.com.jansh.model.CustomerModel;
import cn.com.jansh.service.wsfdn.CfCustomerService;

/**
 * 系统管理-客户管理
 * 
 */
@Controller
@RequestMapping(value ="/customer")
public class CfCustomerController {

	private static final Logger logger = LogManager.getLogger(CfCustomerController.class);

	@Autowired
	private CfCustomerService cfCustomerService;

	/**
	 * 跳转初始化页面
	 * 
	 * @return
	 */
	@RequestMapping(value ="/init")
	public String init(CustomerModel customerModel) {
		logger.info("获取客户列表");
		/**查询条件客户下拉框 */
		List<CfCustomerEntity> customerListselect = cfCustomerService.queryCustomer();	
		customerModel.setCustomerlistselect(customerListselect);
	    /**获取客户列表 */
		List<CfCustomerEntity> customerList = cfCustomerService.queryCustomerBy(ModelToEntity(customerModel));			
		customerModel.setCustomerlist(customerList);
		return "recharge/customer/customer";
	}
	
	private CfCustomerEntity ModelToEntity(CustomerModel customerModel){
		CfCustomerEntity cfCustomerEntity = new CfCustomerEntity();
		cfCustomerEntity.setId(customerModel.getId());
		cfCustomerEntity.setMname(customerModel.getMname());
		cfCustomerEntity.setCname(customerModel.getCname());
		cfCustomerEntity.setEmail(customerModel.getEmail());
		cfCustomerEntity.setQid(customerModel.getQid());
		cfCustomerEntity.setQmname(customerModel.getQmname());
		return cfCustomerEntity;
	}
	/**
	 * 查询数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/customerselect")
	@ExceptionHandle("/customer/init")
	@OperationResult(value=Operation.RETRIEVE)
	public String customerselect(CustomerModel customerModel) throws Exception {
		logger.info("查询客户" + customerModel.getId());
		cfCustomerService.selectCustomerById((customerModel.getId()));
		return init(customerModel);
	}
	

	/**
	 * 新增页面初始化
	 * @param cfCustomerModel
	 * @return
	 */
	@RequestMapping(value = "/addinit")
	@ExceptionHandle("/customer/init")
	public String addinit(CustomerModel customerModel) {
		logger.info("获取客户列表");
		List<CfCustomerEntity> customerlist = cfCustomerService.queryCustomer();
		customerModel.setCustomerlist(customerlist);
		return "recharge/customer/customeradd";
	}

	/**
	 * 增加数据
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping(value = "/adddata")
	@ExceptionHandle("/customer/addinit")
	@OperationResult(value=Operation.CREATE)
	@SecurityRequest(repeatRequest=true)
	public String addcustomer(CustomerModel customerModel) throws AppException {
		logger.info("添加客户数据:{}", customerModel);
		cfCustomerService.insertCustomer(modelToEntity(customerModel,AppCommonsCode.CREATE.value()));
		return init(customerModel);
	}
	
	/**
	 * Model转Entity(新增)
	 * @param supplierModel
	 * @param type("add"时id自动生成，"edit"时用原有id)
	 * @return
	 */
	private CfCustomerEntity modelToEntity(CustomerModel customerModel,String type){
		CfCustomerEntity cfCustomerEntity = new CfCustomerEntity();
		if(type.equals(AppCommonsCode.CREATE.value())){
			cfCustomerEntity.setId(IDUtils.getTimeRandon());
		}else if(type.equals(AppCommonsCode.UPDATE.value())){
			cfCustomerEntity.setId(customerModel.getId());
		}
		cfCustomerEntity.setCname(customerModel.getCname());
		cfCustomerEntity.setMname(customerModel.getMname());
		cfCustomerEntity.setPhone(customerModel.getPhone());
		cfCustomerEntity.setEmail(customerModel.getEmail());
		return cfCustomerEntity;
	}


	/**
	 * 修改页面初始化
	 * @param userManageModel
	 * @return
	 */

	@RequestMapping(value = "/editinit")
	@ExceptionHandle("/customer/init")
	public String editinit(CustomerModel customerModel,Model model) {
		logger.info("修改客户初始化" + customerModel.getId());
		customerModel = entityToModel(cfCustomerService.selectCustomerById(customerModel.getId()));
		model.addAttribute(customerModel);
		return "recharge/customer/customeredit";
	}
	/**
	 * Entity转Model(修改)
	 * @param supplierModel
	 * @return
	 */
	private CustomerModel entityToModel(CfCustomerEntity cfCustomerEntity){
		logger.info("Entity转Model(修改):{}", cfCustomerEntity.getId());
		CustomerModel customerModel = new CustomerModel();
		customerModel.setCname(cfCustomerEntity.getCname());
		customerModel.setMname(cfCustomerEntity.getMname());
		customerModel.setPhone(cfCustomerEntity.getPhone());
		customerModel.setEmail(cfCustomerEntity.getEmail());
		customerModel.setId(cfCustomerEntity.getId());
		return customerModel;
	}
	/**
	 * 修改数据
	 * @param imuser
	 * @return
	 */
	@RequestMapping(value = "/editdata")
	@ExceptionHandle("/customer/editinit")
	@OperationResult(value=Operation.UPDATE)
	@SecurityRequest(repeatRequest=true)
	public String customeredit(CustomerModel customerModel) throws AppException {
		logger.info("修改客户数据:{}", customerModel);
		cfCustomerService.updateCustomer((modelToEntity(customerModel,AppCommonsCode.UPDATE.value())));
		return init(customerModel);
	}
	
	/**
	 * 删除页面初始化
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping(value = "/delinit")
	@ExceptionHandle("/customer/init")
	public String delinit(CustomerModel customerModel,Model model) {
		logger.info("删除客户" + customerModel.getId());
		customerModel = entityToModel(cfCustomerService.selectCustomerById(customerModel.getId()));
		model.addAttribute(customerModel);		
		return "recharge/customer/customerdel";
	}
	
	/**
	 * 删除数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deldata")
	@ExceptionHandle("/customer/delinit")
	@OperationResult(value=Operation.DELETE)
	@SecurityRequest(repeatRequest=true)
	public String customerdel(CustomerModel customerModel) throws AppException {
		logger.info("删除客户数据" + customerModel.getId());
		cfCustomerService.deleteCustomerById((customerModel.getId()));;
		return init(customerModel);
	}
	
	
}
