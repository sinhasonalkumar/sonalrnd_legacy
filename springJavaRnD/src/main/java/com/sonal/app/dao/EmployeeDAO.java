package com.sonal.app.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.sonal.app.persistence.entity.Address;
import com.sonal.app.persistence.entity.Employee;
import com.sonal.app.service.transactiontypes.ReadOnlyTransactional;

@Repository
public class EmployeeDAO extends BaseDAO {

	@ReadOnlyTransactional
	public List<Employee> getEmployeeWithAddress() {
		List<Employee> employees = (List<Employee>)getHibernateTemplate().find("from Employee");
		for (Employee employee : employees) {
			Set<Address> addresses = employee.getAddress();
			if(!Hibernate.isInitialized(addresses)){
				for (Address currAddress : addresses) {
					Hibernate.initialize(currAddress);
				}
				
			}
		}
		

		return employees;
	}

	@ReadOnlyTransactional
	public List<Employee> getEmployeeWithSkills() {
		List<Employee> employees = (List<Employee>)getHibernateTemplate().find("from Employee");

		return employees;
	}

	@ReadOnlyTransactional
	public List<Employee> getEmployeeWithSKillsNSubjects() {
		List<Employee> employees = (List<Employee>)getHibernateTemplate().find("from Employee");

		return employees;

	}

	@ReadOnlyTransactional
	public List<Employee> getEmployees() {
		List<Employee> employees = (List<Employee>)getHibernateTemplate().find("from Employee");

		return employees;
	}
}
