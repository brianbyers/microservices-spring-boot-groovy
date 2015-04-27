package com.dfwgg.sample.service

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfwgg.sample.dao.UserDao;
import com.dfwgg.sample.model.User;

@Service
class UserService {
	
	@Autowired
	UserDao userDaoRepo
	
	final static String queueName = "reward-user-queue";
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	User insertUser(User userIn){
		def newUser = userDaoRepo.save(userIn)
		rabbitTemplate.convertAndSend(queueName, userIn.userName);
		newUser
	}

}
