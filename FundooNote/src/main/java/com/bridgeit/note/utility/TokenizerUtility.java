package com.bridgeit.note.utility;

import java.util.Date;

import org.apache.log4j.Logger;

import com.bridgeit.note.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * 	A Utility class to create a JWT token and to check expiry of the Tokens
 */

public class TokenizerUtility {

	private final static Logger logger = Logger.getLogger(TokenizerUtility.class);
	private static String CLIENT_ID = "abcde";
	private final static String CLIENT_SECRET = "asdasdxccvxc";

	// Creating a new JWT token
	public static String getToken(User reg)

	{
		String token;
		long currentmillis = System.currentTimeMillis();
		Date now = new Date(currentmillis);
		logger.info(now);
		logger.info("----------------------" + reg);
		JwtBuilder builder = Jwts.builder().setId(CLIENT_ID).setIssuedAt(now).setSubject("JWT Token")
				.setIssuer("FundooApplication").claim("Name", reg.getFullName()).claim("Mobile", reg.getMobileNo())
				.claim("Id", reg.getUser_id()).signWith(SignatureAlgorithm.HS256, CLIENT_SECRET);

		long exptime = 4000000;
		if (exptime >= 0) {
			long expmillis = currentmillis + exptime;
			Date expdate = new Date(expmillis);
			logger.info(expdate);
			builder.setExpiration(expdate);
		}
		token = builder.compact();
		logger.info(token);

		return token;
	}

	public static int verifyToken(String token) {

		Claims claims = Jwts.parser().setSigningKey(CLIENT_SECRET).parseClaimsJws(token).getBody();
		logger.info(claims.getId());
		logger.info(claims.getIssuedAt());
		logger.info(claims.getSubject());
		logger.info(claims.getIssuer());
		logger.info(claims.get("Name"));
		logger.info(claims.get("Mobile"));
		logger.info(claims.get("Id"));
		logger.info(claims.getExpiration());
		int idno = (int) claims.get("Id");
		Date expdate = claims.getExpiration();
		logger.info(expdate);
		logger.info(idno);
		return idno;

	}

	// Getting the expiry date set in the Token
	public static Date verifyTokenDate(String token) {

		Claims claims = Jwts.parser().setSigningKey(CLIENT_SECRET).parseClaimsJws(token).getBody();
		Date expdate = claims.getExpiration();
		return expdate;
	}

}
