package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Coordinador;
import com.example.demo.serviceImpl.CoordinadorServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_COODINADORES;;

@RestController
@RequestMapping(API_COODINADORES)
@CrossOrigin(origins = "http://localhost:4100/")
public class CoordinadorController {
	@Autowired
	private CoordinadorServiceImpl coordinadorServiceImpl;
	
	
	@GetMapping("/listarcoord")
	public ResponseEntity<List<Coordinador>> listar() {
		try {
		      List<Coordinador> coord = coordinadorServiceImpl.readAll();
		      if (coord.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(coord, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarcoord")
    public ResponseEntity<Coordinador> crear(@Valid @RequestBody Coordinador coord){
        try {
        	Coordinador _coord = coordinadorServiceImpl.create(coord);
            return new ResponseEntity<Coordinador>(_coord, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarcoord/{id}")
	public ResponseEntity<Coordinador> getCoordinadorById(@PathVariable("id") Long id){
		Optional<Coordinador> carData = coordinadorServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Coordinador>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarcoord/{id}")
	public ResponseEntity<Coordinador> delete(@PathVariable("id") Long id){
		try {
			coordinadorServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarcoord/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Coordinador coordinador){
		Optional<Coordinador> carData = coordinadorServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Coordinador dbcoord = carData.get();
	        dbcoord.setPersona(coordinador.getPersona());
	        dbcoord.setEp(coordinador.getEp());
	        
	        return new ResponseEntity<Coordinador>(coordinadorServiceImpl.update(dbcoord), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
