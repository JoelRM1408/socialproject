package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Persona;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.service.Operaciones;

@Service
public class PersonaServiceImpl implements Operaciones<Persona>{
	
	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public Persona create(Persona t) {
		// TODO Auto-generated method stub
		return personaRepository.save(t);
	}

	@Override
	public Persona update(Persona t) {
		// TODO Auto-generated method stub
		return personaRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		personaRepository.deleteById(id);
	}

	@Override
	public Optional<Persona> read(Long id) {
		// TODO Auto-generated method stub
		return personaRepository.findById(id);
	}

	@Override
	public List<Persona> readAll() {
		// TODO Auto-generated method stub
		return personaRepository.findAll();
	}

}