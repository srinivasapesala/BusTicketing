package com.gap.irs1.flight.model;

import java.util.Calendar;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class BookFlights {

	@NotEmpty(message="Please enter the origin")
	 private String sourceid;
		 
		 @NotEmpty(message="Please enter the destination")
		 private String destinationid;
		 @NotEmpty(message="Please enter the journey date")
		 private String departureDate;
		 
		 private String fare;
		 private String flightId;
		 @Size(max=3)
		 private int availableSeats;
		 private String airline;
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
		public void setDepartureDate(String departureDate) {
			this.departureDate = departureDate;
		}
		public String getFare() {
			return fare;
		}
		public void setFare(String fare) {
			this.fare = fare;
		}
		public String getFlightId() {
			return flightId;
		}
		public void setFlightId(String flightId) {
			this.flightId = flightId;
		}
		public int getAvailableSeats() {
			return availableSeats;
		}
		public void setAvailableSeats(int availableSeats) {
			this.availableSeats = availableSeats;
		}
		public String getAirline() {
			return airline;
		}
		public void setAirline(String airline) {
			this.airline = airline;
		}

}
