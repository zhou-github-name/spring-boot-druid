package com.springboot.exception;

public class DataSourceException extends Exception {

	/**
	 * 数据源异常
	 */
	private static final long serialVersionUID = -1424143767322305013L;

	public DataSourceException() {
		super();
	}

	public DataSourceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataSourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataSourceException(String message) {
		super(message);
	}

	public DataSourceException(Throwable cause) {
		super(cause);
	}

	
}
