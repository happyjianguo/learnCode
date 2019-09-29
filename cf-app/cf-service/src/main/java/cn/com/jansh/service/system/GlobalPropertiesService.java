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

	@Value("${uploadBatchchargePath}")
	private String uploadBatchchargePath;
	
	@Value("${uploadWsBillPath}")
	private String uploadWsBillPath;

	public String getUploadBatchchargePath() {
		return uploadBatchchargePath;
	}

	public String getUploadWsBillPath() {
		return uploadWsBillPath;
	}
}
