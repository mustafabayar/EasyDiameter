package com.easydiameter.exception;

public class DiameterException extends Exception {
	static final long		serialVersionUID				= 0;

	public static final int	DIAMETER_UNSPECIFIED_EXCEPTION	= 0;
	public static final int	DIAMETER_BUILD_EXCEPTION		= 1;
	public static final int	DIAMETER_PARSE_EXCEPTION		= 2;
	public static final int	DIAMETER_DICTIONARY_EXCEPTION	= 3;

	int						exceptionType					= DIAMETER_UNSPECIFIED_EXCEPTION;
	long					errorReason						= 0;

	public DiameterException(long error, String message) {
		super(message);
		this.errorReason = error;
	}

	public DiameterException(long error, String message, Throwable cause) {
		super(message, cause);
		this.errorReason = error;
	}

	public DiameterException(long error, String message, int type) {
		super(message);
		this.errorReason = error;
		this.exceptionType = type;
	}

	public DiameterException(long error, String message, Throwable cause, int type) {
		super(message, cause);
		this.errorReason = error;
		this.exceptionType = type;
	}

	public long getErrorReason() {
		return errorReason;
	}

	public int getExceptionType() {
		return this.exceptionType;
	}

	public void setExceptionType(int type) {
		this.exceptionType = type;
	}
}
