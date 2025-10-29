package com.ordenariscapital.riskengine.service;

import com.ordenariscapital.riskengine.dto.ResultadoNivelRiesgoDTO;
import com.ordenariscapital.riskengine.dto.SolicitudEvaluacionDTO;
import com.ordenariscapital.riskengine.exceptions.BusinessException;

public interface RiskEngineService {
	 ResultadoNivelRiesgoDTO calcularRiesgo(SolicitudEvaluacionDTO solicitud) throws BusinessException ;
}
