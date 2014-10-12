package com.sonal.rnd.testSpring4.utility;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sonal.rnd.testSpring4.restVO.StudentResponseVO;

public class J2JConverter {

	private static ObjectMapper m = new ObjectMapper();
	

	public static <T> Object jsonToObject(String jsonAsString, Class<T> pojoClass) throws JsonMappingException, JsonParseException, IOException {
		return m.readValue(jsonAsString, pojoClass);
	}

	public static String ObjectToJson(Object pojo, boolean prettyPrint) throws JsonMappingException, JsonGenerationException, IOException {
		StringWriter sw = new StringWriter();
		JsonGenerator jg = m.getJsonFactory().createJsonGenerator(sw);
		if (prettyPrint) {
			jg.useDefaultPrettyPrinter();
		}
		m.writeValue(jg, pojo);
		return sw.toString();
	}
	
	public static void main(String[] args) throws Exception{
		StudentResponseVO responseVO = new StudentResponseVO();
		responseVO.setAge(30);
		responseVO.setName("XYZ");
		
		String jsonStudentResponseVO = ObjectToJson(responseVO, true);
		
		System.out.println(jsonStudentResponseVO);
		
		StudentResponseVO studentResponseVOfromJson = (StudentResponseVO)jsonToObject(jsonStudentResponseVO, StudentResponseVO.class);
		
		System.out.println(studentResponseVOfromJson.getAge());
		System.out.println(studentResponseVOfromJson.getName());
	}

}
