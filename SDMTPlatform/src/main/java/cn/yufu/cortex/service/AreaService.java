package cn.yufu.cortex.service;

import java.util.List;

import cn.yufu.cortex.entity.Area;

public interface AreaService {

	/**
	 * 
	 * @param fid	父地址编号
	 * @param isuse 有效1，失效0
	 * @return
	 */
	public List<Area> queryList(String fid,String isuse) ;

}
