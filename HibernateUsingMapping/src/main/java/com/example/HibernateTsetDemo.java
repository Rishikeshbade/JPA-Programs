package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.entity.UserDetails;

public class HibernateTsetDemo {

	public static void main(String[] args) {
		UserDetails userDetails = new UserDetails(101, "Raj", "789678465", "Pune");
		
		insertUserDetails(userDetails);
		
		System.out.println(userDetails);
	}

	public static void insertUserDetails(UserDetails userDetails) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			session.save(userDetails);
			transaction.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
