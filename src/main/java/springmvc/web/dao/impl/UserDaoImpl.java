package springmvc.web.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springmvc.web.dao.UserDao;
import springmvc.web.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int save(User user) throws Exception {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(user);
		return 0;
	}

}
