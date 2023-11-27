package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Ciclo;
import com.example.demo.repository.CicloRepository;
import com.example.demo.service.Operaciones;

@Service
public class CicloServiceImpl implements Operaciones<Ciclo>{
	
	@Autowired
	private CicloRepository cicloRepository;

	@Override
	public Ciclo create(Ciclo t) {
		// TODO Auto-generated method stub
		return cicloRepository.save(t);
	}

	@Override
	public Ciclo update(Ciclo t) {
		// TODO Auto-generated method stub
		return cicloRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		cicloRepository.deleteById(id);
	}

	@Override
	public Optional<Ciclo> read(Long id) {
		// TODO Auto-generated method stub
		return cicloRepository.findById(id);
	}

	@Override
	public List<Ciclo> readAll() {
		// TODO Auto-generated method stub
		return cicloRepository.findAll();
	}

}