package cn.com.jansh.mapper.wsfdn;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import cn.com.jansh.entity.wsfdn.CfReportEntity;

/**
 * 报表查询Mapper
 * @author gll
 *
 */
public interface CfReportMapper {

	/**
	 * 定时任务按接入者查询
	 * @param cfCurrbusilogEntity
	 * @return
	 */
	public List<CfReportEntity> acidAndIspnoAndTime(CfReportEntity cfReportEntity);
	/**
	 * 新增
	 */
	public void insert(List<CfReportEntity> cfReportEntity);
	/***
	 * 查询总页数
	 * @param cfReportEntity
	 * @return
	 */
	public String searchLogCount(CfReportEntity cfReportEntity);
	/**
	 * 查询所有报表（不带分页）
	 * @return
	 */
	public List<CfReportEntity> queryall(CfReportEntity cfReportEntity);	
	/**
	 * 查询所有流水
	 * @return
	 */
	public List<CfReportEntity> query(CfReportEntity cfReportEntity,@Param("start")int start,@Param("length")int length);	
}
