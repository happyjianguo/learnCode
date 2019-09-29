package cn.com.jansh.service.weixin.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.com.jansh.utils.DateUtil;
import cn.com.jansh.utils.FileUpload;
import cn.com.jansh.utils.HttpClientUtil;
import cn.com.jansh.utils.IDUtils;
import cn.com.jansh.utils.JsonUtil;
import cn.com.jansh.utils.StringUtil;
import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.entity.weixin.MediaMaterial;
import cn.com.jansh.entity.weixin.NewsMaterialDetai;
import cn.com.jansh.entity.weixin.WXDMessage;
import cn.com.jansh.entity.weixin.NewsMaterial;
import cn.com.jansh.mapper.weixin.IMediaMaterialMapper;
import cn.com.jansh.mapper.weixin.INewsMaterialDetailMapper;
import cn.com.jansh.mapper.weixin.INewsMaterialMapper;
import cn.com.jansh.mapper.weixin.IWXDMessageMapper;
import cn.com.jansh.service.wechat.WxAuthService;
import cn.com.jansh.service.weixin.INewsMaterialService;
import cn.com.jansh.vo.AjaxObj;
/**
 * 图文消息接口Impl
 * @author gll
 * @version 1.0
 */
@Service
public class INewsMaterialServiceImpl implements INewsMaterialService{
	private static final Logger logger = LogManager.getLogger(INewsMaterialServiceImpl.class);
	// 从weixin.propertites中获取访问微信服务器的url和公众号openId
	@Autowired
	private GlobalProperties globalProperties;
	@Autowired
	private INewsMaterialMapper inewsmaterialmapper;
	@Autowired
	private IMediaMaterialMapper materialMapper;
	@Autowired
	private INewsMaterialDetailMapper inewsmaterialdetailmapper;
	@Autowired
	private IMediaMaterialMapper imediamaterialmapper;
	@Autowired
	private IWXDMessageMapper messageMapper;
	//通过appid获取accesstoken接口
	@Autowired
	private WxAuthService wxAuthService;
	@Value("${uploadImagePath}")
	private String realpath;

