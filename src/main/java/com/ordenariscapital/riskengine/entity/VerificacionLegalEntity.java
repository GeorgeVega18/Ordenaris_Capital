package com.ordenariscapital.riskengine.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "VERIFICACION_LEGAL")
@Data
public class VerificacionLegalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VERIFICACION_LEGAL_ID")
    private Long verificacionLegalId;

    @Column(name = "NOMBRE_PROCESO", length = 100)
    private String nombreProceso;

    @Column(name = "FECHA_PROCESO")
    private Date fechaProceso;

    @ManyToOne
    @JoinColumn(name = "EMPRESA_ID", nullable = false)
    private EmpresaEntity empresa;
}

