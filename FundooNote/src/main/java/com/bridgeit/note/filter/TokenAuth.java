package com.bridgeit.note.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgeit.note.utility.TokenizerUtility;
import com.bridgeit.note.utility.RedisUtility;

/**
 * Filter to check for a valid Token set in the request header
 */
public class TokenAuth implements Filter {

	public TokenAuth() {

	}

	public void init(FilterConfig fConfig) throws ServletException {

		System.out.println("Inside Filter..");

	}

	/**
	 * Getting the Token from Request header and getting the token set in Redis for
	 * that user and matching it and Checking for Token's expiry
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("Hello Filter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String token = req.getHeader("token");

		try {

			System.out.println(token);
			int tokenuserid = TokenizerUtility.verifyToken(token);
			Date expdate = TokenizerUtility.verifyTokenDate(token);
			System.out.println(tokenuserid);
			System.out.println(expdate + " is the expiry date");
			String tokenfromredis = RedisUtility.gettokenfromredis(tokenuserid);
			System.out.println(tokenfromredis);

			if (token.equals(tokenfromredis)) {
				if (new Date().before(expdate)) {
					System.out.println("Yes Inside Filter Pre Processing");
					chain.doFilter(request, response);
					System.out.println("Filtering Done..");
				}
			}

		} catch (io.jsonwebtoken.ExpiredJwtException e) {
			System.out.println(e);
			System.out.println("Please Login Again");
			resp.sendRedirect("/login");
		} catch (java.lang.IllegalArgumentException e) {
			System.out.println(e);
			System.out.println("User needs to Login First. Cannot ACCESS");
			resp.sendRedirect("/login");
		}

	}

	public void destroy() {

	}

}
