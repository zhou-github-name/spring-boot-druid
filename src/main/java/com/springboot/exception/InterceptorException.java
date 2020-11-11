package com.springboot.exception;

public class InterceptorException extends Exception {

	/**
	 * 拦截器异常
	 */
	private static final long serialVersionUID = -3951811996014429645L;

	public InterceptorException() {
		super();
	}

	public InterceptorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InterceptorException(String message, Throwable cause) {
		super(message, cause);
	}

	public InterceptorException(String message) {
		super(message);
	}

	public InterceptorException(Throwable cause) {
		super(cause);
	}

	
}
