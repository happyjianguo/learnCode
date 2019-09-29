package cn.com.jansh.mapper.wsfdn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.wsfdn.CfSupplierEntity;
/**
 * 供应商Mapper
 * @author duanmuyn
 *
 */
public interface CfSupplierMapper {
	/**
	 * 查看所有供应商
	 * @return
	 */
	public List<CfSupplierEntity> query();
	/**
	 * 通过id查询供应商信息
	 * @param id
	 * @return
	 */
	public CfSupplierEntity querySupplierByid(@Param("id")String id);
	/**
	 * 通过id查询供应商信息
	 * @param id
	 * @return
	 */
	public CfSupplierEntity querySupplierByName(@Param("sname")String sname);
	/**
	 * 新增供应商
	 * @param cfSupplierEntity
	 */
	public void insert(CfSupplierEntity cfSupplierEntity);
	/**
	 * 更新供应商
	 * @param cfSupplierEntity
	 */
	public void updateSupplier(CfSupplierEntity cfSupplierEntity);
	/**
	 * 通过id删除供应商信息
	 * @param id
	 */
	public void deleteSupplier(@Param("id")String id);
}
