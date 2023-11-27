package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RolSis;
import com.example.demo.repository.RolSisRepository;
import com.example.demo.service.Operaciones;

@Service
public class RolSisServiceImpl implements Operaciones<RolSis>{
	
	@Autowired
	private RolSisRepository rolsisRepository;

	@Override
	public RolSis create(RolSis t) {
		// TODO Auto-generated method stub
		return rolsisRepository.save(t);
	}

	@Override
	public RolSis update(RolSis t) {
		// TODO Auto-generated method stub
		return rolsisRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<RolSis> read(Long id) {
		// TODO Auto-generated method stub
		return rolsisRepository.findById(id);
	}

	@Override
	public List<RolSis> readAll() {
		// TODO Auto-generated method stub
		return rolsisRepository.findAll();
	}

}
