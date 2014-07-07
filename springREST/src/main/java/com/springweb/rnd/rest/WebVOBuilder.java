package com.springweb.rnd.rest;

import com.springweb.rnd.rest.vo.StudentWebVO;

public class WebVOBuilder {

	public static StudentWebVO buildStudentWebVO() {
		StudentWebVO studentWebVO = new StudentWebVO();
		studentWebVO.setName("test");
		studentWebVO.setAge(30);
		return studentWebVO;
	}
}
