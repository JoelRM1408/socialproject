package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EstudianteEquipo;
@Repository
public interface EstudianteEquipoRepository extends JpaRepository<EstudianteEquipo, Long>{
	
}
