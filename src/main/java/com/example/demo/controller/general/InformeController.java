package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Informe;
import com.example.demo.serviceImpl.InformeServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_INFORMES;

@RestController
@RequestMapping(API_INFORMES)
@CrossOrigin(origins = "http://localhost:4100/")

public class InformeController {
	@Autowired
	private InformeServiceImpl informeServiceImpl;
	
	
	@GetMapping("/listarinforme")
	public ResponseEntity<List<Informe>> listar() {
		try {
		      List<Informe> inf = informeServiceImpl.readAll();
		      if (inf.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(inf, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarinforme")
    public ResponseEntity<Informe> crear(@Valid @RequestBody Informe inf){
        try {
        	Informe _inf = informeServiceImpl.create(inf);
            return new ResponseEntity<Informe>(_inf, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarinforme/{id}")
	public ResponseEntity<Informe> getInformeById(@PathVariable("id") Long id){
		Optional<Informe> carData = informeServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Informe>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarinforme/{id}")
	public ResponseEntity<Informe> delete(@PathVariable("id") Long id){
		try {
			informeServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarinforme/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Informe informe){
		Optional<Informe> carData = informeServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Informe dbinf = carData.get();
	        dbinf.setCbeneficiarios(informe.getCbeneficiarios());
	        dbinf.setCdocentes(informe.getCdocentes());
	        dbinf.setCparticipantes(informe.getCparticipantes());
	        dbinf.setDesempeno(informe.getDesempeno());
	        dbinf.setUrlevidenciagrp(informe.getUrlevidenciagrp());
	        dbinf.setActividad(informe.getActividad());
	        dbinf.setEstequipo(informe.getEstequipo());
	        //dbinf.setAsistencias(informe.getAsistencias());

	        return new ResponseEntity<Informe>(informeServiceImpl.update(dbinf), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}

