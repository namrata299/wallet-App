package com.example.exception;

public class LowBalanceException extends Exception {
	
	private double lowBalance;

	public LowBalanceException(String message, double lowBalance) {
		super(message);
		this.lowBalance = lowBalance;
	}

	@Override
	public String toString() {
		String message=super.getMessage();
		message+=" "+lowBalance;
		return " "+message;
	}
	

}
