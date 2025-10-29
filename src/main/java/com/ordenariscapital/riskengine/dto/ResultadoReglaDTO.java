package com.ordenariscapital.riskengine.dto;

import com.ordenariscapital.riskengine.enums.NivelRiesgo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoReglaDTO {
	private String nombreRegla;
    private boolean aplicada;
    private NivelRiesgo impactoRiesgo;
}