	/**
	 * 上传图片至微信服务器
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AjaxObj addPicToWeChat(MediaMaterial<MultipartFile> mmModel) {
		AjaxObj ajaxObj = new AjaxObj();
		Map<String, String> picInfoMap = new HashMap<String, String>();
		String fileName = mmModel.getPicSelect().getOriginalFilename();
		long currentTime = System.currentTimeMillis();
		String serverPicUrl = "";
		String picSize = Long.toString(mmModel.getPicSelect().getSize());
		MediaMaterial<MultipartFile> materialModel = new MediaMaterial<>();
		materialModel.setMaterialName(mmModel.getTitle());
		materialModel.setAppid(mmModel.getAppid());
		MediaMaterial<MultipartFile> materialExist = materialMapper.queryMediaByName(materialModel);
		if(materialExist!=null&&!StringUtils.isEmpty(materialExist.getMaterialId())){
			logger.info("图片标题已存在,请重新输入!");
			ajaxObj.setResult(0);
			ajaxObj.setMsg("图片标题已存在,请重新输入!");
			return ajaxObj;
		}
		// 获取图标的路径
		try {
			logger.info("文件名:" + currentTime + "_" + fileName);
			serverPicUrl = realpath + "/" + currentTime + "_" + fileName;
			FileCopyUtils.copy(mmModel.getPicSelect().getInputStream(), new FileOutputStream(serverPicUrl));
		} catch (IOException e) {
			ajaxObj.setResult(0);
			ajaxObj.setMsg("图片上传发生异常!");
			logger.error("图片上传发生异常!", e);
			return ajaxObj;
		}
		try {
			// 获取accessToken
			String uploadUrl = globalProperties.getApiWeixinURL() + "material/add_material?access_token=" + wxAuthService.getAuthAccessToken(mmModel.getAppid()) + "&type=image";
			logger.info("图片开始上传");
			picInfoMap = (Map<String, String>) FileUpload.send(uploadUrl, serverPicUrl);
			logger.info("图片结束上传");
		} catch (Exception e) {
			ajaxObj.setResult(0);
			ajaxObj.setMsg("图片上传发生异常!");
			logger.error("图片上传发生异常!", e);
			return ajaxObj;
		}
		if (null != picInfoMap.get("errcode")) {
			ajaxObj.setResult(0);
			ajaxObj.setMsg("图片上传发生异常!");
			logger.warn(picInfoMap.get("errmsg").toString());
			return ajaxObj;
		}
		String mediaId = (String) picInfoMap.get("media_id");
		String serverUrl = (String) picInfoMap.get("url");
		mmModel.setMaterialName(mmModel.getTitle());
		mmModel.setFileName(fileName);
		mmModel.setFilePath(serverPicUrl);
		mmModel.setMediaId(mediaId);
		mmModel.setMediaUrl(serverUrl);
		mmModel.setFileLength(picSize);
		mmModel.setType("image");
		mmModel.setTaskStatus("0");
		mmModel.setMaterialId(IDUtils.getMsgId());
		mmModel.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(currentTime)));
		mmModel.setUpdateTime(DateUtil.getDate());
		try {
			materialMapper.saveMaterial(mmModel);
			ajaxObj.setResult(1);
			ajaxObj.setMsg("新增图片成功!!!");
		} catch (Exception e) {
			logger.error("新增图片异常{}",e);
			ajaxObj.setResult(0);
			ajaxObj.setMsg("新增图片异常!!!");
		}
		return ajaxObj;
	}

	/**
	 * 新增图文素材数据
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public AjaxObj addNewsToWeChat(String matList) {
		AjaxObj ajaxObj = new AjaxObj();
		Map returnMap = new HashMap();
		Map returnDetailMap = new HashMap();
		ObjectMapper objMapper = new ObjectMapper();
		// 获取AccessToken
		NewsMaterial newsMatModel = new NewsMaterial();
		Map<String, List<Map<String, String>>> newsPic = new HashMap<String, List<Map<String, String>>>();
		List<Map<String, String>> newsLst = new ArrayList<Map<String, String>>();
		List<NewsMaterialDetai> newsMatDetailLst = new ArrayList<NewsMaterialDetai>();
		NewsMaterialDetai newsMatDetail = null;
		// 创建时间
		String currentTime = DateUtil.getDate();
		String mediaId = "";
		String materialId = IDUtils.getMsgId();
		try {
			// 获取页面输入的图文消息内容
			returnMap = new ObjectMapper().readValue(matList, Map.class);
			NewsMaterial  materialExist = new NewsMaterial();
			materialExist.setMaterialName((String) returnMap.get("materialName"));
			materialExist.setAppid((String) returnMap.get("appid"));
			NewsMaterial  material = inewsmaterialmapper.queryNewsByMaterialName(materialExist);
			if(material!=null&&!StringUtils.isEmpty(material.getMaterialId())){
				logger.info(returnMap.get("materialName")+" 素材名称已经存在");
				ajaxObj.setResult(0);
				ajaxObj.setMsg("标题已存在，请重新输入！");
				return ajaxObj;
			}
			List matLst = (List) returnMap.get("materialLst");
			int i = 0;
			// 遍历素材明细
			for (Object materialObj : matLst) {
				Map<String, String> conMap = new HashMap<String, String>();
				newsMatDetail = new NewsMaterialDetai();
				objMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
				returnDetailMap = objMapper.readValue(JsonUtil.obj2json(materialObj), Map.class);
				conMap.put("title", returnDetailMap.get("title").toString());
				conMap.put("thumb_media_id", returnDetailMap.get("thumb_media_id").toString());
				conMap.put("author", returnDetailMap.get("author").toString());
				conMap.put("digest", returnDetailMap.get("digest").toString());
				conMap.put("show_cover_pic", returnDetailMap.get("show_cover_pic").toString());
//				conMap.put("content", returnDetailMap.get("content").toString());
				String s1 = StringUtil.htmlDecode(returnDetailMap.get("content").toString());
				//将新增素材时的图和文字做处理
				conMap.put("content", findstr(s1));
				conMap.put("content_source_url", returnDetailMap.get("content_source_url").toString());
				newsMatDetail.setDetailId(materialId + i);
				newsMatDetail.setMaterialId(materialId);
				newsMatDetail.setMaterialTitle(returnDetailMap.get("title").toString());
				newsMatDetail.setThumbMediaId(returnDetailMap.get("thumb_media_id").toString());
				newsMatDetail.setAuthor(returnDetailMap.get("author").toString());
				newsMatDetail.setDigest(returnDetailMap.get("digest").toString());
				newsMatDetail.setShowCoverPic(returnDetailMap.get("show_cover_pic").toString());
				newsMatDetail.setContent(returnDetailMap.get("content").toString());
				String contentUrl = returnDetailMap.get("content_source_url").toString();
				if(StringUtils.isNotBlank(contentUrl)){
					if(contentUrl.indexOf("?openid=")==-1){
						contentUrl+="?openid=";
					}
				}
				newsMatDetail.setContentSourceUrl(contentUrl);
				newsMatDetail.setThumbMediaUrl(returnDetailMap.get("thumbUrl").toString());
				newsMatDetail.setDetailSort(i + "");
				newsMatDetail.setCreateTime(currentTime);
				newsMatDetail.setUpdateTime(currentTime);
				newsMatDetailLst.add(newsMatDetail);
				newsLst.add(conMap);
				i++;
			}
			// 组装成微信服务器识别的素材上传报文
			newsPic.put("articles", newsLst);
			String sendNewsStr = objMapper.writeValueAsString(newsPic);
			String accesstoken = wxAuthService.getAuthAccessToken((String) returnMap.get("appid"));
			String addNewsURL = globalProperties.getApiWeixinURL() + "material/add_news?access_token=" + accesstoken;
			// 发送报文
			String res = HttpClientUtil.httpPost(addNewsURL, sendNewsStr);
			Map sendResMap = new ObjectMapper().readValue(res, Map.class);
			if (null != sendResMap.get("errcode")) {
				ajaxObj.setResult(0);
				ajaxObj.setMsg("图文上传发生异常!");
				logger.error("图文上传发生异常!");
				return ajaxObj;
			} else {
				// 取得mediaID
				mediaId = String.valueOf(sendResMap.get("media_id"));
			}
			// 更新图文素材表
			newsMatModel.setMaterialId(materialId);
			newsMatModel.setMaterialName((String) returnMap.get("materialName"));
			newsMatModel.setMediaId(mediaId);
			newsMatModel.setMediaidStatus("1");
			newsMatModel.setUpdateTime(currentTime);
			newsMatModel.setCreateTime(currentTime);
			newsMatModel.setAppid(String.valueOf(returnMap.get("appid")));
			inewsmaterialmapper.saveNewsMaterial(newsMatModel);
			// 更新图文素材明细表
			inewsmaterialdetailmapper.saveNewsMaterialDetail(newsMatDetailLst);
		} catch (Exception e) {
			ajaxObj.setResult(0);
			ajaxObj.setMsg("保存图文失败!!!");
			logger.warn("保存图文失败!!!", e);
			return ajaxObj;
		}
		logger.info("图文素材新增成功");
		ajaxObj.setResult(1);
		ajaxObj.setMsg("图文素材新增成功!");
		return ajaxObj;
	}
	/**
	 * 修改图片数据
	 */
	@Override
	public AjaxObj updatePicInfo(MediaMaterial<MultipartFile> mmModel) {
		AjaxObj ajaxObj = new AjaxObj();
		logger.info("开始更新图片素材");
		try {
			materialMapper.updateMediaMaterial(mmModel);
			ajaxObj.setResult(1);
			ajaxObj.setMsg("更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxObj.setResult(0);
			ajaxObj.setMsg("更新失败");
		}
		return ajaxObj;
	}
	/**
	 * 删除图文素材
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public AjaxObj delNewsMaterialToWeChat(String materialId) {
		AjaxObj ajaxObj = new AjaxObj();
		try {
			Map<String, String> delData = new HashMap<String, String>();
			NewsMaterial materialModel =  inewsmaterialmapper.queryNewsByMaterialId(materialId);
			Map<String, String> params = new HashMap<>();
			params.put("mediaId", materialModel.getMaterialId());
			//通过mediaID查询该图文素材有没有被消息管理使用
			List<WXDMessage> messages = messageMapper.queryPageMessageByParams(params);
			if(messages!=null&&messages.size()>0){
				ajaxObj.setResult(0);
				ajaxObj.setMsg("图片素材正被使用，无法删除!");
				logger.info("图片删除发生异常!");
				return ajaxObj;
			}
			ObjectMapper mapper = new ObjectMapper();
			String mediaId = materialModel.getMediaId();
			delData.put("media_id",mediaId);
			String accessToken = wxAuthService.getAuthAccessToken(materialModel.getAppid());
			String delMatUrl = globalProperties.getApiWeixinURL() + "material/del_material?access_token=" + accessToken;
			String sendNewsStr = mapper.writeValueAsString(delData);
			String res = HttpClientUtil.httpPost(delMatUrl, sendNewsStr);
			Map sendResMap = new ObjectMapper().readValue(res, Map.class);
			if (null!= sendResMap && null != sendResMap.get("errcode")) {
				if (!"0".equals(String.valueOf(sendResMap.get("errcode")))) {
					ajaxObj.setResult(0);
					ajaxObj.setMsg("图片删除发生异常!");
					logger.warn("图片删除发生异常!" + sendResMap.get("errmsg"));
					return ajaxObj;
				}
			}
				// 删除图文素材
				inewsmaterialmapper.deleteNewsMaterial(mediaId);
				//删除图文素材明细
				inewsmaterialdetailmapper.deleteNewsMaterialDetail(materialId);
		} catch (Exception e) {
			logger.error("图片删除异常{}",e);
			ajaxObj.setResult(0);
			ajaxObj.setMsg("系统异常，删除素材失败!!!");
			return ajaxObj;
		}
		ajaxObj.setResult(1);
		ajaxObj.setMsg("删除素材成功!!!");
		return ajaxObj;
	}
	@SuppressWarnings({ "rawtypes", "unused" })
	/**
	 * 删除图片数据
	 */
	@Override
	public AjaxObj delMaterialToWeChat(String materialId) {
		AjaxObj ajaxObj = new AjaxObj();
		try {
			Map<String, String> delData = new HashMap<String, String>();
			MediaMaterial materialModel =materialMapper.queryMediaMaterialById(materialId);
			//使用微信返回的URL查询图片有没有被使用
			List<NewsMaterialDetai> detais = inewsmaterialdetailmapper.queryDetailByMediaUrl(materialModel.getMediaUrl());
			if(detais!=null&&detais.size()>0){
				ajaxObj.setResult(0);
				ajaxObj.setMsg("该图片素材正被使用无法删除!");
				logger.warn("该图片素材正被使用无法删除!");
				return ajaxObj;
			}
			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> maps = null;
			String materialFlg = materialModel.getType();
			String mediaId = materialModel.getMediaId();
			delData.put("media_id",mediaId);
			String accessToken = wxAuthService.getAuthAccessToken(materialModel.getAppid());
			String delMatUrl = globalProperties.getApiWeixinURL() + "material/del_material?access_token=" + accessToken;
			String sendNewsStr = mapper.writeValueAsString(delData);
			String res = HttpClientUtil.httpPost(delMatUrl, sendNewsStr);
			Map sendResMap = new ObjectMapper().readValue(res, Map.class);
			if (null != sendResMap.get("errcode")) {
				if (!"0".equals(String.valueOf(sendResMap.get("errcode")))) {
					ajaxObj.setResult(0);
					ajaxObj.setMsg("图片删除发生异常!");
					logger.warn("图片上传发生异常!" + sendResMap.get("errmsg"));
					return ajaxObj;
				}
			}
			if (null != materialFlg && !"".equals(materialFlg) && "image".equals(materialFlg)) {
				//删除图片素材
				materialMapper.delMediaMaterial(materialId);
			} else if (null != materialFlg && !"".equals(materialFlg) && "news".equals(materialFlg)) {
				// 删除图文素材
				inewsmaterialmapper.deleteNewsMaterial(mediaId);
				inewsmaterialdetailmapper.deleteNewsMaterialDetail(materialId);
			}
		} catch (Exception e) {
			ajaxObj.setResult(0);
			ajaxObj.setMsg("系统异常，删除素材失败!!!");
			e.printStackTrace();
			return ajaxObj;
		}
		ajaxObj.setResult(1);
		ajaxObj.setMsg("删除素材成功!!!");
		return ajaxObj;
	}
	/**
	 * 更新图文素材
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public AjaxObj updateNewsMaterial(String matList) {
		AjaxObj ajaxObj = new AjaxObj();
		Map returnMap;
		try {
			returnMap = new ObjectMapper().readValue(matList, Map.class);
			NewsMaterial  materialExist = new NewsMaterial();
			materialExist.setMaterialName((String) returnMap.get("materialName"));
			materialExist.setAppid((String) returnMap.get("appid"));
			materialExist.setMaterialId((String) returnMap.get("materialId"));
			//查询图文素材
			NewsMaterial  material = inewsmaterialmapper.queryNewsByMaterialName(materialExist);
			if(material!=null&&!materialExist.getMaterialId().equals(material.getMaterialId())){
				logger.info(returnMap.get("materialName")+" 素材名称已经存在");
				ajaxObj.setResult(0);
				ajaxObj.setMsg("标题已存在，请重新输入!");
				return ajaxObj;
			}
			ObjectMapper objMapper = new ObjectMapper();
			List matLst = (List) returnMap.get("detailModels");
			NewsMaterial newsMaterial = new NewsMaterial();
			newsMaterial.setMaterialName((String)returnMap.get("materialName"));
			newsMaterial.setMaterialId((String)returnMap.get("materialId"));
			newsMaterial.setUpdateTime(DateUtil.getDate());
			inewsmaterialmapper.updateNewsMaterial(newsMaterial);
			for (Object newsDetail : matLst) {
				Map returnDetailMap = (Map)newsDetail;
				NewsMaterialDetai detailModel = new NewsMaterialDetai();
				detailModel.setAuthor(returnDetailMap.get("author").toString());
				detailModel.setContent(returnDetailMap.get("content").toString());
				String contentUrl = returnDetailMap.get("contentSourceUrl").toString();
				if(StringUtils.isNotBlank(contentUrl)){
					if(contentUrl.indexOf("?openid=")==-1){
						contentUrl+="?openid=";
					}
				}
				detailModel.setContentSourceUrl(contentUrl);
				detailModel.setCreateTime(returnDetailMap.get("createTime").toString());
				detailModel.setDetailId(returnDetailMap.get("detailId").toString());
				detailModel.setDetailSort(returnDetailMap.get("detailSort").toString());
				detailModel.setDigest(returnDetailMap.get("digest").toString());
				detailModel.setMaterialId(returnDetailMap.get("materialId").toString());
				detailModel.setMaterialTitle(returnDetailMap.get("materialTitle").toString());
				detailModel.setShowCoverPic(returnDetailMap.get("showCoverPic").toString());
				detailModel.setThumbMediaId(returnDetailMap.get("thumbMediaId").toString());
				detailModel.setThumbMediaUrl(returnDetailMap.get("thumbMediaUrl").toString());
				String filePath = returnDetailMap.get("thumbMediaUrl").toString();
				//通过URL查询图片
				MediaMaterial mediaM = materialMapper.selectmaterialByFileUrl(filePath);
				if(null != mediaM){
					detailModel.setThumbMediaUrl(mediaM.getMediaUrl());
				}else{
					detailModel.setThumbMediaUrl(returnDetailMap.get("thumbMediaUrl").toString());
				}
				detailModel.setUpdateTime(returnDetailMap.get("updateTime").toString());
				detailModel.setWxd_materialId(returnDetailMap.get("wxd_materialId").toString());
//				更新素材明细
				ajaxObj = updateNewsDetail(detailModel);
			}
		} catch (Exception e) {
			logger.error("更新素材异常{}",e);
			ajaxObj.setMsg("更新素材失败");
			ajaxObj.setResult(0);
			return ajaxObj;
		}
		ajaxObj.setMsg("更新素材成功");
		ajaxObj.setResult(1);
		return ajaxObj;
	}
	/**
	 * 更新素材明细
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public AjaxObj updateNewsDetail(NewsMaterialDetai newsDetail) {
		AjaxObj ajaxObj = new AjaxObj();
		try {
			Map<String, String> conMap = new HashMap<String, String>();
			Map<String, Object> newsPic = new HashMap<String, Object>();
			Map maps ;
			NewsMaterial newsMaterial = inewsmaterialmapper.queryNewsByMaterialId(newsDetail.getMaterialId());
			conMap.put("title", newsDetail.getMaterialTitle());
			conMap.put("thumb_media_id", newsDetail.getThumbMediaId());
			conMap.put("author", newsDetail.getAuthor());
			conMap.put("digest", newsDetail.getDigest());
			conMap.put("show_cover_pic", newsDetail.getShowCoverPic());
//			conMap.put("content", newsDetail.getContent());
			String s1 = StringUtil.htmlDecode(newsDetail.getContent());
			//将新增素材时的图和文字做处理 
			conMap.put("content", findstr(s1));
			conMap.put("content_source_url", newsDetail.getContentSourceUrl());
			newsPic.put("media_id", newsMaterial.getMediaId());
			newsPic.put("index", newsDetail.getDetailSort());
			newsPic.put("articles", conMap);
			ObjectMapper mapper = new ObjectMapper();
			String sendNewsStr = mapper.writeValueAsString(newsPic);
			// 获取AccessToken
			String currAccessToken = wxAuthService.getAuthAccessToken(newsMaterial.getAppid());
			// 获取微信服务器接收报文的地址
			String addNewsURL = globalProperties.getApiWeixinURL() + "material/update_news?access_token=" + currAccessToken;
			// 发送报文
			String res = HttpClientUtil.httpPost(addNewsURL, sendNewsStr);
			Map sendResMap = new ObjectMapper().readValue(res, Map.class);
			if (!"0".equals(String.valueOf(sendResMap.get("errcode")))) {
				ajaxObj.setResult(0);
				ajaxObj.setMsg("变更素材内容失败!");
				logger.warn("变更素材内容失败!");
			} else {
				inewsmaterialdetailmapper.updateNewsMaterialDetail(newsDetail);
				ajaxObj.setResult(1);
				ajaxObj.setMsg("变更素材内容成功!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxObj.setResult(0);
			ajaxObj.setMsg("变更素材内容失败!!!");
			logger.warn("变更消息内容失败：", e);
		}
		return ajaxObj;
	}
	/**
	 * 编辑器图片显示
	 * 图片后台入库
	 * 用于图文素材新增和修改
	 * @param ss2
	 * 编辑器内容
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String findstr(String ss2) {
		//定位头
		String s11="<input type=\"hidden\" name=\"materialId\" value=\"";
		//切割之后的字符
		String s12 = "";
		//定位尾
		String s13 = "\"/>";
		//赋初值
		s12 = ss2;
		//判断content中有没有插入图片
		int i = s12.indexOf(s11);
		if(i>0){
			do{
				//截取以s11为界限之后的所有字符并保存至s12
				s12= s12.substring(i+s11.length());
				// 找到input的结束边界“ \"/> ”
				int i12 = s12.indexOf(s13);
				// 截取id（“ \"/> ”）之前的字符
				String id = s12.substring(0,i12);
				//查库，根据素材id查询素材库，实现URL替换
				//mapper.queryByid(id);
				MediaMaterial mediaMaterial = materialMapper.queryMediaMaterialById(id);
				//图片本地路径	TODO	（注意：因为图片使用读流显示，所以需要在数据库保存的本地路径之前加读流controller）
				String old = "/material/show?url="+mediaMaterial.getFilePath();
				
				//新增或修改时向微信后台传递真实URL，存库时保存pc端可显示的本地图片路径即可
				/**
				 * 替换:
				 * 两种替换方式 	
				 * 1、StringUtils.replace(string，old,new);	
				 * 2、string.replace(old ,new);
				 */
				//ss2 = StringUtils.replace(ss2, mediaMaterial.getFileLength(), mediaMaterial.getMediaUrl());
				ss2 = ss2.replace(old , mediaMaterial.getMediaUrl());
				/**
				 * 判断：
				 * 原始字符传被切割之后是否还包含s11
				 * 如果包含
				 * 		继续循环替换
				 * 否则
				 * 		结束替换
				 */
				i = s12.indexOf(s11);
				
			}while(i>0);
		}
		return ss2;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List<MediaMaterial> queryMediaMaterialList() {
		return imediamaterialmapper.queryMediaMaterialList();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List<MediaMaterial> queryMediaMaterialByName(MediaMaterial materialModel) {
		return imediamaterialmapper.queryMediaMaterialByName(materialModel);
	}
	@Override
	public List<NewsMaterial> queryNewsMaterialByName(NewsMaterial materialModel) {
		return inewsmaterialmapper.queryNewsMaterialByName(materialModel);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List<MediaMaterial> queryMediaMaterialByType(String type) {
		return imediamaterialmapper.queryMediaMaterialByType(type);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List<MediaMaterial> queryMediaMaterialByPlatformId(String appid) {
		return imediamaterialmapper.queryMediaMaterialByPlatformId(appid);
	}
	/**
	 * 通过materialId查询素材明细
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<NewsMaterialDetai> queryDetailByMaterialId(String materialId) {
		List<NewsMaterialDetai> list =inewsmaterialdetailmapper.queryDetailByMaterialId(materialId);
		//根据素材编号查询素材明细为了拿到thumbMediaUrl
		for(int i=0;i<list.size();i++){
			List<MediaMaterial>  li = materialMapper.selectMaterialByMediaUrl(list.get(i).getThumbMediaUrl());
//			根据URL查询图片素材可能会查出多个相同图片
			list.get(i).setThumbMediaUrl(li.get(0).getFilePath());
		}
		return list;
	}
	/**
	 * 通过materialId查询素材明细（修改跳转）
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<NewsMaterialDetai> queryDetailByTMaterialId(String materialId) {
		List<NewsMaterialDetai> list =inewsmaterialdetailmapper.queryDetailByMaterialId(materialId);
		//根据素材编号查询素材明细为了拿到thumbMediaUrl
		for(int i=0;i<list.size();i++){
			List<MediaMaterial>  li = materialMapper.selectMaterialByMediaUrl(list.get(i).getThumbMediaUrl());
//			根据URL查询图片素材可能会查出多个相同图片
			list.get(i).setThumbMediaUrl(li.get(0).getFilePath());
		}
		return list;
	}
	/**
	 * 通过素材id查询素材
	 */
	@Override
	public NewsMaterial queryNewsMaterialById(String materialId) {
		return inewsmaterialmapper.queryNewsMaterialById(materialId);
	}
	@Override
	public NewsMaterialDetai queryNewsDetailBydetailId(String detailId) {
		return inewsmaterialdetailmapper.queryNewsDetailBydetailId(detailId);
	}
	@Override
	public void saveNewsMaterial(NewsMaterial mmModel) {
		inewsmaterialmapper.saveNewsMaterial(mmModel);
	}
	@Override
	public void deleteNewsMaterial(String materialId, String mediaId) {
		HashMap<String, String> conditon = new HashMap<>();
		conditon.put("materialId", materialId);
		conditon.put("mediaId", mediaId);
		inewsmaterialmapper.deleteByMediaIdAndMaterialId(conditon);
	}
	@Override
	public void deleteBymediaId(Integer mediaId) {
		inewsmaterialmapper.deleteBymediaId(mediaId);
	}
	/**
	 * 根据appid查询图文素材
	 * @param appid
	 * @return
	 */
	@Override
	public List<NewsMaterial> queryNewsMaterialListByAppid(NewsMaterial materialModel) {
		return inewsmaterialmapper.queryNewsMaterialListByAppid(materialModel);
	}
	/**
	 * 根据platformId查询图文素材
	 * @param platformId
	 * @return
	 */
	@Override
	public List<NewsMaterial> queryNewsMaterialListByplatformId(NewsMaterial materialModel) {
		return inewsmaterialmapper.queryNewsMaterialListByplatformId(materialModel);
	}
	@Override
	public NewsMaterial queryNewsMaterialByMessageId(String msgid) {
		return inewsmaterialmapper.queryNewsMaterialByMessageId(msgid);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List<NewsMaterial> selectMaterialByMsgidAndplatformId(Map materialModel) {
		return inewsmaterialmapper.selectMaterialByMsgidAndplatformId(materialModel);
	}
	/**
	 * 根据THUMBMEDIAURL查询MediaMaterial
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public MediaMaterial selectmaterialByUrl(String url) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("mediaurl", url);
		return materialMapper.selectmaterialByUrl(map);
	}
}
