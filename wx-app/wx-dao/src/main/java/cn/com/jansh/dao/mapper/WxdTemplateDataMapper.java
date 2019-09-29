package cn.com.jansh.dao.mapper;

import java.util.List;

import cn.com.jansh.dao.entity.WxdTemplateData;

public interface WxdTemplateDataMapper {

	/**
	 * 根据template_no获取WxdTemplateData的集合
	 * @param template_no
	 * @return
	 */
	public List<WxdTemplateData> selectByTempNo(String template_no);
}
