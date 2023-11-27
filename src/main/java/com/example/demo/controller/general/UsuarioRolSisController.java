package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UsuarioRolSis;
import com.example.demo.serviceImpl.UsuarioRolSisServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_USUARIOSROLESIS;

@RestController
@RequestMapping(API_USUARIOSROLESIS)
@CrossOrigin(origins = "http://localhost:4100/")

public class UsuarioRolSisController {
	@Autowired
	private UsuarioRolSisServiceImpl usuarioServiceImpl;
	
	
	@GetMapping("/listarusrolsis")
	public ResponseEntity<List<UsuarioRolSis>> listar() {
		try {
		      List<UsuarioRolSis> usrol = usuarioServiceImpl.readAll();
		      if (usrol.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(usrol, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarusrolsis")
    public ResponseEntity<UsuarioRolSis> crear(@Valid @RequestBody UsuarioRolSis usrol){
        try {
        	UsuarioRolSis _usrol = usuarioServiceImpl.create(usrol);
            return new ResponseEntity<UsuarioRolSis>(_usrol, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarusrolsis/{id}")
	public ResponseEntity<UsuarioRolSis> getUsuarioRolSisById(@PathVariable("id") Long id){
		Optional<UsuarioRolSis> carData = usuarioServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<UsuarioRolSis>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarusrolsis/{id}")
	public ResponseEntity<UsuarioRolSis> delete(@PathVariable("id") Long id){
		try {
			usuarioServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}	
	@PutMapping("editarusrolsis/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody UsuarioRolSis usrolsis){
		Optional<UsuarioRolSis> carData = usuarioServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	UsuarioRolSis dbusrol = carData.get();
	    	dbusrol.setRolsis(usrolsis.getRolsis());
	    	dbusrol.setUsuario(usrolsis.getUsuario());
	        
	        return new ResponseEntity<UsuarioRolSis>(usuarioServiceImpl.update(dbusrol), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
