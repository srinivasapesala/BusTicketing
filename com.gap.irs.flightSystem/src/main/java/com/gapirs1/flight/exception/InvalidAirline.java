package com.gapirs1.flight.exception;

public class InvalidAirline extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidAirline(String message) {
		super(message);
	}
}
