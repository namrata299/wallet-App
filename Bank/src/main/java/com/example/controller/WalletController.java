package com.example.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.dao.Itransaction;
import com.example.dao.Iwallet;
import com.example.exception.LowBalanceException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Customer;
import com.example.model.Transaction;

import com.example.service.WalletServiceInterface;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/bank")
public class WalletController {


	@Autowired
	WalletServiceInterface walletService;
	
	@Autowired
	Iwallet walletRepo;
	@Autowired
	Itransaction transactionRepository;
	
	
	@RequestMapping(value="/hello")
	public String sayHello() {
		return "Hello World! from Bank Spring Framework!";
	}
	

	 @PostMapping("/customers")
		public Customer insertCustomer(@RequestBody Customer cust)
	 {
			Customer cust1=walletService.add(cust);
			return cust1;
			
		}
	 @GetMapping("/customers/{accNo}")
		public ResponseEntity<Customer> findCustomer(@PathVariable("accNo")Long accNo) throws ResourceNotFoundException{
		Customer customer= walletService.getByIdentity(accNo).
				orElseThrow(()->new ResourceNotFoundException("Customer Not Found for this Account Number::"+accNo));
				
		return ResponseEntity.ok().body(customer);
			
		}

	 @GetMapping("/customers")
		public ResponseEntity<List<Customer>> getAllCustomers(){
			List<Customer> customers= walletService.getAll();
			if(customers.isEmpty()) {
				return new ResponseEntity("Sorry! No Customer Found!", HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		}
	 @GetMapping("/customers/transactions/{accNo}")
	 public ResponseEntity<List<Transaction>> getTransaction(@PathVariable("accNo")Long accNo) throws ResourceNotFoundException
	 {
		
		 Customer cust=walletService.getByIdentity(accNo).orElseThrow(()->new ResourceNotFoundException("Customer Not Found for this Account Number::"+accNo));
		 List<Transaction> list=cust.getTransaction();
		
		return ResponseEntity.ok().body(list);
		 
	 }
	 @GetMapping("/customers/transactions")
	 public ResponseEntity<List<Transaction>> getALlTransaction()
	 {
		List<Transaction> transactions = new ArrayList<>();
		transactionRepository.findAll().forEach(transaction->transactions.add(transaction));
		
		
		
		return ResponseEntity.ok().body(transactions);
		 
	 }
	
	 @PutMapping("/customers/{accNo}")
		public ResponseEntity<Customer> updateCustomer(@PathVariable(value="accNo") Long accNo,
				@RequestBody Customer customerDetails) throws ResourceNotFoundException{
		 
		 Customer customer=walletService.getByIdentity(accNo).
				 orElseThrow(()->new ResourceNotFoundException("Customer Not Found for this Account Number::"+accNo));
		 
		 customer.setAddress(customerDetails.getAddress());
		 customer.setContactNumber(customerDetails.getContactNumber());
		 customer.setEmailId(customerDetails.getEmailId());
		 customer.setName(customerDetails.getName());
		 
			Customer customers= walletService.update(customer);
			
			return  ResponseEntity.ok().body(customers);
		}
	 @DeleteMapping
	 public ResponseEntity<String> deleteAllTransaction()
	 {
		 transactionRepository.deleteAll();
		 String msg="Deleted";
		 return ResponseEntity.ok().body(msg);
	 }
	 
	 @DeleteMapping("/customers/{custId}")
		public  Map<String, Boolean> deleteCustomer(@PathVariable("custId")Long custId) throws ResourceNotFoundException
	    {
			
		 
		 walletService.deleteCustomer(custId);
		
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}
	
	 @PutMapping("/customers/depositmoney/{accNo}/{amount}")
     public ResponseEntity<Customer> depositMoney(@PathVariable("accNo") Long  
		  accNo, @PathVariable("amount") Integer amount) throws ResourceNotFoundException
	{
		 System.out.println("In Deposit"); System.out.println(accNo+"   "+amount);
		  Customer customer =walletService.getByIdentity(accNo).orElseThrow(()->new ResourceNotFoundException("Account Not Found for Id" +accNo));
		 
		  Customer cust=walletService.depositMoney(accNo, amount);
		  
		 
		
		  return  ResponseEntity.ok().body(cust);
		  
   }  
	 @PutMapping("/customers/withdrawmoney/{accNo}/{amount}")
     public ResponseEntity<Customer> withdrawMoney(@PathVariable("accNo") Long
		  accNo, @PathVariable("amount") Integer amount) throws ResourceNotFoundException, LowBalanceException
	{
		 Customer customer =walletService.getByIdentity(accNo).orElseThrow(()->new ResourceNotFoundException("Account Not Found for Id" +accNo));
		 
		 double balance=customer.getBalance();
		  if(balance < amount)
		  {
			  throw new LowBalanceException("You Don't Have enough balance to withdraw Money",balance);
		  }
		  Customer customer2=walletService.withdrawMoney(accNo, amount);
		  
		 
		
		
		  return  ResponseEntity.ok().body(customer2);
   } 
		

	@GetMapping("/customers/showBalance/{accNo}")
	public  ResponseEntity<Double> showBalance(@PathVariable("accNo") Long accNo) throws ResourceNotFoundException
	{
		
		//Customer customer=walletService.showBalance(accNo);
		
		Customer cust =walletService.getByIdentity(accNo).orElseThrow(()->new ResourceNotFoundException("Account Not Found for Id" +accNo));
		double balance=cust.getBalance();
		
		
		return  ResponseEntity.ok().body(balance);
	}
	@PutMapping("/customers/fundtransfer/{accNo1}/{accNo2}/{amount}")
	public ResponseEntity<Customer> fund_transfer(@PathVariable("accNo1") Long accNo1,
			@PathVariable("accNo2") Long accNo2,
			@PathVariable("amount") int amount) throws ResourceNotFoundException, LowBalanceException
	{

		Customer sender=walletService.getByIdentity(accNo1).orElseThrow(()->new ResourceNotFoundException("Account Not Found for this id "+accNo1));
		Customer receiver=walletService.getByIdentity(accNo2).orElseThrow(()->new ResourceNotFoundException("Account Not Found for this id "+accNo2));
		
		

		if(sender.getBalance()<amount)
		{
			throw new LowBalanceException(" You Don't Have Enough Balance to send money", sender.getBalance());
		}
		Customer customer=walletService.fundTransfer(sender, receiver, amount);
		
		String tranid="W"+LocalDateTime.now();
		
		
		 Transaction tran1=new Transaction(accNo1, "debit", amount, LocalDateTime.now(), tranid,sender.getBalance());
		 Transaction tran2=new Transaction(accNo2, "credit", amount, LocalDateTime.now(), tranid,receiver.getBalance());
		 transactionRepository.save(tran1);
		 transactionRepository.save(tran2);
		
		
		
		return  ResponseEntity.ok().body(customer);
	}
	
}
