/**
 * ClgameparamServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月31日
 */
package cn.com.jansh.service.market.impl;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.HttpClientRequest;
import com.jansh.comm.util.HttpClientUtil;
import com.jansh.comm.util.IDUtils;
import com.jansh.comm.util.JsonUtil;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.entity.component.CloudgameinitEntity;
import cn.com.jansh.entity.component.CloudgameparamEntity;
import cn.com.jansh.entity.component.GametemplateEntity;
import cn.com.jansh.entity.component.bo.ShowGameBO;
import cn.com.jansh.entity.component.bo.ShowTemplateBO;
import cn.com.jansh.entity.system.IMUserN;
import cn.com.jansh.entity.wechat.AuthAccount;
import cn.com.jansh.entity.wechat.DefaultAccount;
import cn.com.jansh.janshpay.util.Sign;
import cn.com.jansh.mapper.component.CloudgameinitMapper;
import cn.com.jansh.mapper.component.CloudgameparamMapper;
import cn.com.jansh.mapper.game.IGameTempMapper;
import cn.com.jansh.mapper.system.IMUserMapper;
import cn.com.jansh.mapper.wechat.AuthAccountMapper;
import cn.com.jansh.mapper.wechat.DefaultAccountMapper;
import cn.com.jansh.model.component.GameInfoModel;
import cn.com.jansh.model.component.GameManageModel;
import cn.com.jansh.model.component.ShowGameModel;
import cn.com.jansh.model.component.ShowTemplateModel;
import cn.com.jansh.model.zxing.LogoConfig;
import cn.com.jansh.model.zxing.ZXingConfig;
import cn.com.jansh.service.market.ClgameparamService;
import cn.com.jansh.utils.ZXingCodeUtil;
import cn.com.jansh.vo.AjaxObj;

/**
 * 游戏配置表接口实现
 * 
 * @author Mr.wong
 * @version 1.0
 */
@Service
public class ClgameparamServiceImpl implements ClgameparamService {

	private static final Logger logger = LogManager.getLogger(ClgameparamServiceImpl.class);

	@Autowired
	private CloudgameinitMapper cloudgameinitMapper;
	@Autowired
	private CloudgameparamMapper gameparamMapper;
	@Autowired
	private IMUserMapper userMapper;
	@Autowired
	private GlobalProperties globalProperties;
	@Autowired
	private CloudgameinitMapper gameinitMapper;
	@Autowired
	private IGameTempMapper tempMapper;
	@Autowired
	private AuthAccountMapper authAccountMapper;
	@Autowired
	private DefaultAccountMapper defaultAccountMapper;	//默认公众号 Mapper

	/**
	 * 获取全部的页面展示model
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public GameManageModel getShowModel(GameManageModel gameManageModel) throws Exception {
		logger.info("开始获取全部的游戏参数！");
		String userid = AppUtil.getUserDetail().getUserid();
		IMUserN user = userMapper.selectNewByUserid(userid);
		Map<String, Object> map = new HashMap<>();
		map.put("start", gameManageModel.getStart());
		map.put("length", gameManageModel.getLength());
		map.put("orgid", user.getOrgid());
		map.put("invalid", AppCommonsCode.GameStatus_invalid.value());
		List<ShowGameBO> showBOs = gameparamMapper.selectShowBO(map);
		int count = gameparamMapper.selectShowBOCount(map);
		List<ShowGameModel> showModels = new ArrayList<>();
		for (ShowGameBO bo : showBOs) {
			String tempName = bo.getTempname();
			if(StringUtils.isNotBlank(tempName)){
				int index = tempName.indexOf(".");
				if(index>0){
					String name = tempName.substring(0,index);
					bo.setTempname(name);
				}
			}
			if(StringUtils.isBlank(bo.getBegintime())){
				bo.setBegintime(AppCommonsCode.GameDefaultTime.value());
			}
			if(StringUtils.isBlank(bo.getEndtime())){
				bo.setEndtime(AppCommonsCode.GameDefaultTime.value());
			}
			ShowGameModel showGameModel = new ShowGameModel();
			BeanUtils.copyProperties(showGameModel, bo);
			showModels.add(showGameModel);
		}
		gameManageModel.setCount(count);
		gameManageModel.setShowGameModels(showModels);
		return gameManageModel;
	}

	/**
	 * 获取全部的游戏参数
	 * 
	 * @return
	 */
	@Override
	public List<CloudgameparamEntity> getalldata() {
		logger.info("开始获取全部的游戏参数！");
		Map<String, Object> map = new HashMap<>();
		String userid = AppUtil.getUserDetail().getUserid();
		if (StringUtils.isNotBlank(userid)) {
			IMUserN user = userMapper.selectNewByUserid(userid);
			map.put("orgid", user.getOrgid());
			map.put("invalid", AppCommonsCode.GameStatus_invalid.value());
		}
		List<CloudgameparamEntity> gameparams = gameparamMapper.select(map);
		return gameparams;
	}

