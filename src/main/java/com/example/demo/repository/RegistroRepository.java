package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Registro;
@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long>{
	
}