package com.ordenariscapital.riskengine.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{claveEmpresa}")
    public ResultadoNivelRiesgoDTO calcularRiesgo(
            @PathVariable String claveEmpresa,
            @RequestParam BigDecimal montoSolicitado,
            @RequestParam String producto) throws BusinessException {

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
                new Date()
        );

        return riskEngineService.calcularRiesgo(solicitud);
    }
}
