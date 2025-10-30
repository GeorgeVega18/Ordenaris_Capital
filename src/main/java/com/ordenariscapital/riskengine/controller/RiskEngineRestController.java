package com.ordenariscapital.riskengine.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ordenariscapital.riskengine.dto.ResultadoNivelRiesgoDTO;
import com.ordenariscapital.riskengine.dto.SolicitudEvaluacionDTO;
import com.ordenariscapital.riskengine.enums.ProductoFinanciero;
import com.ordenariscapital.riskengine.exceptions.BusinessException;
import com.ordenariscapital.riskengine.service.RiskEngineService;

@RestController
@RequestMapping("/riskengine")
public class RiskEngineRestController {

    @Autowired
    private RiskEngineService riskEngineService;

    @GetMapping
    public ResultadoNivelRiesgoDTO calcularRiesgo(
    		@RequestParam String claveEmpresa,
            @RequestParam BigDecimal montoSolicitado,
            @RequestParam String producto,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaSolicitud) throws BusinessException {

        ProductoFinanciero productoFinanciero;
        try {
            productoFinanciero = ProductoFinanciero.valueOf(producto.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException();
        }

        SolicitudEvaluacionDTO solicitud = new SolicitudEvaluacionDTO(
                claveEmpresa,
                montoSolicitado,
                productoFinanciero,
                fechaSolicitud
        );

        return riskEngineService.calcularRiesgo(solicitud);
    }
}
