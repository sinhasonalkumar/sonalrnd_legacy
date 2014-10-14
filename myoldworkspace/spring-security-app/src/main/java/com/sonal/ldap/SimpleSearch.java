package com.sonal.ldap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Component;

@Component
public class SimpleSearch {

	@Autowired
	private LdapTemplate ldapTemplate;

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	public LdapTemplate getLdapTemplate() {
		return ldapTemplate;
	}

	public static void main(String[] args) {

		String username = "achevhovich";
		String password = "abc";

		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		SimpleSearch simpleSearch = (SimpleSearch) context.getBean(SimpleSearch.class);
		LdapTemplate ldapTemplate = simpleSearch.getLdapTemplate();
		
		 List<String> users = ldapTemplate.list("ou=Users,dc=stage,dc=dete,dc=warnerbros,dc=com");
		//List<String> users = ldapTemplate.list("ou=Groups,dc=stage,dc=dete,dc=warnerbros,dc=com");
		
		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("sn", username));
		boolean authenticate = ldapTemplate.authenticate("ou=Users,dc=stage,dc=dete,dc=warnerbros,dc=com", filter.toString(), password);

		for (String user : users) {
			System.out.println(user);
		}
		System.out.println(authenticate);

	}

}
