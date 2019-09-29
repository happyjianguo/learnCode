/**
 * SinglehuodongDataController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年11月30日
 */
package cn.com.jansh.controller.market;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.entity.component.CloudgameparamEntity;
import cn.com.jansh.entity.wechat.AuthAccount;
import cn.com.jansh.service.data.DataIUserAnalysisService;
import cn.com.jansh.service.data.SinglehuodongDataService;
import cn.com.jansh.utils.DateUtil;
import cn.com.jansh.vo.DateVO;
import cn.com.jansh.vo.PVUVDataVO;
import cn.com.jansh.vo.SingleActionVO;
/**
 * 单个活动数据Controller
 * @author gll
 * @version 1.0
 */
@Controller
@RequestMapping(value="/huodongdata")
public class SinglehuodongDataController {

	private Logger logger = LogManager.getLogger(SinglehuodongDataController.class);
	
	@Autowired
	private SinglehuodongDataService singlehuodongDataService;	//单个活动数据service
	
	@Autowired
	private DataIUserAnalysisService analysisservice;	//公众号分析service
	
	
	/**
	 * 单个活动数据初始化
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/init")
	public String init(Model model){
		logger.info("SinglehuodongData--init");
		DateVO dateVO = new DateVO();
		//日期格式yyyy-MM-dd HH:mm
		dateVO.setStartDay(DateUtil.getLongFirstDay().substring(0, 16));
		dateVO.setEndDay(DateUtil.getDate().substring(0, 16));
		model.addAttribute("dateVO",dateVO);
		return "/data/singlehuodongdata";
	}
	/**
	 * 根据机构id获取公众号
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="ajax/initappid")
	@ResponseBody
	public ViewObject getAppid(){
		logger.info("获取公众号id{}" );
		List<AuthAccount> list = analysisservice.getAccount();
		return new ViewObject(list);
	}
	/**
	 * 根据公众号id获取活动
	 * @param appid
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="ajax/initgameid")
	@ResponseBody
	public ViewObject getGame(String appid,String channel){
		logger.info("根据公众号id获取活动id{}", appid,channel);
		List<CloudgameparamEntity> game = singlehuodongDataService.getGame(appid,channel);
		return new ViewObject(game);
	}
	/**
	 * ajax通过接口查询PV、UV
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="ajax/getData")
	@ResponseBody
	public ViewObject getData(SingleActionVO params){
		logger.info("活动PV、UV接口....");
		ViewObject viewObject = new ViewObject();
		PVUVDataVO dataVO = singlehuodongDataService.getPvUvData(params);
		viewObject.setData(dataVO);
		return viewObject;
	}
}
