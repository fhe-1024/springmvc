package springmvc.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SpringMvcAction {

	private Log log = LogFactory.getLog(SpringMvcAction.class);

	@RequestMapping("/test")
	public void testControl(HttpServletResponse response) throws IOException {
		System.out.println("nimei");
		log.debug("nimei");
		System.out.println("hello");
		log.debug("nimei1");
		log.info("111");
		response.getWriter().write("helloworld");
	}

	@RequestMapping(path = "/upload", method = RequestMethod.GET)
	public String upload() {
		return "upload";
	}

}