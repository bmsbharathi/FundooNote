package com.bridgeit.note.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bridgeit.note.elasticsearch.ElasticSearch;
import com.bridgeit.note.json.Response;
import com.bridgeit.note.model.Note;
import com.bridgeit.note.service.NoteService;

@Controller
@RequestMapping("auth/")
public class NoteController {

	private static final Logger logger = Logger.getLogger(NoteController.class);

	@Autowired
	private NoteService service;

	@Autowired
	private ElasticSearch elasticsearch;

	Response resp = new Response();

	@RequestMapping(value = "insertNote", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> insertNote(@RequestBody Note note) {

		logger.info("Before Inserting Note");
		service.insertNote(note);
		logger.info("After Insertion of the Note\n" + note);

		return new ResponseEntity<Response>(resp, HttpStatus.OK);

	}

	@RequestMapping(value = "updateNote", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> updateNote(@RequestBody Note note2) {

		logger.info("Before Update Note");
		service.updateNote(note2);
		logger.info("After Update Note");
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "deleteNote", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> deleteNote(@RequestBody Note note3) {
		logger.info("Before Delete Note");
		/*
		 * Note note=new Note(); note.setNotes_id(7); Register user=new Register();
		 * user.setUser_id(2); note.setUser(user);
		 */

		service.deleteNode(note3);
		logger.info("After deleting note");
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "getNotebyId", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> getNotebyId(@RequestBody Note note4) {

		logger.info("Before getNotebyId");
		logger.info(note4);
		note4 = service.getNotebyId(note4);
		logger.info(note4);
		logger.info("After getNotebyId");
		resp.setMessage(note4.toString());
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "indexAllNotes", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> indexNotes(@RequestBody Note note5) {

		logger.info("Before IndexAllNotes");
		List<Note> notes = service.getAllNotes();
		logger.info("After IndexAllNotes");
		logger.info(notes);
		/* elasticsearch.indexAllNotes(notes); */
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "serchAllNotesElastic", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Response> selectAllNotesElastic(@RequestBody Map<String, Object> searchMap) {

		int userid = (Integer) searchMap.get("userid");
		String searchString = (String) searchMap.get("searchString");

		logger.info("Searching Elastic searcAllNotes");
		logger.info(searchString);
		elasticsearch.searchElasticNotes(searchString, userid);
		logger.info("After Search Elastic All Notes");
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "archiveNote", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> archiveNote(@RequestBody Note note6) {
		logger.info("Archiving the selected user node");
		service.archiveNote(note6);
		logger.info("After archiving the node");
		resp.setStatus(note6.getUser().getUser_id());
		resp.setMessage("Archived");
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "trashNote", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> trashNote(@RequestBody Note note7) {

		logger.info("Trashing the selected user note");
		service.trashNote(note7);
		logger.info("After trashing the node");
		resp.setStatus(note7.getUser().getUser_id());
		resp.setMessage("Archived");
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "deleteFromTrash", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> deleteFromTrash(@RequestBody Note note) {

		logger.info("Deleting Permanently the selected user node");
		logger.info(note.isTrash() && note.isDeletefromtrash());
		if (note.isTrash() && note.isDeletefromtrash()) {
			service.deleteNode(note);
			logger.info("Deleted the node permanently");
			resp.setStatus(note.getUser().getUser_id());
			resp.setMessage("Node deleted from trash");
			elasticsearch.deleteElasticNotes(note.getNotes_id());
		} else {

			logger.info("Not deleted");
		}
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "setRemainder", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> setRemainder(@RequestBody Note note9) {

		logger.info("Setting Remainder");
		logger.info(note9);
		service.setRemainder(note9);
		logger.info("Remainder Set");
		resp.setStatus(100);
		resp.setMessage("Remainder for the user " + note9.getUser().getUser_id() + " and note number of "
				+ note9.getNotes_id() + " has been set succesfully to the note by the user");

		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}

	// Funtionalities for Collabrators
	@RequestMapping(value = "addCollabrators", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> addCollaborators(@RequestBody List<Integer> newCollabs,int noteid) {

		List<Integer> collabs = service.checkExistingCollabrators(noteid);
		logger.info(""+collabs);
		return new ResponseEntity<Response>(new Response(), HttpStatus.OK);
	}
}