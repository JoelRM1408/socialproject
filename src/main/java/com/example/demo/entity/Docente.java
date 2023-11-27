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
@Table(name="DOCENTES")
public class Docente {
	@Id
	@Column(name = "ID_DOCENTE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDocente")
    @SequenceGenerator(name = "seqDocente", allocationSize = 1, sequenceName = "SEQ_DOCENTE")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "CODIGO_SEGURO")
	@NotNull     
    private Integer codigoseg;
	
	@Column(name = "CODIGO_AFP")
	@NotNull     	
    private Integer codigoafp;
	
	@OneToOne
    @JoinColumn(name = "PERSONA_ID", nullable = false)
    private Persona persona;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "docente")
	@JsonIgnore
	private Set<CursoSemestre> cursosemestre;
	
}