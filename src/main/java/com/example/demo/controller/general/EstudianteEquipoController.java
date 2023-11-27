package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.EstudianteEquipo;
import com.example.demo.serviceImpl.EstudianteEquipoServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_ESTUDIANTESEQUIPO;

@RestController
@RequestMapping(API_ESTUDIANTESEQUIPO)
@CrossOrigin(origins = "http://localhost:4100/")

public class EstudianteEquipoController {
	@Autowired
	private EstudianteEquipoServiceImpl estdequiServiceImpl;
	
	
	@GetMapping("/listarestdequipo")
	public ResponseEntity<List<EstudianteEquipo>> listar() {
		try {
		      List<EstudianteEquipo> estdequi = estdequiServiceImpl.readAll();
		      if (estdequi.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(estdequi, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarestdequipo")
    public ResponseEntity<EstudianteEquipo> crear(@Valid @RequestBody EstudianteEquipo estdequi){
        try {
        	EstudianteEquipo _estdequi = estdequiServiceImpl.create(estdequi);
            return new ResponseEntity<EstudianteEquipo>(_estdequi, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarestdequipo/{id}")
	public ResponseEntity<EstudianteEquipo> getEstdEquipoById(@PathVariable("id") Long id){
		Optional<EstudianteEquipo> carData = estdequiServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<EstudianteEquipo>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarestdequipo/{id}")
	public ResponseEntity<EstudianteEquipo> delete(@PathVariable("id") Long id){
		try {
			estdequiServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarestdequipo/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody EstudianteEquipo estdequipo){
		Optional<EstudianteEquipo> carData = estdequiServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	EstudianteEquipo dbestdequi = carData.get();
	        dbestdequi.setUrlevidenciaest(estdequipo.getUrlevidenciaest());
	        dbestdequi.setEstudiante(estdequipo.getEstudiante());
	        dbestdequi.setEquipo(estdequipo.getEquipo());
	        dbestdequi.setRolpy(estdequipo.getRolpy());
	        //dbestdequi.setAsistencias(estdequipo.getAsistencias());
	        //dbestdequi.setInformes(estdequipo.getInformes());
	        
	        return new ResponseEntity<EstudianteEquipo>(estdequiServiceImpl.update(dbestdequi), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
