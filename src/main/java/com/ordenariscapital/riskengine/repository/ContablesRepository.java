package com.ordenariscapital.riskengine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ordenariscapital.riskengine.entity.ContablesEntity;
import com.ordenariscapital.riskengine.entity.EmpresaEntity;

@Repository
public interface ContablesRepository extends JpaRepository<ContablesEntity, Long> {
    
    Optional<ContablesEntity> findByEmpresa(EmpresaEntity empresa);
    
    @Query("SELECT c FROM ContablesEntity c WHERE c.empresa.claveEmpresa = :claveEmpresa")
    Optional<ContablesEntity> findByClaveEmpresa(@Param("claveEmpresa") String claveEmpresa);
}