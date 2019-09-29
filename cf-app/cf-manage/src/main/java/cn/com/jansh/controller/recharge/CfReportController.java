package cn.com.jansh.controller.recharge;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.ExportExcel;
import cn.com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.entity.wsfdn.CfReportEntity;
import cn.com.jansh.model.DatatablesViewPage;
import cn.com.jansh.model.PriceModel;
import cn.com.jansh.model.ReportModel;
import cn.com.jansh.service.wsfdn.CfAccessclientService;
import cn.com.jansh.service.wsfdn.CfCustomerService;
import cn.com.jansh.service.wsfdn.CfReportService;
/**
 * 报表管理
 * @author gll
 *
 */
@Controller
@RequestMapping(value = "/reportmanage")
public class CfReportController {
	
	private static final Logger logger = LogManager.getLogger(CfReportController.class);
	
	@Autowired
	private CfCustomerService cfCustomerService;
	
	@Autowired
	private CfAccessclientService cfAccessclientService;
	
	@Autowired
	private CfReportService cfReportService;
	
	ObjectMapper mapper = new ObjectMapper();	
	/**
	 * 报表管理初始化
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init(ReportModel reportModel){
		logger.info("查询条件初始化");
		reportModel.setCfCustomerList(cfCustomerService.queryCustomer());
		reportModel.setBegintime(DateUtil.getFirstDay());
		reportModel.setEndtime(DateUtil.getDateDay());
		return "/recharge/reportmanage/reportmanage";
	}
	/**
	 * 接入者初始化
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/ajax/initClient")
	@ResponseBody
	public ViewObject initClient(String cid){
		logger.info("接入者数据初始化{}",cid);
		return new ViewObject(cfAccessclientService.queryAccessclientByCustormerId(cid));	
	}
	
	
	/**
	 * 进入查询
	 * @param reportModel
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "ajax/query")
	@ResponseBody
	public ViewObject findByMessage(ReportModel reportModel) throws ParseException{				
		logger.info("开始报表查询{},{},{}",reportModel.getStart(),reportModel.getLength(),reportModel.toString());		
		reportModel.setCount(cfReportService.searchLogCount(modelToEntity(reportModel)));			
		List<ReportModel> model = new LinkedList<ReportModel>();
		DatatablesViewPage<ReportModel> view = new DatatablesViewPage<ReportModel>(); 
		int leng = Integer.parseInt(reportModel.getCount());
	    view.setiTotalDisplayRecords(leng);  
	    view.setiTotalRecords(leng);  
		BigDecimal a1=new BigDecimal("0");
		BigDecimal a2=new BigDecimal("0"); 
		BigDecimal a3=new BigDecimal("0");
	   List<CfReportEntity> cfReport = cfReportService.queryReport(modelToEntity(reportModel),reportModel.getStart(),reportModel.getLength());	 
	    for(CfReportEntity cf : cfReport){    		    				
			model.add(EntityToModel(cf));
			a1 = a1.add(new BigDecimal(cf.getTotalspprice()));
			a2 = a2.add(new BigDecimal(cf.getTotalapprice()));
			a3 = a3.add(new BigDecimal(cf.getProfit()));
	    }
	    view.setAaData(model);
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("a1", a1.toString());
		map.put("a2", a2.toString());
		map.put("a3", a3.toString());
	    map.put("view", view);
	    String result = "";
	    try {
	    	result = mapper.writeValueAsString(map);
		} catch (Exception	 e) {
			logger.error("报表查询失败{}",e);
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
	private ResponseEntity<byte[]> exceldownload(ReportModel reportModel) throws IOException, ParseException {
		logger.info("开始下载报表{}",reportModel.toString());
		ExportExcel<CfReportEntity> ex = new ExportExcel<CfReportEntity>();
		String title ="报表查询";
		String title1 ="报表统计";
		String[] headers ={ "日期","客户名称", "接入者名称","运营商编号","成本额","销售额","利润" };
		String[] headers1 ={"总成本额","总销售额","总利润" };
		List<CfReportEntity> dataset = new ArrayList<CfReportEntity>();
		List<PriceModel> dataset1 = new ArrayList<PriceModel>();
		dataset =(List<CfReportEntity>) cfReportService.queryAllReport(modelToEntity(reportModel));
		BigDecimal a1=new BigDecimal("0");
		BigDecimal a2=new BigDecimal("0"); 
		BigDecimal a3=new BigDecimal("0");
		for(CfReportEntity cf : dataset){
//		    	cf.setQuerytime(DateUtil.getDateTime(DateUtil.parseDateTime2(cf.getQuerytime())));	    		    								
				a1 = a1.add(new BigDecimal(cf.getTotalspprice()));
				a2 = a2.add(new BigDecimal(cf.getTotalapprice()));
				a3 = a3.add(new BigDecimal(cf.getProfit()));
		    }		
		PriceModel priceModel = new PriceModel();
		priceModel.setAllapprice(a1.toString());
		priceModel.setAllspprice(a2.toString());
		priceModel.setAllprofit(a3.toString());
		dataset1.add(priceModel);
		int j=dataset.size();
		for(int i = 0;i<j;i++){
			String ispno = dataset.get(i).getIspno();
				if(AppCommonsCode.CHINA_UNICOM.value().equals(ispno)){
					dataset.get(i).setIspno("联通");
				}else if(AppCommonsCode.CHINA_MOBILE_COMMUNICATIONS_CORPORATION.value().equals(ispno)){
					dataset.get(i).setIspno("移动");
				}else if(AppCommonsCode.CHINA_TELICOM.value().equals(ispno)){
					dataset.get(i).setIspno("电信");
				}else{
					dataset.get(i).setIspno("-----------");
				}
			}
        ByteArrayOutputStream outStream =new ByteArrayOutputStream();
        List<Map<String,Object>> Listmap = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("title", title);
        map.put("headers", headers);
        map.put("dataset", dataset);
        Listmap.add(map);
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("title", title1);
        map1.put("headers", headers1);
        map1.put("dataset", dataset1);
        Listmap.add(map1);
        
        HttpHeaders header = new HttpHeaders();    
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=downloadreport.xlsx");
	    return new ResponseEntity<byte[]>(ex.exportExcel(Listmap, outStream).toByteArray(), header, HttpStatus.OK); 
	
	}
	/**
	 * Model转Entity(新增)
	 * @param supplierModel
	 * @return
	 */
	private CfReportEntity modelToEntity(ReportModel reportModel){
		CfReportEntity cfReportEntity = new CfReportEntity();
		
		cfReportEntity.setBegintime(reportModel.getBegintime());
		cfReportEntity.setEndtime(reportModel.getEndtime());
		cfReportEntity.setCid(reportModel.getCid());
		cfReportEntity.setAcid(reportModel.getAcid());
		cfReportEntity.setCname(reportModel.getCname());
		cfReportEntity.setAcname(reportModel.getAcname());
		cfReportEntity.setIspno(reportModel.getIspno());
		cfReportEntity.setCreatetime(reportModel.getCreatetime());
		cfReportEntity.setUpdatetime(reportModel.getUpdatetime());
		return cfReportEntity;
	}
	/**
	 * Entity转Model(新增)
	 * @param supplierModel
	 * @param type("add"时id自动生成，"edit"时用原有id)
	 * @return
	 */
	private ReportModel EntityToModel(CfReportEntity cfReportEntity){
		ReportModel reportModel = new ReportModel();
		reportModel.setQuerytime(cfReportEntity.getQuerytime());
		reportModel.setCname(cfReportEntity.getCname());	
		reportModel.setAcname(cfReportEntity.getAcname());
		reportModel.setIspno(cfReportEntity.getIspno());
		reportModel.setTotalapprice(cfReportEntity.getTotalapprice());
		reportModel.setTotalspprice(cfReportEntity.getTotalspprice());
		reportModel.setProfit(cfReportEntity.getProfit());
		return reportModel;
	}	
}
