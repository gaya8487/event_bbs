package com.event.bbs.web.service;

import com.event.bbs.web.service.AdminUserService;
import org.springframework.stereotype.Service;

import com.event.bbs.web.entity.AdminUser;
import com.event.bbs.web.dto.requestDto.LoginFormDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final AdminUserService adminUserService;


	public AdminUser login(LoginFormDto loginFormDto ){
		return adminUserService.findByLoginId(loginFormDto.getLoginId())
			.filter(x->x.getPassword().equals(loginFormDto.getPassword()))
			.orElse(null);
	}


	public AdminUser findByLoginId(String loginId, String password){

		return  adminUserService.findByLoginId(loginId)
			.filter(x-> x.getPassword().equals(password)).orElse(null);
	}
}
