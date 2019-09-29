package cn.com.jansh.service.system;

import java.util.List;

import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.entity.system.IMResourceRole;
import cn.com.jansh.entity.system.IMRole;
import cn.com.jansh.model.system.RoleManageModel;
/**
 * @author Mr.won
 * 
 */
public interface RoleService {
	/**
	 * 增加角色
	 * 
	 */
	public void addRole(RoleManageModel roleManageModel) throws AppException;
	/**
	 * 获取角色列表
	 * 
	 */
	public List<IMRole> queryRoleList();
	/**
	 * 根据角色名获取角色
	 * 
	 */
	public IMRole queryRoleByRoleName (String roleName);
	/**
	 * 根据id名获取角色
	 * 
	 */
	public IMRole queryRoleByRoleId (String roleId);
	/**
	 * 更新角色信息
	 * @throws AppException 
	 * 
	 */
	public void updateRole(IMRole role) throws AppException;
	/**
	 * 删除角色
	 * 
	 */
	public void deleteRole(String roleId);
	/**
	 * 批量删除
	 * @return 
	 * @throws Exception 
	 * 
	 */
	public void deleteRoleByIds(String[] roleIds) throws Exception;
	/**
	 * 获取角色资源配置
	 * 
	 */
	public List<IMResourceRole> queryResourceIdById(String roleId);
	
	/**
	 * 通过id获取资源String类型的数据
	 * @param roleid
	 * @return
	 */
	public String getResourceString(String roleid);
	/**
	 * 通过userid获取角色
	 *  @param userid
	 * @return
	 */
	public List<String> getRoreidByuserid(String userid);
	/**
	 * 通过List<String> roleids获取角色
	 */
	public List<IMRole> getRolelistByroleids(List<String> roleids);
}
