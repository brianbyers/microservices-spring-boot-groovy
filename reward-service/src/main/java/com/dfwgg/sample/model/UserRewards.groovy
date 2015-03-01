package com.dfwgg.sample.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user_rewards")
class UserRewards {
	
	@Id
	String id
	String userName
	long totalPoints
	List<PointEvent> pointEvents
	
	UserRewards(){
		
	}

}
