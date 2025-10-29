package com.ordenariscapital.riskengine.providers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ordenariscapital.riskengine.dto.VerificacionLegalDTO;
import com.ordenariscapital.riskengine.entity.EmpresaEntity;
import com.ordenariscapital.riskengine.exceptions.BusinessException;
import com.ordenariscapital.riskengine.exceptions.EmpresaNoEncontradaException;
import com.ordenariscapital.riskengine.providers.VerificacionLegalProvider;
import com.ordenariscapital.riskengine.repository.EmpresaRepository;
import com.ordenariscapital.riskengine.repository.VerificacionLegalRepository;

@Component
public class VerificacionLegalImpl implements VerificacionLegalProvider {

	@Autowired
	private VerificacionLegalRepository verificacionLegalRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public List<VerificacionLegalDTO> getInformacionLEgal(String claveEmpresa) throws BusinessException {
		EmpresaEntity empresa = empresaRepository.findByClaveEmpresa(claveEmpresa).orElseThrow(() -> new EmpresaNoEncontradaException());
		var verificaiconLegal = verificacionLegalRepository.findByEmpresa(empresa);
		return verificaiconLegal.stream().map((verificacionLegal)->{
			return new VerificacionLegalDTO(verificacionLegal.getFechaProceso(),verificacionLegal.getNombreProceso());
		}).toList();
	}
	

}
