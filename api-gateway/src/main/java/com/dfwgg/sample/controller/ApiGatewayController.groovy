package com.dfwgg.sample.controller

import static org.springframework.web.bind.annotation.RequestMethod.*

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

import com.dfwgg.sample.model.RecordPointsRequest
import com.dfwgg.sample.model.User
import com.dfwgg.sample.model.UserRewards;
import com.dfwgg.sample.service.ApiGatewayService

@RestController
@RequestMapping( "/api-gateway" )
class ApiGatewayController {
	
	@Autowired
	ApiGatewayService service
	
	@RequestMapping(value = '/user', method = POST)
	@ResponseBody
	ResponseEntity<User> createUser(@RequestBody User userIn){
		def user = service.createUser(userIn)
		if(user)
			 new ResponseEntity<User>(user,HttpStatus.CREATED)
		else
			 new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST)
		
	}
	
	@RequestMapping(value = '/reward/record', method = POST)
	@ResponseBody
	ResponseEntity<String> recordReward(@RequestBody RecordPointsRequest request){
		def result = service.recordReward(request)
		new ResponseEntity<String>(result,HttpStatus.OK)
	}
	
	@RequestMapping(value = '/user/rewards/{userName}', method = GET)
	@ResponseBody
	ResponseEntity<UserRewards> getUserRewards(@PathVariable String userName){
		def userReward = service.getUserRewards(userName)
		new ResponseEntity<UserRewards>(userReward,HttpStatus.OK)
	}

}
