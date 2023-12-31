package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EscuelaProfesional;
@Repository
public interface EscuelaProfesionalRepository extends JpaRepository<EscuelaProfesional, Long>{
	
	@Query(value = "SELECT * FROM ESCUELAS_PROFESIONALES e WHERE e.FACULTAD_ID = ?1",nativeQuery = true)
	List<EscuelaProfesional> findEpByFacultadQueryNative(Integer facultadid);
}
