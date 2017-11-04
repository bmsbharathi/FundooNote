package com.bridgeit.note;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.bridgeit.note.model.User;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

public class TestRegisterController {

	private static User user1, user2;
	private static final Logger logger = Logger.getLogger(TestRegisterController.class);

	@BeforeClass
	public static void init() {

		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/FundooNote";

		user1 = new User();
		user1.setFullName("Bala");
		user1.setEmail("bms_bharathi@live.com");
		user1.setMobileNo("9597307529");
		user1.setPassword("bmss@123");

		user2 = new User();
		user2.setFullName("Luis Suarez");
		user2.setEmail("robo@gmail.com");
		user2.setMobileNo("472342");
		user2.setPassword("suarez");

	}

	@Test
	@Ignore
	public void testRegister() {

		logger.info("testRegister user exists");
		given().contentType("application/json").body(user1).when().post("/register").then().statusCode(200)
				.assertThat();
	}

	@Test
	@Ignore
	public void insertUserWithoutAnyError() {

		logger.info("insertUserWithoutAnyError()");
		given().contentType("application/json").body(user1).when().post("/register").then().statusCode(200)
				.assertThat();
	}

	@Test
	@Ignore
	public void testInsertRegister() {

		logger.info("insert user");
		given().body(user2).contentType("application/json").when().post("/register").then().statusCode(200)
				.assertThat();
	}

}
