package cn.com.jansh.controller.recharge;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.entity.wsfdn.CfBatchrechargeEntity;
import cn.com.jansh.model.BatchcurrbusilogModel;
import cn.com.jansh.model.DatatablesViewPage;
import cn.com.jansh.service.wsfdn.CfAccessclientService;
import cn.com.jansh.service.wsfdn.CfBatchcurrbusilogService;
import cn.com.jansh.service.wsfdn.CfBatchinfoService;
import cn.com.jansh.service.wsfdn.CfBatchrechargeService;
import cn.com.jansh.service.wsfdn.CfCustomerService;

/**
 * 批量充值查询
 * @author gll
 *
 */
@Controller
@RequestMapping(value = "/batchcurrbusilog")
public class CfBatchcurrbusilogController {
	
	private static final Logger logger = LogManager.getLogger(CfBatchcurrbusilogController.class);
	
	@Autowired
	private CfCustomerService cfCustomerService;
	@Autowired
	private CfBatchrechargeService cfBatchrechargeService;
	@Autowired
	private CfBatchinfoService cfBatchinfoService;
	@Autowired
	private CfAccessclientService cfAccessclientService;
	@Autowired
	private CfBatchcurrbusilogService cfBatchcurrbusilogService;
	
	ObjectMapper mapper = new ObjectMapper();
	/**
	 * 初始化查询条件
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init(BatchcurrbusilogModel batchcurrbusilogModel){
		logger.info("开始批量充值初始化");
		batchcurrbusilogModel.setCustomerlist(cfCustomerService.queryCustomer());
		return "/recharge/batchcurrbusilog/batchcurrbusilog";
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
	/**
	 * 根据接入者查询批量充值表的批次信息
	 * @return
	 */
	@RequestMapping(value = "/ajax/initbatchid")
	@ResponseBody
	public ViewObject findBatchid(String apno){
		logger.info("批次号数据{}",apno);
		return new ViewObject(cfBatchinfoService.queryBatchinfoByAccessclient(apno));
	}
	/**
	 * 根据批量充值表的批次信息查询批量充值明细表中的发送状态
	 * @return
	 */
	@RequestMapping(value = "/ajax/initstatus")
	@ResponseBody
	public ViewObject findStatus(String batchid){
		logger.info("批次号数据{}",batchid);
		return new ViewObject(cfBatchrechargeService.queryStatusByBatchid(batchid));
	}
	
	/**
	 * 开始查询
	 * @param batchcurrbusilogModel
	 * @return
	 */
	@RequestMapping(value = "/ajax/query")
	@ResponseBody
	public ViewObject findMessage(BatchcurrbusilogModel batchcurrbusilogModel){
		logger.info("开始流水查询{},{},{}",batchcurrbusilogModel.getStart(),batchcurrbusilogModel.getLength(),batchcurrbusilogModel.toString());
		batchcurrbusilogModel.setCount(cfBatchcurrbusilogService.searchLogCount(modelToEntity(batchcurrbusilogModel)));
		
		List<BatchcurrbusilogModel> model = new LinkedList<BatchcurrbusilogModel>();
		DatatablesViewPage<BatchcurrbusilogModel> view = new DatatablesViewPage<BatchcurrbusilogModel>(); 
		int leng = Integer.parseInt(batchcurrbusilogModel.getCount());
	    view.setiTotalDisplayRecords(leng);  
	    view.setiTotalRecords(leng);  
	    List<CfBatchrechargeEntity> batchrechargelist= cfBatchcurrbusilogService.batchcurrbusilog(modelToEntity(batchcurrbusilogModel),batchcurrbusilogModel.getStart(),batchcurrbusilogModel.getLength());
	    for(CfBatchrechargeEntity cf : batchrechargelist){
	    	model.add(EntityToModel(cf));
	    }
	    view.setAaData(model);
	    String result = "";
	    try {
	    	result = mapper.writeValueAsString(view);
		} catch (Exception	 e) {
			logger.error("日志数据查询失败{}",e);
		}
		
	    return new ViewObject(result);
		
	}
	
	/**
	 * Model转Entity
	 * @param supplierModel
	 * @param type("add"时id自动生成，"edit"时用原有id)
	 * @return
	 */
	private CfBatchrechargeEntity modelToEntity(BatchcurrbusilogModel batchcurrbusilogModel){
		CfBatchrechargeEntity cfBatchrechargeEntity = new CfBatchrechargeEntity();
		cfBatchrechargeEntity.setApno(batchcurrbusilogModel.getApno());
		cfBatchrechargeEntity.setCid(batchcurrbusilogModel.getCid());
		cfBatchrechargeEntity.setAcname(batchcurrbusilogModel.getAcname());
		cfBatchrechargeEntity.setCname(batchcurrbusilogModel.getCname());
		cfBatchrechargeEntity.setBatchid(batchcurrbusilogModel.getBatchid());
		cfBatchrechargeEntity.setBatchname(batchcurrbusilogModel.getBatchname());
		cfBatchrechargeEntity.setBizid(batchcurrbusilogModel.getBizid());
		cfBatchrechargeEntity.setFacevalue(batchcurrbusilogModel.getFacevalue());
		cfBatchrechargeEntity.setId(batchcurrbusilogModel.getId());
		cfBatchrechargeEntity.setIspno(batchcurrbusilogModel.getIspno());
		cfBatchrechargeEntity.setIsptype(batchcurrbusilogModel.getIsptype());
		cfBatchrechargeEntity.setPhone(batchcurrbusilogModel.getPhone());
		cfBatchrechargeEntity.setStatus(batchcurrbusilogModel.getStatus());
		return cfBatchrechargeEntity;
	}
	private BatchcurrbusilogModel EntityToModel(CfBatchrechargeEntity cfBatchrechargeEntity){
		BatchcurrbusilogModel batchcurrbusilogModel = new BatchcurrbusilogModel();
		
		batchcurrbusilogModel.setApno(cfBatchrechargeEntity.getApno());
		batchcurrbusilogModel.setCid(cfBatchrechargeEntity.getCid());
		batchcurrbusilogModel.setAcname(cfBatchrechargeEntity.getAcname());
		batchcurrbusilogModel.setCname(cfBatchrechargeEntity.getCname());
		batchcurrbusilogModel.setBatchid(cfBatchrechargeEntity.getBatchid());
		batchcurrbusilogModel.setBatchname(cfBatchrechargeEntity.getBatchname());
		batchcurrbusilogModel.setBizid(cfBatchrechargeEntity.getBizid());
		batchcurrbusilogModel.setFacevalue(cfBatchrechargeEntity.getFacevalue());
		batchcurrbusilogModel.setId(cfBatchrechargeEntity.getId());
		batchcurrbusilogModel.setIspno(cfBatchrechargeEntity.getIspno());
		batchcurrbusilogModel.setIsptype(cfBatchrechargeEntity.getIsptype());
		batchcurrbusilogModel.setPhone(cfBatchrechargeEntity.getPhone());
		batchcurrbusilogModel.setStatus(cfBatchrechargeEntity.getStatus());
		return batchcurrbusilogModel;
	}
}
