package com.gap.irs1.flight.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="FLIGHT_SERVICE")
public class FlightEntity {
	@Id
	@Column(name="FLIGHTID")
	private String flightId;
	@Column(name="AIRLINE")
	private String airline;
	@Column(name="SOURCE_ID")
	private String sourceid;
	@Column(name="DESTINATION_ID")
	private String destinationid;
	@Column(name="DEPARTUREDATE")
	private String departureDate;
	@Column(name="AVAILABLESEATS")
	private int availableSeats;
	@Column(name="FARE")
	private String fare;
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
	public String getDestinationid() {
		return destinationid;
	}
	public void setDestinationid(String destinationid) {
		this.destinationid = destinationid;
	}
	
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String jDate) {
		this.departureDate = jDate;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	
}
