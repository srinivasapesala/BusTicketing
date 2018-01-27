package com.gap.irs1.flight.api;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gap.irs1.flight.entity.FlightEntity;
import com.gap.irs1.flight.model.SearchFlights;
import com.gap.irs1.flight.model.User;
import com.gap.irs1.flight.service.FlightService;
import com.gap.irs1.flight.service.RegistrationService;
import com.gap.irs1.flight.utility.CalendarUtility;
import com.gapirs1.flight.exception.FlightIdAlreadyPresentException;
import com.gapirs1.flight.exception.FlightNotAvailableException;
	@RestController
	@RequestMapping("BusAPI")
	public class FlightAPI {

		@Autowired
		private FlightService flightService;
		@Autowired
		private Environment environment;
		@Autowired
		private RegistrationService registrationService;

		@RequestMapping(value = "/{source}/{destination}/{journeyDate}", method = RequestMethod.GET,headers="Accept=application/json")
		public ResponseEntity<List<SearchFlights>> searchFlights(@PathVariable String source, @PathVariable String destination,
				@PathVariable String journeyDate	) throws Exception{
			System.out.println("Inside Surce and dest with date");
			Calendar jDate = CalendarUtility.getCalendarFromString(journeyDate);
			System.out.println("Printing the date output in the Console");
			System.out.println(jDate);
			List<SearchFlights> availableFlights = flightService.getFlights(source, destination, jDate);
			//List<FlightEntity> availableFlights = flightService.getFlights(source, destination, jDate);
			//List<SearchFlights> availableFlights=null;
			//availableFlights.add("Hyderabad","Bangalore","jDate");
			return new ResponseEntity<List<SearchFlights>>(availableFlights,HttpStatus.OK);
	}
		
		@RequestMapping(value = "/{source}/{destination}", method = RequestMethod.GET,headers="Accept=application/json")
		public ResponseEntity<List<SearchFlights>> searchFlights(@PathVariable String source, @PathVariable String destination,ModelMapper map) throws Exception{
			System.out.println("Inside Surce and dest");
			List<SearchFlights> availableFlights = flightService.getFlights(source, destination);
			return new ResponseEntity<List<SearchFlights>>(availableFlights,HttpStatus.OK);
		}
		
		@RequestMapping(value = "/AvailableBuses", method = RequestMethod.GET,headers="Accept=application/json")
		public ResponseEntity<List<SearchFlights>> searchFlights1() throws Exception{
			List<SearchFlights> availableFlights = flightService.getFlights();
			return new ResponseEntity<List<SearchFlights>>(availableFlights,HttpStatus.OK);
		}
		
		@RequestMapping(value = "/AddBus", method = RequestMethod.PUT,headers="Accept=application/json")
		public ResponseEntity<Void> addFlights(@RequestBody SearchFlights searchFlights){
			System.out.println("Inside Add flights Methos");
			
			ModelAndView modelAndView = new ModelAndView();
				try{
					System.out.println("Inside try block");
							
					FlightEntity f=new FlightEntity();
					f.setAirline(searchFlights.getAirline());
					f.setAvailableSeats(searchFlights.getAvailableSeats());
					String sDate=searchFlights.getDepartureDate();
					System.out.println(sDate);
					f.setDepartureDate(searchFlights.getDepartureDate());
					f.setDestinationid(searchFlights.getDestinationid());
					f.setFare(searchFlights.getFare());
					f.setSourceid(searchFlights.getSourceid());
					f.setFlightId(searchFlights.getFlightId());
					System.out.println("Before triggerring book tickets");
					//Calendar jDate=CalendarUtility.getCalendarFromString(searchFlights.getDepartureDate());
					//String message = registrationService.bookTickets(searchFlights);
					//System.out.println(message);
					String flightRegistration=flightService.addFlights(searchFlights);
					System.out.println(flightRegistration); 
					//modelAndView= new ModelAndView("register", "command", searchFlights);
					modelAndView.addObject("message",environment.getProperty("RegistrationController.SUCCESSFUL_REGISTRATION"));
					return new ResponseEntity<Void>(HttpStatus.ACCEPTED.OK);
					
				}catch(FlightIdAlreadyPresentException e){
				
					System.out.println("Inside FlightId Catch Exception");
					if (e.getMessage().contains("RegistrationService.FlightID_PRESENT")) {
						
						//System.out.println("Inside In method"+environment.getProperty(e.getMessage()));//y)e.getMessage().contains("RegistrationService.FlightID_PRESENT"));
						//modelAndView = new ModelAndView("register"); 
					//	modelAndView.addObject("command",searchFlights);
						//modelAndView.addObject("message", environment.getProperty(e.getMessage()));
					return new ResponseEntity<Void>(HttpStatus.FOUND);
						
					}
				} 
				catch(FlightNotAvailableException e){
					
					System.out.println("Inside FlightId Catch Exception");
					if (e.getMessage().contains("RegistrationService.Flight_NOTAVAILABLE")) {
						
						System.out.println("Inside In method"+environment.getProperty(e.getMessage()));//y)e.getMessage().contains("RegistrationService.FlightID_PRESENT"));
						//modelAndView = new ModelAndView("register"); 
					//	modelAndView.addObject("command",searchFlights);
						//modelAndView.addObject("message", environment.getProperty(e.getMessage()));
					return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
						
					}
				} 
				catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Inside Flight API Exception Method"+  environment.getProperty(e.getMessage()));
					
					return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
				} 
			
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED.OK);
		}
			
			 
		@RequestMapping(value = "/UpdateBus", method = RequestMethod.PATCH,headers="Accept=application/json")
		public ResponseEntity<Void> updateBus(@RequestBody SearchFlights searchFlights, BindingResult result,
				ModelMap model){
			try {
				String deleteflight=flightService.updateFlights(searchFlights);
			} 
			catch(FlightNotAvailableException e){
				
				System.out.println("Inside FlightId Catch Exception");
				if (e.getMessage().contains("RegistrationService.Flight_NOTAVAILABLE")) {
					
					System.out.println("Inside In method"+environment.getProperty(e.getMessage()));//y)e.getMessage().contains("RegistrationService.FlightID_PRESENT"));
					//modelAndView = new ModelAndView("register"); 
				//	modelAndView.addObject("command",searchFlights);
					//modelAndView.addObject("message", environment.getProperty(e.getMessage()));
				return new ResponseEntity<Void>(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
					
				}
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

		@RequestMapping(value = "/DeleteBus/{flightId}", method = RequestMethod.DELETE,headers="Accept=application/json")
		public ResponseEntity<Void> deleteBus(@PathVariable String flightId){
			try {
				String deleteflight=flightService.deleteFlights(flightId);
			} 
			catch(FlightNotAvailableException e){
				
				System.out.println("Inside FlightId Catch Exception");
				if (e.getMessage().contains("RegistrationService.Flight_NOTAVAILABLE")) {
					
					System.out.println("Inside In method"+environment.getProperty(e.getMessage()));//y)e.getMessage().contains("RegistrationService.FlightID_PRESENT"));
					//modelAndView = new ModelAndView("register"); 
				//	modelAndView.addObject("command",searchFlights);
					//modelAndView.addObject("message", environment.getProperty(e.getMessage()));
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
					
				}
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		@RequestMapping(value = "/source", method = RequestMethod.GET,headers="Accept=application/json")
		public ResponseEntity<SearchFlights> getSources(@PathVariable String source) throws Exception{
			try{
			SearchFlights s1=flightService.getSources(source);			
			return new ResponseEntity<SearchFlights>(s1,HttpStatus.OK);}
			catch(Exception e){
				e.printStackTrace();
				throw e;
			}
		}
		
		@RequestMapping(value = "/destination", method = RequestMethod.GET,headers="Accept=application/json")
		public ResponseEntity<List<SearchFlights>> getDestinations(@PathVariable String destination) throws Exception{
			List<SearchFlights> s2=flightService.getDestinations(destination);
			return new ResponseEntity<List<SearchFlights>>(s2,HttpStatus.OK);
		}
	}
