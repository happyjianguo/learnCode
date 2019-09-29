package cn.com.jansh.mapper.weixin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.weixin.NewsMaterial;

/**
 * 图文消息Mapper
 * @author gll
 * @version 1.0
 */
public interface INewsMaterialMapper {
//	public List<NewsMaterialModel> queryNewsMaterialPager(Map<String, String> map);
	public long getSequence(String materialSdq);
	public List<NewsMaterial> queryNewsMaterialByName(NewsMaterial materialModel);
	public NewsMaterial queryNewsMaterialById(String materialId);
	public void saveNewsMaterial(NewsMaterial newsMaterial);
	public void deleteNewsMaterial(String mediaId);
	public List<NewsMaterial> queryNewsMaterialList();
	public NewsMaterial queryNewsByMaterialId(String materialId);
	public NewsMaterial queryNewsByMaterialName(NewsMaterial materialModel);
	public void deleteBymediaId(Integer mediaId);
	public void deleteByMediaIdAndMaterialId(HashMap<String, String> conditon);
	
	public void updateNewsMaterial(NewsMaterial newsMaterial);
	
	/**
	 * 根据platformId查询图文素材
	 * @param platformId
	 * @return
	 */
	public List<NewsMaterial> queryNewsMaterialListByplatformId(NewsMaterial materialModel);
	/**
	 * 根据appid查询图文素材
	 * @param appid
	 * @return
	 */
	public List<NewsMaterial> queryNewsMaterialListByAppid(NewsMaterial materialModel);
	/**
	 * 根据platformid
	 * @param materialModel
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<NewsMaterial> selectMaterialByMsgidAndplatformId(Map materialModel);
	/**
	 * 根据消息id查询图文素材
	 * @param msgid
	 * @return
	 */
	public NewsMaterial queryNewsMaterialByMessageId(String msgid);
}
