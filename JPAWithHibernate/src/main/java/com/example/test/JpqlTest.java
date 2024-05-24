package com.example.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.example.entity.Employee;

public class JpqlTest {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();

	public static void insertRecord() {
		Employee employee = new Employee();
		employee.setEmpName("Ravi");
		employee.setDeptName("HR");
		employee.setSalary(45000);

		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println(employee);
	}

	public static void findByDept(String dept) {
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("select e from Employee e where e");
		query.setParameter("deptName", dept);
		List<Employee> employees = query.getResultList();
		System.out.println("Fetchig Records on the basis of DepartMent : ");
		for (Employee employee : employees) {
			System.out.println(employee);
		}
		entityManager.getTransaction().commit();
	}

	public static void sortEmployyes() {
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("select e from Employee e order by e.salary");
		List<Employee> employees = query.getResultList();
		System.out.println("Sorting in ascending order of salary : ");
		for (Employee employee : employees) {
			System.out.println(employee);
		}
		entityManager.getTransaction().commit();
		
	}

	public static void findEmployeeByPattern() {
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("select e from Employee e where e.empName like :nameStartsWith ");
		query.setParameter("nameStartsWith", "R%");
		List<Employee> employees = query.getResultList();
		System.out.println("Fetching records whose name starts with character 'R' : ");
		for (Employee employee : employees) {
			System.out.println(employee);
		}
		entityManager.getTransaction().commit();
		
	}
	
	public static void getSalaryByRange() {
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("findBySalaryRange");
		query.setParameter("min", 20000);
		query.setParameter("max", 50000);
		List<Employee> employees = query.getResultList();
		for(Employee employee : employees) {
			System.out.println(employee);
		}
		
		entityManager.getTransaction();
	
	}
	
	public static void sortDescEmployyes() {
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("Select e from Employee e order by e.salary DESC");
		List<Employee> employees = query.getResultList();
		System.out.println("Sorting in Descing order of salary : ");
		for (Employee employee : employees) {
			System.out.println(employee);
		}
		entityManager.getTransaction().commit();
	
	}
	
	public static void getEmployeeWithHighSalary(String Dept, double salary) {
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.department = :dept AND e.salary > :salary");
		query.setParameter("dept", Dept);
		query.setParameter("salary", salary);
		List<Employee> employees = query.getResultList();
		for(Employee employee : employees) {
			System.out.println(employee);
		}
		
		entityManager.getTransaction();
		
		
		
		
	}
	

	public static void main(String[] args) {
		insertRecord();
		/*findByDept("Developer");
		findEmployeeByPattern();
		sortEmployyes();*/
		getSalaryByRange();
		sortDescEmployyes();
		getEmployeeWithHighSalary("HR", 40000);
	}

}
