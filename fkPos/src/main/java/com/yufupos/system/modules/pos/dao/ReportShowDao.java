package com.yufupos.system.modules.pos.dao;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.pos.entity.ReportShow;

/**
 * 报表展示DAO接口
 * @author zqk
 * @version 2017-06-09
 */
@MyBatisDao
public interface ReportShowDao extends CrudDao<ReportShow> {
	
}