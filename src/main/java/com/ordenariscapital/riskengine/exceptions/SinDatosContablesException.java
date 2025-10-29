package com.ordenariscapital.riskengine.exceptions;

public class SinDatosContablesException extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "La empresa no cuenta con datos contables";
	}

}
