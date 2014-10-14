package com.sonal.webservice.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sonal.webservice.model.Student;
import com.sonal.webservice.model.Subject;

@Controller
public class MyService {

	@RequestMapping(value = "/helloGet/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody
	String sayHelloGet(@PathVariable String name) {	
		//String showname = showname();
		return "Hello !! " + name ;
	}
	
	/*private String showname(){		
		return "sonal Kumar Sinha";
	}
*/
	@RequestMapping(value = "/helloPost", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody
	String sayHelloPost(@RequestBody String name) {
		return "Hello !!  " + name;
	}

	@RequestMapping(value = "/getAllStudents", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Student> getAllStudents() {		
		List<Student> students = buildStudents();
		return students;
	}
	
	@RequestMapping(value = "/getStudents", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody List<Student> getStudents(@RequestBody List<Student> students) {		
		
		return students;
	}
	
	

	private List<Student> buildStudents() {
		List<Student> students = new ArrayList<Student>();

		Subject subject1 = new Subject();
		subject1.setSubjectName("Java");

		Subject subject2 = new Subject();
		subject2.setSubjectName("Hibernate");

		Subject subject3 = new Subject();
		subject3.setSubjectName("Spring");

		Subject subject4 = new Subject();
		subject4.setSubjectName("Hadoop");

		List<Subject> subjectsList1 = new ArrayList<Subject>();
		subjectsList1.add(subject1);
		subjectsList1.add(subject2);
		subjectsList1.add(subject3);
		subjectsList1.add(subject4);

		List<Subject> subjectsList2 = new ArrayList<Subject>();
		subjectsList2.add(subject1);
		subjectsList2.add(subject2);

		Student student1 = new Student();
		student1.setId(1);
		student1.setStudentName("XYZ");
		student1.setSubjects(subjectsList1);

		Student student2 = new Student();
		student2.setId(2);
		student2.setStudentName("ABC");
		student2.setSubjects(subjectsList2);

		students.add(student1);
		students.add(student2);
		return students;
	}

}
