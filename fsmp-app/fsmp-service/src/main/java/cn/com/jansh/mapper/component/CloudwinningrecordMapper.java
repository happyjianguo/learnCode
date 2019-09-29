/**
 * CloudwinningrecordMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月25日
 */
package cn.com.jansh.mapper.component;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.component.CloudwinningrecordEntity;
import cn.com.jansh.entity.component.bo.ShowOrderBO;

/**
 * 中奖信息记录表
 * @author duanmuyn
 * @version 1.0
 */
public interface CloudwinningrecordMapper {

	/**
	 * 查询记录显示在页面
	 * 
	 * @param 开始时间 beginTime
	 * @param 结束时间 endTime
	 * @param 派奖状态 sendstatus
	 * @param 奖品类型 prizetype
	 * @param 套餐类型 vrid
	 * @param 活动id gpid
	 * @param 手机号 phone
	 * 
	 * @return 展示订单BO List<ShowOrderBO>
	 */
	public List<ShowOrderBO> selectRecords(Map<String, Object> map);
	
	/**
	 * 查询记录显示在页面
	 * @param 开始时间 beginTime
	 * @param 结束时间 endTime
	 * @param 派奖状态 sendstatus
	 * @param 奖品类型 prizetype
	 * @param 套餐类型 vrid
	 * @param 活动id gpid
	 * @param 手机号 phone
	 * 
	 * @return 数量 int
	 */
	public int selectRecordsCount(Map<String, Object> map);
	/**
	 * 统计充值金额
	 * @param 开始时间 beginTime
	 * @param 结束时间 endTime
	 * @param 派奖状态 sendstatus
	 * @param 奖品类型 prizetype
	 * @param 套餐类型 vrid
	 * @param 活动id gpid
	 * @param 手机号 phone
	 * 
	 * @return 金额 String
	 */
	public String sumPrice(Map<String, Object> map);
	/**
	 * 查询记录
	 * @param cloudwinningrecord
	 * @return
	 */
	public List<CloudwinningrecordEntity> select(CloudwinningrecordEntity cloudwinningrecord);
	
	/**
	 * 多状态条件查询记录
	 * @param list
	 * @return
	 */
	public List<CloudwinningrecordEntity> selectByStatus(@Param("list") List<String> list);
	
	/**
	 * 插入记录
	 * @param cloudwinningrecord
	 * @return
	 */
	public void insert(CloudwinningrecordEntity cloudwinningrecord);
	
	/**
	 * 更新记录
	 * @param cloudwinningrecord
	 */
	public void update(CloudwinningrecordEntity cloudwinningrecord);
	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	public int batchUpdate(List<String> list);
}
