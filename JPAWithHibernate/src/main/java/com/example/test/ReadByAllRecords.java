package com.example.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.example.entity.Person;

public class ReadByAllRecords {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		String query ="select p from Person p";
		
		Query query1 = entityManager.createQuery(query,Person.class);
		
		List<Person> persons = query1.getResultList();
		
		for (Person person : persons) {
			System.out.println(person);
		}
		
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
