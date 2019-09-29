package com.yufupos.system.modules.sys.service;

import java.util.List;

import com.yufupos.system.modules.sys.entity.User;

public interface UserServiceIntf {
	public List<User> findList(User user);

	public List<User> findUserByOfficeId(User user);

	public User get(String id);
	
	public List<User> getByName(String name);

	public int update(User user);

	public int deleteUserRole(User user);

	public int insertUserRole(User user);

	public int updateUserInfo(User user);

	public int deleteOut(User user);

	public int updatePasswordById(User user);

	public int updateLoginInfo(User user);

	public User getByLoginName(User user);
	
	public int insert(User user);

}
