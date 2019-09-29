package cn.com.jansh.service.risk.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.ContextCode;
import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.IDUtils;

import cn.com.jansh.entity.risk.BlacklistEntity;
import cn.com.jansh.mapper.risk.BlackMapper;
import cn.com.jansh.service.risk.BlackService;
import cn.com.jansh.util.DateUtils;

@Service
public class BlackServiceImpl implements BlackService {

	private static final Logger logger = LogManager.getLogger(BlackServiceImpl.class);
	
	@Autowired
	public BlackMapper blackMapper;
	
	/**
	 * 初始化查询黑名单
	 */
	@Override
	public List<BlacklistEntity> selectByOne(BlacklistEntity blacklistEntity) {
		logger.info("初始化查看所有黑名单{}",blacklistEntity);
		List<BlacklistEntity> list = blackMapper.selectByOne(blacklistEntity);
		for(int i=0;i<list.size();i++){
			//将yyyyMMddHHmmss转换成yyyy-MM-dd HH:mm:ss用于显示
			list.get(i).setOvertime(DateUtil.formatDateTimestamp(list.get(i).getOvertime()));
		}
		return list;
	}

	/**
	 * 根据黑名单值和活动查询黑名单
	 */
	@Override
	public BlacklistEntity selectBlackByValueAndAction(BlacklistEntity blackEntity) {
		logger.info("根据黑名单值和活动查询黑名单{}",blackEntity);
		Map<String,String> map = new HashMap<String,String>();
		map.put("actionid", blackEntity.getActionid());
		map.put("blackvalue", blackEntity.getBlackvalue());
		return blackMapper.selectBlackByValueAndAction(map);
	}

	/**
	 * 插入黑名单数据
	 * @throws ParseException 
	 */
	@Override
	public void insertBlack(BlacklistEntity blackEntity) throws ParseException {
		logger.info("新增黑名单{}",blackEntity);
		blackEntity.setBlackid(IDUtils.getTimeRandon());
		//黑名单状态置为1，生效
		blackEntity.setStatus(ContextCode.BLACKLIST_STATUS_SHENGXIAO.value());
		//1、将yyyy－MM－dd转换为yyyyMMddHHmmss用于存库
		blackEntity.setOvertime(DateUtils.getStringToday(DateUtils.strToDateLong3(blackEntity.getOvertime())));
		blackMapper.insertBlack(blackEntity);
	}

	/**
	 * 通过黑名单ID查询黑名单
	 * @throws ParseException 
	 */
	@Override
	public BlacklistEntity selectBlackByBlackId(String blackid) throws ParseException {
		logger.info("通过黑名单ID查询黑名单{}",blackid);
		Map<String,String> map = new HashMap<String,String>();
		map.put("blackid", blackid);
		BlacklistEntity blacklistEntity = blackMapper.selectBlackByBlackId(map);
		// 将yyyyMMddHHmmss转为yyyy-MM-dd
		blacklistEntity.setOvertime(DateUtils.parseShortDate(DateUtils.strToDateLong2(blacklistEntity.getOvertime())));
		return blacklistEntity;
	}

	/**
	 * 修改数据
	 * @throws ParseException 
	 */
	@Override
	public void updateblack(BlacklistEntity blackEntity) throws ParseException {
		logger.info("修改数据{}",blackEntity);
		//1、将yyyy－MM－dd转换为yyyyMMddHHmmss用于存库
		blackEntity.setOvertime(DateUtils.getStringToday(DateUtils.strToDateLong3(blackEntity.getOvertime())));
		blackMapper.updateblack(blackEntity);
	}

	/**
	 * 删除数据
	 */
	@Override
	public void deleteblack(String blackid) {
		logger.info("删除数据{}",blackid);
		Map<String,String> map = new HashMap<String,String>();
		map.put("blackid", blackid);
		blackMapper.deleteblack(map);
	}
}
