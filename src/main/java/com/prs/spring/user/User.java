package com.prs.spring.user;

import jakarta.persistence.*;

@Entity
@Table(name="users",uniqueConstraints=@UniqueConstraint(name="UIDX_Username", columnNames = { "username" }))
public class User {
	
	// PROPERTIES
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=30, nullable=false)
	private String username = "";
	@Column(length=30, nullable=false)
	private String password = "";
	@Column(length=30, nullable=false)
	private String firstname = "";
	@Column(length=30, nullable=false)
	private String lastname = "";
	@Column(length=12, nullable=true)
	private String phone;
	@Column(length=255, nullable=true)
	private String email;
	@Column(columnDefinition="bit not null")
	private boolean isReviewr;
	@Column(columnDefinition="bit not null")
	private boolean isAdmin;
	
	// GETTERS & SETTTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getReviewr() {
		return isReviewr;
	}
	public void setReviewr(boolean isReviewr) {
		this.isReviewr = isReviewr;
	}
	public boolean getAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	
}
