package com.easydiameter.exception;

public class DiameterDictionaryException extends DiameterException {

	private static final long serialVersionUID = 1L;

	public DiameterDictionaryException(long error, String message) {
		super(error, message);
	}

}
