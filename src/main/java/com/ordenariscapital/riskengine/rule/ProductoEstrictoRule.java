package com.ordenariscapital.riskengine.rule;

import org.springframework.stereotype.Component;

import com.ordenariscapital.riskengine.dto.ResultadoReglaDTO;
import com.ordenariscapital.riskengine.dto.SolicitudEvaluacionDTO;
import com.ordenariscapital.riskengine.enums.NivelRiesgo;
import com.ordenariscapital.riskengine.enums.ProductoFinanciero;
import com.ordenariscapital.riskengine.exceptions.BusinessException;

@Component
public class ProductoEstrictoRule implements BusinessRules {

    private static final String NOMBRE_REGLA = "Producto Estricto";

    @Override
    public ResultadoReglaDTO apply(NivelRiesgo nivelRiesgo, SolicitudEvaluacionDTO solicitud) throws BusinessException {
        boolean esArrendamiento = ProductoFinanciero.ARRENDAMIENTO_FINANCIERO.equals(solicitud.productoFinanciero());

        return ResultadoReglaDTO.builder()
                .nombreRegla(NOMBRE_REGLA)
                .aplicada(esArrendamiento)
                .impactoRiesgo(esArrendamiento ? nivelRiesgo.aumentar() : null)
                .build();
    }

    @Override
    public int getPrioridad() {
        return 20;
    }
}
