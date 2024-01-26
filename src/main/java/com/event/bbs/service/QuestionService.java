package com.event.bbs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.event.bbs.dto.requestDto.QuestionReqDto;
import com.event.bbs.entity.Event;
import com.event.bbs.entity.Question;
import com.event.bbs.repository.QuestionRepository;
import com.event.bbs.response.BaseException;
import com.event.bbs.response.ErrorStatus;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;
	private final EventService eventService;

	//수정/삭제
	public void create(List<QuestionReqDto> questionList , int id){

		Event event = eventService.getEvent(id);

		if(event==null){
			throw new BaseException(ErrorStatus.DATA_NOT_FOUND);
		}

		if(questionRepository.findByEvent_id(id).size()>=1){
			//저장되어 있는 내용 삭제
			questionRepository.deleteAll();
		}


		List<Question> questions = questionList.stream().map(
			q -> {
				return Question.builder()
					.qNum(q.getQNum())
					.content(q.getSubject())
					.event(event)
					.build();
			}
		).collect(Collectors.toList());

		questionRepository.saveAll(questions);

	}





}
