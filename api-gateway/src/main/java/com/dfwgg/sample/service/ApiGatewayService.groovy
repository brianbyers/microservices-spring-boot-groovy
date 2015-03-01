package com.dfwgg.sample.service

import com.dfwgg.sample.model.RecordPointsRequest;
import com.dfwgg.sample.model.User;
import com.dfwgg.sample.model.UserRewards;

interface ApiGatewayService {

	User createUser(User user)
	String recordReward(RecordPointsRequest request)
	UserRewards getUserRewards(String userName)
}
