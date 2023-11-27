package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Curso;
import com.example.demo.serviceImpl.CursoServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_CURSOS;

@RestController
@RequestMapping(API_CURSOS)
@CrossOrigin(origins = "http://localhost:4100/")
public class CursoController {
	@Autowired
	private CursoServiceImpl cursoServiceImpl;
	
	
	@GetMapping("/listarcurso")
	public ResponseEntity<List<Curso>> listar() {
		try {
		      List<Curso> cur = cursoServiceImpl.readAll();
		      if (cur.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(cur, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarcurso")
    public ResponseEntity<Curso> crear(@Valid @RequestBody Curso cur){
        try {
        	Curso _cur = cursoServiceImpl.create(cur);
            return new ResponseEntity<Curso>(_cur, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarcurso/{id}")
	public ResponseEntity<Curso> getCursoById(@PathVariable("id") Long id){
		Optional<Curso> carData = cursoServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Curso>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarcurso/{id}")
	public ResponseEntity<Curso> delete(@PathVariable("id") Long id){
		try {
			cursoServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarcurso/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Curso curso){
		Optional<Curso> carData = cursoServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Curso dbcur = carData.get();
	        dbcur.setNombre(curso.getNombre());
	        dbcur.setEp(curso.getEp());
	        dbcur.setCursosemestre(curso.getCursosemestre());

	        return new ResponseEntity<Curso>(cursoServiceImpl.update(dbcur), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}