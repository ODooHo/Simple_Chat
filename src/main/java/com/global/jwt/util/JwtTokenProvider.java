package com.global.jwt.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.api.member.entity.Member;
import com.global.jwt.JwtResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	private final SecretKey secretKey;

	public JwtTokenProvider(@Value("${jwt.secret_key}") String jwtSecret) {
		byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
		this.secretKey = Keys.hmacShaKeyFor(keyBytes);
	}

	public JwtResponse generateToken(Member member) {
		long expirationMs = 1000 * 60 * 120;
		return new JwtResponse(
			Jwts.builder()
				.subject(String.valueOf(member.getId()))
				.claim("email", member.getEmail())
				.claim("roles", "USER")
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expirationMs))
				.signWith(secretKey)
				.compact(),
			expirationMs);
	}

	public String validateAndGetUserId(String token) {
		Claims claims =
			Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();

		return claims.getSubject();
	}

	public Claims getClaims(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
	}
}
