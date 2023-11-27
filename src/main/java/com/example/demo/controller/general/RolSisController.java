package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.RolSis;
import com.example.demo.serviceImpl.RolSisServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_ROLESIS;

@RestController
@RequestMapping(API_ROLESIS)
@CrossOrigin(origins = "http://localhost:4100/")

public class RolSisController {
	@Autowired
	private RolSisServiceImpl rolsisServiceImpl;
	
	
	@GetMapping("/listarolsis")
	public ResponseEntity<List<RolSis>> listar() {
		try {
		      List<RolSis> rs = rolsisServiceImpl.readAll();
		      if (rs.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(rs, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarolsis")
    public ResponseEntity<RolSis> crear(@Valid @RequestBody RolSis rs){
        try {
        	RolSis _rs = rolsisServiceImpl.create(rs);
            return new ResponseEntity<RolSis>(_rs, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarolsis/{id}")
	public ResponseEntity<RolSis> getRolSisById(@PathVariable("id") Long id){
		Optional<RolSis> carData = rolsisServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<RolSis>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarolsis/{id}")
	public ResponseEntity<RolSis> delete(@PathVariable("id") Long id){
		try {
			rolsisServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarolsis/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody RolSis rolsis){
		Optional<RolSis> carData = rolsisServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	RolSis dbrs = carData.get();
	    	dbrs.setNombre(rolsis.getNombre());
	    	//dbrs.setRolesisop(rolsis.getRolesisop());
	    	//dbrs.setUsuariosrs(rolsis.getUsuariosrs());
	        
	        return new ResponseEntity<RolSis>(rolsisServiceImpl.update(dbrs), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}