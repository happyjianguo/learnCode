package cn.com.jansh.service.scheduler.impl;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.BusiLogStatus;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.IDUtils;
import cn.com.jansh.entity.wsfdn.CfReportEntity;
import cn.com.jansh.mapper.wsfdn.CfReportMapper;
import cn.com.jansh.service.scheduler.CfReportOrderTaskService;

@Service
public class CfReportOrderTaskServiceImpl implements CfReportOrderTaskService {
	//private static final Logger logger = LogManager.getLogger(CfReportOrderTaskServiceImpl.class);
	@Autowired
	private CfReportMapper cfReportMapper;
	
	@Override	
	public void queryReportByAcidIspno() {	
		/**查询时间段为昨天一天 */
		String begintime = DateUtil.getDateTime2(DateUtil.parseDateTime(DateUtil.addDate(DateUtil.getDateDay(), -1)+ " 00:00:00"));	
		String endtime = DateUtil.getDateTime2(DateUtil.parseDateTime(DateUtil.addDate(DateUtil.getDateDay(), -1)+" 23:59:59"));
		CfReportEntity currbusilog = new CfReportEntity();
		currbusilog.setBegintime(begintime);
		currbusilog.setEndtime(endtime);
		currbusilog.setStatus(BusiLogStatus.SUCCEED.value());
       		/**按时间查询 */
			List<CfReportEntity> currbusilog1 = cfReportMapper.acidAndIspnoAndTime(currbusilog);	
			if(CollectionUtils.isNotEmpty(currbusilog1)){
				for(CfReportEntity cf : currbusilog1){
					cf.setId(IDUtils.getTimeRandon());
					cf.setCreatetime(DateUtil.getDateTime());
					cf.setUpdatetime(DateUtil.getDateTime());
				}
			      cfReportMapper.insert(currbusilog1);	
			}
	}

}
