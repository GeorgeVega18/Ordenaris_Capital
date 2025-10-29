package com.ordenariscapital.riskengine.rule;

import com.ordenariscapital.riskengine.dto.ResultadoReglaDTO;
import com.ordenariscapital.riskengine.dto.SolicitudEvaluacionDTO;
import com.ordenariscapital.riskengine.enums.NivelRiesgo;
import com.ordenariscapital.riskengine.exceptions.BusinessException;

public interface BusinessRules {
   
	ResultadoReglaDTO  apply(NivelRiesgo nivelRiesgo, SolicitudEvaluacionDTO solicitud) throws BusinessException;
    
    //Obtenemos la prioridad
    default int getPrioridad() {
        return 0;
    }
}