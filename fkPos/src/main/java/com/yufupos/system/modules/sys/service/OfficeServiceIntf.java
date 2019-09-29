package com.yufupos.system.modules.sys.service;

import java.util.List;

import com.yufupos.system.modules.sys.entity.Office;

public interface OfficeServiceIntf {
	public List<Office> findByParentIdsLike(Office office);

	public void saveOut(Office office);

	public void deleteOut(Office office);

	public List<Office> findAllList(Office office);

	public List<Office> findList(Office office);

	public Office getOut(String id);
	
	public List<Office>  findAll();
	
	public List<Office> findList(Boolean isAll);
	
}
