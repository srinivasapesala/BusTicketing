package com.gap.irs1.flight.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gap.irs1.flight.model.BookFlights;
import com.gap.irs1.flight.model.SearchFlights;
import com.gap.irs1.flight.model.User;
import com.gap.irs1.flight.service.RegistrationService;
import com.gapirs1.flight.exception.FlightNotAvailableException;

@RestController
@RequestMapping("BusLogin")
public class UserLogin {
    @Autowired
	private RegistrationService registrationService;
    @Autowired
	private Environment environment;
    
    @RequestMapping(value ="/userAuthentication", method = RequestMethod.POST)
	public ResponseEntity<String> userAuthentication(@RequestBody User user,BindingResult result,
			ModelMap model)
	{
			
		//String poNumber=request.getParameter("poNumber");
		//String product=user.getUserId();
		Boolean message = registrationService.validateUserNameAndPassword(user);//postService.prdBamToNewStyleValidation(product);
		ModelAndView modelAndView=new ModelAndView("NewStyleMessage");
		modelAndView.addObject("message",message);
		if(message) {
		return new ResponseEntity<String>(HttpStatus.OK);
		}else {
		return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
    
    @RequestMapping(value ="/RegisterUser", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@Valid @RequestBody User user, BindingResult result,
			ModelMap model)
	{
			
		//String poNumber=request.getParameter("poNumber");
		//String product=user.getUserId();
		String message;
		ModelAndView modelAndView=new ModelAndView("NewStyleMessage");
		try {
			message = registrationService.registerUser(user);
			System.out.println("Message"+message);
			
			//modelAndView.addObject("message",message);
			System.out.println("Testing time now");
			return new ResponseEntity<>(message,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.METHOD_FAILURE);
		}//postService.prdBamToNewStyleValidation(product);
		
		//return new ResponseEntity<>(message,HttpStatus.OK);
	}
    
    @RequestMapping(value ="/ticketBooking", method = RequestMethod.PUT)
	public ResponseEntity<Void> ticketBooking(@RequestBody BookFlights bookFlights)
	{
			
		//String poNumber=request.getParameter("poNumber");
		//String product=user.getUserId();
		String message;
		try {
			message = registrationService.bookTickets(bookFlights);
			System.out.println("Message"+message);
			ModelAndView modelAndView=new ModelAndView("NewStyleMessage");
			modelAndView.addObject("message",message);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} 
		
		catch (FlightNotAvailableException e) {
			// TODO Auto-generated catch block
			System.out.println("Flight not available Exception catch block");
			String message1=null;
			ModelAndView modelAndView=new ModelAndView("NewStyleMessage");
			modelAndView.addObject("Exception Occured",message1);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception catch block");
			e.printStackTrace();
			ModelAndView modelAndView=new ModelAndView("NewStyleMessage");
			//modelAndView.addObject("Exception Occured",message1);
			return new ResponseEntity<Void>(HttpStatus.BAD_GATEWAY);
			
		}//postService.prdBamToNewStyleValidation(product);
		
	
	}
    
}
