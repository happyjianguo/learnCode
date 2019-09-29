package cn.com.jansh.controller.personcenter;

import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jansh.comm.util.DateUtil;
import com.jansh.core.web.servlet.ViewObject;
import com.jansh.core.web.util.AppUtil;
import cn.com.jansh.entity.system.OperLog;
import cn.com.jansh.entity.system.LoggerManageModel;
import cn.com.jansh.service.system.TransLogService;
import cn.com.jansh.vo.DatatablesViewPage;

@Controller
@RequestMapping(value = "/log")
public class LoggerController {
	private static final Logger logger = LogManager.getLogger(LoggerController.class);
	@Autowired
	private TransLogService translogservice;

	/**
	 * 跳转初始化页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init(LoggerManageModel LogModel) {
		logger.info("init manage-user");
		LogModel.setBeginTime(DateUtil.getFirstDayOfMonth());
		LogModel.setEndTime(DateUtil.getDate());
		return "personcenter/logmanage/loggermanage";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/getdata")
	public ViewObject initData(LoggerManageModel LogModel) {
		logger.info("日志数据查询");
		String userId = AppUtil.getUserDetail().getUserid();
		LogModel.setUserId(userId);
		LogModel.setBeginTime(DateUtil.parseLocalDate(LogModel.getBeginTime()).format(DateTimeFormatter.BASIC_ISO_DATE));
		LogModel.setEndTime(DateUtil.parseLocalDate(LogModel.getEndTime()).plusDays(1).format(DateTimeFormatter.BASIC_ISO_DATE));
		LoggerManageModel loggerManageModel = translogservice.searchLog(LogModel);
		DatatablesViewPage<OperLog> view = new DatatablesViewPage<OperLog>();
		view.setiTotalDisplayRecords(loggerManageModel.getCount());
		view.setiTotalRecords(loggerManageModel.getCount());
		view.setAaData(loggerManageModel.getLoglist());
		return new ViewObject(view);
	}
}
