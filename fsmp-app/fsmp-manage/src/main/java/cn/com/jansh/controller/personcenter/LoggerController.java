package cn.com.jansh.controller.personcenter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 日志查询
 * @author duanmuyn
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/log")
public class LoggerController {
	
	/*private static final Logger logger = LogManager.getLogger(LoggerController.class);
	
	@Autowired
	private TransLogService translogservice;

	*//**
	 * 跳转初始化页面
	 * @param LogModel
	 * @return
	 *//*
	@RequestMapping(value = "/init")
	public String init(LoggerManageModel LogModel) {
		logger.info("init manage-user");
		LogModel.setBeginTime(DateUtil.getFirstDayOfMonth());
		LogModel.setEndTime(DateUtil.getDate());
		return "sysmanage/logmanage/loggermanage";
	}

	@ResponseBody
	@RequestMapping(value = "/getdata")
	public ViewObject<DatatablesViewPage<OperLog>> initData(LoggerManageModel LogModel) {
		logger.info("日志数据查询");
		String userId = AppUtil.getUserDetail().getUserid();
		LogModel.setUserId(userId);
		LogModel.setBeginTime(LogModel.getBeginTime() + " 00:00:00");
		LogModel.setEndTime(LogModel.getEndTime() + " 23:59:59");
		LoggerManageModel loggerManageModel = translogservice.searchLog(LogModel);
		DatatablesViewPage<OperLog> view = new DatatablesViewPage<OperLog>();
		view.setiTotalDisplayRecords(loggerManageModel.getCount());
		view.setiTotalRecords(loggerManageModel.getCount());
		view.setAaData(loggerManageModel.getLoglist());
		return new ViewObject<DatatablesViewPage<OperLog>>(view);
	}*/
}
