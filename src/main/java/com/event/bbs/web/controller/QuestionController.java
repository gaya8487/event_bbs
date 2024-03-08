package com.event.bbs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.bbs.web.service.QuestionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

	private final QuestionService questionService;
	@PostMapping("/create/{id}")
	public String create(Model model, @PathVariable("id") int id ,@RequestParam(value="addQuiz") String quiz) {

		//todo:질문 내용 validation

		log.info("info debug={}, 퀴즈 내용", quiz);
		questionService.create(quiz , id);

		return String.format("redirect:/event/detail/%s", id);
	}


}
