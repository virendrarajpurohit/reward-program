package com.retail.reward.model;

import java.util.List;

/**
 * Model class for representing Customer's data along with list of transactions
 */
public class Customer {

	private String customerId;
	private String name;
	private List<Transaction> transactions;

	public Customer(String customerId, String name, List<Transaction> transactions) {
		this.customerId = customerId;
		this.name = name;
		this.transactions = transactions;
	}

	/**
	 * @return customer ID
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @return customer name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return list of all transaction for the customer
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}

}
