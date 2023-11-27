package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Registro;
import com.example.demo.serviceImpl.RegistroServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_REGISTROS;

@RestController
@RequestMapping(API_REGISTROS)
@CrossOrigin(origins = "http://localhost:4100/")

public class RegistroController {
	@Autowired
	private RegistroServiceImpl registroServiceImpl;
	
	
	@GetMapping("/listarregistro")
	public ResponseEntity<List<Registro>> listar() {
		try {
		      List<Registro> reg = registroServiceImpl.readAll();
		      if (reg.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(reg, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarregistro")
    public ResponseEntity<Registro> crear(@Valid @RequestBody Registro reg){
        try {
        	Registro _reg = registroServiceImpl.create(reg);
            return new ResponseEntity<Registro>(_reg, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarregistro/{id}")
	public ResponseEntity<Registro> getRegistroById(@PathVariable("id") Long id){
		Optional<Registro> carData = registroServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Registro>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarregistro/{id}")
	public ResponseEntity<Registro> delete(@PathVariable("id") Long id){
		try {
			registroServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarregistro/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Registro registro){
		Optional<Registro> carData = registroServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Registro dbreg = carData.get();
	    	dbreg.setNota(registro.getNota());
	        dbreg.setEstudiante(registro.getEstudiante());
	        dbreg.setCursoart(registro.getCursoart());
	        
	        return new ResponseEntity<Registro>(registroServiceImpl.update(dbreg), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}