package com.event.bbs.web.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.event.bbs.web.entity.Question;
import com.event.bbs.web.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;
	private final EventService eventService;

	public void create(String quiz,int eventId ){

		//todo:질문 갯수가 일정 수 이상이면 exception

		questionRepository.save(
			Question.builder()
				.content(quiz)
				.event(eventService.getEvent(eventId))
				.build()
		);
	}



}
