package com.gap.irs1.flight.service;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gap.irs1.flight.entity.BookEntity;
import com.gap.irs1.flight.entity.FlightEntity;
import com.gap.irs1.flight.entity.UserEntity;
import com.gap.irs1.flight.model.BookFlights;
import com.gap.irs1.flight.model.SearchFlights;
import com.gap.irs1.flight.model.User;
import com.gap.irs1.flight.repository.BookRepository;
import com.gap.irs1.flight.repository.FlightRepository;
import com.gap.irs1.flight.repository.UserRepository;
import com.gap.irs1.flight.utility.CalendarUtility;
import com.gapirs1.flight.exception.FlightIdAlreadyPresentException;
import com.gapirs1.flight.exception.FlightNotAvailableException;
import com.gapirs1.flight.exception.InvalidCityException;
import com.gapirs1.flight.exception.InvalidEmailException;
import com.gapirs1.flight.exception.InvalidNameException;
import com.gapirs1.flight.exception.InvalidPasswordException;
import com.gapirs1.flight.exception.InvalidPhoneException;
import com.gapirs1.flight.exception.InvalidUserIdException;
import com.gapirs1.flight.exception.TicketNotAvailableException;
import com.gapirs1.flight.exception.UserIdAlreadyPresentException;

@Service
public class RegistrationService {
    @Autowired
	private UserRepository userRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private BookRepository bookRepository;
	public String registerUser(User user) throws Exception {
		
		validateUser(user);
		
		UserEntity ue=userRepository.findOne(user.getUserId());
		
		if(ue!=null) throw new UserIdAlreadyPresentException("RegistrationService.USERID_PRESENT");
        UserEntity userEntity=new UserEntity();
        userEntity.setCity(user.getCity());
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        userEntity.setPhone(user.getPhone());
        userEntity.setUserId(user.getUserId());
        userRepository.saveAndFlush(userEntity);
        System.out.println("Registering user");
		return "userRepository.Registration_Success";
	}
	
	public void validateUser(User user)throws Exception
    {
		
		System.out.println("user id is " +user.getUserId());
		System.out.println("PWD id is " +user.getPassword());
		System.out.println("name id is " +user.getName());
		System.out.println("email id is " +user.getEmail());
		System.out.println("City id is " +user.getCity());
		System.out.println("PhoneNumber  is " +user.getPhone());
		
		
        if(!isValidUserId(user.getUserId()))
            throw new InvalidUserIdException("RegistrationService.INVALID_USER_ID");
        if(!isValidPassword(user.getPassword()))
            throw new InvalidPasswordException("RegistrationService.INVALID_PASSWORD");
        if(!isValidName(user.getName()))
            throw new InvalidNameException("RegistrationService.INVALID_NAME");
        if(!isValidCity(user.getCity()))
            throw new InvalidCityException("RegistrationService.INVALID_CITY");
        if(!isValidEmail(user.getEmail()))
            throw new InvalidEmailException("RegistrationService.INVALID_EMAIL");
        if(!isValidPhoneNumber(user.getPhone()))
            throw new InvalidPhoneException("RegistrationService.INVALID_PHONE_NUMBER");
    }
	
	public Boolean isValidUserId(String userid)
    {
        Boolean b1=false;
        String regex1 = "^[a-zA-Z0-9]{4,15}+$";
         
        Pattern pattern1 = Pattern.compile(regex1);
         Matcher matcher1 = pattern1.matcher(userid);
          if(matcher1.matches())
         b1=true;
          return b1;
    }
	
	public Boolean isValidPassword(String password)
    {
        Boolean b1=false;
        String regex2 = "^[a-zA-Z0-9]{8,15}+$";
             
            Pattern pattern2 = Pattern.compile(regex2);
             Matcher matcher2 = pattern2.matcher(password);
              if(matcher2.matches())
                  b1=true;
              return b1;        
    }
	public Boolean isValidName(String name)
    {
        Boolean b1=false;
        String regex3 = "^[a-zA-Z0-9]{3,15}+$";
         
        Pattern pattern3 = Pattern.compile(regex3);
         Matcher matcher3 = pattern3.matcher(name);
          if(matcher3.matches())
              b1=true;
          return b1;
    }
	
	public Boolean isValidCity(String city)
    {
        Boolean b1=false;
        String regex4 = "^[a-zA-Z0-9]{4,30}+$";
         
        Pattern pattern4 = Pattern.compile(regex4);
         Matcher matcher4 = pattern4.matcher(city);
          if(matcher4.matches())
              b1=true;
          return b1;
          
    }
    
public Boolean isValidEmail(String email)
    {
        Boolean b1=false; 
        String regex5 = "^[A-Za-z0-9+_.-]+@(.+)$";
         
            Pattern pattern5 = Pattern.compile(regex5);
             Matcher matcher5 = pattern5.matcher(email);
              if(matcher5.matches())
                  b1=true;
              return b1;
    }
    
public Boolean isValidPhoneNumber(String number)
    {
        Boolean b1=false;     
          String regex6 = "[0-9]{10}";
             
            Pattern pattern6 = Pattern.compile(regex6);
             Matcher matcher6 = pattern6.matcher(number);
              if(matcher6.matches())
                  b1=true;              
              return b1;
    }
public Boolean validateUserNameAndPassword(User user)
{
    Boolean b1=false;    
    
     
    UserEntity ue=userRepository.validateCredentials(user.getUserId(),user.getPassword());
    
    
    if(ue!=null) {
    	b1=true;
    }
    return b1;
}

public String bookTickets(BookFlights bookFlights) throws Exception {
	
	System.out.println("Inside Booking method");
	System.out.println(bookFlights.getSourceid()+ ""+ bookFlights.getDestinationid()+ ""+bookFlights.getDepartureDate() );
   Calendar kDate=CalendarUtility.getCalendarFromString(bookFlights.getDepartureDate());
   System.out.println(kDate);
    BookEntity fe=bookRepository.bookingValidation(bookFlights.getSourceid(),bookFlights.getDestinationid(),kDate);
    System.out.println("AFter FLightBooking method"  +fe);
    if(fe==null)
    throw new FlightNotAvailableException("RegistrationService.Flight_NOTAVAILABLE");
    System.out.println("AFter Flight availability validation method");
    int tickets=fe.getAvailableSeats();
    if(tickets<bookFlights.getAvailableSeats()) throw new TicketNotAvailableException("RegistrationService.Tickets_NOTAVAILABLE");
    tickets=tickets-bookFlights.getAvailableSeats();
    fe.setAvailableSeats(tickets);
    bookRepository.save(fe);
    System.out.println("Flight Booked successfully");
    
    // TODO Auto-generated method stub
	return "RegistrationService Ok";
}
}
