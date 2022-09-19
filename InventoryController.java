package com.ennea.inventory.restcontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ennea.inventory.dto.Productdto;
import com.ennea.inventory.dto.Supplierdto;
import com.ennea.inventory.model.Product;
import com.ennea.inventory.model.Supplier;
import com.ennea.inventory.repository.ProductRepository;
import com.ennea.inventory.repository.SupplierRepository;
import com.ennea.inventory.service.ImportData;
import com.ennea.inventory.service.SupplierList;

import java.util.List;
@RestController
public class InventoryController {
  @Autowired
	private ImportData importdata;
   @Autowired
   private ProductRepository productRepository;
   @Autowired
   private SupplierRepository supplierRepository;
   @Autowired
	 private SupplierList supplierList;
   
   
   @GetMapping("/readexceldata")
	public void readExcelData() {
		productRepository.saveAll(this.importdata.excelimport());
	     supplierList.filltable();
	}
    
   @GetMapping("/getproduct")
     public List<Productdto> getproducts(@RequestParam String sup,@RequestParam (required = false) String productname){
	   List<Product> productlist=productRepository.findBysupplier(sup);
	   List<Productdto> productdtolist=new ArrayList<>();
	   Iterator<Product> itr=productlist.iterator();
	   while(itr.hasNext()) {
		   Product product=itr.next();
		   long id=product.getId();
		   String productName=product.getProductName();
		    String code=product.getProductCode();
		    int stock=product.getStock();
		    String supllier=product.getSupplier();
		    if(productname==null ||productname.contentEquals(productname))
		    {
		   productdtolist.add(new Productdto( id, productName, 
				  code, stock,
				   supllier));
		    }  
	   }
	   
	   return productdtolist;
     }
   @GetMapping("/getsupplier")
   public List<Supplierdto> getsupplier(@RequestParam String sup){
	   List<Supplier> supplierlist=supplierRepository.findBysupplier(sup);
	   List<Supplierdto> supplierdtolist=new ArrayList<>();
	   Iterator<Supplier> itr=supplierlist.iterator();
	   while(itr.hasNext()) {
		   Supplier supplier=itr.next();
		   String productName=supplier.getProductName();
		    String code=supplier.getProductCode();
		    String supllier=supplier.getSupplier();
		    Date expiry=supplier.getExpiry();  
		 supplierdtolist.add(new Supplierdto( code,productName,expiry, supllier));
		    
	   }
	   return supplierdtolist;
   }
}
