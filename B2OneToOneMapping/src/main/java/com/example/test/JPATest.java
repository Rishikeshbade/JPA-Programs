package com.example.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.entity.User;
import com.example.entity.Vehicle;

public class JPATest {

	public static void main(String[] args) {
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("BMW");
		
		User user = new User();
		user.setUserName("Riya");
		user.setVehicle(vehicle);
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(vehicle);
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
