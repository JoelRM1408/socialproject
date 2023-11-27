package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Actividad;
import com.example.demo.serviceImpl.ActividadServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_ACTIVIDADES;

@RestController
@RequestMapping(API_ACTIVIDADES)
@CrossOrigin(origins = "http://localhost:4100/")
public class ActividadController {
	@Autowired
	private ActividadServiceImpl actividadServiceImpl;
	
	
	@GetMapping("/listaractividad")
	public ResponseEntity<List<Actividad>> listar() {
		try {
		      List<Actividad> act = actividadServiceImpl.readAll();
		      if (act.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(act, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertaractividad")
    public ResponseEntity<Actividad> crear(@Valid @RequestBody Actividad act){
        try {
        	Actividad _act = actividadServiceImpl.create(act);
            return new ResponseEntity<Actividad>(_act, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscaractividad/{id}")
	public ResponseEntity<Actividad> getActividadById(@PathVariable("id") Long id){
		Optional<Actividad> carData = actividadServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Actividad>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminaractividad/{id}")
	public ResponseEntity<Actividad> delete(@PathVariable("id") Long id){
		try {
		actividadServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editaractividad/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Actividad actividad){
		Optional<Actividad> carData = actividadServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Actividad dbact = carData.get();
	        dbact.setNombre(actividad.getNombre());
	        dbact.setDescripcion(actividad.getDescripcion());
	        dbact.setFecha(actividad.getFecha());
	        dbact.setUrlrecur(actividad.getUrlrecur());
	        dbact.setProyecto(actividad.getProyecto());
	        //dbact.setAsistencias(actividad.getAsistencias());
	        //dbact.setInformes(actividad.getInformes());
	        
	        return new ResponseEntity<Actividad>(actividadServiceImpl.update(dbact), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
