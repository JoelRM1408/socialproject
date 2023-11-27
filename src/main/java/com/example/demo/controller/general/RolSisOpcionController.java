package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.RolSisOpcion;
import com.example.demo.serviceImpl.RolSisOpcionServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_ROLESISOP;

@RestController
@RequestMapping(API_ROLESISOP)
@CrossOrigin(origins = "http://localhost:4100/")

public class RolSisOpcionController {
	@Autowired
	private RolSisOpcionServiceImpl rolsisopServiceImpl;
	
	
	@GetMapping("/listarolsisopc")
	public ResponseEntity<List<RolSisOpcion>> listar() {
		try {
		      List<RolSisOpcion> rso = rolsisopServiceImpl.readAll();
		      if (rso.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(rso, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarolsisopc")
    public ResponseEntity<RolSisOpcion> crear(@Valid @RequestBody RolSisOpcion rso){
        try {
        	RolSisOpcion _rso = rolsisopServiceImpl.create(rso);
            return new ResponseEntity<RolSisOpcion>(_rso, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarolsisopc/{id}")
	public ResponseEntity<RolSisOpcion> getRolSisOpcById(@PathVariable("id") Long id){
		Optional<RolSisOpcion> carData = rolsisopServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<RolSisOpcion>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarolsisopc/{id}")
	public ResponseEntity<RolSisOpcion> delete(@PathVariable("id") Long id){
		try {
			rolsisopServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarolsisopc/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody RolSisOpcion rolsisopc){
		Optional<RolSisOpcion> carData = rolsisopServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	RolSisOpcion dbrso = carData.get();
	    	dbrso.setOpcion(rolsisopc.getOpcion());
	    	dbrso.setRolsis(rolsisopc.getRolsis());
	    	
	        return new ResponseEntity<RolSisOpcion>(rolsisopServiceImpl.update(dbrso), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}