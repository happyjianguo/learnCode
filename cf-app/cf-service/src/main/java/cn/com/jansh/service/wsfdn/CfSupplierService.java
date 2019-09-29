package cn.com.jansh.service.wsfdn;

import java.util.List;

import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.entity.wsfdn.CfSupplierEntity;
/**
 * 供应商Service
 * @author duanmuyn
 *
 */
public interface CfSupplierService {
	
	/**
	 * 初始化供应商列表
	 * @return
	 */
	public List<CfSupplierEntity> querySupplier(); 
	/**
	 * 新增供应商
	 * @param cfSupplierEntity
	 * @throws AppException 
	 */
	public void insertSupplier(CfSupplierEntity cfSupplierEntity) throws AppException;
	
	/**
	 * 通过id查询供应商信息
	 * @return
	 */
	public CfSupplierEntity querySupplierByid(String id); 
	
	/**
	 * 更新供应商信息
	 * @param cfSupplierEntity
	 */
	public void updateSupplier(CfSupplierEntity cfSupplierEntity);
	
	/**
	 * 根据id删除供应商
	 * @param id
	 * @throws AppException 
	 */
	public void deleteSupplier(String id) throws AppException;
}
