package cn.androidminds.springstudy.service;


import cn.androidminds.springstudy.dao.UserDao;
import cn.androidminds.springstudy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private UserDao userDao;


	public boolean hasMatchUser(String userName, String password) {
		return userDao.getMatchCount(userName, password) > 0;
	}
	
	public User findUserByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}

	public boolean addUser(String userName, String password) {
		return userDao.addUser(userName, password);
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
