/**
* Copyright &copy; 2015 All rights reserved.
 */
package cn.yufu.system.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.service.TreeService;
import cn.yufu.system.modules.sys.dao.AreaDao;
import cn.yufu.system.modules.sys.entity.Area;
import cn.yufu.system.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author king
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll(){
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
	
}
