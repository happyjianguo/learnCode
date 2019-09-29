/**
 * IFsMUserRoleMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月26日
 */
package cn.com.jansh.mapper.review;

import cn.com.jansh.entity.review.FsmUserRoleEntity;

/**
 * 用户端角色权限
 * @author xieliangliang
 * @version 1.0
 */
public interface IFsMUserRoleMapper {
	
	/**
	 * 新增用户权限
	 */
	public int insert(FsmUserRoleEntity iMUserRole);

}
