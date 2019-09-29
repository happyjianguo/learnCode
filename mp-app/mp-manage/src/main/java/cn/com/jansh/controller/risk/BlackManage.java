package cn.com.jansh.controller.risk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.constant.ContextCode;
import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.OperationResult;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.core.exception.AppException;
import com.jansh.comm.util.DateUtil;
import com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.entity.risk.BlacklistEntity;
import cn.com.jansh.model.risk.BlacklistModel;
import cn.com.jansh.service.game.GameInitService;
import cn.com.jansh.service.risk.BlackService;

/**
 * 黑名单管理
 * 
 * @author gll
 *
 */
@Controller
@RequestMapping(value = "/blackmanage")
public class BlackManage {

	private static final Logger logger = LogManager.getLogger(BlackManage.class);

	@Autowired
	private BlackService blackService;//黑名单service
	@Autowired
	private GameInitService gameInitService;//游戏service
	
	/**
	 * 黑名单页面初始化跳转
	 * @param blacklistModel
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init(BlacklistModel blacklistModel) {
		logger.info("黑名单页面条件初始化{}",blacklistModel);
		List<String> li = new LinkedList<String>();
		/** 状态（0--未上线，1--上线，2--下线，3--推荐） */
		li.add(ContextCode.ACTIVITY_STATUS_0.value());
		li.add(ContextCode.ACTIVITY_STATUS_1.value());
		li.add(ContextCode.ACTIVITY_STATUS_2.value());
		li.add(ContextCode.ACTIVITY_STATUS_3.value());
		blacklistModel.setGame(gameInitService.getGameNameAndCode(li));
		return "risk/black/blackmanage";
	}
	/**
	 * 黑名单页面数据初始化
	 * @param blacklistModel
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "ajax/getdata")
	@ResponseBody
	public ViewObject getdata(BlacklistModel blacklistModel){
		logger.info("黑名单页面初始化{}",blacklistModel);
		return new ViewObject(blackService.selectByOne(modelToEntity(blacklistModel)));
	}
	/*--------------------------------新增黑名单start---------------------------------*/
	/**
	 * 新增页面初始化跳转
	 * @param blacklistModel
	 * @return
	 */
	@RequestMapping(value = "/addinit")
	public String addinit(BlacklistModel blacklistModel){
		logger.info("新增黑名单页面跳转");
		List<String> li = new LinkedList<String>();
		/** 状态（0--未上线，1--上线，2--下线，3--推荐） */
		li.add(ContextCode.ACTIVITY_STATUS_0.value());
		li.add(ContextCode.ACTIVITY_STATUS_1.value());
		li.add(ContextCode.ACTIVITY_STATUS_2.value());
		li.add(ContextCode.ACTIVITY_STATUS_3.value());
		blacklistModel.setGame(gameInitService.getGameNameAndCode(li));
		//获取当前时间的yyyy-MM-dd+1
		blacklistModel.setBegintime(addDate(1));
		blacklistModel.setOvertime(DateUtil.getLastDayOfMonth());
		return "risk/black/blackadd";
	}
	/**
	 * 新增数据
	 * @param blacklistModel
	 * @return
	 * @throws AppException 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/adddata")
	@ExceptionHandle("/blackmanage/addinit")
	@OperationResult(value = Operation.CREATE)
	@OperationLog(value = Operation.CREATE)
	@SecurityRequest(repeatRequest = true)
	public String adddata(BlacklistModel blacklistModel) throws AppException, ParseException{
		logger.info("新增黑名单数据{}",blacklistModel);
		if(null != blackService.selectBlackByValueAndAction(modelToEntity(blacklistModel))){
			//此活动中已存在此黑名单值，请勿重复添加
			throw new AppException(AppErrorCode.E230001);
		}
		//插入数据
		blackService.insertBlack(modelToEntity(blacklistModel));
		return init(blacklistModel);
	}
	/*--------------------------------新增黑名单 end ---------------------------------*/
	/**
	 * 修改黑名单初始化跳转
	 * @param blacklistModel
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/editinit")
	public String editinit(BlacklistModel blacklistModel,Model model) throws ParseException{
		logger.info("进入修改黑名单页面:{}",blacklistModel);
		blacklistModel = entityToModel(blackService.selectBlackByBlackId(blacklistModel.getBlackid()));
		//获取当前时间的yyyy-MM-dd+1
		blacklistModel.setBegintime(addDate(1));
		model.addAttribute(blacklistModel);
		return "risk/black/blackedit";
	}
	
	/**
	 * 修改数据
	 * @param blacklistModel
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/editdata")
	@ExceptionHandle("/blackmanage/editinit")
	@OperationResult(value = Operation.UPDATE)
	@OperationLog(value = Operation.UPDATE)
	@SecurityRequest(repeatRequest = true)
	public String editdata(BlacklistModel blacklistModel) throws ParseException{
		logger.info("修改黑名单数据:{}",blacklistModel);
		blackService.updateblack(modelToEntity(blacklistModel));
		return init(blacklistModel);
	}
	
	/**
	 * 删除黑名单初始化
	 * @param blacklistModel
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/delinit")
	public String delinit(BlacklistModel blacklistModel,Model model) throws ParseException{
		logger.info("进入删除黑名单页面:{}",blacklistModel);
		blacklistModel = entityToModel(blackService.selectBlackByBlackId(blacklistModel.getBlackid()));
		model.addAttribute(blacklistModel);
		return "risk/black/blackdel";
	}
	/**
	 * 删除数据
	 * @param blacklistModel
	 * @return
	 */
	@RequestMapping(value = "/deldata")
	@ExceptionHandle("/blackmanage/delinit")
	@OperationResult(value = Operation.DELETE)
	@OperationLog(value = Operation.DELETE)
	@SecurityRequest(repeatRequest = true)
	public String deldata(BlacklistModel blacklistModel){
		logger.info("删除黑名单:{}",blacklistModel);
		blackService.deleteblack(blacklistModel.getBlackid());
		return init(blacklistModel);
	}
	/**
	 * Model转Entity
	 * @param blacklistModel
	 * @return
	 */
	private BlacklistEntity modelToEntity(BlacklistModel blacklistModel){
		BlacklistEntity blacklistEntity = new BlacklistEntity();
		blacklistEntity.setActionid(blacklistModel.getActionid());
		blacklistEntity.setBlackvalue(blacklistModel.getBlackvalue());
		blacklistEntity.setBlacktype(blacklistModel.getBlacktype());
		blacklistEntity.setRemark(blacklistModel.getRemark());
		blacklistEntity.setStatus(blacklistModel.getStatus());
		blacklistEntity.setOvertime(blacklistModel.getOvertime());
		blacklistEntity.setBlackid(blacklistModel.getBlackid());
		blacklistEntity.setPlayname(blacklistModel.getPlayname());
		return blacklistEntity;
	}
	/**
	 * Entity转Model
	 * @param blacklistModel
	 * @return
	 */
	private BlacklistModel entityToModel(BlacklistEntity blacklist){
		BlacklistModel blacklistModel = new BlacklistModel();
		blacklistModel.setActionid(blacklist.getActionid());
		blacklistModel.setBlackvalue(blacklist.getBlackvalue());
		blacklistModel.setBlacktype(blacklist.getBlacktype());
		blacklistModel.setRemark(blacklist.getRemark());
		blacklistModel.setStatus(blacklist.getStatus());
		blacklistModel.setOvertime(blacklist.getOvertime());
		blacklistModel.setBlackid(blacklist.getBlackid());
		blacklistModel.setPlayname(blacklist.getPlayname());
		return blacklistModel;
	}
	/**
	 * 当前日期+1，返回yyyy-MM-dd
	 * @param day
	 * @return
	 */
	public static String addDate(int day) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(new Date());
		cl.add(Calendar.DATE, day);
		cl.getTime();
		String currentTime = new SimpleDateFormat("yyyy-MM-dd").format(cl.getTime());
		return currentTime;
	}
}
