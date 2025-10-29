package com.ordenariscapital.riskengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ordenariscapital.riskengine.entity.EmpresaEntity;
import com.ordenariscapital.riskengine.entity.VerificacionLegalEntity;

import java.util.List;

@Repository
public interface VerificacionLegalRepository extends JpaRepository<VerificacionLegalEntity, Long> {
    
    List<VerificacionLegalEntity> findByEmpresa(EmpresaEntity empresa);
    
    @Query("SELECT v FROM VerificacionLegalEntity v WHERE v.empresa.claveEmpresa = :claveEmpresa")
    List<VerificacionLegalEntity> findByClaveEmpresa(@Param("claveEmpresa") String claveEmpresa);
    
    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END " +
           "FROM VerificacionLegalEntity v " +
           "WHERE v.empresa.claveEmpresa = :claveEmpresa ")    
    boolean existeProcesoLegalByClaveEmpresa(
            @Param("claveEmpresa") String claveEmpresa,
            @Param("palabraClave") String palabraClave);
}