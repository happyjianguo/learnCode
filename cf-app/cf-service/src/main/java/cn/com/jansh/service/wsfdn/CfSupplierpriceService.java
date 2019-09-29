package cn.com.jansh.service.wsfdn;

import java.util.List;

import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.entity.wsfdn.CfSupplierpriceEntity;
/**
 * 供应商报价
 * @author duanmuyn
 *
 */
public interface CfSupplierpriceService {

	/**
	 * 根据名称查询供应商报价
	 * @param name
	 * @return
	 */
	public CfSupplierpriceEntity queryByName(String name);
	
	/**
	 * 供应商报价查询
	 * @param cfSupplierpriceEntity
	 * @return
	 */
	public List<CfSupplierpriceEntity> query(CfSupplierpriceEntity cfSupplierpriceEntity);
	/**
	 * 查询运营商种类（移动、联通、电信）
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
	 * 增加供应商报价
	 * @param cfSupplierpriceEntity
	 * @throws AppException 
	 */
	public void addCFSupplierprice(CfSupplierpriceEntity cfSupplierpriceEntity) throws AppException;
	
	/**
	 * 通过id查询供应商报价信息
	 * @return
	 */
	public CfSupplierpriceEntity querySupplierpriceByid(String id); 
	/**
	 * 更新供应商报价信息
	 * @param cfSupplierEntity
	 */
	public void updateSupplierprice(CfSupplierpriceEntity cfSupplierpriceEntity);
	/**
	 * 删除供应商报价（改状态）
	 * @param cfSupplierpriceEntity
	 * @throws AppException 
	 */
	public void delSupplierprice(CfSupplierpriceEntity cfSupplierpriceEntity) throws AppException;
	
	/**
	 * 获取所有报价
	 */
	public List<CfSupplierpriceEntity> queryprice();
	
	
}
