package cn.com.jansh.mapper.wsfdn;

import java.util.List;

import cn.com.jansh.entity.wsfdn.CfCustomerEntity;

/**
 * 客户Mapper
 * @author gll
 *
 */
public interface CfCustomerMapper {
	
	/**
	 * 查询所有客户
	 * @return
	 */
	public List<CfCustomerEntity> query();
	
	/**
	 * 查询所有客户（加条件查询）
	 * @return
	 */
	public List<CfCustomerEntity> queryby(CfCustomerEntity cfCustomerEntity);
	/**
	 * 新增客户
	 * @param cfCustomerEntity
	 */
	public void insert(CfCustomerEntity cfCustomerEntity);
	
	/**
	 * 通过id查询客户
	 * @param id
	 * @return
	 */
	public CfCustomerEntity selectCustomerById(String id);
	
	/**
	 * 修改客户
	 * @param cfCustomerEntity
	 */
	public void updateCustomer(CfCustomerEntity cfCustomerEntity);
	
	/**
	 * 通过id删除客户
	 * @param id
	 */
	public void deleteCustomer(String id);

	/**
	 * 通过cname查询客户
	 * @param cname
	 * @return 
	 */
	public List<CfCustomerEntity> selectByName(String cname);

}
