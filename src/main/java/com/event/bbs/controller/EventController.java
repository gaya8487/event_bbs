package com.event.bbs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.event.bbs.dto.EventDto;
import com.event.bbs.dto.QuestionDto;
import com.event.bbs.entity.Event;
import com.event.bbs.entity.Question;
import com.event.bbs.service.EventService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

	private final EventService eventService;
	@GetMapping("/list")
	public String list(Model model){

		List<Event> events = eventService.getEventList();

		List<EventDto>  eventList =  events.stream().map(x->  {
			return EventDto.builder()
			  .id(x.getId())
			  .subject(x.getSubject())
			  .content(x.getContent())
			  .createDate(x.getCreateDate())
			  .questionList(x.getQuestionList().stream().map(q ->
					  QuestionDto.builder()
						  .qNum(q.getQNum())
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

		Event event = eventService.getEvent(id);

		List<QuestionDto> questionDtoList = event.getQuestionList().stream().map(x ->
				QuestionDto.builder()
				.qNum(x.getQNum())
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
