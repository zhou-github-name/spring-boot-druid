package com.springboot.exception;

public class LogException extends Exception {

	/**
	 * 日志异常
	 */
	private static final long serialVersionUID = 3049387276129991332L;

	public LogException() {
		super();
	}

	public LogException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LogException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogException(String message) {
		super(message);
	}

	public LogException(Throwable cause) {
		super(cause);
	}

	
}
