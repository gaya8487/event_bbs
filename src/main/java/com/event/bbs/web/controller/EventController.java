package com.event.bbs.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.event.bbs.login.entity.AdminUser;
import com.event.bbs.web.common.SessionConst;
import com.event.bbs.web.dto.EventDto;
import com.event.bbs.web.dto.QuestionDto;
import com.event.bbs.web.entity.Event;
import com.event.bbs.web.service.EventService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

	private final EventService eventService;
	@GetMapping("/list")
	public String list(@SessionAttribute(name= SessionConst.LOGIN_MEMBER,required = false) AdminUser adminUser, Model model){

		//세션에 회원 데이터가 없으면 홈으로
		if(adminUser == null){
			return "home";
		}

		//todo: 로그인한 사용자가 만든 이벤트만 조회

		List<Event> events = eventService.getEventList();

		List<EventDto>  eventList =  events.stream().map(x->  {
			return EventDto.builder()
			  .id(x.getId())
			  .subject(x.getSubject())
			  .content(x.getContent())
			  .createDate(x.getCreateDate())
			  .questionList(x.getQuestionList().stream().map(q ->
					  QuestionDto.builder()
						  .content(q.getContent())
						  .id(q.getId())
						  .event_id(q.getEvent().getId())
						  .build())
				  .collect(Collectors.toList()))
			  .answerList(x.getAnswerList())
			  .playerList(x.getPlayerList())
			  .build();
			}).collect(Collectors.toList());
		model.addAttribute("eventList",eventList);
		return "event_list";
	}

	@GetMapping("/detail/{id}")
	public String detailList(Model model, @PathVariable("id") Integer id){

		//todo: 이벤트 url은 UUID 이용해서 추측 불가능하게 수정

		Event event = eventService.getEvent(id);

		List<QuestionDto> questionDtoList = event.getQuestionList().stream().map(x ->
				QuestionDto.builder()
				.content(x.getContent())
				.id(x.getId())
				.event_id(x.getEvent().getId())
				.build())
			.collect(Collectors.toList());

		EventDto eventDto = EventDto.builder()
			.id(event.getId())
			.subject(event.getSubject())
			.content(event.getContent())
			.createDate(event.getCreateDate())
			.questionList(questionDtoList)
			.answerList(event.getAnswerList())
			.playerList(event.getPlayerList())
			.build();

		model.addAttribute("event",eventDto);



		return "event_detail";
	}
}
