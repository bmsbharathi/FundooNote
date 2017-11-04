package com.bridgeit.note.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleToken {

	private String accessToken;
	private String tokenType;
	private String expiresIn;
	private String idToken;
	
	public GoogleToken() {
		super();
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String access_token) {
		this.accessToken = access_token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getIdToken() {
		return idToken;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}

}