	/**
	 * 获取创建营销活动参数
	 * 
	 * @return
	 */
	@Override
	public void gameInit(ShowGameModel showGameModel) {
		logger.info("开始获取营销活动初始化参数！");
		String userid = AppUtil.getUserDetail().getUserid();
		IMUserN user = userMapper.selectNewByUserid(userid);
		
		/* 构建查询参数map */
		Map<String, Object> map = new HashMap<>();
		map.put("orgid", user.getOrgid());
		map.put("status", AppCommonsCode.GameStatus_1.value());
		map.put("start", 0);
		map.put("length", Integer.valueOf(globalProperties.getPageRecordCount()));
		map.put("invalid", AppCommonsCode.GameStatus_invalid.value());
		logger.info("查询参数是：" + map.toString());
		/* 获取查询结果传递到model中 */
		List<CloudgameinitEntity> games = gameinitMapper.select(map);
		if (StringUtils.isNotBlank(showGameModel.getGameid())) {
			CloudgameparamEntity game = gameparamMapper.selectByGameid(showGameModel.getGameid());
			showGameModel.setChannel(game.getChannel());
			showGameModel.setAppid(game.getAppid());
			showGameModel.setGamename(game.getGamename());
			showGameModel.setPlayname(game.getGametype());
			showGameModel.setTempid(game.getTempid());
		}
		List<GametemplateEntity> gameTemps = tempMapper.select(map);
		for (GametemplateEntity temp : gameTemps) {
			String tempName = temp.getTempname();
			if(StringUtils.isNotBlank(tempName)){
				int index = tempName.indexOf(".");
				if(index>0){
					String name = tempName.substring(0,index);
					temp.setTempname(name);
				}
			}
		}
		showGameModel.setCloudgames(games);
		showGameModel.setGameTemps(gameTemps);
	}
	/**
	 * 通过游戏模板获取游戏信息
	 * @param gameInfoModel
	 * @return
	 */
	public String getGameInits(GameInfoModel gameInfoModel){
		//生成活动id
		String gameid = IDUtils.getTimeRandon();
		gameInfoModel.setGameid(gameid);
		String userid = AppUtil.getUserDetail().getUserid();
		gameInfoModel.setUserid(userid);
		//判断当前用户是否选择微信渠道
		if(AppCommonsCode.GameChannelWechat.value().equals(gameInfoModel.getChannel())){
			DefaultAccount defaultAccount = defaultAccountMapper.selectByUserid(userid);
			//判断当前用户是否有默认公众号
			if(null == defaultAccount){
				logger.info("没有选择默认公众号，请选择默认公众号，跳转到公众号管理界面");
				return "fsmp/wechat/skipwxmanage";
			}else{
				gameInfoModel.setAppid(defaultAccount.getAppid());
			}
		}
		
		logger.info("开始获取营销活动初始化参数！");
		/* 构建查询参数map */
		Map<String, Object> map = new HashMap<>();
		map.put("start", 0);
		map.put("tempstatus", AppCommonsCode.TempValidate.value());//有效模板
		map.put("length", Integer.valueOf(globalProperties.getPageRecordCount()));
		logger.info("查询参数是：" + map.toString());
		List<ShowTemplateBO> gameTemps = tempMapper.selectTempBOs(map);
		List<ShowTemplateModel> templateModels = new ArrayList<>();
		for (ShowTemplateBO showTemplateBO : gameTemps) {
			ShowTemplateModel showTemplateModel = new ShowTemplateModel();
			try {
				BeanUtils.copyProperties(showTemplateModel, showTemplateBO);
				templateModels.add(showTemplateModel);
			} catch (Exception e) {
				logger.error("属性拷贝异常",e);
			}
		}
		gameInfoModel.setGameTemps(templateModels);
		//获取游戏平台链接
		String gameURI = globalProperties.getDzpURI();
		gameInfoModel.setGameURI(gameURI);
		return "fsmp/market/gametemp";
	}

