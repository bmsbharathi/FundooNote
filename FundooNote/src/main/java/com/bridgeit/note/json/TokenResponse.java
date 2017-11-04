package com.bridgeit.note.json;

public class TokenResponse {
	
	private String userstatus;
	private String token;

	public String getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "TokenGeneration [userstatus=" + userstatus + ", token=" + token + "]";
	}

}
