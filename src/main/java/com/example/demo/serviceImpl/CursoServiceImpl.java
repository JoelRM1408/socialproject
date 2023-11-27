package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Curso;
import com.example.demo.repository.CursoRepository;
import com.example.demo.service.Operaciones;

@Service
public class CursoServiceImpl implements Operaciones<Curso>{
	
	@Autowired
	private CursoRepository cursoRepository;

	@Override
	public Curso create(Curso t) {
		// TODO Auto-generated method stub
		return cursoRepository.save(t);
	}

	@Override
	public Curso update(Curso t) {
		// TODO Auto-generated method stub
		return cursoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		cursoRepository.deleteById(id);
	}

	@Override
	public Optional<Curso> read(Long id) {
		// TODO Auto-generated method stub
		return cursoRepository.findById(id);
	}

	@Override
	public List<Curso> readAll() {
		// TODO Auto-generated method stub
		return cursoRepository.findAll();
	}

}
