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
@Table(name="ESCUELAS_PROFESIONALES")
public class EscuelaProfesional {
	@Id
	@Column(name = "ID_EP")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEp")
    @SequenceGenerator(name = "seqEp", allocationSize = 1, sequenceName = "SEQ_EP")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "NOMBRE")
	@NotNull     
    private String nombre;
	
	@ManyToOne
    @JoinColumn(name="FACULTAD_ID", nullable = false)
    private Facultad facultad;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ep")
	@JsonIgnore
	private Set<Proyecto> proyectos;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ep")
	@JsonIgnore
	private Set<Curso> cursos;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ep")
	@JsonIgnore
	private Set<Coordinador> coordinadores;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ep")
	@JsonIgnore
	private Set<Estudiante> estudiantes;

}
