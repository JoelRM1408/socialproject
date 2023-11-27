package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="PERSONAS")
public class Persona {
	@Id
	@Column(name = "ID_PERSONA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPersona")
    @SequenceGenerator(name = "seqPersona", allocationSize = 1, sequenceName = "SEQ_PERSONA")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "NOMBRES")
	@NotNull     
    private String nombres;
	
	@Column(name = "APELLIDOS")
	@NotNull     
    private String apellidos;
	
	@Column(name = "NUMERO")
	@NotNull     
    private Integer numero;
	
	@Column(name = "CORREO")
	@NotNull     
    private String correo;
	
	@Column(name = "DNI/CE")
	@NotNull     
    private Integer dnioce;
	
	@Column(name = "GENERO")
	@NotNull     
    private String genero;
	
	@Column(name = "DIRECCION")
	@NotNull     
    private String direccion;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
	@JsonIgnore
    private Estudiante estudiantes;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
	@JsonIgnore
    private Docente docentes;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
	@JsonIgnore
    private Coordinador coordinadores;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
	@JsonIgnore
    private Usuario usuarios;
}