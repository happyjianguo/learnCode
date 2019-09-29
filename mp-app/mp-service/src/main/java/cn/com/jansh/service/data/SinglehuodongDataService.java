/**
 * SinglehuodongDataService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年11月30日
 */
package cn.com.jansh.service.data;

import java.util.List;

import cn.com.jansh.entity.data.AuthAccount;
import cn.com.jansh.entity.game.CloudgameparamEntity;
import cn.com.jansh.entity.system.CloudplatformorginationEntity;
import cn.com.jansh.vo.PVUVDataVO;
import cn.com.jansh.vo.SingleActionVO;

/**
 * 活动Service
 * @author gll
 * @version 1.0
 */
public interface SinglehuodongDataService {
	/**
	 * 根据机构id获取公众号信息
	 * @param orgid 
	 * @return
	 */
	public List<AuthAccount> getAccount(String orgid);
	/**
	 * 获得所有机构信息
	 * @return
	 */
	public List<CloudplatformorginationEntity> getOrg();
	/**
	 * 根据公众号id获取所有活动
	 * @param appid
	 * @return
	 */
	public List<CloudgameparamEntity> getGame(String appid);
	/**
	 * 根据机构id获取所有活动
	 * @param orgid
	 * @return
	 */
	public List<CloudgameparamEntity> getAppGame(String orgid);
	/**
	 *  ajax通过接口查询PV、UV
	 * @param params
	 * @return
	 */
	public PVUVDataVO getPvUvData(SingleActionVO params);
}
