package cn.com.jansh.service.game.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.IDUtils;
import com.jansh.core.exception.AppException;

import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.constant.ContextCode;
import cn.com.jansh.entity.game.CloudGameTemplateTypeEntity;
import cn.com.jansh.entity.game.GameTemplate;
import cn.com.jansh.mapper.game.GameTemplateMapper;
import cn.com.jansh.model.game.CloudGameTemplateModel;
import cn.com.jansh.service.game.GameTemplateService;
import cn.com.jansh.service.system.GlobalPropertiesService;

/**
 * 活动模板管理实现类
 * 
 */
@Service("gameTemplateSerivice")
public class GameTemplateServiceImpl implements GameTemplateService {
	private static final Logger logger = LogManager.getLogger(GameTemplateServiceImpl.class);
	@Autowired
	private GameTemplateMapper gameTemplateMapper;
	@Autowired
	private GlobalPropertiesService globalProperties;
	@Autowired
	private GlobalPropertiesService propertiesService;
	@Override
	public void saveTemp(String templateId, String tempname, String gameid,String tmptypeid,String templateName) {
		GameTemplate gameTemplate = new GameTemplate();
		gameTemplate.setTempid(templateId);
		gameTemplate.setTempname(tempname);
		gameTemplate.setTemplatename(templateName);
		gameTemplate.setCreatetime(DateUtil.getDateTimestamp());
		gameTemplate.setUpdatetime(DateUtil.getDateTimestamp());
		gameTemplate.setUsetimes(ContextCode.GAMETEMPLATE_USETIMES.value());
		gameTemplate.setGameid(gameid);
		gameTemplate.setTmptypeid(tmptypeid);
		gameTemplate.setTempstatus(ContextCode.GAMETEMPLATE_TEMPSTATUS.value());
		gameTemplateMapper.insert(gameTemplate);
	}

	/**
	 * 根据id查询模板信息
	 */
	@Override
	public List<GameTemplate> queryByGameid(String gameid) {
		
		List<GameTemplate> list = gameTemplateMapper.queryByGameid(gameid);

		for(int i=0; i<list.size(); i++){
			
			//将数据库中的时间格式转换成 yyyy-MM-dd HH:mm:ss用于显示
			list.get(i).setCreatetime(DateUtil.formatDateTimestamp(list.get(i).getCreatetime()));
			
			if(StringUtils.isNotBlank(list.get(i).getUpdatetime())) {
				list.get(i).setUpdatetime(DateUtil.formatDateTimestamp(list.get(i).getUpdatetime()));
			}
		}
		
		return list;
	}

	/**
	 * 根据模版名称查询模版
	 */
	@Override
	public List<GameTemplate> selectBytemplateName(String tempname,String tmptypeid,String templateName, String gameid) {
		return gameTemplateMapper.selectBytemplateName(tempname,tmptypeid,templateName,gameid);
	}

	/**
	 * 通过模版id查询模版
	 */
	@Override
	public GameTemplate selectByTempid(String tempid) {
		return gameTemplateMapper.selectByTempid(tempid);
	}

	/**
	 * 修改模版数据
	 */
	@Override
	public void editdata(GameTemplate gameTemplate) {
		gameTemplate.setUpdatetime(DateUtil.getDateTimestamp());
		gameTemplateMapper.editdata(gameTemplate);
	}

