package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Facultad;
import com.example.demo.serviceImpl.FacultadServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_FACULTADES;

@RestController
@RequestMapping(API_FACULTADES)
@CrossOrigin(origins = "http://localhost:4100/")

public class FacultadController {
	@Autowired
	private FacultadServiceImpl facultadServiceImpl;
	
	
	@GetMapping("/listarfacultad")
	public ResponseEntity<List<Facultad>> listar() {
		try {
		      List<Facultad> fac = facultadServiceImpl.readAll();
		      if (fac.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(fac, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarfacultad")
    public ResponseEntity<Facultad> crear(@Valid @RequestBody Facultad fac){
        try {
        	Facultad _fac = facultadServiceImpl.create(fac);
            return new ResponseEntity<Facultad>(_fac, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarfacultad/{id}")
	public ResponseEntity<Facultad> getFacultadById(@PathVariable("id") Long id){
		Optional<Facultad> carData = facultadServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Facultad>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarfacultad/{id}")
	public ResponseEntity<Facultad> delete(@PathVariable("id") Long id){
		try {
			facultadServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarfacultad/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Facultad facultad){
		Optional<Facultad> carData = facultadServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Facultad dbfac = carData.get();
	        dbfac.setNombre(facultad.getNombre());
	        //dbfac.setEsp(facultad.getEsp());
	        
	        return new ResponseEntity<Facultad>(facultadServiceImpl.update(dbfac), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}