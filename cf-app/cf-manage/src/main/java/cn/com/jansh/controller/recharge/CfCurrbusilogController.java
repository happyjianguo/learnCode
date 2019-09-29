package cn.com.jansh.controller.recharge;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.jansh.constant.BusiLogStatus;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.ExportExcel;
import cn.com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.entity.wsfdn.CfCurrbusilogEntity;
import cn.com.jansh.model.CurrbusilogModel;
import cn.com.jansh.model.DatatablesViewPage;
import cn.com.jansh.service.wsfdn.CfCurrbusilogService;
import cn.com.jansh.service.wsfdn.CfCustomerService;
import cn.com.jansh.service.wsfdn.CfProvinceService;


/**
 * 充值管理-流水查询
 * 
 * @author gll
 *
 */
@Controller
@RequestMapping(value = "/currbusilog")
public class CfCurrbusilogController {
	
	private static final Logger logger = LogManager.getLogger(CfCurrbusilogController.class);

	@Autowired
	private CfCurrbusilogService cfCurrbusilogService;
	
	@Autowired
	private CfCustomerService cfCustomerService;
	
	@Autowired
	private CfProvinceService cfProvinceService;
	
	
	ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(value = "/init")
	public String init(CurrbusilogModel currbusilogModel){
		logger.info("开始流水查询");

		currbusilogModel.setCfCustomerList(cfCustomerService.queryCustomer());
		currbusilogModel.setProvinceList(cfProvinceService.query());
		/** 时间初始化  */
		currbusilogModel.setBegintime(DateUtil.getFirstDay());
		currbusilogModel.setEndtime(DateUtil.getDateDay());
		return "recharge/currbusilog/currbusilog";
	}
	/**
	 * （查询）
	 * @param message
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/ajax/query")
	@ResponseBody
	private ViewObject findByMessage(CurrbusilogModel currbusilogModel) throws ParseException {
		logger.info("开始流水查询{},{},{}",currbusilogModel.getStart(),currbusilogModel.getLength(),currbusilogModel.toString());
		/*currbusilogModel.setPagenow(request.getParameter("start"));
		currbusilogModel.setPagesize(request.getParameter("length"));*/
		currbusilogModel.setCount(cfCurrbusilogService.searchLogCount(modelToEntity(currbusilogModel)));
		
