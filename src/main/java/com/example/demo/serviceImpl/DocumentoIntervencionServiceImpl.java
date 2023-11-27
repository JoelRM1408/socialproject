package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DocumentoIntervencion;
import com.example.demo.repository.DocumentoIntervencionRepository;
import com.example.demo.service.Operaciones;

@Service
public class DocumentoIntervencionServiceImpl implements Operaciones<DocumentoIntervencion>{
	
	@Autowired
	private DocumentoIntervencionRepository docintRepository;

	@Override
	public DocumentoIntervencion create(DocumentoIntervencion t) {
		// TODO Auto-generated method stub
		return docintRepository.save(t);
	}

	@Override
	public DocumentoIntervencion update(DocumentoIntervencion t) {
		// TODO Auto-generated method stub
		return docintRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		docintRepository.deleteById(id);
	}

	@Override
	public Optional<DocumentoIntervencion> read(Long id) {
		// TODO Auto-generated method stub
		return docintRepository.findById(id);
	}

	@Override
	public List<DocumentoIntervencion> readAll() {
		// TODO Auto-generated method stub
		return docintRepository.findAll();
	}
	
	public List<DocumentoIntervencion> searchDocIntbyTipo(Integer tipodi){
		return docintRepository.findDocIntByTipoQueryNative(tipodi);
	}

}