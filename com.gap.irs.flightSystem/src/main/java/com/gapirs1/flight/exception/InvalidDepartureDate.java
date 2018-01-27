package com.gapirs1.flight.exception;

public class InvalidDepartureDate extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidDepartureDate(String message) {
		super(message);
	}
}
