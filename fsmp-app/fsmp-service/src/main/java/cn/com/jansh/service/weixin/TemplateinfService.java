package cn.com.jansh.service.weixin;

import java.util.List;

import cn.com.jansh.entity.weixin.TemplateinfEntity;

/**
 * 游戏模板服务类
 * @author hjm
 * 
 * 
 */
public interface TemplateinfService {
	/**
	 * 创建游戏模板
	 * @param templateinf
	 */
	public void insert(TemplateinfEntity templateinf);
	/**
	 * 获取全部游戏模板
	 * @return
	 */
	public List<TemplateinfEntity> query();
}