	/**
	 * 创建营销活动
	 * 
	 * @param cloudgameparamEntity
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public GameInfoModel establishAction(GameInfoModel gameInfoModel)  {
		logger.info("开始创建活动");

		/** 生成活动实体 **/
		CloudgameparamEntity cloudgameparamEntity = new CloudgameparamEntity();
		/** 获取登录用户信息 **/
		String userid = AppUtil.getUserDetail().getUserid();
		String time = DateUtil.getDateTimestamp();
		Map<String, Object> map =new HashMap<>();
		map.put("tempid",gameInfoModel.getTempid());
		List<GametemplateEntity> temps = tempMapper.select(map);
		if (StringUtils.isBlank(gameInfoModel.getGameid())) {
			/** 生成活动参数 **/
			String gameid = IDUtils.getTimeRandon();
			IMUserN user = userMapper.selectNewByUserid(userid);

			cloudgameparamEntity.setGameid(gameid);
			cloudgameparamEntity.setStatus(AppCommonsCode.GameDefaultStatus.value());
			cloudgameparamEntity.setCreatetime(time);
			cloudgameparamEntity.setUpdatetime(time);
			cloudgameparamEntity.setOperator(user.getUserid());
			cloudgameparamEntity.setOrgid(user.getOrgid());
			cloudgameparamEntity.setAmount(0);
			cloudgameparamEntity.setChannel(gameInfoModel.getChannel());
			cloudgameparamEntity.setAppid(gameInfoModel.getAppid());
			cloudgameparamEntity.setGamename(gameInfoModel.getGamename());
			if(CollectionUtils.isNotEmpty(temps)){
				cloudgameparamEntity.setGametype(temps.get(0).getGameid());
			}
			cloudgameparamEntity.setTempid(gameInfoModel.getTempid());
			cloudgameparamEntity.setUamount(0);
			gameparamMapper.insert(cloudgameparamEntity);
		} else {
			cloudgameparamEntity.setOperator(userid);
			cloudgameparamEntity.setUpdatetime(time);
			cloudgameparamEntity.setChannel(gameInfoModel.getChannel());
			cloudgameparamEntity.setAppid(gameInfoModel.getAppid());
			cloudgameparamEntity.setGamename(gameInfoModel.getGamename());
			if(CollectionUtils.isNotEmpty(temps)){
				cloudgameparamEntity.setGametype(temps.get(0).getGameid());
			}
			cloudgameparamEntity.setTempid(gameInfoModel.getTempid());
			cloudgameparamEntity.setGameid(gameInfoModel.getGameid());
			gameparamMapper.update(cloudgameparamEntity);
		}
		gameInfoModel.setGameid(cloudgameparamEntity.getGameid());
		gameInfoModel.setPlayname(cloudgameparamEntity.getGametype());

