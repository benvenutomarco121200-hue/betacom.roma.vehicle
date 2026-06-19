package com.betacom.sb.services.implementation;

import org.springframework.stereotype.Service;

import com.betacom.sb.models.Message;
import com.betacom.sb.repositories.IMessageRepository;
import com.betacom.sb.services.interfaces.IMessageServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageImpl implements IMessageServices {

	private final IMessageRepository repoMessage;
	
	@Override
	public String get(String code) {
		log.debug("get {}", code);
		Message msg = repoMessage.findById(code)
				.orElse( new  Message(code, code));
		return msg.getMessage();
	}

}
