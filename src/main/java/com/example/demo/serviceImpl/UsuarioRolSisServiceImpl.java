package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UsuarioRolSis;
import com.example.demo.repository.UsuarioRolSisRepository;
import com.example.demo.service.Operaciones;

@Service
public class UsuarioRolSisServiceImpl implements Operaciones<UsuarioRolSis>{
	
	@Autowired
	private UsuarioRolSisRepository usrolsisRepository;

	@Override
	public UsuarioRolSis create(UsuarioRolSis t) {
		// TODO Auto-generated method stub
		return usrolsisRepository.save(t);
	}

	@Override
	public UsuarioRolSis update(UsuarioRolSis t) {
		// TODO Auto-generated method stub
		return usrolsisRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<UsuarioRolSis> read(Long id) {
		// TODO Auto-generated method stub
		return usrolsisRepository.findById(id);
	}

	@Override
	public List<UsuarioRolSis> readAll() {
		// TODO Auto-generated method stub
		return usrolsisRepository.findAll();
	}

}
