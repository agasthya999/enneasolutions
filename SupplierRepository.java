package com.ennea.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ennea.inventory.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, String> {

	List<Supplier> findBysupplier(String sup);

	List<Supplier> findBycompany(String sup);

	
}
