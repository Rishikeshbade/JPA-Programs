package com.example.test;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.example.entity.Category;
import com.example.entity.Product;

public class JPATest {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction transaction;
	
	
	
	public void insertRecord() {
		transaction = entityManager.getTransaction();
		transaction.begin();
		
		Category category = new Category();
		category.setCategoryId("C2");
		category.setCategoryName("Electronics");
		
		Product product = new Product();
		product.setProductId("P5");
		product.setProductName("Smart Tv");
		
		Product product2 = new Product();
		product2.setProductId("P6");
		product2.setProductName("Redmi 9");
		
		/*Category category2 = new Category();
		category.setCategoryId("C2");
		category.setCategoryName("Electronics");
		
		Product product1 = new Product();
		product1.setProductId("P1");
		product1.setProductName("Iphone 6");
		
		Product product21 = new Product();
		product21.setProductId("P2");
		product21.setProductName("Redmi 9");*/
		
		category.getProducts().add(product);
		category.getProducts().add(product2);
		//product.getCategories().add(category);
		
		entityManager.persist(category);
		transaction.commit();
		System.out.println("Record inserted successfully!");
		
		
	}
	
	public void categoryById(String categoryId) {
		transaction = entityManager.getTransaction();
		transaction.begin();
		
		Category category = entityManager.find(Category.class, categoryId);
		if(category != null) {
			System.out.println("Category Name : "+category.getCategoryName());
			
			List<Product> products = category.getProducts();
			System.out.println("Product Associated with Category : ");
			
			for(Product product : products) {
				System.out.println(product);
			}
		}else {
			System.out.println("Category not Found.");
		}
		transaction.commit();
		
	}
	
	public void deleteByCategoryId(String categoryIdToDelete) {
		transaction = entityManager.getTransaction();
		transaction.begin();
		
		Category category = entityManager.find(Category.class, categoryIdToDelete);
		
		if(categoryIdToDelete != null) {
			entityManager.remove(category);
			
			System.out.println("Category with ID : "+categoryIdToDelete+"deleted successfully");
		}else {
			System.out.println("Category with ID : "+categoryIdToDelete+"Not Found");
		}
		
		transaction.commit();
	}
	
	public void updateByCategoryId(String categoryIdToUpdate, String newCategoryName, String productIdToUpdate, String newProductName) {
	    transaction = entityManager.getTransaction();
	    transaction.begin();

	    Category category = entityManager.find(Category.class, categoryIdToUpdate);
	    Product product = entityManager.find(Product.class, productIdToUpdate);
	    
	    if(category != null) {
	        category.setCategoryName(newCategoryName);
	        if(product != null) {
	            product.setProductName(newProductName);
	            entityManager.merge(product);
	            System.out.println("Product with ID: " + productIdToUpdate + " updated successfully!");
	        } else {
	            System.out.println("Product with ID: " + productIdToUpdate + " not found.");
	        }
	        entityManager.merge(category);
	        System.out.println("Category with ID: " + categoryIdToUpdate + " updated successfully!");
	    } else {
	        System.out.println("Category with ID: " + categoryIdToUpdate + " not found.");
	    }
	    
	    transaction.commit();
	}

		

	
	
	  public void findAllCategories() {
	        transaction = entityManager.getTransaction();
	        transaction.begin();

	        TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c", Category.class);
	        List<Category> categories = query.getResultList();
	        if (!categories.isEmpty()) {
	            System.out.println("All Categories:");
	            for (Category category : categories) {
	                System.out.println(category.getCategoryId() + " - " + category.getCategoryName());
	            }
	        } else {
	            System.out.println("No categories found.");
	        }

	        transaction.commit();
	    }



	
	public  static void main(String[] args) {

		JPATest jpaTest = new JPATest();
		//jpaTest.insertRecord();
		//jpaTest.categoryById("C2");
		//jpaTest.deleteByCategoryId("C2");
		jpaTest.updateByCategoryId("C2", "Furniture", "P5", "Chair");
		//jpaTest.findAllCategories();
		
		

	}
	
	
	
}
