/**
 * FsGetPackageInformationServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月20日
 */
package cn.com.jansh.service.scheduler.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jansh.comm.util.DateUtil;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.entity.component.CloudvirgoodEntity;
import cn.com.jansh.mapper.component.CloudvirgoodMapper;
import cn.com.jansh.service.component.CfInterfaceService;
import cn.com.jansh.service.scheduler.FsGetPackageInformationService;
import cn.com.jansh.utils.RedisCacheUtil;

/**
 * 获取充值平台套餐信息实现类
 * @author duanmuyn
 * @version 1.0
 */
@Service
public class FsGetPackageInformationServiceImpl implements FsGetPackageInformationService{

	private static final Logger logger = LogManager.getLogger(FsGetPackageInformationServiceImpl.class);
	
	@Autowired
	private CfInterfaceService cfInterfaceService;
	
	@Autowired
	private RedisCacheUtil<Object> redisCache;
	
	@Autowired
	private CloudvirgoodMapper cloudvirgoodMapper;
	
	@Override
	public void action() {
		logger.info("定时获取接入者套餐报价");
		ObjectMapper obj = new ObjectMapper();
		List<CloudvirgoodEntity> list = cfInterfaceService.queryPack();
		properPrizeName(list);
		logger.info("获取mysql数据库中已存在的套餐");
		List<CloudvirgoodEntity> listok =  cloudvirgoodMapper.selectStatusOk();
		
		logger.info("mysql中待更新为关闭状态的套餐集合");
		List<CloudvirgoodEntity> listupdata = new ArrayList<CloudvirgoodEntity>();
		
		Iterator<CloudvirgoodEntity> iteratoren = listok.iterator();
		while(iteratoren.hasNext()){
			CloudvirgoodEntity en = iteratoren.next();
			boolean isequ = true;
			
			Iterator<CloudvirgoodEntity> iteratornen = list.iterator();
			while(iteratornen.hasNext()){
				CloudvirgoodEntity nen = iteratornen.next();
				if(en.getApid().equals(nen.getApid())){
					isequ=false;
					if(en.getPrizestyle().equals(nen.getPrizestyle())
							&en.getCarrier().equals(nen.getCarrier())
							&en.getCombovalue().equals(nen.getCombovalue())
							&en.getPervalue().equals(nen.getPervalue())
							&en.getPrizeprice()==nen.getPrizeprice()
					){
						//如果有套餐相同的套餐，从list中删除，剩余套餐等待新增
						iteratornen.remove();
					}else{
						//已存在并变化的套餐加入更新状态集合
						listupdata.add(en);
					}
				}
			}
			if(isequ){
				//不存在的套餐加入更新状态集合
				listupdata.add(en);
			}
		}
		logger.info("更新的套餐数量："+listupdata.size());
		for(CloudvirgoodEntity en : listupdata){
			//更新套餐状态为关闭
			en.setStatus(AppCommonsCode.RECHARGE_PACKAGE_STATUS_GB.value());
			en.setUpdatetime(DateUtil.getDateTimestamp());
			cloudvirgoodMapper.update(en);
		}
		logger.info("新增的套餐数量："+list.size());
		if(!list.isEmpty()){
			//检查是否有新增套餐，进行新增
			cloudvirgoodMapper.insertList(list);
		}
		
		logger.info("------------redis begin");
		List<CloudvirgoodEntity> listnew =  cloudvirgoodMapper.selectStatusOk();
		try {
			String ss = obj.writeValueAsString(listnew);
			redisCache.setCacheObject("apid", ss);
		} catch (JsonProcessingException e1) {
			logger.error("*************redis error{}",e1);
		}
		
		for(int i=0;i<10;i++){
			logger.debug("------------get redis value"+redisCache.getCacheObject("apid"));
		}
		logger.info("------------redis end");
	}
	private void properPrizeName(List<CloudvirgoodEntity> list){
		for (CloudvirgoodEntity en : list) {
			StringBuilder	builder = new StringBuilder();
		    if(AppCommonsCode.IPSTYPE_BILL.value().equals(en.getPrizestyle())){
		    	builder.append("话费-");
		    }else if(AppCommonsCode.IPSTYPE_Flow.value().equals(en.getPrizestyle())){
		    	builder.append("流量-");
		    }
		    if(AppCommonsCode.CHINA_UNICOM.value().equals(en.getCarrier())){
		    	builder.append("联通-");
		    }else if(AppCommonsCode.CHINA_MOBILE_COMMUNICATIONS_CORPORATION.value().equals(en.getCarrier())){
		    	builder.append("移动-");
		    }else if(AppCommonsCode.CHINA_TELICOM.value().equals(en.getCarrier())){
		    	builder.append("电信-");
		    }
		    builder.append(en.getCombovalue());
		    if(AppCommonsCode.IPSTYPE_BILL.value().equals(en.getPrizestyle())){
		    	builder.append("元");
		    }else if(AppCommonsCode.IPSTYPE_Flow.value().equals(en.getPrizestyle())){
		    	builder.append("M");
		    }
		    en.setPrizename(builder.toString());
		}
	}
}
