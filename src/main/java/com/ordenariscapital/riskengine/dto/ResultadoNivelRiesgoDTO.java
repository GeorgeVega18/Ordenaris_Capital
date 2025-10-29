package com.ordenariscapital.riskengine.dto;

import java.util.List;


public record ResultadoNivelRiesgoDTO(String nivelRiesgo, List<ResultadoReglaDTO> reglasEvaluadas, String motivo) {

}
