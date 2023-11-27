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
import jakarta.persistence.OneToOne;
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
@Table(name="ESTUDIANTES_EQUIPO")
public class EstudianteEquipo {
	@Id
	@Column(name = "ID_ESTUDIANTE_EQUIPO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEstEqui")
    @SequenceGenerator(name = "seqEstEqui", allocationSize = 1, sequenceName = "SEQ_ESTEQUI")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "URL_EVIDENCIA_EST")
	@NotNull     
    private String urlevidenciaest;
	
	@ManyToOne
    @JoinColumn(name="ROL_PROYECTO_ID", nullable = false)
    private RolProyecto rolpy;
	
	@ManyToOne
    @JoinColumn(name="ESTUDIANTE_ID", nullable = false)
    private Estudiante estudiante;
	
	@ManyToOne
    @JoinColumn(name="EQUIPO_ID", nullable = false)
    private Equipo equipo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "estequipo")
	@JsonIgnore
	private Set<Informe> informes;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "estequipo")
	@JsonIgnore
    private Asistencia asistencias;
	
}