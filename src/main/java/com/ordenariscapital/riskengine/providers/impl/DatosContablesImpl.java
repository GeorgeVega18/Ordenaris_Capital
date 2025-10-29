package com.ordenariscapital.riskengine.providers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ordenariscapital.riskengine.dto.DatosContablesDTO;
import com.ordenariscapital.riskengine.entity.EmpresaEntity;
import com.ordenariscapital.riskengine.exceptions.BusinessException;
import com.ordenariscapital.riskengine.exceptions.EmpresaNoEncontradaException;
import com.ordenariscapital.riskengine.exceptions.SinDatosContablesException;
import com.ordenariscapital.riskengine.providers.DatosContablesProvider;
import com.ordenariscapital.riskengine.repository.ContablesRepository;
import com.ordenariscapital.riskengine.repository.EmpresaRepository;

@Component
public class DatosContablesImpl implements DatosContablesProvider {

	@Autowired
	private ContablesRepository contablesRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public DatosContablesDTO getDatosContables(String claveEmpresa) throws BusinessException {
		EmpresaEntity empresa = empresaRepository.findByClaveEmpresa(claveEmpresa)
				.orElseThrow(() -> new EmpresaNoEncontradaException());
		var contables = contablesRepository.findByEmpresa(empresa).orElseThrow(() -> new SinDatosContablesException());
		return new DatosContablesDTO(contables.getVentasPromedio(), contables.getPasivos(), contables.getActivos());
	}

}
