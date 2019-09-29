package cn.com.jansh.controller.recharge;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

import cn.com.jansh.constant.BusiLogStatusZhCode;
import cn.com.jansh.core.annotation.ExceptionHandle;
import cn.com.jansh.core.annotation.OperationResult;
import cn.com.jansh.core.annotation.SecurityRequest;
import cn.com.jansh.core.constant.Operation;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.ExportExcel;
import cn.com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.entity.wsfdn.CfOfbillEntity;
import cn.com.jansh.entity.wsfdn.CfWsbillEntity;
import cn.com.jansh.model.OfBillModel;
import cn.com.jansh.model.WsBillModel;
import cn.com.jansh.service.wsfdn.CfBillService;
/**
 * 对账管理
 * @author gll
 *
 */
/******************************************************欧飞对账Begin*******************************************************/
@Controller
@RequestMapping(value="/bill")
public class CfBillController {

	private static final Logger logger = LogManager.getLogger(CfBillController.class);
	
	@Autowired
	private CfBillService cfBillService;
	
	@RequestMapping(value = "/init")
	public String billinit(OfBillModel ofBillModel){
		logger.info("欧飞对账初始化{}",ofBillModel.toString());
		ofBillModel.setStarttime(DateUtil.getFirstDay());
		ofBillModel.setEndtime(DateUtil.getDateDay());
		return "recharge/bill/ofbill";
	}
	
	/**
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/ajax/ofbillquery")
	@ResponseBody
	public ViewObject initClient(OfBillModel ofBillModel) throws ParseException{
		logger.info("查询报表{}",ofBillModel.toString());
		List<CfOfbillEntity> cfOfbill= cfBillService.query(modelToEntity(ofBillModel));
		List<CfOfbillEntity> cfof =  new ArrayList<CfOfbillEntity>();
	    for(CfOfbillEntity cf : cfOfbill){
	    	cf.setOtime(DateUtil.getDateTime(DateUtil.parseDateTime2(cf.getOtime())));	    	
	    	cfof.add(cf);
	    }
		return new ViewObject(cfof);	
	}
	
	@RequestMapping(value = "/ofdownloads")
	private ResponseEntity<byte[]> exceldownload(HttpServletRequest request,OfBillModel ofBillModel) throws ParseException{
		logger.info("开始下载报表{}",ofBillModel.toString());
		ExportExcel<CfOfbillEntity> ex = new ExportExcel<CfOfbillEntity>();
		String title ="欧飞对账";
		String[] headers ={ "CP流水号","SP订单号", "商品编号","商品数量","充值账号","平台状态","欧飞状态","接入者","订单金额","订单时间","订单状态" };
		List<CfOfbillEntity> dataset  =cfBillService.query(modelToEntity(ofBillModel));
		int j=dataset.size();
		WebApplicationContext ac = RequestContextUtils.findWebApplicationContext(request);
		for(int i = 0;i<j;i++){
			dataset.get(i).setOtime(DateUtil.getDateTime(DateUtil.parseDateTime2(dataset.get(i).getOtime())));
			int k = Integer.parseInt(dataset.get(i).getStatus());
			ac.getMessage(BusiLogStatusZhCode.SUCCEEDZH.value(), null, RequestContextUtils.getLocale(request));
				if(k==Integer.parseInt(BusiLogStatusZhCode.SUCCEEDZH.value())){
					dataset.get(i).setStatus(ac.getMessage(BusiLogStatusZhCode.SUCCEEDZH.value(), null, RequestContextUtils.getLocale(request)));
				}else if(k==Integer.parseInt(BusiLogStatusZhCode.LESSZH.value())){
					dataset.get(i).setStatus(ac.getMessage(BusiLogStatusZhCode.LESSZH.value(), null, RequestContextUtils.getLocale(request)));
				}else if(k==Integer.parseInt(BusiLogStatusZhCode.MOREZH.value())){
					dataset.get(i).setStatus(ac.getMessage(BusiLogStatusZhCode.MOREZH.value(), null, RequestContextUtils.getLocale(request)));
				}else if(k==Integer.parseInt(BusiLogStatusZhCode.UNKNOWZH.value())){
					dataset.get(i).setStatus(ac.getMessage(BusiLogStatusZhCode.UNKNOWZH.value(), null, RequestContextUtils.getLocale(request)));
				}
			} 
        ByteArrayOutputStream outStream =new ByteArrayOutputStream();
        List<Map<String,Object>> Listmap = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("title", title);
        map.put("headers", headers);
        map.put("dataset", dataset);
        Listmap.add(map);
        HttpHeaders header = new HttpHeaders();    
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=downloadofbill.xlsx");
	    return new ResponseEntity<byte[]>(ex.exportExcel(Listmap, outStream).toByteArray(), header, HttpStatus.OK);
	}
	

	/**
	 * Model转Entity
	 * @param supplierModel
	 * @return
	 */
	private CfOfbillEntity modelToEntity(OfBillModel ofBillModel){
		CfOfbillEntity cfOfbillEntity = new CfOfbillEntity();
		
		cfOfbillEntity.setStarttime(ofBillModel.getStarttime());
		cfOfbillEntity.setEndtime(ofBillModel.getEndtime());
		cfOfbillEntity.setAcname(ofBillModel.getAcname());
		cfOfbillEntity.setCpno(ofBillModel.getCpno());
		cfOfbillEntity.setNum(ofBillModel.getNum());
		cfOfbillEntity.setOfstatus(ofBillModel.getOfstatus());
		cfOfbillEntity.setOprice(ofBillModel.getOprice());
		cfOfbillEntity.setOtime(ofBillModel.getOtime());
		cfOfbillEntity.setPhone(ofBillModel.getPhone());
		cfOfbillEntity.setPlatstatus(ofBillModel.getPlatstatus());
		cfOfbillEntity.setSize(ofBillModel.getSize());
		cfOfbillEntity.setSpno(ofBillModel.getSpno());
		cfOfbillEntity.setStatus(ofBillModel.getStatus());
		return cfOfbillEntity;
	}
	/******************************************************欧飞对账End*******************************************************/
	
	
	
