package com.ordenariscapital.riskengine.providers.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ordenariscapital.riskengine.dto.HistorialCreditosDTO;
import com.ordenariscapital.riskengine.entity.CreditoEntity;
import com.ordenariscapital.riskengine.entity.EmpresaEntity;
import com.ordenariscapital.riskengine.entity.PagosEntity;
import com.ordenariscapital.riskengine.exceptions.BusinessException;
import com.ordenariscapital.riskengine.exceptions.EmpresaNoEncontradaException;
import com.ordenariscapital.riskengine.providers.HistorialPagosProvider;
import com.ordenariscapital.riskengine.repository.CreditoRepository;
import com.ordenariscapital.riskengine.repository.EmpresaRepository;

@Component
public class HistorialPagosProviderImpl implements HistorialPagosProvider {

	@Autowired
	private CreditoRepository creditoRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public List<HistorialCreditosDTO> getHistorialPagos(String claveEmpresa) throws BusinessException {
	    EmpresaEntity empresa = empresaRepository.findByClaveEmpresa(claveEmpresa)
	            .orElseThrow(() -> new EmpresaNoEncontradaException());

	    List<CreditoEntity> creditos = creditoRepository.findByEmpresa(empresa);
	    List<HistorialCreditosDTO> historial = new ArrayList<>();

	    creditos.forEach(credito -> {
	        List<PagosEntity> pagos = credito.getPagos();
	        
	        boolean esExcelente = pagos.stream()
	                .limit(12)
	                .noneMatch(pago -> pago.getFechaPagoReal().after(pago.getFechaPagosRequeridos()));
	        long diasAtraso = pagos.stream().mapToLong(pago -> {
	            LocalDateTime pagoSolicitado = LocalDateTime.ofInstant(
	                    pago.getFechaPagosRequeridos().toInstant(), ZoneId.systemDefault());
	            LocalDateTime pagoReal = LocalDateTime.ofInstant(
	                    pago.getFechaPagoReal().toInstant(), ZoneId.systemDefault());
	            long dias = ChronoUnit.DAYS.between(pagoSolicitado, pagoReal);
	            return Math.max(dias, 0L);
	        }).sum();

	        historial.add(new HistorialCreditosDTO(
	                credito.getNoCredito(),
	                credito.getMontoSolicitado(),
	                credito.getMontoPendiente(),
	                diasAtraso,
	                credito.getRefinanciamiento(),
	                esExcelente
	        ));
	    });

	    return historial;
	}

}
