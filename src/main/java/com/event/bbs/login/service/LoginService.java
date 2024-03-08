package com.event.bbs.login.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.event.bbs.login.entity.AdminUser;
import com.event.bbs.web.dto.requestDto.LoginFormDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final AdminUserService adminUserService;


	public boolean login(LoginFormDto loginFormDto , BindingResult bindingResult){

		if(findByLoginId(loginFormDto.getLoginId(), loginFormDto.getPassword())== null) {
			return false;
		}else{
			//todo: 로그인 성공 처리
			return true;
		}
	}


	public AdminUser findByLoginId(String loginId, String password){

		Optional<AdminUser> adminUser = adminUserService.findByLoginId(loginId);

		return adminUser.filter(x-> x.getPassword().equals(password)).orElse(null);
	}
}
