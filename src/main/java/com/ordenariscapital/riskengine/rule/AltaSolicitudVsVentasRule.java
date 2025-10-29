package com.ordenariscapital.riskengine.rule;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ordenariscapital.riskengine.dto.ResultadoReglaDTO;
import com.ordenariscapital.riskengine.dto.SolicitudEvaluacionDTO;
import com.ordenariscapital.riskengine.enums.NivelRiesgo;
import com.ordenariscapital.riskengine.exceptions.BusinessException;
import com.ordenariscapital.riskengine.providers.DatosContablesProvider;

@Component
public class AltaSolicitudVsVentasRule implements BusinessRules {
	
	private static final BigDecimal MULTIPLICADOR_LIMITE = BigDecimal.valueOf(8);
	private static final String NOMBRE_REGLA = "Alta Solicitud vs Ventas";
	
	@Autowired
	DatosContablesProvider datosContablesProvider ;

    @Override
    public ResultadoReglaDTO apply(NivelRiesgo nivelRiesgo, SolicitudEvaluacionDTO solicitud) throws BusinessException{
    	
    	var datosContables = datosContablesProvider.getDatosContables(solicitud.claveEmpresa());
    	
        BigDecimal montoSolicitado = solicitud.montoSolicitado();
        BigDecimal ventasPromedio = datosContables.ventasPromedio();
        
        BigDecimal limiteVentas = ventasPromedio.multiply(MULTIPLICADOR_LIMITE);
        boolean excedeLimite = montoSolicitado.compareTo(limiteVentas) > 0;
        
        return ResultadoReglaDTO.builder()
                .nombreRegla(NOMBRE_REGLA)
                .aplicada(true)
                .impactoRiesgo(excedeLimite ? NivelRiesgo.ALTO : null)
                .build();
    }


    @Override
    public int getPrioridad() {
        return 90;
    }

}
