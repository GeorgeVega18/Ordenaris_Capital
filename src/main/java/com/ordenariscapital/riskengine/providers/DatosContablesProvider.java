package com.ordenariscapital.riskengine.providers;

import com.ordenariscapital.riskengine.dto.DatosContablesDTO;
import com.ordenariscapital.riskengine.exceptions.BusinessException;

public interface DatosContablesProvider {
	
	 DatosContablesDTO getDatosContables(String claveEmpresa) throws BusinessException;	

}
