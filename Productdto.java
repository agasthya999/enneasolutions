package com.ennea.inventory.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Productdto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
 private String productName;
 private String code;
 private int stock;
 private String supllier;
public Productdto(long id, String productName, String code, int stock, String supllier) {
	super();
	this.id = id;
	this.productName = productName;
	this.code = code;
	this.stock = stock;
	this.supllier = supllier;
}
public Productdto() {
	super();
	// TODO Auto-generated constructor stub
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}
public String getSupllier() {
	return supllier;
}
public void setSupllier(String supllier) {
	this.supllier = supllier;
}
 
}
