package cn.com.jansh.service.scheduler.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.com.jansh.constant.BusiLogStatusZhCode;
import cn.com.jansh.entity.wsfdn.CfCurrbusilogEntity;
import cn.com.jansh.entity.wsfdn.CfOfbillEntity;
import cn.com.jansh.mapper.wsfdn.CfCurrbusilogMapper;
import cn.com.jansh.mapper.wsfdn.CfOfbillMapper;
import cn.com.jansh.service.component.OuFeiComponent;
import cn.com.jansh.service.scheduler.CfBillOrderTaskService;
@Service
public class CfBillOrderTaskServiceImpl implements CfBillOrderTaskService{

	private static final Logger logger = LogManager.getLogger(CfBillOrderTaskServiceImpl.class);
	@Autowired
	private  OuFeiComponent ouFeiComponent;

	@Autowired
	private CfCurrbusilogMapper cfCurrbusilogMapper;
	@Autowired
	private CfOfbillMapper cfOfbillMapper;
	
	/**
	 * 解析欧非对账信息并存对账信息表
	 * @throws Exception 
	 */
	public void billOrder() {
	
		String result = "";
		try {
			result = ouFeiComponent.billOrder();
//模拟返回的result			
//			result ="CP流水号|SP订单号|商品编号|商品数量|充值账号|订单金额|订单时间|订单状态\r\nS0703230888|2|360101|1|12|10|20160518144054|1\r\n070323027|4|360101|1|12|9.7|20160518175157|1\r\nS0703230800|1|360101|1|12|10|20160518144054|1\r\n订单总数:932|订单总额:123123.222";					 							 
			if(StringUtils.isNotBlank(result)){
				try {
					//拆分字符串
				    String[] resultArray =result.split("\r\n");
					for(int i=1;i<resultArray.length-1;i++){
						String[] resultAr = resultArray[i].split("\\|");
						Map<String, String> map = new HashMap<String, String>();					
						CfOfbillEntity cfOfbillEntity = new CfOfbillEntity();
					    String[] key ={"cpno","spno","num","size","phone","oprice","otime","status"};
						 for(int j=0;j<resultAr.length;j++){
							 //存放键值对
							map.put(key[j], resultAr[j]);								
						 } 
						 cfOfbillEntity.setCpno(map.get("cpno"));
						 cfOfbillEntity.setSpno(map.get("spno"));
						 cfOfbillEntity.setNum(map.get("num"));
						 cfOfbillEntity.setSize(map.get("size"));
						 cfOfbillEntity.setPhone(map.get("phone"));
						 cfOfbillEntity.setOprice(map.get("oprice"));
						 cfOfbillEntity.setOtime(map.get("otime"));
						 cfOfbillEntity.setStatus(map.get("status"));							 
						 //根据流水号和订单号查欧非对账表
						 CfOfbillEntity cfOfBillEntity = cfOfbillMapper.selectBySpno(map.get("spno"));
						 if(cfOfBillEntity !=null){
							 continue;
						 }
						 //查询充值流水表供应商报价												 
						 CfCurrbusilogEntity cfCurrbusilogEntity = cfCurrbusilogMapper.selectByorderid(map.get("spno"));	
						 if(cfCurrbusilogEntity != null){													   							  
							 //欧非对账金额						 						 
							 if(new BigDecimal(map.get("oprice")).compareTo(new BigDecimal(cfCurrbusilogEntity.getSpprice()))== 0){
								 cfOfbillEntity.setStatus(BusiLogStatusZhCode.SUCCEEDZH.value()); //平账						 
								 cfOfbillMapper.insert(cfOfbillEntity);							 
							 }else if(new BigDecimal(map.get("oprice")).compareTo(new BigDecimal(cfCurrbusilogEntity.getSpprice())) < 0){
								 cfOfbillEntity.setStatus(BusiLogStatusZhCode.LESSZH.value());//订单金额小于供应商报价
								 cfOfbillMapper.insert(cfOfbillEntity);
							 }else{
								 cfOfbillEntity.setStatus(BusiLogStatusZhCode.MOREZH.value());//订单金额大于供应商报价
								 cfOfbillMapper.insert(cfOfbillEntity);
							 }							  
						 }else{
						 cfOfbillEntity.setStatus(BusiLogStatusZhCode.UNKNOWZH.value());//对账失败
						 cfOfbillMapper.insert(cfOfbillEntity);						 
					 }
				}			 
				
			} catch (Exception e) {
					logger.error("解析返回结果异常：{}", e);
				}
			}			
		} catch (Exception e) {
			logger.error("返回结果异常：{}", e);			
		}		
		logger.info("欧非对账返回结果：{}", result);
		}


}