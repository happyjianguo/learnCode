/**
* Copyright &copy; 2015 All rights reserved.
 */
package cn.yufu.system.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.sys.dao.SysClientDao;
import cn.yufu.system.modules.sys.entity.SysClient;

/**
 * 接入工程客户端Service
 * 
 * @author mengfp
 * @version 2015-06-30
 */
@Service
@Transactional(readOnly = true)
public class SysClientService extends CrudService<SysClientDao, SysClient> {

	public SysClient get(String id) {
		return super.get(id);
	}

	public List<SysClient> findList(SysClient sysClient) {
		return super.findList(sysClient);
	}

	public Page<SysClient> findPage(Page<SysClient> page, SysClient sysClient) {
		return super.findPage(page, sysClient);
	}

	@Transactional(readOnly = false)
	public void save(SysClient sysClient) {
		super.save(sysClient);
	}

	@Transactional(readOnly = false)
	public void delete(SysClient sysClient) {
		super.delete(sysClient);
	}

	public SysClient getByClientSecret(String clientSecret) {
		return dao.getByClientSecret(clientSecret);
	}

	public SysClient getByClientId(String clientId) {
		return dao.getByClientId(clientId);
	}

}