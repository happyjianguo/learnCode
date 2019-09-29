package com.yufupos.system.modules.pos.dao;

import java.util.List;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.pos.entity.PSim;

/**
 * SIM卡信息DAO接口
 * @author llg
 * @version 2017-04-05
 */
@MyBatisDao
public interface PSimDao extends CrudDao<PSim> {
	public List<PSim> findPosSim(PSim pSim);
	public List<PSim> findSimSum(PSim pSim);
	
	public void disablePSim(PSim pSim);
	
	public void usablePSim(PSim pSim);
	
}