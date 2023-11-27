package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Proyecto;
import com.example.demo.serviceImpl.ProyectoServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_PROYECTOS;

@RestController
@RequestMapping(API_PROYECTOS)
@CrossOrigin(origins = "http://localhost:4100/")

public class ProyectoController {
	@Autowired
	private ProyectoServiceImpl proyectoServiceImpl;
	
	
	@GetMapping("/listarproyecto")
	public ResponseEntity<List<Proyecto>> listar() {
		try {
		      List<Proyecto> pro = proyectoServiceImpl.readAll();
		      if (pro.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(pro, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarproyecto")
    public ResponseEntity<Proyecto> crear(@Valid @RequestBody Proyecto pro){
        try {
        	Proyecto _pro = proyectoServiceImpl.create(pro);
            return new ResponseEntity<Proyecto>(_pro, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarproyecto/{id}")
	public ResponseEntity<Proyecto> getProyectoById(@PathVariable("id") Long id){
		Optional<Proyecto> carData = proyectoServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Proyecto>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarproyecto/{id}")
	public ResponseEntity<Proyecto> delete(@PathVariable("id") Long id){
		try {
			proyectoServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarproyecto/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Proyecto proyecto){
		Optional<Proyecto> carData = proyectoServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Proyecto dbpro = carData.get();
	        dbpro.setNombre(proyecto.getNombre());
	        dbpro.setPbeneficiaria(proyecto.getPbeneficiaria());
	        dbpro.setZintervencion(proyecto.getZintervencion());
	        dbpro.setResponsable(proyecto.getResponsable());
	        dbpro.setFechaini(proyecto.getFechaini());
	        dbpro.setFechafin(proyecto.getFechafin());
	        dbpro.setDescripcion(proyecto.getDescripcion());
	        dbpro.setObjetivo(proyecto.getObjetivo());
	        dbpro.setPresupuesto(proyecto.getPresupuesto());
	        dbpro.setUrldiagnostico(proyecto.getUrldiagnostico());
	        dbpro.setUrlrecurso(proyecto.getUrlrecurso());
	        dbpro.setEstado(proyecto.getEstado());
	        dbpro.setTipopro(proyecto.getTipopro());
	        dbpro.setDocint(proyecto.getDocint());
	        dbpro.setEp(proyecto.getEp());
	        dbpro.setSemestre(proyecto.getSemestre());
	        //dbpro.setCursoart(proyecto.getCursoart());
	        //dbpro.setActividades(proyecto.getActividades());
	        //dbpro.setRolesproyecto(proyecto.getRolesproyecto());
	        //dbpro.setEquipos(proyecto.getEquipos());
	        
	        return new ResponseEntity<Proyecto>(proyectoServiceImpl.update(dbpro), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}

