package com.ordenariscapital.riskengine.dto;

import java.math.BigDecimal;

public record HistorialCreditosDTO(String numCredito, BigDecimal montoSolicitado, BigDecimal montoPendiente, long diasAtraso, Boolean refinanciado, Boolean excelente) {

}
