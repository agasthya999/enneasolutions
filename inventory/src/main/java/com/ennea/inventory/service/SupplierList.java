package com.ennea.inventory.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ennea.inventory.model.Supplier;
import com.ennea.inventory.dto.Productdto;
import com.ennea.inventory.model.Product;
import com.ennea.inventory.repository.ProductRepository;
import com.ennea.inventory.repository.SupplierRepository;

@Service
public class SupplierList {
	 @Autowired
	   private ProductRepository productRepository;
	 @Autowired
	 private SupplierRepository supplierRepository;
	public void filltable() {
		 List<Product> productlist=productRepository.findAll();
		 List<Supplier> supplierlist=new ArrayList<>();
		 Iterator<Product> itr=productlist.iterator();
		 while(itr.hasNext()) {
			   Product product=itr.next();
			   String productName=product.getProductName();
			    String code=product.getProductCode();
			    String supllier=product.getSupplier();
			    String company=product.getCompany();
			    Date expiry=product.getExpiry();
			    Date now = new java.util.Date();  
			    if(expiry!=null &&expiry.compareTo(now)>0) {
			   supplierlist.add(new Supplier( code,productName, 
					   company,expiry,
					   supllier));
			    }
			   supplierRepository.saveAll(supplierlist);
		   }
	}
}
