package springmvc.web.dao;

import java.util.List;
import java.util.Map;

public interface AppointmentBookDao {
	public List<Map<String, String>> get() throws Exception;
	
	public void save()throws Exception;
}
