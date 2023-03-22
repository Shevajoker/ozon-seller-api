package com.hoffozonparsing.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoffozonparsing.start.model.AnrexProduct;

public interface AnrexRepository extends JpaRepository<AnrexProduct, Long>{

}
