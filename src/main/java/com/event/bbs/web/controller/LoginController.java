package com.event.bbs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.event.bbs.login.entity.AdminUser;
import com.event.bbs.login.service.LoginService;
import com.event.bbs.web.common.ErrorMessage;
import com.event.bbs.web.common.SessionConst;
import com.event.bbs.web.dto.requestDto.LoginFormDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
	public String login(@Validated @ModelAttribute("loginForm") LoginFormDto loginFormDto , BindingResult bindingResult,
		HttpServletRequest httpServletRequest){

		if (bindingResult.hasErrors()) {
			return "login";
		}

		AdminUser adminUser  = loginService.login(loginFormDto);

		//로그인 처리 실패
		if(adminUser==null){
			bindingResult.reject(ErrorMessage.LOGIN_FAIL.getName(), ErrorMessage.LOGIN_FAIL.getExplain());
			return "login";
		}

		//로그인 처리 성공
		
		//세션에 있으면 있는 세션 반환, 없으면 신규 세션 생성
		HttpSession httpSession = httpServletRequest.getSession();

		//세션에 로그인 회원 정보 보관
		httpSession.setAttribute(SessionConst.LOGIN_MEMBER,adminUser);

		return "redirect:/event/list";
	}

	@PostMapping("logout")
	public String logout(HttpServletRequest request){

			HttpSession session = request.getSession(false);
			if(session!=null){
				session.invalidate();
			}
			return "redirect:/";

	}

}
