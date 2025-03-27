package com.api.member.dto.request;

public record CreateMemberReq(
	String email,
	String nickname,
	String password
) {
}
