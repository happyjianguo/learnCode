/**
 * HuodongrewardService.java
 * 版权所有(C) 2017 北京坚石诚信科技有限公司
 * 创建:gll 2017年1月10日
 */
package cn.com.jansh.service.data;

import java.util.List;

import cn.com.jansh.entity.component.HuodongrewardDataEntity;

/**
 * 活动获奖情况service
 * @author gll
 * @version 1.0
 */
public interface HuodongrewardService {

	/**
	 * 查询获奖情况
	 * @param gameid
	 * @return
	 */
	public List<HuodongrewardDataEntity> gethuodongreward(String gameid, String startDate, String endDate);

}
