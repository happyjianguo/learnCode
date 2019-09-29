/**
 * GetPackageInformation.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月20日
 */
package cn.com.jansh.scheduler;


import org.springframework.beans.factory.annotation.Autowired;

import com.jansh.core.scheduler.AbstractScheduledTask;

import cn.com.jansh.service.scheduler.FsGetPackageInformationService;

/**
 * 定时任务-获取商品信息
 * @author duanmuyn
 * @version 1.0
 */
public class FsGetPackageInformationTask extends AbstractScheduledTask{

	@Autowired
	private FsGetPackageInformationService fsGetPackageInformationService;	
	
	@Override
	public void exec() {
		fsGetPackageInformationService.action();
	}
} 