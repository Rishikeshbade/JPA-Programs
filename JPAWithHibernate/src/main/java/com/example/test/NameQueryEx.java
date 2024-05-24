package com.example.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.example.entity.Employee;

public class NameQueryEx {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		Query query = entityManager.createNamedQuery("findBySalaryRange");
		query.setParameter("min", 20000f);
		query.setParameter("max", 50000f);
		List<Employee> employees = query.getResultList();
		/*for(Employee employee : employees) {
			System.out.println(employee);
		}*/
		
		employees.forEach(e -> System.out.println(e));
		
		entityManager.getTransaction();
		entityManager.close();

	}

}
