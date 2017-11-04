package com.bridgeit.note.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bridgeit.note.elasticsearch.ElasticSearch;
import com.bridgeit.note.json.Response;
import com.bridgeit.note.model.Note;
import com.bridgeit.note.service.NoteService;
import com.bridgeit.note.service.UtilityService;

@Controller
@RequestMapping("auth/")
public class NoteController {

	private static final Logger logger = Logger.getLogger(NoteController.class);

	@Autowired
	private NoteService service;

	@Autowired
	private UtilityService utility;

	@Autowired
	private ElasticSearch elasticsearch;

	private Response resp = new Response();

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
		service.deleteNode(note3);
		logger.info("After deleting note");
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "getNotebyId", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> getNotebyId(@RequestBody Note note) {

		logger.info("Before getNotebyId");
		logger.info(note);
		Note note2 = service.getNotebyId(note);
		logger.info("After getNotebyId");
		resp.setMessage(note2.getDescription());

		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "indexAllNotes", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> indexNotes(@RequestBody Note note5) {

		logger.info("Before IndexAllNotes");
		List<Note> notes = service.getAllNotes();
		logger.info("After IndexAllNotes");
		logger.info(notes);
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
		resp.setStatus(note6.getUser().getUserId());
		resp.setMessage("Archived");
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "trashNote", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> trashNote(@RequestBody Note note7) {

		logger.info("Trashing the selected user note");
		service.trashNote(note7);
		logger.info("After trashing the node");
		resp.setStatus(note7.getUser().getUserId());
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
			resp.setStatus(note.getUser().getUserId());
			resp.setMessage("Node deleted from trash");
			elasticsearch.deleteElasticNotes(note.getNotesId());
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
		resp.setMessage("Remainder for the user " + note9.getUser().getUserId() + " and note number of "
				+ note9.getNotesId() + " has been set succesfully to the note by the user");

		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}

	// Funtionalities for Collabrators
	@RequestMapping(value = "addCollaborators/{noteid}/{sharedFrom}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> addCollaborators(@PathVariable int noteid, @PathVariable int sharedFrom,
			@RequestBody List<String> newCollabs) {

		List<Integer> newCollabIds = new ArrayList<Integer>();
		for (String email : newCollabs) {

			Integer uid = utility.checkUserByEmail(email).getUserId();
			if( uid == 0 || uid == null ) {
				
				logger.warn("Cannot find User "+email);
			}
			newCollabIds.add(uid);
		}

		List<Integer> collabs = service.getExistingCollabrators(noteid);
		if (collabs.isEmpty()) {

			service.addCollaborators(noteid, newCollabIds);
		}

		return new ResponseEntity<Response>(new Response(), HttpStatus.OK);
	}
}