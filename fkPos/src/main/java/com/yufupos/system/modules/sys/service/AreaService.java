/**
 * Copyright &copy; 2015 All rights reserved.
 */
package com.yufupos.system.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.service.TreeService;
import com.yufupos.system.modules.sys.dao.AreaDao;
import com.yufupos.system.modules.sys.entity.Area;
import com.yufupos.system.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * 
 * @author king
 * @version 2014-05-16
 */
@Service("areaService")
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> implements
		AreaServiceIntf {

	@Autowired
	AreaDao areaDao;

	public List<Area> findAll() {
		return UserUtils.getAreaList();
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}

	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}

	@Override
	public void deleteOut(Area area) {
		super.delete(area);
	}

	@Override
	public List<Area> findAllList(Area area) {
		return areaDao.findAllList(area);
	}

	@Override
	public void saveOut(Area area) {
		super.delete(area);
	}

	@Override
	public Area getOut(String id) {
		return areaDao.get(id);
	}

}
