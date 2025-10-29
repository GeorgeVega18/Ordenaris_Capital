package com.ordenariscapital.riskengine.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ordenariscapital.riskengine.entity.CreditoEntity;
import com.ordenariscapital.riskengine.entity.EmpresaEntity;

import java.util.List;


@Repository
public interface CreditoRepository extends JpaRepository<CreditoEntity, Long> {
    
    List<CreditoEntity> findByEmpresa(EmpresaEntity empresa);
    
    @Query("SELECT c FROM CreditoEntity c WHERE c.empresa.claveEmpresa = :claveEmpresa")
    List<CreditoEntity> findByClaveEmpresa(@Param("claveEmpresa") String claveEmpresa);
    
    @Query("SELECT c FROM CreditoEntity c WHERE c.empresa.claveEmpresa = :claveEmpresa " +
           "AND CAST(c.diasAtrasado AS int) > :dias")
    List<CreditoEntity> findCreditosConMoraByClaveEmpresa(
            @Param("claveEmpresa") String claveEmpresa, 
            @Param("dias") int dias);
}
