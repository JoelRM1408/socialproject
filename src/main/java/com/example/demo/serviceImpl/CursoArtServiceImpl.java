package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CursoArticulado;
import com.example.demo.repository.CursoArtRepository;
import com.example.demo.service.Operaciones;

@Service
public class CursoArtServiceImpl implements Operaciones<CursoArticulado>{
	
	@Autowired
	private CursoArtRepository cursoartRepository;

	@Override
	public CursoArticulado create(CursoArticulado t) {
		// TODO Auto-generated method stub
		return cursoartRepository.save(t);
	}

	@Override
	public CursoArticulado update(CursoArticulado t) {
		// TODO Auto-generated method stub
		return cursoartRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		cursoartRepository.deleteById(id);
	}

	@Override
	public Optional<CursoArticulado> read(Long id) {
		// TODO Auto-generated method stub
		return cursoartRepository.findById(id);
	}

	@Override
	public List<CursoArticulado> readAll() {
		// TODO Auto-generated method stub
		return cursoartRepository.findAll();
	}

}
