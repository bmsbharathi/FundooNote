package com.bridgeit.note.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.note.dao.UserMapper;
import com.bridgeit.note.model.Login;
import com.bridgeit.note.model.User;

@Service
public class UtilityService {

	@Autowired
	private UserMapper userMapperImpl;

	private Logger logger = Logger.getLogger(UtilityService.class);

	public void insertuser(User user) {

		userMapperImpl.insertUser(user);
		logger.warn("insertuser");
	}

	public User getUser(Login user) {

		logger.warn("Inside UserService - GetUser()");
		User reg = userMapperImpl.getUser(user);
		return reg;
	}

	public User checkUser(String uname) {

		User reg = userMapperImpl.checkUser(uname);
		System.out.println(reg);
		return reg;
	}

	public void updateUser(String password, String usernameupdation) {

		userMapperImpl.updateUser(password, usernameupdation);
		logger.warn("UtiltiyService - updateUser()");
	}

	public User checkUserByEmail(String email) {

		User reg = userMapperImpl.checkUserByEmail(email);
		logger.warn("UtilityService - checkUserByEmail " + reg);
		return reg;
	}

}
