package com.event.bbs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.event.bbs.login.service.LoginService;
import com.event.bbs.web.dto.requestDto.LoginFormDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;
	@GetMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginFormDto loginFormDto){

		return "login";
	}

	@PostMapping("login")
	public String login(@ModelAttribute("loginForm") LoginFormDto loginFormDto , BindingResult bindingResult){

		if (bindingResult.hasErrors()) {
			return "login";
		}

		//로그인 처리 실패
		if(loginService.login(loginFormDto,bindingResult)==false){
			bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
			return "login";
		}
		
		//로그인 처리 성공
		return "redirect:/event/list";

	}


}
