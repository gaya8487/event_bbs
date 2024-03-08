package com.event.bbs.login.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.event.bbs.login.entity.AdminUser;
import com.event.bbs.login.repository.AdminUserRepository;
import com.event.bbs.web.dto.requestDto.AdminUserFormDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminUserService {

	private final AdminUserRepository adminUserRepository;

	public void create(AdminUserFormDto  adminUserFormDto){

		//todo: id가 겹치면 에러처리

		adminUserRepository.save(
			AdminUser.builder()
				.email(adminUserFormDto.getEmail())
				.name(adminUserFormDto.getName())
				.loginId(adminUserFormDto.getLoginId())
				.password(adminUserFormDto.getPassword())
			.build());
	}
	public Optional<AdminUser> findByLoginId(String  loginId) {

		Optional<AdminUser> adminUser = adminUserRepository.findByLoginId(loginId);
		return  adminUser;
	}
}
