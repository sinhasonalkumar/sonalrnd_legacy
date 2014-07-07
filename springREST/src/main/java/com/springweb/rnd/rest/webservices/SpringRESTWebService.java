package com.springweb.rnd.rest.webservices;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springweb.rnd.rest.WebVOBuilder;
import com.springweb.rnd.rest.vo.StudentWebVO;

@Controller
public class SpringRESTWebService {

	@RequestMapping(value = "/helloWorldRESTGETService", method = RequestMethod.GET)
	public @ResponseBody
	StudentWebVO helloWorldRESTGETService() {
		StudentWebVO buildStudentWebVO = WebVOBuilder.buildStudentWebVO();

		return buildStudentWebVO;
	}

	@RequestMapping(value = "/helloWorldRESTPOSTService", method = RequestMethod.POST)
	public @ResponseBody
	StudentWebVO helloWorldRESTPOSTService() {
		StudentWebVO buildStudentWebVO = WebVOBuilder.buildStudentWebVO();

		return buildStudentWebVO;
	}

	@RequestMapping(value = "/helloWorldRESTPUTService", method = RequestMethod.PUT)
	public @ResponseBody
	StudentWebVO helloWorldRESTPUTService() {
		StudentWebVO buildStudentWebVO = WebVOBuilder.buildStudentWebVO();

		return buildStudentWebVO;
	}

	@RequestMapping(value = "/helloWorldRESTDELETEService", method = RequestMethod.DELETE)
	public @ResponseBody
	StudentWebVO helloWorldRESTDELETEService() {
		StudentWebVO buildStudentWebVO = WebVOBuilder.buildStudentWebVO();

		return buildStudentWebVO;
	}

}
