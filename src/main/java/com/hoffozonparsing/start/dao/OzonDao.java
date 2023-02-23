package com.hoffozonparsing.start.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoffozonparsing.start.model.OzonProduct;

public interface OzonDao extends JpaRepository<OzonProduct, Long>{

	OzonProduct findByIdAnrex(String id_anrex);
	
}
