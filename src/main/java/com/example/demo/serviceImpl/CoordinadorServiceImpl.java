package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Coordinador;
import com.example.demo.repository.CoordinadorRepository;
import com.example.demo.service.Operaciones;

@Service
public class CoordinadorServiceImpl implements Operaciones<Coordinador>{
	
	@Autowired
	private CoordinadorRepository coordinadorRepository;

	@Override
	public Coordinador create(Coordinador t) {
		// TODO Auto-generated method stub
		return coordinadorRepository.save(t);
	}

	@Override
	public Coordinador update(Coordinador t) {
		// TODO Auto-generated method stub
		return coordinadorRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		coordinadorRepository.deleteById(id);
	}

	@Override
	public Optional<Coordinador> read(Long id) {
		// TODO Auto-generated method stub
		return coordinadorRepository.findById(id);
	}

	@Override
	public List<Coordinador> readAll() {
		// TODO Auto-generated method stub
		return coordinadorRepository.findAll();
	}

}
