package com.api.chatroom.service;

import org.springframework.stereotype.Service;

import com.api.chatroom.dto.request.CreateChatRoomReq;
import com.api.chatroom.dto.response.CreateChatRoomRes;
import com.api.chatroom.entity.ChatRoom;
import com.api.chatroom.repository.ChatRoomRepository;
import com.api.member.entity.Member;
import com.api.member.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatRoomServiceImpl implements ChatRoomService {

	private final MemberRepository memberRepository;
	private final ChatRoomRepository chatRoomRepository;

	@Transactional
	@Override
	public CreateChatRoomRes createOrGetChatRoom(CreateChatRoomReq createChatRoomReq) {
		return CreateChatRoomRes.of(findOrGetChatRoom(createChatRoomReq));
	}

	private ChatRoom findOrGetChatRoom(CreateChatRoomReq createChatRoomReq) {
		return chatRoomRepository.findByFirstMemberIdAndSecondMemberId(createChatRoomReq.firstMemberId(),
				createChatRoomReq.secondMemberId())
			.orElseGet(() -> {
				Member firstMember = memberRepository.findById(createChatRoomReq.firstMemberId())
					.orElseThrow(() -> new IllegalArgumentException("User not found"));
				Member secondMember = memberRepository.findById(createChatRoomReq.secondMemberId())
					.orElseThrow(() -> new IllegalArgumentException("User not found"));
				return chatRoomRepository.save(ChatRoom.create(firstMember, secondMember));
			});
	}
}
