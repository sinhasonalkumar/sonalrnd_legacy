package com.sonal.hibernatetraining.day3.perstistence;

import java.io.Serializable;

public class Student implements Serializable {
	private Long id;
	private String studentName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Student other = (Student) obj;
		if (this.getId().equals(other.getId()))
			return true;
		else
			return false;
	}

}
