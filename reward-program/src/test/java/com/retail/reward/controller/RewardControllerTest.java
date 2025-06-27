package com.retail.reward.controller;

import com.retail.reward.service.RewardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration Test for RewardController using MockMvc.
 */
@WebMvcTest(RewardController.class)
public class RewardControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RewardService rewardService;

	@Test
	void testGetRewardsData_returnsJson() throws Exception {
		Map<String, Object> dummyResponse = new HashMap<>();
		Map<String, Object> customer1 = new HashMap<>();
		customer1.put("name", "TestUser");
		customer1.put("monthlyPoints", Map.of("JANUARY", 100));
		customer1.put("totalPoints", 100);
		dummyResponse.put("CID01", customer1);
		when(rewardService.getRewardsData()).thenReturn(dummyResponse);
		mockMvc.perform(get("/api/getRewardsData")).andExpect(status().isOk())
				.andExpect(jsonPath("$.CID01.name").value("TestUser"))
				.andExpect(jsonPath("$.CID01.totalPoints").value(100));
	}

	/**
	 * @throws Exception in Invalid endpoint scenario
	 */
	@Test
	void testInvalidEndpoint_returnsNotFound() throws Exception {
		mockMvc.perform(get("/api/invalid")).andExpect(status().isNotFound());
	}

	/**
	 * @throws Exception in Empty response scenario
	 */
	@Test
	void testGetRewardsData_whenEmpty_returnsEmptyJson() throws Exception {
		when(rewardService.getRewardsData()).thenReturn(Collections.emptyMap());
		mockMvc.perform(get("/api/getRewardsData")).andExpect(status().isOk()).andExpect(content().string("{}"));
	}
}
