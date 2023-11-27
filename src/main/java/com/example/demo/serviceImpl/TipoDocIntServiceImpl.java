package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TipoDocInt;
import com.example.demo.repository.TipoDocIntRepository;
import com.example.demo.service.Operaciones;

@Service
public class TipoDocIntServiceImpl implements Operaciones<TipoDocInt>{
	
	@Autowired
	private TipoDocIntRepository tipodocintRepository;

	@Override
	public TipoDocInt create(TipoDocInt t) {
		// TODO Auto-generated method stub
		return tipodocintRepository.save(t);
	}

	@Override
	public TipoDocInt update(TipoDocInt t) {
		// TODO Auto-generated method stub
		return tipodocintRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		tipodocintRepository.deleteById(id);
	}

	@Override
	public Optional<TipoDocInt> read(Long id) {
		// TODO Auto-generated method stub
		return tipodocintRepository.findById(id);
	}

	@Override
	public List<TipoDocInt> readAll() {
		// TODO Auto-generated method stub
		return tipodocintRepository.findAll();
	}

}