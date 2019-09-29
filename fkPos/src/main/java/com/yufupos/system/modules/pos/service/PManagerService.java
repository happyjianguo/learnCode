package com.yufupos.system.modules.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.pos.entity.PManager;
import com.yufupos.system.modules.pos.dao.PManagerDao;

/**
 * 商户经理信息Service
 * @author llg
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class PManagerService extends CrudService<PManagerDao, PManager> {

	public PManager get(String id) {
		return super.get(id);
	}
	
	public List<PManager> findList(PManager pManager) {
		return super.findList(pManager);
	}
	
	public Page<PManager> findPage(Page<PManager> page, PManager pManager) {
		return super.findPage(page, pManager);
	}
	
	@Transactional(readOnly = false)
	public void save(PManager pManager) {
		super.save(pManager);
	}
	
	@Transactional(readOnly = false)
	public void delete(PManager pManager) {
		super.delete(pManager);
	}
	
	/**
	 * 获取普通员工或店长信息
	 * @param role:0.普通员工；1.店长
	 * @param managerArea 区域
	 * @return
	 */
	public List<PManager> findListByRoleId(String role,String managerArea) {
		//获取店长码表
		PManager pManagerSM=new PManager();
		if(!Strings.isNullOrEmpty(role)){
			pManagerSM.setManagerRole(role);	
		}
		if(!Strings.isNullOrEmpty(managerArea)){
			pManagerSM.setManagerArea(managerArea);
		}
		pManagerSM.setManagerStatus("0");
		pManagerSM.setDelFlag("0");
		return super.findList(pManagerSM);
	}
	
	
}