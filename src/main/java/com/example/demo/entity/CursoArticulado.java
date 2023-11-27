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
@Table(name="CURSOS_ARTICULADOS")
public class CursoArticulado {
	@Id
	@Column(name = "ID_CURSO_ART")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCursoArt")
    @SequenceGenerator(name = "seqCursoArt", allocationSize = 1, sequenceName = "SEQ_CURSOART")
    @Builder.Default
    private Long id=0L;
	
	@ManyToOne
    @JoinColumn(name="CURSO_SEM_ID", nullable = false)
    private CursoSemestre cursosem;
	
	@ManyToOne
    @JoinColumn(name="PROYECTO_ID", nullable = false)
    private Proyecto proyecto;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cursoart")
	@JsonIgnore
	private Set<Registro> registros;
	
	

}