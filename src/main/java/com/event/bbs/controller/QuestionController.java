package com.event.bbs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.event.bbs.dto.requestDto.QuestionReqDto;
import com.event.bbs.entity.Event;
import com.event.bbs.service.EventService;
import com.event.bbs.service.QuestionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

	private final QuestionService questionService;
	@PostMapping("/create/{id}")
	public String create(@RequestBody List<QuestionReqDto> QuestionList, @PathVariable("id") Integer id) {

		questionService.create(QuestionList, id);

		return String.format("redirect:/event/detail/%s", id);
	}

}
