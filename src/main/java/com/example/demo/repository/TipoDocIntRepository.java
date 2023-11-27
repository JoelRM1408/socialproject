package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TipoDocInt;
@Repository
public interface TipoDocIntRepository extends JpaRepository<TipoDocInt, Long>{
	
}

