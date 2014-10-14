package com.sonal.hibernatetraining.day2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import sun.swing.BakedArrayList;

import com.sonal.hibernatetraining.day2.perstistence.BankAccount;
import com.sonal.hibernatetraining.day2.perstistence.Passport;
import com.sonal.hibernatetraining.day2.perstistence.Student;
import com.sonal.hibernatetraining.day2.perstistence.Subject;
import com.sonal.hibernatetraining.util.SessionFactoryUtil;

public class Main {
	public static void main(String[] args) {
		SessionFactory sessionFactory = SessionFactoryUtil.getInstance();
		Session session = SessionFactoryUtil.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();		
		  /*Passport passport = new Passport();
		  passport.setPassportNo("123ABcd");
		  
		  
		  Student student = new Student(); student.setStudentName("abcd");		  
		  BankAccount bankAccount1 = new BankAccount();
		  bankAccount1.setBankAccountNo("123ABC");
		  bankAccount1.setStudent(student);
		  
		  BankAccount bankAccount2 = new BankAccount();
		  bankAccount2.setBankAccountNo("123XYZ");
		  bankAccount2.setStudent(student);
		  
		  Set<BankAccount> bankAccounts = new HashSet<BankAccount>();
		  bankAccounts.add(bankAccount1); 
		  bankAccounts.add(bankAccount2);
		  
		  Set<Student> students = new HashSet<Student>();
		  students.add(student);
		  
		  Subject subject1 = new Subject(); subject1.setSubjectName("java");
		  subject1.setStudents(students);
		  
		  Subject subject2 = new Subject(); subject2.setSubjectName("jee");
		  subject2.setStudents(students);
		  
		  Subject subject3 = new Subject(); subject3.setSubjectName("jme");
		  subject3.setStudents(students);
		  
		  Set<Subject> subjects = new HashSet<Subject>();
		  subjects.add(subject1); subjects.add(subject2);
		  subjects.add(subject3);
		  
		  passport.setStudent(student);
		  
		  student.setPassport(passport); 
		  student.setBankAccounts(bankAccounts);
		  student.setSubjects(subjects);
		 
		  
		  
		  session.save(student);*/
		
		/*String hql = "select sb from Student st,Passport p,Subject sb inner join  st.subjects  stsb "
			+ "where stsb.id = st.id and st.passport.id = p.id and p.passportNo = ?";
		Query query = session.createQuery(hql);
		query.setString(0, "123ABcd");
		List<Subject> subjects = query.list();
		for(Subject currentSubject : subjects){
			System.out.println(currentSubject.getSubjectName());
		}*/
		DetachedCriteria dCriteria = DetachedCriteria.forClass(Student.class);
		//dCriteria.createAlias("bankAccounts","b");
		//dCriteria.createAlias("subjects","s");
		dCriteria.createAlias("passport","p");		
		dCriteria.add(Restrictions.eq("p.passportNo","123ABcd"));
		List<Student> students = dCriteria.getExecutableCriteria(session).list();
		for(Student curStudent : students){
			Set<Subject> subjects = curStudent.getSubjects();
			for(Subject curSubject : subjects){
				System.out.println(curSubject.getSubjectName());
			}
		}
		
		
		  tx.commit();			
		  session.close();

		/*
		 * String hql =
		 * "select p from Student s,Passport p where p.id= s.passport.id and s.studentName = ?"
		 * ;
		 * 
		 * //sql =
		 * "select p.passportno from Student s,Passport p where p.id= s.passportid and s.studentName = ?"
		 * ; Query query = session.createQuery(hql); query.setString(0, "ab");
		 * List<Passport> searchedPassport = query.list();
		 * 
		 * System.out.println(searchedPassport.get(0).getPassportNo());
		 */
		/*
		 * String hql = "select b from Student s,Passport p,BankAccount b " +
		 * "where b.student.id = s.id and s.passport.id = p.id and p.passportNo = ?"
		 * ; Query query = session.createQuery(hql); query.setString(0,
		 * "123ABcd"); List<BankAccount> accounts = query.list();
		 * for(BankAccount curBankAccount : accounts ){
		 * System.out.println(curBankAccount.getBankAccountNo()); }
		 */
		/*String hql = "select s from Student s,Passport p,BankAccount b "
				+ "where b.student.id = s.id and s.passport.id = p.id and p.passportNo = ?";
		Query query = session.createQuery(hql);
		query.setString(0, "123ABcd");
		List<Student> students = query.list();
		Set<BankAccount> bankAccounts = null;
		boolean bankAccountsRequired = true;*/
		/*if(bankAccountsRequired){
			for (Student curStudent : students) {			
				for (BankAccount curBankAccount : curStudent.getBankAccounts()){
					if(!Hibernate.isInitialized(curBankAccount)){
						Hibernate.initialize(curBankAccount);
					}			
				}
				
			}
		}*/
		
		/*if(bankAccountsRequired){
			for (Student curStudent : students) {
				bankAccounts = curStudent.getBankAccounts();			
			}
			
			for (BankAccount curBankAccount : bankAccounts){
				System.out.println(curBankAccount.getBankAccountNo());
			}
		}
		
		tx.commit();
		
		session.close();*/
		
		
		/*if(bankAccountsRequired){
			for (Student curStudent : students) {
				bankAccounts = curStudent.getBankAccounts();			
			}
			
			for (BankAccount curBankAccount : bankAccounts){
				System.out.println(curBankAccount.getBankAccountNo());
			}
		}*/
		

	}
}
