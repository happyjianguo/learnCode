package cn.com.jansh.service.system;

import java.util.List;
import java.util.Map;

import org.apache.commons.mail.EmailException;

import cn.com.jansh.core.entity.sys.IMUser;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.model.system.UserManageModel;
/**
 * 系统管理-用户管理
 * @author duanmuyn
 *
 */

public interface IMUserService {

	/**
	 * 初始化数据
	 * @return
	 */
	public List<IMUser> selectImuser(UserManageModel userManageModel);
	
	/**
	 * 用户新增
	 * @param userManageModel
	 * @throws AppException 
	 */
	public void useradd(UserManageModel userManageModel) throws AppException;
	
	/**
	 * 增加数据
	 * @param imuser
	 * @return
	 */
	public int insertImuser(IMUser imuser);
	
	/**
	 * 修改数据
	 * @param imuser
	 * @return
	 * @throws AppException 
	 */
	public void updateImuser(IMUser imuser) throws AppException;
	
	/**
	 * 修改密码
	 * @param map
	 * @return
	 */
	public int updatePwd(Map<String,String> map);
	/**
	 * 密码重置
	 * @param userid
	 * @return
	 * @throws EmailException 
	 */
	public int initPwd(Map<String,String> map) throws EmailException;
	
	/**
	 * 更新校验密码错误次数
	 * @param map
	 * @return
	 */
	public int updatePwdErrNum(Map<String,String> map);
	/**
	 * 更新校验密码错误次数
	 * @param map
	 * @return
	 */
	public int updatePwdErr(IMUser imuser);
	
	/**
	 * 删除管理员
	 * @param imuser 用户id
	 * @return
	 */
	public int delImuser(List<String> userids);
	
	/**
	 * 根据用户名查询管理员信息
	 * @param username 用户名
	 * @return
	 */
	public IMUser selectByUsername(String username);
	
	/**
	 * 根据用户id查询管理员信息
	 * @param userid
	 * @return
	 */
	public IMUser selectByUserid(String userid);
	
	
	public UserManageModel selectModelByUserid(String userid);
	/**
	 * 更新校验密码错误次数
	 * @param map
	 * @return
	 */
	public int updateErrNumSafe(Map<String, String> map);

	/**
	 * 修改用户信息
	 * @param imuser
	 * @throws AppException
	 */
	public void updateUserInfo(IMUser imuser) throws AppException;
}
