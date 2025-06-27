package com.retail.reward.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the RewardService class
 */
public class RewardServiceTest {
	private final RewardService rewardService = new RewardService();

	@Test
	void testcalculateRewardPoints_below50_returnsZero() {
		int points = rewardServiceTestHelper(50); // 0 points
		assertEquals(0, points);
	}

	@Test
	void testcalculateRewardPoints_between50And100_returnsCorrectPoints() {
		int points = rewardServiceTestHelper(80); // 30 points
		assertEquals(30, points);
	}

	@Test
	void testcalculateRewardPoints_above100_returnsCorrectPoints() {
		int points = rewardServiceTestHelper(120); // 50 (51–100) + 40 (2*20) = 90 points
		assertEquals(90, points);
	}

	@Test
	void testcalculateRewardPoints_exactly100() {
		int points = rewardServiceTestHelper(100); // 50 points
		assertEquals(50, points);
	}

	@Test
	void testcalculateRewardPoints_zeroAmount() {
		int points = rewardServiceTestHelper(0);
		assertEquals(0, points);
	}

	private int rewardServiceTestHelper(double amount) {
		try {
			var method = RewardService.class.getDeclaredMethod("calculateRewardPoints", double.class);
			method.setAccessible(true);
			return (int) method.invoke(rewardService, amount);
		} catch (Exception e) {
			fail("Failed to access calculateRewardPoints method: " + e.getMessage());
			return -1;
		}
	}
}
