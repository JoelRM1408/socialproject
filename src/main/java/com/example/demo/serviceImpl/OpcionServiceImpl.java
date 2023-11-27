package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Opcion;
import com.example.demo.repository.OpcionRepository;
import com.example.demo.service.Operaciones;

@Service
public class OpcionServiceImpl implements Operaciones<Opcion>{
	
	@Autowired
	private OpcionRepository asistenciaRepository;

	@Override
	public Opcion create(Opcion t) {
		// TODO Auto-generated method stub
		return asistenciaRepository.save(t);
	}

	@Override
	public Opcion update(Opcion t) {
		// TODO Auto-generated method stub
		return asistenciaRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		asistenciaRepository.deleteById(id);
	}

	@Override
	public Optional<Opcion> read(Long id) {
		// TODO Auto-generated method stub
		return asistenciaRepository.findById(id);
	}

	@Override
	public List<Opcion> readAll() {
		// TODO Auto-generated method stub
		return asistenciaRepository.findAll();
	}

}