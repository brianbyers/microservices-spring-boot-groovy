package com.dfwgg.sample.service

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service;

import com.dfwgg.sample.dao.RewardDao
import com.dfwgg.sample.model.PointEvent
import com.dfwgg.sample.model.RecordPointsRequest
import com.dfwgg.sample.model.UserRewards

@Service
class RewardService {

	private static final Logger log = LoggerFactory.getLogger(RewardService.class)
	@Autowired
	RewardDao rewardDao 
	
     UserRewards recordUserPointEvent(RecordPointsRequest request){
		 log.debug("about to record Point Event")
		 UserRewards userRewardRecord = rewardDao.findByUserName(request.userName)
		 if(userRewardRecord){
			 userRewardRecord.setTotalPoints(userRewardRecord.getTotalPoints() + request.pointEvent.getPoints())
			 userRewardRecord.getPointEvents().add(request.pointEvent)
			 userRewardRecord = rewardDao.save(userRewardRecord)
		 }
		 else
		 {
			 UserRewards newUserRewardRecord = new UserRewards()
			 newUserRewardRecord.userName = request.userName
			 newUserRewardRecord.totalPoints = request.pointEvent.points
			 newUserRewardRecord.pointEvents = new ArrayList<PointEvent>()
			 newUserRewardRecord.pointEvents.add(request.pointEvent)
			 userRewardRecord =  rewardDao.save(newUserRewardRecord)
		 }
		 userRewardRecord
	 }
	 
	 UserRewards getUserRewardRecords(String userName){
		 UserRewards userRewardRecord = rewardDao.findByUserName(userName)
		 userRewardRecord
	 }
	 
    void createEmptyUserRewardsRecord(String userName){
		UserRewards userRewardRecord = rewardDao.findByUserName(userName)
		if(!userRewardRecord){
			log.debug("creating empty reward record")
			UserRewards emptyUserRewardRecord = new UserRewards()
			emptyUserRewardRecord.userName = userName
			emptyUserRewardRecord.totalPoints = 0
			emptyUserRewardRecord.pointEvents = new ArrayList<PointEvent>()
			rewardDao.save(emptyUserRewardRecord)
		}
		
		
	} 
}
