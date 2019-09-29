package cn.yufu.fs.web;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.yufu.fs.entity.FkStlDayEndStep;
import cn.yufu.fs.service.FkStlDayEndStepService;
import cn.yufu.system.common.utils.Log;

@Controller
@RequestMapping(value = "/FkStlDayEndStep")

public class FkStlDayEndStepController {
	Log log = Log.getLog(FkStlDayEndStepController.class);
	
	@Autowired
	@Qualifier("fs.FkStlDayEndStepService")	
	private FkStlDayEndStepService FkStlDayEndStepService;
	
	@RequestMapping(value = { "getList" })
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model,FkStlDayEndStep queryModel) {
		if(queryModel==null){
			queryModel = new FkStlDayEndStep();
		}
		if(queryModel.getDailydate()==null||"".equals(queryModel.getDailydate())){
			queryModel.setDailydate(getNowDt("yyyyMMdd",-1));			
		}
		List<FkStlDayEndStep> list = FkStlDayEndStepService.queryList(queryModel);			
		model.addAttribute("FkStlDayEndStepList", list);	
		model.addAttribute("query", queryModel);
		return "modules/fs/fkStlDayEndStep/fkStlDayEndStepList";
	}
	
	/**
	 * 跳转查看页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "toShow" })
	public String toShow(HttpServletRequest req, HttpServletResponse resp, Model model,String FkStlDayEndStepId) {
		FkStlDayEndStep info = FkStlDayEndStepService.queryInfo(FkStlDayEndStepId);
		model.addAttribute("info", info);		
		return "modules/fs/fkStlDayEndStep/fkStlDayEndStepShow";
	}
	
	/**
	 * 取前一月日期
	 * @param pattern
	 * @return
	 */
	public String getLastMonthDay(String pattern) {
		if(pattern==null||"".equals(pattern)){
			pattern="yyyyMMddHHmmss";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1); // 得到前一月
		Date date = calendar.getTime();
    	SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
    	return df.format(date);
	}
	
	/**
	 * 获取当前时间
	 * @param pattern	时间格式
	 * @return
	 */
	public String getNowDt(String pattern,int flag){
		if(pattern==null||"".equals(pattern)){
			pattern="yyyyMMddHHmmss";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, flag);
		Date date = calendar.getTime();
    	SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
    	return df.format(date);
	}		
}
