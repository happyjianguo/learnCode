package cn.com.jansh.controller.recharge;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
import cn.com.jansh.entity.wsfdn.CfAccesspriceEntity;
import cn.com.jansh.entity.wsfdn.CfSupplierpriceEntity;
import cn.com.jansh.model.AccesspriceModel;
import cn.com.jansh.service.audit.impl.AuditAccesspriceServiceImpl;
import cn.com.jansh.service.wsfdn.CfAccessclientService;
import cn.com.jansh.service.wsfdn.CfAccesspriceService;
import cn.com.jansh.service.wsfdn.CfAuditService;
import cn.com.jansh.service.wsfdn.CfCustomerService;
import cn.com.jansh.service.wsfdn.CfProvinceService;
import cn.com.jansh.service.wsfdn.CfSupplierpriceService;

/**
 * 接入者价格管理
 * @author duanmuyn
 *
 */
@Controller
@RequestMapping(value="/accprice")
public class CfAccesspriceController {
	
	private static final Logger logger = LogManager.getLogger(CfAccesspriceController.class);
	
	@Autowired
	private CfAccesspriceService cfAccesspriceService;
	
	@Autowired
	private CfAccessclientService cfAccessclientService;
	
	@Autowired
	private CfProvinceService cfProvinceService;
	
	@Autowired
	private CfSupplierpriceService cfSupplierpriceService;
	
	@Autowired
	private CfCustomerService cfCustomerService;
	
	@Autowired
	private CfAuditService cfAuditService;
	
	@Autowired
	private AuditAccesspriceServiceImpl auditAccesspriceServiceImpl;
	/**
	 * 跳转初始化页面
	 * @return
	 */
	@RequestMapping(value="/init")
	public String init(AccesspriceModel accesspriceModel){
		logger.info("接入者报价列表初始化");
		/*初始化接入者*/
		accesspriceModel.setPriceList(cfAccessclientService.queryAccessclient());
		/*初始化省份*/
		accesspriceModel.setProvinceList(cfProvinceService.query());
		return"/recharge/accessprice/apmanage";	
	}
	
