package cn.com.jansh.dao.mapper;

import cn.com.jansh.dao.entity.WxdNewsMaterial;

public interface WxdNewsMaterialMapper {

	/**
	 * 根据MATERIALID获取WxdNesMaterial对象
	 * @param materialId
	 * @return
	 */
	public WxdNewsMaterial getWNMById(String materialId);
	
}
