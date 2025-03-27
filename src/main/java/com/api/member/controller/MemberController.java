package com.api.member.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.member.dto.request.CreateMemberReq;
import com.api.member.dto.request.LoginReq;
import com.api.member.dto.response.LoginRes;
import com.api.member.service.MemberService;
import com.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/member/signUp")
	public ApiResponse<Void> signUp(@RequestBody CreateMemberReq req) {
		memberService.signUp(req);
		return ApiResponse.ok();
	}

	@PostMapping("/member/login")
	public ApiResponse<LoginRes> login(@RequestBody LoginReq req) {
		return ApiResponse.ok(memberService.login(req));
	}
}
