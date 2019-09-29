package com.jansh.core.mapper;

import java.util.List;
import java.util.Map;

import com.jansh.core.entity.sys.IMMenu;
import com.jansh.core.entity.sys.IMUser;
import com.jansh.core.entity.sys.IMUserLog;
import com.jansh.core.entity.sys.PubsSysBase;
import com.jansh.core.entity.sys.SysResourceRole;

public interface SysCoreMapper {

	/**
	 * 查询菜单列表
	 * 
	 * @param map
	 * @return
	 */
	public List<IMMenu> selectMenu(Map<String, String> map);

	/**
	 * 通过系统参数名获取参数实体
	 */
	public PubsSysBase selectOneByBaseId(String baseId);

	/**
	 * 插入操作日志
	 * 
	 * @param imUserLog
	 */
	public void insertUserLog(IMUserLog imUserLog);

	/**
	 * 修改用户登录错误次数
	 * 
	 * @return
	 */
	public int updateUserErrNum(IMUser imuser);

	/**
	 * 修改用户登录错误次数
	 * 
	 * @return
	 */
	public int updateUserErr(IMUser imuser);

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username
	 * @return
	 */
	public IMUser selectUserByUserName(String userName);
	/**
	 * 根据用户id查询用户信息
	 * @param userid
	 * @return
	 */
    public IMUser selectUserByUserid(String userid);
	/**
	 * 修改用户信息
	 * 
	 * @return
	 */
	public int updateUser(IMUser imuser);
	/**
	 * 根据用户ID查询用户角色列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<String> selectUserRole(String userid);
	/**
	 * 查询资源对应权限
	 * @return
	 */
	public List<SysResourceRole> queryRoleResource();
}
