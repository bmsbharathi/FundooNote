import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.bridgeit.note.model.Login;
import com.bridgeit.note.model.Note;
import com.bridgeit.note.model.User;

import static io.restassured.RestAssured.given;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestLoginController {

	static Login user1;
	static Login user2;
	static Login user3, user4;
	static User reg1, reg2;
	static Note note1, note2, note3, note4, note5, note6;
	Logger logger = Logger.getLogger(TestRegisterController.class);

	@BeforeClass
	public static void setup() {

		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/FundooNote";

		user1 = new Login();
		user1.setEmail("satya@gmail.com");
		user1.setPassword("satya");

		user2 = new Login();
		user2.setEmail("bmsbharathi@gmail.com");
		user2.setPassword("bmss@123");

		note1 = new Note();
		reg1 = new User();
		reg1.setUser_id(11);
		note1.setTitle("hello hello");
		note1.setDescription("hi hi hih ih");
		note1.setLastaccessdate(new Date());
		note1.setUser(reg1);
		note1.setArchive(true);
		note1.setTrash(false);

		note2 = new Note();
		note2.setTitle("Criket");
		note2.setDescription("A bat and ball game in a ground");
		note2.setNotes_id(26);

		note3 = new Note();
		reg2 = new User();
		reg2.setUser_id(4);
		note3.setNotes_id(26);
		note3.setUser(reg2);

		note4 = new Note();
		note4.setNotes_id(8);

		note5 = new Note();
		reg2 = new User();
		reg2.setUser_id(1);
		note5.setUser(reg2);

		note6 = new Note();
		reg2 = new User();
		reg2.setUser_id(8);
		note6.setUser(reg2);
	}

	@Test
	@Ignore
	public void testLogin() {

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmNkZSIsImlhdCI6MTUwOTQzNDIxMCwic3ViIjoiSldUIFRva2VuIiwiaXNzIjoiRnVuZG9vQXBwbGljYXRpb24iLCJJZCI6OCwiZXhwIjoxNTA5NDM4MjEwfQ.cd3UpaVXuvJqAWAOd-Hc7I_kV8JIWP7B2Ud2BjTn-tQ";
		logger.info("testlogin()");
		Response resp = given().contentType("application/json").body(user1).when().post("login");
		logger.info(resp.asString());
		resp.then().statusCode(200);
	}

	@Test
	@Ignore
	public void testLogin1() {

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NSIsImlhdCI6MTUwODkwNDYxNiwic3ViIjoiSldUIFRva2VuIiwiaXNzIjoiQW5pa2V0aCdzIFRva2VucyIsIk5hbWUiOiJMdWlzIFN1YXJleiIsIk1vYmlsZSI6NDcyMzQyLCJJZCI6NCwiZXhwIjoxNTA4OTA4NjE2fQ.uYFTQcTIWOiTM1pQCX4A_3dAh_ofAQjXc6jHsOgZhsQ";
		System.out.println("testRegister user exists");
		Response resp = given().contentType("application/json").header("token", token).body(user4).when()
				.post("auth/trashNote");
		logger.info(resp.asString());
		resp.then().statusCode(200);
	}

	@Test
	@Ignore
	public void testFilter1() {

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmNkZSIsImlhdCI6MTUwOTQzNDIxMCwic3ViIjoiSldUIFRva2VuIiwiaXNzIjoiRnVuZG9vQXBwbGljYXRpb24iLCJJZCI6OCwiZXhwIjoxNTA5NDM4MjEwfQ.cd3UpaVXuvJqAWAOd-Hc7I_kV8JIWP7B2Ud2BjTn-tQ";
		System.out.println("Testing Notes Functionalities");
		/* Response resp = */given().contentType("application/json").header("token", token).body(note1).when()
				.post("auth/insertNote").then().statusCode(200);
		/* logger.info(resp.asString()); */

	}

	@Test
	@Ignore
	public void testFilter2() {
		// String jsonString =user1.toJSONString;
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NSIsImlhdCI6MTUwOTE3MTkzOSwic3ViIjoiSldUIFRva2VuIiwiaXNzIjoiQW5pa2V0aCdzIFRva2VucyIsIk5hbWUiOiJBbmlrZXRoIEJvbmRhZGEiLCJNb2JpbGUiOjEyMzQ1LCJJZCI6MSwiZXhwIjoxNTA5MTc1OTM5fQ.6YnPuGXoJSMde8tA1JwI2dLvjV3uSYRpM7R0e594wig";
		System.out.println("Testing Notes Functionalities");
		/* Response resp = */given().contentType("application/json").header("token", token).body(note2).when()
				.post("auth/updateNote").then().statusCode(200);
		/* logger.info(resp.asString()); */

	}

	@Test
	@Ignore
	public void testFilter3() {
		// String jsonString =user1.toJSONString;
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NSIsInN1YiI6IkpXVCBUb2tlbiIsImlzcyI6IkFuaWtldGgncyBUb2tlbnMiLCJOYW1lIjoiQmhhcmF0aGkiLCJNb2JpbGUiOjczNzMsIklkIjoyfQ.99TE3zxvrU4fistZ5_JWcVZO71dqxPW6v4RgJbAD2q0";
		System.out.println("Testing Notes Functionalities");
		/* Response resp = */given().contentType("application/json").header("token", token).body(note3).when()
				.post("auth/deleteNote").then().statusCode(200);
		/* logger.info(resp.asString()); */

	}

	@Test
	@Ignore
	public void testFilter4() {
		// String jsonString =user1.toJSONString;
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NSIsInN1YiI6IkpXVCBUb2tlbiIsImlzcyI6IkFuaWtldGgncyBUb2tlbnMiLCJOYW1lIjoiQmhhcmF0aGkiLCJNb2JpbGUiOjczNzMsIklkIjoyfQ.99TE3zxvrU4fistZ5_JWcVZO71dqxPW6v4RgJbAD2q0";
		System.out.println("Testing Notes Functionalities");
		/* Response resp = */given().contentType("application/json").header("token", token).body(note4).when()
				.post("auth/getNotebyId").then().statusCode(200);
		/* logger.info(resp.asString()); */

	}

	@Test
	@Ignore
	public void testNotes() {

		note1.setNotes_id(4);
		reg1.setUser_id(8);
		note1.setUser(reg1);
		note1.setTrash(true);
		logger.warn("Testing Notes Functionalities");
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmNkZSIsImlhdCI6MTUwOTQzMDE0MSwic3ViIjoiSldUIFRva2VuIiwiaXNzIjoiRnVuZG9vQXBwbGljYXRpb24iLCJJZCI6MTAsImV4cCI6MTUwOTQzNDE0MX0.T2aAfPvniIoWxJn87kb8WsMV40ToWb_RewFxmtdNdHI";
		given().contentType(ContentType.JSON).header("token", token).body(note1).when().post("auth/trashNote").then()
				.statusCode(200);

	}

	@Test
	@Ignore
	public void testFilter6() {

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmNkZSIsImlhdCI6MTUwOTQzMDE0MSwic3ViIjoiSldUIFRva2VuIiwiaXNzIjoiRnVuZG9vQXBwbGljYXRpb24iLCJJZCI6MTAsImV4cCI6MTUwOTQzNDE0MX0.T2aAfPvniIoWxJn87kb8WsMV40ToWb_RewFxmtdNdHI";
		System.out.println("Testing Elastic Notes Functionalities");
		given().contentType("application/json").header("token", token).body(note5).when().post("auth/selectAllNotes")
				.then().statusCode(200);

	}

	@Test
	@Ignore
	public void testFilter7() {

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmNkZSIsImlhdCI6MTUwOTQzMDE0MSwic3ViIjoiSldUIFRva2VuIiwiaXNzIjoiRnVuZG9vQXBwbGljYXRpb24iLCJJZCI6MTAsImV4cCI6MTUwOTQzNDE0MX0.T2aAfPvniIoWxJn87kb8WsMV40ToWb_RewFxmtdNdHI";
		String content = "food";
		logger.warn("Testing Elastic Notes Search Functionalities");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", 10);
		map.put("searchString", content);

		given().contentType(ContentType.JSON).header("token", token).body(map).when().post("auth/serchAllNotesElastic")
				.then().statusCode(200);

	}

	@Test
	@Ignore
	public void testFilter8() {

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmNkZSIsImlhdCI6MTUwOTQzMDE0MSwic3ViIjoiSldUIFRva2VuIiwiaXNzIjoiRnVuZG9vQXBwbGljYXRpb24iLCJJZCI6MTAsImV4cCI6MTUwOTQzNDE0MX0.T2aAfPvniIoWxJn87kb8WsMV40ToWb_RewFxmtdNdHI";
		logger.warn("Indexing all Notes");
		given().contentType(ContentType.JSON).header("token", token).body(note6).when().post("auth/indexAllNotes")
				.then().statusCode(200);

	}

}
