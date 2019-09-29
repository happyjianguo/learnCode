package com.yufupos.system.modules.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.pos.entity.AreaCodeInfo;
import com.yufupos.system.modules.pos.dao.AreaCodeInfoDao;

/**
 * 收单地址Service
 * @author llg
 * @version 2017-04-19
 */
@Service
@Transactional(readOnly = true)
public class AreaCodeInfoService extends CrudService<AreaCodeInfoDao, AreaCodeInfo> {

	public AreaCodeInfo get(String id) {
		return super.get(id);
	}
	
	public List<AreaCodeInfo> findList(AreaCodeInfo areaCodeInfo) {
		return super.findList(areaCodeInfo);
	}
	
	public Page<AreaCodeInfo> findPage(Page<AreaCodeInfo> page, AreaCodeInfo areaCodeInfo) {
		return super.findPage(page, areaCodeInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(AreaCodeInfo areaCodeInfo) {
		super.save(areaCodeInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(AreaCodeInfo areaCodeInfo) {
		super.delete(areaCodeInfo);
	}
	
}