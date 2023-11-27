package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Rol;
import com.example.demo.serviceImpl.RolServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_ROLES;

@RestController
@RequestMapping(API_ROLES)
@CrossOrigin(origins = "http://localhost:4100/")

public class RolController {
	@Autowired
	private RolServiceImpl rolServiceImpl;
	
	
	@GetMapping("/listarol")
	public ResponseEntity<List<Rol>> listar() {
		try {
		      List<Rol> rol = rolServiceImpl.readAll();
		      if (rol.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(rol, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarol")
    public ResponseEntity<Rol> crear(@Valid @RequestBody Rol rol){
        try {
        	Rol _rol = rolServiceImpl.create(rol);
            return new ResponseEntity<Rol>(_rol, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarol/{id}")
	public ResponseEntity<Rol> getRolById(@PathVariable("id") Long id){
		Optional<Rol> carData = rolServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Rol>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarol/{id}")
	public ResponseEntity<Rol> delete(@PathVariable("id") Long id){
		try {
			rolServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarol/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Rol rol){
		Optional<Rol> carData = rolServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Rol dbrol = carData.get();
	    	dbrol.setNombre(rol.getNombre());
	    	//dbrol.setRolpro(rol.getRolpro());
	        
	        return new ResponseEntity<Rol>(rolServiceImpl.update(dbrol), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}