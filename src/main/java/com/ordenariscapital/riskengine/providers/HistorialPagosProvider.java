package com.ordenariscapital.riskengine.providers;

import java.util.List;

import com.ordenariscapital.riskengine.dto.HistorialCreditosDTO;
import com.ordenariscapital.riskengine.exceptions.BusinessException;

public interface HistorialPagosProvider {
	
	 List<HistorialCreditosDTO> getHistorialPagos(String claveEmpresa) throws BusinessException;

}
