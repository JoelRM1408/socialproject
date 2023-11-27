package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Asistencia;
import com.example.demo.repository.AsistenciaRepository;
import com.example.demo.service.Operaciones;

@Service
public class AsistenciaServiceImpl implements Operaciones<Asistencia>{
	
	@Autowired
	private AsistenciaRepository asistenciaRepository;

	@Override
	public Asistencia create(Asistencia t) {
		// TODO Auto-generated method stub
		return asistenciaRepository.save(t);
	}

	@Override
	public Asistencia update(Asistencia t) {
		// TODO Auto-generated method stub
		return asistenciaRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		asistenciaRepository.deleteById(id);
	}

	@Override
	public Optional<Asistencia> read(Long id) {
		// TODO Auto-generated method stub
		return asistenciaRepository.findById(id);
	}

	@Override
	public List<Asistencia> readAll() {
		// TODO Auto-generated method stub
		return asistenciaRepository.findAll();
	}

}