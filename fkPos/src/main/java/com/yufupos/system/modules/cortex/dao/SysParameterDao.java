package com.yufupos.system.modules.cortex.dao;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.cortex.entity.SysParameter;

/**
 *	商户类型信息DAO接口
 * @author ZQK
 * @version 2017-08-02
 */
@MyBatisDao
public interface SysParameterDao extends CrudDao<SysParameter>{

}