package org.ecom.userms.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {

	private static final String JWT_SECRET = "uECZQF5DQy5oNsdR7xskT2zF9pEDkqF8QFz3rwX1mRM=";
	private static final int JWT_EXPIRATION_MS = 300000; // 15 minutes

	Logger logger = LoggerFactory.getLogger(JwtUtils.class);
// step 1 genereate secret key

	private SecretKey generateKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
	}
	// step 2 generte token

	public String generateToken(UserDetails useDetails) {
		String token = Jwts.builder().setSubject(useDetails.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + JWT_EXPIRATION_MS)).signWith(generateKey()).compact();
		return token;
	}

	// get username from token
	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	// validate token
	// get token from header

	public boolean validateToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(authToken); // This line throws if
																									// token is invalid
			return true;
		} catch (SecurityException | MalformedJwtException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}

	public String getJwtFromHeader(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		logger.debug("Authorization Header: {}", bearerToken);
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7); // Strip "Bearer "
		}
		return null;
	}
}
