package com.ordenariscapital.riskengine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ordenariscapital.riskengine.entity.EmpresaEntity;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {
	
    Optional<EmpresaEntity> findByClaveEmpresa(String claveEmpresa);

}
