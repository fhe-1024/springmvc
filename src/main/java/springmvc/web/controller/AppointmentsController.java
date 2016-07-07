package springmvc.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springmvc.web.service.AppointmentBookService;

@Controller
@RequestMapping("/appointments")
public class AppointmentsController {

	private Log log = LogFactory.getLog(AppointmentsController.class);

	private AppointmentBookService appointmentbookService;

	@Autowired
	public void setAppointmentbookService(AppointmentBookService appointmentbookService) {
		this.appointmentbookService = appointmentbookService;
	}

	@RequestMapping(path = "get", method = RequestMethod.GET)
	public String get() {
		log.info("/appointments invoke get method");
		// forward /WEB-INF/jsp/ jsppage
		try {
			appointmentbookService.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping(path = "/save", method = RequestMethod.GET)
	public String save() {
		try {
			appointmentbookService.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping(path = "/new", method = RequestMethod.GET)
	public String getNew() {
		log.info("forward");
		return "redirect:/appointments";
	}

	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public void getTest(HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", "test");
		log.info("test map");
		log.info("good luck");
		response.getWriter().write(map.toString());
	}

	@RequestMapping(path = "/owner/{ownerId}")
	public String findOwner(@PathVariable String ownerId, Model model) {
		log.info(ownerId);
		return "index";
	}
}
