package cn.com.jansh.controller.game;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jansh.comm.util.IDUtils;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.exception.AppException;
import com.jansh.core.web.servlet.ViewObject;

import cn.com.jansh.entity.game.CloudGameInitEntity;
import cn.com.jansh.entity.game.CloudGameTemplateTypeEntity;
import cn.com.jansh.entity.game.CloudgameparamEntity;
import cn.com.jansh.entity.game.GameTemplate;
import cn.com.jansh.model.game.CloudGameInitModel;
import cn.com.jansh.model.game.CloudGameTemplateModel;
import cn.com.jansh.service.game.GameInitService;
import cn.com.jansh.service.game.GameTemplateService;
import cn.com.jansh.service.system.GlobalPropertiesService;
import cn.com.jansh.util.ZipUtil;
import cn.com.jansh.vo.JsonVO;

/**
 * 活动模板管理页面
 * 
 * @author panc
 * @version 1.0
 */
@Controller
@RequestMapping("/gametemplate")
public class GameTemplateController {

	private static final Logger logger = LogManager.getLogger(GameTemplateController.class);
	@Autowired
	private GlobalPropertiesService propertiesService;
	@Autowired
	private GameTemplateService gameTemplateService;
	@Autowired
	private GameInitService gameInitService;

	/**
	 * 游戏管理页面初始化
	 */
	@RequestMapping("/init")
	public String init(CloudGameInitModel gameinitModel) {
		logger.info("游戏活动模板页面初始化");
		List<CloudGameInitEntity> gameDataList = gameInitService.queryAllData(modelToEntity(gameinitModel));
		gameinitModel.setGameInitList(gameDataList);// 获取所有活动的数据

		return "/game/template/gametemplate";
	}

	/**
	 * 
	 * @param gameid
	 * @return
	 * @throws AppException
	 */
	@RequestMapping("/tempinit")
	public String tempInit(CloudGameInitModel gameinitModel){
		// List<GameTemplate> templateList =
		// gameTemplateService.queryByGameid(gameinitModel.getGameid());
		// gameinitModel.setGameTemplates(templateList);
		logger.info("游戏活动模板管理页面初始化");
		
		if(StringUtils.isEmpty(gameinitModel.getGameid())){
			logger.error("用户刷新重指定URL，跳转错误页面!");
			throw new NullPointerException();
		}
		//添加游戏模版分类列表通过gameid
		List<CloudGameTemplateTypeEntity> list = gameTemplateService.selectTmpTypeByGameid(gameinitModel.getGameid());
		gameinitModel.setGameTemplateTypelist(list);
		return "/game/template/templateupload";
	}
	/**
	 * 根据活动id，获取活动模板
	 * @param gameinitModel
	 * @return
	 * @throws AppException 
	 */
	@ResponseBody
	@RequestMapping("/gametemplates")
	public ViewObject<List<GameTemplate>> queryGameTemplate(CloudGameInitModel gameinitModel) throws AppException {
		List<GameTemplate> templateList = gameTemplateService.queryByGameid(gameinitModel.getGameid());
		return new ViewObject<List<GameTemplate>>(templateList);
	}

