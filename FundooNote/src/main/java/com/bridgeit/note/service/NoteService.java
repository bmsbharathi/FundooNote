package com.bridgeit.note.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.note.dao.NoteMapperImpl;
import com.bridgeit.note.model.Note;

/*
 * 	A Service class to manage operations on the Note
 */

@Service
public class NoteService {

	@Autowired
	private NoteMapperImpl noteMapperImpl;

	// To create a new note
	public void insertNote(Note newnote) {

		noteMapperImpl.insertNote(newnote);
	}

	// To update an already existing note
	public void updateNote(Note note) {

		noteMapperImpl.updateNote(note);
	}

	// Delete a particular note
	public void deleteNode(Note note) {

		noteMapperImpl.deleteNote(note);
	}

	// Retrieving a note based on note id
	public Note getNotebyId(Note note) {

		Note notesend = noteMapperImpl.getNotebyId(note);
		return notesend;
	}

	// Get all the notes of a particular user based on user id
	public List<Note> selectAllNotes(Note note) {

		List<Note> notes = noteMapperImpl.selectAllNotes(note);
		return notes;
	}

	// Archive a particular note
	public void archiveNote(Note note) {

		noteMapperImpl.archiveNote(note);
	}

	// Moving a note to trash
	public void trashNote(Note note) {

		noteMapperImpl.trashNote(note);
	}

	// Setting a remainder for a particular note
	public void setRemainder(Note note) {

		noteMapperImpl.setRemainder(note);
	}

	// Getting all the notes from database for indexing in elastic search
	public List<Note> getAllNotes() {

		List<Note> allNotes = new ArrayList<Note>();
		allNotes = noteMapperImpl.getAllNotes();

		return allNotes;
	}

	// Getting all the collaborators for a particular note
	public List<Integer> getExistingCollabrators(int noteid) {

		List<Integer> allCollabs = noteMapperImpl.getAllCollaborators(noteid);

		return allCollabs;
	}

	public void addCollaborators(int noteid, List<Integer> newCollabIds) {

		noteMapperImpl.addCollaborators(noteid, newCollabIds);
	}
}
