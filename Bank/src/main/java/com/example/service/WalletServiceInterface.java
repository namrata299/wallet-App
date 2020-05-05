package com.example.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Customer;
import com.example.model.Transaction;

public interface WalletServiceInterface {
	
	public Customer add(Customer t);
	public List<Customer> getAll();
	
	public Customer update(Customer t);
    public void deleteCustomer(Long custID);	
	Optional<Customer> getByIdentity(Long custID);
	public Customer showBalance(Long accNo);
    public Customer depositMoney(long accNo,int amount);
	public Customer withdrawMoney(Long accNo, Integer amount);
	public Customer fundTransfer(Customer cust1, Customer cust2, int amount) ;
	


}
