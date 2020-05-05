package com.example.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.Itransaction;
import com.example.dao.Iwallet;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Customer;
import com.example.model.Transaction;


@Service("walletService")
public class walletServiceImpl implements WalletServiceInterface {

	@Autowired
	private Iwallet walletDAO;

	@Autowired
	private Itransaction transactionRepo;
	
	
	
	@Override
	public Customer add(Customer t) {

		return walletDAO.save(t);

	}

	@Override
	public List<Customer> getAll() {
		List<Customer> list = new ArrayList<>();
		walletDAO.findAll().forEach(e -> list.add(e));
		return list;
		
	}
	

	@Override
	public Customer update(Customer t) {

		walletDAO.save(t);
		return t;
	}

	
	@Override
	public void deleteCustomer(Long custID) {
		Customer customer=walletDAO.getOne(custID);
		
		walletDAO.delete(customer);
	}

	@Override
	public Optional<Customer> getByIdentity(Long custID) {
		
		return walletDAO.findById(custID);
	}


	@Override
	public Customer showBalance(Long accNo) {
		Map<String,Double> list = new HashMap();
		Customer cust=walletDAO.getOne(accNo);
		double bal=cust.getBalance();
		String name="Available Balance :";
		list.put(name, bal);
		return cust;
	}

	@Override
	public Customer fundTransfer(Customer sender, Customer receiver,int amount){
		
		Long accNo1=sender.getAccountNumber();
		Long accNo2=receiver.getAccountNumber();
	
		double bal1=sender.getBalance()-amount;
		double bal2=receiver.getBalance()+amount;
		String tranid="W"+LocalDateTime.now();
		
		sender.setBalance(bal1);
		receiver.setBalance(bal2);
		final Customer updatedAccount = walletDAO.save(sender);
		final Customer updatedAccount2 = walletDAO.save(receiver);
		
		return sender;
		
	}

	@Override
	public Customer depositMoney(long accNo, int amount) {
		
		Customer cust = walletDAO.findById(accNo).get();
			       
			System.out.println(cust);
					double newBal1=cust.getBalance()+amount;
					cust.setBalance(newBal1);
					System.out.println(cust);
				final Customer updatedAccount = walletDAO.save(cust);
				String tranid="W"+LocalDateTime.now();
				
				Transaction T=new Transaction(accNo,"credit",amount,LocalDateTime.now(),tranid,newBal1);
				//cust.addTransaction(T);
				walletDAO.save(cust);
				transactionRepo.save(T);
				
				return updatedAccount;
				
		
		
	}

	@Override
	public Customer withdrawMoney(Long accNo, Integer amount) {
		Customer cust = walletDAO.findById(accNo).get();
	       
		System.out.println(cust);
				double newBal1=cust.getBalance()-amount;
				cust.setBalance(newBal1);
				System.out.println(cust);
			final Customer updatedAccount = walletDAO.save(cust);
			String tranid="W"+LocalDateTime.now();
			
			Transaction T=new Transaction(accNo,"debit",amount,LocalDateTime.now(),tranid,newBal1);
			//cust.addTransaction(T);
			walletDAO.save(cust);
			transactionRepo.save(T);
			
			return updatedAccount;
		
	}

	

	
	

	
}
