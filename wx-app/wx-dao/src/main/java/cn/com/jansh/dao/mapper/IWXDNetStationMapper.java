package cn.com.jansh.dao.mapper;

import java.util.List;

import cn.com.jansh.dao.entity.WXDNetStation;


public interface IWXDNetStationMapper {

	public List<WXDNetStation> getNetStationList(WXDNetStation netStationModel);
	public WXDNetStation getNetStation(WXDNetStation netStationModel);
	public void addNetStationInfo(WXDNetStation netStationModel);
	public void delNetStationById(String orgId);
	public void modifyNetStationInfo(WXDNetStation netStationModel);
}
