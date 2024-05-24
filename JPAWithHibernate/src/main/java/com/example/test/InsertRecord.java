package com.example.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.entity.Person;

public class InsertRecord {

	public static void main(String[] args) {
		
		
		Person person = new Person();
		person.setPersonName("Ajay");
		person.setTechnology("Java");
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(person);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		System.out.println(person);


	}

}
