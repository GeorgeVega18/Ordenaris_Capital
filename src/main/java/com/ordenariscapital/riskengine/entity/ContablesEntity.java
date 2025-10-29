package com.ordenariscapital.riskengine.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;


@Entity
@Table(name = "CONTABLES")
@Data

public class ContablesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTABLES_ID")
    private Long contablesId;

    @OneToOne
    @JoinColumn(name = "EMPRESA_ID", nullable = false)
    private EmpresaEntity empresa;

    @Column(name = "VENTAS_PROMEDIO")
    private BigDecimal ventasPromedio;

    @Column(name = "ACTIVOS")
    private BigDecimal activos;

    @Column(name = "PASIVOS")
    private BigDecimal pasivos;

}

