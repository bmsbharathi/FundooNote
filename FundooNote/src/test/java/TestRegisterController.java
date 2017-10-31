import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.bridgeit.note.json.Response;
import com.bridgeit.note.model.User;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

public class TestRegisterController {

	static User user1,user2;
	Logger logger = Logger.getLogger(TestRegisterController.class);

	@BeforeClass
	public static void setup() {

		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/FundooNote";

		user1 = new User();
		user1.setFullName("Bala");
		user1.setEmail("bmsbharathi@hotmail.com");
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

		System.out.println("testRegister user exists");
		Response resp = (Response) given().contentType("application/json").body(user1).when().post("fundooregister")
				.then().statusCode(200);
		System.out.println(resp);
	}

	@Test
	@Ignore
	public void insertUserWithoutAnyError() {

		logger.info("insertUserWithoutAnyError()");
		given().contentType("application/json").body(user1).when().post("/register").then().statusCode(200);
	}

	@Test
	@Ignore
	public void testInsertRegister() {

		logger.info("insert user");
		RestAssured.given().body(user2).contentType("application/json").when().post("fundooregister").then()
				.statusCode(200);
	}

}
