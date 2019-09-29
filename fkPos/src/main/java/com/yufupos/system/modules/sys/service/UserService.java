package com.yufupos.system.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.sys.dao.UserDao;
import com.yufupos.system.modules.sys.entity.User;

@Service("userService")
@Transactional(readOnly = true)
public class UserService extends CrudService<UserDao, User> implements
		UserServiceIntf {

	@Autowired
	UserDao userDao;

	@Override
	public int deleteOut(User user) {
		return userDao.delete(user);
	}

	@Override
	public int deleteUserRole(User user) {
		return userDao.deleteUserRole(user);
	}

	@Override
	public List<User> findList(User user) {
		return userDao.findList(user);
	}

	@Override
	public List<User> findUserByOfficeId(User user) {
		return userDao.findUserByOfficeId(user);
	}

	@Override
	public User getByLoginName(User user) {
		return userDao.getByLoginName(user);
	}

	@Override
	public int insertUserRole(User user) {
		return userDao.insertUserRole(user);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public int updateLoginInfo(User user) {
		return userDao.updateLoginInfo(user);
	}

	@Override
	public int updatePasswordById(User user) {
		return userDao.updatePasswordById(user);
	}

	@Override
	public int updateUserInfo(User user) {
		return userDao.updateUserInfo(user);
	}

	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public List<User> getByName(String name) {
		User user = new User();
		user.setName(name);
		user.setDelFlag("0");
		return userDao.getOneData(user);
	}

}
