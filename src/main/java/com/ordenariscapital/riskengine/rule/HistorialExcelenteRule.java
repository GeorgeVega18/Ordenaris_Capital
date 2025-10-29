package com.ordenariscapital.riskengine.rule;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ordenariscapital.riskengine.dto.HistorialCreditosDTO;
import com.ordenariscapital.riskengine.dto.ResultadoReglaDTO;
import com.ordenariscapital.riskengine.dto.SolicitudEvaluacionDTO;
import com.ordenariscapital.riskengine.enums.NivelRiesgo;
import com.ordenariscapital.riskengine.exceptions.BusinessException;
import com.ordenariscapital.riskengine.providers.HistorialPagosProvider;

@Component
public class HistorialExcelenteRule implements BusinessRules {

    private static final String NOMBRE_REGLA = "Historial excelente";

    private final HistorialPagosProvider historialPagosProvider;

    public HistorialExcelenteRule(HistorialPagosProvider historialPagosProvider) {
        this.historialPagosProvider = historialPagosProvider;
    }

    @Override
    public ResultadoReglaDTO apply(NivelRiesgo nivelRiesgo, SolicitudEvaluacionDTO solicitud) throws BusinessException {
        List<HistorialCreditosDTO> historial = historialPagosProvider.getHistorialPagos(solicitud.claveEmpresa());

        boolean tieneHistorialExcelente = historial.stream()
            .anyMatch(h -> Boolean.TRUE.equals(h.excelente()) && Boolean.FALSE.equals(h.refinanciado()));

        return ResultadoReglaDTO.builder()
                .nombreRegla(NOMBRE_REGLA)
                .aplicada(tieneHistorialExcelente)
                .impactoRiesgo(null)
                .build();
    }

    @Override
    public int getPrioridad() {
        return 10;
    }


}
