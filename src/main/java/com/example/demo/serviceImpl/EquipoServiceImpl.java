package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Equipo;
import com.example.demo.repository.EquipoRepository;
import com.example.demo.service.Operaciones;

@Service
public class EquipoServiceImpl implements Operaciones<Equipo>{
	
	@Autowired
	private EquipoRepository equipoRepository;

	@Override
	public Equipo create(Equipo t) {
		// TODO Auto-generated method stub
		return equipoRepository.save(t);
	}

	@Override
	public Equipo update(Equipo t) {
		// TODO Auto-generated method stub
		return equipoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		equipoRepository.deleteById(id);
	}

	@Override
	public Optional<Equipo> read(Long id) {
		// TODO Auto-generated method stub
		return equipoRepository.findById(id);
	}

	@Override
	public List<Equipo> readAll() {
		// TODO Auto-generated method stub
		return equipoRepository.findAll();
	}

}