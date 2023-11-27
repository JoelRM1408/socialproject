package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CursoSemestre;
import com.example.demo.repository.CursoSemRepository;
import com.example.demo.service.Operaciones;

@Service
public class CursoSemServiceImpl implements Operaciones<CursoSemestre>{
	
	@Autowired
	private CursoSemRepository cursosemRepository;

	@Override
	public CursoSemestre create(CursoSemestre t) {
		// TODO Auto-generated method stub
		return cursosemRepository.save(t);
	}

	@Override
	public CursoSemestre update(CursoSemestre t) {
		// TODO Auto-generated method stub
		return cursosemRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		cursosemRepository.deleteById(id);
	}

	@Override
	public Optional<CursoSemestre> read(Long id) {
		// TODO Auto-generated method stub
		return cursosemRepository.findById(id);
	}

	@Override
	public List<CursoSemestre> readAll() {
		// TODO Auto-generated method stub
		return cursosemRepository.findAll();
	}

}
