package com.bridgeit.note.utility;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

/*
 * 	A Utility class to set the Generated JWT token in Redis cache
 * 
 */
@Service
public class RedisUtility {

	private final static Logger logger = Logger.getLogger(RedisUtility.class);
	static Jedis jedis = new Jedis("localhost");

	// Setting the Token in Redis cache
	public static void sendtokenredis(int i, String token) {

		jedis.hset(Integer.toString(i), "Token", token);
	}

	// Retrieving the Token set in Redis cache
	public static String gettokenfromredis(int i) {
		String token = jedis.hget(Integer.toString(i), "Token");
		logger.info(token);
		return token;
	}
}
