package cn.com.jansh.mapper.wsfdn;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.wsfdn.CfAccessclientEntity;

/**
 * 接入者Mapper
 * @author gll
 *
 */
public interface CfAccessclientMapper {
	
	/**
	 * 初始化查询接入者
	 * @return
	 */
	public List<CfAccessclientEntity> query();
	/**
	 * 查询接入者
	 * @return
	 */
	public List<CfAccessclientEntity> queryby(CfAccessclientEntity cfAccessclientEntity);	
	
	/**
	 * 通过id查询接入者
	 * @param id
	 * @return
	 */
	public CfAccessclientEntity selectByid(@Param("id")String id);
	/**
	 * 通过id查询接入者
	 * @param id
	 * @return
	 */
	public List<CfAccessclientEntity> queryAccessclientByCustormerId(@Param("id")String id);
	/**
	 * 通过id和状态查询接入者
	 * @param id
	 * @return
	 */
	public List<CfAccessclientEntity> queryAccessclientByCustormerIdAndSta(@Param("id")String id,@Param("status")String status);	
	/**
	 * 批量更新接入者状态
	 * @param cfBatchinfoEntity
	 */
	public void batchUpdate( Map<String, Object> map);
	
	/**
	 * 通过时间查询接入者
	 * @param time
	 * @return
	 */
	public List<CfAccessclientEntity> queryAccessclientByTime(@Param("status")String status,@Param("time")String time);
	/**
	 * 新增接入者
	 * @param cfAccessclientEntity
	 */
	public void insert(CfAccessclientEntity cfAccessclientEntity);
	
	/**
	 * 修改接入者
	 * @param cfAccessclientEntity
	 */
	public void update(CfAccessclientEntity cfAccessclientEntity);

	/**
	 * 删除接入者
	 * @param id
	 */
	public void delete(@Param("id")String id);

	/**
	 * 查询接入者
	 * @param acname
	 * @return
	 */
	public List<CfAccessclientEntity> selectByName(@Param("acname")String acname);

	/**
	 * 增加消费
	 * @param price
	 * @param acid
	 */
	public int countCumulative(@Param("price")String price,@Param("acid")String acid);

	/**
	 * 减少消费
	 * @param price
	 * @param acid
	 */
	public void minusCumulative(@Param("price")String price,@Param("acid")String acid);
	
}
