package com.bridgeit.note.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bridgeit.note.json.Response;
import com.bridgeit.note.json.TokenResponse;
import com.bridgeit.note.model.Login;
import com.bridgeit.note.model.User;
import com.bridgeit.note.service.BusinessService;

@Controller
public class UserController {

	@Autowired
	private BusinessService businessService;

	private Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping(value = "register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<Response> insertUser(@RequestBody User user, BindingResult bindResult) {

		logger.info("Entered insertUser()");
		Response resp = businessService.registerUser(user, bindResult);

		if (resp.getStatus() == -5)
			return new ResponseEntity<Response>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		else if (resp.getStatus() == 0)
			return new ResponseEntity<Response>(resp, HttpStatus.ALREADY_REPORTED);
		else if (resp.getStatus() == -1)
			return new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<TokenResponse> loginuser(@RequestBody Login user) {

		TokenResponse resp = businessService.loginUser(user);

		if (resp.getToken() != null) {

			HttpHeaders header = new HttpHeaders();
			header.add("token", resp.getToken());
			
			return new ResponseEntity<TokenResponse>(resp, HttpStatus.OK);
		} else

			return new ResponseEntity<TokenResponse>(resp, HttpStatus.BAD_REQUEST);
	}
}
