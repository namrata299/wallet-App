package com.example.model;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="customer_master")
public class Customer {
	
	@Column(name="customerId")
	private Integer customerId;
	@Id
	@Column(name="accountNumber")
	private long accountNumber;
	@Column(name="name")
	private String name;
	@Column(name="address")
	private String address;
	@Column(name="contactNumber")
	private long contactNumber;
	@Column(name="emailId")
	private String emailId;
	@Column(name="balance")
	private double balance;

	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="accountNumber")
	List<Transaction> transaction=new ArrayList<Transaction>();
	
	
	/*
	 * customerId accno name address contactNumber emailId balance
	 */
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Integer customerId,long accno,String name,String address, long contactNumber, String emailId, double balance,
			List<Transaction> transactions) {
		this.customerId=customerId;
		this.accountNumber=accno;
		this.name=name;
		this.address=address;
		this.contactNumber=contactNumber;
		this.emailId=emailId;
		this.balance=balance;
		this.transaction=transactions;
	}
	
	public void addTransaction(Transaction T)
	{
		transaction.add(T);
		
		
	}
	public void removeTransaction(Transaction T)
	{
		
		transaction.remove(T);
	}
	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", address=" + address + ", contactNumber="
				+ contactNumber + ", emailId=" + emailId + ", balance=" + balance + ", accountNumber=" + accountNumber
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (contactNumber ^ (contactNumber >>> 32));
		result = prime * result + customerId;
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (contactNumber != other.contactNumber)
			return false;
		if (customerId != other.customerId)
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
