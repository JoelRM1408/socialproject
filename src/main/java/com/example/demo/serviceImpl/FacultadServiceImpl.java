package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Facultad;
import com.example.demo.repository.FacultadRepository;
import com.example.demo.service.Operaciones;

@Service
public class FacultadServiceImpl implements Operaciones<Facultad>{
	
	@Autowired
	private FacultadRepository facultadRepository;

	@Override
	public Facultad create(Facultad t) {
		// TODO Auto-generated method stub
		return facultadRepository.save(t);
	}

	@Override
	public Facultad update(Facultad t) {
		// TODO Auto-generated method stub
		return facultadRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		facultadRepository.deleteById(id);
	}

	@Override
	public Optional<Facultad> read(Long id) {
		// TODO Auto-generated method stub
		return facultadRepository.findById(id);
	}

	@Override
	public List<Facultad> readAll() {
		// TODO Auto-generated method stub
		return facultadRepository.findAll();
	}

}