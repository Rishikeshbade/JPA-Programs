package com.example.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.entity.Person;

public class DeleteRecord {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Person person = entityManager.find(Person.class, 2);
		entityManager.remove(person);
		System.out.println("deleted successfully");
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
