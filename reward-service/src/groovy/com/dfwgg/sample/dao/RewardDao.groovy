package com.dfwgg.sample.dao

import org.springframework.data.mongodb.repository.MongoRepository

import com.dfwgg.sample.model.UserRewards

interface RewardDao  extends MongoRepository<UserRewards, String> {
   
	UserRewards findByUserName(String userName)

}
