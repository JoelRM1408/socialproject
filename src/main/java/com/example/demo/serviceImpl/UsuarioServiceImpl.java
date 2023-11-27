package com.example.demo.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.Operaciones;


@Service
public class UsuarioServiceImpl implements Operaciones<Usuario>{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	public Usuario create(Usuario t) {
		// TODO Auto-generated method stub
		return usuarioRepository.save(t);
	}

	@Override
	public Usuario update(Usuario t) {
		// TODO Auto-generated method stub
		return usuarioRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<Usuario> read(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id);
	}

	@Override
	public List<Usuario> readAll() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}
	
	//public AuthResponseDTO login(UsuarioDTO request) {
	//	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getPassword()));
    //    UserDetails user=(UserDetails) usuarioRepository.findByUsername(request.getUsuario()).orElseThrow();
    //    String token=jwtService.getToken(user);
    //    return AuthResponseDTO.builder()
    //        .token(token)
    //        .build();

    //}

}
