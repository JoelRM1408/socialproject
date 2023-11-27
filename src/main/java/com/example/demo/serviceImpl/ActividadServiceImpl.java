package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Actividad;
import com.example.demo.repository.ActividadRepository;
import com.example.demo.service.Operaciones;

@Service
public class ActividadServiceImpl implements Operaciones<Actividad>{
	
	@Autowired
	private ActividadRepository actividadRepository;

	@Override
	public Actividad create(Actividad t) {
		// TODO Auto-generated method stub
		return actividadRepository.save(t);
	}

	@Override
	public Actividad update(Actividad t) {
		// TODO Auto-generated method stub
		return actividadRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		actividadRepository.deleteById(id);
	}

	@Override
	public Optional<Actividad> read(Long id) {
		// TODO Auto-generated method stub
		return actividadRepository.findById(id);
	}

	@Override
	public List<Actividad> readAll() {
		// TODO Auto-generated method stub
		return actividadRepository.findAll();
	}

}