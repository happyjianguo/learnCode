package cn.com.jansh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.jansh.dao.entity.PathPosition;
import cn.com.jansh.dao.entity.WXDNetStation;
import cn.com.jansh.service.BaiduLocateService;

@Controller
@RequestMapping(value = "/baidLocate")
public class BaiduLocateController {
	
	@Autowired
	private BaiduLocateService localService ;
	@RequestMapping("/init")
	public String init(){
		
		return "map/locations";
	}
	@RequestMapping("/localps")
	public String searchPositon(PathPosition pathPosition){
		WXDNetStation netStation = new WXDNetStation();
		netStation.setLatitude(pathPosition.getDestLocaly());
		netStation.setLongitude(pathPosition.getDestLocalx());
		List<WXDNetStation> netStations = localService.getData(netStation);
		if(netStations!=null&&netStations.size()==1){
			pathPosition.setNetStation(netStations.get(0));
		}
		return "map/localPosition";
	};
}
