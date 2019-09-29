package cn.com.jansh.mapper.system;

import java.util.List;
import java.util.Map;

import com.jansh.core.entity.sys.IMUser;

import cn.com.jansh.entity.system.IMRole;
import cn.com.jansh.entity.system.IMUserN;
import cn.com.jansh.entity.system.IMUserRole;

/**
 * 系统管理 - 用户管理
 * @author duanmuyn
 *
 */
public interface IMUserMapper {

	/**
	 * 添加用户
	 * @param map
	 * @return
	 */
	public int insert(Map<String, String> map);
	
	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username
	 * @return
	 */
	public IMUser selectOne(String userName);
	
	/**
	 * 根据手机号查询用户信息
	 * @param phoneno
	 * @return
	 */
	public IMUser selectOneByPhoneno(String phoneno);
	
	/**
	 * 查询邮箱、手机号是否存在
	 * @param map
	 * @return
	 */
	public List<IMUser> checkInfoExist(Map<String,Object> map);
	
	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param map
	 * @return
	 */
	public IMUser select(Map<String, String> map);
	
	/**
	 * 根据用户id查询用户信息
	 * @param userid
	 * @return
	 */
	public IMUser selectByUserid(String userid);
	/**
	 * 根据用户id查询用户信息
	 * @param userid
	 * @return
	 */
	public IMUserN selectNewByUserid(String userid);
	
	/**
	 * 根据用户名或手机号查询用户信息（用户登录）
	 * @param userid
	 * @return
	 */
	public IMUserN selectNewByUserNameOrPhoneOrEmail(String userName);
	
	/**
	 * 查询同一机构下的用户
	 * @param userid
	 * @return
	 */
	public List<IMUserN> selectInOrg(Map<String, Object> map);
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
	
	/**
	 * 增加
	 * @param imuser
	 * @return
	 */
	public int insert(IMUser imuser);
	/**
	 * 增加
	 * @param imuser
	 * @return
	 */
	public int insertNew(IMUserN imuser);
	
	/**
	 * 修改用户信息
	 * @return 
	 */
	public int updateImUser(IMUser imuser);
	
	/**
	 * 修改用户信息
	 * @return 
	 */
	public int updateImUserN(IMUserN imuser);
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

	/**
	 * 根据用户id查询用户信息
	 * @param imuser
	 * @return
	 */
	public List<IMUser> queryImuserByUserId(IMUser imuser);
}
