package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TipoProyecto;
import com.example.demo.serviceImpl.TipoProyectoServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_TIPOSPROYECTO;

@RestController
@RequestMapping(API_TIPOSPROYECTO)
@CrossOrigin(origins = "http://localhost:4100/")

public class TipoProyectoController {
	@Autowired
	private TipoProyectoServiceImpl tipoproyServiceImpl;
	
	
	@GetMapping("/listartipoproy")
	public ResponseEntity<List<TipoProyecto>> listar() {
		try {
		      List<TipoProyecto> tpy = tipoproyServiceImpl.readAll();
		      if (tpy.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(tpy, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertartipoproy")
    public ResponseEntity<TipoProyecto> crear(@Valid @RequestBody TipoProyecto tpy){
        try {
        	TipoProyecto _tpy = tipoproyServiceImpl.create(tpy);
            return new ResponseEntity<TipoProyecto>(_tpy, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscartipoproy/{id}")
	public ResponseEntity<TipoProyecto> getTipoProyectoById(@PathVariable("id") Long id){
		Optional<TipoProyecto> carData = tipoproyServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<TipoProyecto>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminartipoproy/{id}")
	public ResponseEntity<TipoProyecto> delete(@PathVariable("id") Long id){
		try {
			tipoproyServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editartipoproy/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody TipoProyecto tipoproy){
		Optional<TipoProyecto> carData = tipoproyServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	TipoProyecto dbtpy = carData.get();
	    	dbtpy.setNombre(tipoproy.getNombre());
	    	//dbtpy.setProyectos(tipoproy.getProyectos());
	        
	        return new ResponseEntity<TipoProyecto>(tipoproyServiceImpl.update(dbtpy), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}