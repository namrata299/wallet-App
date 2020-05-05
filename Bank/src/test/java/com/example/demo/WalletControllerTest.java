package com.example.demo;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.example.dao.Itransaction;
import com.example.dao.Iwallet;
import com.example.model.Customer;
import com.example.model.Transaction;

import junit.framework.Assert;
import net.bytebuddy.asm.Advice.Local;
//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =WebEnvironment.DEFINED_PORT)
class WalletControllerTest {

	@LocalServerPort
	int definedport=9093;

	
	@Autowired
	Iwallet customerRepository;
	
	@Autowired
	Itransaction transactionRepository;
	
	@Test
	void contextLoads() {
	}
	 @Test
	public void testAddEmployeeSuccess() throws URISyntaxException 
	{
	        RestTemplate restTemplate = new RestTemplate();
	        final String baseUrl = "http://localhost:"+definedport+"/bank/customers/";
	        URI uri = new URI(baseUrl);
	        
	        Customer customer=new Customer(10001, 20001, "Mohini", "Pune", 876549562, "mohini16@gmail.com", 0, null);
	        ResponseEntity<String> result = restTemplate.postForEntity(uri, customer, String.class);
	         
	        System.out.println(result);
	        //Verify request succeed
	        Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	 @Test
	 public void testGetCustomerListSuccess() throws URISyntaxException 
	 {
	     RestTemplate restTemplate = new RestTemplate();
	      
	     final String baseUrl = "http://localhost:" + definedport +"/bank/customers/";
	     URI uri = new URI(baseUrl);
	  
	     ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	      System.out.println(result);
	     //Verify request succeed
	     Assert.assertEquals(200, result.getStatusCodeValue());
	   //  Assert.assertEquals(true, result.getBody().contains("employeeList"));
	 }
	@Test
	public void testGetcustomer() throws URISyntaxException
	{
		
		TestRestTemplate restTemplate=new TestRestTemplate();
		final String baseURL="https://localhost:"+definedport+"/bank/customers/20001";
		final String baseURL2="https://localhost:"+definedport+"/bank/customers/";
		URI uri=new URI(baseURL);
		URI uri2=new URI(baseURL2);
		Customer customer=new Customer(10001, 20001, "Mohini", "Pune", 876549562, "mohini16@gmail.com", 0, null);
		ResponseEntity<String> result = restTemplate.postForEntity(uri2, customer, String.class);
         
		ResponseEntity<String>  result1=restTemplate.getForEntity(uri, String.class);
		
		Assert.assertNotNull(result1);
		//Assert.assertEquals(200,result1.getStatusCodeValue());
		//Assertions.assertls(20001, result.getAccountNumber());
		
		
	}
}
