package com.yufupos.system.modules.pos.dao;

import java.util.List;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.pos.entity.PPos;

/**
 * POS机信息DAO接口
 * @author llg
 * @version 2017-04-05
 */
@MyBatisDao
public interface PPosDao extends CrudDao<PPos> {
	public List<PPos> findTermPos(PPos pPos);
	public List<PPos> findPosSum(PPos pPos);
	
	void disablePPos(PPos pPos);
	
	void usablePPos(PPos pPos);
}