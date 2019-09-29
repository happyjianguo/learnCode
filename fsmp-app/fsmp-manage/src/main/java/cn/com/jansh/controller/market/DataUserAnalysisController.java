package cn.com.jansh.controller.market;


import java.util.List;

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
import cn.com.jansh.entity.weixin.UserAnalysisTotal;
import cn.com.jansh.mapper.wechat.DefaultAccountMapper;
import cn.com.jansh.service.data.DataIUserAnalysisService;
import cn.com.jansh.utils.DateUtil;
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
public class DataUserAnalysisController {
	
	private Logger logger = LogManager.getLogger(DataUserAnalysisController.class);
	
	@Autowired
	private DataIUserAnalysisService analysisservice;
	@Autowired
	private DefaultAccountMapper defaultAccountMapper;	//默认公众号 Mapper
	
	@RequestMapping(value="/init")
	public String init(Model model){
		logger.info("analysis--init");
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
		DateVO dateVO = new DateVO();
		dateVO.setStartDay(DateUtil.getLongFirstDay().substring(0, 10));
		dateVO.setEndDay(DateUtil.getDate().substring(0, 10));
		List<AuthAccount> list = analysisservice.getAccount();
		model.addAttribute("defaultappid",defaultappid);
		model.addAttribute("dateVO",dateVO);
		model.addAttribute("account",list);
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
		logger.info("用户关注详细信息", params);
		EchartData  dataSource=analysisservice.QuerryUserAnalysisDataSource(params);
		return dataSource;
	}
	/**
	 * 用户关注整体信息
	 * @return
	 */
	@RequestMapping(value="ajax/analysisWholeData")
	@ResponseBody
	public UserAnalysisTotal analysisWholeData(String startDate,String endDate,String appid){
		logger.info("关注信息整体分析....",appid);  
		UserAnalysisTotal userAnalysisTotle =analysisservice.QuerryUserAnalysisTotalData(startDate,endDate,appid);
		return userAnalysisTotle;
	}
}
