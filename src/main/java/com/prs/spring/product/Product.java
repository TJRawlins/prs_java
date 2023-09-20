package com.prs.spring.product;

import com.prs.spring.vendor.Vendor;

import jakarta.persistence.*;

@Entity
@Table(name="products",uniqueConstraints=@UniqueConstraint(name="UIDX_PartNbr", columnNames = { "partNbr" }))
public class Product {
	
	// PROPERTIES
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=30, nullable=false)
	private String partNbr = "";
	@Column(length=30, nullable=false)
	private String name = "";
	@Column(columnDefinition="decimal(11,2)")
	private double price;
	@Column(length=30, nullable=false)
	private String unit = "";
	@Column(length=255, nullable=true)
	private String photoPath;
	
	// FOREIGN KEY
	@ManyToOne(optional=false)
	@JoinColumn(name="vendorId")
	private Vendor vendors;

	// GETTERS & SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPartNbr() {
		return partNbr;
	}

	public void setPartNbr(String partNbr) {
		this.partNbr = partNbr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Vendor getVendors() {
		return vendors;
	}

	public void setVendors(Vendor vendors) {
		this.vendors = vendors;
	}
	
}
