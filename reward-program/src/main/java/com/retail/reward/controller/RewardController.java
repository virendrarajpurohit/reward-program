package com.retail.reward.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.reward.service.RewardService;

/**
 * Reward Controller API
 */
@RestController
@RequestMapping("/api")
public class RewardController {

	@Autowired
	private RewardService rewardService;

	/**
	 * 
	 * @return reward data per customer
	 */
	@GetMapping("/getRewardsData")
	public Map<String, Object> getRewardsData() {
		return rewardService.getRewardsData();
	}
}
