package com.gapirs1.flight.exception;

public class FlightIdAlreadyPresentException extends Exception {

	private static final long serialVersionUID = 1L;

	public FlightIdAlreadyPresentException(String message) {
		super(message);
	}
}
