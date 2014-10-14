package com.sonal.hibernatetraining.day2.perstistence;

import java.io.Serializable;

public class Passport implements Serializable {
	private Long id;
	private String passportNo;
	private Student student;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}	
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
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
		Passport other = (Passport) obj;
		if (this.getId().equals(other.getId()))
			return true;
		else
			return false;
	}
}
