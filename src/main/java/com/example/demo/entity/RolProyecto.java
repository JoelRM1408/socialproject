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
@Table(name="ROLES_PROYECTO")
public class RolProyecto {
	@Id
	@Column(name = "ID_ROL_PROYECTO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRolPy")
    @SequenceGenerator(name = "seqRolPy", allocationSize = 1, sequenceName = "SEQ_ROLPY")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "HORAS")
	@NotNull     
    private Integer horas;
	
	@Column(name = "DESCRIPCION")
	@NotNull     
    private String descripcion;
	
	@ManyToOne
    @JoinColumn(name="ROL_ID", nullable = false)
    private Rol rol;
	
	@ManyToOne
    @JoinColumn(name="PROYECTO_ID", nullable = false)
    private Proyecto proyecto;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rolpy")
	@JsonIgnore
	private Set<EstudianteEquipo> estudiantequipo;

}