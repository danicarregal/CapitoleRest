package com.capitole.techtest.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PriceNotFoundException extends RuntimeException {
	
	public PriceNotFoundException(String message) {
		super(message);
	}
}
