package cn.com.jansh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.dao.entity.WXDNetStation;
import cn.com.jansh.dao.mapper.IWXDNetStationMapper;
import cn.com.jansh.service.BaiduLocateService;

@Service
public class BaiduLocateServiceImpl implements BaiduLocateService {

	@Autowired
	private IWXDNetStationMapper netStationMapper;
	@Override
	public List<WXDNetStation> getData(WXDNetStation netStation) {
		List<WXDNetStation> netStations = null;
		try {
			netStations = netStationMapper.getNetStationList(netStation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return netStations;
	}

}
