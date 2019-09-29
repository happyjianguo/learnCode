package cn.yufu.posp.route.domain.logic;

import java.util.HashMap;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface RouteLogicInterface {

	public PageInfoModel queryRoute(Object newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	public void deleteRoute(Object newKeys, UserData ud) throws OAException;

	public void createRoute(Object newModel, UserData ud) throws OAException;

	public HashMap queryRouteByKey(String newKey, UserData ud) throws OAException;

	public void saveRoute(Object newModel, UserData ud) throws OAException;

}
