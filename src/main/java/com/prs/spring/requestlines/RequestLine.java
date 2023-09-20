package com.prs.spring.requestlines;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.prs.spring.request.Request;
import jakarta.persistence.*;

@Entity
@Table(name = "requestlines")
public class RequestLine {
	
	// PROPERTIES
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int quantity = 1;
		
	// FOREIGN KEY
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
		

}
