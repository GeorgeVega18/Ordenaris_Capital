package com.ordenariscapital.riskengine.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.ordenariscapital.riskengine.enums.ProductoFinanciero;

public record SolicitudEvaluacionDTO(String claveEmpresa, BigDecimal montoSolicitado,
		ProductoFinanciero productoFinanciero, Date fechaSolicitud) {
}
