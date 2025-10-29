package com.ordenariscapital.riskengine.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordenariscapital.riskengine.dto.ResultadoNivelRiesgoDTO;
import com.ordenariscapital.riskengine.dto.ResultadoReglaDTO;
import com.ordenariscapital.riskengine.dto.SolicitudEvaluacionDTO;
import com.ordenariscapital.riskengine.enums.NivelRiesgo;
import com.ordenariscapital.riskengine.exceptions.BusinessException;
import com.ordenariscapital.riskengine.rule.BusinessRules;
import com.ordenariscapital.riskengine.service.RiskEngineService;

@Service
public class RiskEngineServiceImpl implements RiskEngineService {
	
	@Autowired
	private List<BusinessRules> businessRules;

	public ResultadoNivelRiesgoDTO calcularRiesgo(SolicitudEvaluacionDTO solicitud) throws BusinessException {

	    var resultados = new ArrayList<ResultadoReglaDTO>();
	    var riesgoCalculado = NivelRiesgo.BAJO;
	    var motivos = new StringBuilder();
	    
	    businessRules.sort(Comparator.comparingInt(BusinessRules::getPrioridad));
	    
	    for(BusinessRules rule : businessRules) {
	        ResultadoReglaDTO res = rule.apply(riesgoCalculado, solicitud);
	        resultados.add(res);
	        if(res.getImpactoRiesgo() != null) {
	            NivelRiesgo riesgoAnterior = riesgoCalculado;
	            riesgoCalculado = NivelRiesgo.max(riesgoCalculado, res.getImpactoRiesgo());
	            
	            if(riesgoCalculado != riesgoAnterior) {
	                if(motivos.length() > 0) {
	                    motivos.append(", ");
	                }
	                motivos.append(res.getNombreRegla());
	            }
	        }
	        
	        if(riesgoCalculado == NivelRiesgo.RECHAZADO) {
	            break;
	        }
	    }
	    
	    return new ResultadoNivelRiesgoDTO(
	        riesgoCalculado.toString(), 
	        resultados, 
	        motivos.toString()
	    );
	}
}
