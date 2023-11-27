package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DocumentoIntervencion;
@Repository
public interface DocumentoIntervencionRepository extends JpaRepository<DocumentoIntervencion, Long>{
	
	@Query(value = "SELECT * FROM DOCUMENTOS_INTERVENCION d WHERE d.TIPO_DOCINT_ID = ?1",nativeQuery = true)
	List<DocumentoIntervencion> findDocIntByTipoQueryNative(Integer tipodi);
}