package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Opcion;
@Repository
public interface OpcionRepository extends JpaRepository<Opcion, Long>{
	
}
