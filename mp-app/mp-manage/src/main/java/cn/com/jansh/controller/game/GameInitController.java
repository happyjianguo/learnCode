/**
 * GameInitController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年11月9日
 */
package cn.com.jansh.controller.game;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jansh.comm.util.DateUtil;
import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.OperationResult;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.core.exception.AppException;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.entity.game.CloudGameInitEntity;
import cn.com.jansh.model.game.CloudGameInitModel;
import cn.com.jansh.service.game.GameInitService;

/**
 * 游戏活动维护类
 * @author xieliangliang
 * @version 1.0
 */
@Controller
@RequestMapping(value="gameinit")
public class GameInitController {

	private static final Logger logger = LogManager.getLogger(GameInitController.class);
	
	@Autowired
	private GameInitService gameInitService;
	
	/**
	 * 游戏管理页面初始化
	 */
	@RequestMapping("/init")
	public String init(CloudGameInitModel gameinitModel) {
		logger.info("游戏活动管理页面初始化");
		
		List<CloudGameInitEntity> gameDataList = gameInitService.queryAllData(modelToEntity(gameinitModel));
		gameinitModel.setGameInitList(gameDataList);//获取所有活动的数据
		
		return "/game/manager/gameinitlist";
	}
	
	/**
	 * 新增活动初始化页面
	 */
	@RequestMapping("addinit")
	public String addinit(CloudGameInitModel gameinitModel) {
		logger.info("新增活动初始化页面");
		return "/game/manager/gameinitadd";
	}
	
	/**
	 * 新增活动
	 */
	@RequestMapping(value = "/addgame")
	@ExceptionHandle("/gameinit/addinit")
	@OperationResult(value = Operation.CREATE)
	@OperationLog(value = Operation.CREATE)
	@SecurityRequest(repeatRequest = true)
	public String addgame(CloudGameInitModel gameinitModel) throws AppException{
		logger.info("新增活动信息");
		gameInitService.adddata(gameinitModel);
		
		gameinitModel.setPlayname("");
		gameinitModel.setStatus("");
		return init(gameinitModel);
	}
	
	/**
	 * 编辑活动初始化页面
	 */
	@RequestMapping(value="/editinfo")
	public String editinfo(CloudGameInitModel gameinitModel,Model model) {
		
		logger.info("修改活动页面初始化数据: {}",gameinitModel.getId());
		
		gameinitModel = entityToEntity(gameInitService.queryById(gameinitModel.getId()));
		model.addAttribute(gameinitModel);
		
		return "/game/manager/gameinitedit";
	}
	
	/**
	 * 保存修改更新内容
	 */
	@RequestMapping(value = "/editdata")
	@OperationResult(value = Operation.UPDATE)
	@OperationLog(value = Operation.UPDATE)
	@ExceptionHandle("/gameinit/editinfo")
	public String editdata(CloudGameInitModel gameinitModel) throws AppException {
		logger.info("活动数据修改数据:{}", gameinitModel);
		gameinitModel.setUpdatetime(DateUtil.getDateTimestamp());
		gameinitModel.setOperator(AppUtil.getUserDetail().getUsername());
		gameInitService.updateInfo(modelToEntity(gameinitModel));
		
		gameinitModel.setPlayname("");
		gameinitModel.setStatus("");
		return init(gameinitModel);
	} 
	
	/**
	 * 删除页面初始化显示
	 
	@RequestMapping(value="/delinit")
	public String delinit(CloudGameInitModel gameinitModel,Model model) {
		logger.info("删除活动页面初始化数据: {}",gameinitModel.getId());
		gameinitModel = entityToEntity(gameInitService.queryById(gameinitModel.getId()));
		model.addAttribute(gameinitModel);
		
		return "/game/manager/gameinitdel";
	}*/
	
	/**
	 * 删除活动数据 
	@RequestMapping(value = "/deldata")
	@OperationResult(value = Operation.DELETE)
	public String deldata(CloudGameInitModel gameinitModel) throws Exception {
		logger.info("删除用户" + gameinitModel.getId());
		try {
			gameInitService.delGameInfo(gameinitModel.getId());
		} catch (Exception e) {
			throw e;
		}
		gameinitModel.setPlayname("");
		gameinitModel.setStatus("");
		return init(gameinitModel);
	} */
	
	/**
	 * entity转model
	 */
	private CloudGameInitModel entityToEntity(CloudGameInitEntity entity){
		CloudGameInitModel reModel = new CloudGameInitModel();
		reModel.setId(entity.getId());
		reModel.setIndexnum(entity.getIndexnum());
		reModel.setChannel(entity.getChannel());
		reModel.setPlayname(entity.getPlayname());
		reModel.setDeploypath(entity.getDeploypath());
		reModel.setStatus(entity.getStatus());
		reModel.setCreatetime(entity.getCreatetime());
		reModel.setUpdatetime(entity.getUpdatetime());
		reModel.setOperator(entity.getOperator());
		reModel.setAppactionurl(entity.getAppactionurl());
		reModel.setWxactionurl(entity.getWxactionurl());
		reModel.setApprewardurl(entity.getApprewardurl());
		reModel.setWxrewardurl(entity.getWxrewardurl());
		
		return reModel;
	}
	
	/**
	 * MODEL转换实体
	 * @param winningrecordModel
	 * @return
	 */
	private CloudGameInitEntity modelToEntity(CloudGameInitModel cloudGameInitModel) {
		CloudGameInitEntity entity = new CloudGameInitEntity();
		entity.setId(cloudGameInitModel.getId());
		entity.setPlayname(cloudGameInitModel.getPlayname());
		entity.setStatus(cloudGameInitModel.getStatus());
		entity.setIndexnum(cloudGameInitModel.getIndexnum());
		entity.setChannel(cloudGameInitModel.getChannel());
		entity.setDeploypath(cloudGameInitModel.getDeploypath());
		entity.setUpdatetime(cloudGameInitModel.getUpdatetime());
		entity.setAppactionurl(cloudGameInitModel.getAppactionurl());
		entity.setWxactionurl(cloudGameInitModel.getWxactionurl());
		entity.setApprewardurl(cloudGameInitModel.getApprewardurl());
		entity.setWxrewardurl(cloudGameInitModel.getWxrewardurl());
		return entity;
	}
	
}
