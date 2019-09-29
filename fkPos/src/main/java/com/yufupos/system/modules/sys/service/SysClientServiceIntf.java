package com.yufupos.system.modules.sys.service;

import java.util.List;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.modules.sys.entity.SysClient;

public interface SysClientServiceIntf {
	public SysClient get(String id);

	public List<SysClient> findList(SysClient sysClient);

	public Page<SysClient> findPage(Page<SysClient> page, SysClient sysClient);

	public void save(SysClient sysClient);

	public void delete(SysClient sysClient);

	public SysClient getByClientSecret(String clientSecret);

	public SysClient getByClientId(String clientId);
}
