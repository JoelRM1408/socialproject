package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CursoArticulado;
import com.example.demo.serviceImpl.CursoArtServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_CURSOSARTICULADOS;

@RestController
@RequestMapping(API_CURSOSARTICULADOS)
@CrossOrigin(origins = "http://localhost:4100/")
public class CursoArtController {
	@Autowired
	private CursoArtServiceImpl cursoartServiceImpl;
	
	
	@GetMapping("/listarcursart")
	public ResponseEntity<List<CursoArticulado>> listar() {
		try {
		      List<CursoArticulado> cursart = cursoartServiceImpl.readAll();
		      if (cursart.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(cursart, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarcursart")
    public ResponseEntity<CursoArticulado> crear(@Valid @RequestBody CursoArticulado cursart){
        try {
        	CursoArticulado _cursart = cursoartServiceImpl.create(cursart);
            return new ResponseEntity<CursoArticulado>(_cursart, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarcursart/{id}")
	public ResponseEntity<CursoArticulado> getCursoArtById(@PathVariable("id") Long id){
		Optional<CursoArticulado> carData = cursoartServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<CursoArticulado>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarcursart/{id}")
	public ResponseEntity<CursoArticulado> delete(@PathVariable("id") Long id){
		try {
			cursoartServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarcursart/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody CursoArticulado cursoarticulado){
		Optional<CursoArticulado> carData = cursoartServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	CursoArticulado dbcursoart = carData.get();
	        dbcursoart.setCursosem(cursoarticulado.getCursosem());
	        dbcursoart.setProyecto(cursoarticulado.getProyecto());
	        //dbcursoart.setRegistros(cursoarticulado.getRegistros());
	        
	        return new ResponseEntity<CursoArticulado>(cursoartServiceImpl.update(dbcursoart), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}