package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RolSisOpcion;
import com.example.demo.repository.RolSisOpcionRepository;
import com.example.demo.service.Operaciones;

@Service
public class RolSisOpcionServiceImpl implements Operaciones<RolSisOpcion>{
	
	@Autowired
	private RolSisOpcionRepository rolsisopcRepository;

	@Override
	public RolSisOpcion create(RolSisOpcion t) {
		// TODO Auto-generated method stub
		return rolsisopcRepository.save(t);
	}

	@Override
	public RolSisOpcion update(RolSisOpcion t) {
		// TODO Auto-generated method stub
		return rolsisopcRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<RolSisOpcion> read(Long id) {
		// TODO Auto-generated method stub
		return rolsisopcRepository.findById(id);
	}

	@Override
	public List<RolSisOpcion> readAll() {
		// TODO Auto-generated method stub
		return rolsisopcRepository.findAll();
	}

}
