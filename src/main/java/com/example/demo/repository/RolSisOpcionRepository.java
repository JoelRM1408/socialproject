package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.RolSisOpcion;
@Repository
public interface RolSisOpcionRepository extends JpaRepository<RolSisOpcion, Long>{
	
}