package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Ciclo;
import com.example.demo.serviceImpl.CicloServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_CICLOS;

@RestController
@RequestMapping(API_CICLOS)
@CrossOrigin(origins = "http://localhost:4100/")
public class CicloController {
	@Autowired
	private CicloServiceImpl cicloServiceImpl;
	
	
	@GetMapping("/listarciclo")
	public ResponseEntity<List<Ciclo>> listar() {
		try {
		      List<Ciclo> cicl = cicloServiceImpl.readAll();
		      if (cicl.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(cicl, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarciclo")
    public ResponseEntity<Ciclo> crear(@Valid @RequestBody Ciclo cicl){
        try {
        	Ciclo _cicl = cicloServiceImpl.create(cicl);
            return new ResponseEntity<Ciclo>(_cicl, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarciclo/{id}")
	public ResponseEntity<Ciclo> getCicloById(@PathVariable("id") Long id){
		Optional<Ciclo> carData = cicloServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Ciclo>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarciclo/{id}")
	public ResponseEntity<Ciclo> delete(@PathVariable("id") Long id){
		try {
			cicloServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarciclo/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Ciclo ciclo){
		Optional<Ciclo> carData = cicloServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Ciclo dbcicl = carData.get();
	        dbcicl.setNombre(ciclo.getNombre());
	        //dbcicl.setCursosem(ciclo.getCursosem());
	        
	        return new ResponseEntity<Ciclo>(cicloServiceImpl.update(dbcicl), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}