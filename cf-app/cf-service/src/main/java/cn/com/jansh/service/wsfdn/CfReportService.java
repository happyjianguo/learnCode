package cn.com.jansh.service.wsfdn;

import java.text.ParseException;
import java.util.List;
import cn.com.jansh.entity.wsfdn.CfReportEntity;
/**
 * 报表管理service
 * @author gll
 *
 */
public interface CfReportService {
	
	/**
	 * 报表查询
	 * @param cfReportEntity
	 * @return
	 * @throws ParseException 
	 */
	public  List<CfReportEntity> queryReport(CfReportEntity cfReportEntity,String start,String length);
	/**
	 * 查询总页数
	 * 
	 * @param cfReportModel
	 * @return
	 */
	public String searchLogCount(CfReportEntity cfReportEntity);

	/**
	 * 查询数据库中的所有报表记录（不带分页）
	 */
	public List<CfReportEntity> queryAllReport(CfReportEntity cfReportEntity);	
}
