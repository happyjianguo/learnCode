package cn.com.jansh.dao.mapper;

import cn.com.jansh.dao.entity.WxdTemplateConfig;

public interface WxdTemplateConfigMapper {

	/**
	 * 根据TEMPLATE_NO获取WxdTemplateConfig对象
	 * @param tempNo
	 * @return
	 */
	public WxdTemplateConfig selectByTempNo(String tempNo);
}
