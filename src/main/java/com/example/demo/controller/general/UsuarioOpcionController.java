package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UsuarioOpcion;
import com.example.demo.serviceImpl.UsuarioOpcionServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_USUARIOSOPCION;;

@RestController
@RequestMapping(API_USUARIOSOPCION)
@CrossOrigin(origins = "http://localhost:4100/")

public class UsuarioOpcionController {
	@Autowired
	private UsuarioOpcionServiceImpl usuariopcServiceImpl;
	
	
	@GetMapping("/listarusuariopc")
	public ResponseEntity<List<UsuarioOpcion>> listar() {
		try {
		      List<UsuarioOpcion> usrop = usuariopcServiceImpl.readAll();
		      if (usrop.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(usrop, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarusuariopc")
    public ResponseEntity<UsuarioOpcion> crear(@Valid @RequestBody UsuarioOpcion usrop){
        try {
        	UsuarioOpcion _usrop = usuariopcServiceImpl.create(usrop);
            return new ResponseEntity<UsuarioOpcion>(_usrop, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarusuariopc/{id}")
	public ResponseEntity<UsuarioOpcion> getUsuarioOpcById(@PathVariable("id") Long id){
		Optional<UsuarioOpcion> carData = usuariopcServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<UsuarioOpcion>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarusuariopc/{id}")
	public ResponseEntity<UsuarioOpcion> delete(@PathVariable("id") Long id){
		try {
			usuariopcServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarusuariopc/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody UsuarioOpcion usuariopc){
		Optional<UsuarioOpcion> carData = usuariopcServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	UsuarioOpcion dbusrop = carData.get();
	    	dbusrop.setUsuario(usuariopc.getUsuario());
	    	dbusrop.setOpcion(usuariopc.getOpcion());
	    	
	        return new ResponseEntity<UsuarioOpcion>(usuariopcServiceImpl.update(dbusrop), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
