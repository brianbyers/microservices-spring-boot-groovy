package com.dfwgg.sample.controller

import static org.springframework.web.bind.annotation.RequestMethod.*

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import com.dfwgg.sample.model.PointEvent
import com.dfwgg.sample.model.RecordPointsRequest
import com.dfwgg.sample.model.UserRewards
import com.dfwgg.sample.service.RewardService

@RestController
class RewardController {
	
	@Autowired
	RewardService rewardService
	
	@RequestMapping(value="/sampleRequest", method = GET)
	@ResponseBody
	RecordPointsRequest getRequestSample(){
		RecordPointsRequest request = new RecordPointsRequest()
		request.userName="arubalcaba"
		PointEvent pointEvent = new PointEvent()
		pointEvent.pointEventName ="TWITTER_ACTION"
		pointEvent.points = 100
		pointEvent.pointEventDate = new Date()
		request.pointEvent = pointEvent
		request
	}
	
	@RequestMapping(value="/reward/user/record", method = POST)
	@ResponseBody
	ResponseEntity<UserRewards> recordUserReward(@RequestBody RecordPointsRequest recordPointsRequest){
		def userReward = rewardService.recordUserPointEvent(recordPointsRequest)
		if(userReward)
			return new ResponseEntity<UserRewards>(userReward,HttpStatus.CREATED)
		else
			return new ResponseEntity<UserRewards>(HttpStatus.BAD_REQUEST)
	}
	
	@RequestMapping(value="/reward/user/{userName}", method = GET)
	@ResponseBody
	ResponseEntity<UserRewards> getUserRewardRecords(@PathVariable userName){
		def userReward = rewardService.getUserRewardRecords(userName)
		if(userReward)
			return new ResponseEntity<UserRewards>(userReward,HttpStatus.OK)
		else
		return new ResponseEntity<UserRewards>(null,HttpStatus.NO_CONTENT)
	}
	
	
	

}
