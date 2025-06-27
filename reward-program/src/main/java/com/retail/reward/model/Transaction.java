package com.retail.reward.model;

import java.time.LocalDate;

/**
 * Model class for representing Customer's single transaction
 */
public class Transaction {

	private double amount;
	private LocalDate date;

	public Transaction(double amount, LocalDate date) {
		this.amount = amount;
		this.date = date;
	}

	/**
	 * @return amount spent in transaction
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @return date of transaction
	 */
	public LocalDate getDate() {
		return date;
	}

}
