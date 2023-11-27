package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UsuarioOpcion;
import com.example.demo.repository.UsuarioOpcionRepository;
import com.example.demo.service.Operaciones;

@Service
public class UsuarioOpcionServiceImpl implements Operaciones<UsuarioOpcion>{
	
	@Autowired
	private UsuarioOpcionRepository usuariopcRepository;

	@Override
	public UsuarioOpcion create(UsuarioOpcion t) {
		// TODO Auto-generated method stub
		return usuariopcRepository.save(t);
	}

	@Override
	public UsuarioOpcion update(UsuarioOpcion t) {
		// TODO Auto-generated method stub
		return usuariopcRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<UsuarioOpcion> read(Long id) {
		// TODO Auto-generated method stub
		return usuariopcRepository.findById(id);
	}

	@Override
	public List<UsuarioOpcion> readAll() {
		// TODO Auto-generated method stub
		return usuariopcRepository.findAll();
	}

}
