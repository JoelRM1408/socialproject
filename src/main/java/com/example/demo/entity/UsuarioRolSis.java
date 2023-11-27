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
@Table(name="USUARIOS_ROLES_SIS")
public class UsuarioRolSis {
	@Id
	@Column(name = "ID_USUARIO_ROLSIS")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUsRolSis")
    @SequenceGenerator(name = "seqUsRolSis", allocationSize = 1, sequenceName = "SEQ_USROLSIS")
    @Builder.Default
    private Long id=0L;
	
	@ManyToOne
    @JoinColumn(name="USUARIO_ID", nullable = false)
    private Usuario usuario;
	
	@ManyToOne
    @JoinColumn(name="ROLSIS_ID", nullable = false)
    private RolSis rolsis;

}