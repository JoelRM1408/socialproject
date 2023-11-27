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
@Table(name="ASISTENCIAS")
public class Asistencia {
	@Id
	@Column(name = "ID_ASISTENCIA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAsistencia")
    @SequenceGenerator(name = "seqAsistencia", allocationSize = 1, sequenceName = "SEQ_ASISTENCIA")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "ESTADO")
	@NotNull
    private String estado;
	
	@ManyToOne
    @JoinColumn(name="ACTIVIDAD_ID", nullable = false)
    private Actividad actividad;
	
	@ManyToOne
    @JoinColumn(name="INFORME_ID", nullable = false)
    private Informe informe;
	
	@OneToOne
    @JoinColumn(name = "ESTUDIANTE_EQUIPO_ID", nullable = false)
    private EstudianteEquipo estequipo;			

}