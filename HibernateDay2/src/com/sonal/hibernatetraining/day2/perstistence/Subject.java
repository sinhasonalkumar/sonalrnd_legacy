package com.sonal.hibernatetraining.day2.perstistence;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Subject implements Serializable {
	private Long id;
	private String subjectName;
	private Set<Student> students = new HashSet<Student>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}	
	
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
}
