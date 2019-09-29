/**
 * HuodongrewardService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年12月13日
 */
package cn.com.jansh.service.data;

import java.util.List;


import cn.com.jansh.entity.data.HuodongrewardDataEntity;
import cn.com.jansh.entity.system.CloudplatformorginationEntity;

/**
 * 活动获奖情况service
 * @author gll
 * @version 1.0
 */
public interface HuodongrewardService {
	/**
	 * 获得所有机构信息
	 * @return
	 */
	public List<CloudplatformorginationEntity> getOrg();
	/**
	 * 根据活动id调取活动获奖接口
	 * @param gameid
	 * @param endDate 
	 * @param startDate 
	 * @return
	 */
	public List<HuodongrewardDataEntity> gethuodongreward(String gameid, String startDate, String endDate);
}
