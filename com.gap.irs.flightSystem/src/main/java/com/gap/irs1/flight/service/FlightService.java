package com.gap.irs1.flight.service;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gap.irs1.flight.entity.FlightEntity;
import com.gap.irs1.flight.model.SearchFlights;
import com.gap.irs1.flight.repository.FlightRepository;
import com.gapirs1.flight.exception.FlightIdAlreadyPresentException;
import com.gapirs1.flight.exception.FlightNotAvailableException;
import com.gapirs1.flight.exception.InvalidAirline;
import com.gapirs1.flight.exception.InvalidAvailableSeats;
import com.gapirs1.flight.exception.InvalidDepartureDate;
import com.gapirs1.flight.exception.InvalidDestinationId;
import com.gapirs1.flight.exception.InvalidFare;
import com.gapirs1.flight.exception.InvalidFlightIdException;
import com.gapirs1.flight.exception.InvalidSourceId;
@Service
public class FlightService {
    @Autowired
	private FlightRepository flightRepository;

 ModelMapper mp=new ModelMapper(); 
	public List<SearchFlights> getFlights(String source, String destination, Calendar jDate) {
		// TODO Auto-generated method stub
		List<FlightEntity> userEntity1;
		FlightEntity fe=null;// = flightRepository.findOne(source);
		System.out.println("Inside get FLights at source dest and date");
		System.out.println("--------------"+flightRepository.findOne(source));
	   /* if (fe == null) {
	        System.out.println("Flights not available for this source");
	    }*/
	   //userEntity1=(List<SearchFlights>) 
	   // FlightEntity f=new FlightEntity();
			 List<FlightEntity> FEList =  flightRepository.findSourceAndDestDate(source, destination,jDate) ;
			//  Iterator it = FEList.iterator();
			//SearchFlights SFList = new ArrayList<SearchFlights>();
			  
			//  while(it.hasNext()) {
				  SearchFlights SFObj = mp.map(FEList, SearchFlights.class);	
				 // SFList.add(SFObj);
			 // }
			  
	   // List userEntity1= flightRepository.findAll() ;
				  
				  java.lang.reflect.Type targetListType  = new TypeToken<List<FlightEntity>>() {}.getType();
				    List<SearchFlights> personDTOs = mp.map(FEList, targetListType );
		return personDTOs;
	}

	@SuppressWarnings("rawtypes")
	public List<SearchFlights> getFlights(String source, String destination) {
		// TODO Auto-generated method stub
		//FlightEntity fe = flightRepository.findOne(source);
		/*FlightEntity fe=flightRepository.findOne("C1002");
      if (fe == null) {
	        System.out.println("Flights not available for this source");
	    }*/
	    
	    System.out.println("Testing time Srinii\n\n\n");
 List<FlightEntity> FEList =  flightRepository.findSourceAndDest(source, destination);//findAll(); //
//	    Iterator<FlightEntity> it = FEList.iterator();
	    //RestTemplate restTemplate = new RestTemplate();
	    
/* List<SearchFlights> SFList = new ArrayList<SearchFlights>();
		  FlightEntity f=new FlightEntity();  
		  SearchFlights s=new SearchFlights();
		  while(it.hasNext()) {
			  SearchFlights li=mp.map(it.next(), SearchFlights.class);
			  assertEquals( ((FlightEntity)(it.next())).getAirline(), li.getAirline());
			  System.out.println("Printing some value"+li.getAirline());
			 // assertEquals(li., exam.getTitle());
			  
		  }*/
		  
	/*	  for (Iterator<FlightEntity> i = FEList.iterator(); i.hasNext(); ) 
		  {
			  
			  System.out.println("Trying wild thing ");
		       System.out.println(i.next().getAirline());
		  }*/
		  //List<Person> persons = getPersons();
		    // Define the target type
	    
	      
	    
	    
	  /*  working but with null output*/
		java.lang.reflect.Type targetListType  = new TypeToken<List<FlightEntity>>() {}.getType();
		    List<SearchFlights> personDTOs = mp.map(FEList, targetListType );
	  
		  
return personDTOs;

	}

	public List<SearchFlights> getFlights() {
		// TODO Auto-generated method stub
		//FlightEntity fe = flightRepository.findOne(source);
		FlightEntity fe=flightRepository.findOne("C1002");
      if (fe == null) {
	        System.out.println("Flights not available for this source");
	    }
	    
	    System.out.println("Testing time Srinii\n\n\n");
 List<FlightEntity> FEList =  flightRepository.findAll(); //findSourceAndDest(source, destination) ;
 java.lang.reflect.Type targetListType  = new TypeToken<List<FlightEntity>>() {}.getType();
 List<SearchFlights> personDTOs = mp.map(FEList, targetListType );
 return personDTOs;
	}
	
	
	/*private SearchFlights convertToSearchFlights(FlightEntity flightEntity){
		
		//List<SearchFlights> searchList=new ArrayList();
		
		SearchFlights searchFlight=mp.map(flightEntity, SearchFlights.class);
		searchFlight.setAirline(flightEntity.getAirline());
		searchFlight.setAvailableSeats(flightEntity.getAvailableSeats());
		System.out.println("Trying to Convert");
		
	/*	for(FlightEntity flightEntity:FlightEntityList) {
			System.out.println("In Forloop for convertion");
			SearchFlights searchFlights=new SearchFlights();
			searchFlights.setAirline(flightEntity.getAirline());
			searchList.add(searchFlights);
		}
		return searchFlight;
	}*/
	
	
	
