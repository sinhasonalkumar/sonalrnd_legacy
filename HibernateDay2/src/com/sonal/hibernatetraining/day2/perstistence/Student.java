package com.sonal.hibernatetraining.day2.perstistence;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Student implements Serializable {
	private Long id;
	private String studentName;
	private Passport passport;
	private Set<BankAccount> bankAccounts = new  HashSet<BankAccount>();
	private Set<Subject> subjects = new HashSet<Subject>();

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
	
	

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}
	
	

	public Set<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(Set<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}	
	

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
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
