/**
 * ClgameparamService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月31日
 */
package cn.com.jansh.service.market;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import cn.com.jansh.entity.component.CloudgameparamEntity;
import cn.com.jansh.model.component.GameInfoModel;
import cn.com.jansh.model.component.GameManageModel;
import cn.com.jansh.model.component.ShowGameModel;
import cn.com.jansh.vo.AjaxObj;

/**
 * 游戏配置表接口
 * @author Mr.wong
 * @version 1.0
 */
public interface ClgameparamService {

	/** 
	 * 获取全部游戏展示model
	 * @param gameManageModel 
	 * @return 
	 * @throws Exception 
	 */
	public GameManageModel getShowModel(GameManageModel gameManageModel) throws Exception;
	/**
	 * 通过游戏模板获取游戏信息
	 * @param gameInfoModel
	 * @return
	 */
	public String getGameInits(GameInfoModel gameInfoModel);
	/** 
	 * 获取全部的游戏参数
	 * 
	 * @return 
	 */
	public List<CloudgameparamEntity> getalldata();
	/**
	 * 获取创建营销活动参数
	 * @param gameManageModel
	 * @return
	 */
	public void gameInit(ShowGameModel showGameModel);
	/**
	 * 创建营销活动
	 * @param cloudgameparamEntity
	 * @return
	 * @throws Exception 
	 */
	public GameInfoModel establishAction(GameInfoModel gameInfoModel) ;
	
	/**
	 * 更新活动信息
	 * @param request
	 */
	public void updateGameInfo(GameInfoModel gameInfoModel);
	/**
	 * 删除活动信息
	 * @param gameid
	 */
	public AjaxObj deleteActivity(String gameid);
	/**
	 * 发布活动
	 * @param gameid
	 */
	public AjaxObj releaseActivity(String gameid , String status);
	/**
	 * 显示二维码
	 */
	public void showCode(HttpServletRequest request , HttpServletResponse response);
	/**
	 * 拷贝地址到粘贴板
	 * @return 
	 */
	public AjaxObj copyGameURI(String gameid);
	/**
	 * 获取游戏地址
	 * @return 
	 */
	public AjaxObj getGameURI(String gameid);
	/**
	 * 保存游戏参数配置信息
	 * @param request
	 * @param gameInfoModel
	 * @return 
	 * @throws Exception 
	 */
	public AjaxObj updateGameInfo(HttpServletRequest request, String gameInfoModel) throws Exception;
	/**
	 * 下载二维码
	 * @return 
	 * @throws IOException 
	 */
	public ResponseEntity<byte[]> downQR(HttpServletRequest request , HttpServletResponse response) throws IOException;
}
