package cn.yufu.posp.sysparam.dao.hibernate.hql;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysparam.domain.model.SysParam;

public interface SysParamDaoHibernateHQL

{
	// ��KEY���һ���
	public SysParam querySysParamByKey(String newKey, UserData ud) throws OAException;

	// �������
	public void saveSysParam(SysParam newModel, UserData ud) throws OAException;

}
