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
@Table(name="OPCIONES")
public class Opcion {
	@Id
	@Column(name = "ID_OPCION")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqOpcion")
    @SequenceGenerator(name = "seqOpcion", allocationSize = 1, sequenceName = "SEQ_Opcion")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "NOMBRE")
	@NotNull     
    private String nombre;
	
	@Column(name = "URL_IMG")
	@NotNull     
    private String urlimg;
	
	@Column(name = "URL_RUTA")
	@NotNull     
    private String urlruta;
	
	@ManyToOne
    @JoinColumn(name="OPCION_ID", nullable = false)
    private Opcion opc;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "opc")
	@JsonIgnore
	private Set<Opcion> opciones;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "opcion")
	@JsonIgnore
	private Set<UsuarioOpcion> usuariosop;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "opcion")
	@JsonIgnore
	private Set<RolSisOpcion> rolesisop;
	
	

	
}