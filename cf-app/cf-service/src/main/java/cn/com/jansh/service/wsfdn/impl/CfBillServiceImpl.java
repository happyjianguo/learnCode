package cn.com.jansh.service.wsfdn.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.com.jansh.constant.BusiLogStatusZhCode;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.exception.SysErrorCode;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.ExcelUtil;
import cn.com.jansh.entity.wsfdn.CfCurrbusilogEntity;
import cn.com.jansh.entity.wsfdn.CfOfbillEntity;
import cn.com.jansh.entity.wsfdn.CfWsbillEntity;
import cn.com.jansh.mapper.wsfdn.CfBillMapper;
import cn.com.jansh.mapper.wsfdn.CfCurrbusilogMapper;
import cn.com.jansh.mapper.wsfdn.CfWsBillMapper;
import cn.com.jansh.service.system.GlobalPropertiesService;
import cn.com.jansh.service.wsfdn.CfBillService;

@Service
public class CfBillServiceImpl implements CfBillService {

	private static final Logger logger = LogManager.getLogger(CfBillServiceImpl.class);
	
	@Autowired
	private GlobalPropertiesService globalPropertiesService;
	
	@Autowired
	private CfBillMapper cfBillMapper;
	
	@Autowired
	private CfWsBillMapper cfWsBillMapper;
	
	@Autowired
	private CfCurrbusilogMapper cfCurrbusilogMapper;	
	
	@Override
	public List<CfOfbillEntity> query(CfOfbillEntity cfOfbillEntity) {
		logger.info("欧飞对账查询");
		cfOfbillEntity.setStarttime(DateUtil.formatTimestamp(cfOfbillEntity.getStarttime()));
		cfOfbillEntity.setEndtime(DateUtil.formatTimestamp(DateUtil.dateTimeAdd(DateUtil.getDateTime(DateUtil.parseDate(cfOfbillEntity.getEndtime())), 1)));
		return cfBillMapper.query(cfOfbillEntity);
	}


	@Override
	public List<CfWsbillEntity> querywsBill(CfWsbillEntity cfWsbillEntity) {
		logger.info("网宿对账查询");
		cfWsbillEntity.setStarttime(DateUtil.formatTimestamp(cfWsbillEntity.getStarttime()));
		cfWsbillEntity.setEndtime(DateUtil.formatTimestamp(DateUtil.dateTimeAdd(DateUtil.getDateTime(DateUtil.parseDate(cfWsbillEntity.getEndtime())), 1)));
		return cfWsBillMapper.querywsBill(cfWsbillEntity);
	}


	@Override
	public void uploadExcel(MultipartFile myfile, CfWsbillEntity cfWsbillEntity) throws AppException {
		logger.info("批量上传入库");
		//判断目录是否存在
		String path = globalPropertiesService.getUploadWsBillPath();
		File file=new File(path);
		if(!file.exists()&&!file.isDirectory()){
			file.mkdir();
		}
		try {
			FileUtils.copyInputStreamToFile(myfile.getInputStream(),new File(path,DateUtil.getTimestamp()+".xlsx"));
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.error("网宿对账保存文件错误{}",e1);
			throw new AppException(SysErrorCode.E110005);
		}
		
		try {
			List<List<String>> list = ExcelUtil.readExcel(myfile);
			List<CfWsbillEntity> cfWsbillList = new ArrayList<CfWsbillEntity>();
			int i = 0 ;
			for (List<String> list2 : list) {
				logger.info("第{}行{}",i++,list2.toString());
				if(i==1){
					continue;
				}
				CfWsbillEntity cfWsbill = new CfWsbillEntity();
				String spno = list2.get(0).toString();	
				CfWsbillEntity cfwsBillEntity = cfWsBillMapper.selectByspno(spno);	
				if(cfwsBillEntity!=null){
					continue;
				}else{					
					cfWsbill.setSpno(list2.get(0).toString());//SP订单号
					cfWsbill.setCpno(list2.get(1).toString());//CP流水号
					cfWsbill.setNum(list2.get(2).toString());//商品编号
					cfWsbill.setSize(list2.get(3).toString());//商品数量
					cfWsbill.setPhone(list2.get(4).toString());//充值账号
					cfWsbill.setOprice(list2.get(5).toString());//订单金额						
					cfWsbill.setOtime(list2.get(6).toString());//订单时间	
					String oprice = list2.get(5).toString();
					 //查询对应充值流水表供应商报价												 
					 CfCurrbusilogEntity cfCurrbusilogEntity = cfCurrbusilogMapper.selectByorderid(spno);	
					 if(cfCurrbusilogEntity != null){							 						 
					     String sppr = cfCurrbusilogEntity.getSpprice();
						 //网宿对账金额						 						 
						 if(new BigDecimal(oprice).compareTo(new BigDecimal(sppr)) == 0){
							 cfWsbill.setStatus(BusiLogStatusZhCode.SUCCEEDZH.value());//平帐				 			 
						 }else if(new BigDecimal(oprice).compareTo(new BigDecimal(sppr)) < 0){
							 cfWsbill.setStatus(BusiLogStatusZhCode.LESSZH.value());//订单金额小于供应商报价
						 }else{
							 cfWsbill.setStatus(BusiLogStatusZhCode.MOREZH.value());//订单金额大于供应商报价
						 }						 
				    }else{
					       cfWsbill.setStatus(BusiLogStatusZhCode.UNKNOWZH.value());//对账失败					 
				         }					
					cfWsbillList.add(cfWsbill);	
								
				}
			}
			if(CollectionUtils.isNotEmpty(cfWsbillList)){
				cfWsBillMapper.insertWsbillinfo(cfWsbillList);	
			}

		} catch (IOException e) {
			throw new AppException(SysErrorCode.E110005);
		}
		
	}


	@Override
	public void uploadOk(CfWsbillEntity cfWsbillEntity) {
	
	
	}


	@Override
	public void rollback(CfWsbillEntity cfWsbillEntity) {
		// TODO Auto-generated method stub
		
	}

}
