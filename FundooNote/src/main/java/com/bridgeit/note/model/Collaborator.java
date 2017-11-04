package com.bridgeit.note.model;

public class Collaborator {

	private int sharedFromId;
	private int sharedToId;
	private int notes_Id;
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

	public int getNotesId() {
		return notes_Id;
	}

	public void setNotesId(int notes_Id) {
		this.notes_Id = notes_Id;
	}

	public int getCollabId() {
		return collabId;
	}

	public void setCollabId(int collabId) {
		this.collabId = collabId;
	}

	@Override
	public String toString() {
		return "Collabrator [sharedFromId=" + sharedFromId + ", sharedToId=" + sharedToId + ", notes_id=" + notes_Id
				+ ", collabId=" + collabId + "]";
	}

}
