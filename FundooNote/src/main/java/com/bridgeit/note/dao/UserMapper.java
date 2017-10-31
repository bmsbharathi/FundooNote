package com.bridgeit.note.dao;

import org.springframework.stereotype.Component;

import com.bridgeit.note.model.Login;
import com.bridgeit.note.model.User;

@Component
public interface UserMapper {

	public void insertUser(User user);

	public User getUser(Login user);

	public User checkUser(String email);

	public void updateUser(String password, String usernameupdation);

	public User checkUserByEmail(String email);
}
