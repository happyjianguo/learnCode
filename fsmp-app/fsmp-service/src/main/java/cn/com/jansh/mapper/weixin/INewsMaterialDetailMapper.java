package cn.com.jansh.mapper.weixin;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.weixin.NewsMaterialDetai;

/**
 * 图文素材明细Mapper
 * @author gll
 * @version 1.0
 */
public interface INewsMaterialDetailMapper {
	public void saveNewsMaterialDetail(List<NewsMaterialDetai> modelLst);

	public List<NewsMaterialDetai> getDetailByMaterialId(String materialId);

	public void delNewsMaterialDetail(Map<Object, Object> condition);

	public List<NewsMaterialDetai> findAll();

	public void updateNewsMaterialDetail(NewsMaterialDetai newsDetail);

	public void deleteNewsMaterialDetail(String materialId);

	public List<NewsMaterialDetai> queryDetailByMaterialId(String materialId);
	
	public List<NewsMaterialDetai> queryDetailByMediaUrl(String thumbMediaUrl);

	public NewsMaterialDetai queryNewsDetailBydetailId(String detailId);

	public void addNewsMaterialDetail(NewsMaterialDetai newsMaterialDetai);
}
