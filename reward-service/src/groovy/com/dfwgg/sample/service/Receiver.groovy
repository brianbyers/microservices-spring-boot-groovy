package com.dfwgg.sample.service

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

class Receiver {
	
	private static final Logger log = LoggerFactory.getLogger(Receiver.class)
	
	@Autowired
	RewardService rewardService;
	
	public void receiveMessageUserName(String userName){
		log.debug("receievedUserName: " + userName)
		rewardService.createEmptyUserRewardsRecord(userName)
	}

}
