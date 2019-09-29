/**
 * HuodongrewardController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年12月13日
 */
package cn.com.jansh.controller.datamanage;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jansh.core.web.servlet.ViewObject;

import cn.com.jansh.entity.data.HuodongrewardDataEntity;
import cn.com.jansh.service.data.HuodongrewardService;
import cn.com.jansh.util.DateUtil;
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
		model.addAttribute("org",huodongrewardService.getOrg());
		model.addAttribute("dateVO",dateVO);
		return "/data/huodongreward";
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
		logger.info("查询获奖情况**params:{}",params);
		//判断活动id是否为空
		if(StringUtils.isNotEmpty(params.getGameid())){
			return new ViewObject(huodongrewardService.gethuodongreward(params.getGameid(),params.getStartDate()+":00",params.getEndDate()+":00"));
		}else{
			return new ViewObject(new HuodongrewardDataEntity());
		}
	}
}
