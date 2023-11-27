package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.service.Operaciones;

@Service
public class DocenteServiceImpl implements Operaciones<Docente>{
	
	@Autowired
	private DocenteRepository docenteRepository;

	@Override
	public Docente create(Docente t) {
		// TODO Auto-generated method stub
		return docenteRepository.save(t);
	}

	@Override
	public Docente update(Docente t) {
		// TODO Auto-generated method stub
		return docenteRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		docenteRepository.deleteById(id);
	}

	@Override
	public Optional<Docente> read(Long id) {
		// TODO Auto-generated method stub
		return docenteRepository.findById(id);
	}

	@Override
	public List<Docente> readAll() {
		// TODO Auto-generated method stub
		return docenteRepository.findAll();
	}

}
