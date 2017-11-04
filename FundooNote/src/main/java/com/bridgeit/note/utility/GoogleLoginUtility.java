package com.bridgeit.note.utility;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.bridgeit.note.model.GoogleProfile;
import com.bridgeit.note.model.GoogleToken;

public class GoogleLoginUtility {

	private static Logger logger = Logger.getLogger(GoogleLoginUtility.class);
	private static String sScope = "email";
	private static String sGmail_CLIENT_ID = "210547452486-1lin2i9diu2gjbtvp2vubq89042lie45.apps.googleusercontent.com";
	private static String sGmail_CLIENT_SECRET = "N8S_lhGpLflCSsmswarykaGs";
	private static String sGmail_REDIRECT_URI = "/postGoogle";

	private static String sGmail_URL = "https://accounts.google.com/o/oauth2/v2/auth?client_id=%s&redirect_uri=%s&state=%s&"
			+ "response_type=code&scope=%s&approval_prompt=force&access_type=offline";

	private static String sGmail_ACCESS_TOKEN_URL = "https://www.googleapis.com/oauth2/v4/token";

	private static String sGmail_GET_USER_URL = "https://www.googleapis.com/plus/v1/people/me";

	public static String getGmailUrl(String apiRedirectUrl, String stateCode) {

		logger.warn("Inside Gmail URL");
		apiRedirectUrl.concat(sGmail_REDIRECT_URI);
		String gmailUrl = new String().format(sGmail_URL,
				new String[] { sGmail_CLIENT_ID, apiRedirectUrl, stateCode, sScope });

		return gmailUrl;

	}

	public static GoogleProfile authUser(String authcode, String redirectUrl) {

		logger.warn("Inside to get user profile");
		String accessToken = getAcessToken(authcode, redirectUrl);
		logger.warn(authcode + " " + redirectUrl + " " + accessToken);
		return getuserProfile(accessToken);
	}

	private static GoogleProfile getuserProfile(String accessToken) {

		logger.warn("Inside gmail for user profile");
		String accProfileUrl = sGmail_GET_USER_URL;

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(accProfileUrl);

		String headerAuth = "Bearer " + accessToken;
		Response response = target.request().header("Authorization", headerAuth).accept(MediaType.APPLICATION_JSON)
				.get();
		GoogleProfile profile = response.readEntity(GoogleProfile.class);
		logger.warn(profile);
		client.close();
		return profile;

	}

	private static String getAcessToken(String authcode, String apiRedirectUrl) {

		logger.warn("Inside gmail for Access token");
		apiRedirectUrl.concat(sGmail_REDIRECT_URI);
		String accTokenUrl = sGmail_ACCESS_TOKEN_URL;

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(accTokenUrl);
		logger.warn("here");

		Form form = new Form();
		form.param("client_id", sGmail_CLIENT_ID);
		form.param("client_secret", sGmail_CLIENT_SECRET);
		form.param("redirect_uri", apiRedirectUrl);
		form.param("code", authcode);
		form.param("grant_type", "authorization_code");

		Response response = target.request().accept(MediaType.APPLICATION_JSON).post(Entity.form(form));
		GoogleToken token = response.readEntity(GoogleToken.class);
		logger.warn(token);
		client.close();
		return token.getAccessToken();

	}
}