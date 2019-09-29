/**
* Copyright &copy; 2015 All rights reserved.
 */
package cn.yufu.system.modules.sys.dao;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.sys.entity.SysClient;

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