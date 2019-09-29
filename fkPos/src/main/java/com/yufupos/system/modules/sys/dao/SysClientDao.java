/**
* Copyright &copy; 2015 All rights reserved.
 */
package com.yufupos.system.modules.sys.dao;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.sys.entity.SysClient;

/**
 * 接入工程客户端DAO接口
 * 
 * @author mengfp
 * @version 2015-06-30
 */
@MyBatisDao
public interface SysClientDao extends CrudDao<SysClient> {

	public SysClient getByClientSecret(String clientSecret);

	public SysClient getByClientId(String clientId);

}