/**
 * SinglehuodongDataController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年11月30日
 */
package cn.com.jansh.controller.datamanage;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.entity.game.CloudgameparamEntity;
import cn.com.jansh.service.data.SinglehuodongDataService;
import cn.com.jansh.util.DateUtil;
import cn.com.jansh.vo.DateVO;
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
		model.addAttribute("org",singlehuodongDataService.getOrg());
		return "/data/singlehuodongdata";
	}
	/**
	 * 根据机构id获取公众号
	 * @param orgid
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="ajax/initappid")
	@ResponseBody
	public ViewObject getAppid(String orgid){
		logger.info("根据机构id获取公众号id{}", orgid);
		return new ViewObject(singlehuodongDataService.getAccount(orgid));
	}
	/**
	 * 根据公众号id获取活动
	 * @param appid
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="ajax/initgameid")
	@ResponseBody
	public ViewObject getGame(String appid,String orgid,String channel){
		logger.info("根据公众号id获取活动id{}", appid,"机构id",orgid,"渠道",channel);
		List<CloudgameparamEntity> game = null;
		if(channel.equals("0")){
			game = singlehuodongDataService.getGame(appid);
		}else if(channel.equals("1")){
			game = singlehuodongDataService.getAppGame(orgid);
		}
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
		viewObject.setData(singlehuodongDataService.getPvUvData(params));
		return viewObject;
	}
}
