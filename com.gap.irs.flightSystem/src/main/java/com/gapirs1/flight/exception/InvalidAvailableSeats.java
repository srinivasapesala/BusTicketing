package com.gapirs1.flight.exception;

public class InvalidAvailableSeats extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidAvailableSeats(String message) {
		super(message);
	}
	
}
