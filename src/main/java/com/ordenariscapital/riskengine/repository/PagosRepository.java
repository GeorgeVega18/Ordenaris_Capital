package com.ordenariscapital.riskengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ordenariscapital.riskengine.entity.EmpresaEntity;
import com.ordenariscapital.riskengine.entity.PagosEntity;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PagosRepository extends JpaRepository<PagosEntity, Long> {
    
    List<PagosEntity> findByEmpresa(EmpresaEntity empresa);
    
    @Query("SELECT p FROM PagosEntity p WHERE p.empresa.claveEmpresa = :claveEmpresa")
    List<PagosEntity> findByClaveEmpresa(@Param("claveEmpresa") String claveEmpresa);
    
    @Query("SELECT p FROM PagosEntity p " +
           "WHERE p.empresa.claveEmpresa = :claveEmpresa " +
           "AND p.fechaPagosRequeridos >= :fechaDesde " +
           "ORDER BY p.fechaPagosRequeridos DESC")
    List<PagosEntity> findUltimosPagosByClaveEmpresa(
            @Param("claveEmpresa") String claveEmpresa,
            @Param("fechaDesde") LocalDateTime fechaDesde);
}
