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
@Table(name="ROLES_SIS_OPCIONES")
public class RolSisOpcion {
	@Id
	@Column(name = "ID_ROLSIS_OPCION")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRolSisOp")
    @SequenceGenerator(name = "seqRolSisOp", allocationSize = 1, sequenceName = "SEQ_ROLSISOP")
    @Builder.Default
    private Long id=0L;
	
	@ManyToOne
    @JoinColumn(name="ROLSIS_ID", nullable = false)
    private RolSis rolsis;
	
	@ManyToOne
    @JoinColumn(name="OPCION_ID", nullable = false)
    private Opcion opcion;

}