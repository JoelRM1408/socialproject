package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Usuario;
import com.example.demo.serviceImpl.UsuarioServiceImpl;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.example.demo.commons.GlobalConstans.API_USUARIO;

@RestController
@RequestMapping(API_USUARIO)
@CrossOrigin(origins = "http://localhost:4100/")

public class UsuarioController {
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	
	@GetMapping("/listarusuario")
	public ResponseEntity<List<Usuario>> listar() {
		try {
		      List<Usuario> usr = usuarioServiceImpl.readAll();
		      if (usr.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(usr, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarusuario")
    public ResponseEntity<Usuario> crear(@Valid @RequestBody Usuario usr){
        try {
        	Usuario _usr = usuarioServiceImpl.create(usr);
            return new ResponseEntity<Usuario>(_usr, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarusuario/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id){
		Optional<Usuario> carData = usuarioServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Usuario>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarusuario/{id}")
	public ResponseEntity<Usuario> delete(@PathVariable("id") Long id){
		try {
			usuarioServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarusuario/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Usuario usuario){
		Optional<Usuario> carData = usuarioServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Usuario dbusr = carData.get();
	    	dbusr.setNombreus(usuario.getNombreus());
	    	dbusr.setContrasena(usuario.getContrasena());
	    	dbusr.setPersona(usuario.getPersona());
	    	//dbusr.setUsuariosop(usuario.getUsuariosop());
	    	//dbusr.setUsuariosrs(usuario.getUsuariosrs());
	        
	        return new ResponseEntity<Usuario>(usuarioServiceImpl.update(dbusr), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}