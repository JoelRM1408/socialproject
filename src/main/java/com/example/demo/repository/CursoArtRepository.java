package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CursoArticulado;
@Repository
public interface CursoArtRepository extends JpaRepository<CursoArticulado, Long>{
	
}
