package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Docente;
import com.example.demo.serviceImpl.DocenteServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_DOCENTES;

@RestController
@RequestMapping(API_DOCENTES)
@CrossOrigin(origins = "http://localhost:4100/")
public class DocenteController {
	@Autowired
	private DocenteServiceImpl docenteserviceImpl;
	
	
	@GetMapping("/listardocente")
	public ResponseEntity<List<Docente>> listar() {
		try {
		      List<Docente> dct = docenteserviceImpl.readAll();
		      if (dct.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(dct, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertardocente")
    public ResponseEntity<Docente> crear(@Valid @RequestBody Docente dct){
        try {
        	Docente _dct = docenteserviceImpl.create(dct);
            return new ResponseEntity<Docente>(_dct, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscardocente/{id}")
	public ResponseEntity<Docente> getDocenteById(@PathVariable("id") Long id){
		Optional<Docente> carData = docenteserviceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Docente>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarcursoart/{id}")
	public ResponseEntity<Docente> delete(@PathVariable("id") Long id){
		try {
			docenteserviceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editardocente/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Docente docente){
		Optional<Docente> carData = docenteserviceImpl.read(id);
	      if (carData.isPresent()) {
	    	Docente dbdct = carData.get();
	        dbdct.setCodigoafp(docente.getCodigoafp());
	        dbdct.setCodigoseg(docente.getCodigoseg());
	        dbdct.setPersona(docente.getPersona());
	        //dbdct.setCursosemestre(docente.getCursosemestre());
	        
	        return new ResponseEntity<Docente>(docenteserviceImpl.update(dbdct), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
