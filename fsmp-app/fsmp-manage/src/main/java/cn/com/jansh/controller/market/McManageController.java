/**
 * McManageController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年11月9日
 */
package cn.com.jansh.controller.market;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.core.exception.AppException;

import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.entity.weixin.TemplateinfEntity;
import cn.com.jansh.model.DatatablesViewPage;
import cn.com.jansh.model.component.GameInfoModel;
import cn.com.jansh.model.component.GameManageModel;
import cn.com.jansh.model.component.ShowGameModel;
import cn.com.jansh.service.market.ClgameparamService;
import cn.com.jansh.service.weixin.TemplateinfService;
import cn.com.jansh.vo.AjaxObj;

/**
 * 营销活动
 * @author duanmuyn
 * @version 1.0
 */
@Controller
@RequestMapping("/mcmanage")
public class McManageController {
	
	private static final Logger logger = LogManager.getLogger(McManageController.class);

	@Autowired
	private ClgameparamService gameService;
	@Autowired
	private TemplateinfService templateinfService;
	@Autowired
	private GlobalProperties  globalProperties;
	/**
	 * 跳转到初始化页面
	 * @return
	 */
	@RequestMapping("/init")
	public String mcinit(HttpServletRequest request){
		logger.info("营销活动页面初始化");
		String gameURI = globalProperties.getGameURI();
		String dzpURI = globalProperties.getDzpURI();
		request.setAttribute("gameURI",gameURI);
		request.setAttribute("dzpURI", dzpURI);
		return "fsmp/market/mcmanage";
	}
	
	@RequestMapping("/saveGameInfo")
	@ResponseBody
	public AjaxObj saveGameInfo(HttpServletRequest request ,@RequestBody()String gameInfoModel) throws Exception{
		logger.info("开始保存活动配置信息");
		return gameService.updateGameInfo(request,gameInfoModel);
	}
	/**
	 * 跳转到活动首页面
	 * @return
	 */
	@RequestMapping("/gameindex")
	public String acinit(ShowGameModel showGameModel){
		logger.info("创建活动首页面");
		gameService.gameInit(showGameModel);
		return "fsmp/market/gameindex";
	}
	
	@RequestMapping("/temp")
	@SecurityRequest(repeatRequest=true)
	@OperationLog(value=Operation.CREATE,key="310100")
	public String gametemp(@Valid ShowGameModel showGameModel,Model model) throws Exception{
		logger.info("开始重定向到游戏参数设置界面");
		
//		ShowGameModel gameModel = gameService.establishAction(showGameModel);
		GameInfoModel gameInfoModel = new GameInfoModel();
//		gameInfoModel.setGameid(gameModel.getGameid());
//		gameInfoModel.setAppid(gameModel.getAppid());
//		gameInfoModel.setGamename(gameModel.getGamename());
//		gameInfoModel.setPlayname(gameModel.getPlayname());
		logger.info("活动模板初始化");
		List<TemplateinfEntity> templateinfList = templateinfService.query();
		gameInfoModel.setTemplateinflist(templateinfList);
		model.addAttribute(gameInfoModel);
		return "fsmp/temppara/tempselect";
	}
	
	@RequestMapping("/delete")
	@SecurityRequest(repeatRequest=true)
	@OperationLog(value=Operation.CREATE,key="310300")
	@ResponseBody
	public AjaxObj deleteActivity(String gameid){
		logger.info("开始删除活动");
		AjaxObj ajaxObj = gameService.deleteActivity(gameid);
		return ajaxObj;
	}
	
	/**
	 * 展示活动信息
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getdata")
	@ResponseBody
	public DatatablesViewPage<ShowGameModel> getData(GameManageModel gameManageModel) throws Exception{
		logger.info("开始获取活动信息");
		DatatablesViewPage<ShowGameModel> viewPage = new DatatablesViewPage<>();
		GameManageModel manageModel = gameService.getShowModel(gameManageModel);
		viewPage.setAaData(manageModel.getShowGameModels());
		viewPage.setiTotalRecords(manageModel.getCount());
		viewPage.setiTotalDisplayRecords(manageModel.getCount());
		viewPage.setLength(manageModel.getCount());
		
		return viewPage;
	}
	
	@RequestMapping(value="/tempinit")
	public String init(GameInfoModel gameInfoModel){
		logger.info("活动模板初始化");
		List<TemplateinfEntity> templateinfList = templateinfService.query();
		gameInfoModel.setTemplateinflist(templateinfList);
		return "fsmp/temppara/tempselect";
	}
	@RequestMapping(value="/tempmake")
	@OperationLog(value=Operation.CREATE,key="310900")
	public String tempmakeinit(GameInfoModel gameInfoModel) throws AppException{
		return "fsmp/temppara/tempmake";
	}
	
	/**
	 * 跳转到活动参数设置页面
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping(value="/gameinit")
	public String gameinit( GameInfoModel gameInfoModel,HttpServletRequest request) throws AppException{
		logger.info("通过游戏模板获取游戏信息");
		String redirect =  gameService.getGameInits(gameInfoModel);
		return redirect;
	}
	/**
	 * 发布活动
	 * @param gameid
	 * @return
	 */
	@RequestMapping("/release")
	@OperationLog(value=Operation.CREATE,key="310700")
	@ResponseBody
	public AjaxObj modifyActivityStatus(String gameid , String status){
		logger.info("开始变更活动发布状态！");
		AjaxObj ajaxObj = gameService.releaseActivity(gameid,status);
		return ajaxObj;
	}
	@RequestMapping("/showQR")
	@ResponseBody
	public void showQRcode(HttpServletRequest request , HttpServletResponse response){
		logger.info("开始显示二维码！");
		gameService.showCode(request, response);
	}
	/**
	 * 拷贝二维码链接地址
	 * @param gameid
	 * @return
	 */
	@RequestMapping("/copyURI")
	@ResponseBody
	public AjaxObj  copyURI(String gameid){
		logger.info("开始拷贝二维码链接地址！");
		return gameService.copyGameURI(gameid);
	}
	/**
	 * 获取二维码链接地址
	 * @param gameid
	 * @return
	 */
	@RequestMapping("/getURI")
	@ResponseBody
	public AjaxObj  getURI(String gameid){
		logger.info("开始获取二维码链接地址！");
		return gameService.getGameURI(gameid);
	}
	@RequestMapping("/downQR")
	@OperationLog(value=Operation.CREATE,key="310500")
	@ResponseBody
	public ResponseEntity<byte[]> downQR(HttpServletRequest request , HttpServletResponse response) throws IOException{
		logger.info("下载二维码");
		return gameService.downQR(request, response);
	}
} 
