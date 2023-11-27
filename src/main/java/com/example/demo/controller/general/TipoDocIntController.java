	package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TipoDocInt;
import com.example.demo.serviceImpl.TipoDocIntServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_TIPOSDOCINT;

@RestController
@RequestMapping(API_TIPOSDOCINT)
@CrossOrigin(origins = "http://localhost:4100/")

public class TipoDocIntController {
	@Autowired
	private TipoDocIntServiceImpl tipodocintServiceImpl;
	
	
	@GetMapping("/listartipodocint")
	public ResponseEntity<List<TipoDocInt>> listar() {
		try {
		      List<TipoDocInt> tdi = tipodocintServiceImpl.readAll();
		      if (tdi.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(tdi, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertartipodocint")
    public ResponseEntity<TipoDocInt> crear(@Valid @RequestBody TipoDocInt tdi){
        try {
        	TipoDocInt _tdi = tipodocintServiceImpl.create(tdi);
            return new ResponseEntity<TipoDocInt>(_tdi, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscartipodocint/{id}")
	public ResponseEntity<TipoDocInt> getTipoDocIntById(@PathVariable("id") Long id){
		Optional<TipoDocInt> carData = tipodocintServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<TipoDocInt>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminartipodocint/{id}")
	public ResponseEntity<TipoDocInt> delete(@PathVariable("id") Long id){
		try {
			tipodocintServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editartipodocint/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody TipoDocInt tipodocint){
		Optional<TipoDocInt> carData = tipodocintServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	TipoDocInt dbtdi = carData.get();
	    	dbtdi.setNombre(tipodocint.getNombre());
	    	//dbtdi.setDocsint(tipodocint.getDocsint());
	        
	        return new ResponseEntity<TipoDocInt>(tipodocintServiceImpl.update(dbtdi), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
