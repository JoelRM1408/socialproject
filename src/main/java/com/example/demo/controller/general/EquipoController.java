package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Equipo;
import com.example.demo.serviceImpl.EquipoServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_EQUIPOS;

@RestController
@RequestMapping(API_EQUIPOS)
@CrossOrigin(origins = "http://localhost:4100/")

public class EquipoController {
	@Autowired
	private EquipoServiceImpl equipoServiceImpl;
	
	
	@GetMapping("/listarequipo")
	public ResponseEntity<List<Equipo>> listar() {
		try {
		      List<Equipo> equi = equipoServiceImpl.readAll();
		      if (equi.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(equi, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarequipo")
    public ResponseEntity<Equipo> crear(@Valid @RequestBody Equipo equi){
        try {
        	Equipo _equi = equipoServiceImpl.create(equi);
            return new ResponseEntity<Equipo>(_equi, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarequipo/{id}")
	public ResponseEntity<Equipo> getEquipoById(@PathVariable("id") Long id){
		Optional<Equipo> carData = equipoServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Equipo>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarequipo/{id}")
	public ResponseEntity<Equipo> delete(@PathVariable("id") Long id){
		try {
			equipoServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarequipo/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Equipo equipo){
		Optional<Equipo> carData = equipoServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Equipo dbequi = carData.get();
	        dbequi.setNombre(equipo.getNombre());
	        dbequi.setSemestre(equipo.getSemestre());
	        dbequi.setProyecto(equipo.getProyecto());
	        //dbequi.setEstudiantequipo(equipo.getEstudiantequipo());
	        
	        return new ResponseEntity<Equipo>(equipoServiceImpl.update(dbequi), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
