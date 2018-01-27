package com.gapirs1.flight.exception;

public class FlightNotAvailableException extends Exception {

	private static final long serialVersionUID = 1L;

	public FlightNotAvailableException(String message) {
		super(message);
	}
}
