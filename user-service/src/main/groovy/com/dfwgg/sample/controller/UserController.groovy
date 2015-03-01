package com.dfwgg.sample.controller

import static org.springframework.web.bind.annotation.RequestMethod.*

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

import com.dfwgg.sample.dao.UserDao;
import com.dfwgg.sample.model.User

@RestController
class UserController {

	@Autowired
	UserDao userDaoRepo

	@RequestMapping(value="/sampleUser", method = GET)
	@ResponseBody
	ResponseEntity<User> getSampleUser(){
		def user = new User()
		user.firstName = "Andrew"
		user.lastName = "Rubalcaba"
		new ResponseEntity<User>(user,HttpStatus.OK)
	}

	@RequestMapping(value = '/user', method = POST)
	ResponseEntity<User> addUser(@RequestBody User userIn){
		def user = userDaoRepo.save(userIn)
		if(user)
			 new ResponseEntity<User>(user,HttpStatus.CREATED)
		else
			 new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST)
	}
	
	@RequestMapping(value = '/user', method = PUT)
	@ResponseBody
	ResponseEntity<User> updateUser(@RequestBody User userIn){
		def user = userDaoRepo.findOne(userIn.id)
		if(user)
		{
			user.firstName = userIn.firstName
			user.lastName = userIn.lastName
			userDaoRepo.save(userIn)
			new ResponseEntity<User>(user,HttpStatus.OK)
		}
		else
			new ResponseEntity<User>(null,HttpStatus.NOT_FOUND)
		
	}
	
	@RequestMapping(value="/user/{userName}", method = GET)
	@ResponseBody
	ResponseEntity<User> getUser(@PathVariable String userName){
		def user = userDaoRepo.findByUserName(userName)
		if(user)
		{
			new ResponseEntity<User>(user,HttpStatus.OK)
		}
		else
			new ResponseEntity<User>(null,HttpStatus.NOT_FOUND)
	}
	
	@RequestMapping(value="/users", method = GET)
	List<User> getUsers(){
		def userList = userDaoRepo.findAll()
		userList.toList()
	}
	
	

}