		List<CurrbusilogModel> model = new LinkedList<CurrbusilogModel>();
		DatatablesViewPage<CurrbusilogModel> view = new DatatablesViewPage<CurrbusilogModel>(); 
		int leng = Integer.parseInt(currbusilogModel.getCount());
	    view.setiTotalDisplayRecords(leng);  
	    view.setiTotalRecords(leng);  
	    List<CfCurrbusilogEntity> cfCurrbusilog= cfCurrbusilogService.queryCurrbusilog(modelToEntity(currbusilogModel),currbusilogModel.getStart(),currbusilogModel.getLength());
	    for(CfCurrbusilogEntity cf : cfCurrbusilog){
	    	cf.setCreatetime(DateUtil.getDateTime(DateUtil.parseDateTime2(cf.getCreatetime())));
	    	cf.setUpdatetime(DateUtil.getDateTime(DateUtil.parseDateTime2(cf.getUpdatetime())));
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
	 * 下载报表
	 * @param message
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/download")
	private ResponseEntity<byte[]> exceldownload(HttpServletRequest request, CurrbusilogModel currbusilogModel) throws IOException, ParseException {
		logger.info("开始下载报表{}",currbusilogModel.toString());
		ExportExcel<CfCurrbusilogEntity> ex = new ExportExcel<CfCurrbusilogEntity>();
		String title = "流水查询";
		String[] headers ={ "流水号","客户名称", "接入者名称", "手机号", "运营商编号", "套餐类型","省份","面值","供应商订单号","平台订单号","接入者订单号","返回码","供应商报价","供应商编号","接入者报价","接入者编号","创建时间","更新时间","状态" };
		List<CfCurrbusilogEntity> dataset = new ArrayList<CfCurrbusilogEntity>();
		dataset =cfCurrbusilogService.queryAllCurrbusilog(modelToEntity(currbusilogModel));
		int j=dataset.size();
		WebApplicationContext ac = RequestContextUtils.findWebApplicationContext(request);
		for(int i = 0;i<j;i++){
			dataset.get(i).setCreatetime(DateUtil.getDateTime(DateUtil.parseDateTime2(dataset.get(i).getCreatetime())));
			dataset.get(i).setUpdatetime(DateUtil.getDateTime(DateUtil.parseDateTime2(dataset.get(i).getUpdatetime())));
			ac.getMessage(BusiLogStatus.INPUT.value(), null, RequestContextUtils.getLocale(request));
			dataset.get(i).setStatus(ac.getMessage(dataset.get(i).getStatus(), null, RequestContextUtils.getLocale(request)));
		} 
        ByteArrayOutputStream outStream =new ByteArrayOutputStream();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("title", title);
        map.put("headers", headers);
        map.put("dataset", dataset);
        list.add(map);
        HttpHeaders header = new HttpHeaders();    
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=dowmloaddata.xlsx");
	    return new ResponseEntity<byte[]>(ex.exportExcel(list, outStream).toByteArray(), header, HttpStatus.OK); 
	}
	public static void main(String[] args){
		
	}
	/**
	 * Model转Entity(新增)
	 * @param supplierModel
	 * @param type("add"时id自动生成，"edit"时用原有id)
	 * @return
	 */
	private CurrbusilogModel EntityToModel(CfCurrbusilogEntity cfCurrbusilogEntity){
		CurrbusilogModel currbusilogModel = new CurrbusilogModel();
		currbusilogModel.setBegintime(cfCurrbusilogEntity.getBegintime());
		currbusilogModel.setEndtime(cfCurrbusilogEntity.getEndtime());
		currbusilogModel.setCid(cfCurrbusilogEntity.getCid());
		currbusilogModel.setAcid(cfCurrbusilogEntity.getAcid());
		currbusilogModel.setBizid(cfCurrbusilogEntity.getBizid());
		currbusilogModel.setCname(cfCurrbusilogEntity.getCname());
		currbusilogModel.setAcname(cfCurrbusilogEntity.getAcname());
		currbusilogModel.setIspno(cfCurrbusilogEntity.getIspno());
		currbusilogModel.setIpstype(cfCurrbusilogEntity.getIpstype());
		currbusilogModel.setIpstype(cfCurrbusilogEntity.getIpstype());
		currbusilogModel.setProvince(cfCurrbusilogEntity.getProvince());
		currbusilogModel.setFacevalue(cfCurrbusilogEntity.getFacevalue());
		currbusilogModel.setOrderid(cfCurrbusilogEntity.getOrderid());
		currbusilogModel.setCporderno(cfCurrbusilogEntity.getCporderno());
		currbusilogModel.setSystransno(cfCurrbusilogEntity.getSystransno());
		currbusilogModel.setResponsecode(cfCurrbusilogEntity.getResponsecode());
		currbusilogModel.setSpprice(cfCurrbusilogEntity.getSpprice());
		currbusilogModel.setPhone(cfCurrbusilogEntity.getPhone());
		currbusilogModel.setSpno(cfCurrbusilogEntity.getSpno());
		currbusilogModel.setApprice(cfCurrbusilogEntity.getApprice());
		currbusilogModel.setApno(cfCurrbusilogEntity.getApno());
		currbusilogModel.setCreatetime(cfCurrbusilogEntity.getCreatetime());
		currbusilogModel.setUpdatetime(cfCurrbusilogEntity.getUpdatetime());
		currbusilogModel.setStatus(cfCurrbusilogEntity.getStatus());
		currbusilogModel.setPname(cfCurrbusilogEntity.getPname());
		return currbusilogModel;
	}
	/**
	 * Model转Entity(新增)
	 * @param supplierModel
	 * @param type("add"时id自动生成，"edit"时用原有id)
	 * @return
	 */
	private CfCurrbusilogEntity modelToEntity(CurrbusilogModel currbusilogModel){
		CfCurrbusilogEntity cfCurrbusilogEntity = new CfCurrbusilogEntity();
		
		cfCurrbusilogEntity.setBegintime(currbusilogModel.getBegintime());
		cfCurrbusilogEntity.setEndtime(currbusilogModel.getEndtime());
		cfCurrbusilogEntity.setCid(currbusilogModel.getCid());
		cfCurrbusilogEntity.setAcid(currbusilogModel.getAcid());
		cfCurrbusilogEntity.setBizid(currbusilogModel.getBizid());
		cfCurrbusilogEntity.setCname(currbusilogModel.getCname());
		cfCurrbusilogEntity.setAcname(currbusilogModel.getAcname());
		cfCurrbusilogEntity.setIspno(currbusilogModel.getIspno());
		cfCurrbusilogEntity.setIpstype(currbusilogModel.getIpstype());
		cfCurrbusilogEntity.setIpstype(currbusilogModel.getIpstype());
		cfCurrbusilogEntity.setProvince(currbusilogModel.getProvince());
		cfCurrbusilogEntity.setFacevalue(currbusilogModel.getFacevalue());
		cfCurrbusilogEntity.setOrderid(currbusilogModel.getOrderid());
		cfCurrbusilogEntity.setPhone(currbusilogModel.getPhone());
		cfCurrbusilogEntity.setCporderno(currbusilogModel.getCporderno());
		cfCurrbusilogEntity.setSystransno(currbusilogModel.getSystransno());
		cfCurrbusilogEntity.setResponsecode(currbusilogModel.getResponsecode());
		cfCurrbusilogEntity.setSpprice(currbusilogModel.getSpprice());
		cfCurrbusilogEntity.setSpno(currbusilogModel.getSpno());
		cfCurrbusilogEntity.setApprice(currbusilogModel.getApprice());
		cfCurrbusilogEntity.setApno(currbusilogModel.getApno());
		cfCurrbusilogEntity.setCreatetime(currbusilogModel.getCreatetime());
		cfCurrbusilogEntity.setUpdatetime(currbusilogModel.getUpdatetime());
		cfCurrbusilogEntity.setStatus(currbusilogModel.getStatus());
		cfCurrbusilogEntity.setPname( (currbusilogModel.getPname()));
		return cfCurrbusilogEntity;
	}
}
