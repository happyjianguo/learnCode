
package cn.yufu.posp.sysparam.domain.logic;

import java.util.HashMap;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysparam.domain.model.SysParam;

public interface SysParamLogicInterface {

	// ��key�����
	public HashMap querySysParamByKey(String newKey, UserData ud) throws OAException;

	// �������
	public void saveSysParam(SysParam newModel, UserData ud) throws OAException;
	// public void deleteRoute(SysParam newKeys, UserData ud) throws
	// OAException;

}
