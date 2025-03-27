package com.api.member.dto.response;

import com.global.jwt.JwtResponse;

public record LoginRes(
	String token,
	Long expiration
) {
	public static LoginRes of(JwtResponse jwtResponse) {
		return new LoginRes(jwtResponse.token(), jwtResponse.expiration());
	}
}
