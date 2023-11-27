package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Estudiante;
import com.example.demo.serviceImpl.EstudianteServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_ESTUDIANTES;

@RestController
@RequestMapping(API_ESTUDIANTES)
@CrossOrigin(origins = "http://localhost:4100/")
public class EstudianteController {
	@Autowired
	private EstudianteServiceImpl estudianteServiceImpl;
	
	
	@GetMapping("/listarestudiante")
	public ResponseEntity<List<Estudiante>> listar() {
		try {
		      List<Estudiante> est = estudianteServiceImpl.readAll();
		      if (est.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(est, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarestudiante")
    public ResponseEntity<Estudiante> crear(@Valid @RequestBody Estudiante est){
        try {
        	Estudiante _est = estudianteServiceImpl.create(est);
            return new ResponseEntity<Estudiante>(_est, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarestudiante/{id}")
	public ResponseEntity<Estudiante> getEstudianteById(@PathVariable("id") Long id){
		Optional<Estudiante> carData = estudianteServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Estudiante>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarestudiante/{id}")
	public ResponseEntity<Estudiante> delete(@PathVariable("id") Long id){
		try {
			estudianteServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	
	@PutMapping("editarestudiante/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Estudiante estudiante){
		Optional<Estudiante> carData = estudianteServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Estudiante dbest = carData.get();
	        dbest.setCodigou(estudiante.getCodigou());
	        dbest.setPersona(estudiante.getPersona());
	        dbest.setEp(estudiante.getEp());
	        //dbest.setEstudiantequipo(estudiante.getEstudiantequipo());
	        //dbest.setRegistros(estudiante.getRegistros());
	        

	        return new ResponseEntity<Estudiante>(estudianteServiceImpl.update(dbest), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}

