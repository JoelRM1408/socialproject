package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RolProyecto;
import com.example.demo.repository.RolProyectoRepository;
import com.example.demo.service.Operaciones;

@Service
public class RolProyectoServiceImpl implements Operaciones<RolProyecto>{
	
	@Autowired
	private RolProyectoRepository rolproyRepository;

	@Override
	public RolProyecto create(RolProyecto t) {
		// TODO Auto-generated method stub
		return rolproyRepository.save(t);
	}

	@Override
	public RolProyecto update(RolProyecto t) {
		// TODO Auto-generated method stub
		return rolproyRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<RolProyecto> read(Long id) {
		// TODO Auto-generated method stub
		return rolproyRepository.findById(id);
	}

	@Override
	public List<RolProyecto> readAll() {
		// TODO Auto-generated method stub
		return rolproyRepository.findAll();
	}

}