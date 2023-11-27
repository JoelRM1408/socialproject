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
@Table(name="CURSOS_SEMESTRE")
public class CursoSemestre {
	@Id
	@Column(name = "ID_CURSO_SEM")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCursoSem")
    @SequenceGenerator(name = "seqCursoSem", allocationSize = 1, sequenceName = "SEQ_CURSOSEM")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "GRUPO")
	@NotNull     
    private String grupo;
	
	@ManyToOne
    @JoinColumn(name="CURSO_ID", nullable = false)
    private Curso curso;
	
	@ManyToOne
    @JoinColumn(name="CICLO_ID", nullable = false)
    private Ciclo ciclo;
	
	@ManyToOne
    @JoinColumn(name="SEMESTRE_ID", nullable = false)
    private Semestre semestre;
	
	@ManyToOne
    @JoinColumn(name="DOCENTE_ID", nullable = false)
    private Docente docente;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cursosem")
	@JsonIgnore
	private Set<CursoArticulado> cursoart;
	
}