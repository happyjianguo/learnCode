package cn.com.jansh.mapper.wsfdn;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.wsfdn.CfSupplierpriceEntity;

/**
 * 供应商报价Mapper
 * @author duanmuyn
 *
 */
public interface CfSupplierpriceMapper {
	/**
	 * 供应商报价查询
	 * @param cfSupplierpriceEntity
	 * @return
	 */
	public List<CfSupplierpriceEntity> query(CfSupplierpriceEntity cfSupplierpriceEntity);
	/**
	 * 查询运营商种类
	 * @return
	 */
	public List<String> queryIpsno();
	/**
	 * 查询套餐类型（话费、流量）
	 * @param ispno
	 * @return
	 */
	public List<String> queryIpsType(String ispno);
	/**
	 * 查询省份
	 * @param ispno
	 * @return
	 */
	public List<CfSupplierpriceEntity> queryprovince(CfSupplierpriceEntity cfSupplierpriceEntity);
	/**
	 * 查询面值
	 * @param cfSupplierpriceEntity
	 * @return
	 */
	public List<String> queryFaceValue(CfSupplierpriceEntity cfSupplierpriceEntity);
	/**
	 * 通过多条件查询供应商报价信息
	 * @param id
	 * @return
	 */
	public List<CfSupplierpriceEntity> queryrecharge(CfSupplierpriceEntity cfSupplierpriceEntity);
	/**
	 * 通过id查询供应商信息
	 * @param id
	 * @return
	 */
	public CfSupplierpriceEntity querySupplierpriceByid(@Param("id")String id);
	/**
	 * 通过name查询供应商信息
	 * @param id
	 * @return
	 */
	public CfSupplierpriceEntity querySupplierpriceByName(@Param("pname")String pname);
	
	/**
	 * 通过时间查询供应商报价
	 * @param time
	 * @return
	 */
	public List<CfSupplierpriceEntity> querySupplierpriceByTime(@Param("status") String status,@Param("time")String time);
	
	/**
	 * 增加供应商报价
	 * @param cfSupplierpriceEntity
	 */
	public void addCFSupplierprice(CfSupplierpriceEntity cfSupplierpriceEntity);
	/**
	 * 更新供应商报价
	 * @param cfSupplierEntity
	 */
	public void updateSupplierprice(CfSupplierpriceEntity cfSupplierpriceEntity);
	
	/**
	 * 批量更新供应商报价状态
	 * @param cfBatchinfoEntity
	 */
	public void batchUpdate(Map<String, Object> map);
	/**
	 * 删除供应商报价
	 * @param id
	 */
	public void delSupplierprice(@Param("id")String id);
	
	/**
	 * 查询所有报价
	 * @return
	 */
	public List<CfSupplierpriceEntity> queryprice();
	
	/**
	 * 通过供应商id 查询 供应商报价
	 * @param sid
	 * @return
	 */
	public List<CfSupplierpriceEntity> selectSupplierPriceBySid(@Param("sid")String sid);
}
