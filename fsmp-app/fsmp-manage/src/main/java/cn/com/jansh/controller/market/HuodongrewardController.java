/**
 * HuodongrewardController.java
 * 版权所有(C) 2017 北京坚石诚信科技有限公司
 * 创建:gll 2017年1月10日
 */
package cn.com.jansh.controller.market;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jansh.core.web.servlet.ViewObject;

import cn.com.jansh.entity.component.CloudgameparamEntity;
import cn.com.jansh.entity.component.HuodongrewardDataEntity;
import cn.com.jansh.entity.wechat.AuthAccount;
import cn.com.jansh.service.data.DataIUserAnalysisService;
import cn.com.jansh.service.data.HuodongrewardService;
import cn.com.jansh.service.data.SinglehuodongDataService;
import cn.com.jansh.utils.DateUtil;
import cn.com.jansh.vo.DateVO;
import cn.com.jansh.vo.SingleActionVO;

/**
 * 活动获奖情况contoller
 * @author gll
 * @version 1.0
 */
@Controller
@RequestMapping(value="huodongreward")
public class HuodongrewardController {
	
	private final static Logger logger = LogManager.getLogger(HuodongrewardController.class);
	
	@Autowired
	private HuodongrewardService huodongrewardService; 
	
	@Autowired
	private SinglehuodongDataService singlehuodongDataService;	//单个活动数据service
	
	@Autowired
	private DataIUserAnalysisService analysisservice;	//公众号分析service
	/**
	 * 活动获奖情况初始化
	 * @param model
	 */
	@RequestMapping(value="init")
	public String init(Model model){
		logger.info("活动获奖情况页面初始化");
		DateVO dateVO = new DateVO();
		//日期格式yyyy-MM-dd HH:mm
		dateVO.setStartDay(DateUtil.getLongFirstDay().substring(0, 16));
		dateVO.setEndDay(DateUtil.getDate().substring(0, 16));
		model.addAttribute("dateVO",dateVO);
		return "/data/huodongreward";
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
		//根据所在组织获取公众号列表
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
	 * 查询获奖情况
	 * @param gameid
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="ajax/getdata")
	@ResponseBody
	public ViewObject getData(SingleActionVO params){
		logger.info("查询获奖情况**gameid:{}",params);
		//判断活动id是否为空
		if(StringUtils.isNotEmpty(params.getGameid())){
			List<HuodongrewardDataEntity> list = huodongrewardService.gethuodongreward(params.getGameid(),params.getStartDate()+":00",params.getEndDate()+":00");
			return new ViewObject(list);
		}else{
			HuodongrewardDataEntity hd = new HuodongrewardDataEntity();
			return new ViewObject(hd);
		}
		
	}

}
