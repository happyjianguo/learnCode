package cn.com.jansh.mapper.weixin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.weixin.MediaMaterial;

/**
 * 图片素材Mapper
 * @author gll
 * @version 1.0
 */
public interface IMediaMaterialMapper {
	@SuppressWarnings("rawtypes")
	public void saveMaterial(MediaMaterial model);
	/**
	 * 按条件查询素材数据，可分页
	 */
	@SuppressWarnings("rawtypes")
	public List<MediaMaterial> queryMediaMaterialPager(Map<String, Object> map);
	public void delMediaMaterial(String materialId);
	@SuppressWarnings("rawtypes")
	public void updateMediaMaterial(MediaMaterial model);
	@SuppressWarnings("rawtypes")
	public MediaMaterial queryMediaMaterialById(String materialId);
	/**
	 * 根据文件类型查找多媒体素材
	 */
	@SuppressWarnings("rawtypes")
	public List<MediaMaterial> queryMediaMaterialByType(String type);
	@SuppressWarnings("rawtypes")
	public List<MediaMaterial> queryMediaMaterialByPlatformId(@Param(value="appid") String appid);
	@SuppressWarnings("rawtypes")
	public List<MediaMaterial> queryMediaMaterialByNamePager(String name);
	@SuppressWarnings("rawtypes")
	public List<MediaMaterial> queryMediaMaterialList();
	@SuppressWarnings("rawtypes")
	public List<MediaMaterial> queryMediaMaterialByName(MediaMaterial materialModel);
	@SuppressWarnings("rawtypes")
	public MediaMaterial queryMediaByName(MediaMaterial materialModel);
	/**
	 * 通过URL查询图片
	 * @param thumbMediaUrl
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<MediaMaterial> selectMaterialByMediaUrl(@Param(value="mediaurl") String mediaUrl);
	/**
	 * 根据THUMBMEDIAURL查询MediaMaterial
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public MediaMaterial selectmaterialByUrl(Map<String, String> map);
	/**
	 * 根据filePath查询MediaMaterial
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public MediaMaterial selectmaterialByFileUrl(@Param(value="filepath") String filePath);
}
