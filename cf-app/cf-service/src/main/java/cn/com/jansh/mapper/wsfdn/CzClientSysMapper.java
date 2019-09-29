package cn.com.jansh.mapper.wsfdn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.wsfdn.CzClientSys;

public interface CzClientSysMapper {

	/**
	 * 根据sysid查询syskey
	 * @param sysid
	 * @return
	 */
	public String querysyskey(String sysid);
	/** 全部查询
	 * @return 平台信息集合
	 */
	public List<CzClientSys> queryPlatformInfo();
	/**
	 * 条件查询
	 * @param platformUsermodel
	 * @return
	 */
	public List<CzClientSys> checkPlatExist(@Param(value="sysid") String  sysid);
	/**
	 * 添加客户端信息
	 */
	public void addPlatformInfo(CzClientSys platformUsermodel);
	/**
	 * 根据客户端Id删除
	 */
	public void delPlatformById(@Param(value="sysid") String sysid);
	/**
	 * 单个查询客户端信息
	 * @return 客户端信息
	 */
	public CzClientSys queryPlatformInfoById(String platformId);
	/**
	 * 修改客户端信息
	 * @param platformUsermodel
	 */
	public void modifyPlatformInfo(CzClientSys platformUsermodel);
}