	/******************************************************网宿对账Begin*****************************************************/
	@RequestMapping(value = "/wsbill")
	public String wsbillinit(WsBillModel wsBillModel){
		logger.info("网宿对账初始化{}",wsBillModel.toString());
		wsBillModel.setStarttime(DateUtil.getFirstDay());
		wsBillModel.setEndtime(DateUtil.getDateDay());
		return "recharge/bill/wsbill";
	}
	/**
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/ajax/wsbillquery")
	@ResponseBody
	public ViewObject initwsBill(WsBillModel wsBillModel) throws ParseException{
		logger.info("查询报表{}",wsBillModel.toString());
		List<CfWsbillEntity> dataset  =cfBillService.querywsBill(modelToEntity01(wsBillModel));
		List<CfWsbillEntity> cfws =  new ArrayList<CfWsbillEntity>();
		 for(CfWsbillEntity cf : dataset){
			   	cf.setOtime(DateUtil.getDateTime(DateUtil.parseDateTime2(cf.getOtime())));		    
		    	cfws.add(cf);
		    }
		return new ViewObject(cfws);	
	}
	
	@RequestMapping(value = "/wsdownloads")
	private ResponseEntity<byte[]> exceldownloads(HttpServletRequest request,WsBillModel wsBillModel) throws ParseException{
		logger.info("开始下载报表{}",wsBillModel.toString());
		ExportExcel<CfWsbillEntity> ex = new ExportExcel<CfWsbillEntity>();
		String title ="网宿对账";
		String[] headers ={ "CP流水号","SP订单号", "商品编号","商品数量","充值账号","平台状态","欧飞状态","接入者","订单金额","订单时间","订单状态" };
		List<CfWsbillEntity> dataset  =cfBillService.querywsBill(modelToEntity01(wsBillModel));
		int j=dataset.size();
		WebApplicationContext ac = RequestContextUtils.findWebApplicationContext(request);
		for(int i = 0;i<j;i++){
			dataset.get(i).setOtime(DateUtil.getDateTime(DateUtil.parseDateTime2(dataset.get(i).getOtime())));
			int k = Integer.parseInt(dataset.get(i).getStatus());
			ac.getMessage(BusiLogStatusZhCode.SUCCEEDZH.value(), null, RequestContextUtils.getLocale(request));
			if(k==Integer.parseInt(BusiLogStatusZhCode.SUCCEEDZH.value())){
				dataset.get(i).setStatus(ac.getMessage(BusiLogStatusZhCode.SUCCEEDZH.value(), null, RequestContextUtils.getLocale(request)));
			}else if(k==Integer.parseInt(BusiLogStatusZhCode.LESSZH.value())){
				dataset.get(i).setStatus(ac.getMessage(BusiLogStatusZhCode.LESSZH.value(), null, RequestContextUtils.getLocale(request)));
			}else if(k==Integer.parseInt(BusiLogStatusZhCode.MOREZH.value())){
				dataset.get(i).setStatus(ac.getMessage(BusiLogStatusZhCode.MOREZH.value(), null, RequestContextUtils.getLocale(request)));
			}else if(k==Integer.parseInt(BusiLogStatusZhCode.UNKNOWZH.value())){
				dataset.get(i).setStatus(ac.getMessage(BusiLogStatusZhCode.UNKNOWZH.value(), null, RequestContextUtils.getLocale(request)));
			}
		} 
        ByteArrayOutputStream outStream =new ByteArrayOutputStream();
        List<Map<String,Object>> Listmap = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("title", title);
        map.put("headers", headers);
        map.put("dataset", dataset);
        Listmap.add(map);
        HttpHeaders header = new HttpHeaders();    
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=downloadwsbill.xlsx");
	    return new ResponseEntity<byte[]>(ex.exportExcel(Listmap, outStream).toByteArray(), header, HttpStatus.OK);
	}
	
	/**
	 * Model转Entity
	 * @param supplierModel
	 * @return
	 */
	private CfWsbillEntity modelToEntity01(WsBillModel wsBillModel){
		CfWsbillEntity cfWsbillEntity = new CfWsbillEntity();
		cfWsbillEntity.setStarttime(wsBillModel.getStarttime());
		cfWsbillEntity.setEndtime(wsBillModel.getEndtime());
		cfWsbillEntity.setAcname(wsBillModel.getAcname());
		cfWsbillEntity.setCpno(wsBillModel.getCpno());
		cfWsbillEntity.setNum(wsBillModel.getNum());
		cfWsbillEntity.setWsstatus(wsBillModel.getWsstatus());
		cfWsbillEntity.setOprice(wsBillModel.getOprice());
		cfWsbillEntity.setOtime(wsBillModel.getOtime());
		cfWsbillEntity.setPhone(wsBillModel.getPhone());
		cfWsbillEntity.setPlatstatus(wsBillModel.getPlatstatus());
		cfWsbillEntity.setSize(wsBillModel.getSize());
		cfWsbillEntity.setSpno(wsBillModel.getSpno());
		cfWsbillEntity.setStatus(wsBillModel.getStatus());
		return cfWsbillEntity;
	}

