package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Product {
	
	@Id
	private String productId;
	private String productName;
	
	@ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
	private List<Category> categories = new ArrayList<>();
	
	public Product() {
		super();
	}

	public Product(String productId, String productName, List<Category> categories) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.categories = categories;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ",categories=" + categories + " ]";
	}
	
	
	

}
