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
@Table(name="ACTIVIDADES")
public class Actividad {
	@Id
	@Column(name = "ID_ACTIVIDAD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqActividad")
    @SequenceGenerator(name = "seqActividad", allocationSize = 1, sequenceName = "SEQ_ACTIVIDAD")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "NOMBRE")
	@NotNull     
    private String nombre;
	
	@Column(name = "DESCRIPCION")
	@NotNull     
    private String descripcion;
	
	@Column(name = "FECHA")
	@NotNull    
    private String fecha;
	
	@Column(name = "URL_RECURSOS")
	@NotNull    
    private String urlrecur;
	
	@ManyToOne
    @JoinColumn(name="PROYECTO_ID", nullable = false)
    private Proyecto proyecto;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actividad")
	@JsonIgnore
	private Set<Informe> informes;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actividad")
	@JsonIgnore
	private Set<Asistencia> asistencias;
}