package cn.com.jansh.controller.recharge;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.core.annotation.ExceptionHandle;
import cn.com.jansh.core.annotation.OperationResult;
import cn.com.jansh.core.annotation.SecurityRequest;
import cn.com.jansh.core.constant.Operation;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.IDUtils;
import cn.com.jansh.core.util.StringUtil;
import cn.com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.entity.wsfdn.CfAccessclientEntity;
import cn.com.jansh.entity.wsfdn.CfCustomerEntity;
import cn.com.jansh.model.AccessclientModel;
import cn.com.jansh.service.wsfdn.CfAccessclientService;
import cn.com.jansh.service.wsfdn.CfCustomerService;

/**
 * 接入者管理
 * 
 * @author gll
 * @version 20160421
 */
@Controller
@RequestMapping(value = "/accessclient")
public class CfAccessclientController {

	private static final Logger logger = LogManager.getLogger(CfAccessclientController.class);

	@Autowired
	private CfAccessclientService cfAccessclientService;
	
	@Autowired
	private CfCustomerService cfCustomerService;
	
	/**
	 * 初始化接入者列表
	 * 
	 * @param accessclientModel
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init(AccessclientModel accessclientModel) {		
		/**查询接入者下拉框值 */
		List<CfAccessclientEntity> accessclientListselect = cfAccessclientService.queryAccessclient();
		accessclientModel.setAccessclientListselect(accessclientListselect);
		/**查询客户下拉框值 */
		List<CfCustomerEntity> customerListselect = cfCustomerService.queryCustomer();	
		accessclientModel.setCustomerListselect(customerListselect);
		/**获取接入者列表*/
		logger.info("获取接入者列表");		
		List<CfAccessclientEntity> accessclientList = cfAccessclientService.queryAccessclientBy(ModelToEntity(accessclientModel));
		accessclientModel.setAccessclientList(accessclientList);
		return "/recharge/accessclient/accessclient";
	}

	private CfAccessclientEntity ModelToEntity(AccessclientModel accessclientModel){
		CfAccessclientEntity cfAccessclientEntity = new CfAccessclientEntity();
		cfAccessclientEntity.setId(accessclientModel.getId());
		cfAccessclientEntity.setCid(accessclientModel.getCid());
		cfAccessclientEntity.setStatus(accessclientModel.getStatus());
		cfAccessclientEntity.setQid(accessclientModel.getQid());
		cfAccessclientEntity.setQcid(accessclientModel.getQcid());
		cfAccessclientEntity.setQstatus(accessclientModel.getQstatus());
		return cfAccessclientEntity;		
	}
	/**
	 * 查询数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/accessclientselect")
	@ExceptionHandle("/accessclient/init")
	@OperationResult(value=Operation.RETRIEVE)
	public String accessclientselect(AccessclientModel accessclientModel) throws Exception {
		logger.info("查询接入者" + accessclientModel.getId());
		cfAccessclientService.queryAccessclientById(accessclientModel.getId());
		return init(accessclientModel);
	}

	/**
	 * 新增页面初始化
	 * 
	 * @param cfCustomerModel
	 * @return
	 */
	@RequestMapping(value = "/addinit")
	@ExceptionHandle("/accessclient/init")
	public String addinit(AccessclientModel accessclientModel) {
		logger.info("获取接入者列表");
		List<CfAccessclientEntity> accessclientList = cfAccessclientService.queryAccessclient();
		/*初始化起止日期*/
		accessclientModel.setBegintime(DateUtil.getDateDay());
		accessclientModel.setEndtime(DateUtil.getLastDay());
		accessclientModel.setAccessclientList(accessclientList);
		accessclientModel.setCfCustomerList(cfCustomerService.queryCustomer());
		return "recharge/accessclient/accessclientadd";
	}

	/**
	 * 增加数据
	 * 
	 * @return
	 * @throws AppException
	 */
	@RequestMapping(value = "/adddata")
	@ExceptionHandle("/accessclient/addinit")
	@OperationResult(value=Operation.CREATE)
	@SecurityRequest(repeatRequest=true)
	public String addaccessclient(AccessclientModel accessclientModel) throws AppException {
		logger.info("添加接入者数据:{}", accessclientModel);
		//初始化累计消费为0.00
		accessclientModel.setCumulative("0.00");
		accessclientModel.setAckey(StringUtil.randomCharNum(8));
		accessclientModel.setBudget(accessclientModel.getBudget().replaceAll(",",""));
		accessclientModel.setBegintime(DateUtil.getDateTime(DateUtil.parseDate(accessclientModel.getBegintime())));
		accessclientModel.setEndtime(DateUtil.getDateTime(DateUtil.addOSecond(DateUtil.addDate(DateUtil.parseDate(accessclientModel.getEndtime()),1),-1)));
		cfAccessclientService.insertAccessclient(modelToEntity(accessclientModel, AppCommonsCode.CREATE.value()));
		return init(accessclientModel);
	}

	/**
	 * Model转Entity(新增)
	 * 
	 * @param supplierModel
	 * @param type("add"时id自动生成，"edit"时用原有id)
	 * @return
	 */
	private CfAccessclientEntity modelToEntity(AccessclientModel accessclientModel, String type) {
		CfAccessclientEntity cfAccessclientEntity = new CfAccessclientEntity();
		if (type.equals(AppCommonsCode.CREATE.value())) {
			cfAccessclientEntity.setId(IDUtils.getTimeRandon());
		} else if (type.equals(AppCommonsCode.UPDATE.value())) {
			cfAccessclientEntity.setId(accessclientModel.getId());
		}
		cfAccessclientEntity.setAcname(accessclientModel.getAcname());
		cfAccessclientEntity.setCid(accessclientModel.getCid());
		cfAccessclientEntity.setMname(accessclientModel.getMname());
		cfAccessclientEntity.setPhone(accessclientModel.getPhone());
		cfAccessclientEntity.setEmail(accessclientModel.getEmail());
		cfAccessclientEntity.setAckey(accessclientModel.getAckey());
		cfAccessclientEntity.setBegintime(accessclientModel.getBegintime());
		cfAccessclientEntity.setEndtime(accessclientModel.getEndtime());
		cfAccessclientEntity.setBudget(accessclientModel.getBudget());
		cfAccessclientEntity.setStatus(accessclientModel.getStatus());
		cfAccessclientEntity.setCumulative(accessclientModel.getCumulative());
		cfAccessclientEntity.setCallbackurl(accessclientModel.getCallbackurl());
		return cfAccessclientEntity;
	}

	/**
	 * 修改页面初始化
	 * 
	 * @param userManageModel
	 * @return
	 */

	@RequestMapping(value = "/editinit")
	@ExceptionHandle("/accessclient/init")
	public String editinit(AccessclientModel accessclientModel, Model model) {
		logger.info("修改接入者初始化" + accessclientModel.getId());
		accessclientModel = entityToModel(cfAccessclientService.queryAccessclientById(accessclientModel.getId()));	
		accessclientModel.setBegintime(DateUtil.getDate(DateUtil.parseDateTime(accessclientModel.getBegintime())));
		accessclientModel.setEndtime(DateUtil.getDate(DateUtil.parseDateTime(accessclientModel.getEndtime())));
		accessclientModel.setCfCustomerList(cfCustomerService.queryCustomer());
		model.addAttribute(accessclientModel);
		return "recharge/accessclient/accessclientedit";
	}

	/**
	 * Entity转Model(修改)
	 * @param cfAccessclientEntity
	 * @return
	 */
	private AccessclientModel entityToModel(CfAccessclientEntity cfAccessclientEntity) {
		logger.info("Entity转Model(修改):{}", cfAccessclientEntity.getId());
		AccessclientModel accessclientModel = new AccessclientModel();

		accessclientModel.setAcname(cfAccessclientEntity.getAcname());
		accessclientModel.setMname(cfAccessclientEntity.getMname());
		accessclientModel.setPhone(cfAccessclientEntity.getPhone());
		accessclientModel.setEmail(cfAccessclientEntity.getEmail());
		accessclientModel.setId(cfAccessclientEntity.getId());
		accessclientModel.setCid(cfAccessclientEntity.getCid());
		accessclientModel.setAckey(cfAccessclientEntity.getAckey());
		accessclientModel.setBegintime(cfAccessclientEntity.getBegintime());
		accessclientModel.setEndtime(cfAccessclientEntity.getEndtime());
		accessclientModel.setBudget(cfAccessclientEntity.getBudget());
		accessclientModel.setStatus(cfAccessclientEntity.getStatus());
		accessclientModel.setCumulative(cfAccessclientEntity.getCumulative());
		accessclientModel.setCallbackurl(cfAccessclientEntity.getCallbackurl());
		return accessclientModel;
	}

	/**
	 * 修改数据
	 * 
	 * @param imuser
	 * @return
	 */
	@RequestMapping(value = "/editdata")
	@ExceptionHandle("/accessclient/editinit")
	@OperationResult(value=Operation.UPDATE)
	@SecurityRequest(repeatRequest=true)
	public String accessclientedit(AccessclientModel accessclientModel) throws AppException {
		logger.info("修改接入者数据:{}", accessclientModel);
		accessclientModel.setBudget(accessclientModel.getBudget().replaceAll(",",""));
		accessclientModel.setBegintime(DateUtil.getDateTime(DateUtil.parseDate(accessclientModel.getBegintime())));
		accessclientModel.setEndtime(DateUtil.getDateTime(DateUtil.addOSecond(DateUtil.addDate(DateUtil.parseDate(accessclientModel.getEndtime()),1),-1)));
		cfAccessclientService.updateAccessclient((modelToEntity(accessclientModel, AppCommonsCode.UPDATE.value())));
		return init(accessclientModel);
	}

	/**
	 * 删除页面初始化
	 * 
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping(value = "/delinit")
	@ExceptionHandle("/accessclient/init")
	public String delinit(AccessclientModel accessclientModel,Model model) {
		logger.info("删除接入者" + accessclientModel.getId());
		accessclientModel = entityToModel(cfAccessclientService.queryAccessclientById(accessclientModel.getId()));	
		accessclientModel.setBegintime(DateUtil.getDate(DateUtil.parseDateTime(accessclientModel.getBegintime())));
		accessclientModel.setEndtime(DateUtil.getDate(DateUtil.parseDateTime(accessclientModel.getEndtime())));
		accessclientModel.setCfCustomerList(cfCustomerService.queryCustomer());
		model.addAttribute(accessclientModel);		
		return "recharge/accessclient/accessclientdel";
	}

	/**
	 * 删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deldata")
	@ExceptionHandle("/accessclient/delinit")
	@OperationResult(value=Operation.DELETE)
	@SecurityRequest(repeatRequest=true)
	public String accessclientdel(AccessclientModel accessclientModel) throws AppException {
		logger.info("删除接入者数据" + accessclientModel.getId());
		cfAccessclientService.deleteAccessclient((accessclientModel.getId()));
		return init(accessclientModel);
	}
	
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

}
