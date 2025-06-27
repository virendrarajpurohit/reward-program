package com.retail.reward.strategy;

/**
 * Strategy interface for reward point calculation
 */
public interface RewardCalculationStrategy {

	/**
	 * @param amount the transaction amount
	 * @return calculated reward points per transaction
	 */
	int calculateRewardPoints(double amount);

}