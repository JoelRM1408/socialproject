package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EscuelaProfesional;
import com.example.demo.repository.EscuelaProfesionalRepository;
import com.example.demo.service.Operaciones;

@Service
public class EscuelaProfesionalServiceImpl implements Operaciones<EscuelaProfesional>{
	
	@Autowired
	private EscuelaProfesionalRepository epRepository;

	@Override
	public EscuelaProfesional create(EscuelaProfesional t) {
		// TODO Auto-generated method stub
		return epRepository.save(t);
	}

	@Override
	public EscuelaProfesional update(EscuelaProfesional t) {
		// TODO Auto-generated method stub
		return epRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		epRepository.deleteById(id);
	}

	@Override
	public Optional<EscuelaProfesional> read(Long id) {
		// TODO Auto-generated method stub
		return epRepository.findById(id);
	}

	@Override
	public List<EscuelaProfesional> readAll() {
		// TODO Auto-generated method stub
		return epRepository.findAll();
	}
	
	public List<EscuelaProfesional> searchEpbyFacultad(Integer facultadid){
		return epRepository.findEpByFacultadQueryNative(facultadid);
	}
}
