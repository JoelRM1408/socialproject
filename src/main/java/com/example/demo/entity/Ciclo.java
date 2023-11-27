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
@Table(name="CICLOS")
public class Ciclo {
	@Id
	@Column(name = "ID_CICLO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCiclo")
    @SequenceGenerator(name = "seqCiclo", allocationSize = 1, sequenceName = "SEQ_CICLO")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "NOMBRE")
	@NotNull     
    private String nombre;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ciclo")
	@JsonIgnore
	private Set<CursoSemestre> cursosem;
	
}
