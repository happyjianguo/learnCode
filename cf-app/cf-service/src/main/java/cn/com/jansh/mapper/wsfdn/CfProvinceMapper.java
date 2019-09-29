package cn.com.jansh.mapper.wsfdn;

import java.util.List;

import cn.com.jansh.entity.wsfdn.CfProvinceEntity;

/**
 * 省份Mapper
 * @author gll
 *
 */
public interface CfProvinceMapper {
	
	/**
	 * 查询所有省份
	 * @return
	 */
	public List<CfProvinceEntity> query();
	
	/**
	 * 通过省份名称查询省份详情
	 * @param pname
	 * @return
	 */
	public CfProvinceEntity queryByPname(String pname);

	public CfProvinceEntity selectPnameBypno(String pno);
}
