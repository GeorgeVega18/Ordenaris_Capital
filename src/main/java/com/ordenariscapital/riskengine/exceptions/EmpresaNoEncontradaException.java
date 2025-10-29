package com.ordenariscapital.riskengine.exceptions;

public class EmpresaNoEncontradaException extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Empresa no encontrada";
	}

}
