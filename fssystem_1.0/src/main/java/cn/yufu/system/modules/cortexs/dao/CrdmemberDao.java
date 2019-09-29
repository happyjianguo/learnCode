package cn.yufu.system.modules.cortexs.dao;

import java.util.List;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.cortexs.entity.Crdmember;

/**
 * 会员(卡BIN)信息DAO接口
 * @author ZQK
 * @version 2017-09-08
 */
@MyBatisDao
public interface CrdmemberDao extends CrudDao<Crdmember>{
	
	public List<Crdmember> getOneData(Crdmember crdmember);
	
	public List<String> getMember();
	
}