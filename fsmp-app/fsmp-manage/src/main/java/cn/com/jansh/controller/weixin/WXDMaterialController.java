package cn.com.jansh.controller.weixin;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.entity.wechat.DefaultAccount;
import cn.com.jansh.entity.weixin.MediaMaterial;
import cn.com.jansh.entity.weixin.NewsMaterial;
import cn.com.jansh.entity.weixin.NewsMaterialDetai;
import cn.com.jansh.mapper.wechat.DefaultAccountMapper;
import cn.com.jansh.service.weixin.INewsMaterialService;
import cn.com.jansh.utils.DateUtil;
import cn.com.jansh.utils.JsonUtil;
import cn.com.jansh.vo.AjaxObj;
import cn.com.jansh.vo.JsonVO;
/**
 * 微信图片素材管理
 * @author gll
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/material")
public class WXDMaterialController {
	@Autowired
	private INewsMaterialService newsMaterialService;	//素材service
	@Autowired
	private DefaultAccountMapper defaultAccountMapper;	//默认公众号 Mapper
	private static final Logger logger = LogManager.getLogger(WXDMaterialController.class);
	// 跳转至素材管理页面
	@RequestMapping("/init")
	public String manager(Model model) {
		/*获取登录用户信息*/
		String userid = AppUtil.getUserDetail().getUserid();
		//通过userid查询defaultAccount
		DefaultAccount defaultAccount = defaultAccountMapper.selectByUserid(userid);
		/*判断当前用户是否有默认公众号*/
		if(null != defaultAccount){
			String appid = defaultAccount.getAppid();
			logger.info("跳转到素材管理页面",appid);
			model.addAttribute("appid", appid);
			return "weixin/materialManager";
		}else{
			logger.info("没有选择默认公众号，请选择默认公众号，跳转到公众号管理界面");
			return "fsmp/wechat/skipwxmanage";
		}
	}
	@RequestMapping("/addMaterial")
	public String materialOpera(String appid,Model model) {
		logger.info("跳转到素材管理页面",appid);
		model.addAttribute("appid", appid);
		return "weixin/materialOpera";
	}
	/**
	 * 修改图文素材页面跳转
	 * @param model1
	 * @param materialModel
	 * @return
	 */
	@RequestMapping("/updateMaterial")
	public String materialUpdate(Model model1,NewsMaterial materialModel) {
		logger.info("跳转到素材管理页面");
		try {
			//通过素材id查询素材
			NewsMaterial model = newsMaterialService.queryNewsMaterialById(materialModel.getMaterialId());
			materialModel.setCreateTime(model.getCreateTime());
			materialModel.setMaterialName(model.getMaterialName());
			materialModel.setMediaId(model.getMediaId());
			materialModel.setMediaidStatus(model.getMediaidStatus());
			materialModel.setAppid(model.getAppid());
			materialModel.setUpdateTime(model.getUpdateTime());
			model1.addAttribute("appid",model.getAppid());
			//通过素材id查询素材明细
//			List<NewsMaterialDetai> detailModels = newsMaterialService.queryDetailByMaterialId(materialModel.getMaterialId());
			List<NewsMaterialDetai> detailModels = newsMaterialService.queryDetailByTMaterialId(materialModel.getMaterialId());
			for(int i=0;i<detailModels.size();i++){
				String contentUrl = detailModels.get(i).getContentSourceUrl();
				int index = contentUrl.indexOf("?openid=");
				if(index>-1){
					contentUrl = contentUrl.substring(0, index);
					detailModels.get(i).setContentSourceUrl(contentUrl);
				}
			}
			materialModel.setDetailModels(detailModels);
			materialModel.setDetailModelsLength(detailModels.size());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取信息失败");
		}
		return "weixin/materialUpdate";
	}
	// 从weixin.propertites中获取访问微信服务器的url和公众号openId
	// 新增图片数据到数据库
	// onclick='update("+{"id":id,"url":url,"name":name,"title":title}+") value="/upload", method=RequestMethod.POST
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/material/init")
	@OperationLog(value=Operation.CREATE,key="350100")
	@RequestMapping(value = "/uploadImage", produces = "text/html;charset=UTF-8" , method=RequestMethod.POST)
	public String addPic(Model model,MediaMaterial<MultipartFile> mmModel, @RequestParam MultipartFile myfiles, HttpServletResponse response) throws IOException {
		AjaxObj ajaxObj = new AjaxObj();
		model.addAttribute("appid", mmModel.getAppid());
		logger.info("myfiles.getSize()：{},{}" + myfiles.getSize(),mmModel.toString());
		String[] fileNames = myfiles.getOriginalFilename().split("\\.");
		if ((!fileNames[1].toLowerCase().equals("jpg") && !fileNames[1].toLowerCase().equals("png") && !fileNames[1].toLowerCase().equals("jpeg")
				&& !fileNames[1].toLowerCase().equals("bmp"))) {
			logger.info(myfiles.getOriginalFilename() + "，上传格式错误：" + myfiles.getOriginalFilename().split("\\.")[1]);
			mmModel.setResflag("2");
			return "weixin/materialManager";
		}
		if (myfiles.getSize() > 2097152) {
			logger.info("上传文件超限制：");
			mmModel.setResflag("2");
			return "weixin/materialManager";
		}
		mmModel.setPicSelect(myfiles);
		response.setContentType("text/html;charset=utf-8");
		ajaxObj = newsMaterialService.addPicToWeChat(mmModel);
		if(ajaxObj.getResult()==0){
			mmModel.setResflag("2");
			mmModel.setResMsg(ajaxObj.getMsg());
			return "weixin/materialManager";
		}
		//设置成功标识
		mmModel.setResflag("1");
		logger.info("新增媒体素材结束------------end");
		return "weixin/materialManager";
	}
	// 图片处理
	@SuppressWarnings("unused")
	private static InputStream compressPic(MultipartFile file) throws IOException {
		logger.info("图片处理");
		int legth = 480;
		BufferedImage img;
		BufferedImage tag = null;
		ImageOutputStream imOut = null;
		InputStream is = null;
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		InputStream input = file.getInputStream();
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
				if (tag.getHeight() < tag.getWidth()) {
					// tag = rotateImage(tag, 90);
				}
				ImageIO.write(tag, file.getOriginalFilename().split("\\.")[1], imOut);
				is = new ByteArrayInputStream(bs.toByteArray());
			} else {
				is = input;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}
	/**
	 * 修改图片
	 * @param mmModel
	 * @return
	 */
	@RequestMapping("/updatePic")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/material/init")
	@OperationLog(value=Operation.UPDATE,key="350101")
	public AjaxObj updatePic(MediaMaterial<MultipartFile> mmModel) {
		logger.info("修改图片");
		AjaxObj ajaxObj = new AjaxObj();
		mmModel.setUpdateTime(DateUtil.getDate());
		mmModel.setMaterialName(mmModel.getTitle());
		ajaxObj = newsMaterialService.updatePicInfo(mmModel);
		ajaxObj.setObj(mmModel);
		return ajaxObj;
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
		//通过get方式获取图片的地址 然后跟硬盘路径拼接成绝对路径
		//通过二进制流 将图片显示到页面上
		try {
			File file = new File(url);
			logger.info("图片地址是："+url);
			 response.reset();
	         response.setCharacterEncoding("utf-8");
	         response.setContentType("image/png; charset=utf-8");
	         response.setHeader("Content-Disposition", "attachment; filename=" + new String(file.getName().getBytes("utf-8"),"utf-8"));
	         OutputStream output = null;
	         FileInputStream fis = null;
	         try{
	             output  = response.getOutputStream();
	             fis = new FileInputStream(file);
	             byte[] b = new byte[1024];
	             int i = 0;
	             while((i = fis.read(b))!=-1)
	             {
	                 output.write(b, 0, i);
	             }
	             output.flush();
	             response.flushBuffer();
	         }
	         catch(Exception e){
	        	 logger.error("图片显示异常{}",e);
	         }
	         finally
	         {
	             if(fis != null)
	             {
	                 fis.close();
	                 fis = null;
	             }
	             if(output != null)
	             {
	                 output.close();
	                 output = null;
	             }
	         }
		} catch (Exception e) {
			logger.error("图片显示异常{}",e);
		}
		 
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping("/selectPic")
	@ResponseBody
	public AjaxObj selectPic(String appid) {
		AjaxObj ajaxObj = new AjaxObj();
		logger.info("------------------开始获取媒体素材");
		try {
			List<MediaMaterial> materialModels = newsMaterialService.queryMediaMaterialByPlatformId(appid);
			ajaxObj.setResult(1);
			ajaxObj.setMsg("获取媒体素材成功");
			ajaxObj.setObj(materialModels);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxObj.setResult(0);
			ajaxObj.setMsg("获取媒体素材失败");
		}
		logger.info("------------------获取媒体素材结束");
		return ajaxObj;
	}
	// 保存文本数据到数据库
	@SuppressWarnings("rawtypes")
	@RequestMapping("/addNews")
	@ResponseBody
	public AjaxObj addNews(Model model,String matList) {
		logger.info("新增图文素材开始------------start");
		AjaxObj ajaxObj = new AjaxObj();
		try {
			Map returnMap = new ObjectMapper().readValue(matList, Map.class);
			model.addAttribute("appid", (String) returnMap.get("appid"));
			ajaxObj = newsMaterialService.addNewsToWeChat(matList);
			logger.info("保存图文成功!!!");
			logger.info("新增图文素材结束------------end");
			return ajaxObj;
		} catch (IOException e) {
			ajaxObj.setResult(0);
			ajaxObj.setMsg("新增图文素材失败!!!");
			logger.warn("新增图文失败!!!", e);
			return ajaxObj;
		}
	}
	// 删除图片
	@RequestMapping("/delPic")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/material/init")
	@OperationLog(value=Operation.DELETE,key="350102")
	public AjaxObj delPic(String materialId) throws Exception {
		AjaxObj ajaxObj = new AjaxObj();
		logger.info("删除媒体素材开始------------start");
		ajaxObj = newsMaterialService.delMaterialToWeChat(materialId);
		logger.info("删除媒体素材结束------------end");
		return ajaxObj;
	}
	// 删除图文素材
	@RequestMapping("/delMaterial")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/material/init")
	@OperationLog(value=Operation.DELETE,key="350200")
	public AjaxObj delMaterial(String materialId) {
		AjaxObj ajaxObj = new AjaxObj();
		logger.info("删除媒体素材开始------------start");
		ajaxObj = newsMaterialService.delNewsMaterialToWeChat(materialId);
		logger.info("删除媒体素材结束------------end");
		return ajaxObj;
	}
	/**
	 * 初始化获取图文消息
	 * @param materialName
	 * @param appid
	 * @return
	 */
	@RequestMapping("/selectMaterialNewsListByPlatformId")
	@ResponseBody
	public AjaxObj selectMaterialNewsListByPlatformId(String materialName, String appid) {
		logger.info("初始化获取图文消息");
		AjaxObj ajaxObj = new AjaxObj();
		List<NewsMaterial> newsMateriallst = new ArrayList<NewsMaterial>();
		try {
			NewsMaterial model = new NewsMaterial();
			model.setAppid(appid);
			if (StringUtils.isEmpty(materialName)) {
				//根据appid获取所有图文消息
				newsMateriallst = newsMaterialService.queryNewsMaterialListByplatformId(model);
				for(int i=0; i<newsMateriallst.size(); i++){
					//拿到materialId素材编号
					String materialId = newsMateriallst.get(i).getMaterialId();
					//根据素材编号查询素材明细为了拿到thumbMediaUrl
					List<NewsMaterialDetai> detailModels = newsMaterialService.queryDetailByMaterialId(materialId);
					newsMateriallst.get(i).setDetailModels(detailModels);
				}
			} else {
				model.setMaterialName(materialName);
				newsMateriallst = newsMaterialService.queryNewsMaterialByName(model);
				for(int i=0; i<newsMateriallst.size(); i++){
					List<NewsMaterialDetai> detailModels = newsMaterialService.queryDetailByMaterialId(newsMateriallst.get(i).getMaterialId());
					newsMateriallst.get(i).setDetailModels(detailModels);
				}
			}
			ajaxObj.setMsg("获取素材成功");
			ajaxObj.setObj(newsMateriallst);
			ajaxObj.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxObj.setMsg("获取素材失败");
			ajaxObj.setResult(0);
		}

		return ajaxObj;
	}
	/**
	 * 获取图片
	 * @param materialName
	 * @param appid
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/selectMeterial")
	@ResponseBody
	public JsonVO selectMeterial(String materialName, String appid) {
		logger.info("获取图片");
		List<MediaMaterial> materials = null;
		JsonVO jsonVO = new JsonVO();
		try {
			MediaMaterial materialModel = new MediaMaterial<>();
			if (StringUtils.isEmpty(materialName)) {
				materials = newsMaterialService.queryMediaMaterialByPlatformId(appid);
			} else {
				materialModel.setMaterialName(materialName);
				materialModel.setAppid(appid);
				materials = newsMaterialService.queryMediaMaterialByName(materialModel);
			}
			jsonVO.setData(materials);
			jsonVO.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonVO.setSuccess(false);
			jsonVO.setMsg("获取素材失败");
		}

		return jsonVO;
	}
	/**
	 * 查看素材内容
	 * 
	 * @param msgId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "viewMeterial")
	private String viewNewsDetail(String materialId, Model model) {
		logger.info("查看素材内容");
		String rendPath = "";
		NewsMaterial newsMaterial = newsMaterialService.queryNewsMaterialById(materialId);
		List<NewsMaterialDetai> newsDetails = newsMaterialService.queryDetailByMaterialId(materialId);
		model.addAttribute("newsMaterial", newsMaterial);
		model.addAttribute("newsDetails", newsDetails);
		rendPath = "/weChat/material/mediaNewsShow";
		return rendPath;
	}
	/**
	 * 跳转跟修改图文素材内容页面
	 * 
	 * @param newsId
	 * @return
	 */
	@RequestMapping(value = "materialUpdatePage")
	private String updateNewsDetailPage(String detailId, Model model) {
		logger.info("跳转至修改图文素材内容页面");
		NewsMaterialDetai newsDetail = newsMaterialService.queryNewsDetailBydetailId(detailId);
		model.addAttribute("newsDetail", newsDetail);
		return "/weChat/material/materialManagerUpdate";
	}
	/**
	 * 修改图文素材数据
	 * @param matList
	 * @return
	 */
	@RequestMapping(value = "updateNewsMaterialModel")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/material/init")
	@OperationLog(value=Operation.UPDATE,key="350201")
	public AjaxObj updateNewsMaterialModel(String matList) {
		logger.info("修改图文素材数据");
		AjaxObj ajaxObj = new AjaxObj();
		ajaxObj = newsMaterialService.updateNewsMaterial(matList);
		return ajaxObj;
	}
	@RequestMapping(value = "updateNewsMaterial")
	@ResponseBody
	private String updateNewsDetail(NewsMaterialDetai newsDetail) throws Exception {
		logger.info("变更消息内容");
		AjaxObj ajaxObj = new AjaxObj();
		ajaxObj = newsMaterialService.updateNewsDetail(newsDetail);
		// ajaxObj.setResult(1);
		// ajaxObj.setMsg("删除素材成功!!!");
		return JsonUtil.obj2json(ajaxObj);
	}
}
