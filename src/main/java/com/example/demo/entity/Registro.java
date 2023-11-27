package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;	
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="REGISTROS")
public class Registro {
	@Id
	@Column(name = "ID_REGISTRO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRegistro")
    @SequenceGenerator(name = "seqRegistro", allocationSize = 1, sequenceName = "SEQ_REGISTRO")
    @Builder.Default
    private Long id=0L;	
	
	@Column(name = "NOTA")
	@NotNull     
    private Integer nota;

	@ManyToOne
    @JoinColumn(name="CURSO_ART_ID", nullable = false)
    private CursoArticulado cursoart;
	
	@ManyToOne
    @JoinColumn(name="ESTUDIANTE_ID", nullable = false)
    private Estudiante estudiante;
	
}