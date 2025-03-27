package com.api.message.service;

import java.util.List;

import com.api.message.dto.request.CreateMessageReq;
import com.api.message.dto.response.ReadMessageRes;

/**
 * TODO: 채팅방 정보와 가장 최근 발송한 메시지 내역을 함께 가져와 미리보기 기능 구현 예정
 */

public interface MessageService {
	ReadMessageRes sendMessage(CreateMessageReq req);

	List<ReadMessageRes> readAllMessages(Long chatRoomId);

}
