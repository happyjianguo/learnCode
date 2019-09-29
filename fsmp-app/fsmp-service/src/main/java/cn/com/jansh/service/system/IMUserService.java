package cn.com.jansh.service.system;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;

import com.jansh.core.entity.sys.IMUser;
import com.jansh.core.exception.AppException;

import cn.com.jansh.entity.system.IMUserN;
import cn.com.jansh.model.component.PassWordModel;
import cn.com.jansh.model.system.RegisterAccountModel;
import cn.com.jansh.model.system.UserManageModel;
import cn.com.jansh.vo.AjaxObj;
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
	 * 保存新用户
	 * @param model
	 * @return
	 * @throws AppException 
	 */
	public int insertImuserNew(RegisterAccountModel model , HttpSession session) throws AppException;
	/**
	 * 获取登录用户信息
	 * @return IMUser
	 */
	public IMUser getCurrentUser();
	/**
	 * 查询手机号是否存在
	 * @return AjaxObj
	 */
	public AjaxObj checkPhoneNo(String phoneno);
	/**
	 * 查看验证码是否有效
	 * @return AjaxObj
	 */
	public AjaxObj checkValidate(HttpServletRequest request);
	/**
	 * 查询手机号是否限制
	 * @return AjaxObj
	 */
	public AjaxObj checkPhoneExist(String phoneno,String email,String username);
	/**
	 * 
	 * 查询同一机构下的用户
	 * 
	 */
	public List<IMUserN> queryUserInOrg();
	/**
	 * 校验验证码
	 * @param request
	 * @throws AppException 
	 */
	public void checkValidCode(HttpServletRequest request) throws AppException;
	/**
	 * 根据验证码修改账户密码
	 * @param request
	 * @throws AppException 
	 */
	public void modifypassword(HttpServletRequest request ) throws AppException;
	/**
	 * 修改账户密码
	 * @param request
	 * @throws AppException 
	 */
	public void editpassword(PassWordModel passWordModel) throws AppException;
	/**
	 * 显示用户头像
	 * @param request 
	 * @param response
	 */
	public void showUserHead(HttpServletRequest request, HttpServletResponse response);
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
	/**
	 * 根据用户id查询管理员信息
	 * @param userid
	 * @return
	 */
	public IMUserN selectNewByUserid(String userid);
	
	
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
	public void updateUserInfo(HttpServletRequest request ,  UserManageModel userManageModel) throws AppException;
}
