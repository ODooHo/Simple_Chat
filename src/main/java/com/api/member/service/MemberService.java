package com.api.member.service;

import com.api.member.dto.request.CreateMemberReq;
import com.api.member.dto.request.LoginReq;
import com.api.member.dto.response.LoginRes;

public interface MemberService {
	void signUp(CreateMemberReq createMemberReq);

	LoginRes login(LoginReq loginReq);
}
