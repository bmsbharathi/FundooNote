package com.bridgeit.note.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.bridgeit.note.controller.UserController;
import com.bridgeit.note.json.Response;
import com.bridgeit.note.json.TokenResponse;
import com.bridgeit.note.model.Login;
import com.bridgeit.note.model.User;
import com.bridgeit.note.utility.EmailSchedulerUtility;
import com.bridgeit.note.utility.EmailUtility;
import com.bridgeit.note.utility.RedisUtility;
import com.bridgeit.note.utility.TokenizerUtility;
import com.bridgeit.note.validator.UserValidator;

@Service
public class BusinessService {

	@Autowired
	private UtilityService service;

	@Autowired
	private UserValidator formvalid;

	@Autowired
	private EmailUtility email;

	@Autowired
	private TaskExecutor taskExecutor;

	private Logger logger = Logger.getLogger(UserController.class);

	public TokenResponse loginUser(Login user) {

		logger.warn("inside Login Controller - loginUser()");
		User reg = service.getUser(user);
		logger.info("User details:" + reg);
		TokenResponse tokenobject = null;

		if (reg != null) {

			// Checking User credentials
			tokenobject = new TokenResponse();
			tokenobject.setUserstatus("Valid User");
			logger.info("Login Successfull");

			// Generating JWT Token
			String token = TokenizerUtility.getToken(reg);
			logger.info(token);

			// Storing into Redis
			logger.info("After sending to redis");
			logger.info(reg.getUserId());
			RedisUtility.sendtokenredis(reg.getUserId(), token);

			// Retrieving from Redis
			logger.info("After fetching the value from Redis");
			String redistoken = RedisUtility.gettokenfromredis(reg.getUserId());
			logger.info("Redis Final generation" + redistoken);
			tokenobject.setToken(token);

			// Setting the token in Header
			HttpHeaders headers = new HttpHeaders();
			headers.add("token", token);
			logger.info(headers.toString());

			logger.info("Logged in Successfully");

			return tokenobject;
		} else {
			
			tokenobject = new TokenResponse();
			logger.info("Login failed");
			tokenobject.setToken(null);
			tokenobject.setUserstatus("Invalid Credentials");

			return tokenobject;
		}

	}

	public Response registerUser(User user, BindingResult bindResult) {
		formvalid.validate(user, bindResult);

		Response resp = null;
		// If errors occur during validation
		if (bindResult.hasErrors()) {

			logger.info("has errors while validating");
			logger.info(bindResult.getFieldErrors().toString());
			resp = new Response();
			resp.setStatus(-1);
			resp.setMessage("Entered invalid details");
			return resp;
		}
		// If user is already registerd
		if (service.checkUserByEmail(user.getEmail()) != null) {

			logger.info("email already exists");
			resp = new Response();
			resp.setStatus(0);
			resp.setMessage("User already exist");
			return resp;
		}

		// Successful registration
		try {

			service.insertuser(user);
			logger.info("registered");
			taskExecutor.execute(new EmailSchedulerUtility(user, email));
			logger.info("Registered successfully");
			resp = new Response();
			resp.setStatus(1);
			resp.setMessage("User registered successfully!!!");
			/*
			 * String jsonStr = mapperObj.writeValueAsString(user);
			 * System.out.println(jsonStr + " After converting to json from Java Object");
			 */
			return resp;
		} catch (Exception e) {

			logger.info("EXCEPTION OCCURED");
			resp = new Response();
			resp.setStatus(-5);
			resp.setMessage("Internal server error");
			return resp;
		}
	}

}
