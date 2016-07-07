package springmvc.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springmvc.web.dao.UserDao;
import springmvc.web.entity.User;
import springmvc.web.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void save(User user) throws Exception {
		// TODO Auto-generated method stub
		userDao.save(user);
		throw new RuntimeException("run time exception");
	}

}
