package com.bridgeit.note.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bridgeit.note.model.Note;

@Component
public interface NoteMapper {

	public void insertNote(Note newnote);

	public void updateNote(Note modifynote);

	public void deleteNote(Note note);

	public Note getNotebyId(Note note);

	public List<Note> selectAllNotes(Note note);

	public void archiveNote(Note note);

	public void trashNote(Note note);

	public void setRemainder(Note note);

	public List<Note> getAllNotes();

	public List<Integer> getAllCollaborators(int noteid);

	public void addCollaborators(int noteid, int newCollabId);

}
