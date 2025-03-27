package com.api.message.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.chatroom.entity.ChatRoom;
import com.api.chatroom.exception.ChatRoomErrorCode;
import com.api.chatroom.repository.ChatRoomRepository;
import com.api.member.entity.Member;
import com.api.member.exception.MemberErrorCode;
import com.api.member.repository.MemberRepository;
import com.api.message.dto.request.CreateMessageReq;
import com.api.message.dto.response.ReadMessageRes;
import com.api.message.entity.Message;
import com.api.message.repository.MessageRepository;
import com.global.exception.DomainException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

	private final MessageRepository messageRepository;
	private final ChatRoomRepository chatRoomRepository;
	private final MemberRepository memberRepository;

	@Transactional
	@Override
	public ReadMessageRes sendMessage(CreateMessageReq req) {
		ChatRoom chatRoom = findChatRoom(req.chatRoomId());
		Member sender = findMember(req.senderId());
		return ReadMessageRes.of(messageRepository.save(Message.create(req, chatRoom, sender)));
	}

	@Override
	public List<ReadMessageRes> readAllMessages(Long chatRoomId) {
		if (chatRoomRepository.existsById(chatRoomId)) {
			return messageRepository.findByChatRoomIdOrderBySentAt(chatRoomId)
				.stream()
				.map(ReadMessageRes::of)
				.toList();
		}
		return List.of();
	}

	private ChatRoom findChatRoom(Long chatRoomId) {
		return chatRoomRepository.findById(chatRoomId).orElseThrow(
			() -> new DomainException(ChatRoomErrorCode.CHAT_ROOM_NOT_FOUND, "MessageService.sendMessage")
		);
	}

	private Member findMember(Long memberId) {
		return memberRepository.findById(memberId).orElseThrow(
			() -> new DomainException(MemberErrorCode.MEMBER_NOT_FOUND, "MessageService.sendMessage")
		);
	}
}
