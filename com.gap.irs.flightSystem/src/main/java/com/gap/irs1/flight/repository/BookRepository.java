package com.gap.irs1.flight.repository;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gap.irs1.flight.entity.BookEntity;

public interface BookRepository extends  JpaRepository<BookEntity, String>{

	@Query("SELECT  e FROM BookEntity e WHERE SOURCE_ID=:sourceid and DESTINATION_ID=:destinationid and DEPARTUREDATE = :jdate")
	BookEntity bookingValidation(@Param("sourceid") String sourceid,@Param("destinationid") String destinationid,@Param("jdate") Calendar jDate);
}
