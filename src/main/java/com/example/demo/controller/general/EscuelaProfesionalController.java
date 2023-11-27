package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.EscuelaProfesional;
import com.example.demo.serviceImpl.EscuelaProfesionalServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_ESCUELASPROFESIONALES;

@RestController
@RequestMapping(API_ESCUELASPROFESIONALES)
@CrossOrigin(origins = "http://localhost:4100/")
public class EscuelaProfesionalController {
	@Autowired
	private EscuelaProfesionalServiceImpl escuelaprofesionalServiceImpl;
	
	
	@GetMapping("/listarep")
	public ResponseEntity<List<EscuelaProfesional>> listar() {
		try {
		      List<EscuelaProfesional> ep = escuelaprofesionalServiceImpl.readAll();
		      if (ep.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(ep, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	@GetMapping("/buscarepfacu/{facultadid}")
	public ResponseEntity<List<EscuelaProfesional>> getEpByFacultad(@PathVariable("facultadid") Integer facultadid){
		List <EscuelaProfesional> ep = escuelaprofesionalServiceImpl.searchEpbyFacultad(facultadid);
	    if (ep!=null ) {
	      return new ResponseEntity<List<EscuelaProfesional>>(ep, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/insertarep")
    public ResponseEntity<EscuelaProfesional> crear(@Valid @RequestBody EscuelaProfesional ep){
        try {
        	EscuelaProfesional _ep = escuelaprofesionalServiceImpl.create(ep);
            return new ResponseEntity<EscuelaProfesional>(_ep, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarep/{id}")
	public ResponseEntity<EscuelaProfesional> getEPById(@PathVariable("id") Long id){
		Optional<EscuelaProfesional> carData = escuelaprofesionalServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<EscuelaProfesional>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarep/{id}")
	public ResponseEntity<EscuelaProfesional> delete(@PathVariable("id") Long id){
		try {
			escuelaprofesionalServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarep/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody EscuelaProfesional escuelap){
		Optional<EscuelaProfesional> carData = escuelaprofesionalServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	EscuelaProfesional dbep = carData.get();
	        dbep.setNombre(escuelap.getNombre());
	        dbep.setFacultad(escuelap.getFacultad());
	        //dbep.setEstudiantes(escuelap.getEstudiantes());
	        //dbep.setCursos(escuelap.getCursos());
	        //dbep.setCoordinadores(escuelap.getCoordinadores());
	        //dbep.setProyectos(escuelap.getProyectos());
	        	    
	        return new ResponseEntity<EscuelaProfesional>(escuelaprofesionalServiceImpl.update(dbep), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
