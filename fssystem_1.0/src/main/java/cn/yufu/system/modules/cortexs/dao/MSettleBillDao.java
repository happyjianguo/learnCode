package cn.yufu.system.modules.cortexs.dao;

import java.util.List;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.cortexs.entity.MSettleBill;
import cn.yufu.system.modules.cortexs.entity.SysParameterBean;


/**
 * TLOG交易类型DAO接口
 * @author LLG
 * @version 2016-08-24
 */
@MyBatisDao
public interface MSettleBillDao extends CrudDao<MSettleBill> {
	public List<SysParameterBean> getSysParameterList(String paramType);
}