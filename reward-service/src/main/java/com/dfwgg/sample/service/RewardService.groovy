package com.dfwgg.sample.service

import com.dfwgg.sample.model.RecordPointsRequest;
import com.dfwgg.sample.model.UserRewards;

interface RewardService {

	
	UserRewards recordUserPointEvent(RecordPointsRequest request)
	UserRewards getUserRewardRecords(String userName)
}
