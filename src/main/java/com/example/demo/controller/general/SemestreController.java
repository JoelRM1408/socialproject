package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Semestre;
import com.example.demo.serviceImpl.SemestreServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_SEMESTRE;

@RestController
@RequestMapping(API_SEMESTRE)
@CrossOrigin(origins = "http://localhost:4100/")
public class SemestreController {
	@Autowired
	private SemestreServiceImpl semestreServiceImpl;
	
	
	@GetMapping("/listarsemestre")
	public ResponseEntity<List<Semestre>> listar() {
		try {
		      List<Semestre> sem = semestreServiceImpl.readAll();
		      if (sem.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(sem, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarsemestre")
    public ResponseEntity<Semestre> crear(@Valid @RequestBody Semestre sem){
        try {
        	Semestre _sem = semestreServiceImpl.create(sem);
            return new ResponseEntity<Semestre>(_sem, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarsemestre/{id}")
	public ResponseEntity<Semestre> getSemestreById(@PathVariable("id") Long id){
		Optional<Semestre> carData = semestreServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Semestre>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarsemestre/{id}")
	public ResponseEntity<Semestre> delete(@PathVariable("id") Long id){
		try {
			semestreServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarsemestre/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Semestre semestre){
		Optional<Semestre> carData = semestreServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Semestre dbsem = carData.get();
	    	dbsem.setNombre(semestre.getNombre());
	    	//dbsem.setCursosemestre(semestre.getCursosemestre());
	    	//dbsem.setEquipos(semestre.getEquipos());
	    	//dbsem.setProyectos(semestre.getProyectos());
	        
	        return new ResponseEntity<Semestre>(semestreServiceImpl.update(dbsem), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
