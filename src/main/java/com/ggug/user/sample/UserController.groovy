package com.ggug.user.sample

import com.ggug.user.sample.model.User
import org.springframework.web.bind.annotation.*
import org.springframework.http.*
import static org.springframework.web.bind.annotation.RequestMethod.*

@RestController
class UserController {
	
	
	@RequestMapping(value="/sampleUser", method = GET)
	@ResponseBody 
	ResponseEntity<User> getSampleUser(){
		def user = new User()
		user.firstName = "Andrew"
		user.lastName = "Rubalcaba"
		user.phoneNumber = "2146214523"
		new ResponseEntity<User>(user,HttpStatus.OK)
	}
	
	@RequestMapping(value = '/user', method = POST)
	ResponseEntity addUser(@RequestBody User userIn){
		User.withTransaction{
			def user = new User(firstName: userIn.firstName, lastName: userIn.lastName, phoneNumber: userIn.phoneNumber).save()
			if(user)
				return new ResponseEntity(HttpStatus.CREATED)	
			else
				return new ResponseEntity(HttpStatus.BAD_REQUEST)
		}
	}
	
	@RequestMapping(value="/users", method = GET)
	List<User> getUsers(){
		User.findAll()
	}

}
