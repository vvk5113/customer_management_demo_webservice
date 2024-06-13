package com.test.webservice.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.test.webservice.model.Customer;
import com.test.webservice.model.CustomerResponse;
import com.test.webservice.model.CustomerService;

import main.java.com.test.webservice.constants.Constants;


@RestController
public class CustomerController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	public CustomerService customerService;
	
	
	@RequestMapping(method = RequestMethod.POST, value="/customer/register")
	@ResponseBody
	public CustomerResponse addCustomer(@RequestBody Customer customer) {
		logger.info("CustomerController:addCustomer started >>>>>");
		
		CustomerResponse customerResponse = new CustomerResponse();   
		
		String validationResult = validateRequestPayload(customer);
		
		if(StringUtils.isNotBlank(validationResult)) {
			customerResponse.setResponseStatus(Constants.FAILURE);
	    	customerResponse.setErrorMessage(validationResult);
	    	return customerResponse;
		}
		
		String result = customerService.addCustomer(customer);
		
		if("Registration successful".equals(result)) {
			customerResponse.setFullName(customer.getFullName());
			customerResponse.setEmailAddress(customer.getEmailAddress());
			customerResponse.setPhoneNumber(customer.getPhoneNumber());
	        customerResponse.setResponseStatus(Constants.SUCCESS);
		} else {
			customerResponse.setResponseStatus(Constants.FAILURE);
			customerResponse.setErrorMessage("Customer registration is un-successful."); 
		}

        
        
        logger.info("CustomerController:addCustomer ended <<<<<");

        return customerResponse;
        
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/customer/allcustomers")
	@ResponseBody
	public List<Customer> getAllCustomers() {	
		logger.info("CustomerController:getAllCustomers started >>>>>");
		
		List<Customer> customers = customerService.getAllCustomers();
		
		logger.info("CustomerController:getAllCustomers ended <<<<<");
		
		return customers;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/customer/update")
	@ResponseBody
	public String updateCustomer(@RequestBody Customer customer) {		
		logger.info("CustomerController:updateCustomer started >>>>>");   
	    
		String result = customerService.updateCustomer(customer);		
	    
		logger.info("CustomerController:updateCustomer ended <<<<<");   
		
		return result;
	}	
		
	@RequestMapping(method = RequestMethod.DELETE, value="/customer/delete/{emailAddress}")
	@ResponseBody
	public String deleteCustomer(@PathVariable("emailAddress") String emailAddress) {		
		logger.info("CustomerController:deleteCustomer started >>>>>");     
		
	    String result = customerService.deleteCustomer(emailAddress);		
	    
	    logger.info("CustomerController:deleteCustomer ended >>>>>");     
	    
	    return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/customer/retrieve/{emailAddress}")
	@ResponseBody
	public CustomerResponse getCustomerByEmail(@PathVariable("emailAddress") String emailAddress) {		
		logger.info("CustomerController:getCustomerByEmail started >>>>>");  
		
	    Customer customer = customerService.getCustomerByEmail(emailAddress);		
	    CustomerResponse customerResponse = new CustomerResponse(); 
	    
	    if(customer != null) {
			customerResponse.setFullName(customer.getFullName());
			customerResponse.setEmailAddress(customer.getEmailAddress());
			customerResponse.setPhoneNumber(customer.getPhoneNumber());
	        customerResponse.setResponseStatus(Constants.SUCCESS);
	    } else {
	    	customerResponse.setResponseStatus(Constants.FAILURE);
	    	customerResponse.setErrorMessage("No customer information found for given email address.");
	    }
	    
	    logger.info("CustomerController:getCustomerByEmail ended <<<<<");  
	    
	    return customerResponse;
	    
	}
	
	private String validateRequestPayload(Customer customer) {
		logger.info("CustomerController:validateRequestPayload started >>>>>");  
		
		
		StringBuilder sb = new StringBuilder();
			
		EmailValidator validator = EmailValidator.getInstance();
		
		if(StringUtils.isNotBlank(customer.getFullName())) {
			Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
		    Matcher matcher = pattern.matcher(customer.getFullName());
		    if(!matcher.matches())
		    {
		    	sb.append("Customer name is not valid.");
		    }
		}
		
		if(StringUtils.isNotBlank(customer.getPhoneNumber())) {
			Pattern pattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
			Matcher matcher = pattern.matcher("+111 (202) 555-0125");
			if(!matcher.matches()) {
				sb.append("Customer phone number is not valid.");
			}
		}
				
		if (StringUtils.isBlank(customer.getEmailAddress())) {
			sb.append("Email address is required for customer.");
		} else if(!validator.isValid(customer.getEmailAddress())){
			sb.append("Invalid email address for customer.");
		}
		
		logger.info("CustomerController:validateRequestPayload ended <<<<<");
		
		return sb.toString();
		
	}

}