	/**
	 * 修改游戏模版初始化页面
	 * @param tempid
	 * @return
	 */
	@RequestMapping(value="/editinit")
	public String editinit(CloudGameTemplateModel cloudGameTemplateModel,Model model){
		logger.info("修改游戏模版初始化",cloudGameTemplateModel);
		
		cloudGameTemplateModel = EntityToModel(gameTemplateService.selectByTempid(cloudGameTemplateModel.getTempid()));
		model.addAttribute(cloudGameTemplateModel);
		
		return "/game/template/templateedit";
	}
	/**
	 * ajax 修改模版
	 * @param cloudGameTemplateModel
	 * @return
	 */
	@RequestMapping(value = "ajax/editdata")
	@ResponseBody
	private JsonVO textMsgAdd(CloudGameTemplateModel cloudGameTemplateModel) {
		logger.info("修改模版{}",cloudGameTemplateModel);
		JsonVO jsonVO = new JsonVO();
		//判断该游戏的模版名是否重复
		List<GameTemplate> list = gameTemplateService.selectBytmpNameAndGameid(cloudGameTemplateModel);
		if(!(null == list || list.size() ==0)){
			logger.error("模版名已存在");
			jsonVO.setMsg("模版名已存在");
			jsonVO.setSuccess(false);
			return jsonVO;
		}
		try {
			//修改数据
			gameTemplateService.editdata(ModelToEntity(cloudGameTemplateModel));
			jsonVO.setMsg("修改模版成功");
			jsonVO.setSuccess(true);
		} catch (Exception e) {
			logger.error("修改模版失败：", e);
			jsonVO.setMsg("修改模版失败");
			jsonVO.setSuccess(false);
			throw e;
		}
		return jsonVO;
	}
	/**
	 * jsp页面使用数据库中的绝对路径将图片转成流
	 * 显示图片
	 * jsp页面只能显示resource中的资源，本地硬盘中的资源不能直接显示
	 * @param request
	 * @param response
	 */
	@RequestMapping("/show")
	@ResponseBody
	public void show(String url ,HttpServletRequest request,HttpServletResponse response){
		logger.info("读流显示图片", url);
		OutputStream output = null;
		InputStream fis = null;
		try {
			response.reset();
			response.setCharacterEncoding("utf-8");
			response.setContentType("image/png; charset=utf-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String((url).getBytes("utf-8"), "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			logger.error("字符编码异常！",e1);
		}
		URL ur;
		try {
			ur = new URL(url);
			URLConnection connection = ur.openConnection();
			fis = connection.getInputStream();
			logger.info("图片地址是：" + url);
		}catch (IOException e) {
				logger.error("图片未找到！",e);
				return;
		}
		try {
			output = response.getOutputStream();
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
			try {
				if (fis != null) {
					fis.close();
					fis = null;
				}
				if (output != null) {
					output.close();
					output = null;
				}
			} catch (Exception e2) {
			}
		}
	}
	
	/**
	 * 上传活动参数 图片
	 */
	@RequestMapping("/uploadpic")
	@ResponseBody 
	public JsonVO uploadPic(@RequestParam(value = "tempid")String tempid ,@RequestParam(value = "tempname")String tempname ,@RequestParam(value = "exampleInputFile")MultipartFile myfiles) {
		logger.info("上传活动参数 图片",tempid,tempname);
		JsonVO jsonVO = new JsonVO();
		try {
			gameTemplateService.uploadspic(tempid,tempname,myfiles);
			jsonVO.setMsg("图片上传成功");
			jsonVO.setSuccess(true);
		} catch (Exception e) {
			logger.error("图片上传失败：", e);
			jsonVO.setMsg("图片上传失败");
			jsonVO.setSuccess(false);
		}
		return jsonVO;
	}
	/**
	 * 判断文件名中“-”出现的个数
	 * 判断文件名称是否合法	1、XXX_XXX_XXX.zip格式	2、文件名只能由数字、字母下划线.号（最后）组成
	 * @param templateName
	 * @return
	 */
	public static Boolean charAtnumAndBoolean(String ss) {
		String strExp="^[A-Za-z0-9_]+$";
		if(!ss.substring(0, ss.length()-4).matches(strExp)){
			return false;
		}
		int count=0;
		for(int i=0;i<ss.length();i++){
			if(ss.charAt(i)=='_'){
				count++;
			}
		}
		if(count != 2){
			return false;
		}else{
			if(ss.indexOf("_") == 0	|| 
				ss.lastIndexOf("_") == ss.length()-5 ||
				ss.lastIndexOf("_")==ss.lastIndexOf("_")-1){
				return false;
			}else{
				return true;
			}
		}
	}
	/**
	 * 上传活动参数 图片 音频视频等媒体素材 压缩包
	 * @param HttpServletRequest
	 * @return AjaxJson
	 */
	@RequestMapping("/uploadtemp")
	@ResponseBody
	public Map<String, String> uploadTemp(@RequestParam(value = "tempfile") MultipartFile myfile,
			@RequestParam(value = "tmptypeid") String tmptypeid, @RequestParam(value = "templatename") String templatename, @RequestParam(value = "gameid") String gameid) {
		logger.info("myfile.getSize()：" + myfile.getSize());
		Map<String, String> dataMap = new HashMap<String, String>();
		String tempname = myfile.getOriginalFilename().trim();
		//判断templateName是否存在
		List<GameTemplate> list = gameTemplateService.selectBytemplateName(tempname,tmptypeid,templatename,gameid);
		if(!(null == list || list.size() == 0)){
			dataMap.put("result", "此模版已存在");
			return dataMap;
		}
		//判断文件名称是否合法	1、XXX_XXX_XXX.zip格式	2、文件名只能由数字、字母下划线.号（最后）组成
		logger.info("判断文件名称是否合法");
		if(charAtnumAndBoolean(tempname)){
			String tempid = IDUtils.getTimeRandon();
			String[] newName = tempname.split("_"); //将文件名字进行分割
			String route1 = newName[0].trim(); //拼装二级目录
//			String route2 = newName[1].trim(); //拼装三级目录
			String path = propertiesService.getGameUploadBasedir() + File.separator + route1 + File.separator;// "F:/gameres/user/"+"11/logo";
			try {
				logger.info("复制文件");
				FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(path, "temp" + ".zip"));
				logger.info("读取压缩包内总文件夹名称");
				String filenames=ZipUtil.readZipFileName(path+"temp" + ".zip");
				//判断压缩包内有没有总文件夹
				if(filenames.equals("")){
					dataMap.put("result", "压缩包内文件夹名有误");
					return dataMap;
				}else{
					if(!filenames.equals(tempname.replace(".zip", "").trim())){
						dataMap.put("result", "压缩包内总文件夹名和压缩包名不一致");
						return dataMap;
					}
				}
				logger.info("解压文件");
				//解压文件
				ZipUtil.decompressZipfile(path + "temp.zip", path);
				gameTemplateService.saveTemp(tempid, tempname, gameid,tmptypeid,templatename);// 同步数据库
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error("活动模板保存文件错误{}", e1);
				dataMap.put("result", "创建目录或者解压文件失败");
				return dataMap;
			}
			dataMap.put("result", "success");
			return dataMap;
		}else{
			dataMap.put("result", "文件名必须为AAA_BBB_CCC.zip格式且必须为数字或者英文!注：AAA代表:游戏名,BBB代表:模版名,CCC代表:编号。");
			return dataMap;
		}
	}

