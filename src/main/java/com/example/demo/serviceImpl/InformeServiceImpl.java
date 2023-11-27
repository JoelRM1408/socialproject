package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Informe;
import com.example.demo.repository.InformeRepository;
import com.example.demo.service.Operaciones;

@Service
public class InformeServiceImpl implements Operaciones<Informe>{
	
	@Autowired
	private InformeRepository informeRepository;

	@Override
	public Informe create(Informe t) {
		// TODO Auto-generated method stub
		return informeRepository.save(t);
	}

	@Override
	public Informe update(Informe t) {
		// TODO Auto-generated method stub
		return informeRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		informeRepository.deleteById(id);
	}

	@Override
	public Optional<Informe> read(Long id) {
		// TODO Auto-generated method stub
		return informeRepository.findById(id);
	}

	@Override
	public List<Informe> readAll() {
		// TODO Auto-generated method stub
		return informeRepository.findAll();
	}

}