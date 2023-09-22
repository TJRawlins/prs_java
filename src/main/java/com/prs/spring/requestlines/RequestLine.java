package com.prs.spring.requestlines;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.prs.spring.product.Product;
import com.prs.spring.request.Request;

import jakarta.persistence.*;

@Entity
@Table(name = "requestlines")
public class RequestLine {
	
	// PROPERTIES
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private int quantity = 1;
		
	// FOREIGN KEYS
	@ManyToOne(optional=false)
	@JoinColumn(name="productId")
	private Product product;
	
	@JsonBackReference
	@ManyToOne(optional=false)
	@JoinColumn(name="requestId")
	private Request request;
	

	// GETTERS & SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

		

}
