package com.example.test;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.entity.Books;
import com.example.entity.Student;

public class JPATest {

	public static void main(String[] args) {
		
		Books books1 = new Books();
		books1.setBookName("Python");
		
		Books books2 = new Books();
		books2.setBookName("Learn Core Java");
		
		ArrayList<Books> list = new ArrayList<Books>();
		list.add(books1);
		list.add(books2);
		
		Student student = new Student();
		student.setStudentName("Rishi");
		student.setBooks_issued(list);
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(books1);
		entityManager.persist(books2);
		entityManager.persist(student);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
