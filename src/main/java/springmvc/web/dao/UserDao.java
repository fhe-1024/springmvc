package springmvc.web.dao;

import springmvc.web.entity.User;

public interface UserDao {
	public int save(User user) throws Exception;
}
