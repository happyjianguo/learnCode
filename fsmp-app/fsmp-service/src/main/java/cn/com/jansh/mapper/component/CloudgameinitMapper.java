/**
 * ClGameInitService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月11日
 */
package cn.com.jansh.mapper.component;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.component.CloudgameinitEntity;

/**
 * 游戏信息处理层
 * @author Mr.wong
 * @version 1.0
 */
public interface CloudgameinitMapper{
	
	/**
	 * 获取所有游戏信息
	 * @param map
	 * @return
	 */
	public List<CloudgameinitEntity> select(Map<String, Object> map);
	/**
	 * 获取单个游戏信息
	 * @param map
	 * @return
	 */
	public CloudgameinitEntity selectOne(Map<String, Object> map);
}
