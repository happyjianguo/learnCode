/**
 * RegisterReviewService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月18日
 */
package cn.com.jansh.service.review;

import java.util.List;
import java.util.Map;

import com.jansh.core.exception.AppException;
import cn.com.jansh.entity.review.FsmUserEntity;
import cn.com.jansh.model.review.RegisterReviewModel;

/**
 * 注册审核service
 * @author xieliangliang
 * @version 1.0
 */
public interface RegisterReviewService {

	/**
	 * 查询用户未审核的记录
	 */
	public List<FsmUserEntity> query(FsmUserEntity entity);

	/**
	 * 根据用户ID查询用户信息
	 */
	public FsmUserEntity queryByUserId(String userid);

	/**
	 * 更新用户审核状态
	 */
	public void updateStatus(FsmUserEntity modelToEntity);
	
	/**
	 * 根据用户账号查询用户信息
	 */
	public FsmUserEntity queryByUserName(String userName);

	/**
	 * 新增用户信息
	 */
	public void adddata(RegisterReviewModel registerReviewModel) throws AppException;

	/**
	 * 编辑更新用户信息
	 */
	public void updateInfo(FsmUserEntity entity);
	
	/**
	 * 编辑更新用户信息-不修改机构
	 */
	public void updateInfoNoOrgId(FsmUserEntity entity);

	/**
	 * 删除用户信息
	 */
	public void delMemberInfo(String userid);

	/**
	 * 重置密码
	 */
	public void initPwd(Map<String, String> map);
	

}