	/**
	 * 通过模版名和游戏id查询模版
	 */
	@Override
	public List<GameTemplate> selectBytmpNameAndGameid(CloudGameTemplateModel cloudGameTemplateModel) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("templatename", cloudGameTemplateModel.getTemplatename());
		map.put("gameid", cloudGameTemplateModel.getGameid());
		map.put("tempid", cloudGameTemplateModel.getTempid());
		map.put("tmptypeid", cloudGameTemplateModel.getTmptypeid());
		return gameTemplateMapper.selectBytmpNameAndGameid(map);
	}

	/**
	 * 上传图片，并存库
	 * @throws Exception 
	 */
	@Override
	public void uploadspic(String tempid,String tempname,MultipartFile myfiles) throws Exception {
		logger.info("上传图片，并存库");
		//图片上传路径拼接
		String[] newName = tempname.split("_"); //将文件名字进行分割
		String route1 = newName[0].trim(); //拼装二级目录
		String path = propertiesService.getGameUploadBasedir() + File.separator + route1 + File.separator + route1+"_image" + File.separator;// "F:/gameres/user/"+"11/logo";
		//生成随机数
		String random = IDUtils.getTimeRandon();
		
		//上传图片至服务器
		uploadImageToLocal(myfiles,random,path);
		//组装存库参数
		GameTemplate gameTemplate= new GameTemplate();
		//模版id
		gameTemplate.setTempid(tempid);
		//组装图片URL
		String url = propertiesService.getAcquirepictureurl() + File.separator+ route1 + File.separator+ route1+"_image"+ File.separator + random+".png";
		logger.info("上传图片url:{}",url);
		//图片URL
		gameTemplate.setPictrueurl(url);
		//更新时间
		gameTemplate.setUpdatetime(DateUtil.getDateTimestamp());
		logger.info("上传图片entity:{}",gameTemplate);
		//存库
		gameTemplateMapper.editdata(gameTemplate);
		
	}
	/**
	 * 上传图片
	 * @param myfiles
	 * @param random
	 * @param imagePath
	 * @throws Exception
	 */
	private void uploadImageToLocal(MultipartFile myfiles, String random, String imagePath) throws Exception{
		logger.info("上传图片至服务器");
		if (myfiles.isEmpty()) {
			return;
		}
		String imageType = myfiles.getContentType();
		
		if (imageType.equals("image/png")) {
			imageType = "png";
		} else if (imageType.equals("image/jpg")) {
			imageType = "jpg";
		} else if (imageType.equals("image/jpeg")) {
			imageType = "jpeg";
		} else {
			logger.error("图片类型不符合要求！");
			throw new AppException(AppErrorCode.E250003);
		}
		long imageSize = myfiles.getSize();
		if (imageSize > Integer.parseInt(globalProperties.getImageMaxSize()) * 1024 * 1024) {
			logger.error("图片过大！");
			throw new AppException(AppErrorCode.E250002);
		}
		StringBuilder builder = new StringBuilder();
		builder.append(imagePath);
		builder.append(random);
		builder.append(".");
		// 再添加上图片类型
		builder.append("png");
		String iconPathAndName = builder.toString();
		File toPath = new File(imagePath);
		File toFile = new File(iconPathAndName);

		if (!toPath.exists()) {
			toPath.mkdirs();
		}
		try {
			if (!toFile.exists()) {
				toFile.createNewFile();
			}
			OutputStream output = null;
			InputStream fis = null;
			try {
				output = new FileOutputStream(toFile);
				fis = compressPic(myfiles.getInputStream());
				if(fis == null){
					fis = myfiles.getInputStream();
				}
				byte[] b = new byte[1024];
				int i = 0;

				while ((i = fis.read(b)) != -1) {

					output.write(b, 0, i);
				}
				output.flush();
			} catch (Exception e) {
				logger.error("图片上传异常{}", e);
				throw new AppException(AppErrorCode.E250001);
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
			logger.error("图片上传异常{}", e);
			throw new AppException(AppErrorCode.E250001);
		}
	}
	/**
	 * 图片压缩处理
	 * @param input
	 * @return
	 */
	private  InputStream compressPic(InputStream input) {
		int legth = 300;
		BufferedImage img;
		BufferedImage tag = null;
		ImageOutputStream imOut = null;
		InputStream is = null;
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		try {
			imOut = ImageIO.createImageOutputStream(bs);
			img = ImageIO.read(input);
			if (img.getWidth(null) > legth || img.getHeight(null) > legth) {
				logger.info("img.getWidth(null):" + img.getWidth(null) + ",img.getHeight(null):" + img.getHeight(null));
				int newWidth;
				int newHeight;
				double rate = img.getWidth(null) > img.getHeight(null) ? ((double) img.getHeight(null)) / (double) legth
						: ((double) img.getWidth(null)) / (double) legth;
				newWidth = (int) (((double) img.getWidth(null)) / rate);
				newHeight = (int) (((double) img.getHeight(null)) / rate);
				tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
				ImageIO.write(tag, "jpg", imOut);
				is = new ByteArrayInputStream(bs.toByteArray());
			} else {
				return null;
			}
		} catch (IOException e) {
			logger.error("压缩图片异常{}", e);
		}
		return is;
	}

	/**
	 * 添加游戏模版分类列表通过gameid
	 */
	@Override
	public List<CloudGameTemplateTypeEntity> selectTmpTypeByGameid(String gameid) {
		logger.info("添加游戏模版分类列表通过gameid{}",gameid);
		return gameTemplateMapper.selectTmpTypeByGameid(gameid);
	}

	/**
	 * 删除模版
	 */
	@Override
	public void deleteTemp(String gameid) {
		logger.info("删除模版通过gameid{}",gameid);
		gameTemplateMapper.deleteTemp(gameid);
	}
}
