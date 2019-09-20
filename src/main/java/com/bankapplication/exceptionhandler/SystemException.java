package com.bankapplication.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SystemException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SystemException(String message) {
		super(message);
	}
}
