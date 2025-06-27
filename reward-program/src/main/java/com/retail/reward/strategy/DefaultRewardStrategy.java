package com.retail.reward.strategy;

public class DefaultRewardStrategy implements RewardCalculationStrategy {

	/**
	 * @param amount the transaction amount
	 * @return calculated reward points based on transaction amount
	 */

	@Override
	public int calculateRewardPoints(double amount) {
		if (amount <= 50)
			return 0;
		if (amount <= 100)
			return (int) (amount - 50);
		return 50 + (int) ((amount - 100) * 2);
	}

}
