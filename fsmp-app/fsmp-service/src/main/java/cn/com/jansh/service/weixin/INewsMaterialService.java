package cn.com.jansh.service.weixin;

import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;
import cn.com.jansh.vo.AjaxObj;
import cn.com.jansh.entity.weixin.MediaMaterial;
import cn.com.jansh.entity.weixin.NewsMaterial;
import cn.com.jansh.entity.weixin.NewsMaterialDetai;
/**
 * 图文消息接口
 * @author gll
 * @version 1.0
 */
public interface INewsMaterialService  {
	
	public AjaxObj addPicToWeChat(MediaMaterial<MultipartFile> mmModel);
	/**
	 * 新增图文素材service
	 * @param matList
	 * @return
	 */
	public AjaxObj addNewsToWeChat(String matList);
	public AjaxObj updatePicInfo(MediaMaterial<MultipartFile> mmModel);
	/**
	 * 删除图片
	 * @param materialId
	 * @return
	 */
	public AjaxObj delMaterialToWeChat(String materialId);
	/**
	 * 删除图文素材
	 * @param materialId
	 * @return
	 */
	public AjaxObj delNewsMaterialToWeChat(String materialId);
	/**
	 * 变更消息内容
	 * @param newsDetail
	 * @return
	 */
	public AjaxObj updateNewsDetail(NewsMaterialDetai newsDetail);
	/**
	 * 修改图文素材数据
	 * @param matList
	 * @return
	 */
	public AjaxObj updateNewsMaterial(String matList);
	@SuppressWarnings("rawtypes")
	public List<MediaMaterial> queryMediaMaterialList();
	/**
	 * 根据素材id和素材名称查找素材
	 * @param materialModel
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<MediaMaterial> queryMediaMaterialByName(MediaMaterial materialModel);
	public List<NewsMaterial> queryNewsMaterialByName(NewsMaterial materialModel);
	@SuppressWarnings("rawtypes")
	public List<MediaMaterial> queryMediaMaterialByType(String type);
	/**
	 * 通过appid查询素材
	 * @param appid
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<MediaMaterial> queryMediaMaterialByPlatformId(String appid);
	/**
	 * 通过id查询素材明细
	 * @param materialId
	 * @return
	 */
	public List<NewsMaterialDetai> queryDetailByMaterialId(String materialId);
	/**
	 * 通过素材id查询素材
	 */
	public NewsMaterial queryNewsMaterialById(String materialId);
	
	public NewsMaterial queryNewsMaterialByMessageId(String msgid);
	
	@SuppressWarnings("rawtypes")
	public List<NewsMaterial> selectMaterialByMsgidAndplatformId(Map map);
	
	/**
	 * 根据素材明细id查找素材
	 * @param detailId
	 * @return
	 */
	public NewsMaterialDetai queryNewsDetailBydetailId(String detailId);
	
	public void saveNewsMaterial(NewsMaterial mmModel);
	public void deleteNewsMaterial(String materialId, String mediaId);
	public void deleteBymediaId(Integer newsId);
	
	/**
	 * 根据公众号id查询图文素材
	 * @param appid
	 * @return
	 */
	public List<NewsMaterial> queryNewsMaterialListByAppid(NewsMaterial materialModel);
	/**
	 * 根据platformId查询图文素材
	 * @param platformId
	 * @return
	 */
	public List<NewsMaterial> queryNewsMaterialListByplatformId(NewsMaterial materialModel);
	/**
	 * 根据THUMBMEDIAURL查询MediaMaterial
	 * @param url
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public MediaMaterial selectmaterialByUrl(String url);
	/**
	 * 通过id查询素材明细(修改頁面跳转)
	 * @param materialId
	 * @return
	 */
	public List<NewsMaterialDetai> queryDetailByTMaterialId(String materialId);
}
