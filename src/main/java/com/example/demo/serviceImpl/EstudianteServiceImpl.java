package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Estudiante;
import com.example.demo.repository.EstudianteRepository;
import com.example.demo.service.Operaciones;

@Service
public class EstudianteServiceImpl implements Operaciones<Estudiante>{
	
	@Autowired
	private EstudianteRepository estudianteRepository;

	@Override
	public Estudiante create(Estudiante t) {
		// TODO Auto-generated method stub
		return estudianteRepository.save(t);
	}

	@Override
	public Estudiante update(Estudiante t) {
		// TODO Auto-generated method stub
		return estudianteRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		estudianteRepository.deleteById(id);
	}

	@Override
	public Optional<Estudiante> read(Long id) {
		// TODO Auto-generated method stub
		return estudianteRepository.findById(id);
	}

	@Override
	public List<Estudiante> readAll() {
		// TODO Auto-generated method stub
		return estudianteRepository.findAll();
	}

}