package com.hoffozonparsing.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoffozonparsing.start.model.OzonProduct;

public interface OzonRepository extends JpaRepository<OzonProduct, Long>{

	OzonProduct findByIdAnrex(String id_anrex);
	
}
