package com.bridgeit.note.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.note.elasticsearch.ElasticSearch;
import com.bridgeit.note.model.Note;
import com.bridgeit.note.mybatisutility.MyBatisUtil;

@Repository
public class NoteMapperImpl implements NoteMapper {

	@Autowired
	ElasticSearch elasticSearch;

	private static final Logger logger = Logger.getLogger(NoteMapperImpl.class);
	private SqlSession session;

	public void insertNote(Note newnote)

	{
		session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			NoteMapper notemapper = session.getMapper(NoteMapper.class);
			notemapper.insertNote(newnote);
			session.commit();
		}

		finally {
			session.close();
		}
	}

	public void updateNote(Note note)

	{
		session = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			NoteMapper notemapper = session.getMapper(NoteMapper.class);
			notemapper.updateNote(note);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void deleteNote(Note note)

	{
		session = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			NoteMapper notemapper = session.getMapper(NoteMapper.class);
			notemapper.deleteNote(note);
			elasticSearch.deleteElasticNotes(note.getNotes_id());
			session.commit();
		} finally {
			session.close();
		}
	}

	public Note getNotebyId(Note note)

	{
		session = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			NoteMapper notemapper = session.getMapper(NoteMapper.class);
			Note notesend = notemapper.getNotebyId(note);
			session.commit();
			logger.info(notesend);
			return notesend;
		} finally {
			session.close();
		}

	}

	public List<Note> selectAllNotes(Note note)

	{
		session = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			NoteMapper notemapper = session.getMapper(NoteMapper.class);
			List<Note> notes = notemapper.selectAllNotes(note);
			session.commit();
			logger.info(notes);
			return notes;
		} finally {
			session.close();
		}
	}

	public void archiveNote(Note note)

	{
		session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {

			NoteMapper notemapper = session.getMapper(NoteMapper.class);
			logger.info(note);
			notemapper.archiveNote(note);
			session.commit();
			logger.info(note + " has been archived by the user");
		} finally {
			session.close();
		}

	}

	public void trashNote(Note note)

	{
		session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {

			NoteMapper notemapper = session.getMapper(NoteMapper.class);
			logger.info(note);
			notemapper.trashNote(note);
			session.commit();
			logger.info(note + " has been archived by the user");
		} finally {
			session.close();
		}

	}

	public void setRemainder(Note note)

	{
		session = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			NoteMapper notemapper = session.getMapper(NoteMapper.class);
			logger.info(note);
			notemapper.setRemainder(note);
			session.commit();
			logger.info("Remainder set in the database");
		} finally {
			session.close();
		}

	}

	@Override
	public List<Note> getAllNotes() {

		session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Note> allNotes = new ArrayList<Note>();

		try {

			NoteMapper notemapper = session.getMapper(NoteMapper.class);

			allNotes = notemapper.getAllNotes();
			session.commit();
			logger.info("Remainder set in the database");
		} finally {
			session.close();
		}

		return allNotes;
	}

}
