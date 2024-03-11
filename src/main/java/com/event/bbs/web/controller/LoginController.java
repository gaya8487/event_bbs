package com.event.bbs.web.controller;

import com.event.bbs.web.service.LoginService;
import com.event.bbs.web.dto.requestDto.LoginFormDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;
	@GetMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginFormDto loginFormDto){

		return "login_form";
	}

}
