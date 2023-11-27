package com.example.demo.controller.general;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.DocumentoIntervencion;
import com.example.demo.serviceImpl.DocumentoIntervencionServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_DOCUMENTOSINT;

@RestController
@RequestMapping(API_DOCUMENTOSINT)
@CrossOrigin(origins = "http://localhost:4100/")
public class DocumentoIntervencionController {
	@Autowired
	private DocumentoIntervencionServiceImpl docintServiceImpl;
	
	
	@GetMapping("/listardocint")
	public ResponseEntity<List<DocumentoIntervencion>> listar() {
		try {
		      List<DocumentoIntervencion> docint = docintServiceImpl.readAll();
		      if (docint.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(docint, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	@GetMapping("/buscardocinttipo/{tipodi}")
	public ResponseEntity<List<DocumentoIntervencion>> getDocIntByTipo(@PathVariable("tipodi") Integer tipodi){
		List <DocumentoIntervencion> docint = docintServiceImpl.searchDocIntbyTipo(tipodi);
	    if (docint!=null ) {
	      return new ResponseEntity<List<DocumentoIntervencion>>(docint, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/insertardocint")
    public ResponseEntity<DocumentoIntervencion> crear(@Valid @RequestBody DocumentoIntervencion docint){
        try {
        	DocumentoIntervencion _docint = docintServiceImpl.create(docint);
            return new ResponseEntity<DocumentoIntervencion>(_docint, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscardocint/{id}")
	public ResponseEntity<DocumentoIntervencion> getDocIntById(@PathVariable("id") Long id){
		Optional<DocumentoIntervencion> carData = docintServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<DocumentoIntervencion>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminardocint/{id}")
	public ResponseEntity<DocumentoIntervencion> delete(@PathVariable("id") Long id){
		try {
			docintServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editardocint/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody DocumentoIntervencion docint){
		Optional<DocumentoIntervencion> carData = docintServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	DocumentoIntervencion dbdocint = carData.get();
	        dbdocint.setNombre(docint.getNombre());
	        dbdocint.setDistrito(docint.getDistrito());
	        dbdocint.setRepresentante(docint.getRepresentante());
	        dbdocint.setInstitucion(dbdocint.getInstitucion());
	        dbdocint.setCategoria(docint.getCategoria());
	        dbdocint.setFechaini(docint.getFechaini());
	        dbdocint.setFechafin(docint.getFechafin());
	        dbdocint.setEstado(docint.getEstado());
	        dbdocint.setUrldoc(docint.getUrldoc());
	        dbdocint.setTipodocint(docint.getTipodocint());
	        dbdocint.setProyectos(docint.getProyectos());
	        
	        return new ResponseEntity<DocumentoIntervencion>(docintServiceImpl.update(dbdocint), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
