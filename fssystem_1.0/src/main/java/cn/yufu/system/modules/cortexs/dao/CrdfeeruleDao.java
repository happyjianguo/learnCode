package cn.yufu.system.modules.cortexs.dao;

import java.util.List;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.cortexs.entity.Crdfeerule;

/**
 * 扣款费率管理DAO接口
 * @author ZQK
 * @version 2017-07-16
 */
@MyBatisDao
public interface CrdfeeruleDao extends CrudDao<Crdfeerule> {
	
	public List<Crdfeerule> getDateByIID(Crdfeerule crdfeerule);
	
	public Crdfeerule selectByIIDAndTrueFlag(Crdfeerule crdfeerule);
}