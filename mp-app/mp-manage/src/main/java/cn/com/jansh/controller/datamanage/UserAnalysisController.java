package cn.com.jansh.controller.datamanage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.com.jansh.entity.data.UserAnalysisTotal;
import cn.com.jansh.service.data.IUserAnalysisService;
import cn.com.jansh.util.DateUtil;
import cn.com.jansh.vo.AttentionAnalysisVO;
import cn.com.jansh.vo.DateVO;
import cn.com.jansh.vo.EchartData;


/**
 * 关注分析
 * @author gll
 * @version 1.0
 */
@Controller
@RequestMapping(value="/accountgzanalysis")
public class UserAnalysisController {
	
	private Logger logger = LogManager.getLogger(UserAnalysisController.class);
	
	@Autowired
	private IUserAnalysisService analysisservice;
	
	@RequestMapping(value="/init")
	public String init(Model model){
		logger.info("analysis--init");
		DateVO dateVO = new DateVO();
		dateVO.setStartDay(DateUtil.getLongFirstDay().substring(0, 10));
		dateVO.setEndDay(DateUtil.getDate().substring(0, 10));
		model.addAttribute("dateVO",dateVO);
		model.addAttribute("account",analysisservice.getAccount());
		return "/data/followanalysis";
	}
	/**
	 * 用户关注详细信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="ajax/analysisDataSource")
	@ResponseBody
	public EchartData analysisData(AttentionAnalysisVO params){
		return analysisservice.QuerryUserAnalysisDataSource(params);
	}
	/**
	 * 用户关注整体信息
	 * @return
	 */
	@RequestMapping(value="ajax/analysisWholeData")
	@ResponseBody
	public UserAnalysisTotal analysisWholeData(String startDate,String endDate,String appid){
		logger.info("关注信息整体分析....");  
		return analysisservice.QuerryUserAnalysisTotalData(startDate, endDate,appid);
	}
}
