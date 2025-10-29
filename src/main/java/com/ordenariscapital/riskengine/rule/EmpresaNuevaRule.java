package com.ordenariscapital.riskengine.rule;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.ordenariscapital.riskengine.dto.ResultadoReglaDTO;
import com.ordenariscapital.riskengine.dto.SolicitudEvaluacionDTO;
import com.ordenariscapital.riskengine.entity.EmpresaEntity;
import com.ordenariscapital.riskengine.enums.NivelRiesgo;
import com.ordenariscapital.riskengine.exceptions.BusinessException;
import com.ordenariscapital.riskengine.repository.EmpresaRepository;

@Component
public class EmpresaNuevaRule implements BusinessRules {
	
	private final EmpresaRepository empresaRepository;

    public EmpresaNuevaRule(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }
	
	private static final int MESES_MINIMOS = 18;
	private static final String NOMBRE_REGLA = "Empresa nueva";

	@Override
    public ResultadoReglaDTO apply(NivelRiesgo nivelRiesgo, SolicitudEvaluacionDTO solicitud) throws BusinessException {
        EmpresaEntity empresa = empresaRepository.findByClaveEmpresa(solicitud.claveEmpresa())
                .orElseThrow(() -> new BusinessException());

        LocalDateTime fechaCreacion = LocalDateTime.ofInstant(
                empresa.getFechaCreacion().toInstant(), ZoneId.systemDefault());

        long mesesExistencia = ChronoUnit.MONTHS.between(fechaCreacion, LocalDateTime.now());
        boolean esNueva = mesesExistencia < MESES_MINIMOS;

        return ResultadoReglaDTO.builder()
                .nombreRegla(NOMBRE_REGLA)
                .aplicada(true)
                .impactoRiesgo(esNueva ? NivelRiesgo.MEDIO : null)
                .build();
    }


    @Override
    public int getPrioridad() {
        return 80;
    }

}
