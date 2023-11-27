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
@Table(name="DOCUMENTOS_INTERVENCION")
public class DocumentoIntervencion {
	@Id
	@Column(name = "ID_DOCINT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDocInt")
    @SequenceGenerator(name = "seqDocInt", allocationSize = 1, sequenceName = "SEQ_DOCINT")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "NOMBRE")
	@NotNull     
    private String nombre;
	
	@Column(name = "DISTRITO")
	@NotNull     
    private String distrito;
	
	@Column(name = "REPRESENTANTE")
	@NotNull     
    private String representante;
	
	@Column(name = "INSTITUCION")
	@NotNull     
    private String institucion;
	
	@Column(name = "CATEGORIA")  
    private String categoria;
	
	@Column(name = "FECHA_INICIO")
	@NotNull     
    private String fechaini;
	
	@Column(name = "FECHA_FIN")
    private String fechafin;
	
	@Column(name = "ESTADO")
	@NotNull     
    private String estado;
	
	@Column(name = "URL_DOC")
    private String urldoc;
	
	@ManyToOne
    @JoinColumn(name="TIPO_DOCINT_ID", nullable = false)
    private TipoDocInt tipodocint;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "docint")
	@JsonIgnore
	private Set<Proyecto> proyectos;

}
