package com.global.config.security.filter;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

@Getter
public class JwtAuthentication extends UsernamePasswordAuthenticationToken {

	private final String token;

	public JwtAuthentication(
		UserDetails principal, String token, Collection<? extends GrantedAuthority> authorities) {
		super(principal, null, authorities);
		this.token = token;
	}
}
