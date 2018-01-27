package com.gap.irs1.flight.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="FLIGHT_SERVICE")
public class BookEntity {
	@Id
	@Column(name="FLIGHTID")
	private String flightId;
	@Column(name="AIRLINE")
	private String airline;
	@Column(name="SOURCE_ID")
	private String sourceid;
	@Column(name="DESTINATION_ID")
	private String destinationid;
	@Temporal(TemporalType.DATE)
	@Column(name="DEPARTUREDATE")
	private Date departureDate;
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
	
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date string) {
		this.departureDate = string;
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
