package com.event.bbs.web.controller;

import com.event.bbs.web.entity.AdminUser;
import com.event.bbs.web.service.AdminUserService;
import com.event.bbs.web.dto.EventDto;
import com.event.bbs.web.dto.QuestionDto;
import com.event.bbs.web.entity.Event;
import com.event.bbs.web.response.BaseException;
import com.event.bbs.web.response.ErrorStatus;
import com.event.bbs.web.service.EventService;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

	private final EventService eventService;
	private final AdminUserService adminUserService;

	@GetMapping("/list")
	public String list(Model model , Principal principal){

		//principal spring security에서 제공해주는 객체
		//principal.getName() //사용자 조회

		//세션에 회원 데이터가 없으면 홈으로
		if(principal == null){
			return "redirect: /";
		}

		//로그인한 사용자가 만든 이벤트만 조회
		Optional<AdminUser> adminUser =   adminUserService.findByLoginId(principal.getName());

		if(!adminUser.isPresent()){
			throw new BaseException(ErrorStatus.DATA_NOT_FOUND);
		}

		int userid =  adminUser.get().getId();

		List<Event> events = eventService.getEventListByAdminUserId(userid);

		if(events.size()>0){
			List<EventDto>  eventList =  events.stream().map(x->  {
				return EventDto.builder()
						.id(x.getId())
						.subject(x.getSubject())
						.content(x.getContent())
						.createDate(x.getCreateDate())
						.answerList(x.getAnswerList())
						.playerList(x.getPlayerList())
						.build();
			}).collect(Collectors.toList());

			model.addAttribute("eventList",eventList);

		}else{

			List<EventDto> eventList = Arrays.asList(EventDto.builder().build());

		}

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