	/**
	 * 上传数据
	 * @param joinmodel
	 * @return
	 * @throws AppException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/upload")
	@ExceptionHandle("/bill/init")
	@OperationResult(value=Operation.CREATE)
	@SecurityRequest(repeatRequest=true)
	public String upload(WsBillModel wsBillModel,@RequestParam MultipartFile myfile) throws AppException {
		logger.info("myfile.getSize()：" + myfile.getSize());
		cfBillService.uploadExcel(myfile,modelToEntity(wsBillModel));
			return wsbillinit(wsBillModel);

	}	
	/**
	 * Model转Entity
	 * @param batchinfoModel
	 * @return
	 */
	private CfWsbillEntity modelToEntity(WsBillModel wsBillModel){
		CfWsbillEntity cfWsbillEntity = new CfWsbillEntity();
		cfWsbillEntity.setCpno(wsBillModel.getCpno());
		cfWsbillEntity.setSpno(wsBillModel.getSpno());
		cfWsbillEntity.setNum(wsBillModel.getNum());
		cfWsbillEntity.setStatus(wsBillModel.getStatus());
		cfWsbillEntity.setSize(wsBillModel.getSize());
		cfWsbillEntity.setPhone(wsBillModel.getPhone());
		cfWsbillEntity.setOprice(wsBillModel.getOprice());
		cfWsbillEntity.setOtime(wsBillModel.getOtime());
		return cfWsbillEntity;
	}	
	
	/******************************************************网宿对账End*******************************************************/	 
}
