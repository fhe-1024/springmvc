package springmvc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springmvc.web.entity.User;
import springmvc.web.service.impl.UserServiceImpl;

@RequestMapping(path = "/hibernate")
@Controller
public class HibernateController {
	private UserServiceImpl userServiceImpl;

	@Autowired
	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	@RequestMapping(path = "/save", method = RequestMethod.GET)
	public String save() {
		User user = new User();
		user.setPassword("helloworld");
		user.setUsername("fhe");
		try {
			userServiceImpl.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}
}