	/**
	 * 删除
	 * @param msgId
	 */
	@RequestMapping(value = "ajax/delete")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	public JsonVO delete(String delmsgid) {
		JsonVO jsvo = new JsonVO();
		logger.info("删除模板");
		try {
			//判断此模版有没有被游戏使用
			List<CloudgameparamEntity> cloudgameparamlist= gameInitService.queryByTempid(delmsgid);
			if(null == cloudgameparamlist || cloudgameparamlist.size() == 0){
				//删除
				gameTemplateService.deleteTemp(delmsgid);
				jsvo.setMsg("删除模板成功");
				jsvo.setSuccess(true);
			}else{
				jsvo.setMsg("删除模板失败,此模版已被使用");
				jsvo.setSuccess(false);
			}
		} catch (Exception e) {
			logger.error("删除模板失败：", e);
			jsvo.setMsg("删除模板失败");
			jsvo.setSuccess(false);
		}
		return jsvo;
	}
	/**
	 * MODEL转换实体
	 * 
	 * @param winningrecordModel
	 * @return
	 */
	private CloudGameInitEntity modelToEntity(CloudGameInitModel cloudGameInitModel) {
		CloudGameInitEntity entity = new CloudGameInitEntity();
		entity.setId(cloudGameInitModel.getId());
		entity.setPlayname(cloudGameInitModel.getPlayname());
		entity.setStatus(cloudGameInitModel.getStatus());
		return entity;
	}
	/**
	 * EntityToModel
	 * @param GameTemplate
	 * @return
	 */
	public CloudGameTemplateModel EntityToModel(GameTemplate gameTemplate){
		CloudGameTemplateModel gameTemplateModel = new CloudGameTemplateModel();
		gameTemplateModel.setCreatetime(gameTemplate.getCreatetime());
		gameTemplateModel.setGameid(gameTemplate.getGameid());
//		gameTemplateModel.setGameTemplate(gameTemplate);
		gameTemplateModel.setPictrueurl(gameTemplate.getPictrueurl());
		gameTemplateModel.setTempid(gameTemplate.getTempid());
		gameTemplateModel.setTemplatename(gameTemplate.getTemplatename());
		gameTemplateModel.setTempname(gameTemplate.getTempname());
		gameTemplateModel.setTempstatus(gameTemplate.getTempstatus());
		gameTemplateModel.setUpdatetime(gameTemplate.getUpdatetime());
		gameTemplateModel.setUsetimes(gameTemplate.getUsetimes());
		gameTemplateModel.setTmptypeid(gameTemplate.getTmptypeid());
		return gameTemplateModel;
	}
	
	/**
	 * ModelToEntity
	 * @param GameTemplate
	 * @return
	 */
	public GameTemplate ModelToEntity(CloudGameTemplateModel gameTemplateModel){
		GameTemplate gameTemplate = new GameTemplate();
		gameTemplate.setCreatetime(gameTemplateModel.getCreatetime());
		gameTemplate.setGameid(gameTemplateModel.getGameid());
		gameTemplate.setPictrueurl(gameTemplateModel.getPictrueurl());
		gameTemplate.setTempid(gameTemplateModel.getTempid());
		gameTemplate.setTemplatename(gameTemplateModel.getTemplatename());
		gameTemplate.setTempname(gameTemplateModel.getTempname());
		gameTemplate.setTempstatus(gameTemplateModel.getTempstatus());
		gameTemplate.setUpdatetime(gameTemplateModel.getUpdatetime());
		gameTemplate.setUsetimes(gameTemplateModel.getUsetimes());
		gameTemplate.setTmptypeid(gameTemplateModel.getTmptypeid());
		return gameTemplate;
	}
}
