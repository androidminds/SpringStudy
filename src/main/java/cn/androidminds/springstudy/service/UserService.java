package cn.androidminds.springstudy.service;


import cn.androidminds.springstudy.dao.LogRepository;
import cn.androidminds.springstudy.dao.UserDao;
import cn.androidminds.springstudy.domain.LoginLog;
import cn.androidminds.springstudy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserService {
	private UserDao userDao;
	private LogRepository logRepository;


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

    @Transactional
	public void addLoginLog(int uId, String ipAddress) {
		logRepository.save(new LoginLog(uId, new Date(), ipAddress) );
	}

	@Autowired
	void setLogRepository(LogRepository logRepository) {this.logRepository = logRepository;}
}
