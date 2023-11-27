package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.RolProyecto;
import com.example.demo.serviceImpl.RolProyectoServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_ROLESPROYECTO;

@RestController
@RequestMapping(API_ROLESPROYECTO)
@CrossOrigin(origins = "http://localhost:4100/")

public class RolProyectoController {
	@Autowired
	private RolProyectoServiceImpl rolproyServiceImpl;
	
	
	@GetMapping("/listarolproy")
	public ResponseEntity<List<RolProyecto>> listar() {
		try {
		      List<RolProyecto> rolpy = rolproyServiceImpl.readAll();
		      if (rolpy.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(rolpy, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarolproy")
    public ResponseEntity<RolProyecto> crear(@Valid @RequestBody RolProyecto rolpy){
        try {
        	RolProyecto _rolpy = rolproyServiceImpl.create(rolpy);
            return new ResponseEntity<RolProyecto>(_rolpy, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarolproy/{id}")
	public ResponseEntity<RolProyecto> getRolProyById(@PathVariable("id") Long id){
		Optional<RolProyecto> carData = rolproyServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<RolProyecto>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarrol/{id}")
	public ResponseEntity<RolProyecto> delete(@PathVariable("id") Long id){
		try {
			rolproyServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarolproy/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody RolProyecto rolproy){
		Optional<RolProyecto> carData = rolproyServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	RolProyecto dbrolpy = carData.get();
	    	dbrolpy.setDescripcion(rolproy.getDescripcion());
	    	dbrolpy.setHoras(rolproy.getHoras());
	    	dbrolpy.setProyecto(rolproy.getProyecto());
	    	dbrolpy.setRol(rolproy.getRol());
	    	//dbrolpy.setEstudiantequipo(rolproy.getEstudiantequipo());
	        
	        return new ResponseEntity<RolProyecto>(rolproyServiceImpl.update(dbrolpy), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
