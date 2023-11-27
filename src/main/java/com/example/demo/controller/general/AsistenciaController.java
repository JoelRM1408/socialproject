package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Asistencia;
import com.example.demo.serviceImpl.AsistenciaServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_ASISTENCIA;

@RestController
@RequestMapping(API_ASISTENCIA)
@CrossOrigin(origins = "http://localhost:4100/")
public class AsistenciaController {
	@Autowired
	private AsistenciaServiceImpl asistenciaServiceImpl;
	
	
	@GetMapping("/listarasistencia")
	public ResponseEntity<List<Asistencia>> listar() {
		try {
		      List<Asistencia> asis = asistenciaServiceImpl.readAll();
		      if (asis.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(asis, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarasistencia")
    public ResponseEntity<Asistencia> crear(@Valid @RequestBody Asistencia asis){
        try {
        	Asistencia _asis = asistenciaServiceImpl.create(asis);
            return new ResponseEntity<Asistencia>(_asis, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarasistencia/{id}")
	public ResponseEntity<Asistencia> getAsistenciaById(@PathVariable("id") Long id){
		Optional<Asistencia> carData = asistenciaServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Asistencia>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarasistencia/{id}")
	public ResponseEntity<Asistencia> delete(@PathVariable("id") Long id){
		try {
			asistenciaServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarasistencia/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Asistencia asistencia){
		Optional<Asistencia> carData = asistenciaServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Asistencia dbasis = carData.get();
	        dbasis.setEstado(asistencia.getEstado());
	        dbasis.setActividad(asistencia.getActividad());
	        dbasis.setEstequipo(asistencia.getEstequipo());
	        dbasis.setInforme(asistencia.getInforme());
	        
	        return new ResponseEntity<Asistencia>(asistenciaServiceImpl.update(dbasis), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}