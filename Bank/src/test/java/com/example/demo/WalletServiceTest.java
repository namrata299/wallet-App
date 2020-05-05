package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dao.Itransaction;
import com.example.dao.Iwallet;
import com.example.model.Customer;
import com.example.model.Transaction;
import com.example.service.WalletServiceInterface;

import junit.framework.Assert;

@SpringBootTest
public class WalletServiceTest {
	
	@Autowired
	WalletServiceInterface serviceRepository;
	
	@Autowired
	Itransaction transactionRepository;
	
	@Autowired
	Iwallet customerRepository;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void TestCustomerServiceCreate()
	{
		System.out.println("In Service Testing");
		Customer customer = new Customer();
		customer.setAccountNumber(30001);
		customer.setCustomerId(30002);
		customer.setName("Minakshi");
		customer.setAddress("Solapur");
		customer.setEmailId("minakshi@gmail.com");
		customer.setBalance(1000);
		Customer customer2=serviceRepository.add(customer);
		
		
		System.out.println(customer2);
		
		Assert.assertNotNull(customer.getAccountNumber());
	}
	@Test
	public void TestCustomerServiceFind()
	{
		Customer customer = new Customer();
		customer.setAccountNumber(30001);
		customer.setCustomerId(30002);
		customer.setName("Minakshi");
		customer.setAddress("Solapur");
		customer.setEmailId("minakshi@gmail.com");
		customer.setBalance(1000);
		serviceRepository.add(customer);
		
		Customer customer3=serviceRepository.getByIdentity((long) 30001).get();
		System.out.println(customer3);
		Assert.assertNotNull(customer3.getAccountNumber());
		
	}
	@Test
	public void TestCustomerServiceUpdate()
	{
		System.out.println("In Update Testing");
		Customer customer=serviceRepository.getByIdentity((long) 30001).get();
		customer.setEmailId("minakshi15@gmail.com");
		String actual=customer.getEmailId();
		System.out.println("Updates EMial ID"+actual);
		Assert.assertEquals("minakshi15@gmail.com", actual);
	}
	@Test
	public void TestGetAllCustomer()
	{
		List<Customer> customers=serviceRepository.getAll();
		
		System.out.println("List of Customer "+customers);
		Assert.assertNotNull(customers);
	}
	@Test
	public void Deposit()
	{
		System.out.println("In Deposit ");
		/*Customer customer=serviceRepository.getByIdentity((long) 30001).get();
		double balance=customer.getBalance();*/
		Customer customer = new Customer();
		customer.setAccountNumber(30001);
		customer.setCustomerId(30002);
		customer.setName("Minakshi");
		customer.setAddress("Solapur");
		customer.setEmailId("minakshi@gmail.com");
		customer.setBalance(1000);
		serviceRepository.add(customer);
		Customer customer3=serviceRepository.depositMoney((long)30001, 1000);
		double balance=customer.getBalance();
		double expected=balance+1000;
		Assert.assertEquals(expected, customer3.getBalance());
		//System.out.println(customer3.getBalance());
		//Assert.assertNotNull(customer3);
		
	}
	@Test
	public void Withdraw()
	{
		System.out.println("In Withdraw ");
		Customer customer = new Customer();
		customer.setAccountNumber(30001);
		customer.setCustomerId(30002);
		customer.setName("Minakshi");
		customer.setAddress("Solapur");
		customer.setEmailId("minakshi@gmail.com");
		customer.setBalance(1000);
		serviceRepository.add(customer);
		Customer customer3=serviceRepository.withdrawMoney((long)30001, 500);
		double balance=customer.getBalance();
		double expected=balance-500;
		Assert.assertEquals(expected, customer3.getBalance());
		
		
	}
	@Test
	public void FundTransfer()
	{
		System.out.println("In FundTransfer ");
		Customer customer = new Customer();
		customer.setAccountNumber(30001);
		customer.setCustomerId(30002);
		customer.setName("Minakshi");
		customer.setAddress("Solapur");
		customer.setEmailId("minakshi@gmail.com");
		customer.setBalance(1000);
		Customer sender=serviceRepository.add(customer);
		
		Customer customer1 = new Customer();
		customer1.setAccountNumber(30001);
		customer1.setCustomerId(30002);
		customer1.setName("Minakshi");
		customer1.setAddress("Solapur");
		customer1.setEmailId("minakshi@gmail.com");
		customer1.setBalance(1000);
		Customer receiver=serviceRepository.add(customer1);
		
		serviceRepository.fundTransfer(sender, receiver, 500);
		double expected=sender.getBalance()-500;
		System.out.println(expected);
		sender.setBalance(expected);
		receiver.setBalance(receiver.getBalance()+500);
		Assert.assertEquals(expected, sender.getBalance());
		
	}
	@Test
	public void ShowBalance()
	{
		System.out.println("In Show Balance");
		Customer customer=customerRepository.findById((long) 30001).get();
		double balance=customer.getBalance();
		System.out.println("Balance "+balance);
		
		Assertions.assertNotNull(balance);
	}
	@Test
	public void Delete()
	{
		System.out.println("In Delete");
		serviceRepository.deleteCustomer((long)30001);
	}
	
}
