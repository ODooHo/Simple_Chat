package com.api.member.dto.request;

public record LoginReq(
	String email,
	String password
) {
}
