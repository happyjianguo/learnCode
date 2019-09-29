package cn.com.jansh.controller.recharge;

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
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.util.IDUtils;
import cn.com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.core.web.util.AppUtil;
import cn.com.jansh.entity.wsfdn.CfAccessclientEntity;
import cn.com.jansh.entity.wsfdn.CfAccesspriceEntity;
import cn.com.jansh.entity.wsfdn.CfCustomerEntity;
import cn.com.jansh.entity.wsfdn.CfProvinceEntity;
import cn.com.jansh.entity.wsfdn.CfRechargeEntity;
import cn.com.jansh.model.RechargeModel;
import cn.com.jansh.service.wsfdn.CfAccessclientService;
import cn.com.jansh.service.wsfdn.CfAccesspriceService;
import cn.com.jansh.service.wsfdn.CfCustomerService;
import cn.com.jansh.service.wsfdn.CfProvinceService;
import cn.com.jansh.service.wsfdn.CfRechargeService;

/**
 * 充值管理
 * @author duanmuyn
 *
 */
@Controller
@RequestMapping(value="/recharge")
public class CfRechargeController {
	
	private static final Logger logger = LogManager.getLogger(CfRechargeController.class);
	
	@Autowired
	private CfCustomerService cfCustomerService;
	@Autowired
	private CfProvinceService cfProvinceService;
	@Autowired
	private CfAccesspriceService cfAccesspriceService;
	@Autowired
	private CfRechargeService cfRechargeService;
	@Autowired
	private CfAccessclientService cfAccessclientService;		
	/**-------------------------------------------话费充值begin--------------------------------------------------**/
	/**
	 * 话费充值页面初始化
	 * @return
	 */
	@RequestMapping(value="/init")
	public String hfrecharge(RechargeModel rechargeModel){
		logger.info("单笔话费充值初始化");
		rechargeModel.setCfCustomerList(cfCustomerService.queryCustomer());
		rechargeModel.setProvinceList(cfProvinceService.query());
		return"/recharge/recharge/recharge";
	}
	
	/**
	 * 运营商初始化数据
	 * @return
	 */
	@RequestMapping(value="/ajax/initIpsnoValue")
	@ResponseBody
	public ViewObject initIpsnoValue(String acid){
		logger.info("运营商初始化数据{}",acid);
		CfAccesspriceEntity cfAccesspriceEntity = new CfAccesspriceEntity();
		cfAccesspriceEntity.setIpstype(AppCommonsCode.IPSTYPE_BILL.value());//话费
		cfAccesspriceEntity.setAcid(acid);
		return new ViewObject(cfAccesspriceService.queryIspno(cfAccesspriceEntity));
	}
	
	/**
	 * 省份初始化数据
	 * @return
	 */
	@RequestMapping(value="/ajax/initprovinceValue")
	@ResponseBody
	public ViewObject initprovinceValue(String acid,String ispno){
		logger.info("运营商初始化数据{}",acid);
		CfAccesspriceEntity cfAccesspriceEntity = new CfAccesspriceEntity();
		cfAccesspriceEntity.setIpstype(AppCommonsCode.IPSTYPE_BILL.value());//话费
		cfAccesspriceEntity.setAcid(acid);
		cfAccesspriceEntity.setIspno(ispno);
		return new ViewObject(cfAccesspriceService.queryprovince(cfAccesspriceEntity));
	}
	
	/**
	 * 面值初始化数据
	 * @return
	 */
	@RequestMapping(value="/ajax/initFaceValue")
	@ResponseBody
	public ViewObject initFaceValue(String ispno, String province, String acid){
		logger.info("面值初始化数据{},{},{}",ispno,province,acid);
		CfAccesspriceEntity cfAccesspriceEntity = new CfAccesspriceEntity();
		cfAccesspriceEntity.setIspno(ispno);
		cfAccesspriceEntity.setIpstype(AppCommonsCode.IPSTYPE_BILL.value());//话费
		cfAccesspriceEntity.setProvince(province);
		cfAccesspriceEntity.setAcid(acid);
		cfAccesspriceEntity.setStatus(AppCommonsCode.STATUS_OK.value());
		return new ViewObject(cfAccesspriceService.query(cfAccesspriceEntity));
	}
	
	/**
	 * 增加数据
	 * 结果回显
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping(value = "/add")
	@ExceptionHandle("/recharge/init")
	@OperationResult
	@SecurityRequest(repeatRequest=true)
	public String useradd(RechargeModel rechargeModel,Model model) {
		logger.info("添加单笔充值信息:{}", rechargeModel);
		rechargeModel.setIsptype(AppCommonsCode.IPSTYPE_BILL.value());
		rechargeModel.setStatus(AppCommonsCode.RECHARGE_30.value());
		cfRechargeService.insert(modelToEntity(rechargeModel));
		CfCustomerEntity cfCustomer = cfCustomerService.selectCustomerById(rechargeModel.getCid());
		CfAccessclientEntity cfAccessclient = cfAccessclientService.queryAccessclientById(rechargeModel.getAcid());
		CfProvinceEntity cfProvince = cfProvinceService.selectPnameBypno(rechargeModel.getProvince());
		rechargeModel.setCname(cfCustomer.getCname());
		rechargeModel.setAcname(cfAccessclient.getAcname());
		rechargeModel.setPname(cfProvince.getPname());
		model.addAttribute(rechargeModel);
		return"/recharge/recharge/rechargeresult2";
		
	}
	/**-------------------------------------------话费充值end--------------------------------------------------**/
	
	/**-------------------------------------------流量充值begin--------------------------------------------------**/
	
