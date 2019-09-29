package cn.yufu.posp.queryManager.domain.logic;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.queryManager.domain.model.CurTranLs;

public interface CurTranLsReportLogicInterface {

	PageInfoModel queryAll(CurTranLs queryModel, PageInfoModel pageInfo, UserData sessionUserData) throws OAException;

	List queryExport(CurTranLs queryModel, UserData sessionUserData) throws OAException;

}
