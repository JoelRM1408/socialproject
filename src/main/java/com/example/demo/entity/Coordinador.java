package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;	
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name="COORDINADORES")
public class Coordinador {
	@Id
	@Column(name = "ID_COORDINADOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCoordinador")
    @SequenceGenerator(name = "seqCoordinador", allocationSize = 1, sequenceName = "SEQ_COORDINADOR")
    @Builder.Default
    private Long id=0L;
		
	@OneToOne
    @JoinColumn(name = "PERSONA_ID", nullable = false)
    private Persona persona;
	
	@ManyToOne
    @JoinColumn(name="EP_ID", nullable = false)
    private EscuelaProfesional ep;
	
}