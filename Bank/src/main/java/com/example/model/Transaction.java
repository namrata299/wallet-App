package com.example.model;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.core.serializer.Serializer;
import org.xml.sax.ContentHandler;

import com.sun.org.apache.xml.internal.serializer.DOMSerializer;

//import org.springframework.data.annotation.Id;

@Entity
@Table(name="transaction_master")
public class Transaction {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(name="accountNumber")
	private Long accountNumber;
	@Column(name="details")
	private String details;
	@Column(name="amount")
	private double amount;
	@Column(name="trsanDateTime")
	private LocalDateTime trsanDateTime;
	@Column(name="transID")
	private String transID;
	@Column(name="senderAccNo")
	private Long senderAccNo;
	@Column(name="recvAccNo")
	private Long recvAccNo;
	@Column(name="nBalance")
	private double nBalance;
	
	
	@Override
	public String toString() {
		return "Transaction [accountNumber=" + accountNumber + ", details=" + details + ", amount=" + amount
				+ ", trsanDateTime=" + trsanDateTime + ", transID=" + transID + ", senderAccNo=" + senderAccNo
				+ ", recvAccNo=" + recvAccNo + ", nBalance=" + nBalance + ", toString()=" + super.toString() + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (Double.doubleToLongBits(nBalance) != Double.doubleToLongBits(other.nBalance))
			return false;
		if (recvAccNo == null) {
			if (other.recvAccNo != null)
				return false;
		} else if (!recvAccNo.equals(other.recvAccNo))
			return false;
		if (senderAccNo == null) {
			if (other.senderAccNo != null)
				return false;
		} else if (!senderAccNo.equals(other.senderAccNo))
			return false;
		if (transID == null) {
			if (other.transID != null)
				return false;
		} else if (!transID.equals(other.transID))
			return false;
		if (trsanDateTime == null) {
			if (other.trsanDateTime != null)
				return false;
		} else if (!trsanDateTime.equals(other.trsanDateTime))
			return false;
		return true;
	}
	public Transaction(Long senderAccNo, String details, Long recvAccNo,double amount, LocalDateTime trsanDateTime, String transID) {
		super();
		
		this.details = details;
		this.amount = amount;
		this.trsanDateTime = trsanDateTime;
		this.transID = transID;
		this.senderAccNo = senderAccNo;
		this.recvAccNo = recvAccNo;
		
	}
	public Transaction(Long accountNumber, String details, double amount, LocalDateTime trsanDateTime, String transID,
			double nBalance) {
		super();
		this.accountNumber = accountNumber;
		this.details = details;
		this.amount = amount;
		this.trsanDateTime = trsanDateTime;
		this.transID = transID;
		this.nBalance = nBalance;
	}
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDateTime getTrsanDateTime() {
		return trsanDateTime;
	}
	public void setTrsanDateTime(LocalDateTime trsanDateTime) {
		this.trsanDateTime = trsanDateTime;
	}
	public String getTransID() {
		return transID;
	}
	public void setTransID(String transID) {
		this.transID = transID;
	}
	public Long getSenderAccNo() {
		return senderAccNo;
	}
	public void setSenderAccNo(Long senderAccNo) {
		this.senderAccNo = senderAccNo;
	}
	public Long getRecvAccNo() {
		return recvAccNo;
	}
	public void setRecvAccNo(Long recvAccNo) {
		this.recvAccNo = recvAccNo;
	}
	public double getnBalance() {
		return nBalance;
	}
	public void setnBalance(double nBalance) {
		this.nBalance = nBalance;
	}


	
}
