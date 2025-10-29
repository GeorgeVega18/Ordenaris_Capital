package com.ordenariscapital.riskengine.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "PAGOS")
@Data

public class PagosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAGOS_ID")
    private Long pagosId;

    @Column(name = "FECHA_PAGOS_REQUERIDOS")
    private Date fechaPagosRequeridos;

    @Column(name = "FECHA_PAGO_REAL")
    private Date fechaPagoReal;

    @Column(name = "MONTO_SOLICITADO", length = 10)
    private BigDecimal montoSolicitado;

    @Column(name = "MONTO_PAGADO", length = 10)
    private BigDecimal montoPagado;

    @ManyToOne
    @JoinColumn(name = "CREDITO_ID", nullable = false)
    private CreditoEntity credito;
    
    @OneToOne
    @JoinColumn(name = "EMPRESA_ID", nullable = false)
    private EmpresaEntity empresa;


}
