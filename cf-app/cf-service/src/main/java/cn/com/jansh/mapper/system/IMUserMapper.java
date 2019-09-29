package cn.com.jansh.mapper.system;

import java.util.List;
import java.util.Map;

import cn.com.jansh.core.entity.sys.IMUser;
import cn.com.jansh.entity.system.IMRole;
import cn.com.jansh.entity.system.IMUserRole;

/**
 * 系统管理 - 用户管理
 * @author duanmuyn
 *
 */
public interface IMUserMapper {

	
	public int insert(Map<String, String> map);
	
	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username
	 * @return
	 */
	public IMUser selectOne(String userName);
	
	/**
	 * 根据用户id查询用户信息
	 * @param userid
	 * @return
	 */
	public IMUser selectByUserid(String userid);
 	/**
	 * 根据用户ID查询用户角色列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<String> selectUserRole(String userid);
	
	/**
	 * 根据用户ID查询用户-角色-公众号列表
	 * @return
	 */
	public List<IMUserRole> selectUserRolePlatform(String userid);
	
	/**
	 * 初始化查询管理员
	 */
	public List<IMUser> queryImuser(IMUser imuser);
	
	public List<IMRole> queryRole(IMUser imuser);
	
//	/**
//	 * 增加管理员
//	 * @param imuser
//	 * @return
//	 */
//	public int insertImUser(IMUser imuser);
	/**
	 * 增加
	 * @param imuser
	 * @return
	 */
	public int insert(IMUser imuser);
	
	/**
	 * 修改用户信息
	 * @return 
	 */
	public int updateImUser(IMUser imuser);
	/**
	 * 修改用户登录错误次数
	 * @return 
	 */
	public int updateImUserErrNum(IMUser imuser);
	
	/**
	 * 修改用户登录错误次数
	 * @return 
	 */
	public int updateImUserErr(IMUser imuser);
	
	/**
	 * 删除管理员信息
	 * @param imuser
	 * @return
	 */
	public int delImUser(List<String> userids);

	public List<IMUser> queryImuserByUserId(IMUser imuser);
}
