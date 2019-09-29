package cn.com.jansh.controller.market;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.entity.wechat.AuthAccount;
import cn.com.jansh.entity.wechat.DefaultAccount;
import cn.com.jansh.mapper.wechat.DefaultAccountMapper;
import cn.com.jansh.service.data.DataIUserAnalysisService;


/**
 * 关注男女及地区分布分析
 * @author gll
 * @version 1.0
 */
@Controller
@RequestMapping(value="/accountuseranalysis")
public class DataProvinceAnalysisController {
	
	private Logger logger = LogManager.getLogger(DataProvinceAnalysisController.class);
	
	@Autowired
	private DataIUserAnalysisService analysisservice;
	@Autowired
	private DefaultAccountMapper defaultAccountMapper;	//默认公众号 Mapper
	@RequestMapping(value="/init")
	public String init(Model model){
		logger.info("关注男女及地区分布分析--init");
		/*获取登录用户信息*/
		String userid = AppUtil.getUserDetail().getUserid();
		//通过userid查询defaultAccount
		String defaultappid;
		DefaultAccount defaultAccount = defaultAccountMapper.selectByUserid(userid);
		if(null != defaultAccount){
			defaultappid = defaultAccount.getAppid();
		}else{
			defaultappid="";
		}
		List<AuthAccount> list = analysisservice.getAccount();
		model.addAttribute("account",list);
		model.addAttribute("defaultappid",defaultappid);
		return "/data/provinceanalysis";
	}
	
	@RequestMapping(value="ajax/getData")
	@ResponseBody
	public Map<String,String> getData(String appid){
		logger.info("关注男女及地区分布分析--getdata");
		Map<String,String> map = analysisservice.ProvinceSexAna(appid);
		return map;
	}
	
}
