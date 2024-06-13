package com.test.webservice.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.test.webservice.model.Customer;

import main.java.com.test.webservice.constants.Constants;

@Service
public class CustomerService {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	private List<Customer> customersList;
	
	public CustomerService(){
		customersList = new ArrayList<Customer>();
	}
	
	public String addCustomer(Customer customer) {
		logger.info("CustomerService:addCustomer started >>>>>");
		customersList.add(customer);
		return Constants.REGISTER_SUCCESS;
	}
		
	public String updateCustomer(Customer customer) {
		logger.info("CustomerService:updateCustomer started >>>>>");
		
		for(int i=0; i<customersList.size(); i++)
        {
			Customer existingCustomer = customersList.get(i);
            if(existingCustomer.getEmailAddress().equals(customer.getEmailAddress())) {
            	customersList.set(i, customer); 
            	return Constants.UPDATE_SUCCESS;
            }
        }
		
		logger.info("CustomerService:addCustomer ended <<<<<");
		
		return Constants.UPDATE_UNSUCCESS;
		
	}
		
	public String deleteCustomer(String emailAddress) {
		logger.info("CustomerService:deleteCustomer started >>>>>");
		logger.info("Deleting customer information associated with email: "+emailAddress);
		
		for(int i=0; i<customersList.size(); i++)
        {
            Customer existingCustomer = customersList.get(i);
            if(existingCustomer.getEmailAddress().equals(emailAddress)) {
            	customersList.remove(i); 
            	return Constants.DELETE_SUCCESS;
            }
        }
		
		logger.info("CustomerService:deleteCustomer ended <<<<<");
		
		return Constants.DELETE_UNSUCCESS;
		
	}
	
	public Customer getCustomerByEmail(String emailAddress) {
		logger.info("CustomerService:getCustomerByEmail started >>>>>");
		logger.info("Retrieving customer information associated with email: "+emailAddress);
		
		System.out.println("Search Email 1: "+emailAddress);
		
		Customer customer = null;
		
		for(int i=0; i<customersList.size(); i++)
        {
            customer = customersList.get(i);
            if(emailAddress.equals(customer.getEmailAddress())) {
            	return customer;
            }
        }
		
		logger.info("CustomerService:getCustomerByEmail ended <<<<<");
		
		return customer;
		
	}

	public List<Customer> getAllCustomers() {
		logger.info("CustomerService:getAllCustomers started >>>>>");
		return customersList;
	}

}
