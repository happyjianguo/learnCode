package cn.com.jansh.service.scheduler;

public interface CfRefreshStatusService {

	/**
	 * 定时刷新供应商报价及接入者状态
	 * (不在开始-结束时间范围内自动将状态改为关闭)
	 * @author duanmuyn
	 *
	 */
	public void refreshStatus();
}
