package springmvc.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/form")
public class FileUploadController {

	private Log log = LogFactory.getLog(getClass());

	@RequestMapping(method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file)
			throws Exception {

		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			log.info(bytes);
			// store the bytes somewhere
			return "redirect:uploadSuccess";
		}

		return "redirect:uploadFailure";
	}

}
