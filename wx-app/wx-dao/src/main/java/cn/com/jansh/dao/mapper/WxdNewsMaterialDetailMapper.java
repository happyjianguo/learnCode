package cn.com.jansh.dao.mapper;


import java.util.List;

import cn.com.jansh.dao.entity.WxdNewsMaterialDetail;

public interface WxdNewsMaterialDetailMapper {
	/**
	 * 根据MATERIALID获取图文详情
	 * @param mid
	 * @return
	 */
	public List<WxdNewsMaterialDetail> getWNMDByMId(String mid);
}
