package com.bridgeit.note.model;

public class Collabrator {

	private int sharedFromId;
	private int sharedToId;
	private int notes_id;
	private int collabId;


	public int getSharedFromId() {
		return sharedFromId;
	}

	public void setSharedFromId(int sharedFromId) {
		this.sharedFromId = sharedFromId;
	}

	public int getSharedToId() {
		return sharedToId;
	}

	public void setSharedToId(int sharedToId) {
		this.sharedToId = sharedToId;
	}

	public int getNotes_id() {
		return notes_id;
	}

	public void setNotes_id(int notes_id) {
		this.notes_id = notes_id;
	}

	public int getCollabId() {
		return collabId;
	}

	public void setCollabId(int collabId) {
		this.collabId = collabId;
	}


	@Override
	public String toString() {
		return "Collabrator [sharedFromId=" + sharedFromId + ", sharedToId=" + sharedToId + ", notes_id=" + notes_id
				+ ", collabId=" + collabId + "]";
	}

}
