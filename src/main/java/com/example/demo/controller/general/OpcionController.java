package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Opcion;
import com.example.demo.serviceImpl.OpcionServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_OPCIONES;

@RestController
@RequestMapping(API_OPCIONES)
@CrossOrigin(origins = "http://localhost:4100/")

public class OpcionController {
	@Autowired
	private OpcionServiceImpl opcionServiceImpl;
	
	
	@GetMapping("/listaropcion")
	public ResponseEntity<List<Opcion>> listar() {
		try {
		      List<Opcion> opc = opcionServiceImpl.readAll();
		      if (opc.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(opc, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertaropcion")
    public ResponseEntity<Opcion> crear(@Valid @RequestBody Opcion opc){
        try {
        	Opcion _opc = opcionServiceImpl.create(opc);
            return new ResponseEntity<Opcion>(_opc, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscaropcion/{id}")
	public ResponseEntity<Opcion> getOpcionById(@PathVariable("id") Long id){
		Optional<Opcion> carData = opcionServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Opcion>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminaropcion/{id}")
	public ResponseEntity<Opcion> delete(@PathVariable("id") Long id){
		try {
			opcionServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editaropcion/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Opcion opcion){
		Optional<Opcion> carData = opcionServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Opcion dbopc = carData.get();
	        dbopc.setNombre(opcion.getNombre());
	        dbopc.setUrlruta(opcion.getUrlruta());
	        dbopc.setUrlimg(opcion.getUrlimg());
	        dbopc.setOpc(opcion.getOpc());
	        dbopc.setOpciones(opcion.getOpciones());
	        dbopc.setUsuariosop(opcion.getUsuariosop());
	        dbopc.setRolesisop(opcion.getRolesisop());

	        return new ResponseEntity<Opcion>(opcionServiceImpl.update(dbopc), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}