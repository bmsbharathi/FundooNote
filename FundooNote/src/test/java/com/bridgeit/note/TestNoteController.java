package com.bridgeit.note;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.bridgeit.note.model.Note;
import com.bridgeit.note.model.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class TestNoteController {

	private static Note note1, note2, note3, note4, note5, note6, noteunarchive, note7, note8, note9;
	private static User user1, user2, user3, user5, user6, userunarchive, user7, user8, user9;
	private static final Logger logger = Logger.getLogger(TestNoteController.class);

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/FundooNote";

		note1 = new Note();
		user1 = new User();
		user1.setUserId(8);
		note1.setTitle("Soccer");
		note1.setDescription("A beautiful game in peace");
		note1.setLastaccessdate(new Date());
		note1.setUser(user1);

		note2 = new Note();
		user2 = new User();
		note2.setTitle("Criket");
		note2.setDescription("A bat and ball game in a ground");
		note2.setNotesId(7);
		note2.setUser(user2);

		note3 = new Note();
		user3 = new User();
		user3.setUserId(1);
		note3.setNotesId(17);
		note3.setUser(user3);

		note4 = new Note();
		note4.setNotesId(8);

		note5 = new Note();
		user5 = new User();
		user5.setUserId(4);
		note5.setUser(user5);

		note6 = new Note();
		user6 = new User();
		user6.setUserId(5);
		note6.setNotesId(36);
		note6.setArchive(true);
		note6.setUser(user6);

		noteunarchive = new Note();
		userunarchive = new User();
		userunarchive.setUserId(5);
		noteunarchive.setNotesId(36);
		noteunarchive.setArchive(false);
		noteunarchive.setUser(userunarchive);

		/* Sending note to trash */
		note7 = new Note();
		user7 = new User();
		user7.setUserId(4);
		note7.setNotesId(48);
		note7.setTrash(true);
		note7.setRemainder(new Date());
		note7.setDeletefromtrash(false);
		note7.setUser(user7);

		/* Permanent deletion of the note */
		note8 = new Note();
		user8 = new User();
		user8.setUserId(5);
		note8.setNotesId(34);
		note8.setTrash(true);
		note8.setDeletefromtrash(true);
		note8.setUser(user7);

		note9 = new Note();
		user9 = new User();
		user9.setUserId(9);
		note9.setNotesId(40);
		note9.setRemainder(new Date());
		note9.setUser(user9);

	}

	@Test
	@Ignore
	public void testInsertNote() {

		given().contentType(ContentType.JSON).body(note3).when().post("insertNote").then().statusCode(200).assertThat();
	}

	@Test
	@Ignore
	public void testUpdateNote() {

		given().contentType(ContentType.JSON).body(note2).when().post("updateNote").then().statusCode(200).assertThat();
	}

	@Test
	@Ignore
	public void testDeleteNote() {

		given().contentType(ContentType.JSON).body(note3).when().post("deleteNote").then().statusCode(200).assertThat();
	}

	@Test
	@Ignore
	public void testgetNotebyId() {

		given().contentType(ContentType.JSON).body(note4).when().post("getNotebyId").then().statusCode(200)
				.assertThat();
	}

	@Test
	@Ignore
	public void testgetallNotes() {

		given().contentType(ContentType.JSON).body(note5).when().post("selectAllNotes").then().statusCode(200)
				.assertThat();
	}

	@Test
	@Ignore
	public void archiveNote() {

		given().contentType(ContentType.JSON).body(note6).when().post("archiveNote").then().statusCode(200)
				.assertThat();
	}

	@Test
	@Ignore
	public void unarchiveNote() {

		given().contentType(ContentType.JSON).body(noteunarchive).when().post("archiveNote").then().statusCode(200)
				.assertThat();
	}

	@Test
	@Ignore
	public void trashNote() {
		given().contentType(ContentType.JSON).body(note7).when().post("trashNote").then().statusCode(200).assertThat();
	}

	@Test
	@Ignore
	public void deletefromtrash() {

		given().contentType(ContentType.JSON).body(note7).when().post("deleteFromTrash").then().statusCode(200)
				.assertThat();
	}

	@Test
	@Ignore
	public void setRemainder() {

		logger.info("Setting Remainder");
		given().contentType(ContentType.JSON).body(note7).when().post("setRemainder").then().statusCode(200)
				.assertThat();
	}

}
