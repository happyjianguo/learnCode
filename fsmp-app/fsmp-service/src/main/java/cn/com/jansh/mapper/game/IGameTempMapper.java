/**
 * IGameTempMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年12月27日
 */
package cn.com.jansh.mapper.game;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.component.GametemplateEntity;
import cn.com.jansh.entity.component.bo.ShowTemplateBO;

/**
 * 游戏模板mapper
 * @author Mr.wong
 * @version 1.0
 */
public interface IGameTempMapper {
	/**
	 * 获取全部游戏模板信息
	 * @return
	 */
	public List<GametemplateEntity> select(Map<String,Object> map);
	/**
	 * 获取全部游戏模板信息
	 * @return
	 */
	public List<ShowTemplateBO> selectTempBOs(Map<String,Object> map);
}
