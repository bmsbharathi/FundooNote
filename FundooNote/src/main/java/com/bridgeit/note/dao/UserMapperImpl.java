package com.bridgeit.note.dao;

import org.apache.ibatis.session.SqlSession; 
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.bridgeit.note.model.Login;
import com.bridgeit.note.model.User;
import com.bridgeit.note.mybatisutility.MyBatisUtil;

@Repository
public class UserMapperImpl implements UserMapper{

	private final Logger log = Logger.getLogger(UserMapperImpl.class);

	public void insertUser(User user) {

		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		log.warn("Inside UserMapperImpl - insertUser()");
		try {
			UserMapper usermapper = session.getMapper(UserMapper.class);
			System.out.println(usermapper);
			usermapper.insertUser(user);
			session.commit();
		} finally {
			session.close();
		}
	}

	public User getUser(Login user) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		System.out.println(session);
		try {
			UserMapper usermapper = session.getMapper(UserMapper.class);
			User reg = usermapper.getUser(user);
			System.out.println(reg);
			session.commit();
			return reg;
		} finally {
			session.close();
		}

	}

	public User checkUser(String uname) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		System.out.println(session);
		try {
			UserMapper usermapper = session.getMapper(UserMapper.class);
			User reg = usermapper.checkUser(uname);
			System.out.println(reg);
			session.commit();
			return reg;
		} finally {
			session.close();
		}
	}

	public void updateUser(String password, String usernameupdation) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		System.out.println(session);
		try {
			UserMapper usermapper = session.getMapper(UserMapper.class);
			usermapper.updateUser(password, usernameupdation);
			session.commit();

			// return reg;
		} finally {
			session.close();
		}
	}

	public User checkUserByEmail(String email) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		System.out.println(session);
		try {
			UserMapper usermapper = session.getMapper(UserMapper.class);
			User reg = usermapper.checkUserByEmail(email);
			System.out.println(reg);
			session.commit();
			return reg;
		} finally {
			session.close();
		}
	}

}
