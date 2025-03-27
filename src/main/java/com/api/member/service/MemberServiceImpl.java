package com.api.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.member.dto.request.CreateMemberReq;
import com.api.member.dto.request.LoginReq;
import com.api.member.dto.response.LoginRes;
import com.api.member.entity.Member;
import com.api.member.exception.MemberErrorCode;
import com.api.member.repository.MemberRepository;
import com.global.exception.DomainException;
import com.global.jwt.util.JwtTokenProvider;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public void signUp(CreateMemberReq createMemberReq) {
		conflictCheck(createMemberReq);
		String encryptedPassword = passwordEncoder.encode(createMemberReq.password());
		memberRepository.save(Member.create(createMemberReq, encryptedPassword));

	}

	@Override
	public LoginRes login(LoginReq loginReq) {
		return LoginRes.of(jwtTokenProvider.generateToken(validateAndFindMember(loginReq)));
	}

	/**
	 *  닉네임 중복 처리 까지 추가될 경우 회원가입 여정에 포함하는 것이 아니라 분리해서 중복 체크 api로 분리
	 */
	private void conflictCheck(CreateMemberReq createMemberReq) {
		if (memberRepository.existsByEmail(createMemberReq.email())) {
			throw new DomainException(MemberErrorCode.ALREADY_EXISTS_EMAIL, "MemberService.signUp");
		}
	}

	private Member validateAndFindMember(LoginReq req) {
		Member member = findMemberByEmail(req.email());
		validatePassword(req.password(), member.getPassword());
		return member;
	}

	private Member findMemberByEmail(String email) {
		return memberRepository.findByEmail(email)
			.orElseThrow(() -> new DomainException(MemberErrorCode.MEMBER_NOT_FOUND, "MemberService.login"));
	}

	private void validatePassword(String rawPassword, String encodedPassword) {
		if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
			throw new DomainException(MemberErrorCode.INVALID_LOGIN_REQUEST, "MemberService.login");
		}
	}
}
