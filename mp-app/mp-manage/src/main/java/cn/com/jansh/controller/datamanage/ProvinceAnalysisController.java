package cn.com.jansh.controller.datamanage;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.com.jansh.service.data.IUserAnalysisService;

/**
 * 关注男女及地区分布分析
 * @author gll
 * @version 1.0
 */
@Controller
@RequestMapping(value="/accountuseranalysis")
public class ProvinceAnalysisController {
	
	private Logger logger = LogManager.getLogger(ProvinceAnalysisController.class);
	
	@Autowired
	private IUserAnalysisService analysisservice;
	/**
	 * 初始化
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/init")
	public String init(Model model){
		logger.info("关注男女及地区分布分析--init");
		model.addAttribute("account",analysisservice.getAccount());
		return "/data/provinceanalysis";
	}
	/**
	 * 关注男女及地区分布分析
	 * @param appid
	 * @return
	 */
	@RequestMapping(value="ajax/getData")
	@ResponseBody
	public Map<String,String> getData(String appid){
		logger.info("关注男女及地区分布分析--getdata",appid);
		return analysisservice.ProvinceSexAna(appid);
	}
	
}
