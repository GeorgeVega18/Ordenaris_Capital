package com.ordenariscapital.riskengine.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "EMPRESA")
@Data
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPRESA_ID")
    private Long empresaId;

    @Column(name = "CLAVE_EMPRESA", length = 50)
    private String claveEmpresa;

    @Column(name = "NOMBRE", length = 200)
    private String nombre;

    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;

    @OneToOne(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ContablesEntity contables;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VerificacionLegalEntity> verificacionesLegales;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CreditoEntity> creditos;
    
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PagosEntity> pagos;

}
