package com.hoffozonparsing.start.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoffozonparsing.start.model.AnrexProduct;

public interface AnrexDao extends JpaRepository<AnrexProduct, Long>{

}