	/**
	 * 流量充值页面初始化
	 * @return
	 */
	@RequestMapping(value="/llrecharge")
	public String llrecharge(RechargeModel rechargeModel){
		logger.info("单笔流量充值初始化");
		rechargeModel.setCfCustomerList(cfCustomerService.queryCustomer());
		rechargeModel.setProvinceList(cfProvinceService.query());
		return "/recharge/recharge/trafficrecharge";
	}

	/**
	 * 运营商初始化数据
	 * @return
	 */
	@RequestMapping(value="/ajax/llinitIpsnoValue")
	@ResponseBody
	public ViewObject llinitIpsnoValue(String acid){
		logger.info("运营商初始化数据{}",acid);
		CfAccesspriceEntity cfAccesspriceEntity = new CfAccesspriceEntity();
		cfAccesspriceEntity.setIpstype(AppCommonsCode.IPSTYPE_Flow.value());//流量
		cfAccesspriceEntity.setAcid(acid);
		return new ViewObject(cfAccesspriceService.queryIspno(cfAccesspriceEntity));
	}
	
	/**
	 * 省份初始化数据
	 * @return
	 */
	@RequestMapping(value="/ajax/llinitprovinceValue")
	@ResponseBody
	public ViewObject llinitprovinceValue(String acid,String ispno){
		logger.info("运营商初始化数据{}",acid);
		CfAccesspriceEntity cfAccesspriceEntity = new CfAccesspriceEntity();
		cfAccesspriceEntity.setIpstype(AppCommonsCode.IPSTYPE_Flow.value());//流量
		cfAccesspriceEntity.setAcid(acid);
		cfAccesspriceEntity.setIspno(ispno);
		return new ViewObject(cfAccesspriceService.queryprovince(cfAccesspriceEntity));
	}
	
	/**
	 * 面值初始化数据
	 * @return
	 */
	@RequestMapping(value="/ajax/llinitFaceValue")
	@ResponseBody
	public ViewObject llinitFaceValue(String ispno, String province, String acid){
		logger.info("面值初始化数据{},{},{}",ispno,province,acid);
		CfAccesspriceEntity cfAccesspriceEntity = new CfAccesspriceEntity();
		cfAccesspriceEntity.setIspno(ispno);
		cfAccesspriceEntity.setIpstype(AppCommonsCode.IPSTYPE_Flow.value());//流量
		cfAccesspriceEntity.setProvince(province);
		cfAccesspriceEntity.setAcid(acid);
		cfAccesspriceEntity.setStatus(AppCommonsCode.STATUS_OK.value());
		return new ViewObject(cfAccesspriceService.query(cfAccesspriceEntity));
	}
	
	/**
	 * 增加数据
	 * 数据回显
	 * @param rechargeModel
	 * @return
	 * @throws AppException
	 */
	@RequestMapping(value = "/lladd")
	@ExceptionHandle("/recharge/llrecharge")
	@OperationResult
	@SecurityRequest(repeatRequest=true)
	public String lladd(RechargeModel rechargeModel,Model model) {
		logger.info("添加单笔充值信息:{}", rechargeModel);
		rechargeModel.setIsptype(AppCommonsCode.IPSTYPE_Flow.value());
		rechargeModel.setStatus(AppCommonsCode.RECHARGE_30.value());
		cfRechargeService.insert(modelToEntity(rechargeModel));
		CfCustomerEntity cfCustomer = cfCustomerService.selectCustomerById(rechargeModel.getCid());
		CfAccessclientEntity cfAccessclient = cfAccessclientService.queryAccessclientById(rechargeModel.getAcid());
		CfProvinceEntity cfProvince = cfProvinceService.selectPnameBypno(rechargeModel.getProvince());
		rechargeModel.setCname(cfCustomer.getCname());
		rechargeModel.setAcname(cfAccessclient.getAcname());
		rechargeModel.setPname(cfProvince.getPname());
		model.addAttribute(rechargeModel);		
		return"/recharge/recharge/rechargeresult2";
	}
	
	/**-------------------------------------------流量充值end--------------------------------------------------**/
	
	/**
	 * Model转Entity(新增)
	 * @param supplierModel
	 * @param type("add"时id自动生成，"edit"时用原有id)
	 * @return
	 */
	private CfRechargeEntity modelToEntity(RechargeModel rechargeModel){
		CfRechargeEntity cfRechargeEntity = new CfRechargeEntity();
		cfRechargeEntity.setId(IDUtils.getTimeRandon());
		cfRechargeEntity.setBizid("");
		cfRechargeEntity.setFacevalue(rechargeModel.getFacevalue());
		cfRechargeEntity.setOrderid(IDUtils.getTimeRandon());
		cfRechargeEntity.setIspno(rechargeModel.getIspno());
		cfRechargeEntity.setIsptype(rechargeModel.getIsptype());
		cfRechargeEntity.setPhone(rechargeModel.getPhone());
		cfRechargeEntity.setProvince(rechargeModel.getProvince());
		cfRechargeEntity.setStatus(rechargeModel.getStatus());
		cfRechargeEntity.setCid(rechargeModel.getCid());
		cfRechargeEntity.setAcid(rechargeModel.getAcid());
		cfRechargeEntity.setStatus(rechargeModel.getStatus());
		cfRechargeEntity.setSource(AppCommonsCode.SOURCE_PLATFORM.value());
		cfRechargeEntity.setCbstatus(AppCommonsCode.CALLBACK_NOT.value());
		cfRechargeEntity.setUserid(AppUtil.getUserDetail().getUserid());
		return cfRechargeEntity;
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
