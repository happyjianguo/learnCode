package cn.com.jansh.service.wsfdn;

import java.util.List;

import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.entity.wsfdn.CfCustomerEntity;

public interface CfCustomerService {
	/**
	 * 初始化客户页面
	 * @param cfCustomerModel
	 * @return
	 */
	public List<CfCustomerEntity> queryCustomer();
	
	/**
	 * 新增客户
	 * @param cfCustomerModel
	 * @throws AppException 
	 */
	public void insertCustomer(CfCustomerEntity cfCustomerEntity) throws AppException;
	
	/**
	 * 通过id查询客户信息
	 * @param id
	 * @return
	 */
	public CfCustomerEntity selectCustomerById(String id);
	
	/**
	 * 修改客户信息
	 * @param cfCustomerEntity
	 */
	public void updateCustomer(CfCustomerEntity cfCustomerEntity);
	
	/**
	 * 通过id删除客户信息
	 * @param id
	 * @throws AppException 
	 */
	public void deleteCustomerById(String id) throws AppException;
	
	public List<CfCustomerEntity> queryCustomerByAcName(String acname);
	/**
	 * 查询所有客户（加条件）	
	 * @param cfCustomerEntity
	 * @return
	 */
	public List<CfCustomerEntity> queryCustomerBy(CfCustomerEntity cfCustomerEntity);
	
}
