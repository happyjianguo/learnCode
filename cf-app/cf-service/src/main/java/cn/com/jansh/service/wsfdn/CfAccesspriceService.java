package cn.com.jansh.service.wsfdn;

import java.util.List;

import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.entity.wsfdn.CfAccesspriceEntity;

/**
 * 接入者报价管理
 * @author duanmuyn
 *
 */
public interface CfAccesspriceService {
	
	/**
	 * 查询接入者报价
	 * @param cfAccesspriceEntity
	 * @return
	 */
	public List<CfAccesspriceEntity> query(CfAccesspriceEntity cfAccesspriceEntity);
	
	/**
	 * 查询该接入者的报价运营商
	 * @param cfAccesspriceEntity
	 * @return
	 */
	public List<CfAccesspriceEntity> queryIspno(CfAccesspriceEntity cfAccesspriceEntity);
	/**
	 * 查询该接入者的报价省份
	 * @param cfAccesspriceEntity
	 * @return
	 */
	public List<CfAccesspriceEntity> queryprovince(CfAccesspriceEntity cfAccesspriceEntity);
	
	/**
	 * 根据id查询接入者报价
	 * @param id
	 * @return
	 */
	public CfAccesspriceEntity queryAccesspriceByid(String id);
	/**
	 * 新增接入者报价
	 * @param cfAccessclientEntity
	 * @throws AppException 
	 */
	public void insertAccessprice(CfAccesspriceEntity cfAccesspriceEntity) throws AppException;
	
	/**
	 * 修改接入者报价
	 * @param cfAccesspriceEntity
	 */
	public void updateAccessprice(CfAccesspriceEntity cfAccesspriceEntity);
	
	/**
	 * 删除接入者报价(逻辑删除)
	 * @param cfAccesspriceEntity
	 */
	public void deldeteAccessprice(String apid);

	/**
	 * 多字段查询接入者报价
	 * @param acid
	 * @param facevalue
	 * @param ispno
	 * @param isptype
	 */
	public CfAccesspriceEntity selectprice(CfAccesspriceEntity cfAccesspriceEntity);
}
