package com.example;

public class NotFoundException extends RuntimeException {
	public static final int CONST = 10;
	public final int readonly;
	
	static int algo = 0;
	static {
		algo = 0;
	}
	public NotFoundException() {
		this("Not found");
	}

	public NotFoundException(String message) {
//		if(!"".equals(message) || (message == null && !message.equals("")))
		if(message == null || message.isBlank())
			throw new IllegalArgumentException("El mensaje es obligatorio");
		super(message);		
		readonly = 10;
	}

	public NotFoundException(Throwable cause) {
		super(cause);
		readonly = 20;
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
		readonly = 30;
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		readonly = 40;
	}

}
