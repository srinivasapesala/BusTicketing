package com.gapirs1.flight.exception;

public class InvalidFare extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidFare(String message) {
		super(message);
	}
}
