package com.cap.filter;

import java.io.IOException;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String authHeader = httpRequest.getHeader("authorization");
		
		if(authHeader==null || !authHeader.startsWith("Bearer"))
		{
			throw new ServletException("Missing or Invalid Authentication");
			
		}
		String jwtToken = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secrete key").parseClaimsJws(jwtToken).getBody();
		System.out.println(claims);
		httpRequest.setAttribute("email", claims);
		chain.doFilter(request, response);
		

	}

}