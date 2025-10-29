package com.ordenariscapital.riskengine.rule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ordenariscapital.riskengine.dto.HistorialCreditosDTO;
import com.ordenariscapital.riskengine.dto.ResultadoReglaDTO;
import com.ordenariscapital.riskengine.dto.SolicitudEvaluacionDTO;
import com.ordenariscapital.riskengine.enums.NivelRiesgo;
import com.ordenariscapital.riskengine.exceptions.BusinessException;
import com.ordenariscapital.riskengine.providers.HistorialPagosProvider;


@Component
public class DeudaActivaRule implements BusinessRules {
	
	private static final String NOMBRE_REGLA = "Deuda Activa";
	
	@Autowired
	private HistorialPagosProvider historialPagosProvider;
	
	@Override
    public ResultadoReglaDTO apply(NivelRiesgo nivelRiesgo, SolicitudEvaluacionDTO solicitud) throws BusinessException {
       
		List<HistorialCreditosDTO> historial = historialPagosProvider.getHistorialPagos(solicitud.claveEmpresa());
		var tieneDiasAtraso = historial.stream().anyMatch(item ->  (item.diasAtraso() > 90) );
        
        return ResultadoReglaDTO.builder()
                .nombreRegla(NOMBRE_REGLA)
                .aplicada(true)
                .impactoRiesgo(tieneDiasAtraso ? NivelRiesgo.RECHAZADO : null)
                .build();
    }

    @Override
    public int getPrioridad() {
        return 100;
    }

}
