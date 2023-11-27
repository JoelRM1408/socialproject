package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TipoProyecto;
import com.example.demo.repository.TipoProyectoRepository;
import com.example.demo.service.Operaciones;

@Service
public class TipoProyectoServiceImpl implements Operaciones<TipoProyecto>{
	
	@Autowired
	private TipoProyectoRepository tipoproyectoRepository;

	@Override
	public TipoProyecto create(TipoProyecto t) {
		// TODO Auto-generated method stub
		return tipoproyectoRepository.save(t);
	}

	@Override
	public TipoProyecto update(TipoProyecto t) {
		// TODO Auto-generated method stub
		return tipoproyectoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<TipoProyecto> read(Long id) {
		// TODO Auto-generated method stub
		return tipoproyectoRepository.findById(id);
	}

	@Override
	public List<TipoProyecto> readAll() {
		// TODO Auto-generated method stub
		return tipoproyectoRepository.findAll();
	}

}
