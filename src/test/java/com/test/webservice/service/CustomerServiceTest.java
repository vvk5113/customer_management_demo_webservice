package test.java.com.test.webservice.service;

import java.util.ArrayList;
import java.util.List;

import com.test.webservice.model.Customer;
import com.test.webservice.model.CustomerService;

import main.java.com.test.webservice.constants.Constants;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerServiceTest {
		
	@Mock
	private CustomerService customerService;
	
	@Test
	public void getAllCustomersTest()
	{
	    Customer customer1 = new Customer("Test User1", "test@gmail.com", "43241234432");
		Customer customer2 = new Customer("Test User2", "test1@gmail.com", "6743734543");
		
		List<Customer> customersList = new ArrayList<Customer>();
		customersList.add(customer1);
		customersList.add(customer2);
		
		Mockito.when(customerService.getAllCustomers()).thenReturn(customersList);
		
        assertNotNull(customersList);
	}
	
	@Test
	public void getCustomerByEmailTest() {
		Customer customer = new Customer("Test User1", "test@gmail.com", "43241234432");
		
		Mockito.when(customerService.getCustomerByEmail("test@gmail.com")).thenReturn(customer);
		
		assertNotNull(customer);
	    assertEquals(customer, customerService.getCustomerByEmail("test@gmail.com"));
	}
	
	@Test
	public void addCustomerTest() 
	{
		Customer customer = new Customer("Test User1", "test@gmail.com", "43241234432");   
		
		Mockito.when(customerService.addCustomer(customer)).thenReturn(Constants.REGISTER_SUCCESS);
		
	    assertEquals(Constants.REGISTER_SUCCESS, customerService.addCustomer(customer));
	}
	
	@Test
	public void updateCustomerTest() 
	{
		Customer customer = new Customer("Test User1", "test@gmail.com", "43241234432");   
		
		Mockito.when(customerService.updateCustomer(customer)).thenReturn(Constants.UPDATE_SUCCESS);
		
	    assertEquals(Constants.UPDATE_SUCCESS, customerService.updateCustomer(customer));
	}
	
	@Test
	public void deleteCustomerTest() 
	{
		Customer customer1 = new Customer("Test User1", "test@gmail.com", "43241234432");
		Customer customer2 = new Customer("Test User2", "test1@gmail.com", "6743734543");
		
		List<Customer> customersList = new ArrayList<Customer>();
		customersList.add(customer1);
		customersList.add(customer2);
		
		Mockito.when(customerService.getAllCustomers()).thenReturn(customersList);		
		Mockito.when(customerService.deleteCustomer("test@gmail.com")).thenReturn(Constants.DELETE_SUCCESS);
		
	    assertEquals(Constants.DELETE_SUCCESS, customerService.deleteCustomer("test@gmail.com"));
	}
	
	

}