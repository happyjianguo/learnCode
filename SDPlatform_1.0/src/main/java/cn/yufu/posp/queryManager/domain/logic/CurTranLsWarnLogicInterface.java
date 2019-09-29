package cn.yufu.posp.queryManager.domain.logic;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.queryManager.domain.model.CurTranLsWarn;

public interface CurTranLsWarnLogicInterface {

	PageInfoModel queryAll(CurTranLsWarn queryModel, PageInfoModel pageInfo, UserData sessionUserData) throws OAException;

	List queryExport(CurTranLsWarn queryModel, UserData sessionUserData) throws OAException;

}
