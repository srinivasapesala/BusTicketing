package com.gap.irs1.flight.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gap.irs1.flight.entity.BookEntity;
import com.gap.irs1.flight.entity.FlightEntity;

public interface FlightRepository extends JpaRepository<FlightEntity, String>{
	
			
	@Query("SELECT e FROM FlightEntity e WHERE SOURCE_ID=:source and DESTINATION_ID=:destination")
	List<FlightEntity> findSourceAndDest(@Param("source") String source,@Param("destination") String destination);
	
	@Query("SELECT  flightId, airline, sourceid, destinationid, departureDate, availableSeats, fare FROM FlightEntity WHERE SOURCE_ID=:source")
	FlightEntity findSource(@Param("source") String source);
	
	@Query("SELECT  flightId, airline, sourceid, destinationid, departureDate, availableSeats, fare FROM FlightEntity WHERE DESTINATION_ID=:destination")
	FlightEntity findDestination(@Param("destination") String destination);
	
	@Query("SELECT e FROM FlightEntity e WHERE SOURCE_ID=:source and DESTINATION_ID=:destination and DEPARTUREDATE > :jdate")
	List<FlightEntity> findSourceAndDestDate(@Param("source") String source,@Param("destination") String destination,@Param("jdate") Calendar jDate);


	@Query("SELECT  e FROM FlightEntity e WHERE SOURCE_ID=:sourceid and DESTINATION_ID=:destinationid and DEPARTUREDATE >= :jdate")
	FlightEntity bookingValidation(@Param("sourceid") String sourceid,@Param("destinationid") String destinationid,@Param("jdate") Calendar jDate);
	
}

