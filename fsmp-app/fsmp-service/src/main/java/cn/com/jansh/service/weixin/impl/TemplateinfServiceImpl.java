package cn.com.jansh.service.weixin.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jansh.comm.util.IDUtils;

import cn.com.jansh.entity.weixin.TemplateinfEntity;
import cn.com.jansh.mapper.weixin.TemplateinfMapper;
import cn.com.jansh.service.weixin.TemplateinfService;

@Service("templateinfService")
public class TemplateinfServiceImpl implements TemplateinfService {
	private static final Logger logger = LogManager.getLogger(TemplateinfServiceImpl.class);
	@Autowired
	private TemplateinfMapper templateinfMapper;
	@Override
	public void insert(TemplateinfEntity templateinf) {
		String tempid=IDUtils.getTimeRandon();
		templateinf.setTempid(tempid);
		templateinfMapper.insert(templateinf);
		logger.info("编号为"+tempid+"模板创建成功");
	}
	@Override
	public List<TemplateinfEntity> query(){
		logger.info("模板查询");
		return templateinfMapper.query();
	}

}