		return gameInfoModel;
	}

	/**
	 * 更新活动信息
	 * 
	 * @param request
	 */
	@Override
	public void updateGameInfo(GameInfoModel gameInfoModel) {
		logger.info("更新活动信息");
		if (gameInfoModel != null) {
			String begin = gameInfoModel.getBegintime();
			String end = gameInfoModel.getEndtime();
			String amount = gameInfoModel.getTotalbudget();
			String gameid = gameInfoModel.getGameid();
			if (StringUtils.isNotBlank(gameid) && StringUtils.isNotBlank(end) && StringUtils.isNotBlank(amount)
					&& StringUtils.isNotBlank(begin)) {
				CloudgameparamEntity cloudgameparamEntity = new CloudgameparamEntity();
				cloudgameparamEntity.setAmount(Integer.valueOf(amount));
				cloudgameparamEntity.setBegintime(begin);
				cloudgameparamEntity.setEndtime(end);
				cloudgameparamEntity.setGameid(gameid);
				gameparamMapper.update(cloudgameparamEntity);
			}
		}
	}
	/**
	 * 更新活动信息
	 * 
	 * @param request
	 * @throws Exception 
	 */
	@Override
	public AjaxObj updateGameInfo(HttpServletRequest request ,String gameInfoString) throws Exception {
		AjaxObj ajaxObj = new AjaxObj();
		logger.info("更新活动信息");
		GameInfoModel gameInfoModel = JsonUtil.readObject(gameInfoString,new TypeReference<GameInfoModel>(){});
		Map<String, String> map = new HashMap< String, String>();
		map.put("gameid", gameInfoModel.getGameid());
		String paramSign = Sign.getSign(map, globalProperties.getTokenSecret());
		String sign = request.getHeader("sign");
		if(StringUtils.isBlank(sign)||!sign.equals(paramSign)){
			ajaxObj.setResult(0);
			return ajaxObj;
		}
		if (gameInfoModel != null) {
			String begin = gameInfoModel.getBegintime();
			String end = gameInfoModel.getEndtime();
			String amount = gameInfoModel.getTotalbudget();
			String gameid = gameInfoModel.getGameid();
			String gamename = gameInfoModel.getGamename();
			String userid = gameInfoModel.getUserid();
			String appid = gameInfoModel.getAppid();
			String tempid = gameInfoModel.getTempid();
			String channel = gameInfoModel.getChannel();
			String gametype = gameInfoModel.getPlayname();
			if (StringUtils.isNotBlank(gameid) && 
					StringUtils.isNotBlank(gamename) && 
					StringUtils.isNotBlank(end) && 
					StringUtils.isNotBlank(begin)&&
					StringUtils.isNotBlank(tempid)||
					StringUtils.isNotBlank(amount)&&
					StringUtils.isNotBlank(gameid)
					) {
				IMUserN userN = userMapper.selectNewByUserid(userid);
 				CloudgameparamEntity cloudgameparamEntity = gameparamMapper.selectByGameid(gameid);
				if(cloudgameparamEntity == null){
					cloudgameparamEntity = new CloudgameparamEntity();
					if(StringUtils.isNotBlank(amount)){
						cloudgameparamEntity.setAmount(Integer.valueOf(amount));
					}
					cloudgameparamEntity.setUamount(0);
					cloudgameparamEntity.setGamename(gamename);
					cloudgameparamEntity.setOrgid(userN.getOrgid());
					cloudgameparamEntity.setBegintime(begin);
					cloudgameparamEntity.setEndtime(end);
					cloudgameparamEntity.setGameid(gameid);
					cloudgameparamEntity.setAppid(appid);
					cloudgameparamEntity.setTempid(tempid);
					cloudgameparamEntity.setChannel(channel);
					cloudgameparamEntity.setGametype(gametype);
					cloudgameparamEntity.setStatus(AppCommonsCode.GameDefaultStatus.value());
					cloudgameparamEntity.setOperator(userid);
					cloudgameparamEntity.setCreatetime(DateUtil.getDateTimestamp());
					cloudgameparamEntity.setUpdatetime(DateUtil.getDateTimestamp());
					gameparamMapper.insert(cloudgameparamEntity);
				}else{
					if(StringUtils.isNotBlank(amount)){
						cloudgameparamEntity.setAmount(Integer.valueOf(amount));
					}
					cloudgameparamEntity.setGamename(gamename);
					cloudgameparamEntity.setBegintime(begin);
					cloudgameparamEntity.setEndtime(end);
					cloudgameparamEntity.setOperator(userid);
					cloudgameparamEntity.setUpdatetime(DateUtil.getDateTimestamp());
					gameparamMapper.update(cloudgameparamEntity);
				}
				ajaxObj.setResult(1);
				return ajaxObj;
			}
		}
		return ajaxObj;
	}
	/**
	 * 删除活动信息
	 * 
	 * @param gameid
	 * @return
	 */
	@Override
	public AjaxObj deleteActivity(String gameid) {
		logger.info("开始删除活动");
		AjaxObj ajaxObj = new AjaxObj();
		CloudgameparamEntity game = gameparamMapper.selectByGameid(gameid);
		if (game != null) {
			if (AppCommonsCode.GameStatus_1.value().equals(game.getStatus())) {
				ajaxObj.setResult(0);
				ajaxObj.setMsg("该活动已上线不能删除！");
			} else if (AppCommonsCode.GameStatus_0.value().equals(game.getStatus())
					|| AppCommonsCode.GameStatus_2.value().equals(game.getStatus())) {
				game.setStatus(AppCommonsCode.GameStatus_invalid.value());
				gameparamMapper.update(game);
				ajaxObj.setResult(1);
				ajaxObj.setMsg("该活动删除成功！");
			}
		} else {
			ajaxObj.setResult(0);
			ajaxObj.setMsg("该活动不存在！");
		}
		return ajaxObj;
	}

	/**
	 * 发布活动
	 * 
	 * @param gameid
	 */
	@Override
	public AjaxObj releaseActivity(String gameid , String status) {
		logger.info("开始变更活动状态！");
		AjaxObj ajaxObj = new AjaxObj();
		CloudgameparamEntity game = gameparamMapper.selectByGameid(gameid);
		if (game != null) {
			HttpClientRequest request = new HttpClientRequest();
			Map<String, String> map = new HashMap<>();
			map.put("gameid", game.getGameid());
			map.put("status", status);
			request.addHeader("sign", getSign(gameid));
			String httpPost = null;
			try {
				request.setBody(JsonUtil.obj2json(map));
				CloudgameparamEntity cgpEntity = gameparamMapper.selectByGameid(gameid);
				Map<String, Object> gameInitMap = new HashMap<String,Object>();
				gameInitMap.put("id", cgpEntity.getGametype());
				CloudgameinitEntity cgiEntity = cloudgameinitMapper.selectOne(gameInitMap);
				httpPost = HttpClientUtil.httpPost(cgiEntity.getReleseactivityurl(), request);
				logger.info("发布活动返回结果：" + httpPost);
				if (StringUtils.isNotBlank(httpPost)) {
					Map<String, Object> result = JsonUtil.readMapObject(httpPost);
					String errorCode = (String) result.get("errorCode");
					String errorMsg = (String) result.get("errorMsg");
					if (AppCommonsCode.GameStatus_publish.value().equals(errorCode)) {
						game.setStatus(status);
						gameparamMapper.update(game);
						ajaxObj.setResult(1);
						if(AppCommonsCode.GameStatus_1.value().equals(status)){
							ajaxObj.setMsg("活动发布成功！");
						}else{
							ajaxObj.setMsg("活动下线成功！");
						}
						
					} else {
						ajaxObj.setResult(0);
						ajaxObj.setMsg(errorMsg);
					}
				} else {
					logger.error("发布活动接口无数据返回");
					ajaxObj.setResult(0);
					if(AppCommonsCode.GameStatus_1.value().equals(status)){
						ajaxObj.setMsg("活动发布失败！");
					}else{
						ajaxObj.setMsg("活动下线失败！");
					}
					return ajaxObj;
				}
			} catch (Exception e) {
				logger.error("发布活动接口调用失败{}", e);
				ajaxObj.setResult(0);
				if(AppCommonsCode.GameStatus_1.value().equals(status)){
					ajaxObj.setMsg("活动发布失败！");
				}else{
					ajaxObj.setMsg("活动下线失败！");
				}
				return ajaxObj;
			}
		}
		return ajaxObj;
	}

	/**
	 * 获取 接口校验秘钥
	 * 
	 * @param gameid
	 * @return
	 */
	private String getSign(String gameid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("gameid", gameid);
		String paramSign = Sign.getSign(map, globalProperties.getTokenSecret());
		return paramSign;
	}

	/**
	 * 显示二维码
	 */
	@Override
	public void showCode(HttpServletRequest request, HttpServletResponse response) {
		logger.info("开始显示二维码");
		String gameid = request.getParameter("gameid");
		if (StringUtils.isNotBlank(gameid)) {
			CloudgameparamEntity game = gameparamMapper.selectByGameid(gameid);
			if (game != null) {
				AuthAccount account = authAccountMapper.selectOneByAppid(game.getAppid());
				if (account != null) {
					String headImg = account.getHeadImg();
					logger.info("开始创建公众号logo");
					String logoPath = createAppLogo(game.getAppid(), headImg);
					boolean qrFlag = createQR(gameid, logoPath);
					if(qrFlag){
						operaqr(gameid, response);
					}
				}
			}
		}
	}
		/*创建公众号logo文件路径*/
		private String createAppLogo(String appid, String headImg) {
			logger.info("开始创建公众号logo" + appid);
			StringBuilder builder = new StringBuilder();
			builder.append(globalProperties.getAppLogoPath());
			builder.append(appid);
			builder.append(".png");
			String logoPath = builder.toString();
			logger.info("公众号logo路径" + logoPath);
			File dir = new File(globalProperties.getAppLogoPath());
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File logo = new File(logoPath);
			if (!logo.exists()) {
				try {
					logo.createNewFile();
					boolean downflag = downloadImage(headImg, logoPath);
					if (downflag) {
						logger.info("logo下载成功");
						return logoPath;
					} else {
						logger.error("logo图片下载失败" + headImg);
						return null;
					}
				} catch (IOException e) {
					logger.error("创建文件异常", e);
					return null;
				}
			}

			return logoPath;
		}
		/*下载公众号logo图片方法*/
		private boolean downloadImage(String fromUrl, String toPath) {
			logger.info("downloadImage:下载logo");
			try {
				URL url = new URL(fromUrl);
				File outFile = new File(toPath);
				OutputStream os = new FileOutputStream(outFile);
				InputStream is = url.openStream();
				byte[] buff = new byte[1024];
				while (true) {
					int readed = is.read(buff);
					if (readed == -1) {
						break;
					}
					byte[] temp = new byte[readed];
					System.arraycopy(buff, 0, temp, 0, readed);
					os.write(temp);
				}
				is.close();
				os.close();
				return true;
			} catch (Exception e) {
				logger.error("图片下载失败", e);
				return false;
			}
		}	
	/*图片显示方法*/
	private void operaqr(String gameid , HttpServletResponse response){
		StringBuilder builder = new StringBuilder();
		try {
			builder.append(globalProperties.getGameQRPath());
			builder.append(gameid);
			builder.append(".png");
			File file = new File(builder.toString());
			logger.info("图片地址是：" + builder.toString());
			response.reset();
			response.setCharacterEncoding("utf-8");
			response.setContentType("image/png; charset=utf-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String(file.getName().getBytes("utf-8"), "utf-8"));
			OutputStream output = null;
			FileInputStream fis = null;
			try {
				output = response.getOutputStream();
				fis = new FileInputStream(file);

				byte[] b = new byte[1024];
				int i = 0;

				while ((i = fis.read(b)) != -1) {

					output.write(b, 0, i);
				}
				output.flush();
				response.flushBuffer();
			} catch (Exception e) {
				logger.error("图片显示异常{}", e);
			} finally {
				if (fis != null) {
					fis.close();
					fis = null;
				}
				if (output != null) {
					output.close();
					output = null;
				}
			}
		} catch (Exception e) {
			logger.error("图片显示异常{}", e);
		}
	}

	private boolean createQR(String gameid, String logoPath) {
		logger.info("createQR : 创建二维码");
		CloudgameparamEntity gameparam = gameparamMapper.selectByGameid(gameid);
		Map<String, Object> map = new HashMap<>();
		map.put("tempid", gameparam.getTempid());
		List<GametemplateEntity> temps = tempMapper.select(map);
		StringBuilder contentBuilder = new StringBuilder();
		if(CollectionUtils.isNotEmpty(temps)){
			String tempname = temps.get(0).getTempname();
			contentBuilder.append(globalProperties.getGameURI());
			contentBuilder.append(tempname.substring(0, tempname.indexOf("_")));
			contentBuilder.append("/");
			contentBuilder.append(tempname.substring(0, tempname.indexOf(".")));
			contentBuilder.append("?gameid=");
			contentBuilder.append(gameid);
			
		}
		StringBuilder builder = new StringBuilder();
		builder.append(globalProperties.getGameQRPath());
		File qrdir = new File(builder.toString());
		if (!qrdir.exists()) {
			qrdir.mkdirs();
		}
		builder.append(gameid);
		builder.append(".png");
		String qrPath = builder.toString();
		File qrfile = new File(qrPath);
		try {
			if (!qrfile.exists()) {
				qrfile.createNewFile();
			}
			if (qrfile.length() == 0) {
				ZXingCodeUtil zp = new ZXingCodeUtil(); // 实例化二维码工具
				ZXingConfig zxingconfig = new ZXingConfig(); // 实例化二维码配置參数
				zxingconfig.setHints(zp.getDecodeHintType()); // 设置二维码的格式參数
				zxingconfig.setContent(contentBuilder.toString());// 设置二维码生成内容
				zxingconfig.setLogoPath(logoPath); // 设置Logo图片
				zxingconfig.setLogoConfig(new LogoConfig()); // Logo图片參数设置
				zxingconfig.setLogoFlg(true); // 设置生成Logo图片
				BufferedImage bim = zp.getQR_CODEBufferedImage(zxingconfig);// 生成二维码
				ImageIO.write(bim, "png", qrfile); // 图片写出
				return true;
			}
		} catch (Exception e) {
			logger.error("创建二维码异常{}", e);
			return false;
		}
		return true;
	}



	/**
	 * 拷贝地址到粘贴板
	 */
	@Override
	public AjaxObj copyGameURI(String gameid) {
		logger.info("copyGameURI：开始拷贝二维码链接地址！");
		AjaxObj ajaxObj = new AjaxObj();
		String gameURI = globalProperties.getGameURI();
		CloudgameparamEntity game = gameparamMapper.selectByGameid(gameid);
		Map<String, Object> map = new HashMap<>();
		map.put("tempid", game.getTempid());
 		List<GametemplateEntity> temp = tempMapper.select(map);
 		if(CollectionUtils.isNotEmpty(temp)){
 			StringBuilder builder = new StringBuilder();
 			String tempname = temp.get(0).getTempname();
 	 		builder.append(gameURI);
 	 		builder.append(tempname.substring(0, tempname.indexOf(".")));
 	 		builder.append("?gameid=");
 	 		builder.append(gameid);
 			Toolkit toolkit = Toolkit.getDefaultToolkit();
 			Clipboard clipboard = toolkit.getSystemClipboard();
 			StringSelection stringSel = new StringSelection(builder.toString());
 			clipboard.setContents(stringSel, null);
 		}
		return ajaxObj;
	}
	
	/**
	 * 获取游戏链接地址
	 */
	@Override
	public AjaxObj getGameURI(String gameid) {
		logger.info("copyGameURI：开始拷贝二维码链接地址！");
		AjaxObj ajaxObj = new AjaxObj();
		String gameURI = globalProperties.getGameURI();
		CloudgameparamEntity game = gameparamMapper.selectByGameid(gameid);
		Map<String, Object> map = new HashMap<>();
		map.put("tempid", game.getTempid());
 		List<GametemplateEntity> temp = tempMapper.select(map);
 		if(CollectionUtils.isNotEmpty(temp)){
 			StringBuilder builder = new StringBuilder();
 			String tempname = temp.get(0).getTempname();
 	 		builder.append(gameURI);
 	 		builder.append(tempname.substring(0, tempname.indexOf("_")));
 	 		builder.append(File.separator);
 	 		builder.append(tempname.substring(0, tempname.indexOf(".")));
 	 		builder.append("?gameid=");
 	 		builder.append(gameid);
 	 		String uri = builder.toString();
 	 		ajaxObj.setResult(1);
 	 		ajaxObj.setObj(uri);
 		}else{
 			ajaxObj.setResult(0);
 		}
		return ajaxObj;
	}

	/**
	 * 下载二维码
	 * @throws IOException 
	 */
	@Override
	public ResponseEntity<byte[]> downQR(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String gameid = request.getParameter("gameid");
		StringBuilder builder = new StringBuilder();
		builder.append(globalProperties.getGameQRPath());
		builder.append(gameid);
		builder.append(".png");
		File file = new File(builder.toString());
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.IMAGE_PNG);
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+gameid+".png");
		byte[] datafiles = FileUtils.readFileToByteArray(file);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(datafiles, header, HttpStatus.CREATED);
		return responseEntity;
	}
}
