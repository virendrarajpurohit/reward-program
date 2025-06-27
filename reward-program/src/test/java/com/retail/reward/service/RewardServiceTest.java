package com.retail.reward.service;

import org.junit.jupiter.api.Test;

import com.retail.reward.strategy.DefaultRewardStrategy;
import com.retail.reward.strategy.RewardCalculationStrategy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

/**
 * Unit tests for the RewardService class with actual reward calculation logic.
 */
public class RewardServiceTest {

	@Test
	void testcalculateRewardPoints_below50_returnsZero() {
		RewardCalculationStrategy rewardStrategy = new DefaultRewardStrategy();
		int points = rewardStrategy.calculateRewardPoints(50); // 0 points
		assertEquals(0, points);
	}

	@Test
	void testcalculateRewardPoints_between50And100_returnsCorrectPoints() {
		RewardCalculationStrategy rewardStrategy = new DefaultRewardStrategy();
		int points = rewardStrategy.calculateRewardPoints(80); // 30 points
		assertEquals(30, points);
	}

	@Test
	void testcalculateRewardPoints_above100_returnsCorrectPoints() {
		RewardCalculationStrategy rewardStrategy = new DefaultRewardStrategy();
		int points = rewardStrategy.calculateRewardPoints(120); // 50 (51–100) + 40 (2*20) = 90 points
		assertEquals(90, points);
	}

	@Test
	void testcalculateRewardPoints_exactly100() {
		RewardCalculationStrategy rewardStrategy = new DefaultRewardStrategy();
		int points = rewardStrategy.calculateRewardPoints(100); // 50 points
		assertEquals(50, points);
	}

	@Test
	void testcalculateRewardPoints_zeroAmount() {
		RewardCalculationStrategy rewardStrategy = new DefaultRewardStrategy();
		int points = rewardStrategy.calculateRewardPoints(0); // 0 points
		assertEquals(0, points);
	}

	/**
	 * Test case to verify correct reward points for one customer over multiple
	 * months.
	 */
	@Test
	void testRewardSummaryForSingleCustomer() {
		RewardService rewardService = new RewardService();
		Map<String, Object> result = rewardService.getRewardsData();
		assertTrue(result.containsKey("CID01"));
		Map<String, Object> customer1 = (Map<String, Object>) result.get("CID01");
		assertEquals("Virendra", customer1.get("name"));
		Map<String, Integer> monthly = (Map<String, Integer>) customer1.get("monthlyPoints");
		assertTrue(monthly.get("JANUARY") > 0);
		assertTrue(monthly.get("MARCH") > 0);
		assertEquals(3, monthly.size());
		assertEquals(365, customer1.get("totalPoints"));
	}

	/**
	 * Test to verify multiple transactions in same month are accumulated.
	 */
	@Test
	void testMultipleTransactionsSameMonthAreSummedCorrectly() {
		RewardService rewardService = new RewardService();
		Map<String, Object> result = rewardService.getRewardsData();
		Map<String, Object> customer2 = (Map<String, Object>) result.get("CID02");
		Map<String, Integer> monthly = (Map<String, Integer>) customer2.get("monthlyPoints");
		assertEquals(100, monthly.get("JANUARY"));
	}

}
