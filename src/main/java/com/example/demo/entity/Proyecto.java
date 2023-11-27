package com.example.demo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;	
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="PROYECTOS")
public class Proyecto {
	@Id
	@Column(name = "ID_PROYECTO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProyecto")
    @SequenceGenerator(name = "seqProyecto", allocationSize = 1, sequenceName = "SEQ_PROYECTO")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "NOMBRE")
	@NotNull     
    private String nombre;
	
	@Column(name = "POBLACION_BENEFICIARIA")
	@NotNull    
    private String pbeneficiaria;
	
	@Column(name = "ZONA_INTERVENCION")
	@NotNull    
    private String zintervencion;
	
	@Column(name = "RESPONSABLE")
	@NotNull    
    private String responsable;
	
	@Column(name = "DESCRIPCION")
	@NotNull    
    private String descripcion;
	
	@Column(name = "OBJETIVO")
    private String objetivo;
	
	@Column(name = "FECHA_INICIO")
	@NotNull     
    private String fechaini;
	
	@Column(name = "FECHA_FIN")
	@NotNull
    private String fechafin;
	
	@Column(name = "PRESUPUESTO")
	@NotNull
    private Integer presupuesto;
	
	@Column(name = "URL_DIAGNOSTICO")
    private String urldiagnostico;
	
	@Column(name = "URL_RECURSO")
    private String urlrecurso;
	
	@Column(name = "ESTADO")
	@NotNull
    private String estado;
	
	@ManyToOne
    @JoinColumn(name="DOCINT_ID", nullable = false)	
    private DocumentoIntervencion docint;
	
	@ManyToOne
    @JoinColumn(name="TIPO_PROYECTO_ID", nullable = false)	
    private TipoProyecto tipopro;
	
	@ManyToOne
    @JoinColumn(name="SEMESTRE_ID", nullable = false)
    private Semestre semestre;
	
	@ManyToOne
    @JoinColumn(name="EP_ID", nullable = false)
    private EscuelaProfesional ep;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proyecto")
	@JsonIgnore
	private Set<CursoArticulado> cursoart;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proyecto")
	@JsonIgnore
	private Set<RolProyecto> rolesproyecto;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proyecto")
	@JsonIgnore
	private Set<Actividad> actividades;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proyecto")
	@JsonIgnore
	private Set<Equipo> equipos;
}