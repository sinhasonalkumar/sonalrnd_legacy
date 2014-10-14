package com.sonal.hibernatetraining.day1;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.sonal.hibernatetraining.day1.perstistence.Student;
import com.sonal.hibernatetraining.day1.util.SessionFactoryUtil;

public class Main {
	
	public static void main(String[] args) throws Exception {
		System.out.println("Welcome !!");
		SessionFactory sessionFactory = SessionFactoryUtil.getInstance();		
		Session session1 = sessionFactory.openSession();
		Transaction tx1 = session1.getTransaction();		
		tx1.begin();
		Student student = new Student();
		student.setStudentName("Abc");
		session1.save(student);
		student.setStudentName("xyx");
		Student student1 = (Student)session1.load(Student.class, student.getId());
		student1.setStudentName("dfgdfg");
		tx1.commit();		
		session1.close();
		student.setStudentName("yyy");
		
		
		Session session2 = SessionFactoryUtil.openSession();
		Transaction tx2 = session2.getTransaction();		
		tx2.begin();
		student1 = (Student)session2.load(Student.class, student.getId());
		student1.setStudentName("xcvdfvg");
		session2.merge(student1);
		//session2.merge(student);
		//student.setStudentName("gh");
		student1.setStudentName("gh");
		//Query query = session2.createQuery("from Student s where s.studentName = :studentName ");
		//query.setString("studentName", "abc");
		Query query = session2.createQuery("from Student s where s.studentName = ? ");		
		query.setString(0, "abc");
		List<Student> stuList = query.list();		
		for(Student curStudent : stuList){
			System.out.println(curStudent.getId());
			System.out.println(curStudent.getStudentName());
		}
		List<Student> studentList = session2.getNamedQuery("findStudentByName").setString("studentName", "abc").list();
		
		for(Student curStudent : studentList){
			System.out.println(curStudent.getId());
			System.out.println(curStudent.getStudentName());
		}
		
		DetachedCriteria studentsCriteria = DetachedCriteria.forClass(Student.class);
		studentsCriteria.add(Restrictions.eq("studentName", "gh"));
		List<Student> students = studentsCriteria.getExecutableCriteria(session2).list();
		
		
		for(Student curStudent : students){
			System.out.println(curStudent.getId());
			System.out.println(curStudent.getStudentName());
		}
		for(Student curStudent : students){
			System.out.println(curStudent.getId());
			System.out.println(curStudent.getStudentName());
			session2.delete(curStudent);
		}
		Query deleteQuery = session2.createQuery("delete from Student");
		int executeUpdate = deleteQuery.executeUpdate();
		System.out.println(executeUpdate);
		
		
		tx2.commit();
		session2.close();
		
		System.out.println(student == student1);
		System.out.println(student.getId());
		System.out.println(student1.getId());
		System.out.println(student.equals(student1));
		
		
		
	}
}
