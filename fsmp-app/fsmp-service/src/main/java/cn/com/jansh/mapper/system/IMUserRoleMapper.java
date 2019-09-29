package cn.com.jansh.mapper.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.system.IMUserRole;

public interface IMUserRoleMapper {
	
	public int delImUserRole(List<String> userids);
	
	public void deleteByRoleId(String roleId);
	
	public int insert(IMUserRole iMUserRole);
	
	/**
	 * 修改数据
	 * @param imuserrole
	 * @return
	 */
	public int update(IMUserRole imuserrole);
	
	public List<IMUserRole> select(String roleId);
	
	public IMUserRole selectOne(IMUserRole imuserrole);
	
	public List<String> selectRoleidByUserid(@Param("userid")String userid);
}