	public SearchFlights getSources(String source) {
		// TODO Auto-generated method stub
		
		FlightEntity userEntity = flightRepository.findOne(source);
		System.out.println("In Get Source Method");
	    System.out.println("After the user Entity");
		java.lang.reflect.Type targetListType  = new TypeToken<FlightEntity>() {}.getType();
	    SearchFlights personDTOs = mp.map(userEntity, targetListType );
	    
	  // List<String> userEntity1=(List<String>) flightRepository.findSource(source) ;
		return personDTOs;
		
	}

	public List<SearchFlights> getDestinations(String destination ){
		// TODO Auto-generated method stub
		FlightEntity userEntity = flightRepository.findOne(destination);
	java.lang.reflect.Type targetListType  = new TypeToken<List<FlightEntity>>() {}.getType();
	    List<SearchFlights> personDTOs = mp.map(userEntity, targetListType );
return personDTOs;
	}
	
	public String addFlights(SearchFlights flights) throws Exception {
		
		validateUser(flights);
		
		System.out.println("Inside Flight Service class");
		FlightEntity few= flightRepository.findOne(flights.getFlightId());
		System.out.println("Output of find method"+flights.getFlightId());
		if(few!=null) throw new FlightIdAlreadyPresentException("RegistrationService.FlightID_PRESENT");
		FlightEntity f=new FlightEntity();
		f.setAirline(flights.getAirline());
		f.setAvailableSeats(flights.getAvailableSeats());
		f.setDepartureDate(flights.getDepartureDate());
		f.setDestinationid(flights.getDestinationid());
		f.setFare(flights.getFare());
		f.setSourceid(flights.getSourceid());
		f.setFlightId(flights.getFlightId());
		System.out.println("End of FLight Service method");
		flightRepository.saveAndFlush(f);
		System.out.println("End of FLight Service method");
		return "flightRepository.Registration_Success";
	}
	
	public void validateUser(SearchFlights flights)throws Exception
    {
        if(!isValidFlightId(flights.getFlightId()))
            throw new InvalidFlightIdException("RegistrationService.INVALID_FLIGHT_ID");
        if(!isValidSource(flights.getSourceid()))
            throw new InvalidSourceId("RegistrationService.INVALID_SOURCE");
        if(!isValidDestination(flights.getDestinationid()))
            throw new InvalidDestinationId("RegistrationService.INVALID_NAME");
        if(!isValidDepartureDate(flights.getDepartureDate()))
            throw new InvalidDepartureDate("RegistrationService.INVALID_CITY");
        if(!isValidFare(flights.getFare()))
            throw new InvalidFare("RegistrationService.INVALID_EMAIL");
        if(!isValidAirline(flights.getAirline()))
            throw new InvalidAirline("RegistrationService.INVALID_PHONE_NUMBER");
        if(!isValidAvailableSeats(flights.getAirline()))
            throw new InvalidAvailableSeats("RegistrationService.INVALID_PHONE_NUMBER");
    }
	
	private boolean isValidAvailableSeats(String airline) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean isValidAirline(String airline) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean isValidFare(String fare) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean isValidDepartureDate(String string) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean isValidDestination(String destinationid) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean isValidSource(String sourceid) {
		// TODO Auto-generated method stub
		return true;
	}

	public Boolean isValidFlightId(String flightid)
    {   Boolean b1=false;
        String regex1 = "^[a-zA-Z0-9]{4,15}+$";
        Pattern pattern1 = Pattern.compile(regex1);
         Matcher matcher1 = pattern1.matcher(flightid);
          if(matcher1.matches())
         b1=true;
          return b1;
    }

	public String deleteFlights(String flightId) throws Exception {
		// TODO Auto-generated method stub
		FlightEntity flightEntity=flightRepository.findOne(flightId);
		if(flightEntity==null) throw new FlightNotAvailableException("RegistrationService.Flight_NOTAVAILABLE");
		flightRepository.delete(flightEntity);
		return "Successfully deleted";
	}

	public String updateFlights(SearchFlights searchFlights) throws Exception {
		// TODO Auto-generated method stub
		FlightEntity flightEntity=flightRepository.findOne(searchFlights.getFlightId());
		if(flightEntity==null) throw new FlightNotAvailableException("RegistrationService.Flight_NOTAVAILABLE");
		flightEntity.setAirline(searchFlights.getAirline());
		flightEntity.setSourceid(searchFlights.getSourceid());
		flightEntity.setDepartureDate(searchFlights.getDepartureDate());
		flightEntity.setDestinationid(searchFlights.getDestinationid());
		flightEntity.setAvailableSeats(searchFlights.getAvailableSeats());
		flightEntity.setFare(searchFlights.getFare());
		flightRepository.save(flightEntity);
		return "Successfully updated";
	}

}
