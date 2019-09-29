/**
 * CloudgameparamMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月27日
 */
package cn.com.jansh.mapper.component;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.component.CloudgameparamEntity;
import cn.com.jansh.entity.component.bo.ShowGameBO;

/**
 * 游戏参数持久层处理接口
 * @author duanmuyn
 * @version 1.0
 */
public interface CloudgameparamMapper {

	public List<CloudgameparamEntity> select(Map<String,Object> map);
	
	public void insert(CloudgameparamEntity entity);
	
	public void update(CloudgameparamEntity entity);
	
	public CloudgameparamEntity selectByGameid(String gameid);
	
	public List<ShowGameBO> selectShowBO(Map<String, Object> map);
	
	public int selectShowBOCount(Map<String, Object> map);
}
