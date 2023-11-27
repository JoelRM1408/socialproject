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
@Table(name="INFORMES")
public class Informe {
	@Id
	@Column(name = "ID_INFORME")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqInforme")
    @SequenceGenerator(name = "seqInforme", allocationSize = 1, sequenceName = "SEQ_INFORME")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "CANTIDAD_DOCENTES")
	@NotNull     
    private Integer cdocentes;
	
	@Column(name = "CANTIDAD_BENEFICIARIOS")
	@NotNull     
    private Integer cbeneficiarios;
	
	@Column(name = "CANTIDAD_PARTICIPANTES")
	@NotNull     
    private Integer cparticipantes;
	
	@Column(name = "DESEMPEÑO")
	@NotNull     
    private String desempeno;
	
	@Column(name = "URL_EVIDENCIA_GRP")
	@NotNull     
    private String urlevidenciagrp;
	
	@ManyToOne
    @JoinColumn(name="ESTUDIANTE_EQUIPO_ID", nullable = false)
    private EstudianteEquipo estequipo;
	
	@ManyToOne
    @JoinColumn(name="ACTIVIDAD_ID", nullable = false)
    private Actividad actividad;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "informe")
	@JsonIgnore
	private Set<Asistencia> asistencias;
	
}