	/**
	 * 初始化接入者价格列表（查询）
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "ajax/accpriceprice")
	@ResponseBody
	public ViewObject findByMessage(CfAccesspriceEntity cfAccesspriceEntity) {
		return new ViewObject(cfAccesspriceService.query(cfAccesspriceEntity));
	}
	
	/**
	 * 新增页面初始化
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping(value = "/addinit")
	@ExceptionHandle("/accprice/init")
	public String addinit(AccesspriceModel accesspriceModel) {
		logger.info("新增接入者报价初始化");
		/*初始化客户*/
		accesspriceModel.setCfCustomerList(cfCustomerService.queryCustomer());
		return "/recharge/accessprice/apmanageadd";
	}
	
	/**
	 * 增加数据
	 * @return
	 * @throws AppException 
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/add")
	@ExceptionHandle("/accprice/addinit")
	@OperationResult(value=Operation.CREATE)
	@SecurityRequest(repeatRequest=true)
	//@AuditRequest(audittype = Operation.CREATE, classname = "cn.com.jansh.core.model.sys.AccesspriceModel")
	public String useradd(AccesspriceModel accesspriceModel) throws JsonProcessingException, AppException {
		logger.info("添加接入者报价:{}", accesspriceModel);
		List<CfAccesspriceEntity> li = cfAccesspriceService.query(modelToEntity(accesspriceModel));
		if(CollectionUtils.isNotEmpty(li)){
			throw new AppException(AppErrorCode.E260001);
		}else{
		accesspriceModel.setApid(IDUtils.getTimeRandon());
		accesspriceModel.setPrice(accesspriceModel.getPrice().replaceAll(",",""));
		//授权
		cfAuditService.saveAudit("接入者价格管理", Operation.CREATE, accesspriceModel.getApid(), accesspriceModel, auditAccesspriceServiceImpl);
		}		
		return init(accesspriceModel);
	}
	private CfAccesspriceEntity modelToEntity(AccesspriceModel accesspriceModel){
		CfAccesspriceEntity cfAccesspriceEntity = new CfAccesspriceEntity();
		
		cfAccesspriceEntity.setApid("");
		cfAccesspriceEntity.setPrice("");
		cfAccesspriceEntity.setAcid(accesspriceModel.getAcid());
		cfAccesspriceEntity.setAcname(accesspriceModel.getAcname());	
		cfAccesspriceEntity.setFacevalue(accesspriceModel.getFacevalue());
		cfAccesspriceEntity.setIpstype(accesspriceModel.getIpstype());
		cfAccesspriceEntity.setIspno(accesspriceModel.getIspno());
		cfAccesspriceEntity.setPname(accesspriceModel.getPname());
		cfAccesspriceEntity.setProvince(accesspriceModel.getProvince());
		cfAccesspriceEntity.setStatus(accesspriceModel.getStatus());
		return cfAccesspriceEntity;
	}

	/**
	 * 修改页面初始化
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping(value = "/editinit")
	@ExceptionHandle("/accprice/init")
	public String editinit(AccesspriceModel accesspriceModel,Model model) {
		logger.info("修改供应商报价初始化" + accesspriceModel.getApid());
		accesspriceModel = entityToModel(cfAccesspriceService.queryAccesspriceByid(accesspriceModel.getApid()));
		/*初始化接入者*/
		accesspriceModel.setPriceList(cfAccessclientService.queryAccessclient());
		/*初始化省份*/
		accesspriceModel.setProvinceList(cfProvinceService.query());
		model.addAttribute(accesspriceModel);
		return "/recharge/accessprice/apmanageedit";
	}
	
	/**
	 * Entity转Model(修改)
	 * @param supplierModel
	 * @return
	 */
	private AccesspriceModel entityToModel(CfAccesspriceEntity cfAccesspriceEntity){
		logger.info("Entity转Model(修改):{}", cfAccesspriceEntity.getAcid());
		AccesspriceModel accesspriceModel = new AccesspriceModel();
		accesspriceModel.setApid(cfAccesspriceEntity.getApid());
		accesspriceModel.setAcid(cfAccesspriceEntity.getAcid());
		accesspriceModel.setAcname(cfAccesspriceEntity.getAcname());
		accesspriceModel.setFacevalue(cfAccesspriceEntity.getFacevalue());
		accesspriceModel.setIpstype(cfAccesspriceEntity.getIpstype());
		accesspriceModel.setIspno(cfAccesspriceEntity.getIspno());
		accesspriceModel.setPname(cfAccesspriceEntity.getPname());
		accesspriceModel.setPrice(cfAccesspriceEntity.getPrice());
		accesspriceModel.setProvince(cfAccesspriceEntity.getProvince());
		accesspriceModel.setStatus(cfAccesspriceEntity.getStatus());
		return accesspriceModel;
	}
	
	/**
	 * 修改数据
	 * @param imuser
	 * @return
	 * @throws JsonProcessingException 
	 * @throws AppException 
	 */
	@RequestMapping(value = "/editdata")
	@ExceptionHandle("/accprice/editinit")
	@OperationResult(value=Operation.UPDATE)
	@SecurityRequest(repeatRequest=true)
	public String useredit(AccesspriceModel accesspriceModel) throws JsonProcessingException, AppException {
		logger.info("修改接入者报价数据:{}", accesspriceModel);
		//授权
		accesspriceModel.setPrice(accesspriceModel.getPrice().replaceAll(",",""));
		cfAuditService.saveAudit("接入者价格管理", Operation.UPDATE, accesspriceModel.getApid(), accesspriceModel, auditAccesspriceServiceImpl);
		//cfAccesspriceService.updateAccessprice(modelToEntity(accesspriceModel,"edit"));
		return init(accesspriceModel);
	}
	
	/**
	 * 删除页面初始化
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping(value = "/delinit")
	@ExceptionHandle("/accprice/init")
	public String delinit(AccesspriceModel accesspriceModel,Model model) {
		logger.info("删除接入者报价初始化" + accesspriceModel.getAcid());
		accesspriceModel = entityToModel(cfAccesspriceService.queryAccesspriceByid(accesspriceModel.getApid()));
		/*初始化接入者*/
		accesspriceModel.setPriceList(cfAccessclientService.queryAccessclient());
		/*初始化省份*/
		accesspriceModel.setProvinceList(cfProvinceService.query());
		model.addAttribute(accesspriceModel);		
		return "/recharge/accessprice/apmanageedel";
	}
	
	/**
	 * 删除数据(改状态)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deldata")
	@ExceptionHandle("/accprice/delinit")
	@OperationResult(value=Operation.DELETE)
	@SecurityRequest(repeatRequest=true)
	public String deldata(AccesspriceModel accesspriceModel) throws Exception {
		logger.info("删除接入者报价" + accesspriceModel.getApid());
		accesspriceModel = entityToModel(cfAccesspriceService.queryAccesspriceByid(accesspriceModel.getApid()));
		//授权
		cfAuditService.saveAudit("接入者价格管理", Operation.DELETE, accesspriceModel.getApid(), accesspriceModel, auditAccesspriceServiceImpl);
		//cfAccesspriceService.deldeteAccessprice(accesspriceModel.getApid());
		return init(accesspriceModel);
	}
	
	/***************************************************************************************/
	/**
	 * 运营商初始化数据
	 * @return
	 */
	@RequestMapping(value="/ajax/initIpsno")
	@ResponseBody
	public ViewObject initIpsno(){
		logger.info("运营商初始化数据{}");
		return new ViewObject(cfSupplierpriceService.queryIpsno());
	}
	
	/**
	 * 套餐初始化数据
	 * @return
	 */
	@RequestMapping(value="/ajax/initIpsType")
	@ResponseBody
	public ViewObject initIpsType(String ispno){
		logger.info("套餐初始化数据{}",ispno);
		return new ViewObject(cfSupplierpriceService.queryIpsType(ispno));
	}
	
	/**
	 * 省份初始化数据
	 * @return
	 */
	@RequestMapping(value="/ajax/initprovince")
	@ResponseBody
	public ViewObject initprovince(String ispno,String ipstype){
		logger.info("省份初始化数据{},{}",ispno,ipstype);
		CfSupplierpriceEntity cfSupplierpriceEntity = new CfSupplierpriceEntity();
		cfSupplierpriceEntity.setIspno(ispno);
		cfSupplierpriceEntity.setIpstype(ipstype);
		return new ViewObject(cfSupplierpriceService.queryprovince(cfSupplierpriceEntity));
	}
	
	/**
	 * 面值初始化数据
	 * @return
	 */
	@RequestMapping(value="/ajax/initFaceValue")
	@ResponseBody
	public ViewObject initFaceValue(String ispno,String ipstype,String province){
		logger.info("面值初始化数据{},{},{}",ispno,ipstype,province);
		CfSupplierpriceEntity cfSupplierpriceEntity = new CfSupplierpriceEntity();
		cfSupplierpriceEntity.setIspno(ispno);
		cfSupplierpriceEntity.setIpstype(ipstype);
		cfSupplierpriceEntity.setPno(province);
		return new ViewObject(cfSupplierpriceService.queryFaceValue(cfSupplierpriceEntity));
	}
	
	/**
	 * 根据客户查询接入者
	 * @return
	 */
	@RequestMapping(value="/ajax/initClient")
	@ResponseBody
	public ViewObject initClient(String cid){
		logger.info("接入者初始化数据{}",cid);
		return new ViewObject(cfAccessclientService.queryAccessclientByCustormerIdAndSta(cid,AppCommonsCode.STATUS_OK.value()));
	} 
}
