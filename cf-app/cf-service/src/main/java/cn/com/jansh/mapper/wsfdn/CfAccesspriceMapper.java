package cn.com.jansh.mapper.wsfdn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.wsfdn.CfAccesspriceEntity;

/**
 * 接入者报价Mapper
 * @author duanmuyn
 *
 */
public interface CfAccesspriceMapper {
	/**
	 * 查询接入者报价
	 * @param cfAccesspriceEntity
	 * @return
	 */
	public List<CfAccesspriceEntity> query(CfAccesspriceEntity cfAccesspriceEntity);
	/**
	 * 查询运营商
	 * @param cfAccesspriceEntity
	 * @return
	 */
	public List<CfAccesspriceEntity> queryIspno(CfAccesspriceEntity cfAccesspriceEntity);
	/**
	 * 查询接入者报价通过供应商报价信息
	 * @param cfAccesspriceEntity
	 * @return
	 */
	public List<CfAccesspriceEntity> queryAccesspriceByApprice(CfAccesspriceEntity cfAccesspriceEntity);
	/**
	 * 查询省份
	 * @param cfAccesspriceEntity
	 * @return
	 */
	public List<CfAccesspriceEntity> queryprovince(CfAccesspriceEntity cfAccesspriceEntity);
	
	/**
	 * 通过id查询接入者报价
	 * @param id
	 * @return
	 */
	public CfAccesspriceEntity queryAccesspriceByid(@Param("apid")String apid);
	/**
	 * 通过id查询接入者报价
	 * @param id
	 * @return
	 */
	public List<CfAccesspriceEntity> queryAccesspriceByAcid(@Param("acid")String acid);
	
	/**
	 * 新增接入者报价
	 * @param cfAccesspriceEntity
	 */
	public void insert(CfAccesspriceEntity cfAccesspriceEntity);
	
	/**
	 * 修改供应商报价
	 * @param cfAccesspriceEntity
	 */
	public void updateAccessprice(CfAccesspriceEntity cfAccesspriceEntity);
	
	/**
	 * 删除供应商报价（逻辑删除，改状态）
	 * @param apid
	 */
	public void deleteAccessprice(@Param("apid")String apid);

	/**
	 * 多条件获取接入者
	 * @param acid
	 * @param facevalue
	 * @param ispno
	 * @param isptype
	 * @return
	 */
	public CfAccesspriceEntity selectprice(CfAccesspriceEntity cfAccesspriceEntity);
	
}
