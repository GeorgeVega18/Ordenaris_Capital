package com.ordenariscapital.riskengine.rule;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ordenariscapital.riskengine.dto.ResultadoReglaDTO;
import com.ordenariscapital.riskengine.dto.SolicitudEvaluacionDTO;
import com.ordenariscapital.riskengine.dto.VerificacionLegalDTO;
import com.ordenariscapital.riskengine.enums.NivelRiesgo;
import com.ordenariscapital.riskengine.exceptions.BusinessException;
import com.ordenariscapital.riskengine.providers.VerificacionLegalProvider;

@Component
public class DemandaLegalAbiertaRule implements BusinessRules {

    private final VerificacionLegalProvider verificacionLegalProvider;

    public DemandaLegalAbiertaRule(VerificacionLegalProvider verificacionLegalProvider) {
        this.verificacionLegalProvider = verificacionLegalProvider;
    }

    private static final String NOMBRE_REGLA = "Demanda Legal Abierta";
    private static final String PALABRA_CLAVE_JUICIO = "juicio mercantil";

    @Override
    public ResultadoReglaDTO apply(NivelRiesgo nivelRiesgo, SolicitudEvaluacionDTO solicitud) throws BusinessException {
        List<VerificacionLegalDTO> procesos = verificacionLegalProvider.getInformacionLEgal(solicitud.claveEmpresa());

        boolean tieneJuicio = procesos.stream()
                .anyMatch(p -> p.nombreProceso() != null &&
                               p.nombreProceso().toLowerCase().contains(PALABRA_CLAVE_JUICIO));

        return ResultadoReglaDTO.builder()
                .nombreRegla(NOMBRE_REGLA)
                .aplicada(tieneJuicio)
                .impactoRiesgo(tieneJuicio ? NivelRiesgo.ALTO : null)
                .build();
    }

    @Override
    public int getPrioridad() {
        return 85;
    }
}
