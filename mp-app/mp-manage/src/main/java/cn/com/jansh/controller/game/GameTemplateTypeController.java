/**
 * GameTemplateTypeController.java
 * 版权所有(C) 2017 北京坚石诚信科技有限公司
 * 创建:gll 2017年2月15日
 */
package cn.com.jansh.controller.game;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jansh.comm.util.IDUtils;
import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.OperationResult;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.core.exception.AppException;
import com.jansh.core.web.servlet.ViewObject;

import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.constant.ContextCode;
import cn.com.jansh.entity.game.CloudGameTemplateTypeEntity;
import cn.com.jansh.entity.game.GameTemplate;
import cn.com.jansh.model.game.CloudGameTemplateTypeModel;
import cn.com.jansh.service.game.GameInitService;
import cn.com.jansh.service.game.GameTemplateTypeService;

/**
 * 游戏模版分类 controller
 * @author gll
 * @version 1.0
 */
@Controller
@RequestMapping(value="gametmptype")
public class GameTemplateTypeController {

	private static final Logger logger = LogManager.getLogger(GameTemplateTypeController.class);
	
	@Autowired
	private GameTemplateTypeService gameTemplateTypeService;//模版分类service
	
	@Autowired
	private GameInitService gameInitService;//游戏service
	/**
	 * 游戏模版分类初始化
	 * @return
	 */
	@RequestMapping(value="/init")
	public String init(CloudGameTemplateTypeModel cloudGameTemplateTypeModel){
		logger.info("游戏活动模板分类页面初始化");
		List<String> li = new LinkedList<String>();
		/** 状态（0--未上线，1--上线，2--下线，3--推荐） */
		li.add(ContextCode.ACTIVITY_STATUS_0.value());
		li.add(ContextCode.ACTIVITY_STATUS_1.value());
		li.add(ContextCode.ACTIVITY_STATUS_2.value());
		li.add(ContextCode.ACTIVITY_STATUS_3.value());
		cloudGameTemplateTypeModel.setGame(gameInitService.getGameNameAndCode(li));
		return "/game/templatetype/templatetype";
	}
	/**
	 * 游戏模版分类初始化
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/ajax/query")
	@ResponseBody
	public ViewObject query(CloudGameTemplateTypeModel cloudGameTemplateTypeModel){
		logger.info("游戏活动模板分类页面初始化");
		List<CloudGameTemplateTypeEntity> gameTemplateTypelist = gameTemplateTypeService.queryAllData(modelToEntity(cloudGameTemplateTypeModel));
		return new ViewObject(gameTemplateTypelist);
	}
	
	/**
	 * 新增游戏模版分类跳转
	 * @return
	 */
	@RequestMapping(value="/addinit")
	public String addinit(CloudGameTemplateTypeModel cloudGameTemplateTypeModel){
		logger.info("新增游戏活动模板分类页面初始化");
		List<String> li = new LinkedList<String>();
		/** 状态（0--未上线，1--上线，2--下线，3--推荐） */
		li.add(ContextCode.ACTIVITY_STATUS_0.value());
		li.add(ContextCode.ACTIVITY_STATUS_1.value());
		li.add(ContextCode.ACTIVITY_STATUS_2.value());
		li.add(ContextCode.ACTIVITY_STATUS_3.value());
		cloudGameTemplateTypeModel.setGame(gameInitService.getGameNameAndCode(li));
		return "/game/templatetype/templatetypeadd";
	}
	/**
	 * 新增活动游戏模版分类
	 * @throws AppException 
	 */
	@RequestMapping(value = "/adddata")
	@ExceptionHandle("/gametmptype/addinit")
	@OperationResult(value = Operation.CREATE)
	@OperationLog(value = Operation.CREATE)
	@SecurityRequest(repeatRequest = true)
	public String adddata(CloudGameTemplateTypeModel cloudGameTemplateTypeModel) throws AppException{
		logger.info("新增游戏活动模板分类数据");
		//判断当前游戏下是否有相同模版分类名称
		List<CloudGameTemplateTypeEntity> gameTemplateTypelist = gameTemplateTypeService.queryAllData(modelToEntity(cloudGameTemplateTypeModel));
		if(!(null == gameTemplateTypelist || gameTemplateTypelist.size() ==0 )){
			//该游戏模版分类已存在
			throw new AppException(AppErrorCode.E270001);
		}
		//自动生成模版分类id
		cloudGameTemplateTypeModel.setTmptypeid(IDUtils.getTimeRandon());
		gameTemplateTypeService.insertdate(modelToEntity(cloudGameTemplateTypeModel));
		cloudGameTemplateTypeModel.setTmptypename("");
		cloudGameTemplateTypeModel.setGameid("");
		cloudGameTemplateTypeModel.setTmptypeid("");
		return init(cloudGameTemplateTypeModel);
	}
	/**
	 * 修改游戏模版分类跳转
	 * @return
	 */
	@RequestMapping(value="/editinit")
	public String editinit(CloudGameTemplateTypeModel cloudGameTemplateTypeModel){
		logger.info("修改游戏活动模板分类页面初始化");
		List<CloudGameTemplateTypeEntity> gameTemplateTypelist = gameTemplateTypeService.queryAllData(modelToEntity(cloudGameTemplateTypeModel));
		if(!(null == gameTemplateTypelist || gameTemplateTypelist.size() ==0 )){
			cloudGameTemplateTypeModel.setGameid(gameTemplateTypelist.get(0).getGameid());
			cloudGameTemplateTypeModel.setPlayname(gameTemplateTypelist.get(0).getPlayname());
			cloudGameTemplateTypeModel.setTmptypename(gameTemplateTypelist.get(0).getTmptypename());
		}
		return "/game/templatetype/templatetypeedit";
	}
	/**
	 * 保存修改内容
	 * @throws AppException 
	 */
	@ExceptionHandle("/gametmptype/editinit")
	@RequestMapping(value="/editdata")
	@OperationResult(value = Operation.UPDATE)
	@OperationLog(value = Operation.UPDATE)
	public String editdata(CloudGameTemplateTypeModel cloudGameTemplateTypeModel) throws AppException{
		logger.info("修改游戏活动模板分类数据:{}", cloudGameTemplateTypeModel);
		//判断此分类名有没有被使用
		CloudGameTemplateTypeEntity GameTemplateTypeEntity =gameTemplateTypeService.selectByName(modelToEntity(cloudGameTemplateTypeModel));
		if(null!=GameTemplateTypeEntity){
			//该游戏模版分类已存在
			throw new AppException(AppErrorCode.E270001);
		}
		//修改数据
		gameTemplateTypeService.updatedata(modelToEntity(cloudGameTemplateTypeModel));
		cloudGameTemplateTypeModel.setTmptypename("");
		cloudGameTemplateTypeModel.setGameid("");
		cloudGameTemplateTypeModel.setTmptypeid("");
		return init(cloudGameTemplateTypeModel);
		
	}
	/**
	 * 删除游戏模版分类跳转
	 * @return
	 */
	@RequestMapping(value="/delinit")
	public String delinit(CloudGameTemplateTypeModel cloudGameTemplateTypeModel){
		logger.info("删除游戏活动模板分类页面初始化");
		List<CloudGameTemplateTypeEntity> gameTemplateTypelist = gameTemplateTypeService.queryAllData(modelToEntity(cloudGameTemplateTypeModel));
		if(!(null == gameTemplateTypelist || gameTemplateTypelist.size() ==0 )){
			cloudGameTemplateTypeModel.setGameid(gameTemplateTypelist.get(0).getGameid());
			cloudGameTemplateTypeModel.setPlayname(gameTemplateTypelist.get(0).getPlayname());
			cloudGameTemplateTypeModel.setTmptypename(gameTemplateTypelist.get(0).getTmptypename());
		}
		return "/game/templatetype/templatetypedel";
	}
	/**
	 * 删除内容
	 * @throws AppException 
	 */
	@ExceptionHandle("/gametmptype/delinit")
	@RequestMapping(value="/deldata")
	@OperationResult(value = Operation.DELETE)
	@OperationLog(value = Operation.DELETE)
	public String deldata(CloudGameTemplateTypeModel cloudGameTemplateTypeModel) throws AppException{
		logger.info("删除游戏活动模板分类数据:{}", cloudGameTemplateTypeModel);
		//判断此分类名有没有被使用
		List<GameTemplate> gameTemplate = gameTemplateTypeService.selectActionByTmptypeid(cloudGameTemplateTypeModel.getTmptypeid());
		if(!(null == gameTemplate || gameTemplate.size() ==0 )){
			//该游戏模版分类已存在
			throw new AppException(AppErrorCode.E270002);
		}
		//删除数据
		gameTemplateTypeService.deletedata(modelToEntity(cloudGameTemplateTypeModel));
		cloudGameTemplateTypeModel.setTmptypename("");
		cloudGameTemplateTypeModel.setGameid("");
		cloudGameTemplateTypeModel.setTmptypeid("");
		return init(cloudGameTemplateTypeModel);
	}
	/**
	 * Model 转 Entity
	 * @param cloudGameTemplateTypeModel
	 * @return
	 */
	private CloudGameTemplateTypeEntity modelToEntity(CloudGameTemplateTypeModel cloudGameTemplateTypeModel) {
		logger.info("Model 转 Entity");
		CloudGameTemplateTypeEntity cloudGameTemplateTypeEntity = new CloudGameTemplateTypeEntity();
		cloudGameTemplateTypeEntity.setTmptypeid(cloudGameTemplateTypeModel.getTmptypeid());
		cloudGameTemplateTypeEntity.setTmptypename(cloudGameTemplateTypeModel.getTmptypename());
		cloudGameTemplateTypeEntity.setPlayname(cloudGameTemplateTypeModel.getPlayname());
		cloudGameTemplateTypeEntity.setGameid(cloudGameTemplateTypeModel.getGameid());
		return cloudGameTemplateTypeEntity;
	}
}
