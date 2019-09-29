package cn.com.jansh.service.system.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import cn.com.jansh.mapper.wsfdn.CfMainMapper;
import cn.com.jansh.service.system.CfMainService;
/**
 * 
 * @author gll
 *
 */
@Service
public class CfMainServiceImpl implements CfMainService {

	@Autowired
//	CfMainMapper cfMainMapper;
	
	private final static Logger logger = LogManager.getLogger(CfMainServiceImpl.class);
	
	@Override
	public String oforderprice() {
		logger.info("获取欧飞订单总金额");
//		return cfMainMapper.queryoforderprice();
		return null;
	}

	@Override
	public String wsorderprice() {
		logger.info("获取网宿订单总金额");
//		return cfMainMapper.querywsorderprice();
		return null;
	}
}
