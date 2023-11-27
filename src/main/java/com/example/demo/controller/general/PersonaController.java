package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Persona;
import com.example.demo.serviceImpl.PersonaServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_PERSONAS;

@RestController
@RequestMapping(API_PERSONAS)
@CrossOrigin(origins = "http://localhost:4100/")

public class PersonaController {
	@Autowired
	private PersonaServiceImpl personaServiceImpl;
	
	
	@GetMapping("/listarpersona")
	public ResponseEntity<List<Persona>> listar() {
		try {
		      List<Persona> prs = personaServiceImpl.readAll();
		      if (prs.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(prs, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarpersona")
    public ResponseEntity<Persona> crear(@Valid @RequestBody Persona prs){
        try {
        	Persona _prs = personaServiceImpl.create(prs);
            return new ResponseEntity<Persona>(_prs, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarpersona/{id}")
	public ResponseEntity<Persona> getPersonaById(@PathVariable("id") Long id){
		Optional<Persona> carData = personaServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Persona>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarpersona/{id}")
	public ResponseEntity<Persona> delete(@PathVariable("id") Long id){
		try {
			personaServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarpersona/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Persona persona){
		Optional<Persona> carData = personaServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Persona dbper = carData.get();
	        dbper.setNombres(persona.getNombres());
	        dbper.setApellidos(persona.getApellidos());
	        dbper.setNumero(persona.getNumero());
	        dbper.setDnioce(persona.getDnioce());
	        dbper.setCorreo(persona.getCorreo());
	        dbper.setGenero(persona.getGenero());
	        dbper.setDireccion(persona.getDireccion());
	        //dbper.setCoordinadores(persona.getCoordinadores());
	        //dbper.setEstudiantes(persona.getEstudiantes());
	        //dbper.setDocentes(persona.getDocentes());
	        //dbper.setUsuarios(persona.getUsuarios());
	        

	        return new ResponseEntity<Persona>(personaServiceImpl.update(dbper), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}