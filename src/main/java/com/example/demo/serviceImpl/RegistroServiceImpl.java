package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Registro;
import com.example.demo.repository.RegistroRepository;
import com.example.demo.service.Operaciones;

@Service
public class RegistroServiceImpl implements Operaciones<Registro>{
	
	@Autowired
	private RegistroRepository registroRepository;

	@Override
	public Registro create(Registro t) {
		// TODO Auto-generated method stub
		return registroRepository.save(t);
	}

	@Override
	public Registro update(Registro t) {
		// TODO Auto-generated method stub
		return registroRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<Registro> read(Long id) {
		// TODO Auto-generated method stub
		return registroRepository.findById(id);
	}

	@Override
	public List<Registro> readAll() {
		// TODO Auto-generated method stub
		return registroRepository.findAll();
	}

}
