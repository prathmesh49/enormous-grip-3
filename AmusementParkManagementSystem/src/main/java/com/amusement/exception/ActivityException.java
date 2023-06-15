package com.amusement.exception;

public class ActivityException extends Exception {

	public ActivityException() {
		super();

	}

	public ActivityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public ActivityException(String message, Throwable cause) {
		super(message, cause);
	}

	public ActivityException(String message) {
		super(message);
	}

	public ActivityException(Throwable cause) {
		super(cause);
	}

}
