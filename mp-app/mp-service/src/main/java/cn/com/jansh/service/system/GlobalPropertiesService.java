/**
 * PropertiesService.java
 * 2016年1月7日 下午3:57:07
 */
package cn.com.jansh.service.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * properties组件
 * 
 * @author nie
 *
 */
@Service
public class GlobalPropertiesService {

	@Value("${gameTemplateUploadBasedir}")
	private String gameUploadBasedir;
	//获取单个活动数据PV、UV URL
	@Value("${huodongdataDataUrl}")
	private String huodongdataDataUrl;
	//获取总pv、uv
	@Value("${acquireTotal}")
	private String acquireTotal;
	//验证URL
	@Value("${acquire}")
	private String acquire;
	//图片大小
	@Value("${imageMaxSize}")
	private String imageMaxSize;
	//图片上传URL
	@Value("${acquirepictureurl}")
	private String acquirepictureurl;
	
	public String getAcquirepictureurl() {
		return acquirepictureurl;
	}
	public String getImageMaxSize() {
		return imageMaxSize;
	}
	public String getGameUploadBasedir() {
		return gameUploadBasedir;
	}
	public String getHuodongdataDataUrl() {
		return huodongdataDataUrl;
	}
	public String getAcquireTotal() {
		return acquireTotal;
	}
	public String getAcquire() {
		return acquire;
	}
}
