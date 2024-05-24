package com.example.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.entity.Person;

public class UpdateRecord {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Person person = entityManager.find(Person.class, 2);
		person.setPersonName("Saloni");
		person.setTechnology("Python");
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
