package com.dfwgg.sample.dao

import org.springframework.data.repository.CrudRepository

import com.dfwgg.sample.model.User

interface UserDao extends CrudRepository<User, Long> {
	
	List<User> findByFirstName(String firstName)
	User findByUserName(String userName)

}
