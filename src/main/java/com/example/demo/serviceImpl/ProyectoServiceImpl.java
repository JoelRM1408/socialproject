package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Proyecto;
import com.example.demo.repository.ProyectoRepository;
import com.example.demo.service.Operaciones;

@Service
public class ProyectoServiceImpl implements Operaciones<Proyecto>{
	
	@Autowired
	private ProyectoRepository proyectoRepository;

	@Override
	public Proyecto create(Proyecto t) {
		// TODO Auto-generated method stub
		return proyectoRepository.save(t);
	}

	@Override
	public Proyecto update(Proyecto t) {
		// TODO Auto-generated method stub
		return proyectoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		proyectoRepository.deleteById(id);
	}

	@Override
	public Optional<Proyecto> read(Long id) {
		// TODO Auto-generated method stub
		return proyectoRepository.findById(id);
	}

	@Override
	public List<Proyecto> readAll() {
		// TODO Auto-generated method stub
		return proyectoRepository.findAll();
	}

}