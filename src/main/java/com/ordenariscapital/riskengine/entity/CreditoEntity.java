package com.ordenariscapital.riskengine.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "CREDITO")
@Data
 public class CreditoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CREDITO_ID")
    private Long creditoId;

    @Column(name = "NO_CREDITO", length = 10)
    private String noCredito;

    @ManyToOne
    @JoinColumn(name = "EMPRESA_ID", nullable = false)
    private EmpresaEntity empresa;

    @Column(name = "MONTO_SOLICITADO", nullable = false)
    private BigDecimal montoSolicitado;

    @Column(name = "MONTO_PENDIENTE")
    private BigDecimal montoPendiente;

    @Column(name = "DIAS_ATRASADO", length = 10, nullable = false)
    private String diasAtrasado;

    @Column(name = "REFINANCIAMIENTO")
    private Boolean refinanciamiento;
    
    @OneToMany(mappedBy = "credito")
    @OrderBy("fechaPagosRequeridos DESC")
    private List<PagosEntity> pagos;

    public int getDiasAtrasadoInt() {
        return diasAtrasado != null ? Integer.parseInt(diasAtrasado) : 0;
    }

}

