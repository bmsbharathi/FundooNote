package com.bridgeit.note.service;
 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.bridgeit.note.dao.UserMapper;
import com.bridgeit.note.model.Login;
import com.bridgeit.note.model.User;

@Service
public class UtilityService {

	@Autowired
	UserMapper userMapperImpl;

	public void insertuser(User user) {
		System.out.println("insertuser");
		userMapperImpl.insertUser(user);
		System.out.println("insertuser");
	}

	public User getUser(Login user) {
		
		System.out.println("Inside UserService - GetUser()");
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
		System.out.println();
	}

	public User checkUserByEmail(String email) {
		User reg = userMapperImpl.checkUserByEmail(email);
		System.out.println(reg);
		return reg;
	}

}
