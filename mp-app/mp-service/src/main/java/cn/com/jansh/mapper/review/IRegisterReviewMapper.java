/**
 * RegisterReviewMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月18日
 */
package cn.com.jansh.mapper.review;

import java.util.List;

import cn.com.jansh.entity.review.FsmUserEntity;

/**
 * 注册用户审核操作类
 * @author xieliangliang
 * @version 1.0
 */
public interface IRegisterReviewMapper {

	/**
	 * 查询所有未审核状态的用户注册信息
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
	 * 新增会员信息
	 */
	public void saveData(FsmUserEntity entity);

	/**
	 * 更新用户信息-更新全部信息
	 */
	public void updateInfo(FsmUserEntity entity);

	/**
	 * 删除用户信息
	 */
	public void delMemberInfo(String userid);

	/**
	 * 更新用户信息-不更新机构
	 */
	public void updateInfoNoOrgId(FsmUserEntity entity);
	
}
