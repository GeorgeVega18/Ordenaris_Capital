package com.ordenariscapital.riskengine.providers;

import java.util.List;

import com.ordenariscapital.riskengine.dto.VerificacionLegalDTO;
import com.ordenariscapital.riskengine.exceptions.BusinessException;

public interface VerificacionLegalProvider {
	
	List<VerificacionLegalDTO> getInformacionLEgal(String claveEmpresa) throws BusinessException ;

}
