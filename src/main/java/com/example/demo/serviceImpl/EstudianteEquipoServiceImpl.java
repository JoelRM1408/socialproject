package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EstudianteEquipo;
import com.example.demo.repository.EstudianteEquipoRepository;
import com.example.demo.service.Operaciones;

@Service
public class EstudianteEquipoServiceImpl implements Operaciones<EstudianteEquipo>{
	
	@Autowired
	private EstudianteEquipoRepository estdequiRepository;

	@Override
	public EstudianteEquipo create(EstudianteEquipo t) {
		// TODO Auto-generated method stub
		return estdequiRepository.save(t);
	}

	@Override
	public EstudianteEquipo update(EstudianteEquipo t) {
		// TODO Auto-generated method stub
		return estdequiRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		estdequiRepository.deleteById(id);
	}

	@Override
	public Optional<EstudianteEquipo> read(Long id) {
		// TODO Auto-generated method stub
		return estdequiRepository.findById(id);
	}

	@Override
	public List<EstudianteEquipo> readAll() {
		// TODO Auto-generated method stub
		return estdequiRepository.findAll();
	}

}