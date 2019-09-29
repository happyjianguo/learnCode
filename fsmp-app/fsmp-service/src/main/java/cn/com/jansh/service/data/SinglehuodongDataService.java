/**
 * SinglehuodongDataService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年11月30日
 */
package cn.com.jansh.service.data;

import java.util.List;

import cn.com.jansh.entity.component.CloudgameparamEntity;
import cn.com.jansh.vo.PVUVDataVO;
import cn.com.jansh.vo.SingleActionVO;

/**
 * 活动Service
 * @author gll
 * @version 1.0
 */
public interface SinglehuodongDataService {
	/**
	 * 根据公众号id获取所有活动
	 * @param appid
	 * @param channel 
	 * @return
	 */
	public List<CloudgameparamEntity> getGame(String appid, String channel);

	/**
	 *  ajax通过接口查询PV、UV
	 * @param params
	 * @return
	 */
	public PVUVDataVO getPvUvData(SingleActionVO params);

}
