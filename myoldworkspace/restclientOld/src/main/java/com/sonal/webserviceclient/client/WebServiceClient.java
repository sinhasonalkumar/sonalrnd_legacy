package com.sonal.webserviceclient.client;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sonal.webserviceclient.model.Student;
import com.sonal.webserviceclient.model.Subject;

@Component
public class WebServiceClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public 	String sayHelloGet( String name) {
		String sayHelloGetResponse = restTemplate.getForObject(WebServiceURI.sayHelloGet,String.class,name);
		return sayHelloGetResponse;
	}
	
	public String sayHelloPost(String name){
		return null;
	}
	
	public List<Student> getAllStudents() {
		List<Student> sayHelloGetResponse = (List<Student>) restTemplate.getForObject(WebServiceURI.getAllStudents,List.class);
		return sayHelloGetResponse;
	}
	
	public  List<Student> getStudents(List<Student> students) {
		return null;
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
