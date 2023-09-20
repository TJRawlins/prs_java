package com.prs.spring.request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prs.spring.requestlines.RequestLine;
import jakarta.persistence.*;

@Entity
@Table(name="requests")
public class Request {
	
	// PROPERTIES
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=80, nullable=false)
	private String description = "";
	@Column(length=80, nullable=false)
	private String justification = "";
	@Column(length=80, nullable=true)
	private String rejectionReason;
	@Column(length=20, nullable=false)
	private String deliveryMode = "Pickup";
	@Column(length=10, nullable=false)
	private String Status = "NEW";
	@Column(columnDefinition="decimal(11,2) not null")
	private double total = 0;
	
	// FOREIGN KEY
	@JsonManagedReference
	@OneToMany(mappedBy="request")
	private List<RequestLine> requestlines;

	// GETTERS & SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<RequestLine> getRequestlines() {
		return requestlines;
	}

	public void setRequestlines(List<RequestLine> requestlines) {
		this.requestlines = requestlines;
	}
	
	
	
}
