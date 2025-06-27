package com.retail.reward.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.retail.reward.model.Customer;
import com.retail.reward.model.Transaction;

/**
 * Service class for handling the reward point calculation logic
 */
@Service
public class RewardService {

	/**
	 * @return map of monthly and total reward data per customer ID
	 */
	public Map<String, Object> getRewardsData() {
		List<Customer> customers = getDummyTransactions();
		Map<String, Object> response = new LinkedHashMap<>();
		for (Customer customer : customers) {
			Map<String, Integer> monthlyPoints = new LinkedHashMap<>();
			int totalPoints = 0;
			for (Transaction txn : customer.getTransactions()) {
				int points = calculateRewardPoints(txn.getAmount());
				String month = txn.getDate().getMonth().toString();
				monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);
				totalPoints += points;
			}
			Map<String, Object> customerData = new HashMap<>();
			customerData.put("name", customer.getName());
			customerData.put("monthlyPoints", monthlyPoints);
			customerData.put("totalPoints", totalPoints);
			response.put(customer.getCustomerId(), customerData);
		}
		return response;
	}

	/**
	 * @param amount transaction amount
	 * @return calculated reward points based on transaction amount
	 */
	private int calculateRewardPoints(double amount) {
		int points = 0;
		if (amount > 100) {
			points += (int) ((amount - 100) * 2);
			points += 50;
		} else if (amount > 50) {
			points += (int) (amount - 50);
		}
		return points;
	}

	/**
	 * @return list of dummy customer data with transactions
	 */
	private List<Customer> getDummyTransactions() {
		List<Customer> list = new ArrayList<>();
		Customer c1 = new Customer("CID01", "Virendra",
				Arrays.asList(new Transaction(120, LocalDate.of(2024, Month.JANUARY, 15)),
						new Transaction(75, LocalDate.of(2024, Month.FEBRUARY, 10)),
						new Transaction(200, LocalDate.of(2024, Month.MARCH, 5))));
		Customer c2 = new Customer("CID02", "Raj",
				Arrays.asList(new Transaction(120, LocalDate.of(2024, Month.JANUARY, 20)),
						new Transaction(60, LocalDate.of(2024, Month.JANUARY, 30)),
						new Transaction(90, LocalDate.of(2024, Month.FEBRUARY, 18)),
						new Transaction(130, LocalDate.of(2024, Month.MARCH, 9))));
		list.add(c1);
		list.add(c2);
		return list;
	}
}
