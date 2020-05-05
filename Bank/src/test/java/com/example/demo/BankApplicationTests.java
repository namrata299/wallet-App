package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dao.Itransaction;
import com.example.dao.Iwallet;
import com.example.model.Customer;
import com.example.model.Transaction;

import junit.framework.Assert;
//@RunWith(SpringRunner.class)
@SpringBootTest
class BankApplicationTests {

	@Autowired
	Iwallet customerRepository;
	
	@Autowired
	Itransaction transactionRepository;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void TestCustomerRespositoryCreate()
	{
		System.out.println("In Repository Testing");
		Customer customer = new Customer();
		customer.setAccountNumber(1001);
		customer.setCustomerId(1002);
		customer.setName("Namrata");
		customer.setAddress("Pune");
		customer.setEmailId("namrata@gmail.com");
	
		customerRepository.save(customer);
		
		System.out.println(customer);
		
		Assert.assertNotNull(customer.getAccountNumber());
	}
	@Test
	public void TestCustomerRepositoryFind()
	{
		Customer customer=customerRepository.findById((long) 1001).get();
		System.out.println(customer);
		Assert.assertNotNull(customer);
		
	}
	
	@Test
	public void TestCustomerRepositoryUpdate()
	{
		System.out.println("In Update Testing");
		Customer customer=customerRepository.findById((long) 1001).get();
		customer.setEmailId("namratakamble299@gmail.com");
		String actual=customer.getEmailId();
		System.out.println("Updates EMial ID"+actual);
		Assert.assertEquals("namratakamble299@gmail.com", actual);
	}
	
	@Test
	public void TransactionSave()
	{
		Transaction transaction=new Transaction();
		transaction.setAccountNumber((long) 1001);
		transaction.setDetails("Credit");
		String transId="W"+LocalDateTime.now();
		transaction.setTransID(transId);
		transaction.setAmount(1000);
		
		Assert.assertNotNull(transaction);
	}
	
	@Test
	public void FindTransaction(){
		
		Transaction transaction=new Transaction();
		Customer customer=customerRepository.findById((long) 1001).get();
	
		List list=customer.getTransaction();
		
		Assert.assertNotNull(list);
		
		
	}

}
