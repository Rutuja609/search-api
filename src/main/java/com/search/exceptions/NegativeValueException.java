package com.search.exceptions;

public class NegativeValueException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NegativeValueException() {
		super();
	}
	
	/**
	 * Constructs a new runtime exception with the specified detail.
	 * @param   value   the detail message.
	 */

	public NegativeValueException(String value) {
		super(value);
	}
}
