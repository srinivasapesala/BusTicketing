package com.gapirs1.flight.exception;

public class TicketNotAvailableException extends Exception {

	private static final long serialVersionUID = 1L;

	public TicketNotAvailableException(String message) {
		super(message);
	}
	
}
