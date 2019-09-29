package com.yufupos.system.modules.sys.service;

import java.util.List;

import com.yufupos.system.modules.sys.entity.Area;

public interface AreaServiceIntf {
	public List<Area> findAllList(Area area);

	public void saveOut(Area area);
	
	public void deleteOut(Area area);
	
	public Area getOut(String id);

}
