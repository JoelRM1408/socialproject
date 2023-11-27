package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CursoSemestre;
import com.example.demo.serviceImpl.CursoSemServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_CURSOSEMESTRE;

@RestController
@RequestMapping(API_CURSOSEMESTRE)
@CrossOrigin(origins = "http://localhost:4100/")
public class CursoSemController {
	@Autowired
	private CursoSemServiceImpl cursosemServiceImpl;
	
	
	@GetMapping("/listarcursem")
	public ResponseEntity<List<CursoSemestre>> listar() {
		try {
		      List<CursoSemestre> cursem = cursosemServiceImpl.readAll();
		      if (cursem.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(cursem, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarcursem")
    public ResponseEntity<CursoSemestre> crear(@Valid @RequestBody CursoSemestre cursosem){
        try {
        	CursoSemestre _cursosem = cursosemServiceImpl.create(cursosem);
            return new ResponseEntity<CursoSemestre>(_cursosem, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarcursem/{id}")
	public ResponseEntity<CursoSemestre> getCursoSemById(@PathVariable("id") Long id){
		Optional<CursoSemestre> carData = cursosemServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<CursoSemestre>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarcursem/{id}")
	public ResponseEntity<CursoSemestre> delete(@PathVariable("id") Long id){
		try {
			cursosemServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarcursem/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody CursoSemestre cursosemestre){
		Optional<CursoSemestre> carData = cursosemServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	CursoSemestre dbcursem = carData.get();
	        dbcursem.setGrupo(cursosemestre.getGrupo());
	        dbcursem.setCurso(cursosemestre.getCurso());
	        dbcursem.setSemestre(cursosemestre.getSemestre());
	        dbcursem.setDocente(cursosemestre.getDocente());
	        dbcursem.setCiclo(cursosemestre.getCiclo());
	        //dbcursem.setCursoart(cursosemestre.getCursoart());
	        
	        return new ResponseEntity<CursoSemestre>(cursosemServiceImpl.update(dbcursem), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
