package springmvc.web.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springmvc.web.dao.AppointmentBookDao;
import springmvc.web.service.AppointmentBookService;

@Service
@Transactional
public class AppointmentBookServiceImpl implements AppointmentBookService {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private AppointmentBookDao appointmentBookDao;

	public void get() throws Exception {
		// TODO Auto-generated method stub
		log.info("get() invoke");
		List<Map<String, String>> list = appointmentBookDao.get();
		Iterator<Map<String, String>> it = list.iterator();
		while (it.hasNext()) {
			Map<String, String> map = it.next();
			log.info("user_name:" + map.get("name") + "      " + "password:" + map.get("password"));
		}
	}

	public void save() throws Exception {
		// TODO Auto-generated method stub
		appointmentBookDao.save();
		throw new RuntimeException("runtimeexception");
	}

}
