package com.bazaar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bazaar.model.Shipper;

public interface ShipperDao extends JpaRepository<Shipper, Integer>{

}
